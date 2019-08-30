package kr.or.ddit.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVo {

	private int boardnum;
	private String boardnm;
	private String usestatus;
	private Date boarddate;
	private String userid;
	
	public BoardVo() { }

	public BoardVo(int boardnum, String boardnm, String usestatus, Date boarddate, String userid) {
		this.boardnum = boardnum;
		this.boardnm = boardnm;
		this.usestatus = usestatus;
		this.boarddate = boarddate;
		this.userid = userid;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getBoardnm() {
		return boardnm;
	}

	public void setBoardnm(String boardnm) {
		this.boardnm = boardnm;
	}

	public String getUsestatus() {
		return usestatus;
	}

	public void setUsestatus(String usestatus) {
		this.usestatus = usestatus;
	}

	public Date getBoarddate() {
		return boarddate;
	}

	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBoarddate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(boarddate);
	}
}
