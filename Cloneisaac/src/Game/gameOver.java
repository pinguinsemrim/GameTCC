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
		if(Game.clic) {
			if(Game.cy >= 50 && Game.cy <=67) {
				if(Game.cx >= 77 && Game.cx<=155) {
				World.passaGame(1);
				Game.gameState=1;}
			}
			if(Game.cy >= 69 && Game.cy <=84) {
				if(Game.cx >= 77 && Game.cx<=115) {
				System.exit(0);}
			}
			Game.clic=false;
		}else {
			if(ccy >= 50 && ccy <=67 && ccx >= 77 && ccx<=155) {
				jo=true;
			}else {
				jo=false;
			}
			if(ccy >= 69 && ccy <=84 && ccx >= 77 && ccx<=155) {				
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
		if(jo) {
			g.setColor(Color.white);
		}else {
		g.setColor(Color.gray);
		}
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT-20,150,30,20,20);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Reiniciar", Game.WIDTH-25, Game.HEIGHT+2);
		//botão de sair
		if(sa) {
			g.setColor(Color.white);
		}else {
		g.setColor(Color.gray);
		}
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT+20,150,30,20,20);
		g.drawString("Sair", Game.WIDTH-5, Game.HEIGHT+42);
	}
	
}
