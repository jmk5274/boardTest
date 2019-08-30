package kr.or.ddit.post.model;

public class AttachedfileVo {

	private int atfnum;
	private String atfnm;
	private String atfpath;
	private int postnum;
	
	public AttachedfileVo() {
	}

	public AttachedfileVo(int atfnum, String atfnm, String atfpath, int postnum) {
		this.atfnum = atfnum;
		this.atfnm = atfnm;
		this.atfpath = atfpath;
		this.postnum = postnum;
	}

	public int getAtfnum() {
		return atfnum;
	}

	public void setAtfnum(int atfnum) {
		this.atfnum = atfnum;
	}

	public String getAtfnm() {
		return atfnm;
	}

	public void setAtfnm(String atfnm) {
		this.atfnm = atfnm;
	}

	public String getAtfpath() {
		return atfpath;
	}

	public void setAtfpath(String atfpath) {
		this.atfpath = atfpath;
	}

	public int getPostnum() {
		return postnum;
	}

	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	
}
