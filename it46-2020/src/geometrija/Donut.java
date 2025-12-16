package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle{
	
	private int innerRadius;
	
	
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius ) {
		super(center, radius);
		this.innerRadius=innerRadius;
	}
	public Donut(Point center,int radius, int innerRadius,boolean selected) {
		this(center,radius,innerRadius);
		setSelected(selected);
	}
	public Donut(Point center,int radius, int innerRadius,boolean selected,Color color) {
		this(center,radius,innerRadius  ,selected);
		this.setColor(color);
		
	}
	public Donut(Point center,int radius, int innerRadius,boolean selected,Color color,Color innerColor) {
		this(center,radius,innerRadius,selected,color);
		this.setInnerColor(innerColor);
	}
	public void draw(Graphics g) {
		
		 Graphics2D g2 = (Graphics2D) g;
		
		Ellipse2D outer = new Ellipse2D.Double(
				super.getCenter().getX()- super.getRadius(),
				super.getCenter().getY()- super.getRadius(),
				2* super.getRadius(),
				2*super.getRadius());
		Ellipse2D inner = new Ellipse2D.Double(
				super.getCenter().getX()- getInnerRadius(),
				super.getCenter().getY()- getInnerRadius(),
				2* getInnerRadius(),
				2* getInnerRadius());
		Area donutArea = new Area(outer);
		donutArea.subtract(new Area(inner));
		
		g2.setColor(getInnerColor());
		g2.fill(donutArea);
		
		g2.setColor(getColor());
		g2.draw(donutArea);
		
		if (isSelected()) {
	        Graphics2D g3 = (Graphics2D) g;
	        g2.setColor(Color.BLACK);
	        g2.draw(donutArea);
	    }
		}

	
	
	public double area () {
		return super.area()- innerRadius*innerRadius*Math.PI;	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni=(Donut) obj;
			if (this.innerRadius == pomocni.innerRadius &&
					this.getCenter().equals(pomocni.getCenter()) &&
							this.getRadius() == pomocni.getRadius()) {
				return true;
				
			} else {
				return false;}
			
		} else
		    {return false;}
	}
	
	public boolean contains(int x,int y ) {
		double dFromCenter = this.getCenter().distanca(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	public boolean contains(Point c) {
		double dFromCenter = this.getCenter().distanca(c.getX(), c.getY());
		return super.contains(c.getX(),c.getY()) && dFromCenter > innerRadius;
	}
	
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return super.toString() + ", inner radius" + innerRadius;
	}
    
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int) (this.area() - ((Donut)o).area());
		}
		return 0;
	}
	
	public Shape clone() {
	    Point centerClone = new Point(this.getCenter().getX(),this.getCenter().getY());

	    Donut d = new Donut();
	    d.setCenter(centerClone);
	    d.setRadius(this.getRadius());
	    d.setInnerRadius(this.getInnerRadius());
	    d.setColor(this.getColor());
	    d.setInnerColor(this.getInnerColor());
	    d.setSelected(this.isSelected());

	    return d;
	}	

}
