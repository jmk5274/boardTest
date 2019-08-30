package kr.or.ddit.user.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;

public class UserDaoImpl implements IUserDao {

	private static IUserDao dao;
	
	private UserDaoImpl() { }
	
	public static IUserDao getInstance() {
		if(dao==null) dao = new UserDaoImpl();
		return dao;
	}
	
	@Override
	public UserVo getUser(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

}
