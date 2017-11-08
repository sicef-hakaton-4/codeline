package net.sytes.codeline.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import net.sytes.codeline.entities.User;
import net.sytes.codeline.services.MailService;

public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(User user) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		String subject = "Test naslov";
		String messageText = "Poštovani " + user.getFirstName() + " " + user.getLastName() +
				", Ovo je test poruka. Hvala.";
		
		message.setTo(user.getEmail());
		message.setSubject(subject);
		message.setText(messageText);
		
		mailSender.send(message);
		System.out.println("Mail je poslat na adresu " + user.getEmail());
	}

}
