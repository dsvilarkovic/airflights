package com.isa.airflights.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.dto.FriendshipDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Friendship;
import com.isa.airflights.repository.AbstractUserRepository;
import com.isa.airflights.repository.FriendshipRepository;

/**
 * Servis koji se bavi radom sa prijateljstvima
 * @author dusan
 *
 */
@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepository friendshipRepository;
	
	@Autowired 
	private AbstractUserRepository abstractUserRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Metoda za cuvanje prijateljstva
	 * @param friendship
	 */
	public void saveFriendship(Friendship friendship) {
		AbstractUser sender = abstractUserRepository.getOne(friendship.getSender().getId());
		AbstractUser receiver = abstractUserRepository.getOne(friendship.getReceiver().getId());
		
		sender.getSenders().add(friendship);
		receiver.getReceiver().add(friendship);
		
		friendshipRepository.save(friendship);
	}
	
	public Friendship getFriendship(Long id) {
		return friendshipRepository.getOne(id);		
	}
	
	/**
	 * Metoda za brisanje prijateljstva
	 * @param id
	 * @return
	 */
	public Boolean deleteFriendship(Long id) {
		try {
			friendshipRepository.getOne(id);
			friendshipRepository.deleteById(id);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	
	public Friendship convertToEntity(FriendshipDTO friendshipDTO) {
		Friendship friendship = modelMapper.map(friendshipDTO, Friendship.class);
		
		AbstractUser sender = abstractUserRepository.getOne(friendshipDTO.getSenderId());
		AbstractUser receiver = abstractUserRepository.getOne(friendshipDTO.getReceiverId());
		
		friendship.setReceiver(receiver);
		friendship.setSender(sender);
		
		return friendship;		
	}
	
	
	public FriendshipDTO convertToDTO(Friendship friendship) {
		FriendshipDTO friendshipDTO = modelMapper.map(friendship, FriendshipDTO.class);
		
		Long receiverId = friendship.getReceiver().getId();
		Long senderId = friendship.getSender().getId();
		
		friendshipDTO.setReceiverId(receiverId);
		friendshipDTO.setSenderId(senderId);
		
		return friendshipDTO;
	}

	public Boolean acceptFriendship(Long id) {
		try {
			Friendship friendship = friendshipRepository.getOne(id);
			friendship.setAccepted(true);
			friendshipRepository.save(friendship);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		return true;
	}
	/**
	 * Vraca korisnike koji su u prijateljstvu sa nasim korisnikom
	 * @param userId - id korisnika ciji se prijatelji traze
	 * @param pageRequest - da li zelimo stranicu ili null ako sve
	 * @return - skup prijatelja korisnika
	 */
	public Set<AbstractUser> findAllFriends(Long userId, Pageable pageRequest){
		List<Friendship> approvedFriendships = new ArrayList<>();
		if(pageRequest == null) {
			approvedFriendships = friendshipRepository.findAllByAcceptedIsTrueAndSenderIdOrAcceptedIsTrueAndReceiverId(userId, userId);
		}
		else {
			approvedFriendships = friendshipRepository.findAllByAcceptedIsTrueAndSenderIdOrAcceptedIsTrueAndReceiverId(userId, userId, pageRequest).getContent();
		}
			
		Set<AbstractUser> friends = extractUsersDifferentThanUserId(userId, approvedFriendships);		
		return friends;
	}
	
	/**
	 * Nadji sve korisnike koji mene traze za prijatelja
	 * @param receiverId - id mene kao primaoca
	 * @param pageRequest - stranica koja ako se prosledi null trazi sve, ako ne, onda po kriterijumu poslatom po REST-u
	 * @return - skup korisnika koji cekaju na moje prijateljstvo
	 */
	public Set<AbstractUser> findMyFriendRequests(Long receiverId, Pageable pageRequest){
		Set<AbstractUser> friendRequests = new HashSet<>();
		List<Friendship> friendshipsPending = new ArrayList<>();
		if(pageRequest == null) {
			friendshipsPending = friendshipRepository.findAllByAcceptedIsFalseAndReceiverId(receiverId);
		}
		else {
			//ako je pageable
			Page<Friendship> pageRequests = friendshipRepository.findAllByAcceptedIsFalseAndReceiverId(receiverId, pageRequest);
			friendshipsPending = pageRequests.getContent();
		}
		
		for (Friendship friendship : friendshipsPending) {
			AbstractUser friend = friendship.getSender();
			friendRequests.add(friend);
		}
		
		return friendRequests;		
	}
	
	/**
	 * Nadji sve korisnike kojima sam ja poslao zahtev za prijateljstvo, a nisu prihvatili
	 * @param senderId - moj id naloga
	 * @param pageRequest - ako se ostavi null trazi sve prijatelje
	 * @return
	 */
	public Set<AbstractUser> findFriendsPending(Long senderId, Pageable pageRequest){
		Set<AbstractUser> friendsPending = new HashSet<>();
		List<Friendship> friendshipsPending = new ArrayList<>();
		
		if(pageRequest == null) {
			friendshipsPending = friendshipRepository.findAllByAcceptedIsFalseAndSenderId(senderId);
		}
		else {
			friendshipsPending = friendshipRepository.findAllByAcceptedIsFalseAndSenderId(senderId, pageRequest).getContent();
		}
		
		for (Friendship friendship : friendshipsPending) {
			AbstractUser friend = friendship.getReceiver();
			friendsPending.add(friend);
		}
		
		return friendsPending;	
	}
	
	/**
	 * Trazi prijatelje ili sve ucesnike potencijalnih prijateljstava po kljucnoj reci
	 * @param userId - korisnik za kojeg se kopa po prijateljstvima
	 * @param accepted - da li nam trebaju samo prijatelji ili i oni sto su u odnosu sa nama
	 * @param keyword - kljucna rec
	 * @param pageRequest - ako se ostavi null trazi sve prijatelje
	 * @return prijatelje ili korisnike koji su u nekom odnosu sa nama
	 */
	public Set<AbstractUser> findFriendshipsByKeyword(Long userId, Boolean accepted, String keyword, Pageable pageRequest){
		List<Friendship> userRelations = new ArrayList<>();
		Set<AbstractUser> relatedUsers = new HashSet<>(); 
		if(pageRequest == null) {
			userRelations = friendshipRepository.findAllBySenderIdAndAcceptedOrReceiverIdAndAccepted(accepted, userId, accepted, userId);
		}
		else {
			userRelations = friendshipRepository.findAllBySenderIdAndAcceptedOrReceiverIdAndAccepted(accepted, userId, accepted, userId, pageRequest).getContent();
		}

	
		
		relatedUsers = extractUsersDifferentThanUserId(userId, userRelations);
		
		relatedUsers.removeIf(user -> !user.getFirstName().contains(keyword) && !user.getLastName().contains(keyword));
		return relatedUsers;
	}
	
	
	/**
	 * Parametrizovana pretraga
	 * @param userId
	 * @param accepted
	 * @param sender
	 * @param receiver
	 * @param keyword
	 * @param pageRequest
	 * @return
	 */
	public Set<AbstractUser> getFriendsByCriteria(Long userId,Boolean accepted, Boolean sender, Boolean receiver, String keyword,Pageable pageRequest){
		List<Friendship> receiverFriendships = new ArrayList<>();
		List<Friendship> senderFriendships = new ArrayList<>();
		System.out.println("UserId je: " + userId);
		if(sender) {
			
			senderFriendships = friendshipRepository.findAllByAcceptedAndSenderId(accepted, userId, pageRequest).getContent();
			System.out.println("Velicina senderFriendships: " + senderFriendships.size());
		}
		if(receiver) {
			receiverFriendships = friendshipRepository.findAllByAcceptedAndReceiverId(accepted, userId, pageRequest).getContent();
			System.out.println("Velicina senderFriendships: " + receiverFriendships.size());

		}
		
		Set<AbstractUser> receiverFriends = extractUsersDifferentThanUserId(userId, receiverFriendships);
		Set<AbstractUser> senderFriends = extractUsersDifferentThanUserId(userId, senderFriendships);
		
		Set<AbstractUser> totalFriends = new HashSet<>();
		
		System.out.println("Parametri su " + receiver + " " + sender + " " + accepted);
		
		//proveri sta je obelezeno
		if(receiver && !sender) {
			return receiverFriends;
		}
		if(!receiver && sender) {
			return senderFriends;
		}
		if(!receiver && !sender) {			
			totalFriends.addAll(senderFriends);
			totalFriends.addAll(receiverFriends);
		}
		if(receiver && sender) {
			totalFriends.addAll(senderFriends);
			totalFriends.addAll(receiverFriends);
		}		
		for (AbstractUser abstractUser : totalFriends) {
			System.out.println(abstractUser);
		}
		//proveri keyword
		totalFriends.removeIf(user -> !user.getFirstName().contains(keyword) && !user.getLastName().contains(keyword));
		
		return totalFriends;		
	}
	
	/**
	 * Ekstraktuju se korisnici u relacijama koji nisu korisnik zadat sa {@code userId}
	 * @param userId - korisnik za kojeg se trazi
	 * @param approvedFriendships - veze u kojima se pominje
	 * @return
	 */
	public Set<AbstractUser> extractUsersDifferentThanUserId(Long userId, List<Friendship> approvedFriendships){
		Set<AbstractUser> friends = new HashSet<AbstractUser>();
		for (Friendship friendship : approvedFriendships) {
			AbstractUser friend = null;
			
			if(!friendship.getReceiver().getId().equals(userId)) {
				friend = friendship.getReceiver();
			}
			else if(!friendship.getSender().getId().equals(userId)) {
				friend = friendship.getSender();
			}
			friends.add(friend);
		}
		return friends;
	}

	public boolean exists(Friendship friendship) {
		Long senderId = friendship.getSender().getId();
		Long receiverId = friendship.getReceiver().getId();
		Friendship oneside = friendshipRepository.findByReceiverIdAndSenderId(receiverId, senderId);
		Friendship otherside = friendshipRepository.findBySenderIdAndReceiverId(senderId, receiverId);
		
		
		return (oneside != null || otherside != null);
 	}
}
