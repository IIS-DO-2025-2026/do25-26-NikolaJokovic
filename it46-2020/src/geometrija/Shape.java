package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import controller.Moveable;

public abstract class Shape implements Moveable, Comparable,Serializable{
	
	private boolean selected;
	private Color color;
	public abstract Shape clone();
	
	public Shape() {

	}
	public Shape(Color color) {
		this.color = color;
	}

	public Shape(Color color, boolean selected) {
		this(color);
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public abstract boolean contains(int x, int y);
	public abstract void draw (Graphics g);
	

}
