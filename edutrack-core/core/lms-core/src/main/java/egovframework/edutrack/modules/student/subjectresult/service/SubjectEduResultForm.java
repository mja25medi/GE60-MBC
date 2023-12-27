package egovframework.edutrack.modules.student.subjectresult.service;

import java.util.List;

public class SubjectEduResultForm {
	private SubjectEduResultVO subjectEduResultVO;
	private List<SubjectEduResultVO> subjectEduResultList;
	
	public SubjectEduResultForm() {
		subjectEduResultVO = new SubjectEduResultVO();
	}

	public SubjectEduResultVO getSubjectEduResultVO() {
		return subjectEduResultVO;
	}

	public void setSubjectEduResultVO(SubjectEduResultVO subjectEduResultVO) {
		this.subjectEduResultVO = subjectEduResultVO;
	}

	public List<SubjectEduResultVO> getSubjectEduResultList() {
		return subjectEduResultList;
	}

	public void setSubjectEduResultList(
			List<SubjectEduResultVO> subjectEduResultList) {
		this.subjectEduResultList = subjectEduResultList;
	}
}
