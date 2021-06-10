package com.cardgame.main.cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.cardgame.main.Game;
import com.cardgame.main.boards.Board;
import com.cardgame.main.input.MouseManager;

public abstract class CardTemplate {
	
	private MouseManager mouse;
	private Board board;
	
	private Rectangle cardBounds;
	
	//used to know if the card was moved
	public boolean draggedAndDropped = false;
	float renderX;
	private float renderY;
	private float width 	= 44;
	private float height	= 64;
	
	//TODO make the render use tick methods (I.E. tick x (+ or -) render x) /* What do you mean past LanFour? Future LanFour doesn't understand, be clearer next time. 
	public CardTemplate(String name, Game game, Board board) {
		this.board = board;
		cardBounds	= new Rectangle();
		cardBounds.x = 0;
		cardBounds.y = game.getGame().getHeight() - 80 - 1;
		cardBounds.width = 44;
		cardBounds.height = 64;
		renderY = game.getGame().getHeight() - 80;
		mouse = game.getMouseManager();
	}
	
	public void tick() {
		// renders the object into the middle of the chosen area, using the object's middle point as anchor.
		cardBounds.x = board.getAllyHandArea().x + board.getAllyHandArea().width / 2  - cardBounds.width / 2;
		cardBounds.y = board.getAllyHandArea().y + board.getAllyHandArea().height / 2 - cardBounds.height / 2;
		/**/
		dragAndDrop(board.getAllyArea(), cardBounds);
	}
	
	private void dragAndDrop(Rectangle validArea, Rectangle draggableObject) {
		//TODO when the card is being dragged after changing position the boundary goes back to original position, fix this.
		if(mouse.dragging && mouse.mouseOver(draggableObject)) {
			renderX = mouse.getMouseDragX() - draggableObject.width / 2;
			renderY = mouse.getMouseDragY() - draggableObject.height / 2;
		}
		if(!mouse.dragging) {
			//if the drop position IS NOT valid the object goes back to it's previous valid position
			if(validArea.contains(renderX, renderY, width, height) == false) {
				renderX = cardBounds.x;
				renderY = cardBounds.y;
			} else {
				// if drop position is valid
				// renders the dragged object into the middle of the chosen area, using the object's middle point as anchor.
				draggedAndDropped = true;
				renderX = validArea.x + validArea.width / 2 - draggableObject.width / 2;
				renderY = validArea.y + validArea.height / 2 - draggableObject.height / 2;
				//changes the clickable area to the new render position.
				cardBounds.x = (int) renderX;
				cardBounds.y = (int) renderY;
				return;
			}
		}
		draggedAndDropped = false;
	}
	
	private void renderCard(Graphics g, MouseManager mouse) {
		int mouseHovering = 0;
		if(mouse.mouseOver(cardBounds)) 
			mouseHovering += 10;
		//Card
		g.setColor(Color.BLACK);
		g.fillRect((int) renderX - mouseHovering/2, (int) renderY-mouseHovering/2, (int) width + mouseHovering, (int) height + mouseHovering);
		//Card boundaries
		g.setColor(Color.RED);
		g.drawRect(cardBounds.x, cardBounds.y, cardBounds.width, cardBounds.height);
	}
	
	public void render(Graphics g) {
		renderCard(g, mouse);
	}
}