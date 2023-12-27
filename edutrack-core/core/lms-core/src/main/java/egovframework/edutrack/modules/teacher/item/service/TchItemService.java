package egovframework.edutrack.modules.teacher.item.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * 
 * @author DongKwang
 */
public interface TchItemService {

	/**
	 * 강사 강의안 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchItemVO> listItem(TchItemVO teacherItemVO, int curPage) throws Exception;

	/**
	 * 강사 강의안 입력
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<TchItemVO> addItem(TchItemVO teacherItemVO) throws Exception;

	/**
	 * 강사 강의안 상세 조회
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<TchItemVO> viewItem(TchItemVO teacherItemVO) throws Exception;

	/**
	 * 강사 강의안 수정
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<TchItemVO> editItem(TchItemVO teacherItemVO) throws Exception;

	/**
	 * 강사 강의안 삭제
	 * 
	 * @param userSn 삭제 대상 코드값
	 * @return
	 */
	public abstract ProcessResultVO<TchItemVO> deleteItem(TchItemVO teacherItemVO) throws Exception;

}