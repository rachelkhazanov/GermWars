package germwars;
 
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class GermWars extends PApplet 
{
	public static Random random = new Random();
    PImage background;
    PImage paddleImage;
    PImage soapImage;
    PImage[] germImages = new PImage[5];
    
    
    public Paddle player;
    public Ball bubble;
    public Germ germ;
    public Germ germ2;
    public Germ germ3;
    public Germ germ4;
    public Germ germ5;
    
    public void setup() 
    {
        size(800,600);
        background = loadImage("background.png");
        paddleImage = loadImage("paddle.png");
        soapImage = loadImage("soapbubble.png");
        
        germImages[0] = loadImage("germ_blue.png");
        germImages[1] = loadImage("germ_green.png");
        germImages[2] = loadImage("germ_orange.png");
        germImages[3] = loadImage("germ_red.png");
        germImages[4] = loadImage("germ_yellow.png");
    	
    	player = new Paddle(paddleImage, width/2, height * 4/5);
    	bubble = new Ball(soapImage, 0, height / 2);
    	germ = new Germ(germImages[0], width/2, 30);
    	germ2 = new Germ(germImages[1],width/2,30);
    	germ3 = new Germ(germImages[2],width/2,30);
    	germ4 = new Germ(germImages[3],width/2,30);
    	germ5 = new Germ(germImages[4],width/2,30);
        germ.xSpeed = 4;
        germ.ySpeed = -2;
        germ2.xSpeed = 8;
        germ2.yspeed = 4;
        germ3.xSpeed = -9;
        germ3.ySpeed = -1;
        germ4.xSpeed = -2;
        germ4.ySpeed = 7;
        germ5.xSpeed = 1;
        germ5.ySpeed=-1;
    }
 
    public void draw() 
    {     
    	player.update();
    	bubble.update();
    	germ.update();
    	germ2.update();
    	germ3.update();
    	germ4.update();
    	germ5.update();
    	
        background(background);
        player.drawPaddle();
        bubble.drawBall();
        germ.drawGerm();
        germ2.drawGerm();
        germ3.drawGerm();
        germ4.drawGerm();
        germ5.drawGerm();
    }
    
    public void keyPressed() 
    {
    	 if(key == 'd') 
         {
             player.moveRight = true;
         } 
         if(key == 'a')
         {
             player.moveLeft = true;
         }
    }
    
    public void keyReleased() 
    {
        if(key == 'd') 
        {
            player.moveRight = false;
        } 
        if(key == 'a')
        {
            player.moveLeft = false;
        }
    }
    
    public class Paddle{
        PImage sprite;
        float xPos;
        float yPos;
        
        int speed = 10;
        public boolean moveRight = false;
        public boolean moveLeft = false;
    
    	public Paddle(PImage paddleSprite, float startX, float startY){
    		sprite = paddleSprite;
    		xPos = startX;
    		yPos = startY;
    	}
    		
    	public void drawPaddle(){
    	    image(sprite, xPos, yPos);
    	       }
        public void update()
        {
            if(this.moveRight)
            {
                this.xPos += this.speed;
            }
            if(this.moveLeft)
            {
                this.xPos -= this.speed;
            }
            checkBounds();
          
        }
        public void checkBounds()
        {
        	if(this.xPos < 0)
            {
                this.xPos = 0;
                moveLeft = false;
            }
        	if(this.xPos + this.sprite.width > width){
                this.xPos = width - this.sprite.width;
                moveRight = false;
            }
        }
     }
    
    public class Ball{
    	PImage sprite;
        float xPos;
        float yPos;
         
        int xSpeed = 8;
        int ySpeed = 8;
        
  
        
        public Ball(PImage ballSprite, float startX, float startY){
            sprite = ballSprite;
            xPos = startX;
            yPos = startY;
        }
        public void drawBall(){
            image(sprite, xPos, yPos);
        }
         
        public void update(){
            this.xPos += this.xSpeed;
            this.yPos += this.ySpeed;
            
            checkBounds();
            checkCollision();
        }
        public void checkBounds()
        {
        	 if(this.yPos < 0){
                 this.ySpeed = -this.ySpeed;
             }
             if(this.xPos < 0)
             {
                 this.xSpeed = -this.xSpeed;
                 this.xPos = 0;
             }
             if(this.xPos + this.sprite.width > width){
                 this.xSpeed = -this.xSpeed;
                 this.xPos = width - this.sprite.width;
             }
             if(this.yPos > height){
                 this.xPos = player.xPos;
                 this.yPos = player.yPos - this.sprite.height;
                  
                 this.ySpeed = -this.ySpeed;
             }
        }
        public void checkCollision(){
            if( this.xPos + this.sprite.width > player.xPos && this.xPos < player.xPos + player.sprite.width){
                if(this.yPos > player.yPos - player.sprite.height) {
                    this.ySpeed = -this.ySpeed;
                    this.yPos = player.yPos - this.sprite.height;
                }
 
            }
        }
    }
 public class Germ{
 
        PImage sprite;
        float xPos;
        float yPos;
 
        int xSpeed = 8;
        int ySpeed = 8;
 
        boolean alive = true;
 
        public Germ(PImage ballSprite, float startX, float startY){
            sprite = ballSprite;
            xPos = startX;
            yPos = startY;
        }
 
        public void drawGerm(){
            if(this.alive){
                image(sprite, xPos, yPos);
            }
 
        }
 
        public void update(){
            if(this.alive){
                this.xPos += this.xSpeed;
                this.yPos += this.ySpeed;
 
                checkBounds();
                checkCollision();
            }
        }
 
        public void checkBounds()
        {
            if(this.yPos < 0){
                this.ySpeed = -this.ySpeed;
                this.ySpeed += random.nextInt(5) - 2;
            }
            if(this.xPos < 0)
            {
                this.xSpeed = -this.xSpeed;
                this.ySpeed += random.nextInt(5) - 2;
                this.xPos = 0;
            }
            if(this.xPos + this.sprite.width > width){
                this.xSpeed = -this.xSpeed;
                this.ySpeed += random.nextInt(5) - 2;
                this.xPos = width - this.sprite.width;
            }
 
            if(this.yPos > height / 2){
                this.ySpeed = -this.ySpeed;
                this.ySpeed += random.nextInt(5) - 2;
            }
        }
 
        public void checkCollision(){
             
            if( this.xPos + this.sprite.width > bubble.xPos && this.xPos < bubble.xPos + bubble.sprite.width){
                if(this.yPos + this.sprite.height > bubble.yPos  && this.yPos < bubble.yPos + bubble.sprite.height) {
                    this.alive = false;
                }
            }
        }
 
    }
 
    public static void main(String _args[]) {
        PApplet.main(new String[] { germwars.GermWars.class.getName() });
    }
 
}