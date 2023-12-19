package com.rk.apiwiz.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.apiwiz.exception.UserNotFoundException;
import com.rk.apiwiz.model.GroupMessage;
import com.rk.apiwiz.model.User;
import com.rk.apiwiz.repository.GroupMessageRepo;
import com.rk.apiwiz.repository.UserRepo;
import com.rk.apiwiz.service.GroupMessageService;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {

	@Autowired
	GroupMessageRepo groupMessageRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public String createGroup(List<Integer> list, Integer userId) {
		
		GroupMessage gp = new GroupMessage();
		
		Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		Set<User> u = new HashSet<>();
		
		for(int num:list) {
			Optional<User> user1= userRepo.findById(userId);
			if(!user1.isEmpty())
				u.add(user1.get());
				
		}
		u.add(user.get());
		gp.setAdmin(user.get());
		
		groupMessageRepo.save(gp);
		return "Sucessfully Created";
		
	}

	@Override
	public String updateGroup(List<Integer> list, Integer userId,Integer grpId) {
		// TODO Auto-generated method stub
		
Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		Optional<GroupMessage> grp= groupMessageRepo.findById(grpId);
		
		if(grp.isEmpty())
			throw new UserNotFoundException("Invalid Group Id!!");
		
		if(grp.get().getAdmin()==user.get()) {
			
			Set<User> u = grp.get().getUsers();
			
			for(int num:list) {
				Optional<User> user1= userRepo.findById(userId);
				if(!user1.isEmpty())
					u.add(user1.get());
					
			}
			grp.get().setUsers(u);
			groupMessageRepo.save(grp.get());
			return "Sucessfully  Updated Group Members";
		}
			
		
		return "Unable to Update Group Members";
	}

	@Override
	public String removememebersGroup(List<Integer> list, Integer userId,Integer grpId) {
		// TODO Auto-generated method stub
Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		Optional<GroupMessage> grp= groupMessageRepo.findById(grpId);
		
		if(grp.isEmpty())
			throw new UserNotFoundException("Invalid Group Id!!");
		
		if(grp.get().getAdmin()==user.get()) {
			
			Set<User> u = grp.get().getUsers();
			
			for(int num:list) {
				Optional<User> user1= userRepo.findById(userId);
				if(!user1.isEmpty())
					u.remove(user1.get());
					
			}
			grp.get().setUsers(u);
			groupMessageRepo.save(grp.get());
			return "Sucessfully  removed Group Members";
		}
			
		
		return "Unable to remove Group Members";
		
	}

	@Override
	public String deleteGroup(Integer userId,Integer grpId) {
		// TODO Auto-generated method stub
		
		Optional<User> user= userRepo.findById(userId);
		
		if(user.isEmpty())
			throw new UserNotFoundException("Invalid User Id!!");
		
		Optional<GroupMessage> grp= groupMessageRepo.findById(grpId);
		
		if(grp.isEmpty())
			throw new UserNotFoundException("Invalid Group Id!!");
		
		if(grp.get().getAdmin()==user.get())
			groupMessageRepo.deleteById(grpId);
		
		return "Unable to Delete the Group";
	}
	
	

}
