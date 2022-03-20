package com.edu.capstone.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
}
