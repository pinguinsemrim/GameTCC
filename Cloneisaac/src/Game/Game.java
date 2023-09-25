package Game;
	import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import World.World;
import entities.Entity;
import entities.Player;
import entities.Shoot;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener  {

		
		private static final long serialVersionUID = 1L;
		public static JFrame frame;
		private Thread thread;
		private boolean isRunning = true;
		public static final double SCALE = 3;
		public static final int WIDTH = 230;
		public static final int HEIGHT = 150;
		private BufferedImage image;
		public static Random rand;
		public static ArrayList<Entity> entities;
		public static ArrayList<Entity> enimies;
		public static ArrayList<Entity> shoot;
		public static ArrayList<Entity> colision;
		public static Spritesheet health;
		public static Spritesheet tile;
		public static Spritesheet slime;
		public static Spritesheet flower;
		public static Spritesheet deathflower;
		public static Spritesheet seed;
		public static Spritesheet koala;
		public static Player player;
		public static Ui ui;
		public static World world;
		
		//public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelfont1.ttf"); 
		//public Font newfont;
		
		public int mx,my;
		public int[] pixels;
		public void sprites() {
			deathflower = new Spritesheet("/deathFlower.png");
			health = new Spritesheet("/Health.png");
			tile = new Spritesheet("/Tiles.png");
			slime = new Spritesheet("/slimecube.png");
			flower = new Spritesheet("/flores.png");
			seed = new Spritesheet("/seed.png");
			koala = new Spritesheet("/Koala.png");
		}
		
		public Game() {
			
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setPreferredSize(new Dimension((int)(WIDTH*SCALE),(int)(HEIGHT*SCALE)));
			initFrame();
			//Sound.musicew.loop( );
			rand = new Random();
			 
	//inzalando as coisas	
			image =new BufferedImage((int)(160*(SCALE)),(int)(120*(SCALE)),BufferedImage.TYPE_INT_RGB);
			
			entities = new ArrayList<Entity>();
			enimies = new ArrayList<Entity>();
			colision = new ArrayList<Entity>();
			shoot = new ArrayList<Entity>();
			sprites();
			player = new Player(WIDTH/2 - 30,HEIGHT/2,40,40,Entity.slime);
			world = new World("/level1.png");
			ui = new Ui();
			
			entities.add(player);
		}
		public void initFrame() {
			frame = new JFrame("TGF");
			frame.add(this);
			frame.setResizable(false);
			frame.pack();
			Image imagem = null;
		    frame.setAlwaysOnTop(true);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}	
		public synchronized void start() {
			thread =new Thread(this);
			isRunning = true;
			thread.start();
		}
		public synchronized void stop() {
		 isRunning = false;
			try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
		public static void main(String args[]) {
		Game game = new Game();
		game.start();
		
		}
		public void tick() {//tick das entidades e tiros
			for(int i =0;i<entities.size();i++) {
				entities.get(i).tick();
			}
			for(int i =0;i<shoot.size();i++) {
				shoot.get(i).tick();
			}
			ui.tick();
		}
		
		public void render(){
			BufferStrategy bs = this.getBufferStrategy(); 
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			Graphics g = image.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0,(int)(WIDTH*SCALE),(int)(HEIGHT*SCALE));
		//rederizando entidades e tiros
		world.render(g);
		for(int i =0;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.render(g2);
		}
		
		for(int i =0;i<shoot.size();i++) {
			shoot.get(i).render(g2);
		}
		ui.render(g);
	    //terminou??
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0, (int)(WIDTH*SCALE),(int)(HEIGHT*SCALE),null); 
		bs.show();
		}
		public void run() {
		long lastTime = System.nanoTime();
		double amountOftick = 60.0;
		double ns = 1000000000 / amountOftick ;
		double delta = 0;
		int frames = 0;
		double  timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {	
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;	
		lastTime = now;
		if(delta >= 1) {
			tick();	
			render();	
		frames ++;	
		delta --;}
		if(System.currentTimeMillis() - timer >= 1000 ) {
		System.out.println("FPS:"+frames);
		frames = 0;
		timer += 1000;}}
		stop();}
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			//anda
			if(e.getKeyCode() == e.VK_D) {
				player.right = true;
			}if(e.getKeyCode() == e.VK_A) {
				player.left = true;
			}if(e.getKeyCode() == e.VK_S) {
				player.down = true;
			}if(e.getKeyCode() == e.VK_W) {
				player.up = true;
			}
			//atira
			if(e.getKeyCode() == e.VK_RIGHT) {
				player.aright = true;
			}if(e.getKeyCode() == e.VK_LEFT) {
				player.aleft = true;
			}if(e.getKeyCode() == e.VK_DOWN) {
				player.adown = true;
			}if(e.getKeyCode() == e.VK_UP) {
				player.aup = true;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			//para anda
			if(e.getKeyCode() == e.VK_D) {
				player.right = false;
			}if(e.getKeyCode() == e.VK_A) {
				player.left = false;
			}if(e.getKeyCode() == e.VK_S) {
				player.down = false;
			}if(e.getKeyCode() == e.VK_W) {
				player.up = false;
			}
			//para atira
			if(e.getKeyCode() == e.VK_RIGHT) {
				player.aright = false;
			}if(e.getKeyCode() == e.VK_LEFT) {
				player.aleft = false;
			}if(e.getKeyCode() == e.VK_DOWN) {
				player.adown = false;
			}if(e.getKeyCode() == e.VK_UP) {
				player.aup = false;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		
		}

	}



