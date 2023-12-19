package com.rk.apiwiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.Message;

public interface MsgRepo extends JpaRepository<Message,Integer>{

	@Query("SELECT m FROM Message m WHERE m.group.grpId=:grpId order by m.createdAt desc")
	List<Message> getallmsggrp(Integer grpId);

	@Query("SELECT m FROM Message m WHERE m.sender.userId=:senderId and m.reciver.userId=:reciverId order by m.createdAt desc")
	List<Message> getallmsg(Integer senderId, Integer reciverId);

}
