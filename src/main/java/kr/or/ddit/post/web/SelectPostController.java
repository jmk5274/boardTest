package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/selectPost")
public class SelectPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SelectPostController.class);
    private IPostService postServ;   
	
    @Override
    public void init() throws ServletException {
    	postServ = PostServiceImpl.getInstance();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String postNum = request.getParameter("postNum");
		String boardNum = request.getParameter("boardNum");
		
		if(postNum==null || postNum.equals("")) {
			postNum = (String) request.getAttribute("postNum");
		}
		
		if(postNum.equals("삭제")) {
			String res = "삭제된 게시물입니다";
			request.setAttribute("boardNum", boardNum);
			request.setAttribute("res", res);
			request.getRequestDispatcher("/post").forward(request, response);
		}else {
			logger.debug("postNum : {}", postNum);
			
			Map<String, Object> map = postServ.selectPost(Integer.parseInt(postNum));
			
			PostVo pvo = (PostVo) map.get("pvo");
			List<CommentsVo> cmtList = (List<CommentsVo>) map.get("cmtList");
			List<AttachedfileVo> atfList = postServ.getAttachedFile(Integer.parseInt(postNum));
			
			request.setAttribute("atfList", atfList);
			request.setAttribute("boardNum", boardNum);
			request.setAttribute("pvo", pvo);
			request.setAttribute("cmtList", cmtList);
			request.setAttribute("boardnm", request.getParameter("boardNm"));
			
			request.getRequestDispatcher("/post/selectPost.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String postNum = String.valueOf(request.getAttribute("postNum"));
		request.setAttribute("postNum", postNum);
		request.setAttribute("boardNum", request.getAttribute("boardNum"));
		
		doGet(request, response);
	}

}
