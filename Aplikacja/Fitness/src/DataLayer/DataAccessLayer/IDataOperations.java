package DataLayer.DataAccessLayer;

import java.util.ArrayList;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;

public interface IDataOperations {
	public boolean addRental(ArrayList<Video> videos, CustomerData customer);
	public boolean removeRental(Rental rental);
	public Rental getRentalById(int id);
	public ArrayList<Rental> getRentals();
	public boolean changeSurcharge(float rate);
	public boolean addVideo(Video video);
	public boolean removeVideo(Video video);
	public boolean editVideoById(Video video, int id);
	public ArrayList<Video> getAllVideos();
	public float getRate();
	
}
