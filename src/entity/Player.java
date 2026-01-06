package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public static BufferedImage lastImage;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 5 * gp.scale;
		solidArea.y = 6 * gp.scale;
		//want to record default cause we will change the above ones later
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY= solidArea.y;
		
		solidArea.width = 9 * gp.scale;
		solidArea.height = 7 * gp.scale; // co ords on da chicken for rectangle

		setDefaultValue();
		getPlayerImage();
	}

	public void setDefaultValue() {
		int defaultX = 23;
		int defaultY = 23;
		worldX = gp.tileSize * defaultX;
		worldY = gp.tileSize * defaultY;
		speed = 4;
		directionY = "down";
		directionX="right";
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenUpRF.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenUpLF.png"));
			upStill = ImageIO.read(getClass().getResourceAsStream("/player/ChickenUpStill.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenDownRF.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenDownLF.png"));
			downStill = ImageIO.read(getClass().getResourceAsStream("/player/ChickenDownStill.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenLeftRF.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenLeftLF.png"));
			leftStill = ImageIO.read(getClass().getResourceAsStream("/player/ChickenLeftStill.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenRightRF.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/ChickenRightLF.png"));
			rightStill = ImageIO.read(getClass().getResourceAsStream("/player/ChickenRightStill.png"));
			lastImage=downStill;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		int oldWorldX=worldX;
		int oldWorldY=worldY;
		// (0,0 is top left)
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true
				|| keyH.leftPressed == true) {
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum != 3) {
					spriteNum++;
				} else if (spriteNum == 3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			spriteNum = 2;
		}
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true
				|| keyH.leftPressed == true) {
			if (keyH.upPressed == true) {
				directionY = "up";
				worldY -= speed;
				
			} else if (keyH.downPressed == true) {
				directionY = "down";
				worldY += speed;

			}
			if (keyH.leftPressed == true) {
				directionX = "left";
				worldX -= speed;

			} else if (keyH.rightPressed == true) {
				directionX = "right";
				worldX += speed;
			}
			
			if(!keyH.rightPressed&&!keyH.leftPressed) {
				directionX="not";
			}
			if(!keyH.upPressed&&!keyH.downPressed) {
				directionY="not";
			}
			// check tile collision
			collisionXOn = false;
			collisionYOn=false;
			gp.cChecker.checkTile(this);
			
			//check object colision
//			int objIndex = gp.cChecker.checkObject(this,true);
			// if collsion is true revert back 
			if (collisionYOn == true&&  (keyH.upPressed == true
					|| keyH.downPressed == true)) {
				worldY=oldWorldY;
			}
			if(collisionXOn==true &&( keyH.rightPressed == true
					|| keyH.leftPressed == true)) {
				worldX=oldWorldX;
			}
		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); //draws rectangle as player character x, y, size size

		BufferedImage image = downStill;
//		BufferedImage lastImage=null;
		if((keyH.rightPressed == true
				|| keyH.leftPressed == true)) {
			switch(directionX) {
			case "left": {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = leftStill;
					
				}
				if (spriteNum == 3) {
					image = left2;
				}
				lastImage=leftStill;
				break;
			}
			case "right": {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = rightStill;
				}
				if (spriteNum == 3) {
					image = right2;
				}
				lastImage=rightStill;
				break;
			}
			
			}
		
		}else if(keyH.upPressed == true
				|| keyH.downPressed == true){
			switch (directionY) {
			case "up": {
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = upStill;
				}
				if (spriteNum == 3) {
					image = up2;
				}
				lastImage=upStill;
				break;
			}
			case "down": {
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = downStill;
				}
				if (spriteNum == 3) {
					image = down2;
				}
				lastImage=downStill;
				break;
			}
			}
			
		}else {
			image=lastImage;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
