/**
 *
 */
package egovframework.edutrack.modules.teacher.hsty.service;


import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherHstyForm"
 */
@SuppressWarnings("serial")
public class TchHstyForm {

	private TchHstyVO tchHstyVO;
	private List<TchHstyVO> tchHstyList;

	/**
	 * 생성자
	 */
	public TchHstyForm() {
		this.tchHstyVO = new TchHstyVO();
	}

	public TchHstyVO getTchHstyVO() {
		return tchHstyVO;
	}

	public void setTchHstyVO(TchHstyVO tchHstyVO) {
		this.tchHstyVO = tchHstyVO;
	}

	public List<TchHstyVO> getTchHstyList() {
		return tchHstyList;
	}

	public void setTchHstyList(List<TchHstyVO> tchHstyList) {
		this.tchHstyList = tchHstyList;
	}
}