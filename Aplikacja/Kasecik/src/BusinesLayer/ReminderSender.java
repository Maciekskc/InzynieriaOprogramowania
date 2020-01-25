package BusinesLayer;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import DataLayer.Components.Rental;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;

class ReminderSender {
	private IDataOperations dtoData;

	public ReminderSender(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	public ReminderSender() {
	}
	
	public boolean remindCondition(Rental rental) {
		Calendar currentDate = Calendar.getInstance();
		if(currentDate.after(rental.getRentalExpireDate())) return true;
		return false;
	}
	
	public void sendReminder(Rental rental){
		//metoda wysy³aniia powiadomienia
	}
	
	 private static final String SMTP_SERVER = "smtp server ";
	    private static final String USERNAME = "";
	    private static final String PASSWORD = "";

	    private static final String EMAIL_FROM = "From@gmail.com";
	    private static final String EMAIL_TO = "email_1@yahoo.com, email_2@gmail.com";
	    private static final String EMAIL_TO_CC = "";

	    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
	    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

	
	public void generateReminder() {

		 String to = "maciekskc@gmail.com";//change accordingly  
	      String from = "maciekskc@gmail.com";
	      String host = "localhost";//or IP address  
	  
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject("Ping");  
	         message.setText("Hello, this is example of sending email  ");  
	  
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  
	      }catch (MessagingException mex) {mex.printStackTrace();}  
	    
		   
		for(Rental rent: dtoData.getRentals()) 
			if(remindCondition(rent)) sendReminder(rent);
		
	}
}
