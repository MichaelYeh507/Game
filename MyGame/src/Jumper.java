import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Jumper extends JPanel implements ActionListener, KeyListener{
	//member variables
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	JFrame window;
	Timer timer;
	Player p1 = new Player(50, 50, 50, 50);
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<Spikes> spikes = new ArrayList<Spikes>();
	Font titleFont = new Font ("Arial", Font.PLAIN, 64);
	Font instructionFont = new Font("Arial", Font.PLAIN, 35);
	int numDeaths = 0;
	
	
	Star star = new Star(600, 100, 50, 50);
	int level = 1;
	
	public static void main(String[] args) {
		new Jumper().run();
	}

	

	public void run(){
		window = new JFrame("JUMPER!!");
		window.addKeyListener(this);
		window.add(this);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();
		

		timer = new Timer(1000 / 60, this);
			
		//create the platforms here
		
			//level 1
			platforms.add(new Platform(300, 450, 100, 25));
			platforms.add(new Platform(500, 150, 200, 25));
			platforms.add(new Platform(200, 250, 100, 25));
			platforms.add(new Platform(0, 550, 800, 25));

		
		
		timer.start();

	}

	

	public void paintComponent(Graphics g){
		p1.draw(g);
		star.draw(g);
		
		//level titles
		if (level == 1) {
			g.setFont(instructionFont);
			g.setColor(Color.PINK);
			g.drawString("LEVEL 1", 300,  100);
		} else if (level ==2) {
			g.setFont(instructionFont);
			g.setColor(Color.PINK);
			g.drawString("LEVEL 2", 300,  100);
		} else if (level == 3) {
			g.setFont(instructionFont);
			g.setColor(Color.PINK);
			g.drawString("LEVEL 3", 300,  125);
		} else if (level == 4) {
			g.setFont(instructionFont);
			g.setColor(Color.PINK);
			g.drawString("LEVEL 4", 300,  80);
		} else if (level == 5) {
			g.setFont(instructionFont);
			g.setColor(Color.PINK);
			g.drawString("LEVEL 5", 300,  80);
		} else if (level ==6) {
			g.setFont(titleFont);
			g.setColor(Color.PINK);
			g.drawString("GOOD GAME!", 200,  150);
			g.setFont(instructionFont);
			g.drawString("you died " + numDeaths + " times.", 250, 200);
		}
		
		for(Platform p : platforms){
			p.draw(g);
		}
		for(Spikes s : spikes){
			s.draw(g);
		}

		window.repaint();
	}

	

	public void actionPerformed(ActionEvent e){

		
		p1.update();
		star.update();
		for(Platform p : platforms){
			p.update();
		}
		for(Spikes s : spikes){
			s.update();
		}
		checkCollision();
		checkStarCollision();
		checkSpikeCollision();
		repaint();
	}


	private boolean checkCollision(){
		
		//platform collision
		for(Platform p: platforms){
			if(p1.getCBox().intersects(p.getCBox())){
				handleCollision(p);
				return true;
			}
		}
		
		p1.setYLimit(500);
		return false;
	}

	private boolean checkStarCollision() {
		
		//star collision
		if(p1.getCBox().intersects(star.getCBox())) {
			level ++;
			platforms.clear();
			spikes.clear();
			if (level == 2) {
				
				//level 2
				star = new Star(50, 500, 50, 50);  
				platforms.add(new Platform(600, 200, 50, 50));
				spikes.add(new Spikes(550, 550, 250, 25));
				platforms.add(new Platform(525, 400, 50, 50));
				spikes.add(new Spikes(475, 400, 50, 25));
				platforms.add(new Platform(200, 200, 300, 25));
				spikes.add(new Spikes(0, 200, 400, 25));
				platforms.add(new Platform(0, 550, 800, 25));

				
			} else if (level == 3) {
				
				//level 3
				star = new Star(650, 500, 50, 50);
				spikes.add(new Spikes(150, 525, 500, 25));
				platforms.add(new Platform(0, 400, 25,25));
				platforms.add(new Platform(100, 300, 25, 25));
				spikes.add(new Spikes(125, 275, 25, 25));
				platforms.add(new Platform(0, 150, 50, 25));
				platforms.add(new Platform(100, 50, 500, 25));
				platforms.add(new Platform(600, 400, 200, 25));
				platforms.add(new Platform(0, 550, 800, 25));

			
			} else if (level == 4) {
				
				//level 4
				star = new Star (25, 25, 50, 50);
				spikes.add(new Spikes(100, 450, 750, 25));
				platforms.add(new Platform(100, 425, 750, 25));
				spikes.add(new Spikes (300, 300, 50, 150));
				spikes.add(new Spikes(450, 300, 50, 150));
				platforms.add(new Platform(700, 300, 75, 25));
				platforms.add(new Platform(10, 100, 750, 25));
				platforms.add(new Platform(0, 550, 800, 25));

				
				
			} else if (level == 5) {
				
				//FINAL level
				star = new Star (25, 500, 50, 50);
				platforms.add(new Platform(0, 100, 650, 25));
				spikes.add(new Spikes(750, 250, 60, 25));
				platforms.add(new Platform(0, 550, 800, 25));
				spikes.add(new Spikes (500, 400, 225, 25));
				spikes.add(new Spikes(300, 408, 50, 143));
				
			} else if (level == 6) {
				
				//Good Game 
				star = new Star (0, 0, 0, 0);
				
				
			}
			return true;
		}
		return false;
	}
	
	private boolean checkSpikeCollision(){
		for(Spikes s: spikes){
			if(p1.getCBox().intersects(s.getCBox())){
				if (level == 2) {
					
					//level 2 spawn point
					
					p1 = new Player(600, 100, 50, 50);
				} else if (level ==3) {
					
					//level 3 spawn point
					p1 = new Player(50, 500, 50, 50);
					
				} else if (level == 4) {
					
					//level 4 spawn point
					p1 = new Player(650, 500, 50, 50);
					
				} else if (level == 5) {
					
					//level 5 spawn point
					p1 = new Player (25, 25, 50, 50);
					
				}
				numDeaths++;
				return true;
			}
		}
		
		return false;
	}
	

	private void handleCollision(Platform p){
		//platform holding up Kirby
		if(p1.getYVelocity() >= 0 && p1.getY() + p1.getHeight() < p.getY() + 25){
			p1.setYLimit(p.getY() - p1.getHeight());
		}else{
			p1.setYLimit(500);
		}
	}

	

	@Override

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}



	@Override

	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = true;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = true;
		}


		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			p1.jump();
		}

	

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			timer.stop();
			System.exit(0);
		}
	}

	@Override

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = false;
		}
	}
}