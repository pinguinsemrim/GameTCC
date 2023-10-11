package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ui {
	public BufferedImage full= Game.health.getSprite(0, 0, 15, 16);
	public BufferedImage half= Game.health.getSprite(15, 0, 15, 16);
	public BufferedImage hollow= Game.health.getSprite(30, 0, 15, 16);
	public double draw =3,maxdraw;
	private int ms=0,s=0,m=0;
	public void Ui(){
		
	}
	public void tick() {
		if(Game.enimies.size()>0) {
		ms++;
		if(ms==60) {
			ms=0;
			s++;
			if(s==60) {
				m++;
				s=0;
			}}
		}
		draw = Game.player.vida/2;
		maxdraw = Game.player.maxvida/2;
	}	
	public void render(Graphics g) {
		g.drawString("kills: "+Game.player.kills, Game.WIDTH-20, 20);
		g.drawString(String.format("%02d", m)+":"+String.format("%02d", s)+':'+String.format("%02d", ms), Game.WIDTH*2-40, 20);
		for(int i=0;i<maxdraw;i++) {
			 g.drawImage(hollow, 0+(i*25), 0,25,25, null);
			 if(Game.player.vida%2!=0 && draw==i) {
				g.drawImage(half, 0+(i*25), 0,25,25, null);
			}else if(draw>i) {
				g.drawImage(full, 0+(i*25), 0,25,25, null);
			}
		}
	}
}
