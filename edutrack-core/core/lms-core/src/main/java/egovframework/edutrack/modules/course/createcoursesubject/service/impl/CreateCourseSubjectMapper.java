package egovframework.edutrack.modules.course.createcoursesubject.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("createCourseSubjectMapper")
public interface CreateCourseSubjectMapper {


	/**
	 *  개설 과정 오프라인 과목 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CreateCourseSubjectVO> list(CreateCourseSubjectVO VO)  throws DataAccessException ;
	

}
