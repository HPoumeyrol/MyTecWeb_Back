package fr.bnpp.pf.mytecweb.rest.services;



import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



// Dans mon compte gmail et donc google aller dans 
//  "Paramètre "Autoriser les applications moins sécurisées" activé " apres s'etre connecté sur son compte via le lien ci- dessous
//  https://myaccount.google.com/lesssecureapps

@Service
public class MailSender {

	@Value( "${spring.mail.username}" )
	private String userName;
	
	@Value( "${spring.mail.password}" )
	private String password;
	
	
	@Value( "${spring.mail.from}" )
	private String from;

	@Value( "${spring.mail.port}" )
	private String port;

	@Value( "${spring.mail.host}" )
	private String host;

	@Value( "${mailSender.debug}" )
	private Boolean debug;
	
	public String envoyer(String destTo, String destCC, String destBCC, String subject, String body ) {
		
		String result= "";
		
		try {
			
			if( debug ) {
				System.out.println("");
				System.out.println("------- MAIL SENDER PARAMETERS --------");
				System.out.println("username : " + userName);
				System.out.println("password : " + password);
				System.out.println("port     : " + port);
				System.out.println("host     : " + host);
				System.out.println("---------------------------------------");
				System.out.println("");
			}
			
			// STEP 1 : create SESSION
			if( debug ) { System.out.println(" STEP 1 start"); }	
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			
			props.put("mail.smtp.connectiontimeout", "5000");
		    props.put("mail.smtp.timeout", "5000");
			
			
			Session session = Session.getInstance(
						props, 
					    new javax.mail.Authenticator() {
															protected PasswordAuthentication getPasswordAuthentication() {
																return new PasswordAuthentication(userName, password);
															}
													  }
												);
			
			
			if( debug ) { System.out.println(" STEP 1 end");	}
			
			
			
			// STEP 2 : create MESSAGE object
			if( debug ) { System.out.println(" STEP 2 start"); }
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destTo));
			message.setRecipients(Message.RecipientType .CC, InternetAddress.parse(destCC));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(destBCC));
			
			
			message.setSubject(subject);
			message.setText(body);
			if( debug ) { System.out.println(" STEP 2 end");	}
			
			
			
			// STEP 3 : send MESSAGE
			if( debug ) { System.out.println(" STEP 3 start"); }	
			Transport.send(message);
			result = "mail sent";
			if( debug ) { System.out.println(" STEP 3 end"); }	
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = "mail not sent. An error occurs";
			
		}
		
		return result;
	}

}