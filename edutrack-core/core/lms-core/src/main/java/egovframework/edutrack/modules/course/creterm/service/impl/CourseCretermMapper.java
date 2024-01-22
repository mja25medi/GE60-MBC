package egovframework.edutrack.modules.course.creterm.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("courseCretermMapper")
public interface CourseCretermMapper {

	
	public List<CourseCretermVO> list(CourseCretermVO VO)   ;

	
	public List<CourseCretermVO> listPageing(CourseCretermVO VO)
			 ;
	
	
	public int count(CourseCretermVO VO)
			 ;

	public String selectTermCd()  ;
	
	public CourseCretermVO select(CourseCretermVO VO)  ;

	public int insert(CourseCretermVO VO)  ;

	public int update(CourseCretermVO VO)  ;

	public int delete(CourseCretermVO VO)  ;

}
