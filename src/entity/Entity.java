package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Super class for entitys
 * I added abstract
 */
public class Entity {
	//public int x, y;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2,upStill, down1, down2, downStill, left1, left2, leftStill,right1, right2,rightStill;
	public String directionX;
	public String directionY;
	
	public int spriteCounter=0;
	public int spriteNum =1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionXOn=false;
	public boolean collisionYOn=false;

}
