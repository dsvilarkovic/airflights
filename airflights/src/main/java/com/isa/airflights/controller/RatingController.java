package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.RentACarDTO;
import com.isa.airflights.dto.rating.RentACarRatingDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.rating.RentACarRating;
import com.isa.airflights.repository.RentACarRatingRepository;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.RentACarService;



@RestController
@RequestMapping(value="/api/rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {
	
	@Autowired
	private RentACarService racService;
	
	@Autowired
	private AbstractUserService abs;
	
	@Autowired
	private RentACarRatingRepository rep;
	
	@RequestMapping("/rate/{rate}/{id}/{user}")
	public ResponseEntity<RentACarDTO> setRate(@PathVariable String rate,@PathVariable Long id,@PathVariable Long user) {
		RentACar rac = racService.getOne(id);
		AbstractUser u = abs.getOne(user);
		List<RentACarRating> rr = rep.findAll();
		RentACarRating temp = new RentACarRating(); // za izmenu u bazi
		RentACarRatingDTO dto = new RentACarRatingDTO();
		
		for (RentACarRating r : rr) {
			if(r.getRentacar().equals(id)) {
				temp = r;
				dto = new RentACarRatingDTO(r);
				break;
			}
		}
		
		
		
		u.setMarked(true);//kod usera setujemo da je ocenio
		
		System.out.println("Usao ?");
		
		int r = Integer.parseInt(rate); //trenutno ocenjen - ocena korisnika
		int suma = temp.getSum(); //uzimamo iz baze sumu
		suma++;
		double last = r;
		
		double rat = temp.getLastRating(); //ovde cemo sabrati sve do sad

		rat += last;
		
		double rating = rat/suma; //racunamo trenutni prosek
		
		System.out.println("Rating: " + rating);
		
		rac.setRating(rating); //stavimo u tabelu rent a car-a
		temp.setRating(rating); //satvimo u pomocnu tabelu gde racunamo
		temp.setSum(suma);
		temp.setLastRating(rat); //svaki put uvecavamo sumu na osnovu koje racunamo prosek
		
		abs.save(u);
		racService.save(rac);
		rep.save(temp);
		
		RentACarDTO dto2 = new RentACarDTO(rac);

				
		return new ResponseEntity<RentACarDTO>(dto2,HttpStatus.OK);
	}

}
