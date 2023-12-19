package com.rk.apiwiz.service;

import java.util.List;

import com.rk.apiwiz.dto.PostDto;



public interface PostService {

	
		//create 
		PostDto createPost(PostDto postDto,Integer userId);

		//update 
		PostDto updatePost(PostDto postDto, Integer postId,Integer userId);

		// delete
		String deletePost(Integer postId,Integer userId);
		
		//get single post
		PostDto getPostById(Integer postId);
		
		//get all posts
		List<PostDto> getAllPost();
		
		//get all posts by following
		List<PostDto> getPostsByfollowings(Integer userId);
		
		//get all posts by friends
		List<PostDto> getPostsByfriends(Integer userId);
		
		//get all posts by user
		List<PostDto> getPostsByUser(Integer userId);
		
		// like post 
		Integer likepost(Integer postId,Integer userId);
		
		// unlike the post
		Integer unLikepost(Integer postId,Integer userId);
		
		//search posts
		List<PostDto> searchPosts(String keyword);
		
		//repost
		PostDto createRePost(Integer postId,Integer userId);

}
