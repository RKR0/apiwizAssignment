package com.rk.apiwiz.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.Followerdata;
import com.rk.apiwiz.model.User;

@Repository
public interface FollowerRepo extends JpaRepository<Followerdata,Integer> {

	@Query("SELECT f.follower FROM Followerdata f WHERE f.userId = :userId")
	Set<Integer> getAllFollowers(@Param("userId")Integer userId);
	
	@Query("SELECT f.friends FROM Followerdata f WHERE f.userId = :userId")
	Set<Integer> getAllFriends(@Param("userId") Integer userId);

	@Query("SELECT f.following FROM Followerdata f WHERE f.userId = :userId")
	Set<Integer> getAllFollowingUsers(@Param("userId") Integer userId);

}
