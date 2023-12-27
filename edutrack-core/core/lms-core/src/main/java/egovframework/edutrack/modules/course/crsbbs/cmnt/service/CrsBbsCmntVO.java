package egovframework.edutrack.modules.course.crsbbs.cmnt.service;

import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclVO;

public class CrsBbsCmntVO extends CrsBbsAtclVO{

	private static final long serialVersionUID = -1116876033318273480L;

	private Integer cmntSn;
	private String  cmntCts;
	private Integer emoticonNo;
	
	public Integer getCmntSn() {
		return cmntSn;
	}
	public void setCmntSn(Integer cmntSn) {
		this.cmntSn = cmntSn;
	}
	public String getCmntCts() {
		return cmntCts;
	}
	public void setCmntCts(String cmntCts) {
		this.cmntCts = cmntCts;
	}
	public Integer getEmoticonNo() {
		return emoticonNo;
	}
	public void setEmoticonNo(Integer emoticonNo) {
		this.emoticonNo = emoticonNo;
	}
}
