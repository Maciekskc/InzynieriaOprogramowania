package BusinesLayer;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;

import DataLayer.Components.Rental;
import Test.DataAccessLayer.ExampleData;


public class ReminderSenderTest {

	
	Rental rental = new Rental();
	
	boolean result = false;
	
	@Test
	public void test() {
		ExampleData example = new ExampleData();
		
		Rental rent = new Rental(example.getExampleVideos(),Calendar.getInstance(),Calendar.getInstance(),example.getExampleCustomers().get(0));
		
		//assertFalse("Wyporzeyczenie nie zosta³o przeteerminowane",reminder.remindCondition(rent));
	}

}
