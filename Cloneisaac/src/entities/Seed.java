package entities;
import Game.Game;
import World.World;

public class Seed extends Entity{
	public int dir=0;
	public int speed=2;
	int t=0,tm=150;
	double dam=0;
	double vx=0,vy=0;
	double kb = 6;
	public Seed (int x, int y, int width, int height,double dam,double vx,double vy) {
		super(x, y, width, height,seed);
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
		
		if(isColidding(this, Game.player)) {
			Game.player.damege(dam);
			Game.shoot.remove(this);
			if(World.isFree((int)(Game.player.x + Math.cos(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb),(int)(Game.player.y += Math.sin(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb))) {
			Game.player.x += Math.cos(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb;
			Game.player.y += Math.sin(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()))*kb;
			}
	}
	}
	
	}

