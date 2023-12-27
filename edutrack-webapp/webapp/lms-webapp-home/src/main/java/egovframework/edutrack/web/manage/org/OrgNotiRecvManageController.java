package egovframework.edutrack.web.manage.org;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvService;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvVO;

@Controller
@RequestMapping(value="/mng/org/noti/recv")
public class OrgNotiRecvManageController extends GenericController {

	@Autowired @Qualifier("orgNotiRecvService")
	private OrgNotiRecvService orgNotiRecvService;
	
	@RequestMapping("/recvPop")
	public String recvPop(OrgNotiRecvVO vo, HttpServletRequest request) throws Exception {
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		List<OrgNotiRecvVO> recvList = orgNotiRecvService.recvList(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("recvList", recvList);
		
		return "mng/org/noti/noti_recv_pop";
	}

	@ResponseBody
	@RequestMapping(value="/view")
	public ProcessResultVO<OrgNotiRecvVO> viewRecv(OrgNotiRecvVO vo, HttpServletRequest request) throws Exception {
		ProcessResultVO<OrgNotiRecvVO> result = new ProcessResultVO<>();
		try {
			OrgNotiRecvVO recvInfo = orgNotiRecvService.view(vo);
			result.setReturnVO(recvInfo);
			result.setResultSuccess();
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultSuccess();
		} catch(Exception e) {
			result.setMessage("조회 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public ProcessResultVO<OrgNotiRecvVO> add(OrgNotiRecvVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<OrgNotiRecvVO> result = new ProcessResultVO<>();
		try {
			orgNotiRecvService.addRecv(vo);
			result.setResult(1);
			result.setMessage("정상 처리되었습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("등록에 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 등록에 실패했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public ProcessResultVO<OrgNotiRecvVO> update(OrgNotiRecvVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<OrgNotiRecvVO> result = new ProcessResultVO<>();
		try {
			orgNotiRecvService.updateRecv(vo);
			result.setResult(1);
			result.setMessage("정상 처리되었습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("수정 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 수정 실패했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public ProcessResultVO<OrgNotiRecvVO> delete(OrgNotiRecvVO vo, HttpServletRequest request) throws Exception {
		ProcessResultVO<OrgNotiRecvVO> result = new ProcessResultVO<>();
		try {
			orgNotiRecvService.deleteRecv(vo);
			result.setResult(1);
			result.setMessage("정상 처리되었습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("삭제 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 삭제 실패했습니다.");
		}
		return result;
	}
}
