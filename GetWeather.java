import java.io.*;
import java.net.*;

public class GetWeather {
  public GetWeather() throws Exception {
  // Contructor
  // Removed the conn parts below - experiment
  // May 2018 - had to change URL b/c NOAA was redirecting
    String netplace = "https://forecast.weather.gov/product.php?site=BTV&product=ZFP&issuedby=BTV";
    URL url = new URL(netplace);    
    //URLConnection conn = url.openConnection();
    BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()));
    //BufferedReader in = new BufferedReader( new InputStreamReader(conn.getContent()));
    String s = null;
// --------------------------------------------------
    FileWriter FW = null;
    try {
      FW = new FileWriter("retrieved-weather.txt");
    } catch(IOException x) {System.err.println("IOExcpn:" + x); }

    PrintWriter PW = new PrintWriter(FW);

    // --------------------------------------------------
    while ( (s=in.readLine()) != null) {
//    System.out.println(s);
      PW.println(s);
    }
    in.close();
    PW.close();

  }
  public static void main (String[] args) throws Exception {
    new GetWeather();
  }
}
