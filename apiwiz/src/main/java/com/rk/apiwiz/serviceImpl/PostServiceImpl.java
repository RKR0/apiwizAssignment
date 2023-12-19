package com.rk.apiwiz.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.apiwiz.dto.PostDto;
import com.rk.apiwiz.exception.PostNotFoundException;
import com.rk.apiwiz.exception.UserNotFoundException;
import com.rk.apiwiz.model.Post;
import com.rk.apiwiz.model.User;
import com.rk.apiwiz.repository.FollowerRepo;
import com.rk.apiwiz.repository.PostRepo;
import com.rk.apiwiz.repository.UserRepo;
import com.rk.apiwiz.service.PostService;
import com.rk.apiwiz.transformer.PostTransformer;





@Service
public class PostServiceImpl implements PostService {

	
	final PostRepo postRepo;	
    final UserRepo userRepo;
    final FollowerRepo followerRepo;

	@Autowired
	public PostServiceImpl(PostRepo postRepo,UserRepo userRepo,FollowerRepo followerRepo){
		this.postRepo = postRepo;
		this.userRepo=userRepo;
		this.followerRepo=followerRepo;
	}
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> user = this.userRepo.findById(userId);
        
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");

        Post post = PostTransformer.PostDtoToPost(postDto);
        post.setUser(user.get());

        Post newPost = this.postRepo.save(post);

		return PostTransformer.PostToPostDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId,Integer userId) {
		
		Optional<User> user = this.userRepo.findById(userId);
        
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		Optional<Post> post = this.postRepo.findById(postId);

		if(post.isEmpty())
			throw new PostNotFoundException("Invalid Post Id!!");
		
		if(post.get().getUser()==user.get()) {
			Post newPost = post.get();
			newPost.setContent(postDto.getContent());
			newPost.setImageName(postDto.getImageName());
			newPost.setPrivate(postDto.isPrivate());
			
			Post savedPost = postRepo.save(newPost);
			
			return PostTransformer.PostToPostDto(savedPost);
		}	
		else
			throw new UserNotFoundException("Unable Update Post by User Id!!");
	}

	@Override
	public String deletePost(Integer postId,Integer userId) {
	
		Optional<User> user = this.userRepo.findById(userId);
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
	Optional<Post> post = this.postRepo.findById(postId);
	if(post.isEmpty())
		throw new PostNotFoundException("Invalid Post Id!!");
	
	if(post.get().getUser()==user.get() || user.get().getRole()=="ROLE_ADMIN") {
		postRepo.deleteById(postId);
		return "Sucessfully Deleted!!";
	}
        
		return "Unable Delete Post";
	}

	
	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Optional<Post> post = this.postRepo.findById(postId);
        
		if(post.isEmpty())
			throw new PostNotFoundException("Invalid Post Id!!");
		
		return PostTransformer.PostToPostDto(post.get());
	}


	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		
		Optional<User> user = this.userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		List<Post> post = this.postRepo.findByUser(user.get());
		
		List<PostDto> postDto = new ArrayList<>();
		
		for(Post p:post) {
			postDto.add(PostTransformer.PostToPostDto(p));
		}
				
		return postDto;
	}

	@Override
	public Integer likepost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		
		Optional<Post> post = this.postRepo.findById(postId);
        
		if(post.isEmpty())
			return 0;
		
		Set<Integer> ans = post.get().getLikes();
		
		ans.add(userId);
		
		post.get().setLikes(ans);
		
		Post savedpost =  postRepo.save(post.get());
		
		return savedpost.getLikes().size();
	}

	@Override
	public Integer unLikepost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Optional<Post> post = this.postRepo.findById(postId);
        
		if(post.isEmpty())
			return 0;
		
		Set<Integer> ans = post.get().getLikes();
		
		ans.remove(userId);
		
		post.get().setLikes(ans);
		
		Post savedpost =  postRepo.save(post.get());
		
		return savedpost.getLikes().size();
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		
		List<Post> post = postRepo.searchPosts(keyword);
		
		List<PostDto> ans = new ArrayList<>();
		
		for(Post p: post) {
			ans.add(PostTransformer.PostToPostDto(p));
		}
		
		return ans;
	}


	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		
		List<Post> post = postRepo.getAllPost();
		
		List<PostDto> ans = new ArrayList<>();
		
		for(Post p: post) {
			ans.add(PostTransformer.PostToPostDto(p));
		}
		
		return ans;
	}


	@Override
	public List<PostDto> getPostsByfollowings(Integer userId) {


		Set<Integer> ans1 = followerRepo.getAllFollowingUsers(userId);
		
		List<Post> post = postRepo.follwingPosts(ans1);
		
		List<PostDto> ans = new ArrayList<>();
		
		for(Post p: post) {
			ans.add(PostTransformer.PostToPostDto(p));
		}
		
		
		return ans;
	}


	@Override
	public List<PostDto> getPostsByfriends(Integer userId) {

		Set<Integer> ans1 = followerRepo.getAllFriends(userId);
		
		List<Post> post = postRepo.friendsPosts(ans1);
		
		List<PostDto> ans = new ArrayList<>();
		
		for(Post p: post) {
			ans.add(PostTransformer.PostToPostDto(p));
		}
		
		
		return ans;
	}


	@Override
	public PostDto createRePost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Optional<Post> post = this.postRepo.findById(postId);

		if(post.isEmpty())
			throw new PostNotFoundException("Invalid Post Id!!");
		Optional<User> user = this.userRepo.findById(userId);
        
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		PostDto postDto=PostTransformer.PostToPostDto(post.get());
		
		Post post1 = PostTransformer.PostDtoToPost(postDto);
				
        post1.setUser(user.get());

        Post newPost = this.postRepo.save(post1);

		return PostTransformer.PostToPostDto(newPost);
		
	}
	
	
		

}
