package DataLayer.Components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Rental {
		private int id;
		private ArrayList<Video> videos = new ArrayList<Video>();
		private Calendar rentalDate;
		private Calendar rentalExpireDate;
		private CustomerData customer;
		
		public Rental() {
		}
		
		public Rental(String line) {
			String [] strArr = line.split(";");
			this.id = Integer.parseInt(strArr[0]);
			//zmienne do odczytu danych
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
			try {
				cal.setTime(sdf.parse(strArr[1]));
				this.rentalDate = cal;
				cal.setTime(sdf.parse(strArr[2]));
				this.rentalExpireDate = cal;
			} catch (ParseException e) {
			}
			//przypisanie klienta
			CustomerData customer = new CustomerData(Integer.parseInt(strArr[3]),strArr[4],strArr[5],strArr[6],strArr[7]);
			this.customer = customer;
			
			for(int i = 0 ; i < Integer.parseInt(strArr[8]) ;i++) {
				Video video = new Video(Integer.parseInt(strArr[9+i]),"","",0,0);
				videos.add(video);
			}
		}
		
		public Rental(ArrayList<Video> videos, Calendar rentalDate, Calendar rentalExpireDate, CustomerData customer) {
			super();
			this.videos = videos;
			this.rentalDate = rentalDate;
			this.rentalExpireDate = rentalExpireDate;
			this.customer = customer;
		}
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		public ArrayList<Video> getVideos() {
			return videos;
		}
		public void setVideos(ArrayList<Video> videos) {
			this.videos = videos;
		}
		public Calendar getRentalDate() {
			return rentalDate;
		}
		public void setRentalDate(Calendar rentalDate) {
			this.rentalDate = rentalDate;
		}
		public Calendar getRentalExpireDate() {
			return rentalExpireDate;
		}
		public void setRentalExpireDate(Calendar rentalExpireDate) {
			this.rentalExpireDate = rentalExpireDate;
		}
		public CustomerData getCustomer() {
			return customer;
		}
		public void setCustomer(CustomerData customer) {
			this.customer = customer;
		}
		public String toString() {
			return id+";"+customer.getName() +";"+ customer.getSurname() +";"+rentalExpireDate.getTime(); 
		}
		public String fullString() {
			String result = "";
			//dane wyporzyczenia
			result += id+";";
			result += rentalDate.getTime().toString()+";";
			result += rentalExpireDate.getTime().toString()+";";
			
			//przypisanie klienta
			result += customer.getIdNumber()+";";
			result += customer.getName()+";";
			result += customer.getSurname()+";";
			result += customer.getTelephoneNumber()+";";
			result += customer.getEmail()+";";
			
			//lista kaset
			result += videos.size()+";";
			for(Video video : videos) 
				result+=video.getId()+";";
			return result;
		}
		
		public String generateReceipt() {
			return "something";
		}
		
		public boolean equals(Rental rent) {
			if(this.id == rent.getId()) return true;
			return false;
		}
}
