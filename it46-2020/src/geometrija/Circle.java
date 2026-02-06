package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Circle extends SurfaceShape implements Serializable{
	
	private Point center;
	private int radius;
	
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center=center;
		this.radius=radius;
	}
	
	public Circle (Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	public Circle (Point center, int radius, boolean selected, Color color) {
		this(center,radius,selected);
		this.setColor(color);
	}
	public Circle (Point center, int radius, boolean selected, Color color,Color innerColor) {
		this(center,radius,selected,color);
		this.setInnerColor(innerColor);
	}
	
	public double area () {
		return radius * radius * Math.PI;
	}
	
	public boolean equals (Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna= (Circle) obj;
			if(this.center.equals(pomocna.center) && this.radius == pomocna.radius) {
				return true;}
			else {
				return false;}
			}
		else {
			return false;}
	}
	
	public boolean contains(int x, int y) {
		return this.center.distanca(x, y) <= this.radius;
	}
	public boolean contains(Point p) {
		return this.center.distanca(p.getX(), p.getY()) <= this.radius;
	}
	
    public void draw(Graphics g) {
    	g.setColor(getColor());
    	g.drawOval(this.center.getX()-this.radius,this.center.getY()-this.radius, this.radius*2,this.radius*2);
    	this.fill(g);
    	if (isSelected()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.drawOval(center.getX() - radius - 2, center.getY() - radius - 2,
                        radius * 2 + 4, radius * 2 + 4);
        }
	}

	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX()-this.radius+1,this.center.getY()-this.radius+1,
				this.radius*2-2,this.radius*2-2);
		
		
	}
	
	
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
    public String toString() {
    	return "center=" + center + ", radius=" + radius;    }

	
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
		
	}

	
	public int compareTo(Object o) {
		if(o instanceof Circle) {
		return this.radius - ((Circle) o).radius;}
	
		return 0;
	}

	@Override
	public Shape clone() {
	    Circle c = new Circle(new Point(center.getX(), center.getY()), radius, isSelected(), getColor(), getInnerColor());
	    return c;
	}
	
	
	

}
