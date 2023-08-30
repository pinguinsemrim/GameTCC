package entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.Camera;
import Game.Game;

public class Shoot extends Entity{
	public int dir=0;
	public int speed=2;
	int t=0,tm=100;
	public double dam=0;
	public Shoot(int x, int y, int width, int height,double dam) {
		super(x, y, width, height);
		this.dam=dam;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		if(t<tm) {
			t++;
		}else {
			Game.shoot.remove(this);
		}
		if(dir==0) {
			x-=speed;
		}if(dir==1) {
			x+=speed;
		}if(dir==2) {
			y+=speed;
		}if(dir==3) {
			y-=speed;
		}if(x>Game.WIDTH*Game.SCALE/2) {
			x=0;
		}else if(x<0) {
			x=Game.WIDTH*Game.SCALE/2;
		}if(y>Game.HEIGHT*Game.SCALE/2) {
			y=0;
		}else if(y<0) {
			y=Game.HEIGHT*Game.SCALE/2;
		}
		for(int i=0;i<Game.entities.size();i++) {
			if(Game.entities.get(i).getClass() == Enemy.class) {
				if(Entity.isColidding(this, Game.entities.get(i))) {
				Enemy e =(Enemy)Game.entities.get(i);
				e.damage(dam);
				Game.shoot.remove(this);
			}}
		}
	}
	public void render(Graphics g) {
		if(dam>=5) {
			g.setColor(Color.yellow);
		}else {
		g.setColor(Color.RED);
		}
	g.fillRect(this.getX(),this.getY(), this.getWidth(), this.getHeight());
	}}

