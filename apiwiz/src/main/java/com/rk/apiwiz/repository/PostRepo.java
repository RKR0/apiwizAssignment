package com.rk.apiwiz.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rk.apiwiz.model.Post;
import com.rk.apiwiz.model.User;


@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

	
	public List<Post> findByUser(User user);

	@Query("SELECT p FROM Post p WHERE p.isPrivate=false")
	public List<Post> getAllPost();

	@Query("SELECT p FROM Post p WHERE p.isPrivate=false and p.content like %:keyword% ORDER By p.createdAt desc")
	public List<Post> searchPosts(String keyword);
	
	
	@Query("SELECT p FROM Post p WHERE p.user.userId IN :friends ORDER By p.createdAt desc")
	public List<Post> friendsPosts(Set<Integer> friends );

	@Query("SELECT p FROM Post p WHERE p.isPrivate=false and p.user.userId IN :following ORDER By p.createdAt desc")
	public List<Post> follwingPosts(Set<Integer> following );

}
