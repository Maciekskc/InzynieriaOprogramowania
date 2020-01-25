package BusinesLayer;


import java.util.ArrayList;
import java.util.Calendar;


import DataLayer.Components.Rental;
import DataLayer.Components.Video;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

public class Controller {
	//obiekt interfejsi dostêpu do danych
	private IDataOperations dtoData = new DataOperationsImpl();

	
	//obiekty kontrolerów poszczególnych operacji
	private ParameterMenager parameterMenager = new ParameterMenager(dtoData);
	private VideoController videoController = new VideoController(dtoData);
	private RentalController rentalController =  new RentalController(dtoData);
	
	public Controller() {
		
	}	
	
	public void addVideo() {
		videoController.addVideo();
	}


	public ArrayList<Video> getAllVideos() {
		return videoController.getAllVideos();
	}

	public void editVideo() {
		videoController.editVideoById();
		
	}

	public void deleteVideo() {
		videoController.removeVideo();
	}

	public void addRental() {
		rentalController.addRental();		
	}

	public void removeRental() {
		rentalController.removeRental();
	}

	public ArrayList<Rental> getRentals() {
		return rentalController.getRentals();
	}
	
	public ArrayList<Rental> getDepricatedRentals() {
		return rentalController.getDepricatedRentals();
	}
	
	public void changeRate() {
		float rate = 3.0F;
		parameterMenager.changeRate(rate);
	}

	public void sendReminders() {
		rentalController.sendReminders();
		
	}
}
