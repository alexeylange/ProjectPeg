//GameProject 
//Professor Mood
//Alexey Lange
//3/19/23


//Recreate a game. In this game, there are 15 balls starting in a 4x4 grid 
/*
o o o o
o o o o
  o o o
o o o o
*/
/* Your goal is to have only 1 ball left 
   remove ball by "kicking" a ball over another ball
   Ex:
   This----kick the top left ball----
o o o o                         o o o                          
o o o o                         o o o
  o o o                       o o o o
o o o o                       o o o o
*/

/*Since the ball at 0,0 was kicked over the ball at 0,1, the ball at 0,1 
disappears and the ball at 0,0 is now at 0,2. You can only kick a ball when 
there are two balls right next to each other and the destination space for 
the kicked ball is empty. You can only kick a ball left, right, up, or down.
In our version of this game, you will be creating a 4x4 grid where each “ball”
has a possible button on each side. If you press the button to the side of the 
button, it will kick the ball from that position. i.e., the original starting position 
looks like this:
_
o o o o
o o o o
  o o| o
o o o o
*/




import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
//For the random color 
import java.util.Random;

     
   
public class GameProject extends Application
{
  
   //Create a boolean for wheter the ball is visible 
   boolean isVisible = true;
   //add accessors and mutators as necessary 
   private int circles = 0;
   Label title = new Label();

   public void start(Stage stage)
   {
      //Setting up the BorderPane
      BorderPane bp = new BorderPane();// Adding border pane
      
      //make the borderPane and the label centered within the pane 
      bp.setAlignment(title,Pos.CENTER);
      //position the label of 
      bp.setTop(title);

      //Setting up the GirdPane
      GridPane gp = new GridPane();// adding grid panes 
      
      //establish the gaps between the bps (both horizontal and vertical)
      gp.setHgap(10);
      gp.setVgap(10);
      //agained making sure everything is centered 
      gp.setAlignment(Pos.CENTER);
      
     //The for loop to create the boardgamepanes
      for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
         {
            //create a new gamePane from the GP class
            GamePane game = new GamePane();
            //the array for game setup 
            gameBoard[i][j] = game;
            //adds to the gridpane 
            gp.add(gameBoard[i][j],i,j);
        
            if( i == 0 & j == 2)
            {
            //these ball will be invisible when the game intially appears 
               gameBoard[i][j].setBallVis(false);
         
            }
         }
    }
        //allows the board to be movable 
        isMoveable(gameBoard);
        
       for(int i=0;i<4;i++)
       {
         for(int j=0;j<4;j++)
         {
            gameBoard[i][j].draw();
         }
       }

      //Centers the gridpane within the borderpane        
       bp.setCenter(gp);
       
      //Creates the scene       
      Scene scene = new Scene(bp, 600, 600); 
      stage.setScene(scene); 
      //Creating the Title of the scene 
      stage.setTitle("Ball Game"); 
      stage.show();
         
   }
      //4x4 grid 
      GamePane[][] gameBoard = new GamePane[4][4];
       
       //Create variables for changing titles 
       int count = 0;
       int moves = 0;
      
      
