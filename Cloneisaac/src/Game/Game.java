package Game;
	import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Rock;
import entities.Shoot;
import itens.Updam;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener  {

		
		private static final long serialVersionUID = 1L;
		public static JFrame frame;
		private Thread thread;
		private boolean isRunning = true;
		public static final int WIDTH = 260;
		public static final int HEIGHT = 190 ;
		public static final int SCALE = 3;
		private BufferedImage image;
		public static Random rand;
		public static ArrayList<Entity> entities;
		public static ArrayList<Shoot> shoot;
		public static Player player;
		public static Enemy enemy;
		public static Updam updam;
		public static Rock rock;
		
		//public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelfont1.ttf"); 
		//public Font newfont;
		
		public int mx,my;
		public int[] pixels;
		public Game() {
			
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
			initFrame();
			//Sound.musicew.loop( );
			rand = new Random();
			 
	//inzalando as coisas	
			
			image =new BufferedImage(160,120,BufferedImage.TYPE_INT_RGB);
			entities = new ArrayList<Entity>();
			shoot = new ArrayList<Shoot>();
			player = new Player(0,0,16,16);
			entities.add(player); 
			enemy = new Enemy(50,50,16,16);
			entities.add(enemy);
			updam = new Updam(30,80,10,10);
			entities.add(updam);
			rock = new Rock(90,60,19,19);
			entities.add(rock);
		
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
		}
		
		public void render(){
			BufferStrategy bs = this.getBufferStrategy(); 
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			Graphics g = image.getGraphics();
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		//rederizando entidades e tiros
		for(int i =0;i<entities.size();i++) {
			entities.get(i).render(g);
		}
		for(int i =0;i<shoot.size();i++) {
			shoot.get(i).render(g);
		}
	    //terminou??
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0, WIDTH*SCALE,HEIGHT*SCALE,null); 
		
		
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
	    tick();	render();		
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


