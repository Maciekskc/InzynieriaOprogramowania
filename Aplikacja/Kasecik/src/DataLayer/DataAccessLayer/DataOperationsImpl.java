package DataLayer.DataAccessLayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Callable;

import DataLayer.Data;
import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Type;
import DataLayer.Components.Video;

public class DataOperationsImpl implements IDataOperations {

	//obiekt przchowuj�cy wszystkie dane
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
	
	//usu� podany w parametrze obiekt wypo�yczenia je�li istnieje 
	@Override
	public boolean removeRental(Rental rental) {
		for (Rental rent : data.rentals)
			if(rent.equals(rental)) {
				data.rentals.remove(rent);
				return true;
			}
		return false;
	}

	//zwraca wypo�yczenie o podanym indeksie
	@Override
	public Rental getRentalById(int id) {
		for(int i = 0 ; i < data.rentals.size() ; i ++) {
			if(data.rentals.get(i).getId() == id) return data.rentals.get(i);
		}
		return null;
	}

	//zwraca liste film�w kt�rych data oddania ju� min�a
	@Override
	public ArrayList<Rental> getDepricatedRentals() {
		ArrayList<Rental> depricated = new ArrayList<>();
		Calendar currentDate = Calendar.getInstance();
		
		for(Rental rent: data.rentals) {
			if(currentDate.after(rent.getRentalExpireDate())) {
				depricated.add(rent);
			}
		}
		
		return depricated;
	}

	//zmienia wsp�czynnik cebny wyporzyczenia na podany
	@Override
	public boolean changeSurcharge(float rate) {
		data.rate = rate;
		return true;
	}

	//dodaje wideo do bazy danych
	@Override
	public boolean addVideo(Video video) {
		boolean exist = false;
		for (Video v : data.videos)
			if(v.equals(video)) {
				exist = true;
				break;
			}
		if(exist) {
			data.videos.add(video);
			return true;
		}
		
		return false;
	}

	//usuwa kaset� pod warunkiem �e takowe istnieje
	@Override
	public boolean removeVideo(Video video) {
		for (Video v : data.videos)
			if(v.equals(video)) {
				data.videos.remove(video);
				return true;
			}
		return false;
	}

	//zmienia kaset� o podanym numerze id na nowe
	@Override
	public boolean editVideoById(Video video, int id) {
		for (Video v : data.videos)
			if(v.getId() == id) {
				v = video;
				return true;
			}
		return false;
	}

	//zwraca liste kaset spe�niaj�c� jryteria przeszukiwania
	@Override
	public ArrayList<Video> getVideos(int id, String name, String type) {
		ArrayList<Video> videos = new ArrayList<>();
		
		for(Video video: data.videos) {
			if(video.getId() == id	||	video.getName() == name	||	video.getType() == type)
				videos.add(video);
		}
		
		return videos;
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
