
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class RadicalRacingCollision extends JFrame{
  
        
        // this is the constand that will hold the screen size
        final int WIDTH = 900, HEIGHT = 650;
        
        // THE FOLLOWING ARE ALL OF THE RECTANGLES THAT WILL BE DRAWN
        /*
        The following code (Creating the rectangles may seem complicated at
        first, but it only seems that way ecause it creates the pieces based on
        the WIDTH and HEIGHT. In your version, you could just hanard code values
      
        */
        
            // Player Speed
                    
        double p1Speed = 0.5, p2Speed = 0.5;
        
        // Int represent direction
        final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
        
        // These will keep track of the player's directions defaul(UP)
        int p1Direction = UP;
        int p2Direction = UP;
        
       
        
        // creates rectangles that will represent the left, right, top bottom
        // and center
        Rectangle left = new Rectangle(0,0, WIDTH/9,HEIGHT);
        Rectangle right = new Rectangle((WIDTH/9)*9,0,WIDTH/9, HEIGHT);
        Rectangle top = new Rectangle(0,0,WIDTH, HEIGHT/9);
        Rectangle bottom = new Rectangle(0,HEIGHT/9*9, WIDTH, HEIGHT/9);
        Rectangle center = new Rectangle ((int)((WIDTH/9)*2.5), (int)((HEIGHT/9)
                *2.5), (int) ((WIDTH/9)*5), (HEIGHT/9)*4);
        
        // These obsstacles will obstruct the path and make navigating harder
        Rectangle obstacle = new Rectangle((WIDTH/2), (int)((HEIGHT/9)*7), 
                WIDTH/10, HEIGHT/9);
        
        Rectangle obstacle2 = new Rectangle ((WIDTH/3), (int)((HEIGHT/9)*5), (WIDTH/10), (HEIGHT/4));
        
        Rectangle obstacle3 = new Rectangle (2*(WIDTH/3), (int)((HEIGHT/9)*5),WIDTH/10, HEIGHT/4);
        
        Rectangle obstacle4 = new Rectangle (WIDTH/3, HEIGHT/9, WIDTH/30, HEIGHT/9);
        
        Rectangle obstacle5 = new Rectangle (WIDTH/2, (int)((HEIGHT/9)*1.5), WIDTH/30, HEIGHT/4);
        
        // the following rectangle is the finish line for both players
        
        Rectangle finish = new Rectangle (WIDTH/9, (HEIGHT/2)-HEIGHT/9, (int)((WIDTH/9)* 1.5), HEIGHT/70);
        
        // The following rectangle is the start line for outer player
        Rectangle lineO = new Rectangle (WIDTH/9, (HEIGHT/2), (int)((WIDTH/9)*1.5), HEIGHT/140);
        
        // The following rectangle is the start line for the inner player
        Rectangle lineI = new Rectangle (((WIDTH/9)+(int)((WIDTH/9)*1.5)/2), (HEIGHT/2)+ (HEIGHT/10), (int)((WIDTH/9)*1.5)/2, HEIGHT/140);
        
        // Player
        Rectangle p1 = new Rectangle (WIDTH/9, HEIGHT/2, WIDTH/30, WIDTH/30);
        
        // Player 2
        Rectangle p2 = new Rectangle ((WIDTH/9)+ ((int)((WIDTH/9)*1.5)/2), (HEIGHT/2) + (HEIGHT/10), WIDTH/30, WIDTH/30);
          // The Constructor
          public RadicalRacingCollision()
    {
        // The Following code create a JFrame
        super("Radical Racing");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        Move1 m1 = new Move1();
        Move2 m2 = new Move2();
        m1.start();
        m2.start();
                
    }
        
    public void paint (Graphics g ){
        super.paint(g);
        
        //Draw he background for the racetrack
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // When we Draw the border will be green
        
        g.setColor(Color.GREEN);
        
        //using the rectangles, draw it
                
       g.fillRect(left.x, left.y, left.width, left.height);
       g.fillRect(right.x, right.y, right.width, right.height);
       g.fillRect(top.x, top.y, top.width, top.height);
       g.fillRect(bottom.x, bottom.y, bottom.width, bottom.height);
       g.fillRect(center.x, center.y, center.width, center.height);
       g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
       g.fillRect(obstacle2.x, obstacle2.y, obstacle2.width, obstacle2.height);
       g.fillRect(obstacle3.x, obstacle3.y, obstacle3.width, obstacle3.height);
       g.fillRect(obstacle4.x, obstacle4.y, obstacle4.width, obstacle4.height);
       g.fillRect(obstacle5.x, obstacle5.y, obstacle5.width, obstacle5.height);
       
       //set color starting line color to white
       g.setColor(Color.WHITE);
       //Create starting line white
       g.fillRect(lineO.x,lineO.y,lineO.width, lineO.height);
       g.fillRect(lineI.x, lineI.y,lineI.width,lineI.height);
       //set color finish line to yellow
       g.setColor(Color.YELLOW);
       //Draw finishing LIne
       g.fillRect(finish.x,finish.y,finish.width,finish.height);
       
       // Draw P1 
       g.setColor(Color.BLUE);
       g.fill3DRect(p1.x, p1.y, p1.width, p1.height,true);
       // Draw P2
       g.setColor (Color.RED);
       g.fill3DRect(p2.x,p2.y, p2.width, p2.height,true);
    }
    private class Move1 extends Thread implements KeyListener{
        public void run(){
            // Wake up KeyListener
           addKeyListener(this);
            
            while(true){
               // now, put the code in a try block.
               // program exit
               // if there is an error
            try{
                //first, refresh the screen
                repaint();
                // increase speed a bit
                if(p1.intersects(left)|| 
                        p1.intersects(right) 
                        || 
                        p1.intersects(top) 
                        ||
                        p1.intersects(bottom)
                        ||
                        p1.intersects(p2) 
                        ||
                        p1.intersects(obstacle) 
                        || 
                        p1.intersects(obstacle2)
                        || 
                        p1.intersects(obstacle3) 
                        || 
                        p1.intersects(obstacle4)
                        || 
                        p1.intersects(obstacle5)){
                    
                    
                    p1Speed =-4;
                }
                    // if the car hits the center slowed it down
                    if(p1.intersects(center))
                    {
                        p1Speed = -2.5;
                    }
                    // increase speed a bit
                    if(p1Speed <=5 )
                    
                        p1Speed+=.1;
                        
                    
                    
                    // These will move the player based on direction
                    if (p1Direction == UP){
                        p1.y-=(int)p1Speed;
                        
                    }
                    if(p1Direction == DOWN){
                        p1.y += (int)p1Speed;
                        
                        
                    }
                    if(p1Direction == RIGHT){
                        
                        p1.x+= (int)p1Speed;
                    }
                    if(p1Direction == LEFT){
                        p1.x-= (int)p1Speed;
                    }
                    // Delays Frame
                    Thread.sleep(75);
                    
                
            }
            catch (Exception e){break;}
            
            }
            
            
            
        }
        
        
    
    public void keyPressed(KeyEvent event){
        
        
    }
    public void keyReleased(KeyEvent event){
        
        
    }
    
    public void keyTyped(KeyEvent event){
    if(event.getKeyChar() == 'a'){
        
        p1Direction = LEFT;
    }
    if (event.getKeyChar() == 'd'){
        p1Direction = RIGHT;
    }    
    if (event.getKeyChar() == 's'){
       p1Direction = DOWN;
    }
    if(event.getKeyChar() == 'w'){
        p1Direction = UP;
        
    }
    
    }
    }
    private class Move2 extends Thread implements KeyListener{
        public void run(){
            addKeyListener(this);
            while(true){
                try{
                    repaint();
                    
                    if(p2.intersects(left)|| 
                        p2.intersects(right) 
                        || 
                        p2.intersects(top) 
                        ||
                        p2.intersects(bottom)
                        ||
                        p2.intersects(p1) 
                        ||
                        p2.intersects(obstacle) 
                        || 
                        p2.intersects(obstacle2)
                        || 
                        p2.intersects(obstacle3) 
                        || 
                        p2.intersects(obstacle4)
                        || 
                        p2.intersects(obstacle5)){
                        
                        p2Speed -= 4;
                        
                        
                    }
                    if(p2.intersects(center)){
                        p2Speed = -2.5;
                        
                    }
                    
                    if(p2Speed <= 5)
                        p2Speed +=.1;
                    if(p2Direction == UP){
                        p2.y -=(int)p2Speed;
                        
                    }
                    if(p2Direction == DOWN){
                        p2.y += (int)p2Speed;
                        
                    }
                    if(p2Direction == RIGHT){
                        p2.x += (int)p2Speed;
                    }
                    if(p2Direction == LEFT){
                        p2.x -=(int)p2Speed;
                    }
                    // Delays Refresh rate
                    Thread.sleep(75);
                }
                catch(Exception e){break;};
                
                
            }
         
        }
        public void keyPressed(KeyEvent event){
            
        }
        public void keyReleased(KeyEvent event){
            
            
        }
        public void keyTyped(KeyEvent event){
            
            if(event.getKeyChar() == 'j')
            {
                p2Direction = LEFT;
            }
            if(event.getKeyChar() == 'l'){
                p2Direction = RIGHT;
            }
            if(event.getKeyChar() == 'k'){
                p2Direction = DOWN;
            }
            if(event.getKeyChar() == 'i'){
                p2Direction = UP;
            }
        }
        
    }
    // this starts the program by calling constructor
    
    public static void main(String[]args)
    {
        new RadicalRacingCollision();
        
        
       
    }
     }        
      
  
    
    
   


