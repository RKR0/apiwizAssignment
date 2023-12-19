package com.rk.apiwiz.transformer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.rk.apiwiz.dto.CommentDto;
import com.rk.apiwiz.dto.PostDto;
import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.Post;
import com.rk.apiwiz.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class PostTransformer {

	// Convert EmployeeDto Class to Employee Class
  public static Post PostDtoToPost(PostDto postDto){

      return Post.builder()
              .content(postDto.getContent())
              .imageName(postDto.getImageName())
              .isPrivate(postDto.isPrivate())
              .comments(new ArrayList<>())
              .likes(new HashSet<>())
              .build();

  }

  public static PostDto PostToPostDto(Post post){

	  List<Comment> comments = post.getComments();
	  
	  List<CommentDto> commentsdto = new ArrayList<>();
	  
	  if(comments.size()>0) {
	  for(Comment c:comments) {
		  commentsdto.add(CommentTransformer.CommentToCommentDto(c));
	  }}
	  
	  
      return PostDto.builder()
      		.postId(post.getPostId())
             .createdAt(post.getCreatedAt())
              .isPrivate(post.isPrivate())
              .likes(post.getLikes().size())
              .user(UserTransformer.UserToUserDto(post.getUser()))
               .comments(commentsdto)
              .build();
  }
}
