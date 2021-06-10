package com.cardgame.main.states;

public class StateManager {
	
	private static State s= null;

	public static void setState(State state) {
		s = state;
	}
	
	public static State getState() {
		return s;
	}
}
