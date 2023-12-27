package egovframework.edutrack.modules.lecture.main.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MainLectureService {

	/**
	 * 강의실의 정보를 가져온다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<MainLectureVO> view(MainLectureVO vo) throws Exception;
	
	/**
	 * 강의실의 정보를 가져온다. (과정)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<MainLectureVO> viewCourseSchedule(MainLectureVO vo) throws Exception;
	
	/**
	 * 강의실의 정보를 가져온다. (회차)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<MainLectureVO> viewCreateCourseSchedule(MainLectureVO vo) throws Exception;

	/**
	 * 미채점 현황 리스트 과정,시험,과제 성적 리스트별 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public abstract	ProcessResultListVO<EgovMap> listUnScoreStatus(MainLectureVO vo) throws Exception;

	/**
	 * 강의실의 정보를 가져온다. 
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<MainLectureVO> createCourseSchedule(MainLectureVO vo) throws Exception;
}