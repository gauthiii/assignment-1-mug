
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html>
<head>
  <title>Snake Game Multiplayer</title>
  
  <style>
  
  .text"""),format.raw/*8.8*/("""{"""),format.raw/*8.9*/("""

"""),format.raw/*10.1*/("""color: white;
font-size:137%;
text-align: center;

"""),format.raw/*14.1*/("""}"""),format.raw/*14.2*/("""
  
  """),format.raw/*16.3*/("""body """),format.raw/*16.8*/("""{"""),format.raw/*16.9*/("""
    """),format.raw/*17.5*/("""width: 100%;
	height: 100%;
	padding: 0;
	border: none;
    margin: 0;
	
  """),format.raw/*23.3*/("""}"""),format.raw/*23.4*/("""  

"""),format.raw/*25.1*/("""#title """),format.raw/*25.8*/("""{"""),format.raw/*25.9*/("""
	 """),format.raw/*26.3*/("""color: White;
	 font-family: "Times New Roman", Times, serif;
	 font-size:250%;
	 text-align: center;
	
"""),format.raw/*31.1*/("""}"""),format.raw/*31.2*/("""

 """),format.raw/*33.2*/("""#score
  """),format.raw/*34.3*/("""{"""),format.raw/*34.4*/("""
	  """),format.raw/*35.4*/("""float: left;
	  width: 20%;
	  height: 80%;
	  color: white;
	  font-size:150%;
	  align-items: left;
	  justify-content: left;

  """),format.raw/*43.3*/("""}"""),format.raw/*43.4*/("""
  
   """),format.raw/*45.4*/("""#users
  """),format.raw/*46.3*/("""{"""),format.raw/*46.4*/("""
	  """),format.raw/*47.4*/("""float: left;
	  color: white;
	  font-size:200%;
	  align-items: left;
	  justify-content: left;
  """),format.raw/*52.3*/("""}"""),format.raw/*52.4*/("""
  
   
  """),format.raw/*55.3*/("""#board
  """),format.raw/*56.3*/("""{"""),format.raw/*56.4*/("""
	  """),format.raw/*57.4*/("""float: left;
	  align-items: centre;
	  justify-content: centre;
	  border: 2px solid white;
	  color: white;
  """),format.raw/*62.3*/("""}"""),format.raw/*62.4*/("""
  
  """),format.raw/*64.3*/("""#exit
  """),format.raw/*65.3*/("""{"""),format.raw/*65.4*/("""
	  """),format.raw/*66.4*/("""float: left;
	  align-items: centre;
	  justify-content: centre;
	  color: white;
"""),format.raw/*70.1*/("""}"""),format.raw/*70.2*/("""
  
  """),format.raw/*72.3*/("""</style>
 
</head>

<body bgcolor="black">

<div class="title" id="title">SNAKES IN THE GARDEN</div>
<div class="score" id = "score">
		<p id="sc">My Score:</p>
		<p id="sz">My Cells:</p>
		<p id="cl">My Colour:</p>
		<br>
		<p id="sl">Active Players:</p>

		
		<div class="users" id="players">
			<h3> Players - TOP 3 !!!</h3>
			<ul id="snakes_list"> 
				<li id="Rank1"> </li>
				<li id="Rank2"> </li>
				<li id="Rank3"> </li>
			</ul>	
		</div> 
</div>
<div class="board" id = "board">
	<canvas width="960" height="560" id="game"></canvas>
</div> 

<div class="exit" id = "exit">
	<button onclick="onBrowserTabExit()"> EXIT GAME </button>
</div> 

<script>
var canvas = document.getElementById('game');
var context = canvas.getContext('2d');
var grid = 16;
var count = 0;
var TotalPlayers = 0;
var score=0;
var running = false;
var joined=false;
var listenerAdded = false;
var mySnakeNo=-1;
var reason = "Idle time out!!";
var userSuggestion = "Press enter to play again";
var animation=0;
var date = new Date ();
var username;

var url;
var inputJson;

var snakes = [];

//var rgbcolor = [`gray`,`white`,`maroon`,`red`,`purple`,`fuchsia`,`green`,`lime`,`olive`,`yellow`,`navy`,`blue`,`teal`,`aqua`,`orange`,blueviolet`,`brown`,`burlywood`,`cadetblue`,`chartreuse`,`chocolate`,`coral`,`cornflowerblue`,`cornsilk`,`crimson`,`gold`,`goldenrod`,`greenyellow`,`grey`,`honeydew`,`hotpink`,`indianred`,`indigo`];

var rgbcode  = [`#808080`,`#ffffff`,`#800000`,`#ff0000`,`#800080`,`#ff00ff`,`#008000`,`#00ff00`,`#808000`,`#ffff00`,`#000080`,`#0000ff`,`#008080`,
               `#00ffff`,`#ffa500`,`#8a2be2`,`#a52a2a`,`#deb887`,`#5f9ea0`,`#7fff00`,`#d2691e`,`#ff7f50`,`#6495ed`,`#fff8dc`,`#dc143c`,`#ffd700`,
				`#daa520`,`#adff2f`,`#808080`,`#f0fff0`,`#ff69b4`,`#cd5c5c`,`#4b0082`];
				
var rgbcolor  = [`gray`,`white`,`maroon`,`red`,`purple`,`fuchsia`,`green`,`lime`,`olive`,`yellow`,`navy`,`blue`,`teal`,
                `aqua`,`orange`,`blueviolet`,`brown`,`burlywood`,`cadetblue`,`chartreuse`,`chocolate`,`coral`,`cornflowerblue`,`cornsilk`,`crimson`,`gold`,
				`goldenrod`,`greenyellow`,`grey`,`honeydew`,`hotpink`,`indianred`,`indigo`];

 
var user =  """),format.raw/*137.13*/("""{"""),format.raw/*137.14*/("""name: """""),format.raw/*137.22*/("""}"""),format.raw/*137.23*/(""";
 
var SNAKE = """),format.raw/*139.13*/("""{"""),format.raw/*139.14*/("""
  """),format.raw/*140.3*/("""x: 160,
  y: 160,
  
  // snakes[mySnakeNo] velocity. moves one grid length every frame in either the x or y direction
  dx: grid,
  dy: 0,
  
  // keep track of all grids the snakes[mySnakeNo] body occupies
  cells: [],
  
  score:0, // Keep track of the score
  // length of the snakes[mySnakeNo]. grows when eating an apple
  maxCells: 4,
  
  reset: false,
  
  name: "new_user",
  
  postn: 0
"""),format.raw/*159.1*/("""}"""),format.raw/*159.2*/(""";

var apple = """),format.raw/*161.13*/("""{"""),format.raw/*161.14*/("""
  """),format.raw/*162.3*/("""x: 320,
  y: 320
"""),format.raw/*164.1*/("""}"""),format.raw/*164.2*/(""";


var range = """),format.raw/*167.13*/("""{"""),format.raw/*167.14*/("""
"""),format.raw/*168.1*/("""min:0,
max:25
"""),format.raw/*170.1*/("""}"""),format.raw/*170.2*/(""";

function compare(a,b) """),format.raw/*172.23*/("""{"""),format.raw/*172.24*/("""
  """),format.raw/*173.3*/("""if (a.score < b.score)
    return 1;
  else if (a.score > b.score)
    return -1;
  else
	return 0;
"""),format.raw/*179.1*/("""}"""),format.raw/*179.2*/("""

