
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Rectangle cBox = new Rectangle();
	
	public boolean left = false;
	public boolean right = false;
	private int xVelocity = 5;
	private int gravity = 1;
	private int yVelocity = 0;
	private int jumpPower = 20;
	private int yLimit = 500;
	private int minXLimit = 0;
	private int maxXLimit = 750;
	boolean canJump = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	

	public Player(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		cBox.setBounds(x, y, width, height);
		
		if (needImage) {
			loadImage ("kirby.png");
		}
	}

	

	public void jump(){
		if(canJump){
			yVelocity -= jumpPower;
			canJump = false;
		}
	}

	
	//player movement
	public void update(){
		
		if(left){
			if (x >= minXLimit) {
				x -= xVelocity;
			}
		}
		
		if(right){
			if (x <= maxXLimit) {
				x += xVelocity;
			}
		}
		
		yVelocity += gravity;
		y += yVelocity;

		if(y >= yLimit + 1){
			y = yLimit + 1;
			yVelocity = 0;
			canJump = true;
		}
		
		cBox.setBounds(x, y, width, height);
	}

	//draw player
	public void draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		g.drawString("arrows to move, space to jump", 600, 25);
		g.drawString("Get to the star to pass", 600, 50);
		g.drawString("Don't step on the lasers" , 600, 75);
		g.drawString("You can jump while falling" , 600, 100);

	}


	public Rectangle getCBox(){
		return cBox;
	}

	

	public void setYLimit(int l){
		yLimit = l;
	}

                  


	public int getY(){
		return y;
	}


	public int getWidth(){
		return width;
	}


	public int getHeight(){
		return height;
	}


	public int getYVelocity(){
		return yVelocity;
	}
	
	//image loader
	void loadImage(String imageFile) {
		if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
