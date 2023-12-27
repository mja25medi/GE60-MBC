package egovframework.edutrack.modules.lecture.project.info.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface ProjectService {

	/**
	 * 팀프로젝트 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 */
	public ProcessResultListVO<ProjectVO> list(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 목록 조회
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 */
	public ProcessResultListVO<ProjectVO> listPrjStudent(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 정보 등록
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 */
	public ProcessResultVO<ProjectVO> add(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 */
	public ProcessResultVO<ProjectVO> edit(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 */
	public ProcessResultVO<ProjectVO> view(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 정보 조회
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 */
	public ProcessResultVO<ProjectVO> viewPrjStudent(ProjectVO vo) throws Exception;

	/**
	 * 팀프로젝트 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 */
	public ProcessResultVO<?> remove(ProjectVO vo) throws Exception;

}