"""),format.raw/*181.1*/("""function populateUsersList() 
"""),format.raw/*182.1*/("""{"""),format.raw/*182.2*/("""
	"""),format.raw/*183.2*/("""var topThree = 0;
	var j = 0;
	var temp = [];

	for (var i = 0; i < snakes.length; i++)
	"""),format.raw/*188.2*/("""{"""),format.raw/*188.3*/("""
		"""),format.raw/*189.3*/("""if ( snakes[i] == null)
			continue;
		
		temp[j++] = JSON.parse( JSON.stringify ( snakes[i] ));
	"""),format.raw/*193.2*/("""}"""),format.raw/*193.3*/("""
	
	"""),format.raw/*195.2*/("""temp.sort (compare);
	
    for (var i = 0; i < temp.length; i++)
	"""),format.raw/*198.2*/("""{"""),format.raw/*198.3*/("""
		"""),format.raw/*199.3*/("""if ( temp[i] == null)
			continue;
			
		var user = snakes [temp[i].postn].name + ": " + snakes [temp[i].postn].score;
		 // update  the list item:
		 
		var iD = "Rank"+ (++topThree);
		
		console.log(iD);
		
		document.getElementById(iD).style.color = rgbcolor [ temp[i].postn%rgbcode.length];
		document.getElementById( iD ).innerHTML = user;
		
		if (topThree == 3)
			break;
    """),format.raw/*214.5*/("""}"""),format.raw/*214.6*/("""
 """),format.raw/*215.2*/("""}"""),format.raw/*215.3*/("""

"""),format.raw/*217.1*/("""function create_UUID ()
"""),format.raw/*218.1*/("""{"""),format.raw/*218.2*/("""
    """),format.raw/*219.5*/("""var dt = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) """),format.raw/*220.84*/("""{"""),format.raw/*220.85*/("""
        """),format.raw/*221.9*/("""var r = (dt + Math.random()*16)%16 | 0;
        dt = Math.floor(dt/16);
        return (c=='x' ? r :(r&0x3|0x8)).toString(16);
    """),format.raw/*224.5*/("""}"""),format.raw/*224.6*/(""");
    return uuid;
"""),format.raw/*226.1*/("""}"""),format.raw/*226.2*/("""

"""),format.raw/*228.1*/("""function sleep (time) 
"""),format.raw/*229.1*/("""{"""),format.raw/*229.2*/("""
  """),format.raw/*230.3*/("""return new Promise((resolve) => setTimeout(resolve, time));
"""),format.raw/*231.1*/("""}"""),format.raw/*231.2*/("""

"""),format.raw/*233.1*/("""function UserAction(url,inputJson, func) 
"""),format.raw/*234.1*/("""{"""),format.raw/*234.2*/("""
	"""),format.raw/*235.2*/("""var xhttp;
    xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
								"""),format.raw/*238.9*/("""{"""),format.raw/*238.10*/("""
									"""),format.raw/*239.10*/("""if (this.readyState == 4 && this.status == 200) 
									"""),format.raw/*240.10*/("""{"""),format.raw/*240.11*/("""
										"""),format.raw/*241.11*/("""var obj;
										try
										"""),format.raw/*243.11*/("""{"""),format.raw/*243.12*/("""
  									    """),format.raw/*244.16*/("""obj = JSON.parse (this.responseText);
										//alert ("Response "+JSON.stringify(obj));
										if (obj != null)
											func (obj);
										"""),format.raw/*248.11*/("""}"""),format.raw/*248.12*/("""
										"""),format.raw/*249.11*/("""catch (err) """),format.raw/*249.23*/("""{"""),format.raw/*249.24*/("""}"""),format.raw/*249.25*/("""
									"""),format.raw/*250.10*/("""}"""),format.raw/*250.11*/("""	
								"""),format.raw/*251.9*/("""}"""),format.raw/*251.10*/(""";
    
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
	//alert ("Request "+url+JSON.stringify(inputJson));
	xhttp.send(inputJson);
"""),format.raw/*257.1*/("""}"""),format.raw/*257.2*/(""" 

"""),format.raw/*259.1*/("""function createMyWebSocket()
"""),format.raw/*260.1*/("""{"""),format.raw/*260.2*/("""
	"""),format.raw/*261.2*/("""username = prompt("Please enter your name", "screen name");

	var url = "ws://snakegame.com:9000/ws";
	websocket = new WebSocket(url );
    websocket.onopen = function(evt) """),format.raw/*265.38*/("""{"""),format.raw/*265.39*/(""" """),format.raw/*265.40*/("""onOpen(evt) """),format.raw/*265.52*/("""}"""),format.raw/*265.53*/(""";
    websocket.onclose = function(evt) """),format.raw/*266.39*/("""{"""),format.raw/*266.40*/(""" """),format.raw/*266.41*/("""onClose(evt) """),format.raw/*266.54*/("""}"""),format.raw/*266.55*/(""";
    websocket.onmessage = function(evt) """),format.raw/*267.41*/("""{"""),format.raw/*267.42*/(""" """),format.raw/*267.43*/("""onMessage(evt) """),format.raw/*267.58*/("""}"""),format.raw/*267.59*/(""";
    websocket.onerror = function(evt) """),format.raw/*268.39*/("""{"""),format.raw/*268.40*/(""" """),format.raw/*268.41*/("""onError(evt) """),format.raw/*268.54*/("""}"""),format.raw/*268.55*/(""";
"""),format.raw/*269.1*/("""}"""),format.raw/*269.2*/("""

"""),format.raw/*271.1*/("""function getCookie(name) """),format.raw/*271.26*/("""{"""),format.raw/*271.27*/("""
  """),format.raw/*272.3*/("""var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
"""),format.raw/*275.1*/("""}"""),format.raw/*275.2*/("""

"""),format.raw/*277.1*/("""function onOpen(evt) """),format.raw/*277.22*/("""{"""),format.raw/*277.23*/("""
					
				  """),format.raw/*279.7*/("""joinGame (username);
               """),format.raw/*280.16*/("""}"""),format.raw/*280.17*/("""
					
"""),format.raw/*282.1*/("""function onClose(evt) """),format.raw/*282.23*/("""{"""),format.raw/*282.24*/("""
	"""),format.raw/*283.2*/("""mySnakeNo = -1;
	reason = "Disconnected from server: Idle Timeout!!";
	userSuggestion = "Try reload/refresh..!";
	running = false;
	joined = false;
	snakes = [];
"""),format.raw/*289.1*/("""}"""),format.raw/*289.2*/("""
	
"""),format.raw/*291.1*/("""function onMessage(evt) """),format.raw/*291.25*/("""{"""),format.raw/*291.26*/("""
	
  """),format.raw/*293.3*/("""var message = evt.data;
  receiveMessage (evt.data);	
"""),format.raw/*295.1*/("""}"""),format.raw/*295.2*/("""
	
"""),format.raw/*297.1*/("""function onError(evt) """),format.raw/*297.23*/("""{"""),format.raw/*297.24*/("""
    """),format.raw/*298.5*/("""alert ("Connection Error : (Disconnect/Duplicate session) ");
"""),format.raw/*299.1*/("""}"""),format.raw/*299.2*/("""
	
"""),format.raw/*301.1*/("""function addMessage(message) """),format.raw/*301.30*/("""{"""),format.raw/*301.31*/("""
  
   """),format.raw/*303.4*/("""websocket.send(message);
"""),format.raw/*304.1*/("""}"""),format.raw/*304.2*/("""


