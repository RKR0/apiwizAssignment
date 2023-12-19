package com.rk.apiwiz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.apiwiz.dto.MsgDto;
import com.rk.apiwiz.service.GroupMessageService;
import com.rk.apiwiz.service.MsgService;

@RestController
@RequestMapping("/msg")
public class MsgController {

final MsgService messageService;
	

	
MsgController(MsgService messageService){
		this.messageService=messageService;
	}


// create new Message 
@PostMapping("/create")
public ResponseEntity createMsg(@RequestBody MsgDto msgDto) {
	try {
				
	List<MsgDto> msg = messageService.createMsg(msgDto);
	return new ResponseEntity<>(msg, HttpStatus.CREATED);
} 
	catch(Exception e){
		return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}

@GetMapping("/grp/{grpId}")
public ResponseEntity getallmsggrp(@PathVariable Integer grpId) {
	try {
				
	List<MsgDto> msg = messageService.getallmsggrp(grpId);
	return new ResponseEntity<>(msg, HttpStatus.CREATED);
} 
	catch(Exception e){
		return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}

@GetMapping("/one/{senderId}/{reciverId}")
public ResponseEntity getallmsg(@PathVariable Integer senderId,@PathVariable Integer reciverId) {
	try {
				
	List<MsgDto> msg = messageService.getallmsg(senderId,reciverId);
	return new ResponseEntity<>(msg, HttpStatus.CREATED);
} 
	catch(Exception e){
		return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}




}
