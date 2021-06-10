package com.cardgame.main.states;

import java.awt.Graphics;
import java.util.ArrayList;

import com.cardgame.main.Game;
import com.cardgame.main.boards.Board;
import com.cardgame.main.cards.Card;

public class GameState extends State{
	
	public static ArrayList<Card> cards = new ArrayList<>();
	private Game game;
	private Card card;
	private Board board;
	
	public GameState(Game game) {
		this.game = game;
		board = new Board(game);
		card = new Card("Card01", game, board);
		cards.add(card);
	}
	
	@Override
	public void tick() {
		board.tick();
		cards.get(0).tick();
		if(cards.get(0).draggedAndDropped) {
			System.out.println(cards.get(0).draggedAndDropped);
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		board.render(g);
		cards.get(0).render(g);
	}
}
