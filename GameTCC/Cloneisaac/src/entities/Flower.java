package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.Game;
import World.Camera;

public class Flower extends Entity {
	public boolean dam =false;
	public int vida =10;
	public int ma=30,a=0,dir=0;
	private double kb =50,dama=1;
	public boolean ab=true,animate=false,at=false,death;
	private BufferedImage[] flowers;
	private BufferedImage[] deathFlowers;
	private int FAnima = 0,DFAnima=0;
    private int FMax = 3,DFMax=6;
    private int maxSprite = 2,dmaxSprite=18;
    private int curSprite = 0,dcurSprite=0;
	public Flower(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		flowers = new BufferedImage[2];
		for(int i = 0; i < maxSprite ; i++){
			flowers[i] = Game.flower.getSprite(0 + (i*40),0,40,40);
			}
		deathFlowers = new BufferedImage[18];
		for(int i = 0; i < dmaxSprite ; i++){
			deathFlowers[i] = Game.deathflower.getSprite(0 + (i*40),0,40,40);
			}
	}
	public void tick(){
		if(vida<=0) {
			death=true;
			
		}
		
		if(this.calculateDistance(this.getX(), this.getY(),Game.player.getX(),Game.player.getY()) <160 && at) {
			animate=true;
		}else {
			animate=false;
		}
		if(isColidding(this,Game.player) && ab) {
			Game.player.damege(dama,this);
		}
		if(a<ma) {
			a++;
		}else {
			at=true;
		}
			
	}
	public void damage(double dam2) {
		dam=true;
		vida-=dam2;
	}
	public void Shoot() {
		Seed s = new Seed(this.getX()+this.height/2,this.getY()+this.height/2, 10,10,
				dama,Math.cos(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY())),
				Math.sin(this.calculateAngle(this.getX(), this.getY(),Game.player.getX(),Game.player.getY())));
		Game.shoot.add(s);
	}
	public void render(Graphics2D g) {
			if(animate) {
				sprite = flowers[curSprite];
				 FAnima++;
				   if(FAnima == FMax && ab) {
					   curSprite++;
					   FAnima=0;
					   if(curSprite == maxSprite) {
						   curSprite =0;
						   Shoot();
						   at=false;
						  a=0;
					   }}}else {
					    	  sprite = flowers[0];
					      }
			if(death) {
				ab=false;
				 DFAnima++;
				 sprite = deathFlowers[dcurSprite];
				   if(DFAnima == DFMax) {
					   dcurSprite++;
					   DFAnima=0;
					   if(dcurSprite == dmaxSprite) {
						   Game.entities.remove(this);
						   Game.enimies.remove(this);	
						   Game.player.kills++;
		      }}}
			g.drawImage(sprite,this.getX()-Camera.x,this.getY()-Camera.y,this.getWidth(),this.getHeight(),null);	
	}}
   