package com.isa.airflights.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.repository.AbstractUserRepository;


@Service
public class AbstractUserService {

	
	@Autowired
	private  AbstractUserRepository abstractUserRepository;
	
	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	private Environment env;


	public List<AbstractUser> findAll() {
		return abstractUserRepository.findAll();
	}
	
	public Page<AbstractUser> findAll(Pageable page) {
		return abstractUserRepository.findAll(page);
	}

	public AbstractUser save(AbstractUser AbstractUser) {
		return abstractUserRepository.save(AbstractUser);
	}


	
	public AbstractUser findByIndex(String index) {
		return abstractUserRepository.findOneByIndex(index);
	}

	
	
	public AbstractUser findByEmail(String email) {
		System.out.println("Usao u find by email iznad " + email);
		return abstractUserRepository.findByEmail(email);
	}
	
	public AbstractUser login(AbstractUser user) {
		System.out.println("USao u servis login " + user.getEmail());
		AbstractUser _user = abstractUserRepository.findByEmail(user.getEmail());
		
		
		
		if(_user != null) {
			System.out.println("USao u if u servis login");
			if(_user.getVerify() == false) {
				System.out.println("Nije verifikovan");
				return null;
			}
			if(_user.getPassword().equals(user.getPassword())) {
				return _user;
			} else {
				return null;
			}
		}else
			return null;
	}
	
	public AbstractUser getOne(Long id) {
		return abstractUserRepository.getOne(id);
	}
	
	
	public String sendVerMail(AbstractUser user) throws MailException, InterruptedException, MessagingException {
		MimeMessage mime = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime,false,"utf-8");
		
		String link = "<html>" + 
				"					  \r\n" + 
				"					  <head>" + 
				"					    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + "\r\n" + 
				"					    <title>Welcome to airflights</title>" + 
				"					  </head>\r\n" + 
				"					  \r\n" + 
				" 					  <body>" +
				"					<br><br><a href=\"http://localhost:4200/verify/" + user.getId() + "\" style=\"display:block; padding:15px 25px; background-color:#0087D1; color:#ffffff; border-radius:3px; text-decoration:none;\">Verify Email Address</a>"
				+ "</body></html>";
		
			try {
				mime.setContent(link, "text/html");
				helper.setTo(user.getEmail());
				/*helper.setText("Pozdrav "+ user.getFirstName() + "\n Ovde ce "
						+ "kasnije biti aktivacioni link");*/
				helper.setSubject("Verifikacioni e-mail");
				helper.setFrom(env.getProperty("spring.mail.username"));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		sender.send(mime);
		
		return "Mail je poslat";
		
	}
	
	
}
