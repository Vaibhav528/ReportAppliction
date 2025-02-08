package in.vaibhav.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 

{
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to, File f)
	{
		try
		{
			MimeMessage mimeMsg= mailSender.createMimeMessage(); // Email with Attachment
			
			 MimeMessageHelper helper= new MimeMessageHelper(mimeMsg,true);
			 
			 
			 helper.setSubject(subject);
			 helper.setText(body, true);
			 helper.setTo("vaibhavsingh528@gmail.com");
			 helper.addAttachment("Plans-Info", f);
			
			mailSender.send(mimeMsg);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
			
		}
		
		return true;
		
	}

}
