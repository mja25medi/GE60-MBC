package egovframework.edutrack.web.home.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.security.KISASeed;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsCmntVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시판 액션 컨트롤
 *
 * @author MediopiaTech
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/brd/bbs")
public class BrdBbsHomeController extends GenericController {

	@Autowired @Qualifier("brdBbsAtclService")
	private BrdBbsAtclService brdBbsAtclService;

	@Autowired @Qualifier("brdBbsInfoService")
	private BrdBbsInfoService brdBbsInfoService;

	@Autowired @Qualifier("brdBbsHeadService")
	private BrdBbsHeadService brdBbsHeadService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;
	
	@Autowired @Qualifier("courseService")
	private CourseService 			courseService;
	
//	@Autowired @Qualifier("bbsCmntService")
//	private IBbsCmntService bbsCmntService;
//
//	@Autowired @Qualifier("codeMemService")
//	private ICodeMemService codeMemService;
	
	/**
     * @Method Name : listAtcl
     * @Method 설명 : 게시판 게시글 목록 조회
	 * @param BbsVO 
	 * @param commandMap
	 * @param model
	 * @return  bbsListType
	 * @throws Exception
	 */
	@RequestMapping(value="/listAtclMain")
	public String listAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String returnUrl = "";
		
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbsInfoVo = new BrdBbsInfoVO();
		bbsInfoVo.setOrgCd(orgCd);
		bbsInfoVo.setBbsCd(vo.getBbsCd());
		bbsInfoVo = brdBbsInfoService.view(bbsInfoVo);
		request.setAttribute("bbsInfoVo", bbsInfoVo);

		//---- 게시판 말머리 목록
		BrdBbsHeadVO bbsHeadVo = new BrdBbsHeadVO();
		bbsInfoVo.setOrgCd(orgCd);
		bbsHeadVo.setBbsCd(vo.getBbsCd());
		
		List<BrdBbsHeadVO> bbsHeadList = brdBbsHeadService.list(bbsHeadVo).getReturnList();
		request.setAttribute("bbsHeadList", bbsHeadList);

		String crsCreCd = request.getParameter("crsCreCd");
		vo.setCrsCreCd(crsCreCd);
		
		//---- 게시판 목록 유형 설정.
		String bbsListType = "list_atcl_board"; //-- 기본형은 일반 게시판형.
		if("BOARD".equals(bbsInfoVo.getBbsTypeCd())) bbsListType = "list_atcl_board";
		else if("GALLERY".equals(bbsInfoVo.getBbsTypeCd())) bbsListType = "list_atcl_gallery";
		else if("SERVICE".equals(bbsInfoVo.getBbsTypeCd())) {
			if(bbsInfoVo.getBbsCd().equals("BOCTI")){
				bbsListType = "write_atcl_bocti";
			}else {
				bbsListType = "write_atcl_service";
			}
		}
		else if("CATEGORY".equals(bbsInfoVo.getBbsTypeCd())) {
			bbsListType = "list_atcl_blog";
			String locale = UserBroker.getLocaleKey(request);
			List<SysCodeVO> categoryList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
		        for(SysCodeVO scvo : categoryList) {
		        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
		        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
		        	}
		     }
		        
