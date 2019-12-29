import java.io.*;
//import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConditionParser {

  public ConditionParser() throws Exception {

    try {
      File file = new File("retrieved-conditions.txt");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      System.out.println("Root element " + doc.getDocumentElement().getNodeName());
      NodeList nodeLst = doc.getElementsByTagName("current_observation");
      System.out.println("Weather Conditions");

      // Open a file to hold conditions text
      FileWriter writeConditions = null;
      try {
        writeConditions = new FileWriter("conditions.txt");
      } catch (IOException x) {System.err.println("IOExcpn:" + x); }

      PrintWriter printConditions = new PrintWriter(writeConditions);

      for (int s = 0; s < nodeLst.getLength(); s++) {
        Node fstNode = nodeLst.item(s);
    
        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
          Element fstElmnt = (Element) fstNode;

          NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("observation_time_rfc822");
          Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
          NodeList fstNm = fstNmElmnt.getChildNodes();
          printConditions.println("Date: "  + ((Node) fstNm.item(0)).getNodeValue());

          NodeList fstANmElmntLst = fstElmnt.getElementsByTagName("weather");
          Element fstANmElmnt = (Element) fstANmElmntLst.item(0);
          NodeList fstANm = fstANmElmnt.getChildNodes();
          printConditions.println("Weather: "  + ((Node) fstANm.item(0)).getNodeValue());

          NodeList secondNmElmntLst = fstElmnt.getElementsByTagName("temp_f");
          Element secondNmElmnt = (Element) secondNmElmntLst.item(0);
          NodeList secondNm = secondNmElmnt.getChildNodes();
          printConditions.println("Temperature: " + ((Node) secondNm.item(0)).getNodeValue() + " F");

          NodeList thirdNmElmntLst = fstElmnt.getElementsByTagName("wind_mph");
          Element thirdNmElmnt = (Element) thirdNmElmntLst.item(0);
          NodeList thirdNm = thirdNmElmnt.getChildNodes();
          printConditions.println("Windspeed: " + ((Node) thirdNm.item(0)).getNodeValue() + " mph");


          NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("wind_dir");
          Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
          NodeList lstNm = lstNmElmnt.getChildNodes();
          printConditions.println("Wind Direction: " + ((Node) lstNm.item(0)).getNodeValue());
        } // end if-statement

        printConditions.close();

      } // end for loop
    } catch (Exception e) {
      e.printStackTrace();
      }
  } // end constructor
 public static void main(String argv[]) throws Exception {
     new ConditionParser();

 } // end main
} // end ConditionParser class
