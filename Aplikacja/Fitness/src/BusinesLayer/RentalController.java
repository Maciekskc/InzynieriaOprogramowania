package BusinesLayer;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import DataLayer.Components.Rental;
import DataLayer.Components.Video;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;
import PresentationLayer.Forms.RentalDialog;

class RentalController {
	private IDataOperations dtoData;

	private ReminderSender reminderSender;

	public RentalController(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
		reminderSender = new ReminderSender(this.dtoData);
	}

	public boolean addRental() {
		JDialog dialog = new RentalDialog(dtoData.getAllVideos()) {
			public void actionPerformed(ActionEvent e) {
				if(dtoData.addRental(getSelectedVideos(),createCustomer())) {	
					ArrayList<Video> videos = getSelectedVideos();
					for(Video list: videos)
						for(Video video: dtoData.getAllVideos())
							if(video.equals(list))
								video.setAmount(video.getAmount()-1);
					JOptionPane.showMessageDialog(new Frame(), "Dodano Wypo�yczenie");
				}else {
					JOptionPane.showMessageDialog(new Frame(), "Nie uda�o si� doda� wypo�yczenia");
				}
			}
        };
        dialog.show();
        return true;
	}

	public boolean removeRental() {
		ArrayList<Rental> rental = getRentals();
		JDialog dialog = new JDialog() ;
		dialog.setLayout(null);
		dialog.setBounds(100, 100, 220, 130);
			JComboBox comboBoxVideos = new JComboBox<Video>();
			comboBoxVideos.setBounds(10, 10, 180, 20);
			for(int i = 0 ; i < rental.size() ; i++)
				comboBoxVideos.addItem(rental.get(i));
		dialog.add(comboBoxVideos);
		
		JRadioButton rdbtnWygenerujPotwierdzenie = new JRadioButton("Potwierdzenie");
		rdbtnWygenerujPotwierdzenie.setBounds(10, 60, 109, 23);
		dialog.add(rdbtnWygenerujPotwierdzenie);
		
		JButton buttonRemove = new JButton("Zwr��");
		buttonRemove.setBounds(50, 40, 100, 20);
		buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//drukowanie potwierdzeia
				if(rdbtnWygenerujPotwierdzenie.isSelected()) {
					JDialog reciptDialog = new JDialog() ;
					reciptDialog.setLayout(null);
					reciptDialog.setBounds(100, 100, 400, 400);
					
					JTextArea textAreaRecipt = new JTextArea();
					textAreaRecipt.setText("zrob opis");
					textAreaRecipt.setBounds(10, 10, 350, 380);
					reciptDialog.add(textAreaRecipt);
					
					Rental rental = (Rental)comboBoxVideos.getSelectedItem();
					String receipt = "\t///Potwierdzenie///\n";
					receipt+="U�ytkownik: "+rental.getCustomer().getName()+rental.getCustomer().getSurname()+"\n";
					receipt+="Numer Telefonu: "+rental.getCustomer().getTelephoneNumber()+"\n";
					receipt+="Adres E-mail: "+rental.getCustomer().getEmail()+"\n"+"\n";
					receipt+="Data Wypo�yczenia: "+rental.getRentalDate().getTime()+"\n";
					receipt+="Data Zwrotu: "+rental.getRentalExpireDate().getTime()+"\n\n";
					receipt+="Lista Film�w: \n";
					for(Video video : rental.getVideos())
						receipt+="\t"+video.getName()+"\n";
					receipt+="\nDop�ata:\t"+calculateSurcharge(rental)+"z�";
					textAreaRecipt.setText(receipt);
					reciptDialog.show();
				}
				
				if(dtoData.removeRental((Rental)comboBoxVideos.getSelectedItem()))
					JOptionPane.showMessageDialog(new Frame(), "Pomy�lnie usuni�to obiekt");
			}
		});
		dialog.add(buttonRemove);
		dialog.show();
		return true;
	}

	public ArrayList<Rental> getRentals(){
		return dtoData.getRentals();		
	}
	
	public ArrayList<Rental> getDepricatedRentals(){
		ArrayList<Rental> depricated = new ArrayList<>();
		Calendar currentDate = Calendar.getInstance();
		
		for(Rental rent: dtoData.getRentals()) {
			if(currentDate.after(rent.getRentalExpireDate())) {
				depricated.add(rent);
			}
		}
		
		return depricated;	
	}
	
	
	public Rental getRentalById() {
		int id=0;
		for(Rental rental: getRentals()) 
			if(rental.getId() == id)
				return rental;
		return null;
	}
	
	public void sendReminders() {
		reminderSender.generateReminder();
	}

	//obliczanie dop�aty do rachunku
	public float calculateSurcharge(Rental rental) {
		Calendar currentDate = Calendar.getInstance();
		float surcharge = dtoData.getRate() * (float)currentDate.compareTo(rental.getRentalExpireDate());
		if(surcharge<0)return 0;
		return surcharge;
				
	}
}
