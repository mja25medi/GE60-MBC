package egovframework.edutrack.modules.log.educourse.service;

import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogEduStatusVO extends DefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125028132746758469L;

	private LogEduTeamStatusVO logEduTeamStatusVO;
	private LogEduCourseStatusVO logEduCourseStatusVO;
	
	public LogEduStatusVO() {
		setLogEduTeamStatusVO(new LogEduTeamStatusVO());
		setLogEduCourseStatusVO(new LogEduCourseStatusVO());
	}

	public LogEduTeamStatusVO getLogEduTeamStatusVO() {
		return logEduTeamStatusVO;
	}

	public void setLogEduTeamStatusVO(LogEduTeamStatusVO logEduTeamStatusVO) {
		this.logEduTeamStatusVO = logEduTeamStatusVO;
	}

	public LogEduCourseStatusVO getLogEduCourseStatusVO() {
		return logEduCourseStatusVO;
	}

	public void setLogEduCourseStatusVO(LogEduCourseStatusVO logEduCourseStatusVO) {
		this.logEduCourseStatusVO = logEduCourseStatusVO;
	}
}
