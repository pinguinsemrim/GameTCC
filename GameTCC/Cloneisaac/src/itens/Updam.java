package itens;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;
import entities.Entity;

public class Updam extends Entity{

	public Updam(int x, int y, int width, int height) {
		super(x, y, width, height,slime);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		if(this.isColidding(this, Game.player)) {
			Game.player.damage=5;
			Game.entities.remove(this);
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(this.getX(), this.getY(), width, height);
	}

}
