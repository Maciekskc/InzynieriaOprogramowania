package BusinesLayer;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DataLayer.Components.Rental;
import DataLayer.Components.Video;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;
import PresentationLayer.Forms.CreateVideoDialog;
import PresentationLayer.Forms.RentalDialog;

class RentalController {
	private IDataOperations dtoData = new DataOperationsImpl();

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
	
	
	public void genetateReminders() {
		reminderSender.generateReminder();
	}

}
