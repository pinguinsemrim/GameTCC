package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;

public class Flower extends Entity {
	public boolean dam =false;
	public int vida =10;
	public int ma=30,a=0,dir=0;
	public double kb =10,dama=1;
	public boolean ab=true,animate=false;
	private int FAnima = 0;
    private int FMax = 3;
    private int maxSprite = 4;
    private int curSprite = 0;
	public Flower(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		if(vida<=0) {
			Game.entities.remove(this);
		}
		
		if(this.calculateDistance(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()) <80) {
			animate=true;
		}else {
			animate=false;
		}
		if(isColidding(this,Game.player) && ab) {
			Game.player.damege(dama);
			if(dir==0) {
				Game.player.x+=kb;
			}else if(dir==1) {
				Game.player.x-=kb;
			}else if(dir==2) {
				Game.player.y+=kb;
			}else {
				Game.player.y-=kb;
			}
			ab=false;
			}
		if(ab==false) {
			a++;
			if(a==ma) {
				ab=true;
				a=0;
			}}	
	}
	public void damage(double dam2) {
		dam=true;
		vida-=dam2;
	}
	public void render(Graphics g) {
		if(animate) {
			 FAnima++;
			   if(FAnima == FMax) {
				   curSprite++;
				   FAnima=0;
				   if(curSprite == maxSprite) {
					   curSprite =0;
				   }}}
	    	  sprite = Entity.slime;
	    	  g.drawString("rave de matal", this.getX(), this.getY());}


}
