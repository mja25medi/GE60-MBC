/**
 *
 */
package egovframework.edutrack.modules.teacher.field.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherFieldForm"
 */
@SuppressWarnings("serial")
public class TchFieldForm {

	private TchFieldVO tchFieldVO;
	private List<TchFieldVO> tchFieldList;

	public TchFieldForm() {
		this.tchFieldVO = new TchFieldVO();
	}

	public TchFieldVO getTchFieldVO() {
		return tchFieldVO;
	}

	public void setTchFieldVO(TchFieldVO tchFieldVO) {
		this.tchFieldVO = tchFieldVO;
	}

	public List<TchFieldVO> getTchFieldList() {
		return tchFieldList;
	}

	public void setTchFieldList(List<TchFieldVO> tchFieldList) {
		this.tchFieldList = tchFieldList;
	}
}