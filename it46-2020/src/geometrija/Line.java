package geometrija;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	
	private Point startPoint;
	private Point endPoint;
    
    
    public double lenght() {
    	return startPoint.distanca(endPoint.getX(), endPoint.getY());
    }
    
    public Line() {
		
	}
	
	public Line(Point startPoint,Point endPoint) {
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint,endPoint);
		setSelected(selected);
	}
	public Line(Point startPoint, Point endPoint, boolean selected,Color color) {
		this(startPoint,endPoint,selected);
		this.setColor(color);
	}
    
    public boolean equals (Object obj) {
    	if(obj instanceof Line ) {
    		Line pomocna= (Line) obj;
    		if(this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint)) {
    			return true;}
    		else {
    			return false;}}
    		else {
    			return false;}
    }
    
    public boolean contains(int x, int y) {
    	if((startPoint.distanca(x, y) + endPoint.distanca(x, y)) - lenght() <=0.05 ) {
    		return true;}
    	else {
    		return false;}
    }
    
    public void draw(Graphics g) {
    	g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
	}
    
    
    
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		return  startPoint.toString() +"-->" + endPoint.toString() ; 
	}

	
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return (int) (this.lenght()-((Line)o).lenght());
		}
		return 0;
	}
	@Override
	public Shape clone() {
		Point sp = new Point(this.startPoint.getX(), this.startPoint.getY());
	    Point ep = new Point(this.endPoint.getX(), this.endPoint.getY());

	    Line l = new Line(sp, ep, this.isSelected(), this.getColor());
	    return l;
	}
	
	
    

}
