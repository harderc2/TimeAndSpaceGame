/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enginealgorithm;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author harderc2
 */
public class Game {
    public int length;
    public String title;
    public String locNames[] = new String[length];
    public double lats[] = new double[length];
    public double longs[] = new double[length]; 
    public String startTimes[] = new String[length];
    public String endTimes[] = new String[length];
    
    /**
 * Constructor class
 * take in the name of the XML file
 * from there, in one thing it should be able to get the title, size and
 * fill all of the objects
     * @param fileName
 */
    public Game(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();
            title = doc.getElementsByTagName("title").item(0).getTextContent();
            NodeList list = doc.getElementsByTagName("location"); //this gets the list/number of locations
            length = list.getLength();
            for (int i = 0; i < length; i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                  // get the location name
                    String loc = element.getAttribute("id");
                  // get data variables
                    String lat = element.getElementsByTagName("latitude").item(0).getTextContent();
                    String lon = element.getElementsByTagName("longitude").item(0).getTextContent();
                    String startT = element.getElementsByTagName("startTime").item(0).getTextContent();
                    String endT = element.getElementsByTagName("endTime").item(0).getTextContent();
                    locNames[i] = loc;
                    lats[i] = Float.parseFloat(lat);
                    longs[i] = Float.parseFloat(lon);
                    startTimes[i] = startT;
                    endTimes[i] = endT;
                }
            }
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



