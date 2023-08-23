package entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;


public class Enemy extends Entity {
	public boolean dam =false;
	public int vida =10;
	double speed = 0.3;
	public int ma=30,a=0,dir=0;
	public double kb =10,dama=3;
	public boolean ab=true;
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		if(vida<=0) {
			Game.entities.remove(this);
		}
		
		if(this.calculateDistance(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()) < 8000) {
		if((int)x < Game.player.getX()) {
			x+=speed;			
			dir=0;
		}else if((int)x > Game.player.getX()) {
			x-=speed;
			dir=1;
		}if((int)y < Game.player.getY()) {
			y+=speed;
			dir=2;
		}else if((int)y > Game.player.getY()) {
			y-=speed;
			dir=3;
		}			
		if(isColidding(this,Game.player) && ab) {
			Game.player.damege(dama);
			if(dir==0) {
				Game.player.x+=kb;
			}else if(dir==1) {
				Game.player.x-=kb;
			}else if(dir==2) {
				Game.player.y+=kb;
			}else {
				Game.player.y-=kb;
			}
			ab=false;
			}}
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
	public void render(Graphics g) {
		if(!dam) {
		g.setColor(Color.GREEN);
		}else {
		g.setColor(Color.WHITE);
		dam=false;
		}
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getWidth());	
			
	}

}
