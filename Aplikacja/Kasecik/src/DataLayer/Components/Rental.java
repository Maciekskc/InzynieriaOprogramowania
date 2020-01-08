package DataLayer.Components;

import java.util.ArrayList;
import java.util.Calendar;

public class Rental {
		private int id;
		private ArrayList<Video> videos = new ArrayList();
		private Calendar rentalDate;
		private Calendar rentalExpireDate;
		private CustomerData customer;
		
		public Rental() {
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
			return rentalDate+";"+rentalExpireDate+";"+customer.getName()+";"+customer.getSurname();
		}
		public String generateReceipt() {
			return "something";
		}
		//oblicza dop�ate wzgl�dem obecnej daty
		public float calculateSurcharge(float rate) {
			return rate *  Math.abs(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-rentalExpireDate.get(Calendar.DAY_OF_MONTH));
		}
}
