package egovframework.edutrack.modules.etc.hrdapi.service;

public interface HrdStdSendable {
	public String callHrdUserAgentPk();
	public String callHrdCourseAgentPk();
	public String callHrdClassAgentPk();
	public Integer callHrdPassFlag();
	public Integer callHrdAttendValidFlag();
	public Integer callHrdEmpInsFlag();
}
