let timeTog = 0;
var loc = document.getElementById("testLoc");
var time = document.getElementById("testTime");
var count = 0;
var rightSpot = false;
var done = false;
let points = 0;
const locations = [ "loc 1", "loc 2"];
const latitudes = [ 39.516090393066406, 39.516090393066406];
const longitudes = [ -84.73914337158203, -84.73914337158203];
const startTimes = [ "00:00", "00:00"];
const endTimes = [ "00:00", "00:00"];
const locID = [ 1, 2];
const onTime = [ 2, 1];
const wrongTime = [ -1, -1];
var hourOfPlay;
var minOfPlay;
var timeOfPlay;
const getLocIDW = (element) => element == wrongTime[count];
const getLocIDR = (element) => element == onTime[count];
function format(n){return (n<10? '0':'') + n;}
function timeToMins(time) {
  var mins = time.split(':');
  return mins[0]*60 + +mins[1];
}
function timeFromMins(mins) {
  var hour = (mins/60 |0) % 24;
  var minutes = mins % 60;
  return format(hour) + ':' + format(minutes);
}
function addTimes(t0, t1) {
  return timeFromMins(timeToMins(t0) + timeToMins(t1));
}function fillData()	{
                       document.getElementById('location').innerHTML = locations[0];
                       document.getElementById('longVal').value = longitudes[0];
                       document.getElementById('latVal').value = latitudes[0]; 
                       document.getElementById('timeVal').innerHTML = startTimes[0] + "-" + endTimes[0];
					   if (timeTog == 0){
							var dt = new Date();
							let h = dt.getHours();
							let m = dt.getMinutes();
							timeOfPlay = "" + format(h) + ":" + format(m);
							document.getElementById('timeVal').innerHTML = timeOfPlay + "-" + addTimes(timeOfPlay, endTimes[0]);
							document.getElementById('timeA').value = timeOfPlay;
							document.getElementById('timeB').value = addTimes(timeOfPlay, endTimes[0]);
					   }
}
function getLocation() {
if (navigator.geolocation) {
navigator.geolocation.getCurrentPosition(showPosition);
} else { 
document.getElementById("testLoc").text("Geolocation is not supported by this browser.");
}
}
function showPosition(position) {
  	        document.getElementById('testLoc').innerHTML = "Latitude: " + position.coords.latitude + 
  	    	"<br>Longitude: " + position.coords.longitude;
	    }
function getTime() {
			var dt = new Date();
			let hours = dt.getHours();
			let minutes = dt.getMinutes();
			var currentTime = "" + hours + ":" + minutes;
  	        document.getElementById('testTime').innerHTML = "Time: " + currentTime;
	    }
function checkPosition(position, rightLoc) { 
			var latDiff = Math.abs(position.coords.latitude - document.getElementById('latVal').value);
			var longDiff = Math.abs(position.coords.longitude - document.getElementById('longVal').value);
			if (latDiff <= .01 && longDiff <= .01) {
				rightSpot = true;
				rightLoc = true;
				console.log(rightSpot);
				done = true;
				checkTime();
				console.log(count);
				if (count != -1) {
				document.getElementById('locCheck').innerHTML = "Location Matches!";
				document.getElementById('location').innerHTML = locations[count];
				document.getElementById('longVal').value = longitudes[count];
				document.getElementById('latVal').value = latitudes[count]; 
				document.getElementById('timeVal').innerHTML = startTimes[count] + "-" + endTimes[count];
				if (timeTog == 0){
							var dt = new Date();
							let h = dt.getHours();
							let m = dt.getMinutes();
							timeOfPlay = "" + format(h) + ":" + format(m);
							document.getElementById('timeVal').innerHTML = timeOfPlay + "-" + addTimes(timeOfPlay, endTimes[count]);
							document.getElementById('timeA').value = timeOfPlay;
							document.getElementById('timeB').value = addTimes(timeOfPlay, endTimes[count]);
					   }
				} else {
					document.getElementById('gameOver').innerHTML = "Game Over!!!";
				}
			} else {
				document.getElementById('locCheck').innerHTML = "Location Does Not Match!";
				document.getElementById('timeCheck').innerHTML = "";
				righSpot = false;
				rightLoc = false;
				console.log(rightSpot);
				done = true;
			}
		}
function checkTime() {
			if (timeTog == 1){
			var dt = new Date();
			var correct = false;
			let hours = dt.getHours();
			let minutes = dt.getMinutes();
			var currentTime = "" + format(hours) + ":" + format(minutes);
			if (currentTime >= document.getElementById('timeA').value && 
				currentTime <= document.getElementById('timeB').value) {
				document.getElementById('timeCheck').innerHTML = "Time Matches!";
				addToInv(0);
				correct = true;
				count = locID.findIndex(getLocIDR);
			} else {
				document.getElementById('timeCheck').innerHTML = "Time Does Not Match!";
				addToInv(1);
				correct = false;
				count = locID.findIndex(getLocIDW);
			}
			document.getElementById('timeA').value = startTimes[count];  
			document.getElementById('timeB').value = endTimes[count];
			return correct;
			} else { 
				var dt = new Date();
				var correct = false;
				let hours = dt.getHours();
				let minutes = dt.getMinutes();
				var currentTime = "" + format(hours) + ":" + format(minutes);
				if (currentTime >= document.getElementById('timeA').value && 
				currentTime <= document.getElementById('timeB').value) {
				document.getElementById('timeCheck').innerHTML = "Time Matches!";
				addToInv(0);
				correct = true;
				count = locID.findIndex(getLocIDR);	
			} else {
				document.getElementById('timeCheck').innerHTML = "Time Does Not Match!";
				addToInv(1);
				correct = false;
				count = locID.findIndex(getLocIDW);
			}
			document.getElementById('timeA').value = startTimes[count];  
			document.getElementById('timeB').value = endTimes[count];
			return correct;
			}
		}
function addToInv(value) {
			var li = document.createElement("li");
			if (value == 0) {
				points+= 10;
				document.getElementById('points').innerHTML = "Score: " + points;
			} else if (value == 1) {
				points-= 5;
				document.getElementById('points').innerHTML = "Score: " + points;
			}
		}
function retrieveLocation(rightLoc, callback) {
			navigator.geolocation.getCurrentPosition(checkPosition);
			callback();
			console.log(rightLoc);
			return rightLoc;
		}
function check() {
	console.log(count);
			if(count != -1) {
var rightLoc = false;
retrieveLocation(rightLoc, function() { rightLoc = rightSpot;})
console.log(rightSpot);
console.log(rightLoc);
console.log(rightTime);
			} else {
				document.getElementById('gameOver').innerHTML = "Game Over!!!";
			}
}
