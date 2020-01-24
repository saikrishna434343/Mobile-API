package com.mobileApplication.api.ws.ui.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileApplication.api.ws.model.Response.UserRest;
import com.mobileApplication.api.ws.model.request.UserDetailsRequestModel;
import com.mobileApplication.api.ws.service.UserService;
import com.mobileApplication.api.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/check/status")
	public String getStatus() {
		return "Working";
	}
		
	@PostMapping(consumes =  {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML
		}, produces = {
				MediaType.APPLICATION_JSON, 
				MediaType.APPLICATION_XML
		})
	public UserRest addUser(@RequestBody UserDetailsRequestModel userDetails)  {
		
		UserRest uc = new UserRest();
		
		UserDto userDto = new UserDto();
		
		//Copied values from userRequest to UserDto
		BeanUtils.copyProperties(userDetails, userDto);
		
		//Create user method to create a new user
		UserDto createduser = userService.createUser(userDto);
		
		//Copied values form userDto to user rest to return values to front end
		BeanUtils.copyProperties(createduser, uc);
		
		
		return uc;
		
	}
}
