package egovframework.edutrack.web.lecture.course;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclService;
import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclVO;
import egovframework.edutrack.modules.course.crsbbs.cmnt.service.CrsBbsCmntService;
import egovframework.edutrack.modules.course.crsbbs.cmnt.service.CrsBbsCmntVO;
import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoService;
import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/crsBbs")
public class CrsBbsLectureController extends GenericController {
	
	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService sysMenuMemService;
	
	@Autowired @Qualifier("crsBbsInfoService")
	private CrsBbsInfoService 		crsBbsInfoService;

	@Autowired @Qualifier("crsBbsAtclService")
	private CrsBbsAtclService 		crsBbsAtclService;
	
	@Autowired @Qualifier("crsBbsCmntService")
	private CrsBbsCmntService 		crsBbsCmntService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 	createCourseService;
	
	/**
	 * 과정 게시판 메인 페이지
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		
		vo.setCrsCd(createCourseVO.getCrsCd());
		//ProcessResultListVO<CrsBbsAtclVO> result = crsBbsAtclService.listPageing(vo);
		ProcessResultListVO<CrsBbsAtclVO> result = new ProcessResultListVO<>();
		
		request.setAttribute("crsBbsAtclList", result.getReturnList());
	   	request.setAttribute("pageInfo", result.getPageInfo());
	   	request.setAttribute("crsBbsInfoVO", crsBbsInfoVO);
	   	request.setAttribute("vo", vo);

		return "home/lecture/crsbbs/list_atcl";
	}
	
	/**
	 * 게시판 게시글 조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/read")
	public String read(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		String searchKey = vo.getSearchKey();
		String searchValue = vo.getSearchValue();

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		
		//-- 게시물의 정보를 가져온다.
		vo.setCrsCd(createCourseVO.getCrsCd());
		ProcessResultVO<CrsBbsAtclVO> resultVO = crsBbsAtclService.view(vo,true);
		vo = resultVO.getReturnVO();

		vo.setSearchKey(searchKey);
		vo.setSearchValue(searchValue);
		
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("crsBbsInfoVO", crsBbsInfoVO);
		return "home/lecture/crsbbs/read_atcl";
	}
	
	
	/**
	 * 과정 게시판 글쓰기 화면
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addForm")
	public String addForm(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		request.setAttribute("gubun", "A");

		
		// 댓글을 위한 상위글 파라매터가 넘어오면 기본 제목을 설정
		if(ValidationUtils.isNotNull(vo.getParAtclSn())) {
			CrsBbsAtclVO parent = new CrsBbsAtclVO();
			parent.setCrsCd(createCourseVO.getCrsCd());
			parent.setBbsCd(vo.getBbsCd());
			parent.setAtclSn(vo.getParAtclSn());
			parent = crsBbsAtclService.view(parent).getReturnVO();
			vo.setTitle("[답변] " + parent.getTitle());
			vo.setBbsCd(parent.getBbsCd());
			//VO.setAtclSn(null);
			request.setAttribute("gubun", "AR"); //댓글일때 구분코드.

		}

	   	request.setAttribute("vo", vo);
	   	request.setAttribute("crsBbsInfoVO", crsBbsInfoVO);

		return "home/lecture/crsbbs/write_atcl";
	}
	
	/**
	 * 게시판 글 등록
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setRegNm(UserBroker.getUserName(request));
		
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		
		// 스크립트, 스타일 태그 제거
		vo.setCts( HtmlCleaner.cleanScript(vo.getCts()) );
		// 과정 코드 셋팅
		vo.setCrsCd(createCourseVO.getCrsCd());		
		try {
			crsBbsAtclService.add(vo);
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
			e.printStackTrace();
			setAlertMessage(request, "글을 저장하지 못했습니다.");
			return "home/lecture/crsbbs/write_atcl";	// 다시 편집 화면으로
		}

		setAlertMessage(request, "글을 저장했습니다.");

		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/crsBbs/read")
				.addParameter("crsBbsAtclVO.atclSn", vo.getAtclSn())
				.addParameter("crsBbsAtclVO.bbsCd", vo.getBbsCd())
				.addParameter("crsBbsAtclVO.searchKey", vo.getSearchKey())
				.addParameter("crsBbsAtclVO.searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();
	}
	
	/**
	 * 게시판 글 수정 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editForm")
	public String editForm(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String searchKey = vo.getSearchKey();
		String searchValue = vo.getSearchValue();
		
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		
		vo.setCrsCd(createCourseVO.getCrsCd());
		ProcessResultVO<CrsBbsAtclVO> resultVO = crsBbsAtclService.view(vo);
		vo = resultVO.getReturnVO();
		vo.setSearchKey(searchKey);
		vo.setSearchValue(searchValue);

		request.setAttribute("crsBbsAtclVO", vo);
		
		request.setAttribute("crsBbsAtclVO", vo);
	   	request.setAttribute("gubun", "E");

		return "home/lecture/crsbbs/write_atcl";
	}

	/**
	 * 게시판 글 수정
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//-- 게시판의 정보를 가져온다.
		CrsBbsInfoVO crsBbsInfoVO = new CrsBbsInfoVO();
		crsBbsInfoVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsInfoVO.setBbsCd(vo.getBbsCd());
		crsBbsInfoVO = crsBbsInfoService.select(crsBbsInfoVO).getReturnVO();
		
		try {
			vo.setCrsCd(createCourseVO.getCrsCd());
			crsBbsAtclService.edit(vo);
		} catch (Exception e) {
		   	request.setAttribute("gubun", "E");
			e.printStackTrace();
			setAlertMessage(request, "글을 저장하지 못했습니다.");
			return "home/lecture/crsbbs/write_atcl";	// 다시 편집 화면으로
		}

		setAlertMessage(request, "글을 저장했습니다.");
		
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/crsBbs/read")
				.addParameter("crsBbsAtclVO.atclSn", vo.getAtclSn())
				.addParameter("crsBbsAtclVO.bbsCd", vo.getBbsCd())
				.addParameter("crsBbsAtclVO.searchKey", vo.getSearchKey())
				.addParameter("crsBbsAtclVO.searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();
	}

	/**
	 * 게시판 글 삭제
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/remove")
	public String remove(CrsBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//게시글 댓글 삭제
		CrsBbsCmntVO crsBbsCmntVO = new CrsBbsCmntVO();
		crsBbsCmntVO.setCrsCd(createCourseVO.getCrsCd());
		crsBbsCmntVO.setBbsCd(vo.getBbsCd());
		crsBbsCmntVO.setAtclSn(vo.getAtclSn());
		
		// 삭제
		@SuppressWarnings("unused")
		ProcessResultVO<?> resultVO; 

		try {
			vo.setCrsCd(createCourseVO.getCrsCd());
			crsBbsCmntService.removeCmntNtcAll(crsBbsCmntVO);
			resultVO = crsBbsAtclService.remove(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "글을 삭제하지 못했습니다.");
			return "home/lecture/crsbbs/read_atcl";	// 다시 읽기 화면으로
		}

		setAlertMessage(request, "글을 삭제 했습니다.");
		// 목록 화면으로 
		return "redirect:"+ new URLBuilder("/lec/crsBbs/main")
				.addParameter("bbsCd", vo.getBbsCd())
				.addParameter("searchKey", vo.getSearchKey())
				.addParameter("searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();		
	}
	
	
	
	/**
	 * 게시판 댓글 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCmnt")
	public String listCmnt(CrsBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		vo.setCrsCd(createCourseVO.getCrsCd());
		ProcessResultListVO<CrsBbsCmntVO> resultList = crsBbsCmntService.listPageing(vo);
		return JsonUtil.responseJson(response, resultList);
	}

	/**
	 * 개시판 댓글 등록 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCmnt")
	public String addCmnt(CrsBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		// 스크립트, 스타일 태그 제거
		vo.setCmntCts(HtmlCleaner.cleanScript(vo.getCmntCts()) );
		
		this.validateCmnt(vo);
		
		vo.setCrsCd(createCourseVO.getCrsCd());
		ProcessResultVO<CrsBbsCmntVO> result = crsBbsCmntService.add(vo);
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 개시판 댓글 수정 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCmnt")
	public String editCmnt(CrsBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		// 스크립트, 스타일 태그 제거
		vo.setCmntCts( HtmlCleaner.cleanScript(vo.getCmntCts()) );
		this.validateCmnt(vo);		

		vo.setCrsCd(createCourseVO.getCrsCd());
		ProcessResultVO<CrsBbsCmntVO> result = crsBbsCmntService.edit(vo);
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 개시판 댓글 삭제 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeCmnt")
	public String removeCmnt(CrsBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		String classUserType = UserBroker.getClassUserType(request);
		

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		ProcessResultVO<?> result = null ;
		//교수자가 들어 왔을때 모두 삭제 가능
		if(classUserType == "TCH"){
			vo.setCrsCd(createCourseVO.getCrsCd());
			result = crsBbsCmntService.remove(vo);
		}else if(classUserType == "STU"){
			//-- 등록자와 동일한 경우만.. 삭제 가능하도록함.
			CrsBbsCmntVO sVO = crsBbsCmntService.view(vo).getReturnVO();
			if(!sVO.getRegNo().equals(UserBroker.getUserNo(request))) {
				throw new AjaxIllegalFormatException("댓글의 삭제는 등록자만 삭제 할 수 있습니다.");
			}
			vo.setCrsCd(createCourseVO.getCrsCd());
			result = crsBbsCmntService.remove(vo);
		}
		return JsonUtil.responseJson( response, result);
	}

	/**
	 * 댓글의 유효성 검사.
	 * @param comment
	 * @return
	 */
	private void validateCmnt(CrsBbsCmntVO VO) {
		if(StringUtil.isNull(VO.getCmntCts())) {
			throw new AjaxIllegalFormatException("본문의 내용이 너무 짧습니다.");
		}
	}
}
