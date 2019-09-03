package kr.or.ddit.post.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentsVo {

	private int cmtnum;
	private String cmtcont;
	private Date cmtdate;
	private int postnum;
	private String userid;
	private String delstatus;

	public CommentsVo() {
	}

	public CommentsVo(int cmtnum, String cmtcont, Date cmtdate, int postnum, String userid) {
		this.cmtnum = cmtnum;
		this.cmtcont = cmtcont;
		this.cmtdate = cmtdate;
		this.postnum = postnum;
		this.userid = userid;
	}

	public String getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}

	public int getCmtnum() {
		return cmtnum;
	}

	public void setCmtnum(int cmtnum) {
		this.cmtnum = cmtnum;
	}

	public String getCmtcont() {
		return cmtcont;
	}

	public void setCmtcont(String cmtcont) {
		this.cmtcont = cmtcont;
	}

	public Date getCmtdate() {
		return cmtdate;
	}

	public void setCmtdate(Date cmtdate) {
		this.cmtdate = cmtdate;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCmtdate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cmtdate);
	}
	
}
