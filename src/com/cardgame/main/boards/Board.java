package com.cardgame.main.boards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.cardgame.main.Game;

public class Board {
	
	private Rectangle enemyHandArea;
	private Rectangle enemyArea;
	private Rectangle allyArea;
	private Rectangle allyHandArea;
	private Game game;
	
	public Board(Game game) {
		this.game = game;		
	}
	public void tick() {
		enemyHandArea = new Rectangle(10, 10, game.getWidth() - 20, (int) ( (float) game.getHeight() / 100 * 20) - 10);
		enemyArea = new Rectangle(10, enemyHandArea.height + 10, game.getWidth() - 20, (int) ( (float) game.getHeight() / 100 * 30));
		allyArea = new Rectangle(10, enemyHandArea.height + enemyArea.height + 10, game.getWidth() - 20, (int) ( (float) game.getHeight() / 100 * 30));
		allyHandArea = new Rectangle(10, enemyHandArea.height + enemyArea.height + allyArea.height +10, game.getWidth() - 20, (int) ( (float) game.getHeight() / 100 * 20) - 10);
	}
	
	public void render(Graphics g) {
		g.drawRect(enemyHandArea.x, enemyHandArea.y, enemyHandArea.width, enemyHandArea.height);
		g.setColor(Color.red);
		g.drawRect(enemyArea.x, enemyArea.y, enemyArea.width, enemyArea.height);
		g.setColor(Color.green);
		g.drawRect(allyArea.x, allyArea.y, allyArea.width, allyArea.height);
		g.setColor(Color.black);
		g.drawRect(allyHandArea.x, allyHandArea.y, allyHandArea.width, allyHandArea.height);
	}

	public Rectangle getEnemyHandArea() {
		return enemyHandArea;
	}
	
	public Rectangle getEnemyArea() {
		return enemyArea;
	}
	
	public Rectangle getAllyArea() {
		return allyArea;
	}
	
	public Rectangle getAllyHandArea() {
		return allyHandArea;
	}
}
