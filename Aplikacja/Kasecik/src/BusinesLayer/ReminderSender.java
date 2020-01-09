package BusinesLayer;

import java.util.Calendar;

import DataLayer.Components.Rental;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

class ReminderSender {
	private IDataOperations dtoData;

	public ReminderSender(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	public boolean remindCondition(Rental rental) {
		Calendar currentDate = Calendar.getInstance();
		if(currentDate.after(rental.getRentalExpireDate())) return true;
		return false;
	}
	
	public void sendReminder(Rental rental){
		//metoda wysyłaniia powiadomienia
	}
	
	public void generateReminder() {
		for(Rental rent: dtoData.getRentals()) 
			if(remindCondition(rent)) sendReminder(rent);
		
	}
}
