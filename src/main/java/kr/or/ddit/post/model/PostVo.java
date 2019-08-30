package kr.or.ddit.post.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostVo {

	private int postnum;
	private String postnm;
	private String postcont;
	private Date postdate;
	private String delstatus;
	private String userid;
	private int boardnum;
	private int postnum2;
	private int gn;
	private int level;
	
	public PostVo() { }

	public PostVo(int postnum, String postnm, String postcont, Date postdate, String delstatus, String userid,
			int boardnum, int postnum2, int gn) {
		this.postnum = postnum;
		this.postnm = postnm;
		this.postcont = postcont;
		this.postdate = postdate;
		this.delstatus = delstatus;
		this.userid = userid;
		this.boardnum = boardnum;
		this.postnum2 = postnum2;
		this.gn = gn;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public String getPostnm() {
		return postnm;
	}

	public void setPostnm(String postnm) {
		this.postnm = postnm;
	}

	public String getPostcont() {
		return postcont;
	}

	public void setPostcont(String postcont) {
		this.postcont = postcont;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public int getPostnum2() {
		return postnum2;
	}

	public void setPostnum2(int postnum2) {
		this.postnum2 = postnum2;
	}
	
	public int getGn() {
		return gn;
	}

	public void setGn(int gn) {
		this.gn = gn;
	}

	public String getPostdate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(postdate);
	}

}
