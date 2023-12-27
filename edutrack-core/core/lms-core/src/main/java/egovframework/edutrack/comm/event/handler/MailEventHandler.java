package egovframework.edutrack.comm.event.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.event.AddEquRentEvent;
import egovframework.edutrack.comm.event.AddFacResEvent;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvService;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvVO;

@Component
public class MailEventHandler {
	
	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService					logMsgLogService;
	
	@Autowired @Qualifier("orgEmailTplService")
	private OrgEmailTplService		 		orgEmailTplService;
	
	@Autowired @Qualifier("orgNotiRecvService")
	private OrgNotiRecvService orgNotiRecvService;

	@Async
	@EventListener
	public void handleAddFacResEvent(AddFacResEvent event) {
		OrgFacResVO resInfo = event.getFacResInfo();
		String orgCd = resInfo.getOrgCd();
		
		LogMsgLogVO message = new LogMsgLogVO();
		message.setSendMenuCd(event.getMenuCd());
		message.setMsgDivCd(event.getMsgDivCd());
		message.setSendAddr(Constants.JAVAMAIL_USER);
		message.setSendNm("메디오피아테크");
		
		OrgNotiRecvVO recvVO = new OrgNotiRecvVO();
		recvVO.setOrgCd(orgCd);
		recvVO.setNotiCtgr("FAC");
		List<LogMsgTransLogVO> transList = orgNotiRecvService.recvList(recvVO).stream()
				.map(OrgNotiRecvVO::toMsgTransVO)
				.collect(Collectors.toList());
		
		if(transList.size() == 0) return;
		
		message.setSubTransList(transList);
		
		Map<String, Object> argu = new HashMap<String, Object>();
		argu.put("FacCtgrName", resInfo.getFacCtgrNm());
		argu.put("FacName", resInfo.getFacNm());
		argu.put("ResUserName", resInfo.getUserNm());
		argu.put("ResDttm", resInfo.getResDt() + " " + resInfo.getResStm() + "~" + resInfo.getResEtm());
		
		orgEmailTplService.decorator(orgCd, "TPL000007", argu, message);
		
		try {
			logMsgLogService.addMessageWithSend(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Async
	@EventListener
	public void handleAddEquRentEvent(AddEquRentEvent event) {
		OrgEquRentVO rentInfo = event.getEquRentInfo();
		String orgCd = rentInfo.getOrgCd();
		
		LogMsgLogVO message = new LogMsgLogVO();
		message.setSendMenuCd(event.getMenuCd());
		message.setMsgDivCd(event.getMsgDivCd());
		message.setSendAddr(Constants.JAVAMAIL_USER);
		message.setSendNm("메디오피아테크");
		
		OrgNotiRecvVO recvVO = new OrgNotiRecvVO();
		recvVO.setOrgCd(orgCd);
		recvVO.setNotiCtgr("EQU");
		List<LogMsgTransLogVO> transList = orgNotiRecvService.recvList(recvVO).stream()
				.map(OrgNotiRecvVO::toMsgTransVO)
				.collect(Collectors.toList());
		
		if(transList.size() == 0) return;
		
		message.setSubTransList(transList);
		
		Map<String, Object> argu = new HashMap<String, Object>();
		argu.put("EquName", rentInfo.getEquNm());
		argu.put("RentUserName", rentInfo.getUserNm());
		argu.put("RentDttm", 
				DateTimeUtil.getDateType(8, rentInfo.getRentStartDttm(), "-")
				+ " ~ "
				+ DateTimeUtil.getDateType(8, rentInfo.getRentEndDttm(), "-"));
		
		orgEmailTplService.decorator(orgCd, "TPL000008", argu, message);
		
		try {
			logMsgLogService.addMessageWithSend(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
