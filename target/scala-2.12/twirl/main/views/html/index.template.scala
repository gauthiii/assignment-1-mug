
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
  <title></title>
  <style>
  .abc"""),format.raw/*6.7*/("""{"""),format.raw/*6.8*/("""
  """),format.raw/*7.3*/("""color: white;
  align= top left;
  """),format.raw/*9.3*/("""}"""),format.raw/*9.4*/("""
  """),format.raw/*10.3*/("""html, body """),format.raw/*10.14*/("""{"""),format.raw/*10.15*/("""
    """),format.raw/*11.5*/("""height: 100%;
    margin: 0;
  """),format.raw/*13.3*/("""}"""),format.raw/*13.4*/("""
  """),format.raw/*14.3*/("""body """),format.raw/*14.8*/("""{"""),format.raw/*14.9*/("""
    """),format.raw/*15.5*/("""background: black;
    display: flex;
    align-items: center;
    justify-content: center;
  """),format.raw/*19.3*/("""}"""),format.raw/*19.4*/("""
  """),format.raw/*20.3*/("""canvas """),format.raw/*20.10*/("""{"""),format.raw/*20.11*/("""
    """),format.raw/*21.5*/("""border: 1px solid white;
  """),format.raw/*22.3*/("""}"""),format.raw/*22.4*/("""
"""),format.raw/*23.1*/("""</style>
</head>
<body>

<canvas width="960" height="592" id="game"></canvas>
<p class="abc" id = "score">Score:<br> </p>
<p class="abc" id = "#Players">#Players:</p>
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

var url;
var inputJson;

var snakes = [];

//var rgbcolor = [`gray`,`white`,`maroon`,`red`,`purple`,`fuchsia`,`green`,`lime`,`olive`,`yellow`,`navy`,`blue`,`teal`,`aqua`,`orange`,blueviolet`,`brown`,`burlywood`,`cadetblue`,`chartreuse`,`chocolate`,`coral`,`cornflowerblue`,`cornsilk`,`crimson`,`gold`,`goldenrod`,`greenyellow`,`grey`,`honeydew`,`hotpink`,`indianred`,`indigo`];
				
var rgbcode  = [`#808080`,`#ffffff`,`#800000`,`#ff0000`,`#800080`,`#ff00ff`,`#008000`,`#00ff00`,`#808000`,`#ffff00`,`#000080`,`#0000ff`,`#008080`,
                `#00ffff`,`#ffa500`,`#8a2be2`,`#a52a2a`,`#deb887`,`#5f9ea0`,`#7fff00`,`#d2691e`,`#ff7f50`,`#6495ed`,`#fff8dc`,`#dc143c`,`#ffd700`,
				`#daa520`,`#adff2f`,`#808080`,`#f0fff0`,`#ff69b4`,`#cd5c5c`,`#4b0082`];

 
var user =  """),format.raw/*54.13*/("""{"""),format.raw/*54.14*/("""name: """""),format.raw/*54.22*/("""}"""),format.raw/*54.23*/(""";

  
var SNAKE = """),format.raw/*57.13*/("""{"""),format.raw/*57.14*/("""
  """),format.raw/*58.3*/("""x: 160,
  y: 160,
  
  // snakes[mySnakeNo] velocity. moves one grid length every frame in either the x or y direction
  dx: grid,
  dy: 0,
  
  // keep track of all grids the snakes[mySnakeNo] body occupies
  cells: [],
  
  // length of the snakes[mySnakeNo]. grows when eating an apple
  maxCells: 4,
  
  reset: false
"""),format.raw/*72.1*/("""}"""),format.raw/*72.2*/(""";

var apple = """),format.raw/*74.13*/("""{"""),format.raw/*74.14*/("""
  """),format.raw/*75.3*/("""x: 320,
  y: 320
"""),format.raw/*77.1*/("""}"""),format.raw/*77.2*/(""";

var range = """),format.raw/*79.13*/("""{"""),format.raw/*79.14*/("""
"""),format.raw/*80.1*/("""min:0,
max:25
"""),format.raw/*82.1*/("""}"""),format.raw/*82.2*/(""";

<!-- // get random whole numbers in a specific range
// see https://stackoverflow.com/a/1527820/2124254 -->

function sleep (time) 
"""),format.raw/*88.1*/("""{"""),format.raw/*88.2*/("""
  """),format.raw/*89.3*/("""return new Promise((resolve) => setTimeout(resolve, time));
"""),format.raw/*90.1*/("""}"""),format.raw/*90.2*/("""

"""),format.raw/*92.1*/("""function UserAction(func) 
"""),format.raw/*93.1*/("""{"""),format.raw/*93.2*/("""

	"""),format.raw/*95.2*/("""var xhttp;
    xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
								"""),format.raw/*98.9*/("""{"""),format.raw/*98.10*/("""
									"""),format.raw/*99.10*/("""if (this.readyState == 4 && this.status == 200) 
									"""),format.raw/*100.10*/("""{"""),format.raw/*100.11*/("""
										"""),format.raw/*101.11*/("""var obj;
										try
										"""),format.raw/*103.11*/("""{"""),format.raw/*103.12*/("""
  									    """),format.raw/*104.16*/("""obj = JSON.parse (this.responseText);
										//alert ("Response "+JSON.stringify(obj));
										if (obj != null)
											func (obj);
										"""),format.raw/*108.11*/("""}"""),format.raw/*108.12*/("""
										"""),format.raw/*109.11*/("""catch (err) """),format.raw/*109.23*/("""{"""),format.raw/*109.24*/("""}"""),format.raw/*109.25*/("""
									"""),format.raw/*110.10*/("""}"""),format.raw/*110.11*/("""	
								"""),format.raw/*111.9*/("""}"""),format.raw/*111.10*/(""";
    
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
	//alert ("Request "+url+JSON.stringify(inputJson));
	xhttp.send(inputJson);
"""),format.raw/*117.1*/("""}"""),format.raw/*117.2*/(""" 

