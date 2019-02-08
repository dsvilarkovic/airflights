package com.isa.airflights.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.airflights.model.Friendship;

/**
 * Repozitorijum za rad sa prijateljima
 * @author dusan
 *
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

	/**
	 * Nadji sve friendship-e gde je korisnik posiljaoc iil primaoc i gde je accepted true
	 * @param senderId - id posiljaoca
	 * @param receiverId - id primaoca
	 * @return - prijateljstva koja zadovoljavaju gornji kriterijum
	 */
	List<Friendship> findAllByAcceptedIsTrueAndSenderIdOrAcceptedIsTrueAndReceiverId(Long senderId, Long receiverId );
	
	/**
	 *  Nadji sve friendship-e gde je korisnik posiljaoc iil primaoc i gde je accepted true
	 * @param senderId - posiljaoc
	 * @param receiverId - primaoc
	 * @param pageRequest - stranica
	 * @return - prijateljstva koja zadovoljavaju gornji kriterijum
	 */
	Page<Friendship> findAllByAcceptedIsTrueAndSenderIdOrAcceptedIsTrueAndReceiverId(Long senderId, Long receiverId, Pageable pageRequest);
	
	/**
	 * Nadji sve pending zahteve za prijateljstvo koje sam ja poslao
	 * @param senderId - posiljaoc
	 * @param receiverId - primaoc
	 * @return - relacije koje zadovoljavaju uslov
	 */
	
	List<Friendship> findAllByAcceptedIsFalseAndSenderId(Long senderId );
	
	/**
	 * Nadji sve pending zahteve za prijateljstvo koje sam ja poslao
	 * @param senderId - posiljaoc
	 * @param receiverId - primaoc
	 * @param pageRequest - stranica
	 * @return- relacije koje zadovoljavaju uslov
	 */
	Page<Friendship> findAllByAcceptedIsFalseAndSenderId(Long senderId, Pageable pageRequest);
	
	/**
	 * Nadji sve pending zahteve za prijateljstvo koji mene cekaju
	 * @param receiver - id mene kao trazenog
	 * @return - listu relacija gde se spominje
	 */
	
	List<Friendship> findAllByAcceptedIsFalseAndReceiverId(Long receiverId );
	
	/**
	 * Nadji sve pending zahteve za prijateljstvo koji mene cekaju, stranicno
	 * @param receiver - id mene kao trazenog
	 * @param pageRequest
	 * @return - listu relacije gde se spominje
	 */
	Page<Friendship> findAllByAcceptedIsFalseAndReceiverId(Long receiverId, Pageable pageRequest);
	
	
	

	/**
	 * Nadji sve korisnike koji su u odnosu sa nasim korisnikom
	 * @param acceptedFirst - isti kao i {@code acceptedSecond}
	 * @param senderId - isti kao i {@code receiverId}
	 * @param acceptedSecond - isti kao i {@code acceptedFirst}
	 * @param receiverId - isti kao i {@code senderId}
	 * @return - sve zadovoljavajuce odnose
	 */
	List<Friendship> findAllBySenderIdAndAcceptedOrReceiverIdAndAccepted(Boolean acceptedFirst, Long senderId, Boolean acceptedSecond, Long receiverId );
	
	
	/**
	 * Nadji sve korisnike koji su u odnosu sa nasim korisnikom, stranicno
	 * @param acceptedFirst - isti kao i {@code acceptedSecond}
	 * @param senderId - isti kao i {@code receiverId}
	 * @param acceptedSecond - isti kao i {@code acceptedFirst}
	 * @param receiverId - isti kao i {@code senderId}
	 * @param pageRequest - stranicno gledanje
	 * @return -sve zadovoljavajuce odnose
	 */
	Page<Friendship> findAllBySenderIdAndAcceptedOrReceiverIdAndAccepted(Boolean acceptedFirst, Long senderId, Boolean acceptedSecond, Long receiverId , Pageable pageRequest);
	
	
	Page<Friendship> findAllByAcceptedAndSenderId(Boolean accepted, Long senderId, Pageable pageRequest);
	
	Page<Friendship> findAllByAcceptedAndReceiverId(Boolean accepted, Long receiverId, Pageable pageRequest);
		
	
	List<Friendship> findAllByAcceptedAndSenderId(Boolean accepted, Long senderId);
	List<Friendship> findAllByAcceptedAndReceiverId(Boolean accepted, Long receiverId);
	
	Friendship findByReceiverIdAndSenderId(Long receiverId, Long senderId);
	Friendship findBySenderIdAndReceiverId(Long senderId, Long receiverId);
	
	Friendship findByReceiverIdOrSenderId(Long receiverId, Long senderId);
	Friendship findBySenderIdOrReceiverId(Long senderId, Long receiverId);

	List<Friendship> findAllBySenderId(Long userId);

	List<Friendship> findAllByReceiverId(Long userId);
	
	
}
