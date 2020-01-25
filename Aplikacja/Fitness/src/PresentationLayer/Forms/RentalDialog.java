package PresentationLayer.Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Video;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class RentalDialog extends JDialog implements ActionListener {

	public JPanel contentPanel = new JPanel();
	public JButton okButton;
	public JTextField textFieldId;
	public JTextField textFieldName;
	public JTextField textFieldTelephone;
	public JTextField textFieldSurname;
	public JTextField textFieldEmail;
	public JComboBox<Video> comboBoxVideos = new JComboBox<Video>();
	
	private ArrayList<Video> allVideos = new ArrayList<>();
	public ArrayList<Video> videos = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RentalDialog dialog = new RentalDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalDialog(ArrayList<Video> allVideos) {
		this.allVideos = allVideos;
		System.out.println("Dostêpne id filmów");
		for(Video video: allVideos)
			System.out.println(video.getId());
		setBounds(100, 100, 341, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("Numer Identyfikacyjny");
		lblId.setBounds(10, 11, 130, 14);
		contentPanel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(10, 25, 130, 20);
		contentPanel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel labelTelephone = new JLabel("Numer Telefonu");
		labelTelephone.setBounds(182, 11, 130, 14);
		contentPanel.add(labelTelephone);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(182, 25, 130, 20);
		contentPanel.add(textFieldTelephone);
		
		JLabel labelName = new JLabel("Imie");
		labelName.setBounds(10, 56, 130, 14);
		contentPanel.add(labelName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(10, 70, 130, 20);
		contentPanel.add(textFieldName);
		
		JLabel labelSurname = new JLabel("Nazwisko");
		labelSurname.setBounds(182, 56, 130, 14);
		contentPanel.add(labelSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(182, 70, 130, 20);
		contentPanel.add(textFieldSurname);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(10, 101, 130, 14);
		contentPanel.add(labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 115, 130, 20);
		contentPanel.add(textFieldEmail);
		
		JLabel labelChooseVideos = new JLabel("Wybieanie Fimów");
		labelChooseVideos.setBounds(182, 101, 130, 14);
		contentPanel.add(labelChooseVideos);
		
		JButton btnChoseVideos = new JButton("Otwórz Panel Wyboru");
		btnChoseVideos.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setBounds(100, 100, 160, 120);
				dialog.getContentPane().setLayout(null);
				JLabel lblId = new JLabel("Id Filmu");
				lblId.setBounds(10, 11, 130, 14);
				dialog.getContentPane().add(lblId);
				
				textFieldId = new JTextField();
				textFieldId.setBounds(10, 25, 130, 20);
				dialog.getContentPane().add(textFieldId);
				textFieldId.setColumns(10);
				
				JButton btnNewButton = new JButton("Dodaj");
				btnNewButton.setBounds(34, 52, 89, 23);
				btnNewButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
							if(addVideo(Integer.parseInt(textFieldId.getText()))) {								
								JOptionPane.showMessageDialog(new Frame(), "Kaseta zosta³a dodana");
							}else {
								JOptionPane.showMessageDialog(new Frame(), "Nie uda³o siê dodaæ kasety");
							}
					}
				});
				dialog.getContentPane().add(btnNewButton);
				
		        dialog.show();
			}
		});
		btnChoseVideos.setBounds(175, 115, 137, 59);
		contentPanel.add(btnChoseVideos);
		
		JLabel labelComboBoxVideos = new JLabel("Wybrane Pozycje");
		labelComboBoxVideos.setBounds(10, 160, 130, 14);
		contentPanel.add(labelComboBoxVideos);
		
		comboBoxVideos = new JComboBox<Video>();
		comboBoxVideos.setBounds(10, 185, 302, 34);
		contentPanel.add(comboBoxVideos);
		
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
	public boolean addVideo(int id) {
		boolean isNew = true;
		Video toAdd = new Video();
		for (Video v : allVideos)
			if(v.getId() == id) {
				toAdd=v;
				for (Video found : videos) 
					if(found.equals(v)){	
						isNew = false;
					}
			}
		if(isNew && toAdd.getAmount()>0) {
			videos.add(toAdd);
			comboBoxVideos.addItem(toAdd);
			return true;
		}
		return false;
	}
	
	public CustomerData createCustomer() {
		return new CustomerData(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldSurname.getText(),textFieldTelephone.getText(),textFieldEmail.getText());		
	}
	
	public ArrayList<Video> getSelectedVideos(){
		return videos;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Klasa Bazowa");
		
	}
}
