package my.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import my.mail.Email;

@Controller
public class HomeController {
	
	@Autowired
	private Email email;
	
	
	@GetMapping("/sendEmail")
	public String getIndex() {
		
		String msg="";
		try {
			email.sendEmailWithTemplate("...", "...", "...", 
					msg ,"...");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "index";
	}

}
