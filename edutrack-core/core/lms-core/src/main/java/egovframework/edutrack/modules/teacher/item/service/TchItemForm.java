/**
 *
 */
package egovframework.edutrack.modules.teacher.item.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Xdoclet을 위한 태그
 * 
 * @struts:form name="teacherItemForm"
 */
@SuppressWarnings("serial")
public class TchItemForm{

	/**
	 * 강사 강의안 VO
	 */
	private TchItemVO			teacherItemVO;

	/**
	 * 강사 강의안 리스트
	 */
	private List<TchItemVO>	teacherItemList;

	/**
	 * 생성자
	 */
	public TchItemForm() {
		this.teacherItemVO = new TchItemVO();
		this.teacherItemList = new ArrayList<TchItemVO>();

	}

	/**
	 * @return the teacherItemVO
	 */
	public TchItemVO getTeacherItemVO() {
		return teacherItemVO;
	}

	/**
	 * @param teacherItemVO the teacherItemVO to set
	 */
	public void setTeacherItemVO(TchItemVO teacherItemVO) {
		this.teacherItemVO = teacherItemVO;
	}

	/**
	 * @return the teacherItemList
	 */
	public List<TchItemVO> getTeacherItemList() {
		return teacherItemList;
	}

	/**
	 * @param teacherItemList the teacherItemList to set
	 */
	public void setTeacherItemList(List<TchItemVO> teacherItemList) {
		this.teacherItemList = teacherItemList;
	}

}