package PresentationLayer.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import DataLayer.Components.Video;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.awt.event.ActionEvent;

public class CreateVideoDialog extends JDialog implements ActionListener{

	public JPanel contentPanel = new JPanel();
	public JButton okButton;
	public JTextField textFieldId;
	public JTextField textFieldName;
	public JTextField textFieldDuration;
	public JTextField textFieldAmount;
	public JTextField textFieldType;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateVideoDialog dialog = new CreateVideoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateVideoDialog() {
		setBounds(100, 100, 354, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 130, 14);
		contentPanel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(10, 25, 130, 20);
		contentPanel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel labelDuration = new JLabel("Czas Trwania");
		labelDuration.setBounds(182, 11, 130, 14);
		contentPanel.add(labelDuration);
		
		textFieldDuration = new JTextField();
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(182, 25, 130, 20);
		contentPanel.add(textFieldDuration);
		
		JLabel labelName = new JLabel("Nazwa");
		labelName.setBounds(10, 56, 130, 14);
		contentPanel.add(labelName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(10, 70, 130, 20);
		contentPanel.add(textFieldName);
		
		JLabel labelAmount = new JLabel("Liczba Kaset");
		labelAmount.setBounds(182, 56, 130, 14);
		contentPanel.add(labelAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(182, 70, 130, 20);
		contentPanel.add(textFieldAmount);
		
		JLabel labelType = new JLabel("Typ");
		labelType.setBounds(10, 101, 130, 14);
		contentPanel.add(labelType);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(10, 115, 130, 20);
		contentPanel.add(textFieldType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	public Video createVideo() {
		return new Video(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldType.getText(),Integer.parseInt(textFieldDuration.getText()),Integer.parseInt(textFieldAmount.getText()));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Klasa bazowa");
	}
}
