package com.rk.apiwiz.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Followerdata {

	@Id
	Integer userId;
	 @ElementCollection
	Set<Integer> follower = new HashSet<>();
	 @ElementCollection
	Set<Integer> following = new HashSet<>();
	
	 @ElementCollection
	Set<Integer> friends = new HashSet<>();
}
