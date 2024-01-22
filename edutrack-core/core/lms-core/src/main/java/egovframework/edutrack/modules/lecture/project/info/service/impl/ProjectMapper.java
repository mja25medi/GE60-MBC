package egovframework.edutrack.modules.lecture.project.info.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("projectMapper")
public interface ProjectMapper {
	
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 팀프로젝트 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<ProjectVO> list(ProjectVO vo) ;

	/**
	 * 팀프로젝트 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<ProjectVO> listPrjStudent(ProjectVO vo) ;

	/**
	 * 팀프로젝트 정보 등록
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int insert(ProjectVO vo) ;

	/**
	 * 팀프로젝트 정보 수정
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int update(ProjectVO vo) ;

	/**
	 * 팀프로젝트 정보 조회
	 * @param vo
	 * @return ProcessResultVO
	 */
	public ProjectVO select(ProjectVO vo) ;

	/**
	 * 팀프로젝트 정보 조회 (학습자용)
	 * @param vo
	 * @return ProcessResultVO
	 */
	public ProjectVO selectPrjStudent(ProjectVO vo) ;
	
	/**
	 * 팀프로젝트 정보 삭제
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int delete(ProjectVO vo) ;

}