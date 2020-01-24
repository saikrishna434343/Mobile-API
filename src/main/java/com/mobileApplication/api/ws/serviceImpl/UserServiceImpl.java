package com.mobileApplication.api.ws.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileApplication.api.ws.UserRepository;
import com.mobileApplication.api.ws.io.entity.UserEntity;
import com.mobileApplication.api.ws.service.UserService;
import com.mobileApplication.api.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
		
	
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId("101");
		
		UserEntity storedDetails = userRepository.save(userEntity);
		
		UserDto userdto = new UserDto();
		BeanUtils.copyProperties(storedDetails, userdto);
		
		return userdto;
	}
	
}
