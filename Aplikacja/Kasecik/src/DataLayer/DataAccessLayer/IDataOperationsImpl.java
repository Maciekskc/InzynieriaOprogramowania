package DataLayer.DataAccessLayer;

import java.util.ArrayList;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Type;
import DataLayer.Components.Video;

public class IDataOperationsImpl implements IDataOperations {

	@Override
	public boolean addRental(Video video, CustomerData customer) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
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

}
