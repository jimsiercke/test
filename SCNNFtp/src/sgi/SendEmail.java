package sgi;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public static boolean sendEmail(String body, String subject)  {
		boolean mailSent = false;
		try  {
			String host = "10.11.12.25";
			String from = "support@storygroup.net";
//			String to[] =  new String[] emails;
			String to ="systems@storygroup.net";
		   // Get system properties
		   Properties props = System.getProperties();
		   props.put("mail.smtp.host", host);
	
		   Session session = Session.getInstance(props, null);
		   //System.out.println("PROPERTIES==========="+session.getProperties());
	
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   //here if we want to allow multiple email addresses in the future
//		   InternetAddress[] toAddress = new InternetAddress[to.length];
//		   for (int i = 0; i < to.length; i++)
//		   toAddress[i] = new InternetAddress(to[i]);
		   InternetAddress toAddress = new InternetAddress(to);
		   message.setRecipient(Message.RecipientType.TO, toAddress);    
		   message.setSubject(subject);
		   message.setSentDate(new Date());
		   BodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setText(body);
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   message.setContent(multipart);
		   try{
		       Transport.send(message);
		       mailSent = true;
		   }
		   catch(SendFailedException sfe)  {
			 System.out.println("GenFTP SEND FAILED ERROR="+sfe.getMessage()); 
		   }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mailSent;
	}
	
}
