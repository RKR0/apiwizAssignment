package com.rk.apiwiz.transformer;

import com.rk.apiwiz.dto.UserDto;
import com.rk.apiwiz.model.User;

public class UserTransformer {

	 //Convert UserDto Class to User Class
    public static User UserDtoToUser(UserDto userDto){

        return User.builder()
        		.user_name(userDto.getUserName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                .mobile(userDto.getMobile())
                .password(userDto.getPassword())
                .image(userDto.getImage())
                .role("ROLE_NORMAL")
                .build();
    }
    
    // Convert User Class to UserDto Class
    public static UserDto UserToUserDto(User user){

        return UserDto.builder()
        		.userId(user.getUserId())
        		.userName(user.getUser_name())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .about(user.getAbout())
                .mobile(user.getMobile())
                .password(user.getPassword())
                .image(user.getImage())
                .role(user.getRole())
                .build();
    }
	
}
