package egovframework.edutrack.modules.student.worklog.service;

import java.util.List;

public class StdEduRsltWorkLogForm{

	private static final long serialVersionUID = 7708990807187934229L;
	private StdEduRsltWorkLogVO stdEduRsltWorkLogVO;
	private List<StdEduRsltWorkLogVO> stdEduRsltWorkLogList;

	public StdEduRsltWorkLogForm() {
		stdEduRsltWorkLogVO = new StdEduRsltWorkLogVO();
	}

	public StdEduRsltWorkLogVO getStdEduRsltWorkLogVO() {
		return stdEduRsltWorkLogVO;
	}

	public void setStdEduRsltWorkLogVO(StdEduRsltWorkLogVO stdEduRsltWorkLogVO) {
		this.stdEduRsltWorkLogVO = stdEduRsltWorkLogVO;
	}

	public List<StdEduRsltWorkLogVO> getStdEduRsltWorkLogList() {
		return stdEduRsltWorkLogList;
	}

	public void setStdEduRsltWorkLogList(
			List<StdEduRsltWorkLogVO> stdEduRsltWorkLogList) {
		this.stdEduRsltWorkLogList = stdEduRsltWorkLogList;
	}

}
