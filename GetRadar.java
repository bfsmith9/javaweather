import java.io.*;
import java.net.*;

public class GetRadar {
  public GetRadar() throws Exception {
  // constructor
      //String radarplace = "http://sirocco.accuweather.com/nx_mosaic_640x480c/SIR/inmSIRNH_.gif";
    // For smaller screen
    String radarplace = "http://sirocco.accuweather.com/nx_mosaic_400x300c/SIR/inmSIRNH_.gif";
  	URL url2 = new URL(radarplace);    
  	URLConnection conn2 = url2.openConnection();
  	InputStream is = conn2.getInputStream();
  	FileOutputStream fos = new FileOutputStream( "radar.gif" );
    byte buffer[] = new byte[1024];
    int nRead = -1;
    while( (nRead = is.read(buffer)) > 0 ) {
      fos.write( buffer, 0, nRead ); }
    fos.flush();
    fos.close();
    is.close();
  } // end constructor

  public static void main (String[] args) throws Exception {
    new GetRadar();
  } // end main

} // GetRadar4 class
/* Code for getting image from URL from this Webpage:
 * http://www.liviutudor.com/tech/programming/java/ 
 */
