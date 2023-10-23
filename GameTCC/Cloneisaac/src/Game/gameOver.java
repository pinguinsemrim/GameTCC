package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import World.World;

public class gameOver {
	public boolean rec =false;
	public boolean jo=false,sa=false;
	public int ccx=0,ccy=0;
	public void gameOver(){}
	public void tick() {
		if(rec) {
			World.passaGame(0);
			Game.gameState=1;
			rec=false;
		}
		if(Game.clic) {
			if(Game.cy >= 556 && Game.cy <=114) {
				if(Game.cx >= 107 && Game.cx<=240) {
				World.passaGame(0);
				Game.gameState=1;}
			}
			if(Game.cy >= 119 && Game.cy <=147) {
				if(Game.cx >= 107 && Game.cx<=240) {
				System.exit(0);}
			}
			Game.clic=false;
		}else {
			if(ccy >= 87 && ccy <=114 && ccx >= 107 && ccx<=240) {
				jo=true;
			}else {
				jo=false;
			}
			if(ccy >= 119 && ccy <=147 && ccx >= 107 && ccx<=240) {				
				sa=true;
			}else {
				sa=false;
			}
		}
	}	
	public void render(Graphics2D g) {
		g.setColor(new Color(0,0,0,170));
		g.fillRect(0, 0, Game.WIDTH*3, Game.HEIGHT*3); 
		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.white);
		g.drawString("Game Over", Game.WIDTH/2,Game.HEIGHT/2);
		//botão de reset
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT-20,150,30,20,20);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Reiniciar", Game.WIDTH-25, Game.HEIGHT+2);
		//botão de sair
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT+20,150,30,20,20);
		g.drawString("Sair", Game.WIDTH-5, Game.HEIGHT+42);
	}
	
}
