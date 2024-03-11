package egovframework.edutrack.modules.log.educourse.service.impl;

import java.util.List;

import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduOrgStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduTeamStatusVO;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 과정 상태 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logEduCourseStatusMapper")
public interface LogEduCourseStatusMapper {
	
    /**
	 * 팀별 /년도 월에 대한 과정 운영 현황 목록 
	 * @param  LogEduTeamStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogEduTeamStatusVO> listTeamStatus(LogEduTeamStatusVO vo) ;

    /**
	 * 팀별, 년,월별 개설과정 목록 
	 * @param  LogEduCourseStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogEduCourseStatusVO> listTeamStatus(LogEduCourseStatusVO vo) ;
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	
	public List<LogEduCourseStatusVO> listCourseStatus(LogEduCourseStatusVO vo) ;
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	
	public List<LogEduCourseStatusVO> deptStatusList(LogEduCourseStatusVO vo) ;
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	
	public int count(LogEduCourseStatusVO VO)  ;
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다. (페이징)
	 * @param iQuizStareDTO
	 * @return
	 */
	
	public List<LogEduCourseStatusVO> listCourseStatusPageing(LogEduCourseStatusVO VO)  ;
	
	
    /**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param  LogEduCourseStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogEduCourseStatusVO> listCourseResult(LogEduCourseStatusVO vo) ;
	
	/**
	 * 학습관리- 상세정보 엑셀 조회
	 * 수강생의 성적 및 나이순으로 오더를 해온다.
	 * @return ProcessReslutListVO
	 */
	
	public List<EduResultVO> listStdEduResultExcel(LogEduCourseStatusVO vo) ;

    /**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param  LogEduCourseStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogEduCourseStatusVO> listCourseResultDash(LogEduCourseStatusVO vo) ;
	
    /**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param  LogEduOrgStatusVO 
	 * @return List
	 * @
	 */
	public LogEduOrgStatusVO selectResult(LogEduOrgStatusVO vo) ;

	public String selectDeptCd(String userNo);

	public List<LogEduCourseStatusVO> listCourseStatusDeptMngPageing(LogEduCourseStatusVO vO);
}
