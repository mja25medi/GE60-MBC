/**
 *
 */
package egovframework.edutrack.modules.teacher.aplc.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.aplc.service.TchAplcVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchAplcMapper")
public interface TchAplcMapper {

	/**
	 * 강사 신청 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<TchAplcVO> listTeacherAplc(TchAplcVO teacherAplcVO) throws Exception;
	
	
	/**
	 * 강사 신청 목록(엑셀다운로드)
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<TchAplcVO> listTeacherAplcResult(TchAplcVO teacherAplcVO) throws Exception;
	
	/**
	 * 강사신청
	 *  
	 * @reurn 
	 */
	public int insertTeacherAplc(TchAplcVO teacherAplcVO) throws Exception;
	
	
	/**
	 * 강사승인,취소
	 *  
	 * @reurn 
	 */
	public int updateTeacherAplc(TchAplcVO teacherAplcVO) throws Exception;
	
	/**
	 * 강사승인 시 강사입력
	 *  
	 * @reurn 
	 */
	public int insertTeacherInfo(TchAplcVO teacherAplcVO) throws Exception;
	
	/**
	 * 강사취소 시 강사삭제
	 *  
	 * @reurn 
	 */
	public int deleteTeacherInfo(TchAplcVO teacherAplcVO) throws Exception;
	
	/**
	 * 강사신청 유무확인
	 *  
	 * @reurn 
	 */
	public TchAplcVO selectProfReg(TchAplcVO teacherAplcVO) throws Exception;
	
}