package utilities;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClass {
	public MailClass(String mail,String object,String description) {
	        try {
	            send(mail,object,description);
	        } catch (MessagingException ex) {System.out.println(ex.getMessage()); }
	    }
	    
	    public void send(String to, String subject, String messageText) throws MessagingException {
	               /* final String username = "tunisiamallcondors@gmail.com";
			final String password = "testcondors";*/
	                
	                final String user = "WebopsTeam1@gmail.com";
			final String pass = "rqcrfxztobglbmfv";
	                
			try {
	                    
	            boolean sessionDebug = false;

	            Properties props = System.getProperties();

	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host",  "smtp.gmail.com");
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");

	            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	            Session mailSession = Session.getDefaultInstance(props, null);
	            mailSession.setDebug(sessionDebug);
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(subject));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject); msg.setSentDate(new Date());
	            msg.setText(messageText);

	           Transport transport=mailSession.getTransport("smtp");
	           transport.connect("smtp.gmail.com",user, pass);
	           transport.sendMessage(msg, msg.getAllRecipients());
	           transport.close();
	           System.out.println("message send successfully");
	        }catch(Exception ex)
	        {
	            System.out.println(ex);
	        }

	    }
}
