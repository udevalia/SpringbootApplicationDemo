package com.springbootcrud.usermodule.dao;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ResponseDto {

	Object data;

	Object errors;

	@Override
	public String toString() {
		return "ResponseDto [data=" + data + ", errors=" + errors + "]";
	}
	
}
