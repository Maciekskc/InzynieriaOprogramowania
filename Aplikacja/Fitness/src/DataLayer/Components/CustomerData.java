package DataLayer.Components;

/**
 * @author user
 *
 */
public class CustomerData {

	private int idNumber;
	private String name;
	private String surname;
	private String telephoneNumber;
	private String email;
	
	
	
	public CustomerData(int idNumber, String name, String surname, String telephoneNumber, String email) {
		super();
		this.idNumber = idNumber;
		this.name = name;
		this.surname = surname;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
	}
	
	public int getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return idNumber+";"+name+";"+surname+";"+telephoneNumber+";"+email;
	}
	
}
