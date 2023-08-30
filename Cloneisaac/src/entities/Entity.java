package entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Camera;
import Game.World;
import Game.Game;
public class Entity {

	protected  double x;
	protected double y;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	public int maskx,masky,mwidth,mheight;


	public Entity (int x,int y,int width,int height/*,BufferedImage sprite*/) {
	this.x = x ;	
	this.y = y;
	this.width = width;
	this.height =height;
	//this.sprite =sprite;
	this.maskx = 0;
	this.masky = 0;
	this.mwidth = width;
	this.mheight = height;
	}

	public void setMask(int maskx,int masky,int mwidth,int mheight){
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}

	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	public void setX(int newX) {
		this.x=newX;
	}
	public void setY(int newY) {
		this.x=newY;
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
		public void tick(){}
			
		public double calculateDistance(int x1,int y1,int x2,int y2) {
			return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
		}
		
		public static boolean isColidding(Entity e1,Entity e2){
			Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.mwidth,e1.mheight);
			Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY()+e2.masky,e2.mwidth,e2.mheight);
			
			return e1Mask.intersects(e2Mask);
		}
		public boolean isColiddingfora(Entity e1,int x,int y,ArrayList<Entity> e2){
			Rectangle e1Mask = new Rectangle(x,y,e1.mwidth,e1.mheight);
			for(int i=0;i<e2.size();i++) {
			Rectangle e2Mask = new Rectangle(e2.get(i).getX() + e2.get(i).maskx,e2.get(i).getY()+e2.get(i).masky,e2.get(i).mwidth,e2.get(i).mheight);
			return e1Mask.intersects(e2Mask);}
			return false;
			}
		
		public void render(Graphics g) {
		g.drawImage(sprite,this.getX()-Camera.x,this.getY()-Camera.y,null);	
			
		}
		
		
		
		
		
	}


