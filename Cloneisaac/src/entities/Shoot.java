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
		if(dam>=5) {
			g.setColor(Color.yellow);
		}else {
		g.setColor(Color.RED);
		}
	g.fillRect(this.getX()-Camera.x,this.getY()-Camera.y, this.getWidth(), this.getHeight());
	}}

