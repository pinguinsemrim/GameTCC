package entities;
import Game.Game;
import World.World;

public class Seed extends Entity{
	public int dir=0;
	public int speed=2;
	int t=0,tm=150;
	double dam=0;
	double vx=0,vy=0;
	int kb = 4;
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
		
		if(isColidding(this, Game.player) && !Game.player.dam) {
			Game.player.damege(dam,this);
			Game.shoot.remove(this);
	}
	}
	
	}

