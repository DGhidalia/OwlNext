package internals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

public class Rate extends Thread{

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

    private void getRate() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        JSONObject obj;
        Gson gson = new GsonBuilder().create();
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
                obj = new JSONObject(responseContent.toString()).getJSONObject("rate");
                reader.close();
                this.currentRate = obj.getDouble("rate");
            }
        }
            catch(Exception e){
                e.printStackTrace();
            } finally{
                connection.disconnect();
            }
        }
}
