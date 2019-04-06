package controllers;

import java.util.*;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import play.mvc.*;
import play.libs.Json;
import play.api.Logger.*;
import play.*;
import play.libs.streams.ActorFlow;

import akka.actor.*;
import akka.stream.*;



class KeyPressed
{
	String code;
	
	public KeyPressed ( String key)
	{ code = key;}
}

class Range
{
	int min;
	int max;
	
	public Range () { min=0; max=25; }
}

class CoOrd
{
	int cx;
	int cy;
	
	public CoOrd (int x, int y) { cx=x; cy=y; }
	public void set (int x, int y) { cx=x; cy=y; }
	public int getx() {return cx;}
	public int gety() {return cy;}
}

class Apple
{
	int x;
	int y;
	
	public Apple () { x=320; y=320; }
}

class Snake
{
	
	int x,y,dx,dy,grid,maxCells;
	CoOrd cells [];
	Logger.ALogger logger;
	boolean reset = false;
  
  // dx, dy snake velocity. moves one grid length every frame in either the x or y direction
 
  // cells []keep track of all grids the snake body occupies
  
  // length of the snake. grows when eating an apple
  
  
	  public Snake ()
	  {
		  x=160;
		  y = 160;
		  grid = 16;
		  dx = 16;
		  dy = 0;
		  maxCells = 4;
		  cells  = new CoOrd [4];
	  }  
		public ObjectNode toObjectNode() 
		{ 
			ObjectNode result = Json.newObject();
			ArrayNode arrayNode = new ObjectMapper().createArrayNode();
			
			for ( int i=0; i < cells.length; i++)    
			{
				if ( cells[i] != null)
				{
					ObjectNode node = Json.newObject();
					node.put ("x", cells[i].getx());
					node.put ("y", cells[i].gety());
					arrayNode.add (node);	
				}
			}				
			result.put("x" ,x);
			result.put("y" , y);
			result.put("dx" , dx);
			result.put("dy" , dy);
			result.put("cells" , arrayNode);
			result.put("maxCells" ,maxCells);
			
			result.put("reset" , reset);
			return result; 
		} 
  }
	

class  SnakeBoard
{
	int users=0;
	
	ArrayList<String> usernames = new ArrayList<String> ();
	ArrayList<Snake> snakes = new ArrayList<Snake> ();
	ArrayList<MyWebSocketActor> endPoints = new ArrayList<MyWebSocketActor> ();
	
	Apple apple;
	Range range;
	int grid = 16;
	int count = 0;
	int score=0;
	boolean running = false;
	Logger.ALogger logger;
	
	public SnakeBoard ()
	{
		grid = 16;
		apple = new Apple ();
		range = new Range ();
	}

	
	public synchronized int addSnakeFor (String name)
	{
		users++;
		usernames.add (name);
		snakes.add (new Snake());
		return (snakes.size()-1);
	}
	
	 public synchronized void  removeSnakeFor (int no)
	{
		if (users > 0)
		{
			usernames.set (no,null);
			snakes.set (no, null);
			users--;
		}
	}
	
} 


class MyWebSocketActor extends AbstractActor 
{

    public static Props props(ActorRef out) 
	{
        return Props.create(MyWebSocketActor.class, out);
    }
	
	public static ArrayList <MyWebSocketActor> clients = new ArrayList<MyWebSocketActor> ();
	
	public Receive receiver;
	
	private final ActorRef out;
	
	
	public static void sendAll (String message)
	{
		for (int i =0; i < clients.size(); i++)
		{
			//HomeController.hc.logger.info( "As Event: to client " + Integer.toString (i)+ message);
			MyWebSocketActor item = clients.get (i);
			item.out.tell ( message, item.self() );
		}
	}
	
	public static void sendTo (int no, String message)
	{
		//HomeController.hc.logger.info( "As Event: to client " + Integer.toString (no)+ message);
		MyWebSocketActor item = clients.get (no);
		item.out.tell ( message, item.self() );	
	}
	
	@Inject
    public MyWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() 
	{	
		HomeController.hc.logger.info("WebSocket opened");
		clients.add (this);
		
		receiver = receiveBuilder().match(String.class, message -> out.tell(HomeController.hc.processMessage (message), self())  ).build();
	    //return receiveBuilder().match(String.class, message -> out.tell("Received Your messge "+ message, self())  ).build();
		
		return receiver;
    }
	
	public void postStop() throws Exception 
	{
        clients.remove (this);
	   	HomeController.hc.logger.info("WebSocket closed");
    }
}

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
 
