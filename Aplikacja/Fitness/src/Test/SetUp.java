package Test;

import DataLayer.*;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;
import fit.Fixture;

public class SetUp extends Fixture {

	public static IDataOperations dtoData; 
	
	public SetUp(){
		dtoData = new DataOperationsImpl();	
	//	dtoData.changeSurcharge(2.00F);
	}
}