package com.rk.apiwiz.dto;


import java.util.HashSet;
import java.util.Set;




import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	int userId;
	
	String userName;
	
	String email;
	
	String firstName;
	
	String lastName;
	
	String mobile;
	
	String password;
	
	String about;
	
	String image;
	
	String role;
}
