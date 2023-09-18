package World;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Game.Game;
import entities.Entity;



public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.tile.getSprite(0,0,16,16);
	public static BufferedImage TILE_WALL = Game.tile.getSprite(16,0,16,16);

	private BufferedImage sprite;
	private double x,y;
	private int width,height;
	public int mwidth,mheight;
    public int maskx,masky;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void tick(){
		
	}
	public static boolean isColidding(Entity e1,Tile atual){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(atual.getX() + atual.maskx,atual.getY()+atual.masky,atual.mwidth,atual.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	public void render(Graphics g){
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y,World.TILE_SIZE,World.TILE_SIZE, null);
	}

}

