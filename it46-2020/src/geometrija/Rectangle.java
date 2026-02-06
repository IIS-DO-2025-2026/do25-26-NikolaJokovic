package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rectangle extends SurfaceShape implements Serializable{

	public int width;
	public int height;
	private Point upperLeftPoint;
	
	
	
	public Rectangle(){
	}
	
	public Rectangle(Point upperLeftPoint,int width, int height) {
		this.upperLeftPoint=upperLeftPoint;
		this.height=height;
		this.width=width;
		
	}
	
	public Rectangle (Point upperLeftPoint,int width, int height, boolean selected) {
		this(upperLeftPoint,width,height);
		setSelected(selected);
	}
	public Rectangle (Point upperLeftPoint,int width, int height, boolean selected, Color color) {
		this(upperLeftPoint,width,height,selected);
		this.setColor(color);
		
	}
	public Rectangle (Point upperLeftPoint,int width, int height, boolean selected,Color color,Color innerColor) {
		this(upperLeftPoint,width,height,selected,color);
		this.setInnerColor(innerColor);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(),this.upperLeftPoint.getY(), this.width, this.height);
		this.fill(g);
		if(isSelected()) {
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(Color.black);
			g.drawRect(this.upperLeftPoint.getX(),this.upperLeftPoint.getY(), this.width +1, this.height+1);
		}
		
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.upperLeftPoint.getY()+1, this.width-1, this.height-1);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle ) {
			Rectangle pomocni = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocni.upperLeftPoint) && 
					this.width == pomocni.width &&
					this.height == pomocni.height) {
			    return true;}
			else {
				return false;}
			}
			
		else {
				return false;}
	}
	
	public boolean contains(int x, int y) {
		if (this.upperLeftPoint.getX() <=x &&
				this.upperLeftPoint.getY() <=y &&
				x<= this.upperLeftPoint.getX() + this.width &&
				y<= this.upperLeftPoint.getY() + this.height) {
			return true;
		}else {
			return false;}
		}
	
	public boolean contains(Point p) {
		if (this.upperLeftPoint.getX() <=p.getX() &&
				this.upperLeftPoint.getY() <=p.getY() &&
				p.getX()<= this.upperLeftPoint.getX() + this.width &&
				p.getY()<= this.upperLeftPoint.getY() + this.height) {
			return true;
		}else {
			return false;}
		}
	
	
	public int area () {
		return width * height;
	}
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", width: " + width + ", height: " + height; 
	}

	
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
		
	}

    
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return (this.area() - ((Rectangle)o).area());
		}
		return 0;
	}
	@Override
	public Shape clone() {
		Rectangle r = new Rectangle();

	    r.setUpperLeftPoint(new Point(upperLeftPoint.getX(),upperLeftPoint.getY()));
	    r.setWidth(width);
	    r.setHeight(height);
	    r.setColor(getColor());
	    r.setInnerColor(getInnerColor());
	    r.setSelected(isSelected());

	    return r;
	}
	

	
}
