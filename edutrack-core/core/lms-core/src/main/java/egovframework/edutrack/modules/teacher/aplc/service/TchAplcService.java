package egovframework.edutrack.modules.teacher.aplc.service;

import java.io.OutputStream;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * @author DongKwang
 */
public interface TchAplcService {

	/**
	 * 강사 신청목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchAplcVO> listTeacherAplc(TchAplcVO teacherAplcVO, int curPage)throws Exception;
	
	
	/**
	 * 강사 신청목록 조회(엑셀다운로드)
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchAplcVO> listTeacherAplcResult(TchAplcVO teacherAplcVO)throws Exception;
	
	public abstract void listTeacherAplcExcelDownload(TchAplcVO teacherAplcVO,  OutputStream os) throws ServiceProcessException, Exception;
	
	/**
	 * 강사정보 신청
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchAplcVO> addTeacherAplc(TchAplcVO teacherAplcVO)throws Exception;
	
	/**
	 * 강사정보 승인
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchAplcVO> editTeacherAplc(TchAplcVO teacherAplcVO)throws Exception;

	/**
	 *  강사정보 승인취소
	 * @param ProcessResultVO
	 * @return
	 */
	public abstract ProcessResultVO<TchAplcVO> editTeacherCancel(TchAplcVO teacherAplcVO)throws Exception;
	
	/**
	 * 강사정보 유무확인
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchAplcVO> selectProfReg(TchAplcVO teacherAplcVO)throws Exception;

}