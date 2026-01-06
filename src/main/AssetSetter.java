package main;

import objects.OBJ_Boots;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_KEY;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		gp.obj[0]= new OBJ_KEY();
		gp.obj[0].worldX=15*gp.tileSize;
		gp.obj[0].worldY=5*gp.tileSize;
		
		gp.obj[1]= new OBJ_Chest();
		gp.obj[1].worldX=24*gp.tileSize;
		gp.obj[1].worldY=8*gp.tileSize;
		//16 - 24/25 - you need to minus one 
		gp.obj[2]= new OBJ_Door();
		gp.obj[2].worldX=23*gp.tileSize;
		gp.obj[2].worldY=15*gp.tileSize;
		
		gp.obj[3]= new OBJ_Door();
		gp.obj[3].worldX=24*gp.tileSize;
		gp.obj[3].worldY=15*gp.tileSize;
		
		gp.obj[4]= new OBJ_Boots();
		gp.obj[4].worldX=25*gp.tileSize;
		gp.obj[4].worldY=25*gp.tileSize;
	}
}
