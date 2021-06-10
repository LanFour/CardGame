package com.cardgame.main;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import com.cardgame.main.graphics.*;
import com.cardgame.main.input.KeyManager;
import com.cardgame.main.input.MouseManager;
import com.cardgame.main.states.*;

public class Game implements Runnable {

	private Thread thread;
	
	private boolean isRunning = false;
		
	//create window
	private Display display;
	public String title;
	private int width, height;
	
	//buffers e drawing
	private BufferStrategy bs;
	private Graphics g;
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//states
	private State gameState;
	private State menuState;
	
	//TODO delete this array, more info on its .add
	private ArrayList<State> states;
	
	public Game(String title, int width, int height) {	
		this.width = width;
		this.height = height;
		this.title = title;
		
		keyManager = new KeyManager();
		mouseManager =  new MouseManager();
		//TODO useless, more info on .add
		states = new ArrayList<State>();
	}
	//TODO make this synchros package access
	public synchronized void start() {
		thread = new Thread(this);
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
	
	private void init() {
		//frame and canvas
		//TODO What a cluster
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
				
		gameState = new GameState(this);
		menuState = new MenuState();
		StateManager.setState(gameState);
		
		//TODO this is just so Eclipse leaves me alone about unused menuState
		states.add(gameState);
		states.add(menuState);
	}
	
	//TODO tick is here
	public void tick() {
		
		keyManager.tick();
		mouseManager.tick();
				
		if(StateManager.getState() != null)
			StateManager.getState().tick();
	}
	//TODO render is here
	public void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0,  display.getCanvas().getSize().width,  display.getCanvas().getSize().height);
		
		//Draw here
		
		if(StateManager.getState() != null)
			StateManager.getState().render(g);
		//End drawing
		bs.show();
		g.dispose();
	}
	
	//Getters
	public Game getGame() {
		return this;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public int getWidth() {
		return display.getCanvas().getSize().width;
	}

	public int getHeight() {
		return display.getCanvas().getSize().height;
	}

	
	@Override
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}
	
}