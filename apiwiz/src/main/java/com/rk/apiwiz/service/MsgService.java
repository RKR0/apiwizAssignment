package com.rk.apiwiz.service;

import java.util.List;

import com.rk.apiwiz.dto.MsgDto;

public interface MsgService {

	List<MsgDto> createMsg(MsgDto msgDto);

	List<MsgDto> getallmsggrp(Integer grpId);

	List<MsgDto> getallmsg(Integer senderId, Integer reciverId);

}
