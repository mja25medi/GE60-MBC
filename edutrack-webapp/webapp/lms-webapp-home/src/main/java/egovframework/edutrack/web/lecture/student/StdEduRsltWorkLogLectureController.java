package egovframework.edutrack.web.lecture.student;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogService;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/std/eduRsltWorkLog")
public class StdEduRsltWorkLogLectureController extends GenericController {

	@Autowired @Qualifier("stdEduRsltWorkLogService")
	private StdEduRsltWorkLogService			stdEduRsltWorkLogService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService					sysCodeMemService;

	/**
	 * 로그 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main( StdEduRsltWorkLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<StdEduRsltWorkLogVO> resultVO = stdEduRsltWorkLogService.listPageing(vo, vo.getCurPage());

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("SCORE_PRSS_MTHD");
		request.setAttribute("codeList", codeList);

		request.setAttribute("stdEduRsltWorkLogList", resultVO.getReturnList());
		request.setAttribute("pageInfo", resultVO.getPageInfo());
		request.setAttribute("vo", vo);
		return "home/student/worklog/main_log";
	}

}