public class HomeController extends Controller 
{
	public  static final Logger.ALogger logger = Logger.of(HomeController.class);
	private static  SnakeBoard  board = new SnakeBoard ();
	private final ActorSystem actorSystem;
    private final Materializer materializer;
	public static HomeController hc;
	public static int connections=0;

    @Inject
    public HomeController(ActorSystem actorSystem, Materializer materializer) 
	{
        this.actorSystem = actorSystem;
        this.materializer = materializer;
		//this.materializer = ActorMaterializer.create(ActorMaterializerSettings.create(actorSystem).withInputBuffer(64, 64), actorSystem);
		this.hc = this;
    }

    public WebSocket ws() 
	{
        //return WebSocket.Json.accept(request -> ActorFlow.actorRef( MyWebSocketActor::props,actorSystem, materializer));
			
		return WebSocket.Text.accept(request -> ActorFlow.actorRef( (out) -> MyWebSocketActor.props(out), 1024,  OverflowStrategy.fail(), actorSystem, materializer));
    }
	
	
	public String processMessage (String message)
	{
		 logger.trace ("Incoming--> "+message);
		 JsonNode request;
		 String response = "unknown API";
		 
		 try
	     {
		  ObjectMapper mapper = new ObjectMapper();
		  request = mapper.readTree(message);
		 }
		 catch (java.io.IOException io)
		 {
			 logger.error ("client request not parseable for Json" + message);
			 return("exception");
		 }
	 
		 JsonNode api =  request.findValue("api");
		 String name = api.findValue("name").asText();
		 
		 if ( name.equals ("processEvents") )
			response = ( processEvents (request) );
		 else if (name.equals ("joinGame") )
			 response = ( joinGame (request) );
		 else if (name.equals ("setSnake") )
			 response = ( setSnake (request) );
		 else if (name.equals ("setApplePosition") )
			 response =  ( setApplePosition (request) );
		 else if (name.equals ("exitGame") )
			 response =  ( exitGame (request) );
		 else
			logger.error ("unknow api name found in json request :"+name);
		
		logger.trace ("Outgoing --> "+response);
		return (response);
	}

	
    public Result index() {
        return ok(views.html.index.render());
    }
	
	 public Result arrowLeft() {
        return ok("Left Arrow Pressed");
	 }
		
	 public Result gameover() 
	 {
		logger.info ("API : gameover");
		  JsonNode jsonNode = request().body().asJson();
		  
		  
		  //ObjectNode result = Json.newObject();
		  //result.put ("font" ,"30px Arial");
		  	  
			if (jsonNode != null)
			{
				((ObjectNode)jsonNode).put("font" ,"30px Arial");
				((ObjectNode)jsonNode).put("fillStyle" , "white");
				((ObjectNode)jsonNode).put("textAlign" , "center");
				return ok ( jsonNode);
			}
			else
				return badRequest("Expecting Json data");		
		 	
     }
	 
	 public Result retry() 
	 {
		  logger.info ("API : retry");
		  ObjectNode result = Json.newObject();
		  
				  	  
			if (result != null)
			{
				result.put("x" ,160);
				result.put("y" , 160);
				
				ArrayNode arrayNode = new ObjectMapper().createArrayNode();
				//arrayNode.add(arrayNodeObject1);
				//arrayNode.add(arrayNodeObject2);
				//arrayNode.add(arrayNodeObject3);
				result.put("cells" , arrayNode);
				result.put("maxCells" ,4);
				result.put("dx" , 16);
				result.put("dy" , 0);
				
				return ok ( result);
			}
			else
				return badRequest("Expecting Json data");		
		 	
     }
	 
	   
	 public Result setApplePosition ()
	 {
		 logger.info ("API : setApplePosition");
		 board.apple.x = (int)( Math.floor (  
		                                Math.random() * (board.range.max - board.range.min) 
									  ) + board.range.min  
						  ) * board.grid ;
		 board.apple.y = (int) ( Math.floor ( 
		                                Math.random() * (board.range.max - board.range.min) 
									  ) + board.range.min  
						  ) * board.grid ;
		 
		 ObjectNode result = Json.newObject();
		 
		 result.put("x" , board.apple.x);
		 result.put("y" , board.apple.y);
		 
		 return ok ( result);
	 }
	 
