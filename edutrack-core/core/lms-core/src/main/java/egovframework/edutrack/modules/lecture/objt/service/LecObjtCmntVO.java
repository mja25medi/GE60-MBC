package egovframework.edutrack.modules.lecture.objt.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LecObjtCmntVO extends DefaultVO{
	
	private static final long serialVersionUID = -4554953152035890067L;
	
	private int objtCmntSn;
	private int objtSn;
	private String parCmntSn;
	private int cmntLvl;
	private String cmntCts;
	
	private String userType;

	public int getObjtCmntSn() {
		return objtCmntSn;
	}

	public void setObjtCmntSn(int objtCmntSn) {
		this.objtCmntSn = objtCmntSn;
	}

	public int getObjtSn() {
		return objtSn;
	}

	public void setObjtSn(int objtSn) {
		this.objtSn = objtSn;
	}

	public String getParCmntSn() {
		return parCmntSn;
	}

	public void setParCmntSn(String parCmntSn) {
		this.parCmntSn = parCmntSn;
	}

	public int getCmntLvl() {
		return cmntLvl;
	}

	public void setCmntLvl(int cmntLvl) {
		this.cmntLvl = cmntLvl;
	}

	public String getCmntCts() {
		return cmntCts;
	}

	public void setCmntCts(String cmntCts) {
		this.cmntCts = cmntCts;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


}
