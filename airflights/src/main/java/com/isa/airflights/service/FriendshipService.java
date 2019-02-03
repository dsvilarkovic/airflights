package com.isa.airflights.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	/**
	 * Nema podrske za ovo
	 * @param friendship
	 */
	public void updateFriendship(Friendship friendship) {
		return; //nema 
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
	
	//public List<Friend> findAllBySenderId(Long id){
}
