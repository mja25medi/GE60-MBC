package egovframework.edutrack.web.board;

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

import egovframework.edutrack.comm.exception.AjaxProcessResultFailedException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
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
@RequestMapping(value="/adm/brd/bbs")
public class brdBbsInfoAdminController extends GenericController{

    /** service */
	@Autowired @Qualifier("brdBbsInfoService")
	private BrdBbsInfoService 		brdBbsInfoService;

	@Autowired @Qualifier("brdBbsHeadService")
	private BrdBbsHeadService 		brdBbsHeadService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 게시판 정보 관리 메인 화면으로 이동한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
    	List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
    	request.setAttribute("orgInfoList", orgInfoList);
		request.setAttribute("paging", "Y");
		
		return "board/bbs/info/main";
	}
	
	/**
     * @Method Name : SysCodeCtgrList
     * @Method 설명 : 게시판 정보 관리 분류 목록을 보여 준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/list_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<BrdBbsInfoVO> resultList = brdBbsInfoService.listPageing(vo, vo.getPageIndex(), vo.getListScale());
		request.setAttribute("bbsInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "board/bbs/info/list_info";
	}
	
	/**
     * @Method Name : addFrom
     * @Method 설명 : 게시판 정보 관리 분류 등록 화면을 보여 준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addForm")
	public String addForm(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setBbsTypeCd("BOARD");
        vo.setDetlViewCd("N");

        List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
    	request.setAttribute("orgInfoList", orgInfoList);

        String locale = UserBroker.getLocaleKey(request);
        List<SysCodeVO> bbsTypeList = sysCodeMemService.getSysCodeList("BBS_TYPE_CD");
        for(SysCodeVO sysCodeVO : bbsTypeList) {
        	for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
        		if(locale.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
        	}
        }
        List<SysCodeVO> detlViewList = sysCodeMemService.getSysCodeList("DETL_VIEW_CD");
        for(SysCodeVO sysCodeVO : detlViewList) {
        	for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
        		if(locale.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
        	}
        }
        request.setAttribute("bbsTypeList", bbsTypeList);
        request.setAttribute("detlViewList", detlViewList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		//request.setAttribute("uploadify", "Y");
		request.setAttribute("fileupload", "Y");
		
		
		return "board/bbs/info/write_info";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 게시판 정보 관리 분류를 등록 한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
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
     * @Method Name : editForm
     * @Method 설명 : 게시판 정보 관리 분류 수정 화면을 보여준다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editForm")
	public String editForm(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = brdBbsInfoService.view(vo);
		
        List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
    	request.setAttribute("orgInfoList", orgInfoList);

        String locale = UserBroker.getLocaleKey(request);
        List<SysCodeVO> bbsTypeList = sysCodeMemService.getSysCodeList("BBS_TYPE_CD");
        for(SysCodeVO sysCodeVO : bbsTypeList) {
        	for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
        		if(locale.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
        	}
        }
        List<SysCodeVO> detlViewList = sysCodeMemService.getSysCodeList("DETL_VIEW_CD");
        for(SysCodeVO sysCodeVO : detlViewList) {
        	for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
        		if(locale.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
        	}
        }
        request.setAttribute("bbsTypeList", bbsTypeList);
        request.setAttribute("detlViewList", detlViewList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "board/bbs/info/write_info";
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 게시판 정보 관리 분류를 수정 한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
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
     * @Method Name : remove
     * @Method 설명 : 게시판 정보 관리 분류를 삭제한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(BrdBbsInfoVO vo, Map commandMap, ModelMap model, 
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
     * @Method 설명 : 게시판 머릿말 목록 화면으로 이동한다. 
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/list_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listHeadPop")
	public String listHeadPop(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		ProcessResultListVO<BrdBbsHeadVO> resultList = brdBbsHeadService.list(vo);

		//-- 게시판 정보 조회
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("infovo", bbivo);
		

		request.setAttribute("bbsHeadList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "board/bbs/info/list_head";
	}
	
	/**
     * @Method Name : addFromHead
     * @Method 설명 : 게시판 머릿말 등록 화면을 보여 준다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/write_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addHeadForm")
	public String addHeadForm(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		
		//-- 게시판 정보 조회
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("infovo", bbivo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "board/bbs/info/write_head";
	}
	
	/**
     * @Method Name : addHead
     * @Method 설명 : 게시판 머릿말을 등록 한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return redirect:/adm/board/bbs/info/listHead.do
	 * @throws Exception
	 */
	@RequestMapping(value="/addHead")
	public String addHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			brdBbsHeadService.add(vo);
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.add.success"));
		} catch (DuplicateKeyException e) {
			setAlertMessage(request, "코드가 중복됩니다.");
			return "redirect:/adm/brd/bbs/addHeadForm?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd();
		} catch (Exception e) {
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.add.failed"));
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("infovo", bbivo);
			
			request.setAttribute("vo", vo);
			request.setAttribute("gubun", "A");
			//return "board/bbs/info/write_head";
			return "redirect:/adm/brd/bbs/addHeadForm?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd();
		}
		return "redirect:/adm/brd/bbs/listHeadPop?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd();
	}
	
	/**
     * @Method Name : editFormHead
     * @Method 설명 : 게시판 머릿말 수정 화면을 보여준다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/board/bbs/info/write_head.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editHeadForm")
	public String editHeadForm(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = brdBbsHeadService.view(vo);
		
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setBbsCd(vo.getBbsCd());
		bbivo = brdBbsInfoService.view(bbivo);
		request.setAttribute("infovo", bbivo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "board/bbs/info/write_head";
	}
	
	/**
     * @Method Name : editHead
     * @Method 설명 : 게시판 머릿말을 수정 한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return redirect:/adm/board/bbs/info/listHead.do
	 * @throws Exception
	 */
	@RequestMapping(value="/editHead")
	public String editHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			brdBbsHeadService.edit(vo);
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.edit.success"));
		}  catch (DuplicateKeyException e) {
			setAlertMessage(request, "코드가 중복됩니다.");
			return "redirect:/adm/brd/bbs/editHeadForm?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd()+"&headCd="+vo.getHeadCd();
		} catch (Exception e) {
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.edit.failed"));
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("infovo", bbivo);
			
			request.setAttribute("vo", vo);
			request.setAttribute("gubun", "E");
			//return "board/bbs/info/write_head";
			return "redirect:/adm/brd/bbs/editHeadForm?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd()+"&headCd="+vo.getHeadCd();
		}
		return "redirect:/adm/brd/bbs/listHeadPop?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd();
	}
	
	/**
     * @Method Name : removeHead
     * @Method 설명 : 게시판 머릿말을 삭제한다. 
	 * @param BrdBbsHeadVO 
	 * @param commandMap
	 * @param model
	 * @return redirect:/adm/board/bbs/info/listHead.do
	 * @throws Exception
	 */
	@RequestMapping(value="/removeHead")
	public String removeHead(BrdBbsHeadVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			brdBbsHeadService.remove(vo);
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.delete.success"));
		} catch (Exception e) {
			setAlertMessage(request, getMessage(request, "board.message.bbs.head.delete.failed"));
			
			//-- 게시판 정보 조회
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(vo.getBbsCd());
			bbivo = brdBbsInfoService.view(bbivo);
			request.setAttribute("infovo", bbivo);
			
			request.setAttribute("vo", vo);
			request.setAttribute("gubun", "E");
			return "board/bbs/info/write_head";
		}
		return "redirect:/adm/brd/bbs/listHeadPop?orgCd="+vo.getOrgCd()+"&bbsCd="+vo.getBbsCd();		
	}	
}