	 public Result processEvents ()
	 {
		 /*
		 JsonNode Request = request().body().asJson();
		 
		 JsonNode key = Request.findValue("key");
		 JsonNode snake = Request.findValue("sn");
		 
		 int no = 0;
		 
		
		  board.snake[no].x= snake.findValue("x").asInt();;
		  board.snake[no].y = snake.findValue("y").asInt();;
		  board.snake[no].dx = snake.findValue("dx").asInt();;
		  board.snake[no].dy = snake.findValue("dy").asInt();;
		  board.snake[no].maxCells =snake.findValue("maxCells").asInt();
		  
		  List<JsonNode> cellArray = snake.findValues("cells");
		  board.snake[no].cells  = new int [cellArray.size()];
		  
		  for (int i =0; i < cellArray.size(); i++)
				board.snake[no].cells[i] =  ((JsonNode)cellArray.get(i)).asInt();
		 	 
		 String kp = key.findValue("code").asText();
		 
	 
		 KeyPressed e = new KeyPressed( kp );
		 
 
		 // Left Arrow
		 if (e.code .equals  ("ArrowLeft") && board.snake[no].dx == 0) 
		 {
			 logger.info ("API : processEvents "+e.code);
			board.snake[no].dy = 0;
			board.snake[no].dx = -board.grid;
			return ok (board.snake[no].toObjectNode());
		 }
		  // up arrow key
		  else if (e.code .equals  ("ArrowUp") && board.snake[no].dy == 0) 
		  {
			  logger.info ("API : processEvents "+e.code);
			board.snake[no].dy = -board.grid;
			board.snake[no].dx = 0;
			return ok (board.snake[no].toObjectNode());
		  }
		  // right arrow key
		  else if (e.code .equals  ("ArrowRight") && board.snake[no].dx == 0) 
		  {
			  logger.info ("API : processEvents "+e.code);
			board.snake[no].dy = 0;
			board.snake[no].dx = board.grid; 
			return ok (board.snake[no].toObjectNode());
		  }
		  // down arrow key
		  else if (e.code .equals ("ArrowDown") && board.snake[no].dy == 0) 
		  {
			  logger.info ("API : processEvents "+e.code);
			board.snake[no].dy = board.grid;
			board.snake[no].dx = 0;
			return ok (board.snake[no].toObjectNode());
		  }
		  else if(e.code.equals ("Enter") && !board.running)
		  {
			    logger.info ("API : processEvents "+e.code);
				board.snakes[no] = new Snake ();
				board.snake[no].reset = true;
				logger.info ("retry chosen");
				return ok (board.snake[no].toObjectNode());
		  }
		  */
		  return ok ();
	 }
	 
	  
	public String setApplePosition (JsonNode request)
	 {
		 ObjectNode Aapi = createApi ("setApplePosition");  
		 ObjectNode Eapi = createApi ("event");  
		 
		 logger.info ("API : setApplePosition");
		 
		 board.apple.x = (int)( Math.floor (  
		                                Math.random() * (board.range.max - board.range.min) 
									  ) + board.range.min  
						  ) * board.grid ;
		 board.apple.y = (int) ( Math.floor ( 
		                                Math.random() * (board.range.max - board.range.min) 
									  ) + board.range.min  
						  ) * board.grid ;
		 
		 ObjectNode result = Json.newObject();
		 
		 result.put("x" , board.apple.x);
		 result.put("y" , board.apple.y);
		 
		 ObjectNode event = createEvent ( "appleMoved", 
		                        new String [] {"apple"}, 
								new String [] {"ObjectNode"}, 
								new Object [] { result } 
							 );
		 ObjectNode wrapper = Json.newObject();
		 
		 wrapper.setAll (Eapi);;
		 wrapper.setAll (event);;
		 MyWebSocketActor.sendAll (wrapper.toString());
		 
		 wrapper.removeAll();
		 wrapper.setAll (Aapi);;
		 wrapper.setAll (event);;
		 
		 return (wrapper.toString());

	 }
	  
