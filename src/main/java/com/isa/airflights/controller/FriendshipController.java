package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.dto.FriendshipDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Friendship;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.FriendshipService;
import com.isa.airflights.utils.StringJSON;

@RestController
@RequestMapping(value="/api/userProfile/friends")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendshipController {

	@Autowired
	private AbstractUserService abstractUserService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	/**
	 * Slanje zahteva na prijateljstvo
	 * @param friendshipDTO
	 * @return
	 */
	@RequestMapping(value = "/send",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> sendRequest(@RequestBody FriendshipDTO friendshipDTO){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		if(loggedUser == null) {
			return new ResponseEntity<>(new StringJSON("No currently logged-in user found"), HttpStatus.NOT_FOUND);
		} 
		
		if(friendshipDTO.getReceiverId() == null || friendshipDTO.getSenderId() == null) {
			return new ResponseEntity<>(new StringJSON("Invalid friendship"), HttpStatus.NOT_FOUND);
		}
		
		//proveri da li je sender isti kao i ulogovani korisnik
		AbstractUser sender = abstractUserService.getOne(friendshipDTO.getSenderId());
		if(!loggedUser.equals(sender)) {
			return new ResponseEntity<>(new StringJSON("Sender and logged-in user are not the same person. You are intruder! .!."), HttpStatus.NOT_FOUND);
		}
		
		//TODO: proveriti da li se dodaje prijatelj koji je vec dao zahtev
		friendshipDTO.setAccepted(false);
		Friendship friendship = friendshipService.convertToEntity(friendshipDTO);
		
		//proveri da li ova veza vec postoji u nekom pravcu
		
		if(friendshipService.exists(friendship)) {
			return new ResponseEntity<>(new StringJSON("It already exists"), HttpStatus.FOUND);
		}
		friendshipService.saveFriendship(friendship);
		
		return new ResponseEntity<>(new StringJSON("Successfully added friendship"), HttpStatus.OK);
	}
	
	/**
	 * Metoda za brisanje prijateljstva
	 * @param id - id prijateljstva
	 * @return
	 */
	//revert request  + //delete friend
	@RequestMapping(value = "/revert/{id}",
					method = RequestMethod.DELETE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> revertRequest(@PathVariable Long id){
		
		Boolean success = friendshipService.deleteFriendship(id);
		
		if(!success) {
			return new ResponseEntity<>(new StringJSON("Unsuccesful deletion of friendship"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StringJSON("Successful deletion, friendship ended with someone, find someone new who is your best friend!"), HttpStatus.OK);		
	}
	
	
	/**
	 * Prihvatanje prijateljstva
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/accept/{id}",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> acceptFriendRequest(@PathVariable Long id){
		
		Boolean success = friendshipService.acceptFriendship(id);
		if(!success) {
			return new ResponseEntity<>(new StringJSON("Friendship not accepted, because it does not exist"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new StringJSON("Friendship started, congratulations"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 *  Vrati mi sve prihvacene prijatelje, tj sve moje prave prijatelje
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value = "/find/all",
			method = RequestMethod.GET,	
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllApprovedFriendsByPage(Pageable pageRequest){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		//Set<AbstractUser> friends = friendshipService.findAllFriends(loggedUser.getId(), pageRequest);
		
		Page<Friendship> friendships = friendshipService.findAllFriends(loggedUser.getId(), pageRequest);
		Set<AbstractUser> friends = friendshipService.extractUsersDifferentThanUserId(loggedUser.getId(), friendships.getContent());
		Set<AbstractUserDTO> friendDTOs = new HashSet<>();
		for (AbstractUser abstractUser : friends) {
			AbstractUserDTO abstractUserDTO = abstractUserService.convertToDTO(abstractUser);
			friendDTOs.add(abstractUserDTO);
		}
		
		Page<AbstractUserDTO> ret = new PageImpl<>(new ArrayList<>(friendDTOs), friendships.getPageable(), friendships.getTotalElements());
		return ResponseEntity.ok(ret);
		//return new ResponseEntity<>(friendDTOs, HttpStatus.OK);
	}
	

	/**
	 * Vrati mi sve korisnike koji me traze za prijatelja
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value = "/requests",
			method = RequestMethod.GET,	
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFriendRequests(Pageable pageRequest){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		Long userId = loggedUser.getId();
		
		
		Page<Friendship> friendshipRequests = friendshipService.findMyFriendRequests(userId, pageRequest);
		Set<AbstractUserDTO> friendDTOs = new HashSet<>();
		for (Friendship friendship : friendshipRequests) {
			AbstractUser abstractUser = friendship.getSender();
			AbstractUserDTO abstractUserDTO = abstractUserService.convertToDTO(abstractUser);
			friendDTOs.add(abstractUserDTO);
		}
		
		Page<AbstractUserDTO> ret = new PageImpl<>(new ArrayList<>(friendDTOs), friendshipRequests.getPageable(), friendshipRequests.getTotalElements()); 

		return ResponseEntity.ok(ret);
	}
	
	/**
	 * Vrati mi sve prijatelje od kojih cekam prijateljstvo
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value = "/pending",
			method = RequestMethod.GET,	
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFriendPending(Pageable pageRequest){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		Long userId = loggedUser.getId();
		
		Page<Friendship> friendships = friendshipService.findFriendsPending(userId, pageRequest);
	
		Set<AbstractUserDTO> friendDTOs = new HashSet<>();
		for (Friendship friendship : friendships) {
			AbstractUser abstractUser = friendship.getReceiver();
			AbstractUserDTO abstractUserDTO = abstractUserService.convertToDTO(abstractUser);
			friendDTOs.add(abstractUserDTO);
		}
		
		Page<AbstractUserDTO> ret = new PageImpl<>(new ArrayList<>(friendDTOs), friendships.getPageable(), friendships.getTotalElements()); 
		return ResponseEntity.ok(ret);
	}
	
	
	//get all users by search criteria
	/**
	 * Pagination nacin da se pretrazuju prijatelji u zavisnosti od parametara, po default-u su svi otkaceni </br>
	 * accepted = all my friends </br>
	 * sender = requesting </br>
	 * receiver = pending </br>
	 * accepted + sender = friend from pending </br>
	 * accepted + receiver = friend from requested </br>
 	 * sender + receiver = all my pending and requesting, but not accepted </br>
	 * accepted + sender + receiver = all my friends, same as single accepted </br>
	 *
	 * ako se doda keyword - vratice sve prijatelje koji u imenu ili prezimenu sadrze njega (containing) </br>
	 * 
	 * 
	 * @param accepted - oni koji su prihvaceni
	 * @param sender - one koje smo mi poslali
	 * @param receiver - oni koje smo mi primili
	 * @param keyword - kljucna rec po kojoj se trazi
	 * @param pageRequest - paginacija
	 * @return - listu prijatelja po zadovoljenom kriterijumu
	 */
	@RequestMapping(value = "/search",
			method = RequestMethod.GET,	
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFriendshipByCriteria(
				@RequestParam(name = "accepted", defaultValue = "true", required = false) Boolean accepted,
				@RequestParam(name = "sender", defaultValue = "true", required = false) Boolean sender,
				@RequestParam(name = "receiver", defaultValue = "true", required = false) Boolean receiver,
				@RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
				Pageable pageRequest
			){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		Long userId = loggedUser.getId();
		
		Page<AbstractUser> foundUsers = friendshipService.getFriendsByCriteria(userId, accepted, sender, receiver, keyword, pageRequest);
		
	
		Set<AbstractUserDTO> foundUsersDTOs = new HashSet<>();
		
		int size = ((PageRequest) pageRequest).getPageSize();
		int offset = (int) ((PageRequest) pageRequest).getOffset();
		for (int i = offset; i <  offset + size; i++) {
			try {
			AbstractUser abstractUser = foundUsers.getContent().get(i);
			AbstractUserDTO abstractUserDTO = abstractUserService.convertToDTO(abstractUser);
			foundUsersDTOs.add(abstractUserDTO);
			}
			catch(IndexOutOfBoundsException e) {
				break; 
			}
		}
		
		Page<AbstractUserDTO> ret = new PageImpl<>(new ArrayList<>(foundUsersDTOs), foundUsers.getPageable(), foundUsers.getTotalElements());
		return ResponseEntity.ok(ret);
		//return new ResponseEntity<>(foundUsersDTOs, HttpStatus.OK);
	}
	
	
}