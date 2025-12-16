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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.Font;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textStartPointX;
	private JTextField textStartPointY;
	private JTextField textEndPointX;
	private JTextField textEndPointY;
	protected boolean isOk;
	private JButton btnBorderColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setResizable(false);
		setTitle("Create a line:");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPointX = new JLabel("X start point:");
			lblStartPointX.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblStartPointX = new GridBagConstraints();
			gbc_lblStartPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointX.gridx = 1;
			gbc_lblStartPointX.gridy = 1;
			contentPanel.add(lblStartPointX, gbc_lblStartPointX);
		}
		{
			textStartPointX = new JTextField();
			GridBagConstraints gbc_textStartPointX = new GridBagConstraints();
			gbc_textStartPointX.insets = new Insets(0, 0, 5, 0);
			gbc_textStartPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textStartPointX.gridx = 7;
			gbc_textStartPointX.gridy = 1;
			contentPanel.add(textStartPointX, gbc_textStartPointX);
			textStartPointX.setColumns(10);
		}
		{
			JLabel lblStartPointY = new JLabel("Y start point:");
			lblStartPointY.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblStartPointY = new GridBagConstraints();
			gbc_lblStartPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointY.gridx = 1;
			gbc_lblStartPointY.gridy = 2;
			contentPanel.add(lblStartPointY, gbc_lblStartPointY);
		}
		{
			textStartPointY = new JTextField();
			GridBagConstraints gbc_textStartPointY = new GridBagConstraints();
			gbc_textStartPointY.insets = new Insets(0, 0, 5, 0);
			gbc_textStartPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textStartPointY.gridx = 7;
			gbc_textStartPointY.gridy = 2;
			contentPanel.add(textStartPointY, gbc_textStartPointY);
			textStartPointY.setColumns(10);
		}
		{
			JLabel lblEndPointX = new JLabel("X end point: ");
			lblEndPointX.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblEndPointX = new GridBagConstraints();
			gbc_lblEndPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointX.gridx = 1;
			gbc_lblEndPointX.gridy = 3;
			contentPanel.add(lblEndPointX, gbc_lblEndPointX);
		}
		{
			textEndPointX = new JTextField();
			GridBagConstraints gbc_textEndPointX = new GridBagConstraints();
			gbc_textEndPointX.insets = new Insets(0, 0, 5, 0);
			gbc_textEndPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textEndPointX.gridx = 7;
			gbc_textEndPointX.gridy = 3;
			contentPanel.add(textEndPointX, gbc_textEndPointX);
			textEndPointX.setColumns(10);
		}
		{
			JLabel lblEndPointY = new JLabel("Y end point:");
			lblEndPointY.setFont(new Font("Wide Latin", Font.PLAIN, 11));
			GridBagConstraints gbc_lblEndPointY = new GridBagConstraints();
			gbc_lblEndPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointY.gridx = 1;
			gbc_lblEndPointY.gridy = 4;
			contentPanel.add(lblEndPointY, gbc_lblEndPointY);
		}
		{
			textEndPointY = new JTextField();
			GridBagConstraints gbc_textEndPointY = new GridBagConstraints();
			gbc_textEndPointY.insets = new Insets(0, 0, 5, 0);
			gbc_textEndPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textEndPointY.gridx = 7;
			gbc_textEndPointY.gridy = 4;
			contentPanel.add(textEndPointY, gbc_textEndPointY);
			textEndPointY.setColumns(10);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(255, 0, 255), 2, true));
			buttonPane.setBackground(new Color(0, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Edit");
				okButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk=true;
						setVisible(false);
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

	public JTextField getTextStartPointX() {
		return textStartPointX;
	}

	public void setTextStartPointX(JTextField textStartPointX) {
		this.textStartPointX = textStartPointX;
	}

	public JTextField getTextStartPointY() {
		return textStartPointY;
	}

	public void setTextStartPointY(JTextField textStartPointY) {
		this.textStartPointY = textStartPointY;
	}

	public JTextField getTextEndPointX() {
		return textEndPointX;
	}

	public void setTextEndPointX(JTextField textEndPointX) {
		this.textEndPointX = textEndPointX;
	}

	public JTextField getTextEndPointY() {
		return textEndPointY;
	}

	public void setTextEndPointY(JTextField textEndPointY) {
		this.textEndPointY = textEndPointY;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}
	
}