package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.Game;
import World.Camera;
import World.World;


public class Player extends Entity {
		public boolean right,up,left,down;
		public boolean aright,aup,aleft,adown;
		public int ma=30,a=0,xg,yg;
		public boolean ab=true,lr=false,colu=false,cold=false,coll=false,colr=false;
		public double damage=1,speed=2.2;
		public int maxvida = 8,vida =maxvida;
		private BufferedImage[] players;
		private int maxSprite=4,curSprite;
		private boolean dam=false;
		public Player(int x, int y, int width, int height,BufferedImage sprite) {
			super(x, y, width, height, sprite);
			players = new BufferedImage[4];
			for(int i = 0; i < maxSprite ; i++){
				players[i] = Game.plaer.getSprite(0,0 + (i*75),75,75);
				}
		}
	public void tick(){
		if(up &&World.isFree(this.getX(), this.getY()-(int)(speed))) {
			y-=speed;
		}else if(down &&World.isFree(this.getX(), this.getY()+(int)(speed))) {
			y+=speed;
		}if(left &&World.isFree(this.getX()-(int)(speed), this.getY())) {
			x-=speed;
		}else if(right &&World.isFree(this.getX()+(int)(speed), this.getY())) {
			x+=speed;
		}
		if(aup && ab) {
			atira(3);
			ab=false;
			curSprite=1;
		}else if(adown && ab) {
			atira(2);
			ab=false;
			curSprite=0;
		}else if(aleft && ab) {
			atira(0);
			ab=false;
			curSprite=2;
		}else if(aright && ab){
			atira(1);
			ab=false;
			curSprite=3;
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
		
    	Camera.x =Camera.clamp(this.getX()-(Game.WIDTH/2),0,World.WIDTH*World.TILE_SIZE-Game.WIDTH);
    	Camera.y =Camera.clamp(this.getY()-(Game.HEIGHT/2),0,World.HEIGHT*World.TILE_SIZE-Game.HEIGHT);

	}
	public void damege(double dama) {
		dam=true;
		vida-=dama;
	}
	public void atira(int dir) {
		Shoot s = new Shoot(this.getX()-Camera.x,this.getY()-Camera.y, 20, 20,damage);
		if(lr) {
		s = new Shoot(Game.player.getX()+5,Game.player.getY()+7, 20,20,damage);
			lr = false;
		}else{
			s = new Shoot(Game.player.getX()+5,Game.player.getY()+3, 20,20,damage);
			lr=true;
		}
		s.dir = dir;
		Game.shoot.add(s);
	}

	
	public void render(Graphics2D g) {	   
		g.drawImage(players[curSprite], this.getX()-Camera.x, this.getY()-Camera.y, width, height,null);
	}}
