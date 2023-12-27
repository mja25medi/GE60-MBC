/**
 *
 */
package egovframework.edutrack.modules.teacher.thesis.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherThesisForm"
 */
@SuppressWarnings("serial")
public class TchThesisForm{

	private TchThesisVO tchThesisVO;
	private List<TchThesisVO> tchThesisList;

	/**
	 * 생성자
	 */
	public TchThesisForm() {
		this.tchThesisVO  = new TchThesisVO();
	}

	public TchThesisVO getTchThesisVO() {
		return tchThesisVO;
	}

	public void setTchThesisVO(TchThesisVO tchThesisVO) {
		this.tchThesisVO = tchThesisVO;
	}

	public List<TchThesisVO> getTchThesisList() {
		return tchThesisList;
	}

	public void setTchThesisList(List<TchThesisVO> tchThesisList) {
		this.tchThesisList = tchThesisList;
	}
}