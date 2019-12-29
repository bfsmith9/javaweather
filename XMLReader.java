import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

 public static void main(String argv[]) {

  try {
  File file = new File("retrieved-conditions.txt");
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName("current_observation");
  System.out.println("Weather Conditions");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
      Element fstElmnt = (Element) fstNode;

      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("weather");
      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
      NodeList fstNm = fstNmElmnt.getChildNodes();
      System.out.println("Weather : "  + ((Node) fstNm.item(0)).getNodeValue());

      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("temp_f");
      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
      NodeList lstNm = lstNmElmnt.getChildNodes();
      System.out.println("temp_f: " + ((Node) lstNm.item(0)).getNodeValue());
    }

  }
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
}
