package com.rk.apiwiz.service;

import java.util.List;

import com.rk.apiwiz.dto.UserDto;



public interface UserService {
	
	
	UserDto registerNewUser(UserDto user);
	
	boolean userNameIsAvailable(String userName);
	
	boolean emailIsExist(String email);
	
	int emailVerification(String email);
	
	boolean deleteUser(Integer userId);
	
	UserDto updateUser(UserDto user);
	
	UserDto getuserById(Integer userId);
	
	List<UserDto> getAllUsers();

	String following(Integer userId, Integer followingId);

	List<UserDto> getAllFollowingUsers(Integer userId);

	List<UserDto> getAllFollowersUsers(Integer userId);

	List<UserDto> getAllFriendsUsers(Integer userId);

	List<UserDto> searchuser_name(String user_name);
	
	
}
