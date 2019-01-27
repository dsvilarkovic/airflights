package com.isa.airflights.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.repository.AbstractUserRepository;

@Service
public class AbstractUserService {

	@Autowired
	private AbstractUserRepository abstractUserRepository;

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

	public Optional<AbstractUser> findByEmail (String email) throws UsernameNotFoundException{
		System.out.println("Usao u find by email iznad " + email);
		return abstractUserRepository.findByEmail(email);
	}

	public AbstractUser login(AbstractUser user) {
		System.out.println("USao u servis login " + user.getEmail());
		Optional<AbstractUser> _user = abstractUserRepository.findByEmail(user.getEmail());

		/*if (_user != null) {
			System.out.println("USao u if u servis login");
			if (_user.getVerify() == false) {
				System.out.println("Nije verifikovan");
				return null;
			}
			if (_user.getPassword().equals(user.getPassword())) {
				return _user;
			} else {
				return null;
			}
		} else
			return null;*/
		return null;
	}

	public AbstractUser getOne(Long id) throws AccessDeniedException {
		return abstractUserRepository.getOne(id);
	}

	public Boolean checkVerify(String email) {
		Optional<AbstractUser> user = abstractUserRepository.findByEmail(email);
		if(user.get().getVerify()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String sendVerMail(AbstractUser user) throws MailException, InterruptedException, MessagingException {
		MimeMessage mime = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, false, "utf-8");

		String link = "<html>" + "					  \r\n" + "					  <head>"
				+ "					    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
				+ "\r\n" + "					    <title>Welcome to airflights</title>"
				+ "					  </head>\r\n" + "					  \r\n" + " 					  <body>"
				+ "					<br><br><a href=\"http://localhost:4200/verify/" + user.getId()
				+ "\" style=\"display:block; padding:15px 25px; background-color:#0087D1; color:#ffffff; border-radius:3px; text-decoration:none;\">Verify Email Address</a>"
				+ "</body></html>";

		try {
			mime.setContent(link, "text/html");
			helper.setTo(user.getEmail());
			/*
			 * helper.setText("Pozdrav "+ user.getFirstName() + "\n Ovde ce " +
			 * "kasnije biti aktivacioni link");
			 */
			helper.setSubject("Verifikacioni e-mail");
			helper.setFrom(env.getProperty("spring.mail.username"));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sender.send(mime);

		return "Mail je poslat";

	}
	
	/**
	 * Metoda za podesavanje izmena <br>
	 * @author Dusan
	 * @param updatedUser
	 * @return
	 */
	public AbstractUser updateAbstractUser(AbstractUserDTO updatedUser) {
		//Prvo se proveri da li postoji pod tim id-jem.
		AbstractUser foundAbstractUser = abstractUserRepository.getOne(updatedUser.getId());
		if(foundAbstractUser == null) {
			return null;
		}
		//Zatim se proveri za password da li je prazan.
		if(foundAbstractUser.getPassword().isEmpty()) {
			return null;
		}
		//zatim se podesi novi nalog pod tim id-jem
		abstractUserRepository.deleteById(updatedUser.getId());
		abstractUserRepository.saveAndFlush(updatedUser.getAbstractUser());
		//zatim se vrati null ili updatedUser opet
		return abstractUserRepository.getOne(updatedUser.getId());
	}
	
	

}
