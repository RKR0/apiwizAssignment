package com.rk.apiwiz.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.rk.apiwiz.dto.UserDto;
import com.rk.apiwiz.exception.UserNotFoundException;
import com.rk.apiwiz.model.Followerdata;
import com.rk.apiwiz.model.User;
import com.rk.apiwiz.repository.FollowerRepo;
import com.rk.apiwiz.repository.UserRepo;
import com.rk.apiwiz.service.UserService;
import com.rk.apiwiz.transformer.UserTransformer;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	FollowerRepo followerRepo;
	
   

    // New User register
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		// Transfer userDto to user
		User user = UserTransformer.UserDtoToUser(userDto);
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		// save user to database
		User ans = userRepo.save(user);
		
		// Transfer userDto to user
		UserDto updateuserDto = UserTransformer.UserToUserDto(ans);
		
		// create followerData with userId
		Followerdata follower = new  Followerdata();
		follower.setUserId(ans.getUserId());
		follower.setFollower(new HashSet<>());
		follower.setFollowing(new HashSet<>());
		follower.setFriends(new HashSet<>());
		followerRepo.save(follower);
		
		return updateuserDto;
}

	// Verify user_name is available or not 
	@Override
	public boolean userNameIsAvailable(String userName) {
	
		Optional<User> user= userRepo.findUser_name(userName);
		
		if(user.isEmpty())
			return true;
		return false;
	}

	// Verify email is already Exist in database 	
	@Override
	public boolean emailIsExist(String email) {

		Optional<User> user= userRepo.findByEmail(email);
		
		if(user.isEmpty())
			return false;
		return true;
	}

	// send OTP to email verification
	@Override
	public int emailVerification(String email) {
		
		
		
		 Random random = new Random();
	     int otp = 100000 + random.nextInt(900000);
		
		String text = " Hi !!"

		+"\n\n Thanks for getting started with our Social Media platform! "

		+"\n\n To complete your registration, please enter the OTP code below to verify your email address "

		+"\n\n OTP Code: "+ otp

		+"\n\n Thank you for joining us! If you have any questions or need assistance, feel free to reach out. "

		+"\n\n\nBest regards,\nSocial Media platform";
		
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	        simpleMailMessage.setFrom("kanna51198@gmail.com");
	        simpleMailMessage.setTo(email);
	        simpleMailMessage.setSubject("Email Verification");
	        simpleMailMessage.setText(text);

	        javaMailSender.send(simpleMailMessage);
		
	     return otp;
		
	}

	// get user data by userId
	@Override
	public UserDto getuserById(Integer userId) {
		// TODO Auto-generated method stub
		
		Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		return  UserTransformer.UserToUserDto(user.get());
	}

	// get all Users
	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> user= userRepo.findAll();
		
		List<UserDto> userDto = new ArrayList<>();
		for(User u:user) {
			userDto.add(UserTransformer.UserToUserDto(u));
		}
		
		return userDto;
	}
	
	// delete User by UserId
	@Override
	public boolean deleteUser(Integer userId) {
		
		Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			return false;
		
		userRepo.deleteById(userId);
		
		return true;
	}

	// Update user data
	@Override
	public UserDto updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		Optional<User> user= userRepo.findById(userDto.getUserId());
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		User updateuser = user.get();
		
		updateuser.setAbout(userDto.getAbout());
		updateuser.setFirstName(userDto.getFirstName());
		updateuser.setLastName(userDto.getLastName());
		updateuser.setMobile(userDto.getMobile());
		updateuser.setPassword(userDto.getPassword());
		
		User ans = userRepo.save(updateuser);
		
		UserDto updateuserDto = UserTransformer.UserToUserDto(ans);
		
		return updateuserDto;
	}

	// followingId following by userId 
	@Override
	public String following(Integer userId, Integer followingId) {
		// TODO Auto-generated method stub
		
		// check userId and Following Id exist
		Optional<Followerdata> user= followerRepo.findById(userId);
		Optional<Followerdata> user1= followerRepo.findById(followingId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		if(user1.isEmpty())
			throw new UserNotFoundException("Invalid following Id!!");
		
		// get userId followerData table and followingId followerData
		Followerdata data = user.get();
		
		Followerdata data1 = user1.get();
		
		Set<Integer> followings = data.getFollowing();
		
		Set<Integer> followers = data.getFollowing();
		
		Set<Integer> friends = data.getFollowing();
		
		Set<Integer> followings1 = data1.getFollowing();
		
		Set<Integer> followers1 = data1.getFollowing();
		
		Set<Integer> friends1 = data1.getFollowing();
		
		// add followingId to user followingList
		followings.add(followingId);
		
		// add UserId to followingUser followerList
		followers1.add(userId);
		
		// followingId is already in user follower list they will become friends 
		if(followers.contains(followingId)) {
			friends.add(followingId);
			friends1.add(userId);
		}
		
		data.setFollowing(followings);
		data.setFollower(followers);
		data.setFriends(friends);
		
		data1.setFollowing(followings1);
		data1.setFollower(followers1);
		data1.setFriends(friends1);
		
		followerRepo.save(data);
		followerRepo.save(data1);
		
		
		return "Sucessfully added!";
	}

	// get all following list
	@Override
	public List<UserDto> getAllFollowingUsers(Integer userId) {
		
		Set<Integer> user= followerRepo.getAllFollowingUsers(userId);
		
		List<UserDto> userDto = new ArrayList<>();
		for(Integer u:user) {
			Optional<User> user1= userRepo.findById(u);
			if(!user1.isEmpty())
				userDto.add(UserTransformer.UserToUserDto(user1.get()));
		}
		
		return userDto;
	}
	
	// get all follower  list
	@Override
	public List<UserDto> getAllFollowersUsers(Integer userId) {
		
		Set<Integer> user= followerRepo.getAllFollowers(userId);
		
		List<UserDto> userDto = new ArrayList<>();
		for(Integer u:user) {
			Optional<User> user1= userRepo.findById(u);
			if(!user1.isEmpty())
				userDto.add(UserTransformer.UserToUserDto(user1.get()));
		}
		
		return userDto;
	}

	// get all friends list
	@Override
	public List<UserDto> getAllFriendsUsers(Integer userId) {
		
		Set<Integer> user= followerRepo.getAllFriends(userId);
		
		List<UserDto> userDto = new ArrayList<>();
		
		for(Integer u:user) {
			Optional<User> user1= userRepo.findById(u);
			if(!user1.isEmpty())
				userDto.add(UserTransformer.UserToUserDto(user1.get()));
		}
		
		return userDto;
	}

	@Override
	public List<UserDto> searchuser_name(String user_name) {
		
		List<User> user= userRepo.searchUsername(user_name);
		
		List<UserDto> userDto = new ArrayList<>();
		for(User u:user) {
			userDto.add(UserTransformer.UserToUserDto(u));
		}
		
		return userDto;
	}

}
