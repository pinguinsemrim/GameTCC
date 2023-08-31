package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ui {
	public BufferedImage full= Game.health.getSprite(0, 0, 15, 16);
	public BufferedImage half= Game.health.getSprite(15, 0, 15, 16);
	public BufferedImage hollow= Game.health.getSprite(30, 0, 15, 16);
	public double draw =3,maxdraw;
	public void Ui(){
		
	}
	public void tick() {
		draw = Game.player.vida/2;
		maxdraw = Game.player.maxvida/2;
	}	
	public void render(Graphics g) {
		
		for(int i=0;i<maxdraw;i++) {
			 g.drawImage(hollow, 0+(i*16), 0, null);
			 if(Game.player.vida%2!=0 && draw==i) {
				g.drawImage(half, 0+(i*16), 0, null);
			}else if(draw>i) {
				g.drawImage(full, 0+(i*16), 0, null);
			}
		}
	}
}
