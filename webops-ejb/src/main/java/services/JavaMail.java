package services;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class JavaMail {
	public static void sendConfirmationAcount(String Recepient,String E) throws MessagingException {
	    // 1 -> Création de la session
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    		
	   String myAccountEmail="oumeimaibnelf@gmail.com";
	   String password="5335588319966";
	   Session session=Session.getInstance(properties,new Authenticator() {
		   @Override
		   protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(myAccountEmail, password);
		   }
	}
	   );
	   Message message =prepareMessage(session,myAccountEmail,Recepient,E);
	   Transport.send(message);
	}

	
	private static Message prepareMessage(Session session,String myAccountEmail,String Recepient, String E) {
		
		try {
			Message message =new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(Recepient));
			message.setSubject("Job Offers of the week ");
			//message.setRecipients(type, addresses);
			message.setText(E);
			
			return message;
		}catch(Exception ex)
		{
			Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE,null,ex);;
		}
		return null;
	}
}
