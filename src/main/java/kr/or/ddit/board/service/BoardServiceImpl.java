package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardDaoImpl;
import kr.or.ddit.board.repository.IBoardDao;
import kr.or.ddit.util.MybatisUtil;

public class BoardServiceImpl implements IBoardService {
	private static IBoardService serv;
	private IBoardDao dao;
	
	private BoardServiceImpl() { 
		dao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(serv==null) serv = new BoardServiceImpl();
		return serv;
	}

	@Override
	public List<BoardVo> getBoardList() {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<BoardVo> list = dao.getBoardList(sqlSession);
		sqlSession.close();
		return list;
	}

	@Override
	public int insertBoard(BoardVo bvo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.insertBoard(sqlSession, bvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int updateBoard(BoardVo bvo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int cnt = dao.updateBoard(sqlSession, bvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public BoardVo getBoard(int boardnum) {
		SqlSession sqlSession = MybatisUtil.getSession();
		BoardVo bvo = dao.getBoard(sqlSession, boardnum);
		sqlSession.commit();
		sqlSession.close();
		return bvo;
	}
}
