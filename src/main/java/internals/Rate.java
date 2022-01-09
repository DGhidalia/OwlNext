package internals;

import backend.Backend;
import org.json.JSONObject;
import org.restlet.resource.ServerResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Thread Handling the API call, retrieve the rate information and
 */
public class Rate extends Thread{

    /**
     * Double containing the currentRate to return/store in the future
     */
    private Double currentRate;

    private static HttpURLConnection connection;

    private String currency1;
    private String currency2;

    private Backend backend_;

    public Rate(String currency1, String currency2, Backend backend){
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.backend_ = backend;
    }

    public void run(){
        RateData toSave = this.getRate();
        if(toSave == null){
            System.out.println("There is an issue with your pair of currencies, please review them");
        }else {
            System.out.println("The rate between " + this.currency1 + " and " + this.currency2 + " is : " + this.currentRate);
            this.backend_.getDatabase().addRate(toSave);
        }
    }

    /**
     * Make a call to the API and extract the rate for two given currencies, then stores it into currentRate
     */
    private RateData getRate() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        RateData rd = null;
        try {
            URL url = new URL("https://api.ibanfirst.com/PublicAPI/Rate/"+currency1+currency2);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                JSONObject obj = new JSONObject(responseContent.toString()).getJSONObject("rate");
                this.currentRate = new JSONObject(responseContent.toString()).getJSONObject("rate").getDouble("rate");
                rd = new RateData(this.currency1, this.currency2, obj.getString("date"), obj.getDouble("rate"));
                reader.close();
            }
        }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                connection.disconnect();
            }
        return rd;
        }
}
