package draw;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.AddShape;
import controller.CommandManager;
import controller.ModelObserver;
import controller.ZAxisAction;
import controller.ZAxisCommand;
import geometrija.Circle;
import geometrija.Donut;
import geometrija.HexagonAdapter;
import geometrija.Line;
import geometrija.Point;
import geometrija.Rectangle;
import geometrija.Shape;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDraw extends JPanel implements ModelObserver{


	private FrmDraw frame;
	private ArrayList<Shape>shapes=new ArrayList<Shape>();
	private boolean clickedFirst;
	private Point p; 
	private Point p1;
	private int indexOfSelectedElement = -1;
	private CommandManager commandManager;
	private DrawingModel model;
	
	private JPopupMenu popup;
	private JMenuItem miToFront,miToBack,miBringToFront,miBringToBack;
	
	
	/**
	 * Create the panel.
	 */
	public PnlDraw(FrmDraw frame,DrawingModel model) {
		setBackground(Color.WHITE);
		this.frame=frame;
		this.model=model;
		this.commandManager = frame.getCommandManager();
		
		popup = new JPopupMenu();
		
		miToFront = new JMenuItem("To Front");
		miToBack = new JMenuItem("To Back");
		miBringToFront = new JMenuItem("Bring To Front");
		miBringToBack = new JMenuItem("Bring To Back");
		
		initPopupMenu();
		initMouseListener();
		
		
	}
		
	private void initPopupMenu(){
		
		popup=new JPopupMenu();
		miToFront = new JMenuItem("To Front");
		miToBack = new JMenuItem("To Back");
        miBringToFront = new JMenuItem("Bring To Front");
        miBringToBack = new JMenuItem("Bring To Back");
        
        popup.add(miToFront);
        popup.add(miToBack);
        popup.addSeparator();
        popup.add(miBringToFront);
        popup.add(miBringToBack);
        
        miToFront.addActionListener(ev->runZOrder(ZAxisAction.TO_FRONT));
        miToBack.addActionListener(ev->runZOrder(ZAxisAction.TO_BACK));
        miBringToFront.addActionListener(ev -> runZOrder(ZAxisAction.BRING_TO_FRONT));
		miBringToBack.addActionListener(ev -> runZOrder(ZAxisAction.BRING_TO_BACK));
	}
	private void initMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handlePopup(e);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }
	
	private void handleMouseClick(MouseEvent e) {
        Point p = new Point(e.getX(), e.getY());
        
        if (frame.getTglBtnSelect().isSelected()) {
            handleSelection(p);
        } else if (frame.getTglBtnPoint().isSelected()) {
            handlePointDrawing(p);
        } else if (frame.getTglBtnLine().isSelected()) {
            handleLineDrawing(e);
        } else if (frame.getTglBtnCircle().isSelected()) {
            handleCircleDrawing(e);
        } else if (frame.getTglBtnDonut().isSelected()) {
            handleDonutDrawing(e);
        } else if (frame.getTglBtnRectangle().isSelected()) {
            handleRectangleDrawing(e);
        } else if (frame.getTglBtnHexagon().isSelected()) {
            handleHexagonDrawing(e);
        }
    }
	 private void handlePointDrawing(Point p) {
	        p.setColor(frame.getActiveBorderColor());
	        commandManager.executeCommand(new AddShape(model, p, this));
	    }
	    
	    private void handleLineDrawing(MouseEvent e) {
	        if (!clickedFirst) {
	            p1 = new Point(e.getX(), e.getY());
	            clickedFirst = true;
	        } else {
	            Point p2 = new Point(e.getX(), e.getY());
	            Line l = new Line(p1, p2);
	            l.setColor(frame.getActiveBorderColor());
	            commandManager.executeCommand(new AddShape(model, l, this));
	            clickedFirst = false;
	        }
	    }
	    
	    private void handleCircleDrawing(MouseEvent e) {
	        Circle c = new Circle();
	        c.setCenter(new Point(e.getX(), e.getY()));
	        
	        DlgCircle dlg = new DlgCircle();
	        dlg.getTextX().setText(e.getX() + "");
	        dlg.getTextY().setText(e.getY() + "");
	        dlg.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
	        dlg.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
	        dlg.setVisible(true);
	        
	        if (dlg.isOk) {
	            try {
	                c.setRadius(Integer.parseInt(dlg.getTextRadius().getText()));
	                c.setColor(dlg.getBtnBorderColor().getBackground());
	                c.setInnerColor(dlg.getBtnInnerColor().getBackground());
	                commandManager.executeCommand(new AddShape(model, c, this));
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    private void handleDonutDrawing(MouseEvent e) {
	        Donut d = new Donut();
	        d.setCenter(new Point(e.getX(), e.getY()));
	        
	        DlgDonut dlg = new DlgDonut();
	        dlg.getTextX().setText(e.getX() + "");
	        dlg.getTextY().setText(e.getY() + "");
	        dlg.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
	        dlg.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
	        dlg.setVisible(true);
	        
	        if (dlg.isOk) {
	            try {
	                d.setRadius(Integer.parseInt(dlg.getTextRadius().getText()));
	                d.setInnerRadius(Integer.parseInt(dlg.getTextInnerRadius().getText()));
	                d.setColor(dlg.getBtnBorderColor().getBackground());
	                d.setInnerColor(dlg.getBtnInnerColor().getBackground());
	                commandManager.executeCommand(new AddShape(model, d, this));
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    private void handleRectangleDrawing(MouseEvent e) {
	        Rectangle r = new Rectangle();
	        r.setUpperLeftPoint(new Point(e.getX(), e.getY()));
	        
	        DlgRectangle dlg = new DlgRectangle();
	        dlg.getTextX().setText(e.getX() + "");
	        dlg.getTextY().setText(e.getY() + "");
	        dlg.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
	        dlg.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
	        dlg.setVisible(true);
	        
	        if (dlg.isOk) {
	            try {
	                r.setHeight(Integer.parseInt(dlg.getTextHeight().getText()));
	                r.setWidth(Integer.parseInt(dlg.getTextWidth().getText()));
	                r.setColor(dlg.getBtnBorderColor().getBackground());
	                r.setInnerColor(dlg.getBtnInnerColor().getBackground());
	                commandManager.executeCommand(new AddShape(model, r, this));
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    private void handleHexagonDrawing(MouseEvent e) {
	        DlgHexagon dlg = new DlgHexagon();
	        dlg.getTextX().setText(e.getX() + "");
	        dlg.getTextY().setText(e.getY() + "");
	        dlg.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
	        dlg.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
	        dlg.setVisible(true);
	        
	        if (dlg.isOk) {
	            try {
	                int x = Integer.parseInt(dlg.getTextX().getText());
	                int y = Integer.parseInt(dlg.getTextY().getText());
	                int r = Integer.parseInt(dlg.getTextR().getText());
	                Color border = dlg.getBtnBorderColor().getBackground();
	                Color inner = dlg.getBtnInnerColor().getBackground();
	                
	                HexagonAdapter h = new HexagonAdapter(x, y, r, border, inner, false);
	                commandManager.executeCommand(new AddShape(model, h, this));
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Invalid values", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	
	  private void handleSelection(Point p) {
	        int hitIndex = -1;
	        
	        for (int i = model.size() - 1; i >= 0; i--) {
	            if (model.getShape(i).contains(p.getX(), p.getY())) {
	                hitIndex = i;
	                break;
	            }
	        }
	        
	        if (hitIndex == -1) {
	            model.deselectAll();
	        } else {
	            Shape s = model.getShape(hitIndex);
	            s.setSelected(!s.isSelected());
	            model.notifyObservers();
	        }
	        
	        frame.selectionChanged(model.getSelectedCount());
	    }
	  
    
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		for (Shape s : model.getShapes()) {
            s.draw(g);
        }
	}
	
	@Override
	public void update() {
		repaint();
    }
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public int getIndexOfSelectedElement() {
		return indexOfSelectedElement;
	}

	public void setIndexOfSelectedElement(int indexOfSelectedElement) {
		this.indexOfSelectedElement = indexOfSelectedElement;
	}
	

	private void handlePopup(MouseEvent e) {
	    if (!e.isPopupTrigger()) return;
	    
	    selectAt(e.getX(), e.getY());
	    System.out.println("Selected index = " + model.getLastSelectedIndex());
	    updatePopupItems();

	    int selectedIndex = model.getLastSelectedIndex();
        if (selectedIndex != -1) {
            popup.show(PnlDraw.this, e.getX(), e.getY());
        }
	}
	
	private void runZOrder(ZAxisAction action) {
	    int idx = model.getLastSelectedIndex();
	    if (idx == -1) return;

	    ZAxisCommand cmd = new ZAxisCommand(model, idx, action, this);
	    frame.getCommandManager().executeCommand(cmd);

	    setIndexOfSelectedElement(cmd.getNewIndex());

	    frame.updateUndoRedoButtons(); 
	}
    private void selectAt(int x, int y) {
        model.deselectAll();
        
        for (int i = model.size() - 1; i >= 0; i--) {
            if (model.getShape(i).contains(x, y)) {
                model.getShape(i).setSelected(true);
                model.notifyObservers();
                break;
            }
        }
    }
	private void updatePopupItems() {
		  int idx = model.getLastSelectedIndex();
		    int size = model.size();

		    boolean enabled = idx != -1;

		    miToFront.setEnabled(enabled);
		    miToBack.setEnabled(enabled);
		    miBringToFront.setEnabled(enabled);
		    miBringToBack.setEnabled(enabled);
	}
	public int getSelectedCount() {
	    int c = 0;
	    for (Shape s : shapes) if (s.isSelected()) c++;
	    return c;
	}

	private int findAnySelectedIndex() {
	    for (int i = 0; i < shapes.size(); i++)
	        if (shapes.get(i).isSelected()) return i;
	    return -1;
	}
	
	
}
