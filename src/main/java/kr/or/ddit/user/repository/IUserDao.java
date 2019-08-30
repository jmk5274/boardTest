package kr.or.ddit.user.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;

public interface IUserDao {

	public UserVo getUser(SqlSession sqlSession,  String userId);
	
}
