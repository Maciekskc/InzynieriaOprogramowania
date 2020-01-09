package BusinesLayer;

import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

class ParameterMenager {

	
	private IDataOperations dtoData = new DataOperationsImpl();

	public ParameterMenager(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	//zmianaa ceny
	public void calculateSurcharge(float rate) {
		dtoData.changeSurcharge(rate);
	}

}
