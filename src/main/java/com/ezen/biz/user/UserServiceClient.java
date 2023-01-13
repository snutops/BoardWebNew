package com.ezen.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.dto.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService =(UserService)container.getBean("userService");
		
		UserVO uVo = new UserVO();
		
//		uVo.setId("test");
//		uVo.setPassword("test123");
		uVo.setId("user1");
		uVo.setPassword("user1");		

		UserVO uVo1 = userService.getUser(uVo);
		if(uVo1 != null) {
			System.out.println("로그인 성공");
			System.out.println("사용자 정보 : "+uVo1);
		}else {
			System.out.println("로그인 실패");
		}		
		container.close();
	}

}
