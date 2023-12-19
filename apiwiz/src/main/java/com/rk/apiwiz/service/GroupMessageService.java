package com.rk.apiwiz.service;

import java.util.List;

public interface GroupMessageService {

	String createGroup(List<Integer> list, Integer userId);

	String updateGroup(List<Integer> list, Integer userId, Integer grpId);

	String removememebersGroup(List<Integer> list, Integer userId,Integer grpId);

	String deleteGroup(Integer userId,Integer grpId);



}
