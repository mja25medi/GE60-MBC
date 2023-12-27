/**
 *
 */
package egovframework.edutrack.modules.teacher.writings.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherWrigingsForm"
 */
@SuppressWarnings("serial")
public class TchWritingsForm {

	private TchWritingsVO tchWritingsVO;
	private List<TchWritingsVO> tchWritingsList;

	public TchWritingsForm() {
		this.tchWritingsVO  = new TchWritingsVO();
	}

	public TchWritingsVO getTchWritingsVO() {
		return tchWritingsVO;
	}

	public void setTchWritingsVO(TchWritingsVO tchWritingsVO) {
		this.tchWritingsVO = tchWritingsVO;
	}

	public List<TchWritingsVO> getTchWritingsList() {
		return tchWritingsList;
	}

	public void setTchWritingsList(List<TchWritingsVO> tchWritingsList) {
		this.tchWritingsList = tchWritingsList;
	}

}