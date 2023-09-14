package entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.Camera;
import Game.Game;

public class Seed extends Entity{
	public int dir=0;
	public int speed=2;
	int t=0,tm=150;
	double dam=0;
	double vx=0,vy=0;
			
	public Seed (int x, int y, int width, int height,double dam,double vx,double vy) {
		super(x, y, width, height,slime);
		this.dam=dam;
		this.vx=vx;
		this.vy=vy;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		if(t<tm) {
			t++;
		}else {
			Game.shoot.remove(this);
		}
		x+=vx*speed;
		y+=vy*speed;
		
		
	}
	}

