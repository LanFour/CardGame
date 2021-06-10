package com.cardgame.main.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private int mouseX;
	private int mouseY;
	private int mouseDragX;
	private int mouseDragY;
	public boolean dragging = false;
	
	public boolean mouseOver(Rectangle rect) {
		if(rect.contains(new Point(mouseX, mouseY)))
			return true;
		return false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseDragX = e.getX();
		mouseDragY = e.getY();
		dragging = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public int getMouseDragX() {
		return mouseDragX;
	}

	public int getMouseDragY() {
		return mouseDragY;
	}

	public void tick() {
		
	}

}
