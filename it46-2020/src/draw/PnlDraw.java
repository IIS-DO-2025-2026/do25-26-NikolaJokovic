package draw;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.AddShape;
import controller.CommandManager;
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

public class PnlDraw extends JPanel {


	private FrmDraw frame;
	private ArrayList<Shape>shapes=new ArrayList<Shape>();
	private boolean clickedFirst;
	private Point p; 
	private Point p1;
	private int indexOfSelectedElement = -1;
	private CommandManager commandManager;
	
	private JPopupMenu popup;
	private JMenuItem miToFront,miToBack,miBringToFront,miBringToBack;
	
	
	/**
	 * Create the panel.
	 */
	public PnlDraw(FrmDraw frame) {
		setBackground(Color.WHITE);
		this.frame=frame;
		this.commandManager = frame.getCommandManager();
		
		popup = new JPopupMenu();
		
		miToFront = new JMenuItem("To Front");
		miToBack = new JMenuItem("To Back");
		miBringToFront = new JMenuItem("Bring To Front");
		miBringToBack = new JMenuItem("Bring To Back");
		
		popup.add(miToFront);
		popup.add(miToBack);
		popup.addSeparator();
		popup.add(miBringToFront);
		popup.add(miBringToBack);
		
		miToFront.addActionListener(ev -> runZOrder(ZAxisAction.TO_FRONT));
		miToBack.addActionListener(ev -> runZOrder(ZAxisAction.TO_BACK));
		miBringToFront.addActionListener(ev -> runZOrder(ZAxisAction.BRING_TO_FRONT));
		miBringToBack.addActionListener(ev -> runZOrder(ZAxisAction.BRING_TO_BACK));
		
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
				 p = new Point(e.getX(),e.getY());
		
				 if (frame.getTglBtnSelect().isSelected()) {

					    int hitIndex = -1;

					    // nađi najviši shape ispod kursora
					    for (int i = shapes.size() - 1; i >= 0; i--) {
					        if (shapes.get(i).contains(p.getX(), p.getY())) {
					            hitIndex = i;
					            break;
					        }
					    }

					    if (hitIndex == -1) {
					        for (Shape s : shapes) s.setSelected(false);
					        indexOfSelectedElement = -1;
					    } else {
					        Shape s = shapes.get(hitIndex);
					        s.setSelected(!s.isSelected());
					        
					        if (s.isSelected()) {
					            indexOfSelectedElement = hitIndex;
					        } else {
					            if (indexOfSelectedElement == hitIndex) {
					                indexOfSelectedElement = findAnySelectedIndex();
					            }
					        }
					    }

					    frame.selectionChanged(getSelectedCount());
					    repaint();
					    return;
					}
				else if(frame.getTglBtnPoint().isSelected()){
					p.setColor(frame.getActiveBorderColor());
					frame.getCommandManager().executeCommand(new AddShape(shapes, p,PnlDraw.this));
					
				}
				else if(frame.getTglBtnLine().isSelected()) {
					
					Point drugiKlik;
				
					if(clickedFirst == false) {
						p1 = new Point(e.getX(),e.getY());
						clickedFirst = true;
					}
					else {
						drugiKlik = new Point(e.getX(),e.getY());
						Line l = new Line(p1,drugiKlik);
						clickedFirst = false;
						l.setColor(frame.getActiveBorderColor());
						frame.getCommandManager().executeCommand(new AddShape(shapes, l,PnlDraw.this));;
					}
				}
				else if(frame.getTglBtnCircle().isSelected()) {
					Circle c = new Circle();
					c.setCenter(new Point (e.getX(), e.getY()));
					
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getTextX().setText(e.getX() + "");
					dlgCircle.getTextY().setText(e.getY() + "");
					dlgCircle.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
					dlgCircle.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
					dlgCircle.setVisible(true);
					
					try {
						if(dlgCircle.isOk) {
							c.setRadius(Integer.parseInt(dlgCircle.getTextRadius().getText()));
							c.setColor(dlgCircle.getBtnBorderColor().getBackground());
							c.setInnerColor(dlgCircle.getBtnInnerColor().getBackground());
							frame.getCommandManager().executeCommand(new AddShape(shapes, c,PnlDraw.this));;
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "please enter valid value", "message", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				else if(frame.getTglBtnDonut().isSelected()) {
							
						Donut d = new Donut();
						d.setCenter(new Point (e.getX(), e.getY()));
						
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.getTextX().setText(e.getX() + "");
						dlgDonut.getTextY().setText(e.getY() + "");	
						dlgDonut.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
						dlgDonut.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
						dlgDonut.setVisible(true);
						try {
							if(dlgDonut.isOk) {
								d.setRadius(Integer.parseInt(dlgDonut.getTextRadius().getText()));
								d.setInnerRadius(Integer.parseInt(dlgDonut.getTextInnerRadius().getText()));
								d.setColor(dlgDonut.getBtnBorderColor().getBackground());
								d.setInnerColor(dlgDonut.getBtnInnerColor().getBackground());
								frame.getCommandManager().executeCommand(new AddShape(shapes, d,PnlDraw.this));
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "please enter valid value", "message", JOptionPane.INFORMATION_MESSAGE);
						}
					
				}
				else if(frame.getTglBtnRectangle().isSelected()) {
					
						Rectangle r = new Rectangle();
						r.setUpperLeftPoint(new Point(e.getX(),e.getY()));		
					
						DlgRectangle dlgRectangle = new DlgRectangle();
						dlgRectangle.getTextX().setText(e.getX() + "");
						dlgRectangle.getTextY().setText(e.getY() + "");
						dlgRectangle.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
						dlgRectangle.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
						dlgRectangle.setVisible(true);
						
						try {
							if(dlgRectangle.isOk) {
								r.setHeight(Integer.parseInt(dlgRectangle.getTextHeight().getText()));
								r.setWidth(Integer.parseInt(dlgRectangle.getTextWidth().getText()));
								r.setColor(dlgRectangle.getBtnBorderColor().getBackground());
								r.setInnerColor(dlgRectangle.getBtnInnerColor().getBackground());
								frame.getCommandManager().executeCommand(new AddShape(shapes, r,PnlDraw.this));
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "please enter valid value", "message", JOptionPane.INFORMATION_MESSAGE);
						}
				}
				else if(frame.getTglBtnHexagon().isSelected()) {
					
					DlgHexagon dlgHexagon = new DlgHexagon();
					
				    dlgHexagon.getTextX().setText(e.getX() + "");
				    dlgHexagon.getTextY().setText(e.getY() + "");
				    dlgHexagon.getBtnBorderColor().setBackground(frame.getActiveBorderColor());
					dlgHexagon.getBtnInnerColor().setBackground(frame.getActiveInnerColor());
				    dlgHexagon.setVisible(true);

				    if (dlgHexagon.isOk) {
				        try {
				            int x = Integer.parseInt(dlgHexagon.getTextX().getText());
				            int y = Integer.parseInt(dlgHexagon.getTextY().getText());
				            int r = Integer.parseInt(dlgHexagon.getTextR().getText());

				            Color border = dlgHexagon.getBtnBorderColor().getBackground();
				            Color inner  = dlgHexagon.getBtnInnerColor().getBackground();

				            HexagonAdapter h = new HexagonAdapter(x, y, r, border, inner, false);


				            frame.getCommandManager().executeCommand(new AddShape(shapes, h,PnlDraw.this));
				        } catch (Exception ex) {
				            JOptionPane.showMessageDialog(null, "Please enter valid values", "Error", JOptionPane.INFORMATION_MESSAGE);
				        }
				    }
				}
				//repaint();
			}
		
		
		});
	}
    
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext())
			it.next().draw(g); 
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

	    updatePopupItems();

	    if (indexOfSelectedElement != -1) {
	        popup.show(PnlDraw.this, e.getX(), e.getY());
	    }
	}
	
	private void runZOrder(ZAxisAction action) {
	    int idx = getIndexOfSelectedElement();
	    if (idx == -1) return;

	    ZAxisCommand cmd = new ZAxisCommand(shapes, idx, action, this);
	    frame.getCommandManager().executeCommand(cmd);

	    setIndexOfSelectedElement(cmd.getNewIndex());

	    frame.updateUndoRedoButtons(); 
	}
	private void selectAt(int x, int y) {
	    for (Shape s : shapes) s.setSelected(false);
	    indexOfSelectedElement = -1;

	    for (int i = shapes.size() - 1; i >= 0; i--) {
	        if (shapes.get(i).contains(x, y)) {
	            shapes.get(i).setSelected(true);
	            indexOfSelectedElement = i;
	            break;
	        }
	    }
	    repaint();
	}
	private void updatePopupItems() {
	    int idx = indexOfSelectedElement;
	    int last = shapes.size() - 1;

	    boolean has = idx != -1;
	    miToFront.setEnabled(has && idx < last);
	    miBringToFront.setEnabled(has && idx < last);

	    miToBack.setEnabled(has && idx > 0);
	    miBringToBack.setEnabled(has && idx > 0);
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
