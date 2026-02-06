package controller;

import java.util.List;

import javax.swing.JComponent;

import draw.DrawingModel;
import geometrija.Shape;

public class ZAxisCommand implements Command {

	private final DrawingModel model;
	private final JComponent panel;
	private final ZAxisAction action;
	
	private int oldIndex;
	private int newIndex;
	
	public ZAxisCommand (DrawingModel model, int selectedIndex, ZAxisAction action, JComponent panel) {
		this.model=model;
		this.oldIndex= selectedIndex;
		this.action= action;
		this.panel=panel;
		
	}
	
	@Override
	public void execute() {
		if (oldIndex < 0 || oldIndex >= model.size()) return;
        
        int last = model.size() - 1;
        newIndex = oldIndex;
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

		 if (newIndex != oldIndex) {
	            Shape shape = model.getShapes().remove(oldIndex);
	            model.getShapes().add(newIndex, shape);
	            model.notifyObservers(); 
	        }
	        
	        if (panel != null) {
	            panel.repaint();
	        }
	}
	
	@Override
	public void unexecute() {
		 if (newIndex < 0 || newIndex >= model.size()) return;
	        
	        Shape shape = model.getShapes().remove(newIndex);
	        model.getShapes().add(oldIndex, shape);
	        model.notifyObservers();  
	        
	        if (panel != null) {
	            panel.repaint();
	        }
	    }
    /*private void move(int from, int to) {
        Shape s = model.remove(from);
        shapes.add(to, s);
    }*/

    public int getNewIndex() {
        return newIndex;
    }	
    
    @Override
    public String toLog() {
    	return "MOVED|" + action.name() + "|" + oldIndex + "|" + newIndex;
    }
    
	
}
