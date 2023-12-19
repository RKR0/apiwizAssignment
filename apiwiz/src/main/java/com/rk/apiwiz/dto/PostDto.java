package com.rk.apiwiz.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rk.apiwiz.model.Post;
import com.rk.apiwiz.model.User;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	Integer postId;
	
	@NotEmpty
	 String content;
	
	String imageName;
	
	Timestamp createdAt;	
	
	boolean isPrivate;

	UserDto user;
	
	Integer likes;
	
	List<CommentDto> comments=new ArrayList<>();

}
