package DataLayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;

public class Data {
	public ArrayList<Video> videos = new ArrayList<>();
	public ArrayList<Rental> rentals = new ArrayList<>();
	public float rate = 1.99F;
	
	public Data() {
		loadBackUp();
	}
	
	public Data(ArrayList<Video> videos, ArrayList<Rental> rentals, float rate) {
		super();
		this.videos = videos;
		this.rentals = rentals;
		this.rate = rate;
	}
	
	private void loadBackUp() {
		    BufferedReader reader;
		    String line;
		    
			try {
		        File file = new File("VideosBackUp.txt");
				reader = new BufferedReader(new FileReader(file));
				while ((line = reader.readLine()) != null)
				{
					//System.out.println("obiekt wygl�da nast�puj�co:"+line);
					Video video = new Video(line);
					videos.add(video);
				}
				reader.close();
			}catch(Exception e) {
				System.out.println("Nie Uda�o si� za�adowa� wszystkich danych o filmach:"+e.getMessage());
			}
			
			try {
				File file = new File("RentalsBackUp.txt");
				reader = new BufferedReader(new FileReader(file));
				while ((line = reader.readLine()) != null)
				{
					//System.out.println("obiekt wygl�da nast�puj�co:"+line);
					Rental rental = new Rental(line);
					for(Video video : rental.getVideos())
						for(Video fromAll : videos) 
							if(video.getId() == fromAll.getId()) {
								video.setName(fromAll.getName());
								video.setDuration(fromAll.getDuration());
								video.setAmount(fromAll.getAmount());
								video.setType(fromAll.getType());
							}
								
					rentals.add(rental);
				}
				reader.close();
			} catch (Exception e) {
				System.out.println("Nie Uda�o si� za�adowa� wszystkich danych o wyporzyczeniach");
			}
	}
}
