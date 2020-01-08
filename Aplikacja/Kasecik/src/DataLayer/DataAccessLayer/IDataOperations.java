package DataLayer.DataAccessLayer;

import java.util.ArrayList;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Type;
import DataLayer.Components.Video;

public interface IDataOperations {
	public boolean addRental(ArrayList<Video> videos, CustomerData customer);
	public boolean removeRental(Rental rental);
	public Rental getRentalById(int id);
	public ArrayList<Rental> getDepricatedRentals();
	public ArrayList<Rental> getAllRentals();
	public boolean changeSurcharge(float rate);
	public boolean addVideo(Video video);
	public boolean removeVideo(Video video);
	public boolean editVideoById(Video video, int id);
	public ArrayList<Video> getVideos(int id, String name,  String type);
	public ArrayList<Video> getAllVideos();
	
}
