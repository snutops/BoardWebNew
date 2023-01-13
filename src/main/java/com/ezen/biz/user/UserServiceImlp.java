package com.ezen.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.UserDAO;
import com.ezen.dto.UserVO;

@Service("userService")
public class UserServiceImlp implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Override
	public UserVO getUser(UserVO user) {
		return userDAO.getUser(user);
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
