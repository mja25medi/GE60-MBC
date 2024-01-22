/**
 *
 */
package egovframework.edutrack.modules.teacher.field.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.field.service.TchFieldVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchFieldMapper")
public interface TchFieldMapper {

	/**
	 * 강사강의분야 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<TchFieldVO> listField(TchFieldVO TeacherFieldVO) ;
	
	/**
	 * 강사강의분야 상세 정보
	 * 
	 * @return ProcessReslutListVO
	 */
	public TchFieldVO selectField(String userNo ,String lecFieldCd) ;
	
	
	/**
	 * 강사학력 입력
	 *  
	 * @reurn 
	 */
	public int insertField(TchFieldVO TeacherFieldVO) ;
	
	
	/**
	 * 강사 삭제
	 *  
	 * @reurn 
	 */
	public int deleteField(String userNo,String lecFieldCd) ;

}