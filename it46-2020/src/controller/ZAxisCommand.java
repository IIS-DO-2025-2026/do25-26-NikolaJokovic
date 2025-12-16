package controller;

import java.util.List;

import javax.swing.JComponent;

import geometrija.Shape;

public class ZAxisCommand implements Command {

	private final List<Shape> shapes;
	private final JComponent panel;
	private final ZAxisAction action;
	
	private int oldIndex;
	private int newIndex;
	
	public ZAxisCommand (List<Shape> shapes, int selectedIndex, ZAxisAction action, JComponent panel) {
		this.shapes=shapes;
		this.oldIndex= selectedIndex;
		this.action= action;
		this.panel=panel;
		
	}
	
	@Override
	public void execute() {
		if (oldIndex < 0 || oldIndex >= shapes.size()) return;

		int last = shapes.size() - 1;
		switch (action) {
			case TO_FRONT:
				if (oldIndex == last) return;
	                newIndex = oldIndex + 1;
	                break;
			case TO_BACK:
				if (oldIndex == 0) return;
				newIndex = oldIndex - 1;
				break;
			case BRING_TO_FRONT:
				if (oldIndex == last) return;
				newIndex = last;
				break;
			case BRING_TO_BACK:
				if (oldIndex == 0) return;
				newIndex = 0;
				break;
			default:
				return;
	        }

	        move(oldIndex, newIndex);
	        panel.repaint();
	}
	
	@Override
	public void unexecute() {
		if (newIndex < 0 || newIndex >= shapes.size()) return;
	        move(newIndex, oldIndex);
	        panel.repaint();
	    }
    private void move(int from, int to) {
        Shape s = shapes.remove(from);
        shapes.add(to, s);
    }

    public int getNewIndex() {
        return newIndex;
    }	
    
    @Override
    public String toLog() {
    	return "MOVED|" + action.name() + "|" + oldIndex + "|" + newIndex;
    }
    
	
}
