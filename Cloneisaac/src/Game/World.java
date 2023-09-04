package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.Flower;
import entities.Rock;
import itens.Updam;

public class World {
	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 40;
	
	
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new Tile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF000000) {
						tiles[xx + (yy * WIDTH)] = new Tile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_FLOOR);						
					}else if(pixelAtual == 0xFFffffff) {
						tiles[xx + (yy * WIDTH)] = new Tile(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_WALL);
						
					}else if(pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx*TILE_SIZE);
						Game.player.setY(yy*TILE_SIZE);
					}else if(pixelAtual == 0xFFFF0000) {
						Flower enemy = new Flower(xx*TILE_SIZE,yy*TILE_SIZE,40,40,Entity.flower);
						Game.entities.add(enemy);
					}else if(pixelAtual == 0xFFffc30e) {
						Rock rock = new Rock(xx*TILE_SIZE,yy*TILE_SIZE,40,40);
						Game.colision.add(rock);
						Game.entities.add(rock);
					}
					else if(pixelAtual == 0xFF99d9ea) {
						Updam updam = new Updam(xx*TILE_SIZE,yy*TILE_SIZE,40,40);
						Game.entities.add(updam);
					}
			}
			}} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void passaGame(int lvl){
		//Game.spritesheet = new Spritesheet("/spritesheet.png");
		//Game.titulo = new Spritesheet("/meen.png");
		Game.entities = new ArrayList<Entity>();
		//Game.player = new Player(WIDTH/2 - 30,HEIGHT/2,16,32,2,Entity.PLAYER_SPRITE[0]);
		Game.world = new World("/level"+lvl+".png");
		Game.entities.add(Game.player);
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 6;
		int ystart = Camera.y >> 6;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 3);
		
		for(int xx = xstart;xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
}