package com.project.MatchingPro.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
	private String errorCode;
	private String errorMsg;
	private Object data;
	//asdfasdfasdfasdf
}