"""),format.raw/*307.1*/("""function dummy(obj)
"""),format.raw/*308.1*/("""{"""),format.raw/*308.2*/("""
"""),format.raw/*309.1*/("""}"""),format.raw/*309.2*/("""

"""),format.raw/*311.1*/("""function setSnake(sno) 
"""),format.raw/*312.1*/("""{"""),format.raw/*312.2*/("""
	"""),format.raw/*313.2*/("""var api =	 """),format.raw/*313.13*/("""{"""),format.raw/*313.14*/("""	"""),format.raw/*313.15*/("""name: "setSnake""""),format.raw/*313.31*/("""}"""),format.raw/*313.32*/(""";
	var userno = """),format.raw/*314.15*/("""{"""),format.raw/*314.16*/(""" """),format.raw/*314.17*/("""no: sno """),format.raw/*314.25*/("""}"""),format.raw/*314.26*/(""";
	
	var request =
	"""),format.raw/*317.2*/("""{"""),format.raw/*317.3*/("""
	  """),format.raw/*318.4*/("""no:userno,
	  api:api,
	  sn:snakes[sno]
	"""),format.raw/*321.2*/("""}"""),format.raw/*321.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*324.1*/("""}"""),format.raw/*324.2*/("""

"""),format.raw/*326.1*/("""function setApplePosition_p2(obj)
"""),format.raw/*327.1*/("""{"""),format.raw/*327.2*/("""
	"""),format.raw/*328.2*/("""apple.x=obj.event.apple.x;
	apple.y=obj.event.apple.y;
"""),format.raw/*330.1*/("""}"""),format.raw/*330.2*/("""


"""),format.raw/*333.1*/("""function setApplePosition(min, max) 
"""),format.raw/*334.1*/("""{"""),format.raw/*334.2*/("""
	"""),format.raw/*335.2*/("""var api =	 """),format.raw/*335.13*/("""{"""),format.raw/*335.14*/(""" """),format.raw/*335.15*/("""name: "setApplePosition""""),format.raw/*335.39*/("""}"""),format.raw/*335.40*/(""";
	var userno = """),format.raw/*336.15*/("""{"""),format.raw/*336.16*/(""" """),format.raw/*336.17*/("""no: mySnakeNo """),format.raw/*336.31*/("""}"""),format.raw/*336.32*/(""";
	var range =  """),format.raw/*337.15*/("""{"""),format.raw/*337.16*/(""" """),format.raw/*337.17*/("""min:min, max:max """),format.raw/*337.34*/("""}"""),format.raw/*337.35*/(""";
	
	var request =
	"""),format.raw/*340.2*/("""{"""),format.raw/*340.3*/("""
	  """),format.raw/*341.4*/("""no:userno,
	  api:api,
	  range:range
	"""),format.raw/*344.2*/("""}"""),format.raw/*344.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*347.1*/("""}"""),format.raw/*347.2*/("""

"""),format.raw/*349.1*/("""// game loop
function animate() 
"""),format.raw/*351.1*/("""{"""),format.raw/*351.2*/("""	
		"""),format.raw/*352.3*/("""if (animation)
		   cancelAnimationFrame(animation);
		   
		if (running)
			animation = requestAnimationFrame(animate); // animate will be called for 60 times in a second
		else
			gameover_p2();
		
		// slow game loop to 15 fps instead of 60 (60/15 = 4)
		if (++count < 4) // Maximum retries.
			return;
		 
		count = 0;
var intime = new Date().getTime ();

        render ();
		
var outtime = new Date().getTime ();		

		if ( (outtime - intime) > 50 )
			console.log ("Time taken for rendering..:" + (outtime - intime) + "..soon will breach the smooth rendering.." );
		else	
			console.log ("Time taken for rendering..:" + (outtime - intime) );
"""),format.raw/*375.1*/("""}"""),format.raw/*375.2*/("""

