package entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;

public class Shoot extends Entity{
	public int dir=0;
	public int speed=2;
	public double dam=0;
	public Shoot(int x, int y, int width, int height,double dam) {
		super(x, y, width, height);
		this.dam=dam;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		
		if(dir==0) {
			x-=speed;
		}if(dir==1) {
			x+=speed;
		}if(dir==2) {
			y+=speed;
		}if(dir==3) {
			y-=speed;
		}
		for(int i=0;i<Game.entities.size();i++) {
			if(Game.entities.get(i).getClass() == Enemy.class) {
				if(Entity.isColiddingfora(this, Game.entities.get(i))) {
				Enemy e =(Enemy)Game.entities.get(i);
				e.damage(dam);
				Game.entities.remove(this);
			}}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
	g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}}

