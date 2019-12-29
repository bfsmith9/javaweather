import java.io.*;
import java.net.*;

public class GetRegionalRadar {
  public GetRegionalRadar() throws Exception {
  // constructor
  // From NOAA
    String radarplace = "https://radar.weather.gov/Conus/RadarImg/northeast.gif";
      //String radarplace = "http://vortex.accuweather.com/adc2010/images/radar/610x480-overlay.gif";
  	URL url2 = new URL(radarplace);    
  	URLConnection conn2 = url2.openConnection();
  	InputStream is = conn2.getInputStream();
  	FileOutputStream fos = new FileOutputStream( "regionalradar.gif" );
    byte buffer[] = new byte[1024];
    int nRead = -1;
    while( (nRead = is.read(buffer)) > 0 ) {
      fos.write( buffer, 0, nRead ); }
    fos.flush();
    fos.close();
    is.close();
  } // end constructor

  public static void main (String[] args) throws Exception {
    new GetRegionalRadar();
  } // end main

} // GetRadar4 class
/* Code for getting image from URL from this Webpage:
 * http://www.liviutudor.com/tech/programming/java/ 
 */
