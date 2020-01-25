package DataLayer.DataAccessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Callable;

import DataLayer.Data;
import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;

public class DataOperationsImpl implements IDataOperations {

	//obiekt przchowuj¹cy wszystkie dane
	Data data = new Data();
	
	//dodaj obiekt wypo¿yczenia
	@Override
	public boolean addRental(ArrayList<Video> videos, CustomerData customer) {
		try{
			Rental newOne = new Rental();
			newOne.setRentalDate(Calendar.getInstance());
			Calendar date = newOne.getRentalDate();
			date.add(Calendar.DAY_OF_YEAR, 5);
			newOne.setRentalExpireDate(date);
			newOne.setCustomer(customer);
			newOne.setVideos(videos);
			newOne.setId(data.rentals.get(data.rentals.size()-1).getId()+1);
			data.rentals.add(newOne);
			createRentalBackUp();
		}catch(Exception e) {
			return false;
		}
		createRentalBackUp();
		return true;
	}
	
	
	
	//usuñ podany w parametrze obiekt wypo¿yczenia jeœli istnieje 
	@Override
	public boolean removeRental(Rental rental) {
		for (Rental rent : data.rentals)
			if(rent.equals(rental)) {
				for(Video list: rent.getVideos())
					for(Video video: data.videos)
						if(video.equals(list))
							video.setAmount(video.getAmount()+1);
				data.rentals.remove(rent);
				createRentalBackUp();
				return true;
			}
		return false;
	}

	//zwraca wypo¿yczenie o podanym indeksie
	@Override
	public Rental getRentalById(int id) {
		for(int i = 0 ; i < data.rentals.size() ; i ++) {
			if(data.rentals.get(i).getId() == id) return data.rentals.get(i);
		}
		return null;
	}


	//zmienia wspó³czynnik cebny wyporzyczenia na podany
	@Override
	public boolean changeSurcharge(float rate) {
		if(data.rate == rate) {
			return false;
		}
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
		if(!exist) {
			data.videos.add(video);
			createVideoBackUp();
			return true;
		}
		
		return false;
	}

	//usuwa kasetê pod warunkiem ¿e takowe istnieje
	@Override
	public boolean removeVideo(Video video) {
		for (Video v : data.videos)
			if(v.equals(video)) {
				System.out.println("Wykasowano id:"+v.getId());
				data.videos.remove(v);
				return true;
			}
			createVideoBackUp();
		return false;
	}

	//zmienia kasetê o podanym numerze id na nowe
	@Override
	public boolean editVideoById(Video video, int id) {
		for (int i = 0 ; i < data.videos.size() ; i ++)
			if(data.videos.get(i).getId() == id) {
				data.videos.get(i).setName(video.getName());
				data.videos.get(i).setType(video.getType());
				data.videos.get(i).setDuration(video.getDuration());
				data.videos.get(i).setAmount(video.getAmount());
				createVideoBackUp();
				return true;
			}
		return false;
	}


	@Override
	public ArrayList<Video> getAllVideos() {
		return data.videos;
	}

	@Override
	public ArrayList<Rental> getRentals() {
		return data.rentals;
	}
	
	public void createVideoBackUp() {
	      BufferedWriter writer = null;
	        try {
	            //create a temporary file
	            String path = "VideosBackUp.txt";
	            File logFile = new File(path);
	            
	            if (logFile.exists() && logFile.isFile())
	            {
	            	logFile.delete();
	            }
	            logFile.createNewFile();
	            
	            System.out.println("Utworzono plik z zapasowymi danymi" + logFile.getCanonicalPath());

	            writer = new BufferedWriter(new FileWriter(logFile));
	            for(Video video : data.videos) {
	            	writer.write(video.toString());
	            	writer.newLine();
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	}

	//tworzenie back upu wypo¿yczeñ
	public void createRentalBackUp() {
	      BufferedWriter writer = null;
	        try {
	            String path = "RentalsBackUp.txt";
	            File logFile = new File(path);
	            System.out.println("Utworzono plik z zapasowymi danymi" + logFile.getCanonicalPath());

	            writer = new BufferedWriter(new FileWriter(logFile));
	            for(Rental rental : data.rentals) {
	            	writer.write(rental.fullString());
	            	writer.newLine();
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e) {
	            	System.out.println("Napotkano problem podczas zamykania pliku");
	            }
	        }
	}
	
	@Override
	public float getRate() {
		return data.rate;
	}

	
}
