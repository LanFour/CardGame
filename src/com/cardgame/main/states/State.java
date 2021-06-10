package com.cardgame.main.states;

import java.awt.Graphics;

public abstract class State{
	//subclasses inherit their parents' field variables.
	
	public State() {
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
