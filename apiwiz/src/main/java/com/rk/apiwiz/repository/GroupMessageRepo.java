package com.rk.apiwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.apiwiz.model.GroupMessage;
import com.rk.apiwiz.model.Post;

@Repository
public interface GroupMessageRepo extends JpaRepository<GroupMessage,Integer>{

	
}
