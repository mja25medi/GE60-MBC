/**
 *
 */
package egovframework.edutrack.modules.teacher.aplc.service;

import java.util.List;

/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherAplcForm"
 */
@SuppressWarnings("serial")
public class TchAplcForm {

	private TchAplcVO tchAplcVO;
	private List<TchAplcVO> tchAplcList;

	public TchAplcForm() {
		this.tchAplcVO = new TchAplcVO();
	}

	public TchAplcVO getTchAplcVO() {
		return tchAplcVO;
	}

	public void setTchAplcVO(TchAplcVO tchAplcVO) {
		this.tchAplcVO = tchAplcVO;
	}

	public List<TchAplcVO> getTchAplcList() {
		return tchAplcList;
	}

	public void setTchAplcList(List<TchAplcVO> tchAplcList) {
		this.tchAplcList = tchAplcList;
	}
}