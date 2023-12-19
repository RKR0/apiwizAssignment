package com.rk.apiwiz.dto;

import java.sql.Timestamp;



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
public class MsgDto {


	Integer msgId;

	String content;

	Integer senderId;
	
	Integer reciverId;
	
	Integer groupId;

	Timestamp createdAt;
}
