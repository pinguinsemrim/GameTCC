package World;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import World.Wall;
import entities.Enemy;
import entities.Entity;
import entities.Flower;
import entities.Player;
import entities.Rock;
import itens.Updam;
import Game.Game;
import Game.Ui;
import Game.gameOver;
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
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_WALL_TOP);	
					}else if(pixelAtual == 0xFFb4b4b4) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_WALL_BOTTON);	
					}else if(pixelAtual == 0xFFe3e3e3) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_WALL_LEFT);	
					}else if(pixelAtual == 0xFFd9d9d9) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_WALL_RIGHT);	
					}else if(pixelAtual == 0xFFff00ff) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_CONNER_LEFT_TOP);	
					}else if(pixelAtual == 0xFFfa9bfa) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_CONNER_RIGHT_TOP);	
					}else if(pixelAtual == 0xFFc400c4) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_CONNER_RIGHT_BOTTON);	
					}else if(pixelAtual == 0xFF570357) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_CONNER_LEFT_BOTTON);	
					}else if(pixelAtual == 0xFF0026FF) {
						Game.player.x =(xx*TILE_SIZE);
						Game.player.y =(yy*TILE_SIZE);
					}else if(pixelAtual == 0xFFFF0000) {
						Flower enemy = new Flower(xx*TILE_SIZE,yy*TILE_SIZE,50,50,Entity.flower);
						Game.entities.add(enemy);
						Game.enimies.add(enemy);
					}else if(pixelAtual == 0xFF7F6A00) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*TILE_SIZE,yy*TILE_SIZE,Tile.TILE_FLOOR);
						Rock rock = new Rock(xx*TILE_SIZE,yy*TILE_SIZE,40,40);
						Game.entities.add(rock);
						
					}else if(pixelAtual == 0xFFFF6A00) {
						Enemy enemy = new Enemy(xx*TILE_SIZE,yy*TILE_SIZE,50,50,Entity.koala1);
						Game.entities.add(enemy);
						Game.enimies.add(enemy);;
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
	public static boolean isFree(int xnext,int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;

		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;

		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		try {
		    return !((tiles[x1 + (y1 * World.WIDTH)] instanceof Wall)
		            || (tiles[x2 + (y2 * World.WIDTH)] instanceof Wall)
		            || (tiles[x3 + (y3 * World.WIDTH)] instanceof Wall)
		            || (tiles[x4 + (y4 * World.WIDTH)] instanceof Wall));
		} catch (ArrayIndexOutOfBoundsException e) {
		    // Trate a exceção aqui (por exemplo, retornando false ou lançando outra exceção)
		    // Você pode adicionar um registro de erro ou tratamento personalizado, se necessário.
		    return false; // Ou faça algo mais apropriado para sua lógica de negócios.
		}
		}
	
	public static void passaGame(int lvl){
		System.out.println(lvl);
		Game.entities = new ArrayList<Entity>();
		Game.enimies = new ArrayList<Entity>();
		Game.colision = new ArrayList<Entity>();
		Game.shoot = new ArrayList<Entity>();
		Game.sprites();
		Game.player = new Player(WIDTH/2 - 30,HEIGHT/2,50,50,Entity.slime);
		Game.world = new World("/Room"+lvl+".png");
		Game.ui = new Ui();
		Game.gO = new gameOver();
		Game.entities.add(Game.player);
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 6;
		int ystart = Camera.y >> 6;
		
		int xfinal = xstart + (Game.WIDTH >> 3);
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
