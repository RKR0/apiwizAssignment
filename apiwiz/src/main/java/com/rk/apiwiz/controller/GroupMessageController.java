package com.rk.apiwiz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rk.apiwiz.dto.PostDto;
import com.rk.apiwiz.service.GroupMessageService;
import com.rk.apiwiz.service.PostService;
import com.rk.apiwiz.serviceImpl.S3ServiceImpl;

@RestController
@RequestMapping("/group")
public class GroupMessageController {

	final GroupMessageService groupService;
	

	
	GroupMessageController(GroupMessageService groupService){
		this.groupService=groupService;
	}
	
	
	
	
	// create new Group 
	@PostMapping("/create/userId/{userId}")
	public ResponseEntity createGroup(@RequestBody List<Integer> list, @PathVariable Integer userId) {
		try {
					
		String msg = groupService.createGroup(list, userId);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
} 
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// update new Group 
	@PutMapping("/update/userId/{userId}/grpId/{grpId}")
	public ResponseEntity updateGroup(@RequestBody List<Integer> list, @PathVariable Integer userId,@PathVariable Integer grpId) {
		try {
					
		String msg = groupService.updateGroup(list, userId,grpId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
} 
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// remove members In Group 
	@PutMapping("/remove/userId/{userId}/grpId/{grpId}")
	public ResponseEntity removememebersGroup(@RequestBody List<Integer> list, @PathVariable Integer userId,@PathVariable Integer grpId) {
		try {
					
		String msg = groupService.removememebersGroup(list, userId,grpId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
} 
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

	// remove members In Group 
	@DeleteMapping("/delete/userId/{userId}/grpId/{grpId}")
	public ResponseEntity deleteGroup( @PathVariable Integer userId,@PathVariable Integer grpId) {
		try {
					
		String msg = groupService.deleteGroup(userId,grpId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
} 
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}	


	
	
}
