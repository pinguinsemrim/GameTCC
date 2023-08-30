package entities;
import java.awt.Color;
import java.awt.Graphics;

import Game.Camera;
import Game.Game;
import Game.World;


public class Player extends Entity {
		public boolean right,up,left,down;
		public boolean aright,aup,aleft,adown;
		public int ma=30,a=0;
		public boolean ab=true,lr=false,colu=false,cold=false,coll=false,colr=false;
		public double damage=1,speed=2.2;
		public int vida =6,maxvida = 6;
		boolean dam=false;
		public Player(int x, int y, int width, int height) {
			super(x, y, width, height);
			
		}
	public void tick(){
		if(up &&!this.isColiddingfora(this, (int)x, (int)(y-(1+speed/2)),Game.colision)) {
			y-=speed;
		}else if(down &&!this.isColiddingfora(this, (int)x, (int)(y+(1+speed/2)),Game.colision)) {
			y+=speed;
		}if(left &&!this.isColiddingfora(this, (int)(x-(1+speed/2)), (int)y,Game.colision)) {
			x-=speed;
		}else if(right &&!this.isColiddingfora(this, (int)(x+(1+speed/2)), (int)y,Game.colision)) {
			x+=speed;
		}
		if(aup && ab) {
			atira(3);
			ab=false;
		}else if(adown && ab) {
			atira(2);
			ab=false;
		}else if(aleft && ab) {
			atira(0);
			ab=false;
		}else if(aright && ab){
			atira(1);
			ab=false;
		}
		if(ab==false) {
			a++;
			if(a==ma) {
				ab=true;
				a=0;
			}
		}
		if(vida <=0) {
			Game.frame.dispose();
		}

    	Camera.x =Camera.clamp(this.getX()-(Game.WIDTH/3),0,World.WIDTH*Game.world.TILE_SIZE-Game.WIDTH);
    	Camera.y =Camera.clamp(this.getY()-(Game.HEIGHT/3),0,World.HEIGHT*Game.world.TILE_SIZE-Game.HEIGHT);

	}
	public void damege(double dama) {
		dam=true;
		vida-=dama;
	}
	public void atira(int dir) {
		Shoot s = new Shoot(Game.player.getX()+5,Game.player.getY()+5, 5, 5,damage);
		if(lr) {
		s = new Shoot(Game.player.getX()+5,Game.player.getY()+7, 5, 5,damage);
			lr = false;
		}else{
			s = new Shoot(Game.player.getX()+5,Game.player.getY()+3, 5, 5,damage);
			lr=true;
		}
		s.dir = dir;
		Game.shoot.add(s);
	}

	
	public void render(Graphics g) {
		if(!dam) {
			g.setColor(Color.BLUE);
			}else {
			g.setColor(Color.WHITE);
			dam=false;
			}
	g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}}
