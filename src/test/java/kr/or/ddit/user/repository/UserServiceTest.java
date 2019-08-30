package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

public class UserServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private IUserService serv;
	private String userId = "brownTest1";
	
	@Before
	public void setup() {
		serv = UserServiceImpl.getInstance();
	}
	
	//테스트에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		
		/***When***/
		UserVo uvo = serv.getUser(userId);
		
		/***Then***/
		assertEquals("brownTest1", uvo.getUserId());
	}

}
