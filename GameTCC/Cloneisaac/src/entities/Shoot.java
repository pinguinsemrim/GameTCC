package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.Game;
import World.Camera;

public class Shoot extends Entity{
	public int dir=0;
	public int speed=6;
	int t=0,tm=100;
	public double dam=10;
	double sca = 1;
	private BufferedImage[] tears;
    private int FMax = 3,FAnima = 0;
	private int maxSprite=4,curSprite;
	public Shoot(int x, int y, int width, int height,double dam) {
		super(x, y, width, height,slime);
		this.dam=dam;
		// TODO Auto-generated constructor stub
		tears = new BufferedImage[4];
		for(int i = 0; i < maxSprite ; i++){
			tears[i] = Game.tear.getSprite(0 + (i*15),0,15,15);
			}
	}
	public void tick(){
		if(t<tm) {
			
			t++;
		}else {
			Game.shoot.remove(this);
		}
		if(dir==0) {
			x-=speed;
		}if(dir==1) {
			x+=speed;
		}if(dir==2) {
			y+=speed;
		}if(dir==3) {
			y-=speed;
		}
		for(int i=0;i<Game.enimies.size();i++) {
				if(Entity.isColidding(this, Game.enimies.get(i))) {
				Entity e =Game.enimies.get(i);
				e.damage(dam);
				Game.shoot.remove(this);
			}
		}
		
	}
	public void render(Graphics2D g) {
	//height-=t;
	//width-=t;
	sprite = tears[curSprite];
	 FAnima++;
	   if(FAnima == FMax) {
		   curSprite++;
		   FAnima=0;
		   if(curSprite == maxSprite) {
			   curSprite =0;
		   }}
	g.drawImage(tears[curSprite], this.getX()-Camera.x, this.getY()-Camera.y, width,height, null);
	}}

