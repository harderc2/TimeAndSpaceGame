/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enginealgorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harderc2
 */
public class EngineAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File html = new File("test.html");
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(html));
            bw1.write("<!DOCTYPE html>\n");
            bw1.write("<html lang=\"en\">\n");
            bw1.write("<head>\n<meta charset=\"utf-8\">\n"
                    + "<title>Time and Space</title>\n"
                    + "<script src=\"script.js\"></script>\n<head>\n");
            bw1.write("<body>\n<h1>Time Trial Game</h1>\n"
                    + "<button onclick=\"getLocation()\">Show Location</button>"
                    + "\n<p id=\"testLoc\">LOCATION</p>\n"
                    + "<button onclick=\"getTime()\">Show Time</button>\n"
                    + "<p id=\"testTime\">TIME</p>\n<h2>You need to get to:</h2>"
                    + "\n<p id=\"location\">My House</p>\n"
                    + "<input type=\"hidden\" id = \"latVal\" value=\"39.5149312\">\n"
                    + "<input type=\"hidden\" id = \"longVal\" value=\"-84.7413248\">\n"
                    + "<h3>Between</h3>\n<p id=\"timeVal\">10:00-11:00</p>\n"
                    + "<input type=\"hidden\" id = \"timeA\" value=\"10:00\">\n"
                    + "<input type=\"hidden\" id = \"timeB\" value=\"11:00\">\n"
                    + "<button onclick=\"check()\">I'm Here</button>\n"
                    + "<p id=\"locCheck\"></p>\n<p id=\"timeCheck\"></p>\n"
                    + "<ul id=\"inventory\">\n</ul>\n</body>\n</html>");
            
            bw1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File js = new File("script.js");
        try {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(js));
            bw2.write("var loc = document.getElementById(\"testLoc\");\n"
                    + "var time = document.getElementById(\"testTime\");\n"
                    + "var count = 0;\nvar rightSpot = false;\nvar done = false;\n"
                    + "const locations = [\"My house\", \"Farmer School of Business\", \"Western Dining Hall\"];\n"
                    + "const latitudes = [39.51624142364317, 39.511397732343006, 39.50475521376];\n"
                    + "const longitudes = [-84.73920880600052, -84.72974267090659, -84.727622029366];\n"
                    + "const startTimes = [\"10:00\", \"11:00\", \"12:00\"];\n"
                    + "const endTimes = [\"11:00\", \"12:00\", \"1:00\"];\n");
            bw2.write("function getLocation() {\n"
                    + "if (navigator.geolocation) {\n"
                    + "navigator.geolocation.getCurrentPosition(showPosition);\n"
                    + "} else { \n"
                    + "document.getElementById(\"testLoc\").text(\"Geolocation is not supported by this browser.\");\n"
                    + "}\n}\n");
            bw2.write("function showPosition(position) {\n" +
"  	        document.getElementById('testLoc').innerHTML = \"Latitude: \" + position.coords.latitude + \n" +
"  	    	\"<br>Longitude: \" + position.coords.longitude;\n" +
"	    }");
            bw2.write("function getTime() {\n" +
"			var dt = new Date();\n" +
"			let hours = dt.getHours();\n" +
"			let minutes = dt.getMinutes();\n" +
"			var currentTime = \"\" + hours + \":\" + minutes;\n" +
"  	        document.getElementById('testTime').innerHTML = \"Time: \" + currentTime;\n" +
"	    }");
            bw2.write("function checkPosition(position, rightLoc) { \n" +
"			var latDiff = Math.abs(position.coords.latitude - document.getElementById('latVal').value);\n" +
"			var longDiff = Math.abs(position.coords.longitude - document.getElementById('longVal').value);\n" +
"			if (latDiff <= .01 && longDiff <= .01) {\n" +
"				document.getElementById('locCheck').innerHTML = \"Location Matches!\";\n" +
"				count++;\n" +
"				document.getElementById('location').innerHTML = locations[count];\n" +
"				document.getElementById('longVal').value = longitudes[count];\n" +
"				document.getElementById('latVal').value = latitudes[count]; \n" +
"				document.getElementById('timeVal').innerHTML = startTimes[count] + \"-\" + endTimes[count];\n" +
"				rightSpot = true;\n" +
"				rightLoc = true;\n" +
"				console.log(rightSpot);\n" +
"				done = true;\n" +
"				checkTime();\n" +
"			} else {\n" +
"				document.getElementById('locCheck').innerHTML = \"Location Does Not Match!\";\n" +
"				document.getElementById('timeCheck').innerHTML = \"\";\n" +
"				righSpot = false;\n" +
"				rightLoc = false;\n" +
"				console.log(rightSpot);\n" +
"				done = true;\n" +
"			}\n" +
"		}");
            bw2.write("function checkTime() {\n" +
"			var dt = new Date();\n" +
"			var correct = false;\n" +
"			let hours = dt.getHours();\n" +
"			let minutes = dt.getMinutes();\n" +
"			var currentTime = \"\" + hours + \":\" + minutes;\n" +
"			if (currentTime >= document.getElementById('timeA').value && \n" +
"				currentTime <= document.getElementById('timeB').value) {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Matches!\";\n" +
"				addToInv(0);\n" +
"				correct = true;\n" +
"			} else {\n" +
"				document.getElementById('timeCheck').innerHTML = \"Time Does Not Match!\";\n" +
"				addToInv(1);\n" +
"				correct = false;\n" +
"			}\n" +
"			document.getElementById('timeA').value = startTimes[count];  \n" +
"			document.getElementById('timeB').value = endTimes[count];\n" +
"			return correct;\n" +
"		}");
            bw2.write("function addToInv(value) {\n" +
"			var li = document.createElement(\"li\");\n" +
"			if (value == 0) {\n" +
"				li.appendChild(document.createTextNode(\"Gold Trophy\"));\n" +
"				document.getElementById('inventory').appendChild(li);\n" +
"			} else if (value == 1) {\n" +
"				li.appendChild(document.createTextNode(\"Garbage Trophy\"));\n" +
"				document.getElementById('inventory').appendChild(li);\n" +
"			}\n" +
"		}");
            bw2.write("function retrieveLocation(rightLoc, callback) {\n" +
"			navigator.geolocation.getCurrentPosition(checkPosition);\n" +
"			callback();\n" +
"			console.log(rightLoc);\n" +
"			return rightLoc;\n" +
"		}");
            bw2.write("function check() {\n"
			+ "var rightLoc = false;\n" 
			+ "retrieveLocation(rightLoc, function() { rightLoc = rightSpot;})\n"
			+ "console.log(rightSpot);\n" 
			+ "console.log(rightLoc);\n" 
			+ "console.log(rightTime);\n" 
		+ "}\n");
            
            bw2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
