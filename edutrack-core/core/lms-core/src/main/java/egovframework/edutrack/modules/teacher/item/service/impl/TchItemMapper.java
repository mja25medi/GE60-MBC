/**
 *
 */
package egovframework.edutrack.modules.teacher.item.service.impl;


import java.util.List;

import egovframework.edutrack.modules.teacher.item.service.TchItemVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchItemMapper")
public interface TchItemMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;


	/**
	 * 강사 강의안 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<TchItemVO> listItem(TchItemVO teacherItemVO) ;
	
	/**
	 * 강사 강의안 상세 정보
	 * 
	 * @return ProcessReslutListVO
	 */
	public TchItemVO selectItem(TchItemVO teacherItemVO) ;
	
		
	/**
	 * 강사강의안 입력
	 *  
	 * @reurn 
	 */
	public int insertItem(TchItemVO teacherItemVO) ;
	
	/**
	 * 강사강의안 수정
	 *  
	 * @reurn 
	 */
	public int updateItem(TchItemVO teacherItemVO) ;
	
	
	/**
	 * 강사 강의안 삭제
	 *  
	 * @reurn 
	 */
	public int deleteItem(TchItemVO teacherItemVO) ;
	
}