"""),format.raw/*119.1*/("""function createMyWebSocket()
"""),format.raw/*120.1*/("""{"""),format.raw/*120.2*/("""
	"""),format.raw/*121.2*/("""websocket = new WebSocket("ws://snakegame.com:9000/ws", );
    websocket.onopen = function(evt) """),format.raw/*122.38*/("""{"""),format.raw/*122.39*/(""" """),format.raw/*122.40*/("""onOpen(evt) """),format.raw/*122.52*/("""}"""),format.raw/*122.53*/(""";
    websocket.onclose = function(evt) """),format.raw/*123.39*/("""{"""),format.raw/*123.40*/(""" """),format.raw/*123.41*/("""onClose(evt) """),format.raw/*123.54*/("""}"""),format.raw/*123.55*/(""";
    websocket.onmessage = function(evt) """),format.raw/*124.41*/("""{"""),format.raw/*124.42*/(""" """),format.raw/*124.43*/("""onMessage(evt) """),format.raw/*124.58*/("""}"""),format.raw/*124.59*/(""";
    websocket.onerror = function(evt) """),format.raw/*125.39*/("""{"""),format.raw/*125.40*/(""" """),format.raw/*125.41*/("""onError(evt) """),format.raw/*125.54*/("""}"""),format.raw/*125.55*/(""";
"""),format.raw/*126.1*/("""}"""),format.raw/*126.2*/("""

"""),format.raw/*128.1*/("""function onOpen(evt) """),format.raw/*128.22*/("""{"""),format.raw/*128.23*/("""
                  """),format.raw/*129.19*/("""//alert ("Connected to server");
				  joinGame ("GauthamVatFirst");
               """),format.raw/*131.16*/("""}"""),format.raw/*131.17*/("""
					
"""),format.raw/*133.1*/("""function onClose(evt) """),format.raw/*133.23*/("""{"""),format.raw/*133.24*/("""
    """),format.raw/*134.5*/("""alert ("DisConnected from server");
	mySnakeNo = -1;
	running = false;
	joined = false;
	snakes = [];
"""),format.raw/*139.1*/("""}"""),format.raw/*139.2*/("""
	
"""),format.raw/*141.1*/("""function onMessage(evt) """),format.raw/*141.25*/("""{"""),format.raw/*141.26*/("""
	
  """),format.raw/*143.3*/("""var message = evt.data;
  receiveMessage (evt.data);	
  
"""),format.raw/*146.1*/("""}"""),format.raw/*146.2*/("""
	
"""),format.raw/*148.1*/("""function onError(evt) """),format.raw/*148.23*/("""{"""),format.raw/*148.24*/("""
    """),format.raw/*149.5*/("""alert (evt.data);
"""),format.raw/*150.1*/("""}"""),format.raw/*150.2*/("""
	
"""),format.raw/*152.1*/("""function addMessage(message) """),format.raw/*152.30*/("""{"""),format.raw/*152.31*/("""
  
   """),format.raw/*154.4*/("""websocket.send(message);
"""),format.raw/*155.1*/("""}"""),format.raw/*155.2*/("""



"""),format.raw/*159.1*/("""function dummy(obj)
"""),format.raw/*160.1*/("""{"""),format.raw/*160.2*/("""
"""),format.raw/*161.1*/("""}"""),format.raw/*161.2*/("""


"""),format.raw/*164.1*/("""function setSnake() 
"""),format.raw/*165.1*/("""{"""),format.raw/*165.2*/("""
	"""),format.raw/*166.2*/("""var api =	 """),format.raw/*166.13*/("""{"""),format.raw/*166.14*/("""	"""),format.raw/*166.15*/("""name: "setSnake""""),format.raw/*166.31*/("""}"""),format.raw/*166.32*/(""";
	var userno = """),format.raw/*167.15*/("""{"""),format.raw/*167.16*/(""" """),format.raw/*167.17*/("""no: mySnakeNo """),format.raw/*167.31*/("""}"""),format.raw/*167.32*/(""";
	
	var request =
	"""),format.raw/*170.2*/("""{"""),format.raw/*170.3*/("""
	  """),format.raw/*171.4*/("""no:userno,
	  api:api,
	  sn:snakes[mySnakeNo]
	"""),format.raw/*174.2*/("""}"""),format.raw/*174.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*177.1*/("""}"""),format.raw/*177.2*/("""

"""),format.raw/*179.1*/("""function setApplePosition_p2(obj)
"""),format.raw/*180.1*/("""{"""),format.raw/*180.2*/("""
	"""),format.raw/*181.2*/("""apple.x=obj.event.apple.x;
	apple.y=obj.event.apple.y;
"""),format.raw/*183.1*/("""}"""),format.raw/*183.2*/("""


