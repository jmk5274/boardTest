package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.CommentsVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

/**
 * Servlet implementation class SelectPost
 */
@WebServlet("/selectPost")
public class SelectPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IPostService postServ;   
	
    @Override
    public void init() throws ServletException {
    	postServ = PostServiceImpl.getInstance();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNum = request.getParameter("postNum");
		String boardNum = request.getParameter("boardNum");
		
		if(postNum==null) {
			postNum = (String) request.getAttribute("postNum");
		}
		
		Map<String, Object> map = postServ.selectPost(Integer.parseInt(postNum));
		
		PostVo pvo = (PostVo) map.get("pvo");
		List<CommentsVo> cmtList = (List<CommentsVo>) map.get("cmtList");
		
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("pvo", pvo);
		request.setAttribute("cmtList", cmtList);
		
		request.getRequestDispatcher("/post/selectPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String postNum = (String) request.getAttribute("postNum");
		request.setAttribute("postNum", postNum);

		doGet(request, response);
	}

}