"""),format.raw/*377.1*/("""function prepareForRender ()
"""),format.raw/*378.1*/("""{"""),format.raw/*378.2*/("""
	"""),format.raw/*379.2*/("""TotalPlayers = 0;
	
	for ( var i = 0; i < snakes.length; i++)
	"""),format.raw/*382.2*/("""{"""),format.raw/*382.3*/("""
	  """),format.raw/*383.4*/("""if (	snakes[i] == null )
		continue;
		
      TotalPlayers++;		
	 
	  snakes[i].x += snakes[i].dx;
	  snakes[i].y += snakes[i].dy;
	  // wrap snakes[mySnakeNo] position horizontally on edge of screen
	  
	  if (snakes[i].x < 0) 
	  """),format.raw/*393.4*/("""{"""),format.raw/*393.5*/("""
		"""),format.raw/*394.3*/("""snakes[i].x = canvas.width - grid;
	  """),format.raw/*395.4*/("""}"""),format.raw/*395.5*/("""
	  """),format.raw/*396.4*/("""else if (snakes[i].x >= canvas.width) 
	  """),format.raw/*397.4*/("""{"""),format.raw/*397.5*/("""
		"""),format.raw/*398.3*/("""snakes[i].x = 0;
	  """),format.raw/*399.4*/("""}"""),format.raw/*399.5*/("""
  	     
	  """),format.raw/*401.4*/("""// wrap snakes[mySnakeNo] position vertically on edge of screen
	  if (snakes[i].y < 0) """),format.raw/*402.25*/("""{"""),format.raw/*402.26*/("""
		"""),format.raw/*403.3*/("""snakes[i].y = canvas.height - grid;
	  """),format.raw/*404.4*/("""}"""),format.raw/*404.5*/("""
	  """),format.raw/*405.4*/("""else if (snakes[i].y >= canvas.height) """),format.raw/*405.43*/("""{"""),format.raw/*405.44*/("""
		"""),format.raw/*406.3*/("""snakes[i].y = 0;
	  """),format.raw/*407.4*/("""}"""),format.raw/*407.5*/("""
	
	  """),format.raw/*409.4*/("""// keep track of where snakes[mySnakeNo] has been. front of the array is always the head
	  snakes[i].cells.unshift( """),format.raw/*410.29*/("""{"""),format.raw/*410.30*/("""x: snakes[i].x, y: snakes[i].y"""),format.raw/*410.60*/("""}"""),format.raw/*410.61*/(""" """),format.raw/*410.62*/(""");
	  

	  // remove cells as we move away from them
	  if (snakes[i].cells.length > snakes[i].maxCells) 
	  """),format.raw/*415.4*/("""{"""),format.raw/*415.5*/("""
		"""),format.raw/*416.3*/("""snakes[i].cells.pop();
	  """),format.raw/*417.4*/("""}"""),format.raw/*417.5*/("""
	   
	  """),format.raw/*419.4*/("""snakes[i].cells.forEach ( function(cell, index) 
	  """),format.raw/*420.4*/("""{"""),format.raw/*420.5*/("""
			"""),format.raw/*421.4*/("""// snakes[i] ate apple
			if (cell.x == apple.x && cell.y == apple.y && i == mySnakeNo) 
			"""),format.raw/*423.4*/("""{"""),format.raw/*423.5*/("""
				  """),format.raw/*424.7*/("""snakes[i].maxCells++; 
  				  snakes[i].score+=10;
				  setSnake (i);
				  setApplePosition ();
				  console.log("swallowed a apple..."+i);
			"""),format.raw/*429.4*/("""}"""),format.raw/*429.5*/("""
							
			"""),format.raw/*431.4*/("""//check collision with all cells after this one (modified bubble sort)
			for (var j = index+1; j < snakes[i].cells.length; j++) 
			"""),format.raw/*433.4*/("""{"""),format.raw/*433.5*/(""" 
				"""),format.raw/*434.5*/("""// snakes[mySnakeNo] occupies same space as a body part. reset game	  
				if (cell.x == snakes[i].cells[j].x && cell.y == snakes[i].cells[j].y && i == mySnakeNo) 
				"""),format.raw/*436.5*/("""{"""),format.raw/*436.6*/("""
					"""),format.raw/*437.6*/("""reason = "You bite yourself!!"
					running = false;
					removeSnake (i, reason);
					console.log("Bite my own self..."+i);
					break;
				"""),format.raw/*442.5*/("""}"""),format.raw/*442.6*/("""					
			"""),format.raw/*443.4*/("""}"""),format.raw/*443.5*/("""
		"""),format.raw/*444.3*/("""}"""),format.raw/*444.4*/(""" """),format.raw/*444.5*/(""");	
		
		if ( running ) //Inside the loop,  the snake would have bitten itself and would have to stop running game.
		"""),format.raw/*447.3*/("""{"""),format.raw/*447.4*/("""			
			"""),format.raw/*448.4*/("""if (i == mySnakeNo)
				setSnake (i);
				
			//for (var m = 0; m < snakes.length; m++)
			"""),format.raw/*452.4*/("""{"""),format.raw/*452.5*/("""
				"""),format.raw/*453.5*/("""//Check collision of mySnake's head with other snake's any body part.
				if (snakes [mySnakeNo] == null)
					continue;
				var myX = snakes [mySnakeNo].x;
				var myY = snakes [mySnakeNo].y;

				for (var k =0; k < snakes.length; k++)
				"""),format.raw/*460.5*/("""{"""),format.raw/*460.6*/("""
					"""),format.raw/*461.6*/("""if ( k != mySnakeNo && snakes[k] != null )  // Should  check with all snakes excpet for self.
					"""),format.raw/*462.6*/("""{"""),format.raw/*462.7*/("""
						"""),format.raw/*463.7*/("""for (var l = 0; l < snakes[k].cells.length; l++)  // check on each cells of the snake in the board
						"""),format.raw/*464.7*/("""{"""),format.raw/*464.8*/("""
							"""),format.raw/*465.8*/("""if (myX == snakes[k].cells[l].x && myY == snakes[k].cells[l].y)
							"""),format.raw/*466.8*/("""{"""),format.raw/*466.9*/("""    
							    
									
								"""),format.raw/*469.9*/("""if (snakes[k].cells.length > 4) // Do not reduce length if it reaches initial size of maxCells=4.
								"""),format.raw/*470.9*/("""{"""),format.raw/*470.10*/("""
									"""),format.raw/*471.10*/("""console.log ( JSON.stringify(snakes[mySnakeNo]) + "Hits with..." + JSON.stringify (snakes[k]) +"at the cell no: " + l );
									
									
									snakes[mySnakeNo].maxCells++;
									snakes[mySnakeNo].score += 15;
									setSnake(mySnakeNo);
									
									snakes[k].cells.pop();
									snakes[k].maxCells--;
									snakes[k].score -= 5;
									setSnake(k);
								"""),format.raw/*482.9*/("""}"""),format.raw/*482.10*/("""
								"""),format.raw/*483.9*/("""break;
							"""),format.raw/*484.8*/("""}"""),format.raw/*484.9*/("""
						"""),format.raw/*485.7*/("""}"""),format.raw/*485.8*/("""
					"""),format.raw/*486.6*/("""}"""),format.raw/*486.7*/("""
				"""),format.raw/*487.5*/("""}"""),format.raw/*487.6*/("""
			"""),format.raw/*488.4*/("""}"""),format.raw/*488.5*/("""	
		"""),format.raw/*489.3*/("""}"""),format.raw/*489.4*/("""
	"""),format.raw/*490.2*/("""}"""),format.raw/*490.3*/(""" 
"""),format.raw/*491.1*/("""}"""),format.raw/*491.2*/("""

"""),format.raw/*493.1*/("""function render ()
"""),format.raw/*494.1*/("""{"""),format.raw/*494.2*/("""  
  """),format.raw/*495.3*/("""if (!joined)
		return;

  if (!running)
		return;
  
  if ( snakes.length == 0 )
		return;
		
  if (mySnakeNo == -1)
		return;		
		
  prepareForRender ();
  populateUsersList ();
  
  context.clearRect(0,0,canvas.width,canvas.height);
  
  for ( var i = 0; i < snakes.length; i++)
  """),format.raw/*513.3*/("""{"""),format.raw/*513.4*/("""
"""),format.raw/*514.1*/("""//	 if (!running ) //Inside the loop the snake would have bitten itself and would have to stop running.
//		break;
		
	  if (	snakes[i] == null )
		continue;
		  
 	  // draw apple
	  context.fillStyle = 'red';
	  context.fillRect(apple.x, apple.y, grid-1, grid-1);
	  
	  
	  // draw snakes[mySnakeNo] one cell at a time
	  context.fillStyle = rgbcode[i%rgbcode.length];
	  
	  snakes[i].cells.forEach ( function(cell, index) 
								"""),format.raw/*529.9*/("""{"""),format.raw/*529.10*/("""
									"""),format.raw/*530.10*/("""// drawing 1 px smaller than the grid creates a grid effect in the snakes[mySnakeNo] body so you can see how long it is
									context.fillRect(cell.x, cell.y, grid-1, grid-1);  	
									if (i == mySnakeNo)
									"""),format.raw/*533.10*/("""{"""),format.raw/*533.11*/("""
										"""),format.raw/*534.11*/("""context.strokeStyle = "black";
										context.lineWidth   = 2;
										context.strokeRect(cell.x, cell.y, grid-1, grid-1);									
									"""),format.raw/*537.10*/("""}"""),format.raw/*537.11*/("""
								"""),format.raw/*538.9*/("""}"""),format.raw/*538.10*/(""" """),format.raw/*538.11*/(""");	
		// To display score of my snake.
		if (i == mySnakeNo)
		"""),format.raw/*541.3*/("""{"""),format.raw/*541.4*/("""
			"""),format.raw/*542.4*/("""document.getElementById("sc").style.color = rgbcolor[i%rgbcode.length];
			document.getElementById("sz").style.color  = rgbcolor[i%rgbcode.length];
			document.getElementById("cl").style.color  = rgbcolor[i%rgbcode.length];
		
			document.getElementById("sc").innerHTML = "My Score : " + snakes [i].score;
			document.getElementById("sz").innerHTML = "My Cells: " + snakes [i].maxCells;
			document.getElementById("cl").innerHTML = "My Colour: " + rgbcolor[i%rgbcode.length];
		"""),format.raw/*549.3*/("""}"""),format.raw/*549.4*/("""		
		"""),format.raw/*550.3*/("""document.getElementById("sl").innerHTML = "Active Players:" + TotalPlayers;
	"""),format.raw/*551.2*/("""}"""),format.raw/*551.3*/(""" 	
"""),format.raw/*552.1*/("""}"""),format.raw/*552.2*/("""

