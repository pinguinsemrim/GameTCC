package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
import World.Camera;

public class Shoot extends Entity{
	public int dir=0;
	public int speed=2;
	int t=0,tm=100;
	public double dam=10;
	double sca = 1;
	public Shoot(int x, int y, int width, int height,double dam) {
		super(x, y, width, height,slime);
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
		}
		for(int i=0;i<Game.enimies.size();i++) {
				if(Entity.isColidding(this, Game.enimies.get(i))) {
				Entity e =Game.enimies.get(i);
				e.damage(dam);
				Game.shoot.remove(this);
			}
		}
		
	}
	public void render(Graphics g) {
	height-=t;
	width-=t;
	g.drawImage(Entity.slime, this.getX(), this.getY(), width, height, null);
	}}

