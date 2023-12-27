package egovframework.edutrack.web.manage.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/brd/bbs")
public class BrdBbsManageController extends GenericController {
	
	@Autowired @Qualifier("brdBbsInfoService")
	private BrdBbsInfoService 			brdBbsInfoService;

	@Autowired @Qualifier("brdBbsHeadService")
	private BrdBbsHeadService 			brdBbsHeadService;
	
	@Autowired @Qualifier("brdBbsAtclService")
	private BrdBbsAtclService 			brdBbsAtclService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 			orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;
	
	/**
     * @Method Name : mainInfo
     * @Method 설명 : 게시판 관리 메인 화면을 보여준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/main_info.jsp
	 * @throws Exception
	 */
	@RequestMapping(value="/infoMain")
	public String infoMain(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "mng/board/bbs/main_info";
	}
	
	/**
     * @Method Name : listInfo
     * @Method 설명 : 게시판 정보 목록 화면을 보여준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/list_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listInfo")
	public String listInfo(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	vo.setUseYn("");

    	ProcessResultListVO<BrdBbsInfoVO> resultList = brdBbsInfoService.listPageing(vo, vo.getPageIndex());
    	request.setAttribute("bbsInfoList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/board/bbs/list_info";
	}
	
	/**
     * @Method Name : addFormInfo
     * @Method 설명 : 게시판 정보 등록 화면을 보여준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormInfoPop")
	public String addFormInfoPop(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setBbsTypeCd("BOARD");
        vo.setDetlViewCd("N");

        List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
    	request.setAttribute("orgInfoList", orgInfoList);

        String locale = UserBroker.getLocaleKey(request);
        List<SysCodeVO> bbsTypeList = sysCodeMemService.getSysCodeList("BBS_TYPE_CD");
        for(SysCodeVO scvo : bbsTypeList) {
        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
        	}
        }
        request.setAttribute("bbsTypeList", bbsTypeList);
        request.setAttribute("editorUseYn", Constants.WEBEDITOR_YSEYN);		
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/board/bbs/write_info";
	}
	
	/**
     * @Method Name : addInfo
     * @Method 설명 : 게시판 정보를 등록 한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addInfo")
	public String addInfo(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsInfoVO> result = new ProcessResultVO<BrdBbsInfoVO>();
		try {
			brdBbsInfoService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.info.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.info.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editFormInfo
     * @Method 설명 : 게시판 정보 수정 화면을 보여준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormInfoPop")
	public String editFormInfoPop(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		vo = brdBbsInfoService.view(vo);

        List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
    	request.setAttribute("orgInfoList", orgInfoList);

        String locale = UserBroker.getLocaleKey(request);
        List<SysCodeVO> bbsTypeList = sysCodeMemService.getSysCodeList("BBS_TYPE_CD");
        for(SysCodeVO scvo : bbsTypeList) {
        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
        	}
        }
        request.setAttribute("bbsTypeList", bbsTypeList);
        request.setAttribute("editorUseYn", Constants.WEBEDITOR_YSEYN);		
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/board/bbs/write_info";
	}
	
	/**
     * @Method Name : editInfo
     * @Method 설명 : 게시판 정보를 수정 한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editInfo")
	public String editInfo(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsInfoVO> result = new ProcessResultVO<BrdBbsInfoVO>();
		try {
			brdBbsInfoService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.info.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.info.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : removeInfo
     * @Method 설명 : 게시판 정보를 삭제 한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeInfo")
	public String removeInfo(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<BrdBbsInfoVO> result = new ProcessResultVO<BrdBbsInfoVO>();
		try {
			brdBbsInfoService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.bbs.info.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.bbs.info.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : listHead
     * @Method 설명 : 게시판 머릿말 목록 화면을 보여준다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/list_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listHeadPop")
	public String listHeadPop(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//-- 게시판 정보 조회
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);
		
		ProcessResultListVO<BrdBbsHeadVO> resultList = brdBbsHeadService.list(vo);

		request.setAttribute("bbsHeadList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/board/bbs/list_head";
	}
	
	/**
     * @Method Name : addFormHead
     * @Method 설명 : 게시판 머릿말 등록 화면을 보여준다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormHeadPop")
	public String addFormHeadPop(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUseYn("Y");

		//-- 게시판 정보 조회
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/board/bbs/write_head";
	}
	
	/**
     * @Method Name : addHead
     * @Method 설명 : 게시판 머릿말을 등록 한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addHead")
	public String addHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		try {
			brdBbsHeadService.add(vo);
		} catch (DuplicateKeyException ex) {
//			ex.printStackTrace();
			setAlertMessage(request, getMessage(request, "common.message.code.duplicate"));
			
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("bbsInfoVO", bbivo);

			return "mng/board/bbs/write_head"; //-- 다시 수정 화면
		} catch (Exception e) {
			
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.add.failed"));

			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("bbsInfoVO", bbivo);

			return "mng/board/bbs/write_head"; //-- 다시 수정 화면
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.head.add.success"));
		// 읽기 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/bbs/listHeadPop")
				.addParameter("orgCd", vo.getOrgCd())
				.addParameter("bbsCd", vo.getBbsCd())
				.toString();
	}
	
	/**
     * @Method Name : editFormHead
     * @Method 설명 : 게시판 머릿말 수정 화면을 보여준다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormHeadPop")
	public String editFormHeadPop(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = brdBbsHeadService.view(vo);

		//-- 게시판 정보 조회
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("bbsInfoVO", bbivo);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/board/bbs/write_head";
	}
	
	/**
     * @Method Name : editHead
     * @Method 설명 : 게시판 머릿말을 수정 한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editHead")
	public String editHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		try {
			brdBbsHeadService.edit(vo);
		} catch (Exception e) {
			
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.edit.failed"));

			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("bbsInfoVO", bbivo);

			return "mng/board/bbs/write_head"; //-- 다시 수정 화면
		}
		setAlertMessage(request, getMessage(request, "board.message.bbs.head.edit.success"));
		// 읽기 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/bbs/listHeadPop")		
				.addParameter("orgCd", vo.getOrgCd())
				.addParameter("bbsCd", vo.getBbsCd())
				.toString();
	}
	
	/**
     * @Method Name : removeHead
     * @Method 설명 : 게시판 머릿말을 삭제 한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeHead")
	public String removeHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		try {
			brdBbsHeadService.remove(vo);
		} catch (Exception e) {
			
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.delete.failed"));

			vo = brdBbsHeadService.view(vo);
			
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("bbsInfoVO", bbivo);

			return "mng/board/bbs/write_head"; //-- 다시 수정 화면
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.head.delete.success"));
		// 읽기 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/bbs/listHeadPop")
				.addParameter("orgCd", vo.getOrgCd())
				.addParameter("bbsCd", vo.getBbsCd())
				.toString();
	}
	
	/**
     * @Method Name : listAtcl
     * @Method 설명 : 게시판 게시물 목록 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/list_atcl_{@board_type}.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listAtclMain")
	public String listAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
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
		
		String crsCreCd = request.getParameter("crsCreCd");
		vo.setCrsCreCd(crsCreCd);
		
		//---- 게시판 말머리 목록
		BrdBbsHeadVO bbhvo = new BrdBbsHeadVO();
		bbhvo.setOrgCd(orgCd);
		bbhvo.setBbsCd(vo.getBbsCd());
		List<BrdBbsHeadVO> bbsHeadList = brdBbsHeadService.list(bbhvo).getReturnList();
		request.setAttribute("bbsHeadList", bbsHeadList);

		//---- 게시판 목록 유형 설정.
		String bbsListType = "list_atcl_board"; //-- 기본형은 일반 게시판형.
		if("BOARD".equals(bbivo.getBbsTypeCd())) bbsListType = "list_atcl_board";
		else if("GALLERY".equals(bbivo.getBbsTypeCd())) bbsListType = "list_atcl_gallery";
		else if("SERVICE".equals(bbivo.getBbsTypeCd())) {
			bbsListType = "list_atcl_service";
		}
		else if("BLOG".equals(bbivo.getBbsTypeCd())) bbsListType = "list_atcl_blog";
		else if("NEWSBOARD".equals(bbivo.getBbsTypeCd())) bbsListType = "list_atcl_news_board";

		boolean filein = false;
		if("BLOG".equals(bbivo.getBbsTypeCd())) filein = true;

		ProcessResultListVO<BrdBbsAtclVO> resultList = brdBbsAtclService.listAtclPageing(vo, vo.getPageIndex(), bbivo.getListViewCnt(), filein);

    	request.setAttribute("bbsAtclList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
    	request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "mng/board/bbs/"+bbsListType;
	}
	
	/**
     * @Method Name : viewAtcl
     * @Method 설명 : 게시판 게시물 보기 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/view_atcl.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewAtclMain")
	public String viewAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
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
		
		vo = brdBbsAtclService.viewAtcl(vo, true);

		request.setAttribute("vo", vo);
		request.setAttribute("commonPaging", "Y");
		return "mng/board/bbs/view_atcl";
	}
	
	@RequestMapping(value="/viewAtclServiceMain")
	public String viewAtclServiceMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
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
		
		vo = brdBbsAtclService.viewAtclService(vo, true);

		request.setAttribute("vo", vo);
		request.setAttribute("commonPaging", "Y");
		
		if(bbivo.getBbsCd().equals("BOCTI")) {
			return "mng/board/bbs/view_atcl_bocti";
		}else {
			return "mng/board/bbs/view_atcl_service";
		}
	}
	
	/**
     * @Method Name : addFormAtcl
     * @Method 설명 : 게시판 게시물 등록 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_atcl.jsp"
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
		
		if(bbivo.getBbsTypeCd().equals("CATEGORY")) {
			String locale = UserBroker.getLocaleKey(request);
			List<SysCodeVO> categoryList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
	        for(SysCodeVO scvo : categoryList) {
	        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
	        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
	        	}
	        }
	        
	    request.setAttribute("categoryList", categoryList);
		}
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		return "mng/board/bbs/write_atcl";
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
	@RequestMapping(value="/addAtclMain")
	public String addAtclMain(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
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
				new URLBuilder("/mng/brd/bbs/listAtclMain")
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.toString();
		} else {
			// 읽기 화면으로
			return "redirect:"+
				new URLBuilder("/mng/brd/bbs/viewAtclMain")
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.toString();
		}		
	}
	
	/**
     * @Method Name : editFormAtcl
     * @Method 설명 : 게시판 게시물 수정 화면을 보여준다. 
	 * @param BrdBbsAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/write_atcl.jsp"
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
		vo.setAtclCts(vo.getAtclCts().replaceAll("\r\n", "<br>"));;
		
		if(bbivo.getBbsTypeCd().equals("CATEGORY")) {
			String locale = UserBroker.getLocaleKey(request);
			List<SysCodeVO> categoryList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
	        for(SysCodeVO scvo : categoryList) {
	        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
	        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
	        	}
	        }
	        request.setAttribute("categoryList", categoryList);
		}
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("gubun", "E");		
		request.setAttribute("vo", vo);
		return "mng/board/bbs/write_atcl";
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

		if(StringUtil.isNotNull(vo.getEncryptData())) {
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setPassword(KISASeed.seed256HashEncryption(decrypt[0]));
		}
		
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
				new URLBuilder("/mng/brd/bbs/listAtclMain")
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
					.toString();
		} else {
			// 읽기 화면으로
			return "redirect:"+
				new URLBuilder("/mng/brd/bbs/viewAtclMain")
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("bbsCd", vo.getBbsCd())
					.addParameter("pageIndex", vo.getPageIndex())
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
	public String removeAtcl(BrdBbsAtclVO vo, Map commandMap, ModelMap model, 
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
			new URLBuilder("/mng/brd/bbs/listAtclMain")
				.addParameter("bbsCd", vo.getBbsCd())
				.addParameter("pageIndex", vo.getPageIndex())
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
				
		ProcessResultListVO<BrdBbsCmntVO> resultList = brdBbsAtclService.listCmntPageing(vo, vo.getPageIndex(), 10);
		return JsonUtil.responseJson(response, resultList);
	}
	
	/**
     * @Method Name : viewCmnt
     * @Method 설명 : 게시판 댓글 화면을 보여준다. 
	 * @param BrdBbsCmntVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/viewCmnt")
	public String viewCmnt(BrdBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = brdBbsAtclService.viewCmnt(vo);
		return JsonUtil.responseJson(response, vo);
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
}
