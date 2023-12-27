package egovframework.edutrack.modules.student.result.service;

import java.util.List;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="EduResultForm"
 */
@SuppressWarnings("serial")
public class EduResultForm {
	/**
	 * 결과 VO
	 */
	private EduResultVO eduResultVO;
	
	/**
	 * 결과 리스트
	 */
	private List<EduResultVO> eduResultList;


	/**
	 * 생성자
	 */
	public EduResultForm() {
		eduResultVO = new EduResultVO();
	}

	
	/**
	 * @return the resultVO
	 */
	public EduResultVO getEduResultVO() {
		return this.eduResultVO;
	}

	
	/**
	 * @param resultVO the resultVO to set
	 */
	public void setEduResultVO(EduResultVO eduResultVO) {
		this.eduResultVO = eduResultVO;
	}


	public List<EduResultVO> getEduResultList() {
		return eduResultList;
	}


	public void setEduResultList(List<EduResultVO> eduResultList) {
		this.eduResultList = eduResultList;
	}

}