"""),format.raw/*186.1*/("""function setApplePosition(min, max) 
"""),format.raw/*187.1*/("""{"""),format.raw/*187.2*/("""
	"""),format.raw/*188.2*/("""var api =	 """),format.raw/*188.13*/("""{"""),format.raw/*188.14*/(""" """),format.raw/*188.15*/("""name: "setApplePosition""""),format.raw/*188.39*/("""}"""),format.raw/*188.40*/(""";
	var userno = """),format.raw/*189.15*/("""{"""),format.raw/*189.16*/(""" """),format.raw/*189.17*/("""no: mySnakeNo """),format.raw/*189.31*/("""}"""),format.raw/*189.32*/(""";
	var range =  """),format.raw/*190.15*/("""{"""),format.raw/*190.16*/(""" """),format.raw/*190.17*/("""min:min, max:max """),format.raw/*190.34*/("""}"""),format.raw/*190.35*/(""";
	
	var request =
	"""),format.raw/*193.2*/("""{"""),format.raw/*193.3*/("""
	  """),format.raw/*194.4*/("""no:userno,
	  api:api,
	  range:range
	"""),format.raw/*197.2*/("""}"""),format.raw/*197.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*200.1*/("""}"""),format.raw/*200.2*/("""

"""),format.raw/*202.1*/("""// game loop
function animate() 
"""),format.raw/*204.1*/("""{"""),format.raw/*204.2*/("""
		"""),format.raw/*205.3*/("""if (running)
			requestAnimationFrame(animate); // animate will be called for 60 times in a second
		else
			gameover_p2();
		
  // slow game loop to 15 fps instead of 60 (60/15 = 4)
		if (++count < 4) // Maximum retries.
			return;
		 
		count = 0;
  
        draw ();
 
"""),format.raw/*218.1*/("""}"""),format.raw/*218.2*/("""

"""),format.raw/*220.1*/("""function draw ()
"""),format.raw/*221.1*/("""{"""),format.raw/*221.2*/("""
  """),format.raw/*222.3*/("""if (!joined)
		return;

  if (!running)
		return;
  
  if ( snakes.length == 0 )
		return;
		
  if (mySnakeNo == -1)
		return;
  
  context.clearRect(0,0,canvas.width,canvas.height);
  
  for ( var i = 0; i < snakes.length; i++)
  """),format.raw/*237.3*/("""{"""),format.raw/*237.4*/("""
	 """),format.raw/*238.3*/("""if (!running ) //Inside the loop the snake would have bitten itself and would have to stop running.
		break;
		
	  if (	snakes[i] == null )
		continue;
		  
	  // COMMENTED context.clearRect(0,0,canvas.width,canvas.height);
	  // move snakes[mySnakeNo] by it's velocity
	  snakes[i].x += snakes[i].dx;
	  snakes[i].y += snakes[i].dy;
	  // wrap snakes[mySnakeNo] position horizontally on edge of screen
	  
	  //QUICKER UPDATE FOR OTHERS
	  //setSnake ();

	  if (snakes[i].x < 0) 
	  """),format.raw/*254.4*/("""{"""),format.raw/*254.5*/("""
		"""),format.raw/*255.3*/("""snakes[i].x = canvas.width - grid;
	  """),format.raw/*256.4*/("""}"""),format.raw/*256.5*/("""
	  """),format.raw/*257.4*/("""else if (snakes[i].x >= canvas.width) 
	  """),format.raw/*258.4*/("""{"""),format.raw/*258.5*/("""
		"""),format.raw/*259.3*/("""snakes[i].x = 0;
	  """),format.raw/*260.4*/("""}"""),format.raw/*260.5*/("""
	  	   
	  """),format.raw/*262.4*/("""//QUICKER UPDATE FOR OTHERS
	  //setSnake ();
	  
	  // wrap snakes[mySnakeNo] position vertically on edge of screen
	  if (snakes[i].y < 0) """),format.raw/*266.25*/("""{"""),format.raw/*266.26*/("""
		"""),format.raw/*267.3*/("""snakes[i].y = canvas.height - grid;
	  """),format.raw/*268.4*/("""}"""),format.raw/*268.5*/("""
	  """),format.raw/*269.4*/("""else if (snakes[i].y >= canvas.height) """),format.raw/*269.43*/("""{"""),format.raw/*269.44*/("""
		"""),format.raw/*270.3*/("""snakes[i].y = 0;
	  """),format.raw/*271.4*/("""}"""),format.raw/*271.5*/("""
	
	  """),format.raw/*273.4*/("""// keep track of where snakes[mySnakeNo] has been. front of the array is always the head
	  snakes[i].cells.unshift( """),format.raw/*274.29*/("""{"""),format.raw/*274.30*/("""x: snakes[i].x, y: snakes[i].y"""),format.raw/*274.60*/("""}"""),format.raw/*274.61*/(""" """),format.raw/*274.62*/(""");
	  
	  //QUICKER UPDATE FOR OTHERS
	  //setSnake ();

	  // remove cells as we move away from them
	  if (snakes[i].cells.length > snakes[i].maxCells) 
	  """),format.raw/*281.4*/("""{"""),format.raw/*281.5*/("""
		"""),format.raw/*282.3*/("""snakes[i].cells.pop();
	  """),format.raw/*283.4*/("""}"""),format.raw/*283.5*/("""
	  	"""),format.raw/*284.5*/("""//QUICKER UPDATE FOR OTHERS
        //setSnake ();
	  
 	  // draw apple
	  context.fillStyle = 'red';
	  context.fillRect(apple.x, apple.y, grid-1, grid-1);
	  // draw snakes[mySnakeNo] one cell at a time
	  context.fillStyle = rgbcode[i%snakes.length];
	  
	  snakes[i].cells.forEach( function(cell, index) """),format.raw/*293.51*/("""{"""),format.raw/*293.52*/("""
		
		"""),format.raw/*295.3*/("""// drawing 1 px smaller than the grid creates a grid effect in the snakes[mySnakeNo] body so you can see how long it is
		context.fillRect(cell.x, cell.y, grid-1, grid-1);  
		// snakes[mySnakeNo] ate apple
		if (i == mySnakeNo)
		"""),format.raw/*299.3*/("""{"""),format.raw/*299.4*/("""
			"""),format.raw/*300.4*/("""if (cell.x == apple.x && cell.y == apple.y) 
			"""),format.raw/*301.4*/("""{"""),format.raw/*301.5*/("""
				  """),format.raw/*302.7*/("""snakes[i].maxCells++; 
  				  score+=10;
				  setApplePosition ();
			"""),format.raw/*305.4*/("""}"""),format.raw/*305.5*/("""
		  """),format.raw/*306.5*/("""}"""),format.raw/*306.6*/("""
		"""),format.raw/*307.3*/("""// To display score
		document.getElementById("score").innerHTML = "Score:" + score;

		// check collision with all cells after this one (modified bubble sort)
		if ( i == mySnakeNo)
		"""),format.raw/*312.3*/("""{"""),format.raw/*312.4*/("""
			"""),format.raw/*313.4*/("""for (var j = index+1; j < snakes[i].cells.length; j++) 
			"""),format.raw/*314.4*/("""{"""),format.raw/*314.5*/(""" 
			  """),format.raw/*315.6*/("""// snakes[mySnakeNo] occupies same space as a body part. reset game	  
			  if (cell.x == snakes[i].cells[j].x && cell.y == snakes[i].cells[j].y) 
			  """),format.raw/*317.6*/("""{"""),format.raw/*317.7*/("""
					"""),format.raw/*318.6*/("""running = false;
					snakes[mySnakeNo] = SNAKE;
					exitGame ("GauthamV");	
					break;
				"""),format.raw/*322.5*/("""}"""),format.raw/*322.6*/("""	
			 """),format.raw/*323.5*/("""}"""),format.raw/*323.6*/("""
		"""),format.raw/*324.3*/("""}"""),format.raw/*324.4*/("""
	  """),format.raw/*325.4*/("""}"""),format.raw/*325.5*/(""");
	  if (running)
		setSnake ();	
	"""),format.raw/*328.2*/("""}"""),format.raw/*328.3*/("""
	
"""),format.raw/*330.1*/("""}"""),format.raw/*330.2*/("""

