/**
 *
 */
package egovframework.edutrack.modules.teacher.thesis.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.thesis.service.TchThesisVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchThesisMapper")
public interface TchThesisMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 강사 발표논문 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<TchThesisVO> listThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	/**
	 * 강사  발표논문 상세 정보
	 * 
	 * @return ProcessReslutListVO
	 */
	public TchThesisVO selectThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	
	/**
	 * 강사 발표논문 입력
	 *  
	 * @reurn 
	 */
	public int insertThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	/**
	 * 강사 발표논문 수정
	 *  
	 * @reurn 
	 */
	public int updateThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	
	/**
	 * 강사 발표논문 삭제
	 *  
	 * @reurn 
	 */
	public int deleteThesis(String userNo, Integer thesisSn) throws Exception;

}