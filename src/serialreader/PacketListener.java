

package serialreader;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;



 class PacketListener implements SerialPortPacketListener
{
   
    private int id = 0;
    
    
    public PacketListener(int i) {
        this.id = i;
    }
    
    
    
    
    @Override
   public int getListeningEvents() { 
       return SerialPort.LISTENING_EVENT_DATA_RECEIVED; 
   }

   @Override
   public int getPacketSize() { return 10; }

   @Override
   public void serialEvent(SerialPortEvent event)
   {
      Poster postman = new Poster();
      String collectionString = "";
      double temperature=0;
      double humidity=0;
      String time ="";
      String date ="";
      //luetaan data sarjaportilta
      
      byte[] newData = event.getReceivedData();
      System.out.println("Received data of size: " + newData.length);
      
      //tallennetaan saatu data stringiin;
      
      for (int i = 0; i < newData.length; ++i) {
         System.out.print((char)newData[i]);                          
       collectionString += (char)newData[i]; 
            
      }
      System.out.println("\n");
      
      //jaetaan saatu stringi osiin: lämpötila,kosteus
      
      String[] stringSplitter = collectionString.split("m");                
      String temp = stringSplitter[0];
      String humi = stringSplitter[1];
      
      
      // muutetaan saadut stringit double muotoon
      
       temperature = Double.parseDouble(temp);
       humidity = Double.parseDouble(humi);
       
       
      // luetaan kellonaika muuttujaan
      
       LocalTime timeobject = LocalTime.now();
       time = timeobject.toString();
       
       LocalDate dateobject = LocalDate.now();
       date = dateobject.toString();
       System.out.println(time);
       System.out.println(date);
       System.out.println("\n");
      
      //System.out.println(collectionString);
      //System.out.println(temperature);
      //System.out.println(humidity);
      //System.out.println(time);
      //System.out.println("\n");
      
        
        try {
            //lähetetään saatu data http yhteydellä rajapinnalle
            postman.postData(id, temperature, humidity, time,date);
            id++;
            System.out.println("\n");
        } catch (IOException ex) {
            Logger.getLogger(PacketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}