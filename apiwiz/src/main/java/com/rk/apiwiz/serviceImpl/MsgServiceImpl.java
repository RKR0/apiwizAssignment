package com.rk.apiwiz.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.apiwiz.dto.MsgDto;
import com.rk.apiwiz.exception.UserNotFoundException;
import com.rk.apiwiz.model.Comment;
import com.rk.apiwiz.model.GroupMessage;
import com.rk.apiwiz.model.Message;
import com.rk.apiwiz.model.User;
import com.rk.apiwiz.repository.GroupMessageRepo;
import com.rk.apiwiz.repository.MsgRepo;
import com.rk.apiwiz.repository.UserRepo;
import com.rk.apiwiz.service.MsgService;
import com.rk.apiwiz.transformer.CommentTransformer;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	MsgRepo msgRepo;
	
	@Autowired
	GroupMessageRepo groupMessageRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<MsgDto> createMsg(MsgDto msgDto) {
		// TODO Auto-generated method stub
		Message msd =  new Message();
		Optional<User> user1= userRepo.findById(msgDto.getSenderId());
		
		if(user1.isEmpty())
			throw new UserNotFoundException("Invalid Sender User Id!!");
		msd.setSender(user1.get());
		
		if(msgDto.getGroupId()==null &&  msgDto.getReciverId()!=null){
			Optional<User> user2= userRepo.findById(msgDto.getReciverId());
			
			if(user2.isEmpty())
				throw new UserNotFoundException("Invalid User Id!!");
			msd.setReciver(null);
			
		}
				
		else if(msgDto.getGroupId()!=null && msgDto.getSenderId()!=null) {
			
			Optional<GroupMessage> grp= groupMessageRepo.findById(msgDto.getGroupId());
			
			if(grp.isEmpty())
				throw new UserNotFoundException("Invalid Group Id!!");
			msd.setGroup(grp.get());
			
			
		}
		else
			throw new UserNotFoundException("some error Id!!");
		
		msd.setContent(msgDto.getContent());

		Message msg = msgRepo.save(msd);
		
		
		return null;
	}

	@Override
	public List<MsgDto> getallmsggrp(Integer grpId) {
		
		List<Message> msg= msgRepo.getallmsggrp(grpId);
		
		List<MsgDto> msh12 = new ArrayList<>();
		
		for(Message m:msg) {
			msh12.add(CommentTransformer.MsgToMsgDto(m));
		}
		
		return msh12;
	}

	@Override
	public List<MsgDto> getallmsg(Integer senderId, Integer reciverId) {
		
		List<Message> msg= msgRepo.getallmsg(senderId,reciverId);
		
		List<MsgDto> msh12 = new ArrayList<>();
		
		for(Message m:msg) {
			msh12.add(CommentTransformer.MsgToMsgDto(m));
		}
		
		return msh12;
		

	}

}
