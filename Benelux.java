import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;


public class Benelux{

	public static void putString(int r, int c,Terminal t, String str){
		t.moveCursor(r,c);
		for(int i = 0; i < str.length();i++){
			t.putCharacter(str.charAt(i));
		}
	}
  public static void drawRoom(Room r, Terminal t){
    int x = r.getTLCX();
    int y = r.getTLCY();
    t.moveCursor(x,y);
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
    for(int i=1;i<r.getHeight();i++){
      t.moveCursor(x,y+i);
      t.putCharacter('#');
      t.moveCursor(x+r.getWidth()-1,y+i);
      t.putCharacter('#');
    }
    t.moveCursor(x,y+r.getHeight());
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
  }
	public static void drawFloor(Floor fr, Screen scre){
		String row = "";
		for (int i = 0; i<fr.getGrid().length-1;i++){
			for (int o =0; o<fr.getGrid()[i].length-1;o++){
				row += fr.getGrid()[i][o];
				if (o == fr.getGrid()[i].length -2){
					row += "\n";
				}
			}
		}
		scre.putString(0, fr.getGrid().length, row, Terminal.Color.WHITE, Terminal.Color.BLACK);
	}
	/*
	public static void drawMonster(Monster m){
		int x = m.getX();
		int y = m.getY();
		s.putString(x,y,terminal," ! ");
	}

	public static void eraser(int x, int y){
		s.putString(x,y,terminal, " . ");
	}
	public static void drawPlayer(Player p){
		int x = p.getX();
		int y = p.getY();
		s.putString(x, y, terminal," $ ");
	}

	public static void drawItem(Item i){
		int x = i.getX();
		int y = i.getY();
		s.putString(x,y,terminal, " ? ");
	}
*/
	public static void main(String[] args) throws FileNotFoundException{

		ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 35, 20, r);
    f.addAllRooms();
    f.addEntrance();
    f.addExit();
		f.addAllPaths();
		ArrayList<Monster> mn =new ArrayList<Monster>();
		int numbers = r.nextInt(10);

		Player playerM = new Player();
		playerM.setX(f.getEntranceX());
		playerM.setY(f.getEntranceY());


		Terminal terminal = TerminalFacade.createTextTerminal();
		Screen s = new Screen(terminal);
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

		long tStart = System.currentTimeMillis();
		long lastSecond = 0;

		int depth=0;
	  String mode = "Game Mode";
	  long timer = 0;


	    //while(millis/1000 != lastSecond + 1){ //check for one second?
			while(running){
				long lastTime =  System.currentTimeMillis();
		    long currentTime = lastTime;
				int currentX = playerM.getX();
				int currentY = playerM.getY();
				long tEnd = System.currentTimeMillis();
				long millis = tEnd - tStart;
	      Key key = terminal.readInput();
				/*
				while (mode.equals("Start Menu") && running == true) {
		      putString(1,3,terminal, "Start Menu \n Press the Corresponding Number \n -------- \n 1.Start Game \n 2. Exit Game");
		        if (key != null){
		          if (key.getCharacter() == '1') {
		            mode = "Game Mode";
								terminal.clearScreen();
		          }
		          if (key.getCharacter() == '2') {
		            terminal.exitPrivateMode();
		            running = false;
		          }
							if (key.getKind() == Key.Kind.Escape) {
								terminal.exitPrivateMode();
								running = false;
							}
		        }
		      }
				*/
	      if (key != null){
	        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

	        //pause mode
	        if(mode.equals("Pause Menu")){
	          if (key.getKind() == Key.Kind.Escape) {
	            terminal.exitPrivateMode();
	            running = false;
	          }
	          if (key.getCharacter() == '1') {
	            mode = "Inventory Mode";
							terminal.clearScreen();
	          }
	          if (key.getCharacter() == 'p') {
	            mode = "Game Mode";
							terminal.clearScreen();
	          }
	        }

	        if (mode.equals("Inventory Mode")) {
	          if (key.getCharacter() == 'p') {
	            mode = "Game Mode";
							terminal.clearScreen();
	          }
	          if (key.getCharacter() == '1') {
	            playerM.getInventory().get(0);
	          }
						if (key.getCharacter() == '2') {
	            playerM.getInventory().get(1);
	          }
						if (key.getCharacter() == '3') {
	            playerM.getInventory().get(2);
	          }
						if (key.getCharacter() == '4') {
	            playerM.getInventory().get(3);
	          }
						if (key.getCharacter() == '5') {
	            playerM.getInventory().get(4);
	          }
	        }
	        if (mode.equals("Game Mode")) {
	          if (key.getKind() == Key.Kind.Escape) {
	            terminal.exitPrivateMode();
	            running = false;
	          }
	          if (key.getCharacter() == 'p'){
	            mode = "Pause Menu";
							terminal.clearScreen();
	          }
	          if (key.getKind() == Key.Kind.ArrowDown) {
	            //eraser(currentX, currentY);
	            playerM.changeDirection("South");
	            playerM.moveForward(f);

	          }
	          else if (key.getKind() == Key.Kind.ArrowLeft) {
							//eraser(currentX,currentY);
	            playerM.changeDirection("West");
	            playerM.moveForward(f);

	          }
	          else if (key.getKind() == Key.Kind.ArrowUp) {
	            //eraser(currentX,currentY);
	            playerM.changeDirection("North");
	            playerM.moveForward(f);
	          }
	          else if (key.getKind() == Key.Kind.ArrowRight) {
	            //eraser(currentX,currentY);
	            playerM.changeDirection("East");
	            playerM.moveForward(f);

	          }
	        }
	      }

	      if(mode.equals("Game Mode")){
	        lastTime = currentTime;
	        currentTime = System.currentTimeMillis();
	        timer += (currentTime -lastTime);//add the amount of time since the last frame.
	        //DO GAME STUFF HERE
					drawFloor(f, s);
					s.refresh();
	        //putString(1,3,terminal, "Game here...",Terminal.Color.WHITE,Terminal.Color.RED);
	        //putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);

	      }else if (mode.equals("Inventory Mode")) {
	        putString(1,5,terminal, "Press P to return");
	        putString(1,7,terminal, "1. ");
	      }else if (mode.equals("Pause Menu")) {
	        putString(1,5,terminal, "Press Escape to Close");
	        putString(1,6,terminal, "1. Inventory");
	        putString(1,7,terminal, "Press P to return to the Game");
	      }


			//DO EVEN WHEN NO KEY PRESSED:
			/*
			putString(1,2,terminal,"Milliseconds since start of program: "+millis);
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
			putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
			}
			*/

		}
	}
}
