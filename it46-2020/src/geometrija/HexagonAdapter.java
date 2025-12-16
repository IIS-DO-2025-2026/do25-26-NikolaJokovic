package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;


public class HexagonAdapter extends SurfaceShape{
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {}
	
	public HexagonAdapter(int x, int y, int r, Color borderColor, Color areaColor, boolean selected) {
		
		hexagon = new Hexagon(x,y,r);
		hexagon.setBorderColor(borderColor);
		hexagon.setAreaColor(areaColor);
		hexagon.setSelected(selected);
		
		setColor(borderColor);
		setInnerColor(areaColor);
		setSelected(selected);
	};
	
	public Hexagon getHexagon() { return hexagon;}
	
	@Override
	public void draw(Graphics g) {hexagon.paint(g);}
	
	@Override
	public boolean contains(int x, int y) {return hexagon.doesContain(x, y);}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	@Override
	public void setColor(Color color) {
		super.setColor(color);
		hexagon.setBorderColor(color);}
	
	@Override
	public void setInnerColor(Color areaColor) {
		super.setInnerColor(areaColor);
		hexagon.setAreaColor(areaColor);
	}
	
	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	
	@Override
	public void moveBy(int dx, int dy) {
        hexagon.setX(hexagon.getX() + dx);
        hexagon.setY(hexagon.getY() + dy);
    }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}	
	@Override
	public Shape clone() {
	    HexagonAdapter h = new HexagonAdapter(this.hexagon.getX(), this.hexagon.getY(),this.hexagon.getR(), getColor(), getInnerColor(),isSelected());
	    return h;
	}
	
};

