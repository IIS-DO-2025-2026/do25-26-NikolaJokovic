package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable {
	
	private int x;
	private int y;
	
	
	public Point(){
		
	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point (int x, int y, boolean selected) {
		this(x,y);
		setSelected(selected);
	}
	
	public double distanca(int x2,int y2) {
		double dx=this.x-x2;
		double dy=this.y-y2;
		double d= Math.sqrt(dx*dx+dy*dy);
		return d;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.x && this.y == pomocna.y)
				return true;
			else
				return false;
			}
		else {
			return false;
		}
	}
	
	public boolean contains(int x, int y ) {
		return this.distanca(x, y) <=2;
	}
	
	public Point(int x,int y,boolean selected, Color color) {
		this(x,y,selected);
		this.setColor(color);
		
	}
	
    public void draw(Graphics g) {
    	g.setColor(getColor());
		g.drawLine(this.x-2,this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		
	}
	
	
	public int getX(){
		return this.x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")" ;
	}

	
	public void moveBy(int byX, int byY) {
		this.x=this.x +byX;
		this.y=this.y +byY;
		
		
	}

	
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point pocetak = new Point(0 , 0);
			return (int) (this.distanca(pocetak.getX(), pocetak.getY())-((Point)o).distanca(pocetak.getX(),pocetak.getY() ) );
		}
		return 0;
	}

	@Override
	public Shape clone() {
		Point p = new Point(this.getX(), this.getY(), this.isSelected(), this.getColor());
	    return p;
	}
	
	
	
	
}
