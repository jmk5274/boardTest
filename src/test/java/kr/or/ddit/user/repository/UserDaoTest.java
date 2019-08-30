package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.MybatisUtil;

public class UserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private IUserDao dao;
	private SqlSession sqlSession;
	private String userId = "brownTest1";
	
	@Before
	public void setup() {
		dao = UserDaoImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	//테스트에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	
	@Test
	public void getUserTest() {
		/***Given***/

		/***When***/
		UserVo uvo = dao.getUser(sqlSession, userId);
		
		/***Then***/
		assertEquals("brownTest1", uvo.getUserId());
	}

}
