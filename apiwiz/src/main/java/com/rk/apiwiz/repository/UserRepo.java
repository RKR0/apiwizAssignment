package com.rk.apiwiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rk.apiwiz.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
	
    @Query("SELECT u FROM User u WHERE u.user_name = :user_name")
    Optional<User> findUser_name(@Param("user_name") String user_name);

    @Query("SELECT u FROM User u WHERE u.user_name like %:user_name%")
	List<User> searchUsername(String user_name);
}
