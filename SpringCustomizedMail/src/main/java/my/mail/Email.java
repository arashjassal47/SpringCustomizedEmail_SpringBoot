package my.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class Email {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendEmail(String to, String subject, String message) {
		
		System.out.println("Sending email...");
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(message);
		
		try {
			javaMailSender.send(msg);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}

    public void sendEmailWithTemplate(String to, String subject, 
    		String name, String message, String footer) throws MessagingException {
    	
       Context ctx = new Context();
       ctx.setVariable("name", name);
       ctx.setVariable("message", message);
       ctx.setVariable("footer", footer);
		
		System.out.println("Sending email...");
	
		
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		mimeHelper.setTo(to);
		mimeHelper.setSubject(subject);
		
		String htmlContent = this.templateEngine.process("emailtemplate.html", ctx);
		mimeHelper.setText(htmlContent,true);
		try {
			javaMailSender.send(mimeMessage);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}

}
