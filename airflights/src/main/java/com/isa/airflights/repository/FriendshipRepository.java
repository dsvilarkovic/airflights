package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.airflights.model.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}
