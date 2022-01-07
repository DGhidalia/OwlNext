package internals;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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

    public Rate(String currency1, String currency2){
        this.currency1 = currency1;
        this.currency2 = currency2;
    }

    public void run(){
        this.getRate();
        System.out.println("Le taux de change entre "+this.currency1+" et "+this.currency2+" est de : " + this.currentRate);
    }

    /**
     * Make a call to the API and extract the rate for two given currencies, then stores it into currentRate
     */
    private void getRate() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
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
                File file;
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                this.currentRate = new JSONObject(responseContent.toString()).getJSONObject("rate").getDouble("rate");
                reader.close();
            }
        }
            catch(Exception e){
                e.printStackTrace();
            } finally{
                connection.disconnect();
            }
        }
}
