package com.rk.apiwiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.apiwiz.dto.UserDto;
import com.rk.apiwiz.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService){
      this.userService = userService;
  }
	
	
	// Check UserName is available or not 
	@GetMapping("/userName/{userName}")
	public ResponseEntity userNameIsAvailable(@PathVariable String userName){
		
			boolean available = userService.userNameIsAvailable(userName);
			return new ResponseEntity(available,HttpStatus.OK);
}
	
	// Check Email is alreadyExist or not 
	@GetMapping("/email/{email}")
	public ResponseEntity emailIsExist(@PathVariable String email){
			
			boolean available = userService.emailIsExist(email);	
			return new ResponseEntity(available,HttpStatus.OK);
}
	
	@GetMapping("/verify/email/{email}")
	public ResponseEntity emailVerification(@PathVariable String email){
			
			Integer num = userService.emailVerification(email);	
			return new ResponseEntity(num,HttpStatus.OK);
}
		
	// New User register
	@PostMapping("/register")
	public ResponseEntity registerNewUser(@RequestBody UserDto userDto){
			
			try {
				UserDto createuserdto = userService.registerNewUser(userDto);
				return new ResponseEntity(createuserdto,HttpStatus.CREATED);
				
			}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
	
	
	
	// Update User Profile
	@PutMapping("/update")
	public ResponseEntity updateUser(@RequestBody UserDto userDto){
		
		try {
			UserDto updateuserdto = userService.updateUser(userDto);
			return new ResponseEntity(updateuserdto,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
	
	
	// get User by userId
	@GetMapping("/userId/{userId}")
	public ResponseEntity getuserById(@PathVariable Integer userId){
			
			try {
				UserDto user = userService.getuserById(userId);
				return new ResponseEntity(user,HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
}
		
	// get all Users
	@GetMapping("/users")
	public ResponseEntity getAllUsers(){
					
		try {
			 List<UserDto> user = userService.getAllUsers();
		 	return new ResponseEntity(user,HttpStatus.OK);
	    }
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
					
	// Delete User by userId
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/userId/{userId}")
	public ResponseEntity deleteUser(@PathVariable Integer userId){
					
		try {
			Boolean user = userService.deleteUser(userId);
			return new ResponseEntity("User Sucessfully Deleted!!",HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
				
				
	//  following User by user Id 
	@PostMapping("/userId/{userId}/followingId/{followingId}")
	public ResponseEntity following(@PathVariable Integer userId,@PathVariable Integer followingId){
					
		try {
			String msg = userService.following(userId,followingId);
			return new ResponseEntity(msg,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
	
	// get all following Users 
	@GetMapping("/following/userId/{userId}")
	public ResponseEntity getAllFollowingUsers(@PathVariable Integer userId){
					
		try {
			 List<UserDto> user = userService.getAllFollowingUsers(userId);
		 	return new ResponseEntity(user,HttpStatus.OK);
	    }
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
	
	// get all followers of User 
	@GetMapping("/followers/userId/{userId}")
	public ResponseEntity getAllFollowersUsers(@PathVariable Integer userId){
					
		try {
			 List<UserDto> user = userService.getAllFollowersUsers(userId);
		 	return new ResponseEntity(user,HttpStatus.OK);
	    }
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}	

	// get all Friends of User 
	@GetMapping("/friends/userId/{userId}")
	public ResponseEntity getAllFriendsUsers(@PathVariable Integer userId){
					
		try {
			 List<UserDto> user = userService.getAllFriendsUsers(userId);
		 	return new ResponseEntity(user,HttpStatus.OK);
	    }
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
	
	// get all Friends of User 
	@GetMapping("/user_name/{user_name}")
	public ResponseEntity searchuser_name(@PathVariable String user_name){
					
		try {
			 List<UserDto> user = userService.searchuser_name(user_name);
		 	return new ResponseEntity(user,HttpStatus.OK);
	    }
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
	
				
				
	
	
}
