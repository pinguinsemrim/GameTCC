package entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
		public static boolean isColiddingfora(Entity e1,Entity e2){
			int obj1X = e1.getX() ,obj1Y = e1.getY(), obj1W= e1.getWidth(),obj1H = e1.getHeight();
			int obj2X = e2.getX() ,obj2Y = e2.getY(), obj2W= e2.getWidth(),obj2H = e2.getHeight();
					  if ((obj1X >= obj2X && obj1X <= obj2X + obj2W)
					    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) {
					   return true;
					  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W)
					    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) {
					   return true;
					  } else if ((obj1X >= obj2X && obj1X <= obj2X + obj2W)
					    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) {
					   return true;
					  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W)
					    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) {
					   return true;
					  } else {
					   return false;
					  }
					 
			
		}
		
		public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(),null);	
			
		}
		
		
		
		
		
	}


