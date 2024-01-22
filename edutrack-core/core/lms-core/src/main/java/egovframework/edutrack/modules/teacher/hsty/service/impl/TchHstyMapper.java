/**
 *
 */
package egovframework.edutrack.modules.teacher.hsty.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.hsty.service.TchHstyVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchHstyMapper")
public interface TchHstyMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 강사 활동이력 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<TchHstyVO> listHsty(TchHstyVO teacherHstyVO) ;
	
	/**
	 * 강사 활동이력 상세 정보
	 * 
	 * @return ProcessReslutListVO
	 */
	public TchHstyVO selectHsty(String userNo, int hstySn) ;
	

	/**
	 * 강사활동이력 입력
	 *  
	 * @reurn 
	 */
	public int insertHsty(TchHstyVO teacherHstyVO) ;
	
	/**
	 * 강사활동이력 수정
	 *  
	 * @reurn 
	 */
	public int updateHsty(TchHstyVO teacherHstyVO) ;
	
	
	/**
	 * 강사 활동이력 삭제
	 *  
	 * @reurn 
	 */
	public int deleteHsty(String userNo, int hstySn) ;

}