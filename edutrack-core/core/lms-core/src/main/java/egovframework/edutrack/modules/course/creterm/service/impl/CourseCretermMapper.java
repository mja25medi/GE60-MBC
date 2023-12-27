package egovframework.edutrack.modules.course.creterm.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("courseCretermMapper")
public interface CourseCretermMapper {

	@SuppressWarnings("unchecked")
	public List<CourseCretermVO> list(CourseCretermVO VO)  throws DataAccessException ;

	@SuppressWarnings("unchecked")
	public List<CourseCretermVO> listPageing(CourseCretermVO VO)
			throws DataAccessException ;
	
	@SuppressWarnings("unchecked")
	public int count(CourseCretermVO VO)
			throws DataAccessException ;

	public String selectTermCd() throws DataAccessException ;
	
	public CourseCretermVO select(CourseCretermVO VO) throws DataAccessException ;

	public int insert(CourseCretermVO VO) throws DataAccessException ;

	public int update(CourseCretermVO VO) throws DataAccessException ;

	public int delete(CourseCretermVO VO) throws DataAccessException ;

}
