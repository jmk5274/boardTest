package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import util.FileuploadUtil;

@WebServlet("/modifyPost")
@MultipartConfig(fileSizeThreshold = 1024*1024*5,maxRequestSize = 1024*1024*5*5)
public class ModifyPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPostService serv;
	private String res;
	
	@Override
	public void init() throws ServletException {
		serv = PostServiceImpl.getInstance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardNum = request.getParameter("boardNum");
		String postNum2 = request.getParameter("postNum2");
		String gn = request.getParameter("gn");
		String postNm = request.getParameter("postNm");
		String btnValue = request.getParameter("btnValue");
		String userId = request.getParameter("userId");
		PostVo pvo = new PostVo();
		
		Map<String, Object> map = serv.selectPost(Integer.parseInt(postNum2));
		
		PostVo pvo1 = (PostVo) map.get("pvo");
		
		if(btnValue.equals("답글")) {
			request.setAttribute("boardNum", boardNum);
			request.setAttribute("postNum2", postNum2);
			request.setAttribute("gn", gn);
			
			request.getRequestDispatcher("/post/writePost.jsp").forward(request, response);
		}else if(btnValue.equals("삭제")) {
			pvo.setDelstatus("Y");
			pvo.setPostnum(Integer.parseInt(postNum2));
			
			int cnt = serv.deletePost(pvo);
			
			if(cnt>0) {
				res = "삭제 성공";
			}else {
				res = "삭제 실패";
			}
			
			request.setAttribute("res", res);
			request.setAttribute("boardNum", boardNum);
			
			request.getRequestDispatcher("/post").forward(request, response);;
			
		}else if(btnValue.equals("수정")) {
			pvo.setPostnm(postNm);
			pvo.setUserid(userId);
			pvo.setPostcont(pvo1.getPostcont());
			
			List<AttachedfileVo> atfList = serv.getAttachedFile(Integer.parseInt(postNum2));
			
			/////////
			request.setAttribute("postNum2", postNum2);
			request.setAttribute("pvo", pvo);
			request.setAttribute("atfList", atfList);
			request.setAttribute("boardNum", boardNum);
			
			request.getRequestDispatcher("post/modifyPost.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String boardNum = request.getParameter("boardNum");
		String postNum2 = request.getParameter("postNum2");
		String postNm = request.getParameter("postNm");
		String postCont = request.getParameter("smarteditor");
		
		Collection<Part> parts = request.getParts();
		String filename = "";
		String path = "";
		
		PostVo pvo = new PostVo();
		
		pvo.setPostnum(Integer.parseInt(postNum2));
		pvo.setPostnm(postNm);
		pvo.setPostcont(postCont);
		
		serv.updatePost(pvo);
		
		List<AttachedfileVo> avoList = serv.getAttachedFile(Integer.parseInt(postNum2));
		String files[] = request.getParameterValues("file");
		
		List<Integer> atfnumList = new ArrayList<Integer>();
		
		if(files==null || files.length == 0) {
			for(AttachedfileVo atfVo : avoList) {
				serv.deleteAtf(atfVo.getAtfnum());
			}
		}else {
			
			for(AttachedfileVo atfVo : avoList) {
				atfnumList.add(atfVo.getAtfnum());
			}
			
			for(int atfnum : atfnumList) {
				boolean flag = true;
				for(String filenum : files) {
					if(atfnum == Integer.parseInt(filenum)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					serv.deleteAtf(atfnum);
				}
			}
		}
		
		for(Part p : parts) {
			if("attachedFile".equals(p.getName())){
				if(p.getSize()>0) {
					filename = FileuploadUtil.getFilename(p.getHeader("Content-Disposition"));	//사용자가 업로드한 파일명
					String realFilename = UUID.randomUUID().toString();
					String ext = FileuploadUtil.getFileExtentsion(p.getHeader("Content-Disposition"));
					path = FileuploadUtil.getPath() + realFilename + ext;
					
					p.write(path);
					AttachedfileVo avo = new AttachedfileVo();
					
					avo.setPostnum(Integer.parseInt(postNum2));
					avo.setAtfpath(path);
					avo.setAtfnm(filename);
					
					serv.insertAtf(avo);
				}
			}
		} 
		
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("postNum", postNum2);
		
		request.getRequestDispatcher("/selectPost").forward(request, response);
		
	}
	
}
