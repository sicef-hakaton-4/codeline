package net.sytes.codeline.helpers;

import java.io.File;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import net.sytes.codeline.entities.Recommendation;
import net.sytes.codeline.entities.RecommendationInfo;
import net.sytes.codeline.entities.User;

public class MailHelper {
	private final String USERNAME = "hakaton.codeline@gmail.com";
	private final String PASSWORD = "codelinehakaton";

	private Properties setProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		return props;
	}

	private Session setSession() {
		Properties props = setProperties();

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			});

		return session;
	}
	
	public MailHelper(){}
	
	public void sendRegistrationMail(User user) {
		Session session = setSession();
		
		try  {
			String subject = "CodeLine registracija";
			String messageText = "Poštovani " + user.getFirstName() + " " + user.getLastName() + ",\n\nUspešno ste kreirali nalog! Vaše korisničko ime je: " + user.getUsername() 
			+ ". \n\nDa biste izvršili aktivaciju naloga, molimo Vas da kliknete na sledeći link: "
			+ "http://172.20.10.2:8000/verify/" + Base64.getEncoder().encodeToString(user.getUsername().getBytes())
			+ ".\n\nHvala što koristite naš sistem!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));
			message.setSubject(subject);
			
			message.setText(messageText);
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Mail za registraciju je poslat na adresu " + user.getEmail());
	}
	
	public void sendThanksMail(User user, File file) throws MessagingException {
		Session session = setSession();
		
		/*try  {
			String subject = "Hvala";
			String messageText = "Poštovani " + user.getFirstName() + " " + user.getLastName() + ",\n\nHvala za veliki broj preporučenih zaposlenih! "
			+ "\n\nU znak zahvalnosti, u prilogu ovog mail-a se nalazi mali znak pažnje."
			+ ".\n\nHvala što koristite naš sistem!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));
			message.setSubject(subject);
			 // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(messageText);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(file);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName("Hvala " + user.getFirstName() + ".pdf");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
		MimeMessage message = new MimeMessage(session);
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(user.getEmail());
		helper.setText("<!DOCTYPE html>"
+"<html>"
+"<head>"
+"	<title></title>"
+"	<link rel='stylesheet'"
+"          href='https://fonts.googleapis.com/css?family=Tangerine'>"
+"    <style>"
+"      body {"
+"        font-family: 'Tangerine', serif;"
+"        font-size: 28px;"
+"      }"
+"    </style>"
+"</head>"
+"<body>"
+"	<div style='width:615px; height:842px; padding:20px; text-align:center; border: 10px solid #63b175; background-image: url('https://files.slack.com/files-pri/T7V0U31GS-F7V4U9D8S/temp.png'); '>"
+"		<h1 style='color: white; font-weight: bold; text-align: right;'>CodeLine</h1>"
+"		<div style='margin-top: 200px'>"
+"			<h1>Z A H V A L N I C A</h1>"
+"		</div>"
+"		<div style='margin-top: 40px;'>"
+"			<h3>Zahvaljujemo se što ste preporučili IT stručnjaka.</h3>"
+"		</div>"
+"	</div>"
+"</body>"
+"</html>", true);
		
		Transport.send(message);

		System.out.println("Mail je poslat na adresu " + user.getEmail());
	}
	
	public void sendResetPasswordMail(User user) {
		Session session = setSession();
		
		try  {
			String subject = "Reset password-a";
			String messageText = "Poštovani " + user.getFirstName() + " " + user.getLastName() 
			+ ",\n\nDa biste resetovali Vašu lozinku, molimo Vas da kliknete na sledeći link: "
			+ "http://172.20.10.2:8000/resetPassword/" + Base64.getEncoder().encodeToString(user.getUsername().getBytes())
			+ ".\n\nHvala što koristite naš sistem!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));
			message.setSubject(subject);
			message.setText(messageText);
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Mail za reset password-a je poslat na adresu " + user.getEmail());
	}

	public void sendRecommendationMail(Recommendation recommendation) {
		Session session = setSession();
				
		try  {
			String subject = "Poziv za preporuku novog zaposlenog";
			String messageText = "Poštovani " + recommendation.getFirstName() + " " + recommendation.getLastName() 
			+ ",\n\nKompanija " + recommendation.getCompanyName() + " je objavila novi konkurs: " + recommendation.getJobTitle()
			+ "\n\nUkoliko poznajete nekog koga biste preporučili za ovu poziciju, to možete učiniti na Vašem profilu."						
			+ ".\n\nHvala!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recommendation.getEmail()));
			message.setSubject(subject);
			message.setText(messageText);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Mail za predlog potencijalnog zaposlenog je poslat na adresu " + recommendation.getEmail());
	}
	
	public void sendInfoAboutRecommendationToCompany(RecommendationInfo recommendationInfo) {
		Session session = setSession();
				
		try  {
			String subject = "Preporučen zaposleni";
			String messageText = "Poštovani, " 
			+ ",\n\nKorisnik " + recommendationInfo.getFirstName1() + " " + recommendationInfo.getLastName1() + " je preporučio novog zaposlenog (" + recommendationInfo.getFirstName2() + " " + recommendationInfo.getLastName2() + ") za Vaš konkurs: " + recommendationInfo.getJobTitle()
			+ "\n\nDetaljnije informacije o preporučenom možete videti na Vašem profilu."						
			+ ".\n\nHvala!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recommendationInfo.getEmail()));
			message.setSubject(subject);
			message.setText(messageText);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Mail kompaniji za preporučenog zaposlenog je poslat na adresu " + recommendationInfo.getEmail());
	}
	
	public void sendInfoAboutRecommendation(RecommendationInfo recommendationInfo) {
		Session session = setSession();
				
		try  {
			String subject = "Preporuka za posao";
			String messageText = "Poštovani " + recommendationInfo.getFirstName2() + " " + recommendationInfo.getLastName2() + "," 
			+ "\n\nKorisnik " + recommendationInfo.getFirstName1() + " " + recommendationInfo.getLastName1() + " Vas je preporučio na konkursu " + recommendationInfo.getJobTitle()
			+ " u kompaniji " + recommendationInfo.getCompanyName()
			+ ".\n\nDetaljnije informacije možete videti na Vašem profilu."						
			+ ".\n\nHvala!";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recommendationInfo.getEmail()));
			message.setSubject(subject);
			message.setText(messageText);
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Mail preporučenom zaposlenom je poslat na adresu " + recommendationInfo.getEmail());
	}
}
