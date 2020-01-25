package Test.DataAccessLayer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

import static org.mockito.Mockito.*;

public class MockitoUseTest {

	IDataOperations data;
	
	@Before
	public void setup() {
		data = new DataOperationsImpl(); 
		//mockowanie zachowania metody dla podania argumentu zmiany na ten sam
		//co domnyœlnie metoda blokuje 
		data = mock(DataOperationsImpl.class);
		when(data.changeSurcharge(1.99F)).thenReturn(true);
	}
	
	@Test
	public void testChangeSurcharge() {
		ArrayList<Float> list = new ArrayList<>();
		list.add(2.54F);
		list.add(1.54F);
		list.add(1236.123F);
		list.add(267.52F);

		//po wykomentowaniu mockowania ten argument siê nie wykona
		assertTrue("Nie uda³o siê zmienic na ten sam", data.changeSurcharge(1.99F));

		DataOperationsImpl data = new DataOperationsImpl();
		for (float surcharge : list) {
			//sprawdzamy czy zmieni
			assertTrue("Nie uda³o siê zmieniæ", data.changeSurcharge(surcharge));
			//sprawdzamy czy wyrzuca b³¹d na szmianie na to samo
			assertFalse("Zmiana na to samo wyrzuca b³¹d", data.changeSurcharge(surcharge));
		}
	}



}
