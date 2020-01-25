package Test;

import java.util.IllegalFormatCodePointException;

import fit.ColumnFixture;
import fit.Fixture;
import DataLayer.*;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

public class ChangeRate extends ColumnFixture  {
	
	public float rate;
	
	public boolean changeRate() throws IllegalFormatCodePointException {
		 
		 try{
			 float rate1=getRate();
			 SetUp.dtoData.changeSurcharge(rate);
			 float rate2=getRate();
			 return rate1!=rate2;
			 
		 } catch(IllegalFormatCodePointException e) {
		 }
		 
	 	return false;
	 }
	
	public float getRate() {
		return SetUp.dtoData.getRate();
	} 
}
