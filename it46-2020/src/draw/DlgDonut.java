package draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textX;
	private JTextField textY;
	private JTextField textRadius;
	private JTextField textInnerRadius;
	protected boolean isOk;
	public JButton okButton;
	private JButton btnBorderColor;
	private JButton btnInnerColor;

	
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DlgDonut() {
		setResizable(false);
		setTitle("Create a donut:");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			textX = new JTextField();
			GridBagConstraints gbc_textX = new GridBagConstraints();
			gbc_textX.insets = new Insets(0, 0, 5, 0);
			gbc_textX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textX.gridx = 7;
			gbc_textX.gridy = 1;
			contentPanel.add(textX, gbc_textX);
			textX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			lblY.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			textY = new JTextField();
			GridBagConstraints gbc_textY = new GridBagConstraints();
			gbc_textY.insets = new Insets(0, 0, 5, 0);
			gbc_textY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textY.gridx = 7;
			gbc_textY.gridy = 2;
			contentPanel.add(textY, gbc_textY);
			textY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			textRadius = new JTextField();
			GridBagConstraints gbc_textRadius = new GridBagConstraints();
			gbc_textRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textRadius.gridx = 7;
			gbc_textRadius.gridy = 3;
			contentPanel.add(textRadius, gbc_textRadius);
			textRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner Radius:");
			lblInnerRadius.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 4;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			textInnerRadius = new JTextField();
			GridBagConstraints gbc_textInnerRadius = new GridBagConstraints();
			gbc_textInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textInnerRadius.gridx = 7;
			gbc_textInnerRadius.gridy = 4;
			contentPanel.add(textInnerRadius, gbc_textInnerRadius);
			textInnerRadius.setColumns(10);
		}
		{
			btnBorderColor = new JButton("Border color");
			btnBorderColor.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			btnBorderColor.setForeground(new Color(0, 0, 0));
			btnBorderColor.setBackground(UIManager.getColor("Button.background"));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Odabir boje", btnBorderColor.getBackground());
					btnBorderColor.setBackground(newColor);
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnBorderColor.gridx = 7;
			gbc_btnBorderColor.gridy = 5;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			btnInnerColor = new JButton("Inner color");
			btnInnerColor.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			btnInnerColor.setBackground(UIManager.getColor("Button.background"));
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Odabir boje", btnBorderColor.getBackground());
					btnInnerColor.setBackground(newColor);
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.gridx = 7;
			gbc_btnInnerColor.gridy = 6;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(255, 0, 255), 2, true));
			buttonPane.setBackground(new Color(0, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Add");
				okButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						if(Integer.parseInt(getTextRadius().getText()) < Integer.parseInt(getTextInnerRadius().getText())) {
							JOptionPane.showMessageDialog(null, "radius must be greater than inner radius", "message", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
						isOk=true;
						setVisible(false);
					
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public JTextField getTextX() {
		return textX;
	}

	public void setTextX(JTextField textX) {
		this.textX = textX;
	}

	public JTextField getTextY() {
		return textY;
	}

	public void setTextY(JTextField textY) {
		this.textY = textY;
	}

	public JTextField getTextRadius() {
		return textRadius;
	}

	public void setTextRadius(JTextField textRadius) {
		this.textRadius = textRadius;
	}

	public JTextField getTextInnerRadius() {
		return textInnerRadius;
	}

	public void setTextInnerRadius(JTextField textInnerRadius) {
		this.textInnerRadius = textInnerRadius;
	}
}