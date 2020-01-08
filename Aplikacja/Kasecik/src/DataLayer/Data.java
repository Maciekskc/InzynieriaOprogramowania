package DataLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
			try {
				reader = new BufferedReader(new FileReader("videosBackUp.txt"));
				String line;
				while ((line = reader.readLine()) != null)
				{
					Video video = new Video(line);
					videos.add(video);
				}
				reader.close();
				
				reader = new BufferedReader(new FileReader("rentalsBackUp.txt"));
				while ((line = reader.readLine()) != null)
				{
					//dzielenie stringa na czesci
					String [] strArr = line.split(";");
					
					//tworzenie użytkownika z wydzielonych danych
					CustomerData customer = new CustomerData(Integer.parseInt(strArr[0]),strArr[1],strArr[2],strArr[3],strArr[4]);
					
					//odczytywanie dat
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date rentalDate = sdf.parse(strArr[5]);
					Calendar calendarRent = Calendar.getInstance();
					calendarRent.setTime(rentalDate);
					
					Date rentalExpireDate = sdf.parse(strArr[6]);
					Calendar calendarExpire = Calendar.getInstance();
					calendarExpire.setTime(rentalExpireDate);
					
					//odczytywanie listy wyporzyczonych indeksów
					ArrayList<Integer> videosId = new ArrayList<>();
					for(int i = 0 ; i < Integer.parseInt(strArr[7]) ; i++) {
						videosId.add(Integer.parseInt(strArr[8+i]));
					}
					//przetwarzanie na liste kaset
					ArrayList<Video> listVideos = new ArrayList<>();
					for(int i = 0 ; i < videosId.size() ; i++) {
						listVideos.add(videos.get(videosId.get(i)));
					}
					
					//tworzenie obiektu
					rentals.add(new Rental(listVideos, calendarRent, calendarExpire, customer));
				}
				reader.close();
			} catch (Exception e) {
				System.out.println("Bug");
			}
	}
}
