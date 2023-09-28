package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.Game;
import World.Camera;
import World.World;


public class Enemy extends Entity {
	public boolean dam =false;
	public int vida =10;
	double speed = 0.7;
	public int ma=30,a=0,dir=0;
	public double kb =10,dama=1;
	public boolean ab=true;
	public Enemy(int x, int y, int width, int height,BufferedImage sprite) {
		super(x, y, width, height,sprite);
		// TODO Auto-generated constructor stub
	
	}
	public void tick(){
		if(vida<=0) {
			Game.entities.remove(this);
		}
		
		if(this.calculateDistance(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()) <300) {
		if((int)x < Game.player.getX() &&World.isFree(this.getX()+(int)(speed), this.getY())) {
			x+=speed;			
			dir=0;
		}else if((int)x > Game.player.getX() &&World.isFree(this.getX()-(int)(speed), this.getY())) {
			x-=speed;
			dir=1;
		}if((int)y < Game.player.getY() &&World.isFree(this.getX(), this.getY()+(int)(speed))) {
			y+=speed;
			dir=2;
		}else if((int)y > Game.player.getY() &&World.isFree(this.getX(), this.getY()-(int)(speed))) {
			y-=speed;
			dir=3;
		}}
		if(isColidding(this,Game.player) && ab) {
			Game.player.damege(dama);
			Game.player.x += Math.cos(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb;
			Game.player.y += Math.sin(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb;		
			ab=false;
			}
		if(ab==false) {
			a++;
			if(a==ma) {
				ab=true;
				a=0;
			}}	
	}
	public void damage(double dam2) {
		dam=true;
		vida-=dam2;
	}
	public void render(Graphics2D g) {
		if(dir==1) {
			g.drawImage(koala1, this.getX()-Camera.x, this.getY()-Camera.y, width, height, null);	
		}else if(dir==2) {
			g.drawImage(koala1, this.getX()-Camera.x, this.getY()-Camera.y, width, height, null);	
		}else if(dir==3) {
			g.drawImage(koala1, this.getX()-Camera.x, this.getY()-Camera.y, width, height, null);	
		}else {
			g.drawImage(koala1, this.getX()-Camera.x, this.getY()-Camera.y, width, height, null);	
		}
			
	}

}