		     request.setAttribute("categoryList", categoryList);
		}

		boolean filein = false;
		if("BLOG".equals(bbsInfoVo.getBbsTypeCd())) filein = true; //-- 본문 이미지 첨부
		if("Y".equals(bbsInfoVo.getAtchUseYn())) filein = true; //-- 목록 파일 다운로드 링크
		if("GALLERY".equals(bbsInfoVo.getBbsTypeCd())) filein = true;

		vo.setListViewMode(bbsInfoVo.getDetlViewCd());
		vo.setOrgCd(orgCd);
		ProcessResultListVO<BrdBbsAtclVO> resultList = brdBbsAtclService.listAtclPageing(vo, vo.getPageIndex(), bbsInfoVo.getListViewCnt(), filein);
		
    	request.setAttribute("bbsAtclList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("encryptjs", "Y");
		request.setAttribute("paging", "Y");
		
		returnUrl = "home/board/bbs/" + bbsListType;

		
		return returnUrl;
	}
	
	/**
     * @Method Name : viewAtcl
     * @Method 설명 : 게시판 게시물 보기 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  ""
	 * @throws Exception
	 */
	@RequestMapping(value="/viewAtclMain")
	public String viewAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	int pageIndex = vo.getPageIndex();
    	String searchValue = vo.getSearchValue();
    	String searchKey = vo.getSearchKey();
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);
		
		vo = brdBbsAtclService.viewAtclWithPreNext(vo, true);

		vo.setPageIndex(pageIndex);
		vo.setSearchKey(searchKey);
		vo.setSearchValue(searchValue);
		
		request.setAttribute("vo", vo);
		
		return "home/board/bbs/view_atcl";
	}
	
	@RequestMapping(value="/viewPasswordCheckMain")
	public String viewPasswordCheckMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	int pageIndex = vo.getPageIndex();
    	String searchValue = vo.getSearchValue();
    	String searchKey = vo.getSearchKey();
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);
		
		vo = brdBbsAtclService.viewAtclWithPreNext(vo, true);

		vo.setPageIndex(pageIndex);
		vo.setSearchKey(searchKey);
		vo.setSearchValue(searchValue);
		
		request.setAttribute("vo", vo);
		
		return "home/board/bbs/view_check_password";
	}
	
	/**
     * @Method Name : addFormAtcl
     * @Method 설명 : 게시판 게시물 등록 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "home/board/bbs/write_atcl"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormAtclMain")
	public String addFormAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);

		//---- 게시판 말머리 목록
		BrdBbsHeadVO bbhvo = new BrdBbsHeadVO();
		bbhvo.setOrgCd(orgCd);
		bbhvo.setBbsCd(vo.getBbsCd());
		List<BrdBbsHeadVO> bbsHeadList = brdBbsHeadService.list(bbhvo).getReturnList();
		request.setAttribute("bbsHeadList", bbsHeadList);
		
		request.setAttribute("gubun", "A");

		// 댓글을 위한 상위글 파라매터가 넘어오면 기본 제목을 설정
		if(ValidationUtils.isNotNull(vo.getParAtclSn())) {
			BrdBbsAtclVO parent = new BrdBbsAtclVO();
			parent.setOrgCd(orgCd);
			parent.setBbsCd(vo.getBbsCd());
			parent.setAtclSn(vo.getParAtclSn());
			parent = brdBbsAtclService.viewAtcl(parent);
			vo.setAtclTitle("["+getMessage(request, "common.title.reply")+"] " + parent.getAtclTitle());
			vo.setAtclLvl(parent.getAtclLvl()+1);
			vo.setBbsCd(parent.getBbsCd());
			//dto.setAtclSn(null);
			request.setAttribute("gubun", "AR");
		}
		String userNo = UserBroker.getUserNo(request);
		
		if("REVIEW".equals(vo.getBbsCd())){
			List<CourseVO> courseList = courseService.listUserCourse(userNo).getReturnList();
			if(courseList.size() == 0) {
				setAlertMessage(request, "수강후기를 작성 가능한 과정이 없습니다.");
				return "redirect:/home/main/goMenuPage?mcd=MC00000051";
			}
			request.setAttribute("courseList", courseList);
		}
		
		request.setAttribute("fileupload", "Y");
		request.setAttribute("summernote", "Y");
		request.setAttribute("vo", vo);
		return "home/board/bbs/write_atcl";
	}
	
	/**
     * @Method Name : addAtcl
     * @Method 설명 : 게시판 게시물 등록 한다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addAtcl")
	public String addAtcl(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);
		
		// 스크립트, 스타일 태그 제거
		vo.setAtclCts( HtmlCleaner.cleanScript(vo.getAtclCts()) );

		if(StringUtil.isNotNull(vo.getEncryptData())) {
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setPassword(KISASeed.seed256HashEncryption(decrypt[0]));
		}
		
		try {
			brdBbsAtclService.addAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);			
			return "mng/board/bbs/write_atcl";
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.success"));

		if("BLOG".equals(bbivo.getBbsTypeCd())) {
			// 목록 화면으로
			return "redirect:"+
				new URLBuilder("/home/brd/bbs/listAtclMain")
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.addParameter("searchKey", vo.getSearchKey())
					.addParameter("searchValue", vo.getSearchValue())
					.toString();
		} else {
			// 읽기 화면으로
			return "redirect:"+
				new URLBuilder("/home/brd/bbs/viewAtclMain")
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.addParameter("searchKey", vo.getSearchKey())
					.addParameter("searchValue", vo.getSearchValue())
					.toString();
		}
	}
	
	/**
     * @Method Name : editFormAtcl
     * @Method 설명 : 게시판 게시물 수정 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "home/board/bbs/write_atcl"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormAtclMain")
	public String editFormAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);

		//---- 게시판 말머리 목록
		BrdBbsHeadVO bbhvo = new BrdBbsHeadVO();
		bbhvo.setOrgCd(orgCd);
		bbhvo.setBbsCd(vo.getBbsCd());
		List<BrdBbsHeadVO> bbsHeadList = brdBbsHeadService.list(bbhvo).getReturnList();
		request.setAttribute("bbsHeadList", bbsHeadList);
		
		vo = brdBbsAtclService.viewAtcl(vo);
		String userNo = UserBroker.getUserNo(request);
		
		List<CourseVO> courseList = courseService.listUserCourse(userNo).getReturnList();
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("fileupload", "Y");
		request.setAttribute("summernote", "Y");
		request.setAttribute("gubun", "E");		
		request.setAttribute("vo", vo);
		return "home/board/bbs/write_atcl";
	}
	
	/**
     * @Method Name : editAtcl
     * @Method 설명 : 게시판 게시물 수정 한다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editAtcl")
	public String editAtcl(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);

		// 스크립트, 스타일 태그 제거
		vo.setAtclCts( HtmlCleaner.cleanScript(vo.getAtclCts()) );

		try {
			brdBbsAtclService.editAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.edit.failed"));
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);			
			return "mng/board/bbs/write_atcl";
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.edit.success"));

		if("BLOG".equals(bbivo.getBbsTypeCd())) {
			// 목록 화면으로
			return "redirect:"+
				new URLBuilder("/home/brd/bbs/listAtclMain")
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.addParameter("searchKey", vo.getSearchKey())
					.addParameter("searchValue", vo.getSearchValue())
					.toString();
		} else {
			// 읽기 화면으로
			return "redirect:"+
				new URLBuilder("/home/brd/bbs/viewAtclMain")
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.addParameter("searchKey", vo.getSearchKey())
					.addParameter("searchValue", vo.getSearchValue())
					.toString();
		}		
	}
	
	
	/**
     * @Method Name : removeAtcl
     * @Method 설명 : 게시판 게시물 삭제 한다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/removeAtcl")
	public String removeAtcl(Map commandMap, ModelMap model, BrdBbsAtclVO vo,
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		try {
			brdBbsAtclService.removeAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.failed"));
			vo = brdBbsAtclService.viewAtcl(vo);
			request.setAttribute("vo", vo);			
			return "mng/board/bbs/view_atcl";
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.success"));

		// 읽기 화면으로
		return "redirect:"+
			new URLBuilder("/home/brd/bbs/listAtclMain")
				.addParameter("bbsCd", vo.getBbsCd())
				.addParameter("pageIndex", vo.getPageIndex())
				.addParameter("searchKey", vo.getSearchKey())
				.addParameter("searchValue", vo.getSearchValue())
				.toString();
	}
	
	
	
	
	/**
     * @Method Name : listCmnt
     * @Method 설명 : 게시판 댓글 목록 화면을 보여준다. 
	 * @param BrdBbsCmntVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listCmnt")
	public String listCmnt(BrdBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultListVO<BrdBbsCmntVO> resultList = brdBbsAtclService.listCmntPageing(vo, vo.getCurPage(), vo.getListScale());
		request.setAttribute("cmntList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "home/board/bbs/list_cmnt_div";
	}
	
	/**
     * @Method Name : addCmnt
     * @Method 설명 : 게시판 댓글을 등록 한다. 
	 * @param BrdBbsCmntVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addCmnt")
	public String addCmnt(BrdBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsCmntVO> result = new ProcessResultVO<BrdBbsCmntVO>();
		try {
			brdBbsAtclService.addCmnt(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : addCmnt
     * @Method 설명 : 게시판 댓글을 수정 한다. 
	 * @param BrdBbsCmntVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editCmnt")
	public String editCmnt(BrdBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsCmntVO> result = new ProcessResultVO<BrdBbsCmntVO>();
		try {
			brdBbsAtclService.addCmnt(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : removeCmnt
     * @Method 설명 : 게시판 댓글을 삭제 한다. 
	 * @param BrdBbsCmntVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCmnt")
	public String removeCmnt(BrdBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsCmntVO> result = new ProcessResultVO<BrdBbsCmntVO>();
		try {
			brdBbsAtclService.removeCmnt(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping(value="/checkPassword")
	public String checkPassword(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<BrdBbsAtclVO> result = new ProcessResultVO<BrdBbsAtclVO>();
		
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setPassword(KISASeed.seed256HashEncryption(decrypt[0]));
		}
		
		String check = brdBbsAtclService.checkPassword(vo).getPasswordCheck();
		
		if(check.equals("0")) {
			result.setResult(-1);
		}else {
			result.setResult(1);
		}
		
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping(value="/addAtclService")
	public String addAtclService(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		//---- 게시판 정보 테이블 읽어 온다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo.setOrgCd(orgCd);
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);
		
		// 스크립트, 스타일 태그 제거
		vo.setAtclCts( HtmlCleaner.cleanScript(vo.getAtclCts()) );

		if(StringUtil.isNotNull(vo.getEncryptData())) {
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setServicePw(KISASeed.seed256HashEncryption(decrypt[0]));
		}
		
		try {
			brdBbsAtclService.addAtclService(vo);
			setAlertMessage(request, "정상적으로 등록 되었습니다.");
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);			
			return "mng/board/bbs/write_atcl";
		}
		
		return "redirect:"+
			new URLBuilder("/home/main/indexMain").toString();
	}
}
