package Test.DataAccessLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import DataLayer.Components.CustomerData;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

public class DataOperationsImplTest {

	IDataOperations data;
	
	@Before
	public void setup() {
		data = new DataOperationsImpl(); 
	}
	
	@Test
	public void testAddRental() {
		ExampleData example = new ExampleData();
		
		ArrayList<Rental> list = example.getExampleRentals();
		int i = 0;
		for(Rental rental: list) {
			ArrayList<Video> videoList = new ArrayList<>();
			videoList.add(example.getExampleVideos().get(i));
			videoList.add(example.getExampleVideos().get(i+1));
			assertTrue("blad dodania", data.addRental(videoList, example.getExampleCustomers().get(i)));
			i++;
			videoList = new ArrayList<>();
		}
	}
	
	@Test
	public void testRemoveRental() {
		ExampleData example = new ExampleData();
		
		ArrayList<Rental> list = data.getRentals();
		for(int i = 0 ; i < list.size() ; i++) {
			assertTrue("Nie ma czego usunac",data.removeRental(list.get(i)));
			i++;
		}
	}

	
	@Test
	public void testAdd_RemoveVideo() {
		ExampleData example = new ExampleData();
		ArrayList<Video> list = example.getExampleVideos();
		
		for(Video video : list){
			assertTrue("blad dodania",data.addVideo(video));
			assertTrue("blad usuwania",data.removeVideo(video));
		}
	}



}