public class GamePane extends GridPane
    {
    //Create the variables for the buttons 
    private Button left = new Button();
    private Button right = new Button();
    private Button bottom = new Button();
    private Button top = new Button();
   
    //boolean varibles for the button visibility 
    boolean leftButtonVis = false;
    boolean rightButtonVis = false;
    boolean bottomButtonVis = false;
    boolean topButtonVis = false;
    boolean CircleVis = true;
   
    //Creat a new gridPane 
    
    GridPane gridPane = new GridPane();
    
    //Need a to create a 80x80 canvas 
    Canvas canvas = new Canvas(80,80);
    //adds the canvas to the gc 
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    public GamePane()
    {
      super();
     //making the buttons 20x80
     left.setPrefSize(20,80);
     top.setPrefSize(80,20);
     right.setPrefSize(20,80);
     bottom.setPrefSize(80,20);
     
     //add button to the button listener class and add an action to them 
     top.setOnAction(new ButtonListener());
     right.setOnAction(new ButtonListener());
     left.setOnAction(new ButtonListener());
     bottom.setOnAction(new ButtonListener());
     
     //positioning the buttons within the gridpane 
     gridPane.add(top,1,0);
     gridPane.add(left,0,1);
     gridPane.add(right,2,1);
     gridPane.add(bottom,1,2);
     gridPane.add(canvas,1,1);
     
     //Create random color for the buttons 
     Random rand = new Random();
     float r = rand.nextFloat();
     float g = rand.nextFloat();
     float b = rand.nextFloat();
     
     Color color = Color.rgb((int) (r * 255), (int) (g * 255), (int) (b * 255));
     //adding the random color to the circles 
     gc.setFill(color);
     //fillling in the circles in the canvas 
     gc.fillOval(0,0,80,80);
     
    //adding the Gp to canvas     
    getChildren().add(gridPane);
    
   
   }
   //For the button method 
     public Button buttonSelected(char name)
     {
         //if top = t then it rerturns top applies to all the buttons 
         if(name == 't')
         {
            return top;
         }
         if(name == 'l')
         {
            return left;
         }
         if(name == 'b')
         {
            return bottom;
         }
         if(name == 'r')
         {
            return right;
         }
            return right;
     }
     //draw class 
    public void draw()
    {
         if(CircleVis)//if the button is visble 
         {
            gc.fillOval(0,0,80,80);
         }
         else
         {
            gc.clearRect(0,0,80,80);//makes the button and circle clear 
         }
         
         
         //when the top button is selected it will become visible     
         if(topButtonVis)
         {
            top.setVisible(true);
         }
         else
         {
            top.setVisible(false);
         }
         
         //when the left button is selected it will become visible  
         if(leftButtonVis)
         {
            left.setVisible(true);
         }
         else
         {
            left.setVisible(false);

         }
         
         //when the right button is selected it will become visible  
         if(rightButtonVis)
         {  
            right.setVisible(true);
         }
         else
         {
            right.setVisible(false);
         }
         
         //when the right button is selected it will become visible  
         if(bottomButtonVis)
         {
            bottom.setVisible(true);  
         }
         else
         {
            bottom.setVisible(false); 
         }
            
         //When the ball count and moves = then you win 
         if(count == 1 & moves == 0)
         {
            title.setText("YOU WIN!!!!");
         }
         //when moves equal zero 
         else if(moves == 0)
         {
            title.setText("YOU LOST!!!");
          }
         else 
         //to keep track of the balls left and moves left 
            title.setText("Balls left: " +count+                "   Possible moves: "+moves); 
   }
 
 //The constructor for the balls and buttons visibility 
  public boolean setBallVis(boolean CircleVis)
  {
      this.CircleVis = CircleVis;
      return CircleVis;
  }
  //The start 
   public boolean setbottomButtonVis(boolean bottomButtonVis)
    {
      this.bottomButtonVis = bottomButtonVis;
      return bottomButtonVis;
    }    public boolean settopButtonVis(boolean topButtonVis)
    {
      this.topButtonVis = topButtonVis;
      return topButtonVis;
    }
     
     public boolean setrightButtonVis(boolean rightButtonVis)
    {
      this.rightButtonVis = rightButtonVis;
      return rightButtonVis;
    }
    public boolean setleftButtonVis(boolean leftButtonVis)
    {
      this.leftButtonVis = leftButtonVis;
      return leftButtonVis;
    }
    
   public boolean getBallVis()
   {
      return CircleVis;
   
   }
 //end 
 }
  
  public void isMoveable(GamePane[][] gameBoard)
  {
      count = 0;
      moves = 0;
      
     for(int i=0;i<4;i++)
     {
         for(int j=0;j<4;j++)
         {
         
         if( gameBoard[i][j].getBallVis() == true)
         {
         //increases the count 
            count++;
         }
         
    //Columns and check spaces move with the ball to the space below the ball 
    if(j-2 > -1)
    {
     if(gameBoard[i][j-1].getBallVis() == true && gameBoard[i][j-2].getBallVis() == false && gameBoard[i][j].getBallVis() == true)
     {
         gameBoard[i][j].setbottomButtonVis(true);
         //increases moves 
         moves++;
      }
      else
      {
         //keeps buttons invisible until the parameters are met 
         gameBoard[i][j].setbottomButtonVis(false);
      }
     
    }
    //bottom check 
       if(j+2 < 4)
       {
         if(gameBoard[i][j+1].getBallVis() == true && gameBoard[i][j+2].getBallVis() == false && gameBoard[i][j].getBallVis() == true)
         {
            gameBoard[i][j].settopButtonVis(true);
            //moves increases 
            moves++;
         }
      else
      {
         //buttons will stay invisible till parameters are met 
         gameBoard[i][j].settopButtonVis(false);
      }
     
    }
    //check whether there are still balls on the board  
    if(i-2 > -1)
    {
         if(gameBoard[i-1][j].getBallVis() == true && gameBoard[i-2][j].getBallVis() == false && gameBoard[i][j].getBallVis() == true)
         {
             gameBoard[i][j].setrightButtonVis(true);
             moves++;
          }
         else
         {
            //buttons will stay invisible till parameters are met 
            gameBoard[i][j].setrightButtonVis(false);
         }
     
    }
    // checks balls to the right
    if(i+2 < 4)
    {
         if(gameBoard[i+1][j].getBallVis() == true && gameBoard[i+2][j].getBallVis() == false && gameBoard[i][j].getBallVis() == true)
         {
            gameBoard[i][j].setleftButtonVis(true);
            // Counts moves
            moves++; 
         }
         else
         {
            // Keeps button invisible until the paramters are met 
            gameBoard[i][j].setleftButtonVis(false);
         }
       }  
    }
  }
}
  
  //Button class (adds action to the buttons) 
  public class ButtonListener implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
      for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
         {
            if(e.getSource()== gameBoard[i][j].buttonSelected('t'))
            {
            // Top button is clicked then sends a signal to isMovable to give it functionality for that specific button
               Click(i,j,'t'); 
            }
            if(e.getSource()== gameBoard[i][j].buttonSelected('b'))
            {
            // bottom button is clicked then sends a signal to isMovable to give it functionality for that specific button
               Click(i,j,'b');
            }
            if(e.getSource()== gameBoard[i][j].buttonSelected('l'))
            {
            // left button is clicked then sends a signal to isMovable to give it functionality for that specific button
               Click(i,j,'l');
            }
            if(e.getSource()== gameBoard[i][j].buttonSelected('r'))
            {
            // r button is clicked then sends a signal to isMovable to give it functionality for that specific button
                Click(i,j,'r');
             }
         }
      }
   }
}
  
  
  public void Click(int x, int y, char name)
  {
      // top button was clicked it checks the visibility of the ball below it and then sets the ball visible to true 2 spaces down
      if(name == 't')
      { 
         gameBoard[x][y].setBallVis(false);
         gameBoard[x][y+1].setBallVis(false);
         gameBoard[x][y+2].setBallVis(true);
      } 
      //sets ball 2 spaces to the right to visible and will also do the same but instead to the left button 
      if(name == 'l')
      { 
         gameBoard[x][y].setBallVis(false);
         gameBoard[x+1][y].setBallVis(false);
         gameBoard[x+2][y].setBallVis(true);
      }      //makes ball 2 spaces up to visible and will also do the same thing above but for the bottom button instead 
      if(name == 'b')
      {
         gameBoard[x][y].setBallVis(false);
         gameBoard[x][y-1].setBallVis(false);
         gameBoard[x][y-2].setBallVis(true);
      } 
      //places ball 2 spaces left to visible and again does the same thing above but for right 
     if(name == 'r')
     {
         gameBoard[x][y].setBallVis(false);
         gameBoard[x-1][y].setBallVis(false);
         gameBoard[x-2][y].setBallVis(true);
      } 
      
      //this both redraws the code as well as calling the movable method 
      isMoveable(gameBoard);
      for(int i=0;i<4;i++)
      {
         for(int j=0;j<4;j++)
         {
            gameBoard[i][j].draw();//redraw
         }

      }
  
    }
     
   //launches the code 
   public static void main(String[] args)
   {
      launch(args); 
    }
}
