package egovframework.edutrack.modules.teacher.field.service;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * @author DongKwang
 */
public interface TchFieldService {

	/**
	 * 강사 강의분야 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchFieldVO> listField(TchFieldVO TeacherFieldVO, int curPage) throws Exception;
	

	
	/**
	 * 강사강의분야 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public abstract ProcessResultVO<TchFieldVO> addField(TchFieldVO TeacherFieldVO) throws Exception; 
	
	
	/**
	 * 강사강의분야 상세 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchFieldVO> viewField(String userNo ,String lecFieldCd) throws Exception;
	
	

	/**
	 *  강사강의분야 삭제
	 * @param userSn 삭제 대상 코드값
	 * @return
	 */
	public abstract ProcessResultVO<TchFieldVO> deleteField(TchFieldVO TeacherFieldVO) throws Exception;
	
	
	

}