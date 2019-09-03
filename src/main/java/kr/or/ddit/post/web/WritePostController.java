package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVo;
import util.FileuploadUtil;

@WebServlet("/writePost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class WritePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IPostService serv;  
	
	@Override
	public void init() throws ServletException {
		serv = PostServiceImpl.getInstance();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNum = request.getParameter("boardNum");
	
		request.setAttribute("postNum", "");
		request.setAttribute("boardNum", boardNum);
		
		request.getRequestDispatcher("post/writePost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		UserVo uvo = (UserVo) session.getAttribute("userVo");
		PostVo pvo = new PostVo();
		int seq = serv.getPostSeq();
		String boardNum = request.getParameter("boardNum");
		String postNum2 = request.getParameter("postNum2");	
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		if(postNum2.equals("")) {
			pvo.setPostnum(seq);
			pvo.setPostnm(request.getParameter("postNm"));	
			pvo.setPostcont(request.getParameter("smarteditor"));
			pvo.setUserid(uvo.getUserId());
			pvo.setBoardnum(Integer.parseInt(request.getParameter("boardNum")));	
			
			map.put("pvo", pvo);
			
			serv.insertPost(map);
		}else {
			pvo.setPostnum(seq);
			pvo.setPostnm(request.getParameter("postNm"));	
			pvo.setPostcont(request.getParameter("smarteditor"));
			pvo.setUserid(uvo.getUserId());
			pvo.setBoardnum(Integer.parseInt(request.getParameter("boardNum")));	
			pvo.setPostnum2(Integer.parseInt(postNum2));
			pvo.setGn(Integer.parseInt(request.getParameter("gn")));	
			
			map.put("pvo", pvo);
			
			serv.insertPost2(map);
		}
		
		Collection<Part> parts = request.getParts();
		String filename = "";
		String path = "";
		
		for(Part p : parts) {
			if("attachedFile".equals(p.getName())){
				if(p.getSize()>0) {
					filename = FileuploadUtil.getFilename(p.getHeader("Content-Disposition"));	//사용자가 업로드한 파일명
					String realFilename = UUID.randomUUID().toString();
					String ext = FileuploadUtil.getFileExtentsion(p.getHeader("Content-Disposition"));
					path = FileuploadUtil.getPath() + realFilename + ext;
					
					p.write(path);
					AttachedfileVo avo = new AttachedfileVo();
					
					avo.setPostnum(seq);
					avo.setAtfpath(path);
					avo.setAtfnm(filename);
					
					serv.insertAtf(avo);
					
					String res = "저장 성공";
					
					request.setAttribute("boardNum", boardNum);
					request.setAttribute("res", res);
				}
			}
		} 
		request.setAttribute("postNum", seq);
		request.setAttribute("boardNum", boardNum);
		
		request.getRequestDispatcher("/selectPost").forward(request, response);
		
	}
}
