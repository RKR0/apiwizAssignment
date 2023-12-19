package com.rk.apiwiz.transformer;

import com.rk.apiwiz.dto.CommentDto;
import com.rk.apiwiz.dto.MsgDto;
import com.rk.apiwiz.dto.UserDto;
import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.Message;
import com.rk.apiwiz.model.User;

public class CommentTransformer {


    public static Comment CommentDtoToComment(CommentDto commentDto){

        return Comment.builder()
        		.content(commentDto.getContent())
                
                .build();
    }
    
    // Convert User Class to UserDto Class
    public static CommentDto CommentToCommentDto(Comment comment){

        return CommentDto.builder()
        		.id(comment.getId())
        		.content(comment.getContent())
        		.userDto(UserTransformer.UserToUserDto(comment.getUser()))
                .build();
    }
    

    public static MsgDto MsgToMsgDto(Message msg){

        return MsgDto.builder()
        		.msgId(msg.getMsgId())
        		.content(msg.getContent())
        		.senderId(msg.getSender().getUserId())
        		.reciverId((msg.getReciver().getUserId()))
        		.groupId(msg.getGroup().getGrpId())
        		.createdAt(msg.getCreatedAt())
                .build();
    }
    
}
