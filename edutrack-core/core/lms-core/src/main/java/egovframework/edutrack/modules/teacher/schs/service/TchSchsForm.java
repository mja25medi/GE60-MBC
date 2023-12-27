/**
 *
 */
package egovframework.edutrack.modules.teacher.schs.service;


import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="tchSchsForm"
 */
@SuppressWarnings("serial")
public class TchSchsForm {

	private TchSchsVO tchSchsVO;
	private List<TchSchsVO> tchSchsList;

	public TchSchsForm() {
		this.tchSchsVO = new TchSchsVO();
	}

	public TchSchsVO getTchSchsVO() {
		return tchSchsVO;
	}
	public void setTchSchsVO(TchSchsVO tchSchsVO) {
		this.tchSchsVO = tchSchsVO;
	}
	public List<TchSchsVO> getTchSchsList() {
		return tchSchsList;
	}
	public void setTchSchsList(List<TchSchsVO> tchSchsList) {
		this.tchSchsList = tchSchsList;
	}


}