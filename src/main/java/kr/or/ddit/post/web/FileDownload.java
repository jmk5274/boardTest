package kr.or.ddit.post.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.AttachedfileVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/fileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPostService serv;

	@Override
	public void init() throws ServletException {
		serv = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String atfnum = request.getParameter("atfnum");
		
		AttachedfileVo avo = serv.selectAtf(Integer.parseInt(atfnum));
		
		ServletOutputStream sos = response.getOutputStream();
		
		File file = new File(avo.getAtfpath());
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buff =new byte[512];
		int len = 0;
		
		while((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff,0,len);
		}
		
		fis.close();
	}

}