"""),format.raw/*554.1*/("""// game over fn

function gameover_p2()
"""),format.raw/*557.1*/("""{"""),format.raw/*557.2*/("""
	"""),format.raw/*558.2*/("""context = canvas.getContext('2d');
	context.font = "30px Arial";
	context.fillStyle = "white";
	context.textAlign = "center";
	context.fillText("Gameover! " + reason, canvas.width / 2, 30);
	context.fillText(userSuggestion, canvas.width / 2, 60);
"""),format.raw/*564.1*/("""}"""),format.raw/*564.2*/("""



"""),format.raw/*568.1*/("""function processEvents_p2(obj)
"""),format.raw/*569.1*/("""{"""),format.raw/*569.2*/("""
		"""),format.raw/*570.3*/("""if (obj.event)
		"""),format.raw/*571.3*/("""{"""),format.raw/*571.4*/("""	
			"""),format.raw/*572.4*/("""if (obj.event.no != -1)
			"""),format.raw/*573.4*/("""{"""),format.raw/*573.5*/("""
				"""),format.raw/*574.5*/("""{"""),format.raw/*574.6*/("""
					"""),format.raw/*575.6*/("""snakes[obj.event.no] = obj.event.sn;
					snakes[obj.event.no].dx = obj.event.sn.dx;
					snakes[obj.event.no].dy = obj.event.sn.dy;
				"""),format.raw/*578.5*/("""}"""),format.raw/*578.6*/("""
			"""),format.raw/*579.4*/("""}"""),format.raw/*579.5*/("""
		"""),format.raw/*580.3*/("""}"""),format.raw/*580.4*/("""
"""),format.raw/*581.1*/("""}"""),format.raw/*581.2*/("""

