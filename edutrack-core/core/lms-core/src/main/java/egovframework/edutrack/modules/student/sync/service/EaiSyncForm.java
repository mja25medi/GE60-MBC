package egovframework.edutrack.modules.student.sync.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="eaiSyncForm"
 */
@SuppressWarnings("serial")
public class EaiSyncForm {

	private EaiNkoreducompsendVO eaiNkoreducompsendVO;
	
	private List<EaiNkoreducompsendVO> eaiNkoreducompsendList;
	

	public EaiSyncForm() {
		eaiNkoreducompsendVO = new EaiNkoreducompsendVO();
	}

	
	public EaiNkoreducompsendVO getEaiNkoreducompsendVO() {
		return this.eaiNkoreducompsendVO;
	}

	
	public void setEaiNkoreducompsendVO(EaiNkoreducompsendVO eaiNkoreducompsendVO) {
		this.eaiNkoreducompsendVO = eaiNkoreducompsendVO;
	}

	
	public List<EaiNkoreducompsendVO> getEaiNkoreducompsendList() {
		return this.eaiNkoreducompsendList;
	}

	
	public void setEaiNkoreducompsendList(List<EaiNkoreducompsendVO> eaiNkoreducompsendList) {
		this.eaiNkoreducompsendList = eaiNkoreducompsendList;
	}


}