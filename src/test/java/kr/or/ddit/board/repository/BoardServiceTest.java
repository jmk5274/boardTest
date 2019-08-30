package kr.or.ddit.board.repository;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.repository.UserDaoTest;

public class BoardServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private IBoardService serv;
	
	@Before
	public void setup() {
		serv = BoardServiceImpl.getInstance();
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void getBoardListTest() {
		/***Given***/

		/***When***/
		List<BoardVo> list = serv.getBoardList();
		
		/***Then***/
		assertEquals(2, list.size());
	}
	
	@Test
	public void insertBoardTest() throws ParseException {
		/***Given***/
		Date date = new SimpleDateFormat("yyyyMMdd").parse("20190603");
		
		BoardVo bvo = new BoardVo(3, "테스트", "N", date, "cony");
		
		/***When***/
		int cnt = serv.insertBoard(bvo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void updateBoardTest() throws ParseException {
		/***Given***/
		Date date = new SimpleDateFormat("yyyyMMdd").parse("20190603");
		
		BoardVo bvo = new BoardVo(1, null, "Y", null, null);
		
		/***When***/
		int cnt = serv.updateBoard(bvo);
		
		/***Then***/
		assertEquals(1, cnt);
	}

}