	 public String processEvents (JsonNode request)
	 {
		 ObjectNode Aapi = createApi ("processEvents"); 
		 ObjectNode Eapi = createApi ("event"); 
		 ObjectNode wrapper = Json.newObject();
		 
	     String response;
		 
		 //logger.info ("API : processEvents ");
		 		 
		 JsonNode userno =  request.findValue("no");		 
		 JsonNode key =  request.findValue("key");
		 JsonNode snake = request.findValue("sn");
		 
		 int no = userno.findValue("no").asInt();
		 String kp = key.findValue("code").asText();
		 
		 KeyPressed e = new KeyPressed( kp );
		  
		  if ( no == -1) // Snake had bitten itself and new game initiated.
		  {
			if(e.code.equals ("Enter") )
			{
			    logger.info ("API : processEvents "+e.code);
				logger.info ("retry chosen");
				
				 ObjectNode event = createEvent ("retry", 
		                        new String [] {"no"}, 
								new String [] {"int"}, 
								new Object [] {no} 
							 );
				wrapper.setAll (Eapi);
				wrapper.setAll (event);
				return (wrapper.toString());
			}
			else
			{
				return (Aapi.toString());	  
			}
		  }
		  
		  Snake sn = board.snakes.get(no);
		 
		  sn.x= snake.findValue("x").asInt();;
		  sn.y = snake.findValue("y").asInt();;
		  sn.dx = snake.findValue("dx").asInt();;
		  sn.dy = snake.findValue("dy").asInt();;
		  sn.maxCells =snake.findValue("maxCells").asInt();
		  
		 JsonNode cellArray = snake.findValue("cells");
		 if  (cellArray.isArray ())
		 {
			 sn.cells  = new CoOrd [cellArray.size()];
			 Iterator<JsonNode> it = cellArray.iterator();
			 int i = 0;
             while (it.hasNext()) 
			 {
				 JsonNode n = it.next();
				 int x = n.findValue("x"). asInt();
				 int y = n.findValue("y"). asInt();
				 sn.cells [i++] = new CoOrd (x,y);
			 }	 
         }
		  		

 		 // Left Arrow
		 if (e.code .equals  ("ArrowLeft") && sn.dx == 0) 
		 { 
			logger.info ("API : processEvents "+e.code);
			sn.dy = 0;
			sn.dx = -board.grid;
		 }
		  // up arrow key
		  else if (e.code .equals  ("ArrowUp") && sn.dy == 0) 
		  {
		    logger.info ("API : processEvents "+e.code);
			sn.dy = -board.grid;
			sn.dx = 0;
		  }
		  // right arrow key
		  else if (e.code .equals  ("ArrowRight") && sn.dx == 0) 
		  {
			  logger.info ("API : processEvents "+e.code);
			sn.dy = 0;
			sn.dx = board.grid; 
		  }
		  // down arrow key
		  else if (e.code .equals ("ArrowDown") && sn.dy == 0) 
		  {
			logger.info ("API : processEvents "+e.code);
			sn.dy = board.grid;
			sn.dx = 0;
		  }
		  else
		  {
			  return (Aapi.toString());	  
		  }
		  board.snakes.set(no,sn);
		  
		  ObjectNode event = createEvent ( "keyPressed", 
		                        new String [] {"no","sn"}, 
								new String [] {"int","ObjectNode"}, 
								new Object [] {no, sn.toObjectNode()} 
							 );
							 		 
		 wrapper.setAll (Eapi);;
		 wrapper.setAll (event);;
		 
		 MyWebSocketActor.sendAll (wrapper.toString());
		 
		 wrapper.removeAll();
		 wrapper.setAll (Aapi);;
		 wrapper.setAll (event);;
		 
		 logger.info ("API : processEvents Key "+ kp + "API " + wrapper.toString());
		 return (wrapper.toString());
		 
	 } 

	public String setSnake (JsonNode request)
	{
		 ObjectNode Aapi =  createApi ("setSnake");  
		 ObjectNode Eapi =  createApi ("event");  
		    
	 		 
		 JsonNode userno =  request.findValue("no");		 
		 JsonNode snake =   request.findValue("sn");
		 	 
		 int no = userno.findValue("no").asInt();
		  
		  Snake sn = board.snakes.get(no);
		  
		  if (sn == null)
				logger.error ("API : setSnake " +"null snake" );
		
		  sn.x= snake.findValue("x").asInt();;
		  sn.y = snake.findValue("y").asInt();;
		  sn.dx = snake.findValue("dx").asInt();;
		  sn.dy = snake.findValue("dy").asInt();;
		  sn.maxCells =snake.findValue("maxCells").asInt();
		  sn.reset =snake.findValue("reset").asBoolean();
		  
	     JsonNode cellArray = snake.findValue("cells");
		 if  (cellArray.isArray ())
		 {
			 sn.cells  = new CoOrd [cellArray.size()];
			 Iterator<JsonNode> it = cellArray.iterator();
			 int i = 0;
             while (it.hasNext()) 
			 {
				 JsonNode n = it.next();
				 int x = n.findValue("x"). asInt();
				 int y = n.findValue("y"). asInt();
				 sn.cells [i++] = new CoOrd (x,y);
			 }	 
         }	
		 //logger.info ("API : setSnakeIn " + snake.toString() + Integer.toString (cellArray.size()) );
		 
		 board.snakes.set( no,sn);
		 
		 ObjectNode event = createEvent ( "snakeNewPositioned", 
		                        new String [] {"no","sn"}, 
								new String [] {"int","ObjectNode"}, 
								new Object [] {no, sn.toObjectNode()} 
							 );
		 //logger.info ("API : setSnakeOut " + sn.toString() + Integer.toString (cellArray.size()) );
		 ObjectNode wrapper = Json.newObject();				 
		 wrapper.setAll (Eapi);;
		 wrapper.setAll (event);;
		 MyWebSocketActor.sendAll (wrapper.toString());
		 
		 wrapper = Json.newObject();	
		 wrapper.setAll (Aapi);;
		 wrapper.setAll (event);;
		 
		 return (wrapper.toString());
		 
		 
	}
	 	 
