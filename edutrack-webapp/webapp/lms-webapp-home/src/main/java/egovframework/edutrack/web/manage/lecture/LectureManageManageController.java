package egovframework.edutrack.web.manage.lecture;

import java.util.ArrayList;
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
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/manage")
public class LectureManageManageController extends GenericController{

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("sysCfgService")
	private SysCfgService			sysCfgService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	/**
	 * 수강 신청 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> eduTeamCdList = sysCodeMemService.getSysCodeList("EDU_TEAM_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("eduTeamCdList", eduTeamCdList);
		
		String curYear = DateTimeUtil.getYear();
		//-- 시작년도 가져오기
		SysCfgVO configVO = new SysCfgVO();
		configVO.setCfgCtgrCd("SYSTEM");
		configVO.setCfgCd("START_YEAR");
		configVO = sysCfgService.viewCfg(configVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(configVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("curYear", curYear);
		request.setAttribute("yearList", yearList);
		request.setAttribute("vo", vo);
		return "mng/lecture/manage/course_main";
	}
	
	/**
	 * 개설 과정 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setSelectMode("MANAGE");
		
		ProcessResultListVO<CreateCourseVO> resultList = createCourseService.listCreateCoursePageing(vo, vo.getCurPage(), vo.getListScale(), true);
		request.setAttribute("createCourseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/lecture/manage/course_list_div";
	}
	
	/**
	 * 개설 과정 관리 폼
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/manage")
	public String manage( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = createCourseService.viewCreateCourse(vo).getReturnVO();
		
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		
		request.setAttribute("courseVO", courseVO);
		request.setAttribute("vo", vo);
		return "mng/lecture/manage/course_manage";
	}
}
