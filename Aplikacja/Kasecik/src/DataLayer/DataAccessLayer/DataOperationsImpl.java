package DataLayer.DataAccessLayer;

import java.util.ArrayList;

import DataLayer.Data;
import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Type;
import DataLayer.Components.Video;

public class DataOperationsImpl implements IDataOperations {

	//obiekt przchowujący wszystkie dane
	Data data = new Data();
	
	@Override
	public boolean addRental(ArrayList<Video> videos, CustomerData customer) {
		try{
			Rental newOne = new Rental();
			newOne.setCustomer(customer);
			newOne.setVideos(videos);
			data.rentals.add(newOne);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean removeRental(Rental rental) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rental getRentalById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rental> getDepricatedRentals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeSurcharge(float rate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addVideo(Video video) {
		data.videos.add(video);
		return false;
	}

	@Override
	public boolean removeVideo(Video video) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editVideoById(Video video, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Video> getVideos(int id, String name, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Video> getAllVideos() {
		return data.videos;
	}

	@Override
	public ArrayList<Rental> getAllRentals() {
		return data.rentals;
	}
}
