import java.io.*;
import java.net.*;

public class GetLargeRadar extends GetRadar {
  public GetLargeRadar() throws Exception {
  // constructor
      String radarPlaceLarge = "http://sirocco.accuweather.com/nx_mosaic_640x480c/SIR/inmSIRNH_.gif";
    // For smaller screen
    //String radarplace = "http://sirocco.accuweather.com/nx_mosaic_400x300c/SIR/inmSIRNH_.gif";
    // From NOAA
    //String radarplace = "http://radar.weather.gov/ridge/Conus/RadarImg/northeast.gif";
  	URL url2 = new URL(radarPlaceLarge);    
  	URLConnection conn2 = url2.openConnection();
  	InputStream is = conn2.getInputStream();
  	FileOutputStream fosLarge = new FileOutputStream( "largeradar.gif" );
    byte buffer[] = new byte[1024];
    int nRead = -1;
    while( (nRead = is.read(buffer)) > 0 ) {
      fosLarge.write( buffer, 0, nRead ); }
    fosLarge.flush();
    fosLarge.close();
    is.close();
  } // end constructor

  public static void main (String[] args) throws Exception {
    new GetLargeRadar();
  } // end main

} // GetRadar4 class
/* Code for getting image from URL from this Webpage:
 * http://www.liviutudor.com/tech/programming/java/ 
 */
