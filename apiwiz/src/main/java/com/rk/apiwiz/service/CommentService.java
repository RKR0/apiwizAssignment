package com.rk.apiwiz.service;

import com.rk.apiwiz.dto.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

//	void deleteComment(Integer commentId);
}
