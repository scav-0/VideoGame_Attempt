package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.SuperObject;
import tiles.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 16; //16x16 tile - default size of players npcs etiles, etc
	//looks small as screen is huge nowadays, - need to scale 16x16 to look good
	public final int scale=3; //will make it look 16x3 
	
	public final int tileSize = originalTileSize*scale; //48x48 tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; //screen is 16 x12 tiles big
	public final int screenWidth =tileSize*maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize*maxScreenRow; //576 pixels
	
	//World settings;
	public final int maxWorldCol=50;
	public final int maxWorldRow =50;
	public final int worldWidth =tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxScreenRow;
	
	//FPS
	int FPS=60;
	
	TileManager tileM = new TileManager(this);
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //To help with time - dk what it is
	//threads allow things to run at once i think??
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[]= new SuperObject[10];//10 slots for objects - not 10 objects in game - just can diplay 10 at the same time

	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight ));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //all the drawing will be done in an offscreen painting buffer
		this.addKeyListener(keyH);
		this.setFocusable(true);//game panel is focused to receive keyboard input
	}
	
	public void setUpGame() {
		
		aSetter.setObject();
		
	}
	
	
	public void startGameThread() {
		
		gameThread= new Thread(this);
		gameThread.start();
	}
	//Sleep method
//	@Override
//	public void run() {
//		//need to create game loop
//		
//		//sleep method
//		
//		double drawInterval=1000000000/FPS; //second/FPS 0.166666 seconds
//		double nextDrawTime=System.nanoTime() +drawInterval;
//		
//		
//		while(gameThread != null) {
//			//System.out.println("Game Loop is running"); test to see if game loop is going
//			long currentTime=System.nanoTime();
//			//long currentTime2=System.currentTimeMillis(); - less accurate
//			//1. update information such as character positions
//			update();
//			//2. Draw: draw the screen with the updated information
//			repaint(); //how you call paint component
//			//if fps is 30 - loop runs 30 times a second
//			// need to restrict and slow it down to the fps 
//			//when running before this - the update happened faster than the kyboard input and the player dissapeared
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime=remainingTime/1000000; //change to milli seconds
//				if(remainingTime<0) {
//					remainingTime=0;
//				}
//				Thread.sleep((long)remainingTime);//paused until sleep is over
//				
//				nextDrawTime+=drawInterval;
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
//	}
	//delta method
	public void run() {
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
//		long timer=0;
//		int drawCount =0;
		while(gameThread !=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
//			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			if(delta>=1) {
				update();
				repaint();
				delta--;
//				drawCount++;
			}
//			if(timer>=1000000000) {
//				System.out.println("FPS: "+drawCount);
//				drawCount=0;
//				timer=0;
//			}
			
			
		}
		
	}
	
	public void update() {
		player.update();
		
		
	}
	
	public void paintComponent(Graphics g) {
		//part of JPanel
		super.paintComponent(g); //super means parent class of class - jpanel - as this is a subpanel
		Graphics2D g2 = (Graphics2D)g;
		//Graphics 2d has functions for later
		
		
		
		tileM.draw(g2);//order matters as player is above everything else
		//OBJECTS
		for(int i =0;i<obj.length;i++) {
			if(obj[i]!=null) {
				obj[i].draw(g2, this);
			}
		}
		
		//PLAYER
		player.draw(g2);
		g2.dispose(); //closing to save resources
		
		
	}
}

