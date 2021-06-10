package com.cardgame.main;

public class Launcher {
	
	public static void main(String[] args) {	
		Game game = new Game("Card game", 640, 640);
		game.start();
	}
}