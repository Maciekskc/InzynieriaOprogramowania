package DataLayer;

import java.util.ArrayList;

import DataLayer.Components.Rental;
import DataLayer.Components.Video;

public class Data {
	private ArrayList<Video> videos;
	private ArrayList<Rental> rentals;
	private float rate = 1.99F;
	
	public Data() {
		
	}
	public Data(ArrayList<Video> videos, ArrayList<Rental> rentals, float rate) {
		super();
		this.videos = videos;
		this.rentals = rentals;
		this.rate = rate;
	}
	
}
