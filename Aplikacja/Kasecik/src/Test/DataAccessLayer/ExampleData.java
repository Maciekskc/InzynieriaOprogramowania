package Test.DataAccessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;



import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;

public class ExampleData {

	public ArrayList<Video> getExampleVideos(){
		ArrayList<Video> list = new ArrayList<Video>();
		list.add(new Video(100,"LOTR","Fantasy",123,2));
		list.add(new Video(102,"GraOTron","Fantasy",123617,2));
		list.add(new Video(103,"GW I","Fantasy",120,2));
		list.add(new Video(104,"GW VIII","Fantasy",160,2));
		list.add(new Video(105,"GW IX","Dramat",222,2));
		return list;
	}
	
	public ArrayList<CustomerData> getExampleCustomers(){
		ArrayList<CustomerData> list = new ArrayList<CustomerData>();
		list.add(new CustomerData(2,"Aleksandra","Malicka","876678768","ola@gmail.com"));		 
		list.add(new CustomerData(3,"Jan","Nowak","725781234","jn@gmail.com"));		 
		list.add(new CustomerData(4,"Ania","Kowalska","467856748","aniak@gmail.com"));		 
		list.add( new CustomerData(1,"Maciej","Bia³kowski","601585207","maciekskc@gmail.com"));
		
		return list;
	}
	
	public ArrayList<Rental> getExampleRentals(){
		ArrayList<Rental> list = new ArrayList<Rental>();
		ArrayList<Video> toRental= new ArrayList<>();
		
		Calendar rent = Calendar.getInstance();
		Calendar expire = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		try {
			rent.setTime(sdf.parse("Tue Jan 1 14:31:03 CET 2020"));
			expire.setTime(sdf.parse("Tue Jan 10 14:31:03 CET 2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toRental.add(getExampleVideos().get(0));
		list.add(new Rental(toRental,rent,expire,getExampleCustomers().get(0)));
		toRental= new ArrayList<>();
		
		try {
			rent.setTime(sdf.parse("Tue Jan 9 14:31:03 CET 2020"));
			expire.setTime(sdf.parse("Tue Jan 14 14:31:03 CET 2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toRental.add(getExampleVideos().get(1));
		list.add(new Rental(toRental,rent,expire,getExampleCustomers().get(1)));
		toRental= new ArrayList<>();
		
		try {
			rent.setTime(sdf.parse("Tue Jan 9 14:31:03 CET 2020"));
			expire.setTime(sdf.parse("Tue Jan 14 14:31:03 CET 2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toRental.add(getExampleVideos().get(2));
		list.add(new Rental(toRental,rent,expire,getExampleCustomers().get(2)));
		toRental= new ArrayList<>();
		
		try {
			rent.setTime(sdf.parse("Tue Jan 9 14:31:03 CET 2020"));
			expire.setTime(sdf.parse("Tue Jan 14 14:31:03 CET 2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toRental.add(getExampleVideos().get(3));
		toRental.add(getExampleVideos().get(4));
		list.add(new Rental(toRental,rent,expire,getExampleCustomers().get(3)));
		toRental= new ArrayList<>();

		
		return list;
	}
}
