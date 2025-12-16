package draw;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;


import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import Sort.FrmSort;
import draw.FrmDraw;
import stack.FrmStack;

import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMenu extends JFrame {

	private JPanel contentPane;
	public boolean isOK;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
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
	public FrmMenu() {
		
		setResizable(false);
		setTitle("IT 46-2020 Jokovic Nikola");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(255, 0, 255), 3));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(null);
		panelNorth.setBackground(new Color(0, 255, 255));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblHeader = new JLabel("CHOSE  AN  APP ");
		lblHeader.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 11));
		panelNorth.add(lblHeader);
		
		JPanel panel_center = new JPanel();
		panel_center.setBackground(new Color(0, 255, 255));
		contentPane.add(panel_center, BorderLayout.CENTER);
		GridBagLayout gbl_panel_center = new GridBagLayout();
		gbl_panel_center.columnWidths = new int[]{71, 141, 0, 89, 105, 0};
		gbl_panel_center.rowHeights = new int[]{21, 0, 0, 0, 0};
		gbl_panel_center.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_center.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_center.setLayout(gbl_panel_center);
		
		JButton btnDrawing = new JButton("DRAW");
		btnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDraw dlg=new FrmDraw();
				dlg.setVisible(true);
				dispose();
			}
		});
		
		
		btnDrawing.setFocusable(false);
		btnDrawing.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		GridBagConstraints gbc_btnDrawing = new GridBagConstraints();
		gbc_btnDrawing.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDrawing.anchor = GridBagConstraints.NORTH;
		gbc_btnDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnDrawing.gridx = 2;
		gbc_btnDrawing.gridy = 0;
		panel_center.add(btnDrawing, gbc_btnDrawing);
		
		JButton btnStack = new JButton("STACK");
		btnStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStack stack = new FrmStack();
				stack.setVisible(true);
				dispose();
			}
		});
		btnStack.setFocusable(false);
		btnStack.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		GridBagConstraints gbc_btnStack = new GridBagConstraints();
		gbc_btnStack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStack.insets = new Insets(0, 0, 5, 5);
		gbc_btnStack.gridx = 2;
		gbc_btnStack.gridy = 1;
		panel_center.add(btnStack, gbc_btnStack);
		
		JButton btnSort = new JButton("SORT");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmSort srt =new FrmSort();
				srt.setVisible(true);
				dispose();
			}
		});
		btnSort.setFocusable(false);
		btnSort.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		GridBagConstraints gbc_btnSort = new GridBagConstraints();
		gbc_btnSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSort.insets = new Insets(0, 0, 5, 5);
		gbc_btnSort.gridx = 2;
		gbc_btnSort.gridy = 2;
		panel_center.add(btnSort, gbc_btnSort);
		
		JPanel panel_south = new JPanel();
		FlowLayout fl_panel_south = (FlowLayout) panel_south.getLayout();
		fl_panel_south.setAlignment(FlowLayout.RIGHT);
		panel_south.setBackground(new Color(0, 255, 255));
		contentPane.add(panel_south, BorderLayout.SOUTH);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFocusable(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Wide Latin", Font.BOLD, 11));
		panel_south.add(btnExit);
	}

}
