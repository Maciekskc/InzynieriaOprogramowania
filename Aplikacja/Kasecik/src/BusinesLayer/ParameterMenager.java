package BusinesLayer;

import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

class ParameterMenager {

	
	private IDataOperations dtoData;

	public ParameterMenager(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	public void changeRate(float rate) {
		dtoData.changeSurcharge(rate);
	}

}
