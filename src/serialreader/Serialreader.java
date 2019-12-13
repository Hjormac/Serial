


package serialreader;

import com.fazecast.jSerialComm.*;
import java.io.IOException;










public class Serialreader{

    
static public void main(String[] args) throws IOException
{
   
    //haetaan viimeisin _ID arvo
    // toteutus huono suurilla datamäärillä
    // korvataan tulevaisuudessa rajapinnan routella joka palauttaa _ID arvon
    
    int i = JsonReader.readJsonFromUrl("http://ec2-3-88-203-40.compute-1.amazonaws.com/Wdata");
    int id = 0;
    
    if(i>0) {
       id = i; 
    }
    
    //Luetaan bufferi tyhjäksi roskadatasta;
    
   SerialPort comPort = SerialPort.getCommPorts()[0];
   comPort.openPort();
   
   try { 
                   
        byte[] readBuffer = new byte[comPort.bytesAvailable()];
        int numRead = comPort.readBytes(readBuffer, readBuffer.length);
        System.out.println("Read " + numRead + " bytes.");
        
   }
   catch (Exception e) { e.printStackTrace(); 
   }
   
   
   //aloitetaan sarjaportin datan kuuntelu
   
   comPort.addDataListener(new PacketListener(i));
   
   
   
} 
}