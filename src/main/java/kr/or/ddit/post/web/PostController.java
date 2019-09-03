package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

/**
 * Servlet implementation class 자유게시판Controller
 */
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IPostService postServ;
    private IBoardService boardServ;
    
    @Override
   	public void init() throws ServletException {
    	postServ = PostServiceImpl.getInstance();
    	boardServ = BoardServiceImpl.getInstance();
   	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardNum = request.getParameter("boardNum");
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		String res = (String) request.getAttribute("res");
		
		if(boardNum==null) {
			boardNum = (String) request.getAttribute("boardNum");
		}
		
		BoardVo bvo = boardServ.getBoard(Integer.parseInt(boardNum));
		
		List<PostVo> pageList = postServ.allPostList(Integer.parseInt(boardNum));
		
		int page = pageStr==null ? 1 : Integer.parseInt(pageStr);
		int pagesize = pagesizeStr==null ? 10 : Integer.parseInt(pagesizeStr);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardNum", Integer.parseInt(boardNum));
		map.put("page", page);
		map.put("pagesize", pagesize);
		
		List<PostVo> list = postServ.getPostList(map);
		int paginationSize = (int)Math.ceil((double)pageList.size()/pagesize);
		
		request.setAttribute("boardnm", bvo.getBoardnm());
		request.setAttribute("res", res);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("postList", list);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("post/post.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNum = (String) request.getAttribute("boardNum");
		String res = (String) request.getAttribute("res");
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("res", res);
		
		doGet(request, response);
	}
	
}
