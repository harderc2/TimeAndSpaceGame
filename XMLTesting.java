/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enginealgorithm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;

/**
 *
 * @author harderc2
 */
public class XMLTesting {


  private static final String FILENAME = "input.xml";

  public static void main(String[] args) throws SAXException, IOException {
    String[][] doubleTest = {{"one", "two", "three"}, {"four", "five"}, {"six", "seven", "eight", "nine"}};
    System.out.print("const startTimes = [");
        int i=0, j=0;
            for (i = 0; i < doubleTest.length ; i++) {
                System.out.print("[");
                for (j = 0; j < doubleTest[i].length-1 ; j++) {
                    System.out.print(" \"" + doubleTest[i][j] + "\",");
                }
                System.out.print(" \"" + doubleTest[i][j] + "\"]");
                if (i == doubleTest.length-1) {
                    System.out.print("]");
                } else {
                    System.out.print(",");
                }
            }
  }
  public static String getTitle() throws SAXException, IOException {
      String title = "";
      try {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document doc = db.parse(new File(FILENAME));
          doc.getDocumentElement().normalize();
          title = doc.getElementsByTagName("title").item(0).getTextContent();
      } catch (ParserConfigurationException ex) {
          Logger.getLogger(XMLTesting.class.getName()).log(Level.SEVERE, null, ex);
      }
      return title;
  }
  public static int getXMLSize() throws SAXException, IOException {
      int length = 0;
      try {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document doc = db.parse(new File(FILENAME));
          doc.getDocumentElement().normalize();
          NodeList list = doc.getElementsByTagName("location"); //this gets the list/number of locations
          length = list.getLength();
      } catch (ParserConfigurationException ex) {
          Logger.getLogger(XMLTesting.class.getName()).log(Level.SEVERE, null, ex);
      }
      return length;
  }
  public static int getTimeTog() throws SAXException, IOException {
      int tog = 0;
      try {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document doc = db.parse(new File(FILENAME));
          doc.getDocumentElement().normalize();
          tog = Integer.parseInt(doc.getElementsByTagName("timeTog").item(0).getTextContent());
      } catch (ParserConfigurationException ex) {
          Logger.getLogger(XMLTesting.class.getName()).log(Level.SEVERE, null, ex);
      }
      return tog;
  }
  public static void readData( String locNames[], 
            double lats[], double longs[], String startTimes[], String endTimes[],
            int locID[], int onTime[], int wrongTime[]) {
      // Instantiate the Factory
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      int length = 0;
      try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(FILENAME));

          // optional, but recommended
          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          // get <staff>
          NodeList list = doc.getElementsByTagName("location"); //this gets the list/number of locations
          length = list.getLength();
          for (int i = 0; i < length; i++) {

              Node node = list.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // get staff's attribute
                  String loc = element.getAttribute("id");
                  

                  // get text
                  String lat = element.getElementsByTagName("latitude").item(0).getTextContent();
                  String lon = element.getElementsByTagName("longitude").item(0).getTextContent();
                  String startT = element.getElementsByTagName("startTime").item(0).getTextContent();
                  String endT = element.getElementsByTagName("endTime").item(0).getTextContent();
                  String lID = element.getElementsByTagName("locID").item(0).getTextContent();
                  String onT = element.getElementsByTagName("onTime").item(0).getTextContent();
                  String wrongT = element.getElementsByTagName("wrongTime").item(0).getTextContent();
                  
                  locNames[i] = loc;
                  lats[i] = Float.parseFloat(lat);
                  longs[i] = Float.parseFloat(lon);
                  startTimes[i] = startT;
                  endTimes[i] = endT;
                  locID[i] = Integer.parseInt(lID);
                  onTime[i] = Integer.parseInt(onT);
                  wrongTime[i] = Integer.parseInt(wrongT);
                  
              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }
      
  }

}
