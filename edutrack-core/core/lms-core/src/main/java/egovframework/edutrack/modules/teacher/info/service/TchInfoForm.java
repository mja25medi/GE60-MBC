/**
 *
 */
package egovframework.edutrack.modules.teacher.info.service;

import java.util.List;

/**
 * Xdoclet을 위한 태그
 * @struts:form name="teacherInfoForm"
 */
@SuppressWarnings("serial")
public class TchInfoForm {

	private TchInfoVO tchInfoVO;
	private List<TchInfoVO> tchiNFOList;

	public TchInfoForm() {
		this.tchInfoVO = new TchInfoVO();
	}

	public TchInfoVO getTchInfoVO() {
		return tchInfoVO;
	}

	public void setTchInfoVO(TchInfoVO tchInfoVO) {
		this.tchInfoVO = tchInfoVO;
	}

	public List<TchInfoVO> getTchiNFOList() {
		return tchiNFOList;
	}

	public void setTchiNFOList(List<TchInfoVO> tchiNFOList) {
		this.tchiNFOList = tchiNFOList;
	}
}