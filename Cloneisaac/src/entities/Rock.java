package entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;

public class Rock extends Entity {
    Player player = Game.player;
	public Rock(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		/*
		int px = player.getX(),py = player.getY();
		
		if(this.isColiddingfora(player, px-1, py+1, this)) {
			player.cold=true;
		}else if(this.isColiddingfora(player, px, py-1, this)) {
			player.colu=true;
		}else {
			player.colu =false;
			 player.cold=false;
			 player.coll =false;
			 player.colr=false;
		}
		if(this.isColiddingfora(player, px, py, this)) {
			player.colr=true;
		}else if(this.isColiddingfora(player, px-1, py, this)) {
			player.coll=true;
		}else {
			 player.colu =false;
			 player.cold=false;
			 player.coll =false;
			 player.colr=false;
		}
     */
		
	}
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(this.getX(), this.getY(), width, height);
	}
}