"""),format.raw/*332.1*/("""// game over fn

function gameover_p2()
"""),format.raw/*335.1*/("""{"""),format.raw/*335.2*/("""
	"""),format.raw/*336.2*/("""context = canvas.getContext('2d');
	context.font = "30px Arial";
	context.fillStyle = "white";
	context.textAlign = "center";
	context.fillText("Gameover!", canvas.width / 2, 30);
	context.fillText("Press enter to play again", canvas.width / 2, 60);
	context.fillText("Score="+score, canvas.width / 2, 90);
"""),format.raw/*343.1*/("""}"""),format.raw/*343.2*/("""



"""),format.raw/*347.1*/("""function processEvents_p2(obj)
"""),format.raw/*348.1*/("""{"""),format.raw/*348.2*/("""
		"""),format.raw/*349.3*/("""if (obj.event)
		"""),format.raw/*350.3*/("""{"""),format.raw/*350.4*/("""	
			"""),format.raw/*351.4*/("""if (obj.event.no != -1)
			"""),format.raw/*352.4*/("""{"""),format.raw/*352.5*/("""
				"""),format.raw/*353.5*/("""/*if (obj.event.sn.reset == true)
				"""),format.raw/*354.5*/("""{"""),format.raw/*354.6*/("""
					"""),format.raw/*355.6*/("""snakes[obj.event.no] = SNAKE;

					score=0;
					running=true;
					requestAnimationFrame(animate);
				"""),format.raw/*360.5*/("""}"""),format.raw/*360.6*/("""
				"""),format.raw/*361.5*/("""else*/
				"""),format.raw/*362.5*/("""{"""),format.raw/*362.6*/("""
					"""),format.raw/*363.6*/("""snakes[obj.event.no].dx = obj.event.sn.dx;
					snakes[obj.event.no].dy = obj.event.sn.dy;
				"""),format.raw/*365.5*/("""}"""),format.raw/*365.6*/("""
			"""),format.raw/*366.4*/("""}"""),format.raw/*366.5*/("""
		"""),format.raw/*367.3*/("""}"""),format.raw/*367.4*/("""
"""),format.raw/*368.1*/("""}"""),format.raw/*368.2*/("""

"""),format.raw/*370.1*/("""function processEvents (e)
"""),format.raw/*371.1*/("""{"""),format.raw/*371.2*/("""
	"""),format.raw/*372.2*/("""var api =	"""),format.raw/*372.12*/("""{"""),format.raw/*372.13*/("""	"""),format.raw/*372.14*/("""name: "processEvents"	"""),format.raw/*372.36*/("""}"""),format.raw/*372.37*/(""";
	var event = """),format.raw/*373.14*/("""{"""),format.raw/*373.15*/("""	"""),format.raw/*373.16*/("""code:e.code	"""),format.raw/*373.28*/("""}"""),format.raw/*373.29*/(""";
	var userno = """),format.raw/*374.15*/("""{"""),format.raw/*374.16*/(""" """),format.raw/*374.17*/("""no: mySnakeNo """),format.raw/*374.31*/("""}"""),format.raw/*374.32*/(""";
	
	var request =
	"""),format.raw/*377.2*/("""{"""),format.raw/*377.3*/("""
	  """),format.raw/*378.4*/("""no:userno,
	  api:api,
	  key:event,
	  sn:snakes[mySnakeNo]
	"""),format.raw/*382.2*/("""}"""),format.raw/*382.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*385.1*/("""}"""),format.raw/*385.2*/("""

"""),format.raw/*387.1*/("""function joinGame_p2 (obj)
"""),format.raw/*388.1*/("""{"""),format.raw/*388.2*/("""
	"""),format.raw/*389.2*/("""if (obj.event.name == "addSnake")
	"""),format.raw/*390.2*/("""{"""),format.raw/*390.3*/("""
		"""),format.raw/*391.3*/("""snakes[obj.event.no]=SNAKE;
		mySnakeNo = obj.event.no;	
		//alert ("joinGame_p2 Snake No: "+ obj.event.no + JSON.stringify (snakes[obj.event.no]));
		joined=true;
		running = true;
		score=0;
		animate ();
		if (!listenerAdded)
		"""),format.raw/*399.3*/("""{"""),format.raw/*399.4*/("""
			"""),format.raw/*400.4*/("""document.addEventListener('keydown', processEvents,true );
			listenerAdded = true;
		"""),format.raw/*402.3*/("""}"""),format.raw/*402.4*/("""
	"""),format.raw/*403.2*/("""}"""),format.raw/*403.3*/("""
"""),format.raw/*404.1*/("""}"""),format.raw/*404.2*/("""

"""),format.raw/*406.1*/("""function joinGame (gamer)
"""),format.raw/*407.1*/("""{"""),format.raw/*407.2*/("""
	"""),format.raw/*408.2*/("""var api =	"""),format.raw/*408.12*/("""{"""),format.raw/*408.13*/("""	"""),format.raw/*408.14*/("""name: "joinGame" """),format.raw/*408.31*/("""}"""),format.raw/*408.32*/(""";
	user.name = gamer;
	
	var request =
	"""),format.raw/*412.2*/("""{"""),format.raw/*412.3*/("""
	  """),format.raw/*413.4*/("""api:api,
	  userid:user
	"""),format.raw/*415.2*/("""}"""),format.raw/*415.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*418.1*/("""}"""),format.raw/*418.2*/("""

"""),format.raw/*420.1*/("""function exitGame_p2 (obj)
"""),format.raw/*421.1*/("""{"""),format.raw/*421.2*/("""
	"""),format.raw/*422.2*/("""if (obj.event.name == "removeSnake")
	"""),format.raw/*423.2*/("""{"""),format.raw/*423.3*/("""
		"""),format.raw/*424.3*/("""snakes[obj.event.no]=null;
		mySnakeNo = -1;	
		joined=false;
	"""),format.raw/*427.2*/("""}"""),format.raw/*427.3*/("""
"""),format.raw/*428.1*/("""}"""),format.raw/*428.2*/("""

"""),format.raw/*430.1*/("""function exitGame (gamer)
"""),format.raw/*431.1*/("""{"""),format.raw/*431.2*/("""
	"""),format.raw/*432.2*/("""var api =	"""),format.raw/*432.12*/("""{"""),format.raw/*432.13*/("""	"""),format.raw/*432.14*/("""name: "exitGame" """),format.raw/*432.31*/("""}"""),format.raw/*432.32*/(""";
	user.name = gamer;
	var userno = """),format.raw/*434.15*/("""{"""),format.raw/*434.16*/(""" """),format.raw/*434.17*/("""no: mySnakeNo """),format.raw/*434.31*/("""}"""),format.raw/*434.32*/(""";
	
	var request =
	"""),format.raw/*437.2*/("""{"""),format.raw/*437.3*/("""
	  """),format.raw/*438.4*/("""no:userno,
	  api:api,
	  userid:user
	"""),format.raw/*441.2*/("""}"""),format.raw/*441.3*/(""";
	inputJson = JSON.stringify(request);
	addMessage (inputJson);
"""),format.raw/*444.1*/("""}"""),format.raw/*444.2*/("""

"""),format.raw/*446.1*/("""function eventHandler (obj)
"""),format.raw/*447.1*/("""{"""),format.raw/*447.2*/("""
	"""),format.raw/*448.2*/("""if (obj.event.name == "addSnake")
	"""),format.raw/*449.2*/("""{"""),format.raw/*449.3*/("""
		"""),format.raw/*450.3*/("""snakes[obj.event.no] = SNAKE;
		TotalPlayers ++;
	"""),format.raw/*452.2*/("""}"""),format.raw/*452.3*/("""
	"""),format.raw/*453.2*/("""else if (obj.event.name == "removeSnake")
	"""),format.raw/*454.2*/("""{"""),format.raw/*454.3*/("""
		"""),format.raw/*455.3*/("""snakes[obj.event.no]=null;
		TotalPlayers--;
	"""),format.raw/*457.2*/("""}"""),format.raw/*457.3*/("""
	"""),format.raw/*458.2*/("""else if (obj.event.name == "appleMoved")
	"""),format.raw/*459.2*/("""{"""),format.raw/*459.3*/("""
		"""),format.raw/*460.3*/("""setApplePosition_p2 (obj);
	"""),format.raw/*461.2*/("""}"""),format.raw/*461.3*/("""
	"""),format.raw/*462.2*/("""else if (obj.event.name == "availableSnakes")
	"""),format.raw/*463.2*/("""{"""),format.raw/*463.3*/("""
		"""),format.raw/*464.3*/("""snakes[obj.event.no]=obj.event.sn;
	"""),format.raw/*465.2*/("""}"""),format.raw/*465.3*/("""
	"""),format.raw/*466.2*/("""else if (obj.event.name == "snakeNewPositioned")
	"""),format.raw/*467.2*/("""{"""),format.raw/*467.3*/("""
		"""),format.raw/*468.3*/("""snakes[obj.event.no]=obj.event.sn;
	"""),format.raw/*469.2*/("""}"""),format.raw/*469.3*/("""
	"""),format.raw/*470.2*/("""else if (obj.event.name == "keyPressed")
	"""),format.raw/*471.2*/("""{"""),format.raw/*471.3*/("""
			"""),format.raw/*472.4*/("""/*if (obj.event.sn.reset == true)
			"""),format.raw/*473.4*/("""{"""),format.raw/*473.5*/("""
				"""),format.raw/*474.5*/("""snakes[obj.event.no] = SNAKE;
				
				score=0;
				running=true;
				requestAnimationFrame(animate);
			"""),format.raw/*479.4*/("""}"""),format.raw/*479.5*/("""
			"""),format.raw/*480.4*/("""else*/
			"""),format.raw/*481.4*/("""{"""),format.raw/*481.5*/("""
				"""),format.raw/*482.5*/("""snakes[obj.event.no].dx = obj.event.sn.dx;
				snakes[obj.event.no].dy = obj.event.sn.dy; 
			"""),format.raw/*484.4*/("""}"""),format.raw/*484.5*/("""
	"""),format.raw/*485.2*/("""}"""),format.raw/*485.3*/("""
	"""),format.raw/*486.2*/("""else if (obj.event.name == "retry")
	"""),format.raw/*487.2*/("""{"""),format.raw/*487.3*/("""
		"""),format.raw/*488.3*/("""joinGame ("GauthamVfromrety");
	"""),format.raw/*489.2*/("""}"""),format.raw/*489.3*/("""
"""),format.raw/*490.1*/("""}"""),format.raw/*490.2*/("""



"""),format.raw/*494.1*/("""function receiveMessage(message) """),format.raw/*494.34*/("""{"""),format.raw/*494.35*/("""

  """),format.raw/*496.3*/("""try
  """),format.raw/*497.3*/("""{"""),format.raw/*497.4*/("""
	"""),format.raw/*498.2*/("""if (message.length)
	"""),format.raw/*499.2*/("""{"""),format.raw/*499.3*/("""
		"""),format.raw/*500.3*/("""var obj = JSON.parse (message);
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
	"""),format.raw/*513.2*/("""}"""),format.raw/*513.3*/("""
  """),format.raw/*514.3*/("""}"""),format.raw/*514.4*/("""
  """),format.raw/*515.3*/("""catch (err)
  """),format.raw/*516.3*/("""{"""),format.raw/*516.4*/("""
	"""),format.raw/*517.2*/("""alert (message + err);
  """),format.raw/*518.3*/("""}"""),format.raw/*518.4*/("""
"""),format.raw/*519.1*/("""}"""),format.raw/*519.2*/("""


"""),format.raw/*522.1*/("""function onBrowserTabExit ()
"""),format.raw/*523.1*/("""{"""),format.raw/*523.2*/("""
	"""),format.raw/*524.2*/("""if (joined == true)
	"""),format.raw/*525.2*/("""{"""),format.raw/*525.3*/("""
		"""),format.raw/*526.3*/("""running = false;
		exitGame ("GauthamV");
		sleep(500);
	"""),format.raw/*529.2*/("""}"""),format.raw/*529.3*/("""	
"""),format.raw/*530.1*/("""}"""),format.raw/*530.2*/("""

"""),format.raw/*532.1*/("""window.addEventListener('beforeunload', onBrowserTabExit,true );
//window.onbeforeunload = onBrowserTabExit ();

// listen to keyboard events to move the snakes[mySnakeNo]
createMyWebSocket();

// start the game

running = true;

</script>
</body>
</html>


"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Apr 06 09:58:14 IST 2019
                  SOURCE: C:/Users/vijayarajp/assignments/assignment-1-mug/app/views/index.scala.html
                  HASH: 2feea4db0725d3d5b44896d173663b5f56987791
                  MATRIX: 1030->0|1125->69|1152->70|1182->74|1245->111|1272->112|1303->116|1342->127|1371->128|1404->134|1464->167|1492->168|1523->172|1555->177|1583->178|1616->184|1741->282|1769->283|1800->287|1835->294|1864->295|1897->301|1952->329|1980->330|2009->332|3247->1542|3276->1543|3312->1551|3341->1552|3390->1573|3419->1574|3450->1578|3813->1914|3841->1915|3886->1932|3915->1933|3946->1937|3992->1956|4020->1957|4065->1974|4094->1975|4123->1977|4166->1993|4194->1994|4362->2135|4390->2136|4421->2140|4509->2201|4537->2202|4568->2206|4623->2234|4651->2235|4683->2240|4806->2336|4835->2337|4874->2348|4962->2407|4992->2408|5033->2420|5097->2455|5127->2456|5173->2473|5357->2628|5387->2629|5428->2641|5469->2653|5499->2654|5529->2655|5569->2666|5599->2667|5638->2678|5668->2679|5886->2869|5915->2870|5948->2875|6006->2905|6035->2906|6066->2909|6192->3006|6222->3007|6252->3008|6293->3020|6323->3021|6393->3062|6423->3063|6453->3064|6495->3077|6525->3078|6597->3121|6627->3122|6657->3123|6701->3138|6731->3139|6801->3180|6831->3181|6861->3182|6903->3195|6933->3196|6964->3199|6993->3200|7025->3204|7075->3225|7105->3226|7154->3246|7269->3332|7299->3333|7336->3342|7387->3364|7417->3365|7451->3371|7586->3478|7615->3479|7648->3484|7701->3508|7731->3509|7766->3516|7854->3576|7883->3577|7916->3582|7967->3604|7997->3605|8031->3611|8078->3630|8107->3631|8140->3636|8198->3665|8228->3666|8265->3675|8319->3701|8348->3702|8384->3710|8433->3731|8462->3732|8492->3734|8521->3735|8555->3741|8605->3763|8634->3764|8665->3767|8705->3778|8735->3779|8765->3780|8810->3796|8840->3797|8886->3814|8916->3815|8946->3816|8989->3830|9019->3831|9070->3854|9099->3855|9132->3860|9211->3911|9240->3912|9336->3980|9365->3981|9397->3985|9460->4020|9489->4021|9520->4024|9605->4081|9634->4082|9668->4088|9734->4126|9763->4127|9794->4130|9834->4141|9864->4142|9894->4143|9947->4167|9977->4168|10023->4185|10053->4186|10083->4187|10126->4201|10156->4202|10202->4219|10232->4220|10262->4221|10308->4238|10338->4239|10389->4262|10418->4263|10451->4268|10521->4310|10550->4311|10646->4379|10675->4380|10707->4384|10770->4419|10799->4420|10831->4424|11144->4709|11173->4710|11205->4714|11251->4732|11280->4733|11312->4737|11586->4983|11615->4984|11647->4988|12176->5489|12205->5490|12237->5494|12304->5533|12333->5534|12366->5539|12437->5582|12466->5583|12498->5587|12547->5608|12576->5609|12618->5623|12792->5768|12822->5769|12854->5773|12922->5813|12951->5814|12984->5819|13052->5858|13082->5859|13114->5863|13163->5884|13192->5885|13228->5893|13375->6011|13405->6012|13464->6042|13494->6043|13524->6044|13717->6209|13746->6210|13778->6214|13833->6241|13862->6242|13896->6248|14243->6566|14273->6567|14309->6575|14572->6810|14601->6811|14634->6816|14711->6865|14740->6866|14776->6874|14879->6949|14908->6950|14942->6956|14971->6957|15003->6961|15221->7151|15250->7152|15283->7157|15371->7217|15400->7218|15436->7226|15618->7380|15647->7381|15682->7388|15808->7486|15837->7487|15872->7494|15901->7495|15933->7499|15962->7500|15995->7505|16024->7506|16091->7545|16120->7546|16153->7551|16182->7552|16214->7556|16285->7599|16314->7600|16345->7603|16687->7917|16716->7918|16752->7926|16812->7958|16841->7959|16873->7963|16919->7981|16948->7982|16982->7988|17038->8016|17067->8017|17101->8023|17168->8062|17197->8063|17232->8070|17370->8180|17399->8181|17433->8187|17473->8199|17502->8200|17537->8207|17662->8304|17691->8305|17724->8310|17753->8311|17785->8315|17814->8316|17844->8318|17873->8319|17905->8323|17961->8351|17990->8352|18021->8355|18060->8365|18090->8366|18120->8367|18171->8389|18201->8390|18246->8406|18276->8407|18306->8408|18347->8420|18377->8421|18423->8438|18453->8439|18483->8440|18526->8454|18556->8455|18607->8478|18636->8479|18669->8484|18763->8550|18792->8551|18888->8619|18917->8620|18949->8624|19005->8652|19034->8653|19065->8656|19129->8692|19158->8693|19190->8697|19457->8936|19486->8937|19519->8942|19635->9030|19664->9031|19695->9034|19724->9035|19754->9037|19783->9038|19815->9042|19870->9069|19899->9070|19930->9073|19969->9083|19999->9084|20029->9085|20075->9102|20105->9103|20177->9147|20206->9148|20239->9153|20294->9180|20323->9181|20419->9249|20448->9250|20480->9254|20536->9282|20565->9283|20596->9286|20663->9325|20692->9326|20724->9330|20818->9396|20847->9397|20877->9399|20906->9400|20938->9404|20993->9431|21022->9432|21053->9435|21092->9445|21122->9446|21152->9447|21198->9464|21228->9465|21295->9503|21325->9504|21355->9505|21398->9519|21428->9520|21479->9543|21508->9544|21541->9549|21611->9591|21640->9592|21736->9660|21765->9661|21797->9665|21854->9694|21883->9695|21914->9698|21978->9734|22007->9735|22039->9739|22119->9791|22148->9792|22179->9795|22251->9839|22280->9840|22312->9844|22388->9892|22417->9893|22448->9896|22519->9939|22548->9940|22580->9944|22637->9973|22666->9974|22697->9977|22773->10025|22802->10026|22834->10030|22899->10067|22928->10068|22959->10071|23038->10122|23067->10123|23099->10127|23164->10164|23193->10165|23224->10168|23295->10211|23324->10212|23357->10217|23423->10255|23452->10256|23486->10262|23624->10372|23653->10373|23686->10378|23725->10389|23754->10390|23788->10396|23912->10492|23941->10493|23972->10496|24001->10497|24032->10500|24098->10538|24127->10539|24159->10543|24220->10576|24249->10577|24279->10579|24308->10580|24344->10588|24406->10621|24436->10622|24470->10628|24505->10635|24534->10636|24565->10639|24615->10661|24644->10662|24676->10666|25127->11089|25156->11090|25188->11094|25217->11095|25249->11099|25292->11114|25321->11115|25352->11118|25406->11144|25435->11145|25465->11147|25494->11148|25528->11154|25586->11184|25615->11185|25646->11188|25696->11210|25725->11211|25757->11215|25845->11275|25874->11276|25905->11279|25934->11280|25966->11284
                  LINES: 33->1|38->6|38->6|39->7|41->9|41->9|42->10|42->10|42->10|43->11|45->13|45->13|46->14|46->14|46->14|47->15|51->19|51->19|52->20|52->20|52->20|53->21|54->22|54->22|55->23|86->54|86->54|86->54|86->54|89->57|89->57|90->58|104->72|104->72|106->74|106->74|107->75|109->77|109->77|111->79|111->79|112->80|114->82|114->82|120->88|120->88|121->89|122->90|122->90|124->92|125->93|125->93|127->95|130->98|130->98|131->99|132->100|132->100|133->101|135->103|135->103|136->104|140->108|140->108|141->109|141->109|141->109|141->109|142->110|142->110|143->111|143->111|149->117|149->117|151->119|152->120|152->120|153->121|154->122|154->122|154->122|154->122|154->122|155->123|155->123|155->123|155->123|155->123|156->124|156->124|156->124|156->124|156->124|157->125|157->125|157->125|157->125|157->125|158->126|158->126|160->128|160->128|160->128|161->129|163->131|163->131|165->133|165->133|165->133|166->134|171->139|171->139|173->141|173->141|173->141|175->143|178->146|178->146|180->148|180->148|180->148|181->149|182->150|182->150|184->152|184->152|184->152|186->154|187->155|187->155|191->159|192->160|192->160|193->161|193->161|196->164|197->165|197->165|198->166|198->166|198->166|198->166|198->166|198->166|199->167|199->167|199->167|199->167|199->167|202->170|202->170|203->171|206->174|206->174|209->177|209->177|211->179|212->180|212->180|213->181|215->183|215->183|218->186|219->187|219->187|220->188|220->188|220->188|220->188|220->188|220->188|221->189|221->189|221->189|221->189|221->189|222->190|222->190|222->190|222->190|222->190|225->193|225->193|226->194|229->197|229->197|232->200|232->200|234->202|236->204|236->204|237->205|250->218|250->218|252->220|253->221|253->221|254->222|269->237|269->237|270->238|286->254|286->254|287->255|288->256|288->256|289->257|290->258|290->258|291->259|292->260|292->260|294->262|298->266|298->266|299->267|300->268|300->268|301->269|301->269|301->269|302->270|303->271|303->271|305->273|306->274|306->274|306->274|306->274|306->274|313->281|313->281|314->282|315->283|315->283|316->284|325->293|325->293|327->295|331->299|331->299|332->300|333->301|333->301|334->302|337->305|337->305|338->306|338->306|339->307|344->312|344->312|345->313|346->314|346->314|347->315|349->317|349->317|350->318|354->322|354->322|355->323|355->323|356->324|356->324|357->325|357->325|360->328|360->328|362->330|362->330|364->332|367->335|367->335|368->336|375->343|375->343|379->347|380->348|380->348|381->349|382->350|382->350|383->351|384->352|384->352|385->353|386->354|386->354|387->355|392->360|392->360|393->361|394->362|394->362|395->363|397->365|397->365|398->366|398->366|399->367|399->367|400->368|400->368|402->370|403->371|403->371|404->372|404->372|404->372|404->372|404->372|404->372|405->373|405->373|405->373|405->373|405->373|406->374|406->374|406->374|406->374|406->374|409->377|409->377|410->378|414->382|414->382|417->385|417->385|419->387|420->388|420->388|421->389|422->390|422->390|423->391|431->399|431->399|432->400|434->402|434->402|435->403|435->403|436->404|436->404|438->406|439->407|439->407|440->408|440->408|440->408|440->408|440->408|440->408|444->412|444->412|445->413|447->415|447->415|450->418|450->418|452->420|453->421|453->421|454->422|455->423|455->423|456->424|459->427|459->427|460->428|460->428|462->430|463->431|463->431|464->432|464->432|464->432|464->432|464->432|464->432|466->434|466->434|466->434|466->434|466->434|469->437|469->437|470->438|473->441|473->441|476->444|476->444|478->446|479->447|479->447|480->448|481->449|481->449|482->450|484->452|484->452|485->453|486->454|486->454|487->455|489->457|489->457|490->458|491->459|491->459|492->460|493->461|493->461|494->462|495->463|495->463|496->464|497->465|497->465|498->466|499->467|499->467|500->468|501->469|501->469|502->470|503->471|503->471|504->472|505->473|505->473|506->474|511->479|511->479|512->480|513->481|513->481|514->482|516->484|516->484|517->485|517->485|518->486|519->487|519->487|520->488|521->489|521->489|522->490|522->490|526->494|526->494|526->494|528->496|529->497|529->497|530->498|531->499|531->499|532->500|545->513|545->513|546->514|546->514|547->515|548->516|548->516|549->517|550->518|550->518|551->519|551->519|554->522|555->523|555->523|556->524|557->525|557->525|558->526|561->529|561->529|562->530|562->530|564->532
                  -- GENERATED --
              */
          