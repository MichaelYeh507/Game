
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

public class Spikes {
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle cBox = new Rectangle();
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	
	public Spikes(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;

		cBox.setBounds(x, y, width, height);
		
		if (needImage) {
			loadImage ("laser.png");
		}
	}

	

	public void update(){
		cBox.setBounds(x, y, width, height);
	}

	
	//draw spike/trap
	public void draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, width, height);
		}
	}

	

	public Rectangle getCBox(){
		return cBox;
	}

	

	public int getX(){
		return x;
	}

	

	public int getY(){
		return y;
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
