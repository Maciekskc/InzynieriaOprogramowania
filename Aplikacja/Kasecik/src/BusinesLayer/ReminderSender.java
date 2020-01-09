package BusinesLayer;

import DataLayer.Components.Rental;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

class ReminderSender {
	private IDataOperations dtoData = new DataOperationsImpl();

	public ReminderSender(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	public boolean remindCondition(Rental rental) {
		return false;
	}
	
	public void sendReminder(Rental rental){
		
	}
	
	public void generateReminder() {
		for(Rental rent: dtoData.getRentals()) 
			if(remindCondition(rent)) sendReminder(rent);
		
	}
}
