package com.rk.apiwiz.serviceImpl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.apiwiz.dto.CommentDto;
import com.rk.apiwiz.exception.PostNotFoundException;
import com.rk.apiwiz.exception.UserNotFoundException;
import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.Post;
import com.rk.apiwiz.model.User;
import com.rk.apiwiz.repository.CommentRepo;
import com.rk.apiwiz.repository.PostRepo;
import com.rk.apiwiz.repository.UserRepo;
import com.rk.apiwiz.service.CommentService;
import com.rk.apiwiz.transformer.CommentTransformer;


@Service
public class CommentServiceImpl implements CommentService {


	final  PostRepo postRepo;
	
    final UserRepo userRepo;
    
    final CommentRepo commentRepo;

	
	
	@Autowired
	public CommentServiceImpl(PostRepo postRepo,UserRepo userRepo,CommentRepo commentRepo){
		this.postRepo = postRepo;
		this.userRepo=userRepo;
		this.commentRepo = commentRepo;
	}
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {

		Optional<Post> post = postRepo.findById(postId);
		
		if(post.isEmpty())
			throw new PostNotFoundException("Invalid Post Id!!");
		
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		
		Comment comment = CommentTransformer.CommentDtoToComment(commentDto);

		
		comment.setPost(post.get());
		comment.setUser(user.get());

		Comment savedComment = this.commentRepo.save(comment);

		return CommentTransformer.CommentToCommentDto(savedComment);
	}

//	@Override
//	public void deleteComment(Integer commentId) {
//		
//		Comment com = this.commentRepo.findById(commentId)
//				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
//		this.commentRepo.delete(com);
//
//	}

}
