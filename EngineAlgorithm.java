/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enginealgorithm;

import static enginealgorithm.XMLTesting.getTimeTog;
import static enginealgorithm.XMLTesting.getTitle;
import static enginealgorithm.XMLTesting.getXMLSize;
import static enginealgorithm.XMLTesting.readData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author harderc2
 */
public class EngineAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int length = 0;
        int timeTog = 0;
        String gameTitle = "";
        try {
            length = getXMLSize();
            gameTitle = getTitle();
            timeTog = getTimeTog();
        } catch (SAXException ex) {
            Logger.getLogger(EngineAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EngineAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        String locNames[] = new String[length]; // {"Farmer", "Benton", "Kroger"};  // new String[length];
        double lats[] = new double[length]; //{39.51138898096187, 39.51077679831554, 39.507347186118785};  // new double[length];
        double longs[] = new double[length]; //{-84.72971716997401, -84.73355333891227, -84.75120186889184};  // new double[length];, , 
        String startTimes[] = new String[length]; //{"10:00", "11:00", "12:00"};  // new String[length];
        String endTimes[] = new String[length]; //{"11:00", "12:00", "1:00"};  // new String[length];
        int locID[] = new int[length];
        int onTime[] = new int[length];
        int wrongTime[] = new int[length];
        readData(locNames, lats, longs, startTimes, endTimes, locID, onTime, wrongTime);
        File html = new File("test.html");
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(html));
            bw1.write("<!DOCTYPE html>\n");
            bw1.write("<html lang=\"en\">\n");
            bw1.write("<head>\n<meta charset=\"utf-8\">\n"
                    + "<title>" + gameTitle + "</title>\n"
                    +"<link rel=\"stylesheet\" href=\"styles.css\">\n"
                    + "<script src=\"script.js\"></script>\n<head>\n");
            bw1.write("<body onload=\"fillData()\">\n<h1>" + gameTitle + "</h1>\n"
                    + "<button onclick=\"getLocation()\">Show Location</button>"
                    + "\n<p id=\"testLoc\">LOCATION</p>\n"
                    + "<button onclick=\"getTime()\">Show Time</button>\n"
                    + "<p id=\"testTime\">TIME</p>\n<h2>You need to get to:</h2>"
                    + "\n<p id=\"location\"></p>\n"
                    + "<input type=\"hidden\" id = \"latVal\" value=\"39.5149312\">\n"
                    + "<input type=\"hidden\" id = \"longVal\" value=\"-84.7413248\">\n"
                    + "<h3>Between</h3>\n<p id=\"timeVal\"></p>\n"
                    + "<input type=\"hidden\" id = \"timeA\" value=\"10:00\">\n"
                    + "<input type=\"hidden\" id = \"timeB\" value=\"11:00\">\n"
                    + "<button onclick=\"check()\">I'm Here</button>\n"
                    + "<p id=\"locCheck\"></p>\n<p id=\"timeCheck\"></p>\n"
                    + "<p id=\"points\" value=\"0\"></p>");
            
            bw1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File js = new File("script.js");
        try {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(js));
            bw2.write("let timeTog = " + timeTog + ";\n"
                    + "var loc = document.getElementById(\"testLoc\");\n"
                    + "var time = document.getElementById(\"testTime\");\n"
                    + "var count = 0;\nvar rightSpot = false;\nvar done = false;\nlet points = 0;\n");
            addData(bw2, length, locNames, lats, longs, startTimes, endTimes, locID, onTime, wrongTime);
            bw2.write("var hourOfPlay;\n" +
"var minOfPlay;\n" +
"var timeOfPlay;\n" +
"const getLocIDW = (element) => element == wrongTime[count];\n"
                    + "const getLocIDR = (element) => element == onTime[count];");
            bw2.write("function format(n){return (n<10? '0':'') + n;}\n" +
"function timeToMins(time) {\n" +
"  var mins = time.split(':');\n" +
"  return mins[0]*60 + +mins[1];\n" +
"}\n" +
"function timeFromMins(mins) {\n" +
"  var hour = (mins/60 |0) % 24;\n" +
"  var minutes = mins % 60;\n" +
"  return format(hour) + ':' + format(minutes);\n" +
"}\n" +
"function addTimes(t0, t1) {\n" +
"  return timeFromMins(timeToMins(t0) + timeToMins(t1));\n" +
"}");
            bw2.write("function fillData()	{\n" +
"                       document.getElementById('location').innerHTML = locations[0];\n" +
"                       document.getElementById('longVal').value = longitudes[0];\n" +
"                       document.getElementById('latVal').value = latitudes[0]; \n" +
"                       document.getElementById('timeVal').innerHTML = startTimes[0] + \"-\" + endTimes[0];\n" +
"					   if (timeTog == 0){\n" +
"							var dt = new Date();\n" +
"							let h = dt.getHours();\n" +
"							let m = dt.getMinutes();\n" +
"							timeOfPlay = \"\" + format(h) + \":\" + format(m);\n" +
"							document.getElementById('timeVal').innerHTML = timeOfPlay + \"-\" + addTimes(timeOfPlay, endTimes[0]);\n" +
"							document.getElementById('timeA').value = timeOfPlay;\n" +
"							document.getElementById('timeB').value = addTimes(timeOfPlay, endTimes[0]);\n" +
"					   }\n" +
"}\n");   
            bw2.write("function getLocation() {\n"
                    + "if (navigator.geolocation) {\n"
                    + "navigator.geolocation.getCurrentPosition(showPosition);\n"
                    + "} else { \n"
                    + "document.getElementById(\"testLoc\").text(\"Geolocation is not supported by this browser.\");\n"
                    + "}\n}\n");
            bw2.write("function showPosition(position) {\n" +
"  	        document.getElementById('testLoc').innerHTML = \"Latitude: \" + position.coords.latitude + \n" +
"  	    	\"<br>Longitude: \" + position.coords.longitude;\n" +
"	    }\n");
            bw2.write("function getTime() {\n" +
"			var dt = new Date();\n" +
"			let hours = dt.getHours();\n" +
"			let minutes = dt.getMinutes();\n" +
"			var currentTime = \"\" + hours + \":\" + minutes;\n" +
"  	        document.getElementById('testTime').innerHTML = \"Time: \" + currentTime;\n" +
"	    }\n");
            bw2.write("function checkPosition(position, rightLoc) { \n" +
"			var latDiff = Math.abs(position.coords.latitude - document.getElementById('latVal').value);\n" +
"			var longDiff = Math.abs(position.coords.longitude - document.getElementById('longVal').value);\n" +
"			if (latDiff <= .01 && longDiff <= .01) {\n" +
"				rightSpot = true;\n" +
"				rightLoc = true;\n" +
"				console.log(rightSpot);\n" +
"				done = true;\n" +
"				checkTime();\n" +
"				if (count != -1) {\n" +
"				document.getElementById('locCheck').innerHTML = \"Location Matches!\";\n" +
"				document.getElementById('location').innerHTML = locations[count];\n" +
"				document.getElementById('longVal').value = longitudes[count];\n" +
"				document.getElementById('latVal').value = latitudes[count]; \n" +
"				document.getElementById('timeVal').innerHTML = startTimes[count] + \"-\" + endTimes[count];\n" +
"				if (timeTog == 0){\n" +
"							var dt = new Date();\n" +
"							let h = dt.getHours();\n" +
"							let m = dt.getMinutes();\n" +
"							timeOfPlay = \"\" + format(h) + \":\" + format(m);\n" +
"							document.getElementById('timeVal').innerHTML = timeOfPlay + \"-\" + addTimes(timeOfPlay, endTimes[count]);\n" +
"							document.getElementById('timeA').value = timeOfPlay;\n" +
"							document.getElementById('timeB').value = addTimes(timeOfPlay, endTimes[count]);\n" +
"					   }\n" +
"				} else {\n" +
"					document.getElementById('gameOver').innerHTML = \"Game Over!!!\";\n" +
"				}\n" +
"			} else {\n" +
"				document.getElementById('locCheck').innerHTML = \"Location Does Not Match!\";\n" +
"				document.getElementById('timeCheck').innerHTML = \"\";\n" +
"				righSpot = false;\n" +
"				rightLoc = false;\n" +
"				console.log(rightSpot);\n" +
"				done = true;\n" +
"			}\n" +
"		}\n");
            bw2.write("function checkTime() {\n" +
"			if (timeTog == 1){\n" +
"			var dt = new Date();\n" +
"			var correct = false;\n" +
"			let hours = dt.getHours();\n" +
"			let minutes = dt.getMinutes();\n" +
"			var currentTime = \"\" + format(hours) + \":\" + format(minutes);\n" +
"			if (currentTime >= document.getElementById('timeA').value && \n" +
"				currentTime <= document.getElementById('timeB').value) {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Matches!\";\n" +
"				addToInv(0);\n" +
"				correct = true;\n" +
"				count = locID.findIndex(getLocIDR);\n" +
"			} else {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Does Not Match!\";\n" +
"				addToInv(1);\n" +
"				correct = false;\n" +
"				count = locID.findIndex(getLocIDW);\n" +
"			}\n" +
"			document.getElementById('timeA').value = startTimes[count];  \n" +
"			document.getElementById('timeB').value = endTimes[count];\n" +
"			return correct;\n" +
"			} else { \n" +
"				var dt = new Date();\n" +
"				var correct = false;\n" +
"				let hours = dt.getHours();\n" +
"				let minutes = dt.getMinutes();\n" +
"				var currentTime = \"\" + format(hours) + \":\" + format(minutes);\n" +
"				if (currentTime >= document.getElementById('timeA').value && \n" +
"				currentTime <= document.getElementById('timeB').value) {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Matches!\";\n" +
"				addToInv(0);\n" +
"				correct = true;\n" +
"				count = locID.findIndex(getLocIDR);	\n" +
"			} else {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Does Not Match!\";\n" +
"				addToInv(1);\n" +
"				correct = false;\n" +
"				count = locID.findIndex(getLocIDW);\n" +
"			}\n" +
"			document.getElementById('timeA').value = startTimes[count];  \n" +
"			document.getElementById('timeB').value = endTimes[count];\n" +
"			return correct;\n" +
"			}\n" +
"		}\n");
            bw2.write("function addToInv(value) {\n" +
"			var li = document.createElement(\"li\");\n" +
"			if (value == 0) {\n" +
"				points+= 10;\n" +
"				document.getElementById('points').innerHTML = \"Score: \" + points;\n" +
"			} else if (value == 1) {\n" +
"				points-= 5;\n" +
"				document.getElementById('points').innerHTML = \"Score: \" + points;\n" +
"			}\n" +
"		}\n");
            bw2.write("function retrieveLocation(rightLoc, callback) {\n" +
"			navigator.geolocation.getCurrentPosition(checkPosition);\n" +
"			callback();\n" +
"			console.log(rightLoc);\n" +
"			return rightLoc;\n" +
"		}\n");
            bw2.write("function check() {\n" +
"			if(count != -1) {\n" +
"var rightLoc = false;\n" +
"retrieveLocation(rightLoc, function() { rightLoc = rightSpot;})\n" +
"console.log(rightSpot);\n" +
"console.log(rightLoc);\n" +
"console.log(rightTime);\n" +
"			} else {\n" +
"				document.getElementById('gameOver').innerHTML = \"Game Over!!!\";\n" +
"			}\n" +
"}\n");
            
            bw2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void addData(BufferedWriter bw2, int length, String locNames[], 
            double lats[], double longs[], String startTimes[], String endTimes[],
            int locID[], int onTime[], int wrongTime[]){
        int i = 0;
        try {
            bw2.write("const locations = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" \"" + locNames[i] + "\",");
            }
            bw2.write(" \"" + locNames[i] + "\"];\n");
            bw2.write("const latitudes = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" " + lats[i] + ",");
            }
            bw2.write(" " + lats[i] + "];\n");
            bw2.write("const longitudes = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" " + longs[i] + ",");
            }
            bw2.write(" " + longs[i] + "];\n");
            bw2.write("const startTimes = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" \"" + startTimes[i] + "\",");
            }
            bw2.write(" \"" + startTimes[i] + "\"];\n");
            bw2.write("const endTimes = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" \"" + endTimes[i] + "\",");
            }
            bw2.write(" \"" + endTimes[i] + "\"];\n");
            bw2.write("const locID = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" " + locID[i] + ",");
            }
            bw2.write(" " + locID[i] + "];\n");
            bw2.write("const onTime = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" " + onTime[i] + ",");
            }
            bw2.write(" " + onTime[i] + "];\n");
            bw2.write("const wrongTime = [");
            for (i = 0; i < length-1 ; i++) {
                bw2.write(" " + wrongTime[i] + ",");
            }
            bw2.write(" " + wrongTime[i] + "];\n");
        } catch (IOException ex) {
            Logger.getLogger(EngineAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void addTimes(BufferedWriter bw2, String startTimes[][], String endTimes[][]){ 
        try {
            int i=0, j=0;
            bw2.write("const startTimes = [");
            for (i = 0; i < startTimes.length ; i++) {
                bw2.write("[");
                for (j = 0; j < startTimes[i].length-1 ; j++) {
                    bw2.write(" \"" + startTimes[i][j] + "\",");
                }
                bw2.write(" \"" + startTimes[i][j] + "\"]");
                if (i == startTimes.length-1) {
                    bw2.write("]");
                } else {
                    bw2.write(",");
                }
            }
            bw2.write("const endTimes = [");
            for (i = 0; i < endTimes.length ; i++) {
                bw2.write("[");
                for (j = 0; j < endTimes[i].length-1 ; j++) {
                    bw2.write(" \"" + endTimes[i][j] + "\",");
                }
                bw2.write(" \"" + endTimes[i][j] + "\"]");
                if (i == endTimes.length-1) {
                    bw2.write("]");
                } else {
                    bw2.write(",");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EngineAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
