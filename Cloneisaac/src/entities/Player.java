package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Game.Game;

import entities.Entity;


public class Player extends Entity {
		public boolean right,up,left,down;
		public boolean aright,aup,aleft,adown;
		public int ma=30,a=0;
		public boolean ab=true,lr=false;
		public int vida =10;
		boolean dam=false;
		public Player(int x, int y, int width, int height) {
			super(x, y, width, height);
			
		}
	public void tick(){
		if(up) {
			y--;
		}else if(down) {
			y++;
		}if(left) {
			x--;
		}else if(right) {
			x++;
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
		
	}
	public void damege(int da) {
		dam=true;
		vida-=da;
	}
	public void atira(int dir) {
		Shoot s = new Shoot(Game.player.getX()+5,Game.player.getY()+5, 5, 5,1);
		if(lr) {
		s = new Shoot(Game.player.getX()+5,Game.player.getY()+7, 5, 5,1);
			lr = false;
		}else{
			s = new Shoot(Game.player.getX()+5,Game.player.getY()+3, 5, 5,1);
			lr=true;
		}
		s.dir = dir;
		Game.entities.add(s);
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
