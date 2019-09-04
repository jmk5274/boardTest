package kr.or.ddit.board.repository;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.util.MybatisUtil;

public class BoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	private IBoardDao dao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		dao = BoardDaoImpl.getInstance();
		sqlSession = MybatisUtil.getSession();
	}
	
	//테스트에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	@Test
	public void getBoardListTest() {
		/***Given***/

		/***When***/
		List<BoardVo> list = dao.getBoardList(sqlSession);
		
		/***Then***/
		assertEquals(2, list.size());
	}
	
	@Test
	public void insertBoardTest() throws ParseException {
		/***Given***/
		Date date = new SimpleDateFormat("yyyyMMdd").parse("20190603");
		
		BoardVo bvo = new BoardVo(3, "테스트", "N", date, "cony");
		
		/***When***/
		int cnt = dao.insertBoard(sqlSession, bvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVo bvo = new BoardVo(1, "자유게시판", "Y", null, null);

		/***When***/
		int cnt = dao.updateBoard(sqlSession, bvo);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, cnt);
	}

	@Test
	public void getBoard() {
		/***Given***/
		int boardnum = 1;
		/***When***/
		BoardVo bvo = dao.getBoard(sqlSession, boardnum);
	
		/***Then***/
		assertEquals(1, bvo.getBoardnum());
	}
}
