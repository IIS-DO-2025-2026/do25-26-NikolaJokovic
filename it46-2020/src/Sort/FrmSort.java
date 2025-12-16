package Sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import geometrija.Rectangle;
import geometrija.Point;

import java.awt.Color;
import javax.swing.border.LineBorder;

import draw.FrmMenu;

import java.awt.Font;



public class FrmSort extends JFrame {

	private JPanel contentPane;
	private ArrayList <Rectangle> sortList=new ArrayList<Rectangle>();
	private DefaultListModel<Rectangle> dlm= new DefaultListModel<Rectangle>();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setTitle("IT 46-2020 Jokovic Nikola");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCentar = new JPanel();
		contentPane.add(pnlCentar, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCentar = new GridBagLayout();
		gbl_pnlCentar.columnWidths = new int[]{0, 0};
		gbl_pnlCentar.rowHeights = new int[]{0, 0};
		gbl_pnlCentar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlCentar.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCentar.setLayout(gbl_pnlCentar);
		
		JScrollPane scrlRectangle = new JScrollPane();
		GridBagConstraints gbc_scrlRectangle = new GridBagConstraints();
		gbc_scrlRectangle.fill = GridBagConstraints.BOTH;
		gbc_scrlRectangle.gridx = 0;
		gbc_scrlRectangle.gridy = 0;
		pnlCentar.add(scrlRectangle, gbc_scrlRectangle);
		
		JList<Rectangle> listRectangle = new JList<Rectangle>();
		listRectangle.setBorder(new LineBorder(new Color(255, 0, 255), 2, true));
		scrlRectangle.setViewportView(listRectangle);
		listRectangle.setModel(dlm);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(0, 255, 255));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSort dlgSort=new DlgSort();
				dlgSort.ok=false;
				dlgSort.setVisible(true);
				
				if(dlgSort.ok) 
				{
					try {
						int x = Integer.parseInt(dlgSort.getTxtX().getText());
						int y= Integer.parseInt(dlgSort.getTxtY().getText());
						int width= Integer.parseInt(dlgSort.getTxtWidth().getText());
						int height= Integer.parseInt(dlgSort.getTxtHeight().getText());
						
					
					Rectangle r=new Rectangle(new Point(x,y),width,height);
					dlm.add(0,r);
					} catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "You entered wrong data type!","Error!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		
		pnlSouth.add(btnAdd);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(dlm.isEmpty()){
					JOptionPane.showMessageDialog(null, "The list is empty","Error!",JOptionPane.ERROR_MESSAGE);
				}
				Rectangle temp;
				
				for (int i = 0; i < dlm.getSize(); i++) {
					sortList.add(dlm.getElementAt(i));
				}
				int length = sortList.size();
				
				for(int i = 1; i < length;i++){
					for(int j = 0; j<length-1;j++){
						if(sortList.get(j).compareTo(sortList.get(j+1))>0){
							temp=sortList.get(j);
							sortList.set(j, sortList.get(j+1));
							sortList.set(j+1, temp);
						}
					}
				}
				
				dlm.removeAllElements(); 
				for(int i = 0; i<sortList.size(); i++)
				{
					dlm.addElement(sortList.get(i));
				}
				
			sortList.clear();
			}
		});
		pnlSouth.add(btnSort);
		
		JButton btnBck = new JButton("Back to menu");
		btnBck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMenu frmMenu= new FrmMenu();
				frmMenu.setVisible(true);
				dispose();
			}
		});
		btnBck.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		pnlSouth.add(btnBck);
	}
		
		
}
