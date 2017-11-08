package net.sytes.codeline.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import net.sytes.codeline.entities.Recommendation;
import net.sytes.codeline.entities.RecommendationInfo;
import net.sytes.codeline.entities.User;
import net.sytes.codeline.helpers.MailHelper;
import net.sytes.codeline.util.PdfGenaratorUtil;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	private MailHelper mailHelper;
	
	@Autowired
	private PdfGenaratorUtil pdfGeneratorUtil;
	
	@CrossOrigin
	@RequestMapping(value="/sendRegistrationMail", method=RequestMethod.POST)
	public void sendRegistrationMail(@RequestBody User user) {
		mailHelper = new MailHelper();
		mailHelper.sendRegistrationMail(user);
	}
	
	@CrossOrigin
	@RequestMapping(value="/sendResetPasswordMail", method=RequestMethod.POST)
	public void sendResetPasswordMail(@RequestBody User user) {
		mailHelper = new MailHelper();
		mailHelper.sendResetPasswordMail(user);
	}
	
	@CrossOrigin
	@RequestMapping(value="/sendThanksMail", method=RequestMethod.POST)
	public void generateReport(@RequestBody User user) throws Exception{
		mailHelper = new MailHelper();
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Darko");
		File file = pdfGeneratorUtil.createPdf("test", data);
		
		mailHelper.sendThanksMail(user, file);
	}
	
	@CrossOrigin
	@RequestMapping(value="/sendRecommendationMail", method=RequestMethod.POST)
	public void sendRecommendationMail(@RequestBody Recommendation recommendation) {
		mailHelper = new MailHelper();
		mailHelper.sendRecommendationMail(recommendation);
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/sendInfoAboutRecommendationToCompany", method=RequestMethod.POST)
	public void sendInfoAboutRecommendationToCompany(@RequestBody RecommendationInfo recommendationInfo) {
		mailHelper = new MailHelper();
		mailHelper.sendInfoAboutRecommendationToCompany(recommendationInfo);
	}
	
	@CrossOrigin
	@RequestMapping(value="/sendInfoAboutRecommendation", method=RequestMethod.POST)
	public void sendInfoAboutRecommendation(@RequestBody RecommendationInfo recommendationInfo) {
		mailHelper = new MailHelper();
		mailHelper.sendInfoAboutRecommendation(recommendationInfo);
	}
}
