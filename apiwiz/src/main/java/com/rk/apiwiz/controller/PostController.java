package com.rk.apiwiz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rk.apiwiz.dto.PostDto;

import com.rk.apiwiz.service.PostService;
import com.rk.apiwiz.serviceImpl.S3ServiceImpl;



@RestController
@RequestMapping("/post")
public class PostController {

	final PostService postService;
	
	 final S3ServiceImpl s3ServiceImpl;
	
	PostController(PostService postService,S3ServiceImpl s3ServiceImpl){
		this.postService=postService;
		this.s3ServiceImpl = s3ServiceImpl;
	}
	
	// create new post 
	@PostMapping("/create/userId/{userId}")
	public ResponseEntity createPost(@RequestBody PostDto postDto, @RequestPart("file")MultipartFile file,@PathVariable Integer userId) {
		try {
			
			String mediaFileUrl= s3ServiceImpl.uploadFile(file);
			
		PostDto createPost = this.postService.createPost(postDto, userId);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
} 
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	 // get posts by userId
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get post by postId
	@GetMapping("/postId/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		try {
		PostDto postDto = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}
	
	 	// delete post by postId
		@DeleteMapping("delete/postId/{postId}/userId/{userId}")
		public ResponseEntity deletePost(@PathVariable Integer postId,@PathVariable Integer userId) {
			String msg = postService.deletePost(postId,userId);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}

		// update post
		@PutMapping("update/postId/{postId}/userId/{userId}")
		public ResponseEntity updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId,@PathVariable Integer userId) {
			try {
				PostDto updatePost = this.postService.updatePost(postDto, postId,userId);
				return new ResponseEntity(updatePost, HttpStatus.OK);
}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		// get all posts
		@GetMapping("/posts")
		public ResponseEntity getAllPost() {

			List<PostDto> posts = this.postService.getAllPost();
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		
		// search
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
			List<PostDto> result = this.postService.searchPosts(keywords);
			return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
		} 
		
		// get all posts by followings
		@GetMapping("/followingposts")
		public ResponseEntity getPostsByfollowings(@PathVariable Integer userId) {

			List<PostDto> posts = this.postService.getPostsByfollowings(userId);
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		
		
		// get all posts by friends
		@GetMapping("/friendsposts")
		public ResponseEntity getPostsByfriends(@PathVariable Integer userId) {

			List<PostDto> posts = this.postService.getPostsByfriends(userId);
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		
		// like post
		@PutMapping("like/postId/{postId}/userId/{userId}")
		public ResponseEntity likePost(@PathVariable Integer postId,@PathVariable Integer userId) {
			try {
				Integer updatePost = postService.likepost(postId,userId);
				return new ResponseEntity(updatePost, HttpStatus.OK);
}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		
		// unlike post
		@PutMapping("unlike/postId/{postId}/userId/{userId}")
		public ResponseEntity unLikePost(@PathVariable Integer postId,@PathVariable Integer userId) {
			try {
				Integer updatePost = postService.unLikepost(postId,userId);
				return new ResponseEntity(updatePost, HttpStatus.OK);
}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}

		// repost 
		@PostMapping("/repost/userId/{userId}/postId/{postId}")
		public ResponseEntity createRePost(@PathVariable Integer postId, @PathVariable Integer userId) {
			try {
			PostDto createPost = this.postService.createRePost(postId, userId);
			return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	} 
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
			
		}

	
	
}
