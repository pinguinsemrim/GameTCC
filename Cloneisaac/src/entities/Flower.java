package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.Camera;
import Game.Game;

public class Flower extends Entity {
	public boolean dam =false;
	public int vida =10;
	public int ma=30,a=0,dir=0;
	public double kb =50,dama=1;
	public boolean ab=true,animate=false;
	private BufferedImage[] flowers;
	private int FAnima = 0;
    private int FMax = 3;
    private int maxSprite = 2;
    private int curSprite = 0;
    private double damage =1;
	public Flower(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		flowers = new BufferedImage[24];
		for(int i = 0; i < maxSprite ; i++){
			flowers[i] = Game.flower.getSprite(0 + (i*40),0,40,40);
			}
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
			if(Game.player.left) {
				Game.player.x-=kb;
			}else if(Game.player.right) {
				Game.player.x+=kb;
			}else if(Game.player.down) {
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
	public void Shoot() {
		Seed s = new Seed(this.getX()+this.height/2,this.getY()+this.height/2, 5, 5,
				damage,Math.cos(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY())),
				Math.sin(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY())));
		Game.shoot.add(s);
	}
	public void render(Graphics2D g) {
		if(animate) {
			 FAnima++;
			   if(FAnima == FMax) {
				   curSprite++;
				   FAnima=0;
				   if(curSprite == maxSprite) {
					   curSprite =0;
					   Shoot();
				   }}}
		      sprite = flowers[curSprite];
		      g.drawImage(sprite,this.getX()-Camera.x,this.getY()-Camera.y,this.getWidth(),this.getHeight(),null);	
		      }


}
   