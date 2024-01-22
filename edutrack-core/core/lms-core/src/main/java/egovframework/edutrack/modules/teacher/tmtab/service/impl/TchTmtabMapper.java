/**
 *
 */
package egovframework.edutrack.modules.teacher.tmtab.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.tmtab.service.TchTmtabVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * @author pinkpanda
 *
 */
@Mapper("tchTmtabMapper")
public interface TchTmtabMapper {

	/**
	 * 강사 활동이력 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<TchTmtabVO> listTmtab(TchTmtabVO teacherTmtabVO) ;

}