"""),format.raw/*583.1*/("""function processEvents (e)
"""),format.raw/*584.1*/("""{"""),format.raw/*584.2*/("""
	"""),format.raw/*585.2*/("""var api =	"""),format.raw/*585.12*/("""{"""),format.raw/*585.13*/("""	"""),format.raw/*585.14*/("""name: "processEvents"	"""),format.raw/*585.36*/("""}"""),format.raw/*585.37*/(""";
	var event = """),format.raw/*586.14*/("""{"""),format.raw/*586.15*/("""	"""),format.raw/*586.16*/("""code:e.code	"""),format.raw/*586.28*/("""}"""),format.raw/*586.29*/(""";
	var userno = """),format.raw/*587.15*/("""{"""),format.raw/*587.16*/(""" """),format.raw/*587.17*/("""no: mySnakeNo """),format.raw/*587.31*/("""}"""),format.raw/*587.32*/(""";
	
	var request =
	"""),format.raw/*590.2*/("""{"""),format.raw/*590.3*/("""
	  """),format.raw/*591.4*/("""no:userno,
	  api:api,
	  key:event,
	  sn:SNAKE
	"""),format.raw/*595.2*/("""}"""),format.raw/*595.3*/(""";
	
	if ( mySnakeNo != -1)
		request.sn = snakes [mySnakeNo];
		
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*602.1*/("""}"""),format.raw/*602.2*/("""

"""),format.raw/*604.1*/("""function joinGame_p2 (obj)
"""),format.raw/*605.1*/("""{"""),format.raw/*605.2*/("""
	"""),format.raw/*606.2*/("""if (obj.event.name == "addSnake")
	"""),format.raw/*607.2*/("""{"""),format.raw/*607.3*/("""
		"""),format.raw/*608.3*/("""snakes[obj.event.no]=SNAKE;
		snakes[obj.event.no].name=username;
		snakes[obj.event.no].postn=obj.event.no;
		mySnakeNo = obj.event.no;	
		var gsd = """),format.raw/*612.13*/("""{"""),format.raw/*612.14*/(""" """),format.raw/*612.15*/("""no:mySnakeNo """),format.raw/*612.28*/("""}"""),format.raw/*612.29*/(""";
		//alert ("joinGame_p2 Snake No: "+ obj.event.no + JSON.stringify (snakes[obj.event.no]));
		joined=true;
		running = true;
		animate ();
		UserAction ("http://snakegame.com:9000/setSessionID", JSON.stringify(gsd), dummy);
		if (!listenerAdded)
		"""),format.raw/*619.3*/("""{"""),format.raw/*619.4*/("""
			"""),format.raw/*620.4*/("""document.addEventListener('keydown', processEvents,true );
			listenerAdded = true;
		"""),format.raw/*622.3*/("""}"""),format.raw/*622.4*/("""
	"""),format.raw/*623.2*/("""}"""),format.raw/*623.3*/("""
"""),format.raw/*624.1*/("""}"""),format.raw/*624.2*/("""

"""),format.raw/*626.1*/("""function joinGame (gamer)
"""),format.raw/*627.1*/("""{"""),format.raw/*627.2*/("""
	"""),format.raw/*628.2*/("""var api =	"""),format.raw/*628.12*/("""{"""),format.raw/*628.13*/("""	"""),format.raw/*628.14*/("""name: "joinGame" """),format.raw/*628.31*/("""}"""),format.raw/*628.32*/(""";
	user.name = gamer;
	
	var request =
	"""),format.raw/*632.2*/("""{"""),format.raw/*632.3*/("""
	  """),format.raw/*633.4*/("""api:api,
	  userid:user
	"""),format.raw/*635.2*/("""}"""),format.raw/*635.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*638.1*/("""}"""),format.raw/*638.2*/("""

"""),format.raw/*640.1*/("""function exitGame_p2 (obj)
"""),format.raw/*641.1*/("""{"""),format.raw/*641.2*/("""
	"""),format.raw/*642.2*/("""if (obj.event.name == "removeSnake")
	"""),format.raw/*643.2*/("""{"""),format.raw/*643.3*/("""
		"""),format.raw/*644.3*/("""console.log ("Removed snake : "+obj.event.no);
		snakes[obj.event.no]=null;
		if (obj.event.no == mySnakeNo)
		"""),format.raw/*647.3*/("""{"""),format.raw/*647.4*/("""
			"""),format.raw/*648.4*/("""mySnakeNo = -1;	
			joined=false;
		"""),format.raw/*650.3*/("""}"""),format.raw/*650.4*/("""
	"""),format.raw/*651.2*/("""}"""),format.raw/*651.3*/("""
"""),format.raw/*652.1*/("""}"""),format.raw/*652.2*/("""

"""),format.raw/*654.1*/("""function exitGame ()
"""),format.raw/*655.1*/("""{"""),format.raw/*655.2*/("""
	"""),format.raw/*656.2*/("""var api =	"""),format.raw/*656.12*/("""{"""),format.raw/*656.13*/("""	"""),format.raw/*656.14*/("""name: "exitGame" """),format.raw/*656.31*/("""}"""),format.raw/*656.32*/(""";
	user.name = snakes[mySnakeNo].name;
	var userno = """),format.raw/*658.15*/("""{"""),format.raw/*658.16*/(""" """),format.raw/*658.17*/("""no: mySnakeNo """),format.raw/*658.31*/("""}"""),format.raw/*658.32*/(""";
	
	var request =
	"""),format.raw/*661.2*/("""{"""),format.raw/*661.3*/("""
	  """),format.raw/*662.4*/("""no:userno,
	  api:api,
	  userid:user
	"""),format.raw/*665.2*/("""}"""),format.raw/*665.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*668.1*/("""}"""),format.raw/*668.2*/("""

"""),format.raw/*670.1*/("""function removeSnake (sno, cause)
"""),format.raw/*671.1*/("""{"""),format.raw/*671.2*/("""
	"""),format.raw/*672.2*/("""var api =	"""),format.raw/*672.12*/("""{"""),format.raw/*672.13*/("""	"""),format.raw/*672.14*/("""name: "exitGame" """),format.raw/*672.31*/("""}"""),format.raw/*672.32*/(""";
	user.name = "donotknow";
	var userno = """),format.raw/*674.15*/("""{"""),format.raw/*674.16*/(""" """),format.raw/*674.17*/("""no: sno """),format.raw/*674.25*/("""}"""),format.raw/*674.26*/(""";
	
	var request =
	"""),format.raw/*677.2*/("""{"""),format.raw/*677.3*/("""
	  """),format.raw/*678.4*/("""no:userno,
	  api:api,
	  userid:user
	"""),format.raw/*681.2*/("""}"""),format.raw/*681.3*/(""";
	reason = cause;
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*685.1*/("""}"""),format.raw/*685.2*/("""


"""),format.raw/*688.1*/("""function eventHandler (obj)
"""),format.raw/*689.1*/("""{"""),format.raw/*689.2*/("""
	"""),format.raw/*690.2*/("""if (obj.event.name == "addSnake")
	"""),format.raw/*691.2*/("""{"""),format.raw/*691.3*/("""
		"""),format.raw/*692.3*/("""snakes[obj.event.no] = SNAKE;
		snakes[obj.event.no].postn = obj.event.no;
	"""),format.raw/*694.2*/("""}"""),format.raw/*694.3*/("""
	"""),format.raw/*695.2*/("""else if (obj.event.name == "removeSnake")
	"""),format.raw/*696.2*/("""{"""),format.raw/*696.3*/("""
		"""),format.raw/*697.3*/("""snakes[obj.event.no]=null;
	"""),format.raw/*698.2*/("""}"""),format.raw/*698.3*/("""
	"""),format.raw/*699.2*/("""else if (obj.event.name == "appleMoved")
	"""),format.raw/*700.2*/("""{"""),format.raw/*700.3*/("""
		"""),format.raw/*701.3*/("""setApplePosition_p2 (obj);
	"""),format.raw/*702.2*/("""}"""),format.raw/*702.3*/("""
	"""),format.raw/*703.2*/("""else if (obj.event.name == "availableSnakes")
	"""),format.raw/*704.2*/("""{"""),format.raw/*704.3*/("""
		"""),format.raw/*705.3*/("""snakes[obj.event.no]=obj.event.sn;
	"""),format.raw/*706.2*/("""}"""),format.raw/*706.3*/("""
	"""),format.raw/*707.2*/("""else if (obj.event.name == "snakeNewPositioned")
	"""),format.raw/*708.2*/("""{"""),format.raw/*708.3*/("""
		"""),format.raw/*709.3*/("""snakes[obj.event.no]=obj.event.sn;
	"""),format.raw/*710.2*/("""}"""),format.raw/*710.3*/("""
	"""),format.raw/*711.2*/("""else if (obj.event.name == "keyPressed")
	"""),format.raw/*712.2*/("""{"""),format.raw/*712.3*/("""
		"""),format.raw/*713.3*/("""{"""),format.raw/*713.4*/("""
			"""),format.raw/*714.4*/("""snakes[obj.event.no] = obj.event.sn;
			snakes[obj.event.no].dx = obj.event.sn.dx;
			snakes[obj.event.no].dy = obj.event.sn.dy; 
		"""),format.raw/*717.3*/("""}"""),format.raw/*717.4*/("""
	"""),format.raw/*718.2*/("""}"""),format.raw/*718.3*/("""
	"""),format.raw/*719.2*/("""else if (obj.event.name == "retry")
	"""),format.raw/*720.2*/("""{"""),format.raw/*720.3*/("""
		"""),format.raw/*721.3*/("""joinGame (username);
	"""),format.raw/*722.2*/("""}"""),format.raw/*722.3*/("""
"""),format.raw/*723.1*/("""}"""),format.raw/*723.2*/("""



"""),format.raw/*727.1*/("""function receiveMessage(message) """),format.raw/*727.34*/("""{"""),format.raw/*727.35*/("""

  """),format.raw/*729.3*/("""try
  """),format.raw/*730.3*/("""{"""),format.raw/*730.4*/("""
	"""),format.raw/*731.2*/("""if (message.length)
	"""),format.raw/*732.2*/("""{"""),format.raw/*732.3*/("""
		"""),format.raw/*733.3*/("""var obj = JSON.parse (message);
		if (obj.api.name == "processEvents")
			processEvents_p2(obj);
		else if (obj.api.name == "joinGame")
			joinGame_p2(obj);
		else if (obj.api.name == "event")
			eventHandler (obj);
		else if (obj.api.name == "setApplePosition")
			setApplePosition_p2 (obj);
		else if (obj.api.name == "setSnake")
			dummy (obj);
		else if (obj.api.name == "exitGame")
			exitGame_p2 (obj);
	"""),format.raw/*746.2*/("""}"""),format.raw/*746.3*/("""
  """),format.raw/*747.3*/("""}"""),format.raw/*747.4*/("""
  """),format.raw/*748.3*/("""catch (err)
  """),format.raw/*749.3*/("""{"""),format.raw/*749.4*/("""
	"""),format.raw/*750.2*/("""alert (message + err);
  """),format.raw/*751.3*/("""}"""),format.raw/*751.4*/("""
"""),format.raw/*752.1*/("""}"""),format.raw/*752.2*/("""


"""),format.raw/*755.1*/("""function onBrowserTabExit ()
"""),format.raw/*756.1*/("""{"""),format.raw/*756.2*/("""
	"""),format.raw/*757.2*/("""if (joined == true)
	"""),format.raw/*758.2*/("""{"""),format.raw/*758.3*/("""
		"""),format.raw/*759.3*/("""reason = "Session end...Reload or Press Enter!!!";
		userSuggestion="";
		running = false;
		removeSnake (mySnakeNo, reason);
		sleep(100);
	"""),format.raw/*764.2*/("""}"""),format.raw/*764.3*/("""	
"""),format.raw/*765.1*/("""}"""),format.raw/*765.2*/("""

"""),format.raw/*767.1*/("""window.addEventListener('beforeunload', onBrowserTabExit,true );
//window.onbeforeunload = onBrowserTabExit ();

// listen to keyboard events to move the snakes[mySnakeNo]
createMyWebSocket();

// start the game

running = true;

</script>

</body>

</html>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Apr 13 23:18:28 IST 2021
                  SOURCE: D:/Users/yamin/Documents/Game/assignment-1-mug/app/views/index.scala.html
                  HASH: 5cc59b7d4492474042f60f193e94c3078157fd87
                  MATRIX: 1030->0|1156->100|1183->101|1214->105|1296->160|1324->161|1359->169|1391->174|1419->175|1452->181|1560->262|1588->263|1621->269|1655->276|1683->277|1714->281|1850->390|1878->391|1910->396|1947->406|1975->407|2007->412|2173->551|2201->552|2237->561|2274->571|2302->572|2334->577|2465->681|2493->682|2533->695|2570->705|2598->706|2630->711|2774->828|2802->829|2837->837|2873->846|2901->847|2933->852|3046->938|3074->939|3109->947|5363->3172|5393->3173|5430->3181|5460->3182|5507->3200|5537->3201|5569->3205|6014->3622|6043->3623|6089->3640|6119->3641|6151->3645|6198->3664|6227->3665|6275->3684|6305->3685|6335->3687|6379->3703|6408->3704|6464->3731|6494->3732|6526->3736|6660->3842|6689->3843|6721->3847|6780->3878|6809->3879|6840->3882|6962->3976|6991->3977|7023->3981|7153->4083|7182->4084|7216->4090|7313->4159|7342->4160|7374->4164|7801->4563|7830->4564|7861->4567|7890->4568|7922->4572|7975->4597|8004->4598|8038->4604|8182->4719|8212->4720|8250->4730|8412->4864|8441->4865|8491->4887|8520->4888|8552->4892|8604->4916|8633->4917|8665->4921|8754->4982|8783->4983|8815->4987|8886->5030|8915->5031|8946->5034|9070->5130|9100->5131|9140->5142|9228->5201|9258->5202|9299->5214|9363->5249|9393->5250|9439->5267|9623->5422|9653->5423|9694->5435|9735->5447|9765->5448|9795->5449|9835->5460|9865->5461|9904->5472|9934->5473|10152->5663|10181->5664|10214->5669|10272->5699|10301->5700|10332->5703|10538->5880|10568->5881|10598->5882|10639->5894|10669->5895|10739->5936|10769->5937|10799->5938|10841->5951|10871->5952|10943->5995|10973->5996|11003->5997|11047->6012|11077->6013|11147->6054|11177->6055|11207->6056|11249->6069|11279->6070|11310->6073|11339->6074|11371->6078|11425->6103|11455->6104|11487->6108|11664->6257|11693->6258|11725->6262|11775->6283|11805->6284|11848->6299|11914->6336|11944->6337|11981->6346|12032->6368|12062->6369|12093->6372|12289->6540|12318->6541|12351->6546|12404->6570|12434->6571|12469->6578|12553->6634|12582->6635|12615->6640|12666->6662|12696->6663|12730->6669|12821->6732|12850->6733|12883->6738|12941->6767|12971->6768|13008->6777|13062->6803|13091->6804|13125->6810|13174->6831|13203->6832|13233->6834|13262->6835|13294->6839|13347->6864|13376->6865|13407->6868|13447->6879|13477->6880|13507->6881|13552->6897|13582->6898|13628->6915|13658->6916|13688->6917|13725->6925|13755->6926|13806->6949|13835->6950|13868->6955|13941->7000|13970->7001|14066->7069|14095->7070|14127->7074|14190->7109|14219->7110|14250->7113|14335->7170|14364->7171|14398->7177|14464->7215|14493->7216|14524->7219|14564->7230|14594->7231|14624->7232|14677->7256|14707->7257|14753->7274|14783->7275|14813->7276|14856->7290|14886->7291|14932->7308|14962->7309|14992->7310|15038->7327|15068->7328|15119->7351|15148->7352|15181->7357|15251->7399|15280->7400|15376->7468|15405->7469|15437->7473|15500->7508|15529->7509|15562->7514|16262->8186|16291->8187|16323->8191|16381->8221|16410->8222|16441->8225|16535->8291|16564->8292|16597->8297|16867->8539|16896->8540|16928->8544|16995->8583|17024->8584|17057->8589|17128->8632|17157->8633|17189->8637|17238->8658|17267->8659|17310->8674|17428->8763|17458->8764|17490->8768|17558->8808|17587->8809|17620->8814|17688->8853|17718->8854|17750->8858|17799->8879|17828->8880|17864->8888|18011->9006|18041->9007|18100->9037|18130->9038|18160->9039|18302->9153|18331->9154|18363->9158|18418->9185|18447->9186|18486->9197|18567->9250|18596->9251|18629->9256|18751->9350|18780->9351|18816->9359|18996->9511|19025->9512|19067->9526|19230->9661|19259->9662|19294->9669|19492->9839|19521->9840|19556->9847|19731->9994|19760->9995|19798->10005|19827->10006|19859->10010|19888->10011|19917->10012|20066->10133|20095->10134|20131->10142|20254->10237|20283->10238|20317->10244|20593->10492|20622->10493|20657->10500|20785->10600|20814->10601|20850->10609|20984->10715|21013->10716|21050->10725|21150->10797|21179->10798|21245->10836|21380->10943|21410->10944|21450->10955|21873->11350|21903->11351|21941->11361|21984->11376|22013->11377|22049->11385|22078->11386|22113->11393|22142->11394|22176->11400|22205->11401|22238->11406|22267->11407|22300->11412|22329->11413|22360->11416|22389->11417|22420->11420|22449->11421|22481->11425|22529->11445|22558->11446|22592->11452|22921->11753|22950->11754|22980->11756|23459->12207|23489->12208|23529->12219|23781->12442|23811->12443|23852->12455|24030->12604|24060->12605|24098->12615|24128->12616|24158->12617|24252->12683|24281->12684|24314->12689|24827->13174|24856->13175|24890->13181|24996->13259|25025->13260|25057->13264|25086->13265|25118->13269|25189->13312|25218->13313|25249->13316|25530->13569|25559->13570|25595->13578|25655->13610|25684->13611|25716->13615|25762->13633|25791->13634|25825->13640|25881->13668|25910->13669|25944->13675|25973->13676|26008->13683|26176->13823|26205->13824|26238->13829|26267->13830|26299->13834|26328->13835|26358->13837|26387->13838|26419->13842|26475->13870|26504->13871|26535->13874|26574->13884|26604->13885|26634->13886|26685->13908|26715->13909|26760->13925|26790->13926|26820->13927|26861->13939|26891->13940|26937->13957|26967->13958|26997->13959|27040->13973|27070->13974|27121->13997|27150->13998|27183->14003|27265->14057|27294->14058|27457->14193|27486->14194|27518->14198|27574->14226|27603->14227|27634->14230|27698->14266|27727->14267|27759->14271|27942->14425|27972->14426|28002->14427|28044->14440|28074->14441|28359->14698|28388->14699|28421->14704|28537->14792|28566->14793|28597->14796|28626->14797|28656->14799|28685->14800|28717->14804|28772->14831|28801->14832|28832->14835|28871->14845|28901->14846|28931->14847|28977->14864|29007->14865|29079->14909|29108->14910|29141->14915|29196->14942|29225->14943|29321->15011|29350->15012|29382->15016|29438->15044|29467->15045|29498->15048|29565->15087|29594->15088|29626->15092|29768->15206|29797->15207|29830->15212|29896->15250|29925->15251|29956->15254|29985->15255|30015->15257|30044->15258|30076->15262|30126->15284|30155->15285|30186->15288|30225->15298|30255->15299|30285->15300|30331->15317|30361->15318|30445->15373|30475->15374|30505->15375|30548->15389|30578->15390|30629->15413|30658->15414|30691->15419|30761->15461|30790->15462|30886->15530|30915->15531|30947->15535|31010->15570|31039->15571|31070->15574|31109->15584|31139->15585|31169->15586|31215->15603|31245->15604|31318->15648|31348->15649|31378->15650|31415->15658|31445->15659|31496->15682|31525->15683|31558->15688|31628->15730|31657->15731|31771->15817|31800->15818|31834->15824|31891->15853|31920->15854|31951->15857|32015->15893|32044->15894|32076->15898|32182->15976|32211->15977|32242->15980|32314->16024|32343->16025|32375->16029|32432->16058|32461->16059|32492->16062|32563->16105|32592->16106|32624->16110|32681->16139|32710->16140|32741->16143|32817->16191|32846->16192|32878->16196|32943->16233|32972->16234|33003->16237|33082->16288|33111->16289|33143->16293|33208->16330|33237->16331|33268->16334|33339->16377|33368->16378|33400->16382|33429->16383|33462->16388|33625->16523|33654->16524|33685->16527|33714->16528|33745->16531|33811->16569|33840->16570|33872->16574|33923->16597|33952->16598|33982->16600|34011->16601|34047->16609|34109->16642|34139->16643|34173->16649|34208->16656|34237->16657|34268->16660|34318->16682|34347->16683|34379->16687|34830->17110|34859->17111|34891->17115|34920->17116|34952->17120|34995->17135|35024->17136|35055->17139|35109->17165|35138->17166|35168->17168|35197->17169|35231->17175|35289->17205|35318->17206|35349->17209|35399->17231|35428->17232|35460->17236|35634->17382|35663->17383|35694->17386|35723->17387|35755->17391
                  LINES: 33->1|40->8|40->8|42->10|46->14|46->14|48->16|48->16|48->16|49->17|55->23|55->23|57->25|57->25|57->25|58->26|63->31|63->31|65->33|66->34|66->34|67->35|75->43|75->43|77->45|78->46|78->46|79->47|84->52|84->52|87->55|88->56|88->56|89->57|94->62|94->62|96->64|97->65|97->65|98->66|102->70|102->70|104->72|169->137|169->137|169->137|169->137|171->139|171->139|172->140|191->159|191->159|193->161|193->161|194->162|196->164|196->164|199->167|199->167|200->168|202->170|202->170|204->172|204->172|205->173|211->179|211->179|213->181|214->182|214->182|215->183|220->188|220->188|221->189|225->193|225->193|227->195|230->198|230->198|231->199|246->214|246->214|247->215|247->215|249->217|250->218|250->218|251->219|252->220|252->220|253->221|256->224|256->224|258->226|258->226|260->228|261->229|261->229|262->230|263->231|263->231|265->233|266->234|266->234|267->235|270->238|270->238|271->239|272->240|272->240|273->241|275->243|275->243|276->244|280->248|280->248|281->249|281->249|281->249|281->249|282->250|282->250|283->251|283->251|289->257|289->257|291->259|292->260|292->260|293->261|297->265|297->265|297->265|297->265|297->265|298->266|298->266|298->266|298->266|298->266|299->267|299->267|299->267|299->267|299->267|300->268|300->268|300->268|300->268|300->268|301->269|301->269|303->271|303->271|303->271|304->272|307->275|307->275|309->277|309->277|309->277|311->279|312->280|312->280|314->282|314->282|314->282|315->283|321->289|321->289|323->291|323->291|323->291|325->293|327->295|327->295|329->297|329->297|329->297|330->298|331->299|331->299|333->301|333->301|333->301|335->303|336->304|336->304|339->307|340->308|340->308|341->309|341->309|343->311|344->312|344->312|345->313|345->313|345->313|345->313|345->313|345->313|346->314|346->314|346->314|346->314|346->314|349->317|349->317|350->318|353->321|353->321|356->324|356->324|358->326|359->327|359->327|360->328|362->330|362->330|365->333|366->334|366->334|367->335|367->335|367->335|367->335|367->335|367->335|368->336|368->336|368->336|368->336|368->336|369->337|369->337|369->337|369->337|369->337|372->340|372->340|373->341|376->344|376->344|379->347|379->347|381->349|383->351|383->351|384->352|407->375|407->375|409->377|410->378|410->378|411->379|414->382|414->382|415->383|425->393|425->393|426->394|427->395|427->395|428->396|429->397|429->397|430->398|431->399|431->399|433->401|434->402|434->402|435->403|436->404|436->404|437->405|437->405|437->405|438->406|439->407|439->407|441->409|442->410|442->410|442->410|442->410|442->410|447->415|447->415|448->416|449->417|449->417|451->419|452->420|452->420|453->421|455->423|455->423|456->424|461->429|461->429|463->431|465->433|465->433|466->434|468->436|468->436|469->437|474->442|474->442|475->443|475->443|476->444|476->444|476->444|479->447|479->447|480->448|484->452|484->452|485->453|492->460|492->460|493->461|494->462|494->462|495->463|496->464|496->464|497->465|498->466|498->466|501->469|502->470|502->470|503->471|514->482|514->482|515->483|516->484|516->484|517->485|517->485|518->486|518->486|519->487|519->487|520->488|520->488|521->489|521->489|522->490|522->490|523->491|523->491|525->493|526->494|526->494|527->495|545->513|545->513|546->514|561->529|561->529|562->530|565->533|565->533|566->534|569->537|569->537|570->538|570->538|570->538|573->541|573->541|574->542|581->549|581->549|582->550|583->551|583->551|584->552|584->552|586->554|589->557|589->557|590->558|596->564|596->564|600->568|601->569|601->569|602->570|603->571|603->571|604->572|605->573|605->573|606->574|606->574|607->575|610->578|610->578|611->579|611->579|612->580|612->580|613->581|613->581|615->583|616->584|616->584|617->585|617->585|617->585|617->585|617->585|617->585|618->586|618->586|618->586|618->586|618->586|619->587|619->587|619->587|619->587|619->587|622->590|622->590|623->591|627->595|627->595|634->602|634->602|636->604|637->605|637->605|638->606|639->607|639->607|640->608|644->612|644->612|644->612|644->612|644->612|651->619|651->619|652->620|654->622|654->622|655->623|655->623|656->624|656->624|658->626|659->627|659->627|660->628|660->628|660->628|660->628|660->628|660->628|664->632|664->632|665->633|667->635|667->635|670->638|670->638|672->640|673->641|673->641|674->642|675->643|675->643|676->644|679->647|679->647|680->648|682->650|682->650|683->651|683->651|684->652|684->652|686->654|687->655|687->655|688->656|688->656|688->656|688->656|688->656|688->656|690->658|690->658|690->658|690->658|690->658|693->661|693->661|694->662|697->665|697->665|700->668|700->668|702->670|703->671|703->671|704->672|704->672|704->672|704->672|704->672|704->672|706->674|706->674|706->674|706->674|706->674|709->677|709->677|710->678|713->681|713->681|717->685|717->685|720->688|721->689|721->689|722->690|723->691|723->691|724->692|726->694|726->694|727->695|728->696|728->696|729->697|730->698|730->698|731->699|732->700|732->700|733->701|734->702|734->702|735->703|736->704|736->704|737->705|738->706|738->706|739->707|740->708|740->708|741->709|742->710|742->710|743->711|744->712|744->712|745->713|745->713|746->714|749->717|749->717|750->718|750->718|751->719|752->720|752->720|753->721|754->722|754->722|755->723|755->723|759->727|759->727|759->727|761->729|762->730|762->730|763->731|764->732|764->732|765->733|778->746|778->746|779->747|779->747|780->748|781->749|781->749|782->750|783->751|783->751|784->752|784->752|787->755|788->756|788->756|789->757|790->758|790->758|791->759|796->764|796->764|797->765|797->765|799->767
                  -- GENERATED --
              */
          