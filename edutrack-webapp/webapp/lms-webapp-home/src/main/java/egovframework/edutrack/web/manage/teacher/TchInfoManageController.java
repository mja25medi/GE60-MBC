package egovframework.edutrack.web.manage.teacher;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.teacher.info.service.TchInfoService;
import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/tch/info")
public class TchInfoManageController extends GenericController{

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;

	@Autowired @Qualifier("tchInfoService")
	private TchInfoService 		tchInfoService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService 	logPrivateInfoService;

	@Autowired @Qualifier("usrUserAuthGrpService")
	private UsrUserAuthGrpService 	userAuthGrpService;

	@Autowired @Qualifier("orgUserInfoCfgService")
	private OrgUserInfoCfgService 	 orgUserInfoCfgService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;
	
	@Autowired @Qualifier("studentService")
	private StudentService					studentService;

	/**
	 * 강사 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( UsrUserInfoVO  vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("usrUserInfoVO", vo);
		request.setAttribute("paging", "Y");
		return "mng/teacher/info/teacher_main";
	}

	/**
	 * 강사 관리 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUserSts("U"); //-- 사용중인 사용자만 가져오기 (U, N)
		vo.setSearchAuthGrp("TEACHER");
		request.setAttribute("vo", vo);
		
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);

		//-- 개인 정보 출력 로그
		String inqCts = "";
		for(UsrUserInfoVO uiVO : resultList.getReturnList()) {
			inqCts += uiVO.getUserNo() + "\n";
		}
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(orgCd);
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("PROF_LIST");
		pilVO.setInqCts(inqCts);
		logPrivateInfoService.add(pilVO);

		//사용자 정보관리
		OrgUserInfoCfgVO orgUserInfoCfgVO = new OrgUserInfoCfgVO();
		orgUserInfoCfgVO.setOrgCd(orgCd);
		ProcessResultListVO<OrgUserInfoCfgVO> resultCfgList = orgUserInfoCfgService.list(orgUserInfoCfgVO);
		request.setAttribute("userInfoCfgList",resultCfgList.getReturnList());
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());		
		
		
		return "mng/teacher/info/teacher_list_div";
	}

	/**
	 * 강사 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/viewTchPop")
	public String viewTchPop( UsrUserInfoVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		TchInfoVO tchInfoVO = vo.getTchInfoVO();
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		tchInfoVO.setUserNo(vo.getUserNo());
		try {
			tchInfoVO = tchInfoService.view(tchInfoVO).getReturnVO();

			//-- 개인 정보 조회 로그
			LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
			pilVO.setOrgCd(orgCd);
			pilVO.setMenuCd(UserBroker.getMenuCode(request));
			pilVO.setUserNo(UserBroker.getUserNo(request));
			pilVO.setUserNm(UserBroker.getUserName(request));
			pilVO.setDivCd("PROF_VIEW");
			pilVO.setInqCts(vo.getUserNo());
			logPrivateInfoService.add(pilVO);
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
		}

		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		List<UsrUserAuthGrpVO> authList = userAuthGrpService.list(uagVO).getReturnList();
		String teacherYn = "N";
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType())) {
				if("TEACHER".equals(iagVO.getAuthGrpCd())) teacherYn = "Y";
			}
		}
		
		request.setAttribute("teacherYn", teacherYn);

		request.setAttribute("tchInfoVO", tchInfoVO);
		request.setAttribute("vo", vo);
		
		return "mng/teacher/info/teacher_view_pop";
	}

	/**
	 * 강사 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editPop")
	public String editPop( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing( vo, request);
		
		TchInfoVO tchInfoVO = vo.getTchInfoVO();
        List<SysCodeVO> tchCtgrCdList = sysCodeMemService.getSysCodeList("TCH_CTGR_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> tchDivCdList = sysCodeMemService.getSysCodeList("TCH_DIV_CD", UserBroker.getLocaleKey(request));

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		tchInfoVO.setUserNo(vo.getUserNo());
		try {
			tchInfoVO = tchInfoService.view(tchInfoVO).getReturnVO();

			//-- 개인 정보 조회 로그
			LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
			pilVO.setOrgCd(orgCd);
			pilVO.setMenuCd(UserBroker.getMenuCode(request));
			pilVO.setUserNo(UserBroker.getUserNo(request));
			pilVO.setUserNm(UserBroker.getUserName(request));
			pilVO.setDivCd("PROF_VIEW");
			pilVO.setInqCts(vo.getUserNo());
			logPrivateInfoService.add(pilVO);
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
		}

		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		List<UsrUserAuthGrpVO> authList = userAuthGrpService.list(uagVO).getReturnList();
		String teacherYn = "N";
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType())) {
				if("TEACHER".equals(iagVO.getAuthGrpCd())) teacherYn = "Y";
			}
		}
		request.setAttribute("teacherYn", teacherYn);

		request.setAttribute("tchInfoVO", tchInfoVO);
		request.setAttribute("vo", vo);
		request.setAttribute("tchCtgrCdList", tchCtgrCdList);
		request.setAttribute("tchDivCdList", tchDivCdList);
		request.setAttribute("paging", "Y");
	
		return "mng/teacher/info/teacher_write_pop";
	}

	/**
	 * 강사 등록(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(UsrUserInfoVO vo , Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		TchInfoVO tchInfoVO = vo.getTchInfoVO(); 
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<TchInfoVO> resultVO = null;
		try {
			resultVO = tchInfoService.add(tchInfoVO);
			System.out.println(resultVO);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.add.success"));
			} else if(resultVO.getResult() == -1) {
				resultVO = tchInfoService.edit(tchInfoVO);
				if(resultVO.getResult() > 0) {
					resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.edit.success"));
				} else {
					resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.edit.failed"));
				}				
			}else {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.add.failed"));
			}
		} catch (Exception e) {

		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 강사 POOL 관리 - IDE 부여
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uptIdePop")
	public String uptIde( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//강사리스트
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUserSts("U"); //-- 사용중인 사용자만 가져오기 (U, N)
		vo.setSearchAuthGrp("TEACHER");
		request.setAttribute("vo", vo);
		
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.list(vo);
		request.setAttribute("userInfoList", resultList.getReturnList());
		
		//IDE 리스트
		StudentVO VO = new StudentVO();
		ProcessResultListVO<StudentVO> ideList = studentService.listIdeManage(VO);
		model.addAttribute("ideList", ideList.getReturnList());
		
		return "mng/teacher/info/teacher_ide_pop";
	}
	
	/**
	 * 강사의 IDE 정보를 수정한다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editTeacherUrl")
	public String editTeacherUrl( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<UsrUserInfoVO> resultVO = usrUserInfoService.editTeacherUrl(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage("강사 정보를 수정하였습니다.");
		} else {
			resultVO.setMessage("강사 정보를 수정하지 못했습니다.");
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 강사>IDE URL>엑셀업로드 폼
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addTeacherIdeExcelPop")
	public String addTeacherIdeExcelPop( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("fileupload", "Y");
		
		return "mng/teacher/info/teacher_ide_write_excel_pop";
	}
	
	/**
	 * [HRD] 강사>IDE URL>엑셀업로드 - 수강생 등록
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value="/uploadExcelTeacherIde")
	public String uploadExcelTeacherIde ( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		
		String type =  StringUtil.nvl(request.getParameter("type"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		
		ProcessResultVO<UsrUserInfoVO> resultVO = new ProcessResultVO<>();
		try {
			usrUserInfoService.addTeacherIdeUrlExcel(vo, fileName, filePath);
			resultVO.setResultSuccess();
			resultVO.setMessage("IDE URL 등록에 성공하였습니다.");
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			log.error(e.getMessage());
			resultVO.setMessage("IDE URL 등록에 실패하였습니다. 반복될 경우, 담당자에게 문의바랍니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
}
