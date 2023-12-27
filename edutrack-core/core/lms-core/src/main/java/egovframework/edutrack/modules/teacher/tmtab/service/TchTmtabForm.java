/**
 *
 */
package egovframework.edutrack.modules.teacher.tmtab.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherTmtabForm"
 */
@SuppressWarnings("serial")
public class TchTmtabForm{

	private TchTmtabVO tchTmtabVO;
	private List<TchTmtabVO> tchTmtabList;

	/**
	 * 생성자
	 */
	public TchTmtabForm() {
		this.tchTmtabVO = new TchTmtabVO();
	}

	public TchTmtabVO getTchTmtabVO() {
		return tchTmtabVO;
	}

	public void setTchTmtabVO(TchTmtabVO tchTmtabVO) {
		this.tchTmtabVO = tchTmtabVO;
	}

	public List<TchTmtabVO> getTchTmtabList() {
		return tchTmtabList;
	}

	public void setTchTmtabList(List<TchTmtabVO> tchTmtabList) {
		this.tchTmtabList = tchTmtabList;
	}

}