	 public  synchronized  String joinGame (JsonNode request)
	 {
		 ObjectNode Aapi  =  createApi ("joinGame"); 
		 ObjectNode Eapi =   createApi ("event"); 
		 ObjectNode event;
		 ObjectNode wrapper = Json.newObject();
		 
		 String user =  request.findValue("userid").asText();
		 
		 int no = board.addSnakeFor(user);
		 
		for ( int i = 0; i < board.snakes.size(); i++)  //send existing Snakes To All User.
		 {			 
				 Snake sn = board.snakes.get(i);
				 
				 if (sn != null)
				 {
					 wrapper.removeAll();
					 event = createEvent ( "availableSnakes", 
													new String [] {"no","sn"}, 
													new String [] {"int","ObjectNode"}, 
													new Object [] {i, sn.toObjectNode()} 
												 );
						 
					 wrapper.setAll (Eapi);;
					 wrapper.setAll (event);;
					 MyWebSocketActor.sendAll (wrapper.toString());
				 }	
		 }
  		 
		 event = createEvent ( "addSnake", new String [] {"no"}, new String [] {"int"}, new Object [] {no} );
		 
		 wrapper.setAll (Eapi);;
		 wrapper.setAll (event);;
		 MyWebSocketActor.sendAll (wrapper.toString());
		 
		 wrapper.removeAll();
		 wrapper.setAll (Aapi);;
		 wrapper.setAll (event);;
		 		
		logger.debug ("API : joinGame " + wrapper.toString() );

		 return (wrapper.toString());
		 
	 } 
	 
	 public  synchronized String exitGame (JsonNode request)
	 {
		 ObjectNode Aapi  =  createApi ("exitGame"); 
		 ObjectNode Eapi =  createApi ("event"); 
		 ObjectNode wrapper = Json.newObject(); 
		 
		 String user =  request.findValue("userid").asText();
		 
		 JsonNode userno =  request.findValue("no");		 		 
		 
		 int no = userno.findValue("no").asInt();
		 
		 board.removeSnakeFor(no);
		 
	 	 ObjectNode event = createEvent ( "removeSnake", new String [] {"no"}, new String [] {"int"}, new Object [] {no} );
		 wrapper.setAll (Eapi);;
		 wrapper.setAll (event);;
		 MyWebSocketActor.sendAll (wrapper.toString());
		 
		 wrapper.removeAll();
		 wrapper.setAll (Aapi);;
		 wrapper.setAll (event);;
		 
		 logger.debug ("API : exitGame " + wrapper.toString() );

		 return (wrapper.toString());
	 } 
	 
	 public ObjectNode createEvent ( String eventname, String [] fieldNames, String [] dataTypes, Object [] values)
	 {
		 ObjectNode result = Json.newObject();
		 result.with ("event").put ( "name", eventname );
		 for ( int i = 0; i < fieldNames.length; i++)
		 {
			 if ( dataTypes [i].equals ("int") )
					result.with ("event").put ( fieldNames[i], Integer.valueOf ( (Integer) values[i]) );
			 else if ( dataTypes [i].equals ("String") )
				    result.with ("event").put ( fieldNames[i], (String) values[i]);
			 else if ( dataTypes [i].equals ("ObjectNode") )
				    result.with ("event").put ( fieldNames[i], (ObjectNode)values[i]);
		 }
		 return result;
	 }
	 
	 public ObjectNode createApi ( String name )
	 {
		 ObjectNode result = Json.newObject(); 
		 result.with ("api").put ( "name", name );
		 return result;
	 }
	 
	  public ObjectNode createNamedJson ( String name, String json )
	 {
		 ObjectNode wrapper = Json.newObject();
		 
		 wrapper.put (name, json);

		 return wrapper;
	 }
}

