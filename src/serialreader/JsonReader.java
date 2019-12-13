
package serialreader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;



public class JsonReader {

 

  public static int readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      StringBuilder content = new StringBuilder();
      String line;
      
      while (null != (line = br.readLine())) {
      content.append(line);
      }     
         
      Object obj = JSONValue.parse(content.toString());
      JSONArray finalResult=(JSONArray)obj;
      
      
      
      
      
      return finalResult.size();
    } finally {
      is.close();
    }
    
  }

  
}