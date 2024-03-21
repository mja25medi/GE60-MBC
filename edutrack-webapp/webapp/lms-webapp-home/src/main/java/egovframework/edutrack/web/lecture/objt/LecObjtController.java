package egovframework.edutrack.web.lecture.objt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtCmntVO;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtService;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("/lec/objt")
public class LecObjtController extends GenericController {
	
	@Autowired @Qualifier("lecObjtService")
	private LecObjtService lecObjtService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 	createCourseService;
	
	@RequestMapping("/objtMain")
	public String objtMain(LecObjtVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		request.setAttribute("lecObjtVO", vo);
		request.setAttribute("paging", "Y");
		
		return "home/lecture/objt/objt_main";
	}
	
	@RequestMapping("/listObjt")
	public String listObjt(LecObjtVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		String classUserType = (String)session.getAttribute("CLASSUSERTYPE");
		if(!"TCH".equals(classUserType)) {
			vo.setRegNo(UserBroker.getUserNo(request));
		}
		ProcessResultListVO<LecObjtVO> resultList = lecObjtService.getObjectionList(vo);
		List<LecObjtVO> objtList = resultList.getReturnList();
		PaginationInfo pageInfo = resultList.getPageInfo();
		request.setAttribute("objtList", objtList);
		request.setAttribute("pageInfo", pageInfo);
		
		return "home/lecture/objt/objt_list_div";
	}
	
	@RequestMapping("/viewObjtMain")
	public String viewObjtMain(LecObjtVO vo, HttpServletRequest request) throws Exception {
		LecObjtVO objtVO = lecObjtService.getObjection(vo);
		objtVO.setCmntList(lecObjtService.getCmntList(vo));
		request.setAttribute("objtVO", objtVO);
		
		return "home/lecture/objt/view_objt_main";
	}
	
	@RequestMapping("/writeObjtMain")
	public String writeObjtMain(LecObjtVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd((String)session.getAttribute(Constants.LOGIN_CRSCRECD));
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("creCourseVO", ccvo);
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		
		return "home/lecture/objt/write_objt_main";
	}
	
	@RequestMapping("/writeObjt")
	@ResponseBody
	public AbstractResult writeObjt(LecObjtVO vo, HttpServletRequest request) throws Exception	 {
		commonVOProcessing(vo, request);
		vo.setRegNm(UserBroker.getUserName(request));
		
		AbstractResult result = new AbstractResult();
		try {
			lecObjtService.makeObjection(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("등록 중 오류가 발생했습니다.");
		}
				
		return result;
	}
	
	@RequestMapping(value="/editObjtMain")
	public String editQstnMain(LecObjtVO vo, HttpServletRequest request) throws Exception {
		LecObjtVO objt = lecObjtService.viewObjection(vo);
		request.setAttribute("vo", objt);
		request.setAttribute("gubun","E");
		request.setAttribute("fileupload", "Y");

		return "home/lecture/objt/write_objt_main";
	}

	@RequestMapping("/editObjt")
	@ResponseBody
	public AbstractResult editObjt(LecObjtVO vo, HttpServletRequest request) throws Exception	 {
		commonVOProcessing(vo, request);
		vo.setRegNm(UserBroker.getUserName(request));

		AbstractResult result = new AbstractResult();
		try {
			lecObjtService.modifyObjection(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("등록 중 오류가 발생했습니다.");
		}

		return result;
	}

	@RequestMapping(value="deleteObjt")
	@ResponseBody
	public AbstractResult deleteQstn(LecObjtVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			lecObjtService.deleteObjection(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생 했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/writeCmnt")
	@ResponseBody
	public ProcessResultVO<LecObjtCmntVO> writeCmnt(LecObjtCmntVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setCmntLvl(1);
		ProcessResultVO<LecObjtCmntVO> result = new ProcessResultVO<>();
		vo.setUserType(UserBroker.getClassUserType(request));
		try {
			lecObjtService.makeObjectionCmnt(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("댓글 등록 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/deleteCmnt")
	@ResponseBody
	public AbstractResult deleteCmnt(LecObjtCmntVO vo, HttpServletRequest request) {
		
		AbstractResult result = new AbstractResult();
		try {
			lecObjtService.deleteCmnt(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생했습니다.");
		}
		return result;
	}
}
