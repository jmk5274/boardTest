package kr.or.ddit.post.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.repository.UserDaoTest;

public class PostServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private IPostService serv;
	
	@Before
	public void setup() {
		serv = PostServiceImpl.getInstance();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void getPostListTest() {
		/***Given***/
		int boardNum = 1;
		int page = 1;
		int pagesize = 5;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardnum", boardNum);
		map.put("page", page);
		map.put("pagesize", pagesize);

		/***When***/
		List<PostVo> list = serv.getPostList(map);
		
		/***Then***/
		assertEquals(5, list.size());
	}

	@Test
	public void selectPost() {
		/***Given***/
		int postNum = 101;

		/***When***/
		Map<String, Object> map = serv.selectPost(postNum);
		PostVo pvo = (PostVo) map.get("pvo");
		List<CommentsVo> cmtList = (List<CommentsVo>) map.get("cmtList");
		
		/***Then***/
		assertEquals("테스트1", pvo.getPostnm());
		assertEquals(1, cmtList.size());
	}
	
	@Test
	public void insertCmt() {
		/***Given***/
		CommentsVo cvo = new CommentsVo(0, "테스트3", null, 101, "cony");

		/***When***/
		int cnt = serv.insertCmt(cvo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
}
