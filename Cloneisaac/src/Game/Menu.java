package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import World.World;

public class Menu {
	public boolean rec =false;
	public boolean jo=false,sa=false;
	public int ccx=0,ccy=0;
	public void gameOver(){}
	public void tick() {
		if(Game.clic) {
			if(Game.cy >= 65 && Game.cy <=79) {
				if(Game.cx >= 77 && Game.cx<=155) {
				Game.gameState=1;}
			}
			if(Game.cy >= 86 && Game.cy <=99) {
				if(Game.cx >= 77 && Game.cx<=115) {
				System.exit(0);}
			}
			Game.clic=false;
		}else {
			if(ccy >= 65 && ccy <=79 && ccx >= 77 && ccx<=155) {
				jo=true;
			}else {
				jo=false;
			}
			if(ccy >= 86 && ccy <=99 && ccx >= 77 && ccx<=155) {				
				sa=true;
			}else {
				sa=false;
			}
		}
	}	
	public void render(Graphics2D g) {
		g.setColor(new Color(29,29,134));
		g.fillRect(0, 0, Game.WIDTH*3, Game.HEIGHT*3); 
		g.setColor(new Color(170,0,0,250));
		g.setFont(new Font("serif",Font.BOLD,100));
		g.drawString("Tale for", 80, 80);
		g.setColor(new Color(240,0,0,200));
        g.drawString("Jay", 190, 110);
		//botão de reset
		if(jo) {
			g.setColor(Color.cyan);
		}else {
		g.setColor(Color.gray);
		}
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT+10,150,30,20,20);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Jogar", Game.WIDTH-15, Game.HEIGHT+32);
		//botão de sair
		if(sa) {
			g.setColor(Color.red);
		}else {
		g.setColor(Color.gray);
		}
		g.drawRoundRect(Game.WIDTH-60,Game.HEIGHT+60,150,30,20,20);
		g.drawString("Sair", Game.WIDTH-5, Game.HEIGHT+82);
	}
	
}
