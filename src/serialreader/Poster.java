
package serialreader;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;







public class Poster {
    
   private HttpPost request;
   private CloseableHttpClient httpClient;
   
    public Poster()  {
    
        this.request = new HttpPost("http://ec2-3-88-203-40.compute-1.amazonaws.com/Wdata/add");
        this.httpClient = HttpClientBuilder.create().build();
    
    }

    public void postData(int id, double temp, double hum, String time, String date) throws IOException {
    
    //Rakentaa saaduista metodin arvoista JSON-objektin ja lähettää sen http-osoitteeseen    
    
    
    
    JSONObject json = new JSONObject();
    json.put("_id", id);
    json.put("temperature", temp);
    json.put("humidity", hum);
    json.put("time", time);
    json.put("date", date);

        try {
    
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().toString());
            System.out.println("\n");
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            httpClient.close();
        }
       
    }   

}
