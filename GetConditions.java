import java.io.*;
import java.net.*;

public class GetConditions {
  public GetConditions() throws Exception {
  // Contructor
      // May 2018 - had to change URL b/c NOAA was redirecting
      // www to w1!
      //String netplace = "http://www.weather.gov/xml/current_obs/KBTV.xml";
      String netplace = "https://w1.weather.gov/xml/current_obs/KBTV.xml";
    URL url = new URL(netplace);    
    URLConnection conn = url.openConnection();
    BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
    String s = null;
// --------------------------------------------------
    FileWriter FW = null;
    try {
      FW = new FileWriter("retrieved-conditions.txt");
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
    new GetConditions();
  }
}
