package egovframework.edutrack.modules.teacher.recd.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * 
 * @author DongKwang
 */
public interface TchRecdService {

	/**
	 * 강사 강의기록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public abstract ProcessResultListVO<TchRecdVO> listRecd(TchRecdVO teacherRecdVO, int curPage) throws Exception;	

	/**
	 * 강사강의기록 입력
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<TchRecdVO> addRecd(TchRecdVO teacherRecdVO) throws Exception;	

	/**
	 * 강사강의기록 상세 조회
	 * 
	 * @return ProcessResultVO
	 */
	@Transactional(readOnly = true)
	public abstract ProcessResultVO<TchRecdVO> viewRecd(TchRecdVO teacherRecdVO) throws Exception;	

	/**
	 * 강사강의기록 수정
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<TchRecdVO> editRecd(TchRecdVO teacherRecdVO) throws Exception;	

	/**
	 * 강사강의기록 삭제
	 * 
	 * @param userSn 삭제 대상 코드값
	 * @return
	 */
	public abstract ProcessResultVO<TchRecdVO> deleteRecd(TchRecdVO teacherRecdVO) throws Exception;	

}