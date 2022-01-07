import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {

    private static HttpURLConnection connection;

    public static void main (String[] args){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONObject obj = null;
        Gson gson = new GsonBuilder().create();
        try {
            URL url = new URL("https://api.ibanfirst.com/PublicAPI/Rate/EURUSD");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            //System.out.println(status);

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            } else{
                File file;
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                obj = new JSONObject(responseContent.toString()).getJSONObject("rate");
                reader.close();
            }
            System.out.println(obj.getDouble("rate"));

            Rate rate = new Rate(obj.getString("date"), obj.getDouble("rate"), obj.getString("instrument"));
            //getJSONObject
            //Rate rate = gson.fromJson(obj.getJSONObject())
        }
        catch(Exception e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}
