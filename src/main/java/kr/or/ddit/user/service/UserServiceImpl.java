package kr.or.ddit.user.service;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.IUserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.util.MybatisUtil;

public class UserServiceImpl implements IUserService {
	private static IUserService serv;
	private IUserDao dao;
	private UserServiceImpl() { 
		dao = UserDaoImpl.getInstance();
	}
	
	public static IUserService getInstance() {
		if(serv==null) serv = new UserServiceImpl();
		
		return serv;
	}

	@Override
	public UserVo getUser(String userId) {
		SqlSession sqlSession = MybatisUtil.getSession();
		UserVo uvo = dao.getUser(sqlSession, userId);
		sqlSession.close();
		
		return uvo;
	}
}
