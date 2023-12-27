package egovframework.edutrack.modules.log.message.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.spring.Assert;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.notification.MessageNotificationException;
import egovframework.edutrack.notification.SendService;
import egovframework.edutrack.notification.email.service.MailDecorator;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 메시지 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logMsgLogService")
public class LogMsgLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogMsgLogService {
	
	/** Mapper */
    @Resource(name="logMsgLogMapper")
    private LogMsgLogMapper 		logMsgLogMapper;

    @Resource(name="logMsgTransLogMapper")
    private LogMsgTransLogMapper 	logMsgTransLogMapper;
    
	@Resource(name="sendMailService")
	private SendService			sendMailService;

	@Resource(name="sendSmsService")
	private SendService			sendSmsService;

	@Resource(name="sysFileService")
	private SysFileService		sysFileService;

	private final class MessageFileHandler
			implements FileHandler<LogMsgLogVO> {

		@Override
		public String getRepoCd() {
			return "MESSAGE";
		}

		@Override
		public String getPK(LogMsgLogVO vo) {
			return vo.getMsgSn().toString();
		}

		@Override
		public List<SysFileVO> getFiles(LogMsgLogVO vo) {
			return vo.getAttachFiles();
		}

		@Override
		public LogMsgLogVO setFiles(LogMsgLogVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	/**
	 * 메시지 목록을 조회한다.<br>
     * 필수 조회 항목 : msgDivCd<br>
     * 선택 조회 항목 : msgSn
	 * @param vo
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listMessage(LogMsgLogVO vo) {
		ProcessResultListVO<LogMsgLogVO> resultList = new ProcessResultListVO<LogMsgLogVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logMsgLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogMsgLogVO> msgList =  logMsgLogMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(msgList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 메시지 전송 세부내역 목록 조회
	 * @param request
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgTransLogVO> listMessageTrans(LogMsgTransLogVO vo, int pageIndex) {
		ProcessResultListVO<LogMsgTransLogVO> resultList = new ProcessResultListVO<LogMsgTransLogVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logMsgTransLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogMsgTransLogVO> msgList =  logMsgTransLogMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(msgList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 회원정보에서 해당 사용자의 연락처 목록을 조회한다.
	 * targetVo의 항목에는 ','로 구분된 고유번호 String이 필요하다.
	 * @param targetVo
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgTransLogVO> listReceiver(LogMsgLogVO vo) {
		
		ProcessResultListVO<LogMsgTransLogVO> resultList = new ProcessResultListVO<LogMsgTransLogVO>();
		
		LogMsgTransLogVO transVO = vo.getLogMsgTransLogVO();
		transVO.setMsgDivCd(vo.getMsgDivCd());

		try {
			String userList = transVO.getUserNoList();
			String stdList = transVO.getStdNoList();
			
			List<LogMsgTransLogVO> receiverUserList = new ArrayList<>();
			List<LogMsgTransLogVO> receiverStdList = new ArrayList<>();
			List<LogMsgTransLogVO> receiverMergeList = new ArrayList<>();
			
			if((userList != null && !"".equals(userList))){
				userList = userList.replaceAll("/", "");
				transVO.setUserNoListArr(userList.split(","));
				
				receiverUserList =  logMsgTransLogMapper.listReceiver(transVO);
				
			}
			
			if(stdList != null && !"".equals(stdList)){
				stdList = stdList.replaceAll("/", "");
				transVO.setStdNoListArr(stdList.split(","));
				
				receiverStdList =  logMsgTransLogMapper.listReceiverStd(transVO);
			}
			
			if(receiverUserList != null && receiverUserList.size() > 0) {
				receiverMergeList.addAll(receiverUserList);
			}
			if(receiverStdList != null && receiverStdList.size() > 0) {
				receiverMergeList.addAll(receiverStdList);
			}
			
			if(receiverMergeList != null && receiverMergeList.size() > 0) {
				resultList.setResult(1);
				resultList.setReturnList(receiverMergeList);
			}else {
				resultList.setResult(-1);
			}
			
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 메시지 전송 정보를 저장한다.
	 * 수신자 목록이 비어있을 경우 transVO의 값을 토대로 수신자 목록을 생성해서 저장한다.<br>
	 * @param vo
	 * @return
	 */
	@Override
	public LogMsgLogVO addMessage(LogMsgLogVO vo) throws Exception {

		if(ValidationUtils.isZeroNull(vo.getMsgSn())) {
			vo.setMsgSn(logMsgLogMapper.selectKey());
		}
		int result = logMsgLogMapper.insert(vo);
		sysFileService.bindFile(vo, new MessageFileHandler());

		// 메시지 전송로그(수신자 목록) 이 비어있으면..
		if(vo.getSubTransList().size() < 1) {
			// targetList를 조회해서 수신자 목록을 채운다. (transVO의 값이 있어야 한다.)
			List<LogMsgTransLogVO> targetlist = this.listReceiver(vo).getReturnList();
			System.out.println("targetlist 는 ? =="+targetlist);
			for (LogMsgTransLogVO lmtlvo : targetlist) {
				vo.addSubTrans(lmtlvo);
			}
		}

		// 각 수신자 목록을 저장한다.
		for (LogMsgTransLogVO lmtlvo : vo.getSubTransList()) {
			if(ValidationUtils.isZeroNull(vo.getMsgTransSn())) {
				lmtlvo.setMsgTransSn(logMsgTransLogMapper.selectKey());
			}
			logMsgTransLogMapper.insert(lmtlvo);
		}

		return vo;
	}

	/**
	 * 메시지 재전송 정보를 저장한다.
	 * @param vo
	 * @return
	 */
	@Override
	public int addResendMessage(LogMsgLogVO vo) throws Exception {
		LogMsgLogVO svo = logMsgLogMapper.select(vo);

		//기존 전송 정보 수정
		svo.setSendSts("RESEND");
		svo.setModNo(vo.getModNo());
		logMsgLogMapper.update(svo);

		//전송 정보 insert
		int result = logMsgLogMapper.reinsert(svo);

		//전송 대상 정보 insert
		LogMsgTransLogVO lmtlvo = new LogMsgTransLogVO();
		lmtlvo.setMessage(svo);
		logMsgTransLogMapper.reinsert(lmtlvo);

		return result;
	}

	/**
	 * 메시지 전송단위로 수신자 목록을 조회한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param LogMsgTransLogVO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgTransLogVO> listMessageTrans(LogMsgTransLogVO vo) {
		ProcessResultListVO<LogMsgTransLogVO> resultList = new ProcessResultListVO<LogMsgTransLogVO>(); 
		try {
			List<LogMsgTransLogVO> transList =  logMsgTransLogMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(transList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 메시지 전송단일 항목을 조회한다. 수신자 목록을 포함한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param vo
	 * @return
	 */
	@Override
	public LogMsgLogVO viewMessage(LogMsgLogVO vo) throws Exception {
		vo = logMsgLogMapper.select(vo);
		vo = sysFileService.getFile(vo, new MessageFileHandler());
		LogMsgTransLogVO lmtlvo = new LogMsgTransLogVO();
		lmtlvo.setMsgSn(vo.getMsgSn());

		List<LogMsgTransLogVO> transList = logMsgTransLogMapper.list(lmtlvo);
		vo.setSubTransList(transList);
		return vo;
	}

	/**
	 * 메시지 전송단일 항목을 조회한다. 수신자 목록을 포함한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param vo
	 * @return
	 */
	@Override
	public LogMsgLogVO pageViewMessage(LogMsgLogVO vo, int curPage) throws Exception {
		/*vo = messageDao.selectMessage(vo).getReturnDto();
		vo = fileService.getFile(vo, new MessageFileHandler());
		return new ProcessResultDTO<LogMsgLogVO>().setResultSuccess().setReturnDto(vo);*/
		return vo;
	}

	@Override
	public boolean isPossibleSendSmsByMonthLimit() {
		//return messageDao.selectPossibleSendSmsByMonthLimit();
		return true;
	}

	/**
	 * 메시지 전송 결과를 저장처리 한다.
	 * @param vo
	 */
	@Override
	public void updateMessageTrans(LogMsgTransLogVO vo) throws Exception {
		logMsgTransLogMapper.update(vo);
	}

	/**
	 * 단일 항목 MessageTrans를 조회한다. (LogMsgLogVO 항목을 포함한다.)
	 * @param vo
	 * @return
	 */
	@Override
	public LogMsgTransLogVO viewMessage(LogMsgTransLogVO vo) throws Exception {
		vo = logMsgTransLogMapper.select(vo);

		// 상위 메시지 정보도 저장한다.
		LogMsgLogVO message = logMsgLogMapper.select(vo.getMessage());
		try{
			message = sysFileService.getFile(message, new MessageFileHandler());
		} catch (SQLException e) {
		}
		vo.setMessage(message);
		return vo;
	}

	/**
	 * 단건 메시지를 실제로 전송한다.
	 * @param trans 메시지
	 */
	@Override
	public void sendMessageTrans(LogMsgTransLogVO trans) throws MessageNotificationException, Exception {
		Assert.notNull(trans.getMsgTransSn(), "메시지 전송 고유번호는 필수값입니다.");

		// 전송 대상 주소 값이 비어있으면 DB에서 다시 조회 (주키만 넘어온 경우이다.)
		if(StringUtil.isNull(trans.getRecvAddr()) || StringUtil.isNull(trans.getTransSts())) {
			trans = this.viewMessage(trans);
		}

		// 대기상태 메시지가 아니라면 취소
		if(!trans.getTransSts().equals(LogMsgTransLogVO.SEND_WATING)) return;

		try {
			// 메시지 유형에 맞춰서 메시지 발송 처리
			chooseSender(trans).sendMessage(chooseDecorator(trans));
		}catch (Exception ex) {
			// 실패 코드 저장하고 다음 메시지 전송을 속행할 수 있도록 ignore...
			trans.setTransSts(LogMsgTransLogVO.SEND_FAILED);
			//log.error("메시지 전송을 수행할 수 없습니다. -> 메시지 수신처 : " + trans.getRecvAddr());
		} finally {
			// 메시지 전송 결과를 저장
			try {
				logMsgTransLogMapper.update(trans);
			} catch (Exception e) {
				
			}
		}
	}

	/**
	 * 메시지를 등록하고 즉시 전송한다. 즉시 처리가 필요한 서비스에서만 사용한다.<br>
	 *
	 * @param vo
	 * @throws MessageNotificationException
	 */
	@Override
	public LogMsgLogVO addMessageWithSend(LogMsgLogVO vo) throws MessageNotificationException, Exception {
		vo = this.addMessage(vo);
		this.sendMessage(vo);
		return vo;
	}

	/**
	 * 단위 메시지의 복수개 수신자 목록을 일괄로 전송한다.
	 * @param message
	 * @throws MessageNotificationException
	 */
	@Override
	public void sendMessage(LogMsgLogVO message) throws MessageNotificationException, Exception {
		Assert.notNull(message.getMsgSn(), "메시지 고유번호는 필수값입니다.");
		Assert.notNull(message.getMsgDivCd(), "메시지의 종류구분코드는 필수값입니다.");
		System.out.println("sendMessage start");

		List<LogMsgTransLogVO> transList = null;

		// 발신자 주소정보가 비어있으면 DB에서 다시 조회 (주키만 넘어온 경우로 가정)
		if(StringUtil.isNull(message.getSendAddr())) {
			message = this.viewMessage(message);
		}

		if(message.getSubTransList().size() == 0) {
			// 전송정보도 대기상태 메시지만 같이 조회
			LogMsgTransLogVO param = new LogMsgTransLogVO();
			param.setMsgSn(message.getMsgSn());
			param.setTransSts(LogMsgTransLogVO.SEND_WATING);	// 미발송 메시지만 조회

			transList = logMsgTransLogMapper.list(param);
			message.setSubTransList(transList);
		}

		//-- 2015.11.18 jamfam 썬더메일 대량 메일 연동시 변경.
		if("EMAIL".equals(message.getMsgDivCd()) && "ThunderMail".equals(Constants.MAIL_AGENT) && message.getSubTransList().size() > 1) {
			// 보내는 형식이 EMAIL 이고, Mail Agent가 ThunderMail 이고, 1명 이상의 사용자게 보내질때 대량 메일 사용.
			sendMailService.sendMassMessage(message);
			for(LogMsgTransLogVO mtdto : message.getSubTransList()) {
				mtdto.setTransSts(message.getSendSts());
				if(!"100".equals(message.getSendSts())) {
					mtdto.setErrorMsg(message.getTransSts());
				}
				logMsgTransLogMapper.update(mtdto);
			}
		} else {
			for (LogMsgTransLogVO trans : message.getSubTransList()) {
				this.sendMessageTrans(trans);
			}
		}
	}

	/**
	 * 테이블에서 발신되지 않은 모든 메시지를 목록을 찾아서 메시지 송신을 시도한다.
	 * @param msgDivCd (EMAIL, SMS) 발신을 처리할 메시지 종류
	 * @throws MessageNotificationException
	 */
	@Override
	public void sendMessageDivCd(String msgDivCd) throws MessageNotificationException, Exception {

		System.out.println("sendMessageDivCd start");
		// 발신 처리가 필요한 메시지 목록을 조회
		LogMsgLogVO paramVO = new LogMsgLogVO();
		paramVO.setMsgDivCd(msgDivCd);
		paramVO.setSearchKey(LogMsgTransLogVO.SEARCH_TRANS_STATUS);
		
		

		List<LogMsgLogVO> listMessage = logMsgLogMapper.list(paramVO);

		//log.info("메시지 전송(" + msgDivCd + ")을 시작합니다. 갯수 : " + listMessage.size());

		// LogMsgLogVO 단위 전송 반복
		for (LogMsgLogVO message : listMessage) {
			// 첨부파일을 이곳에서 연결시킨다.
			sysFileService.getFile(message, new MessageFileHandler());
			if(Constants.MSG_SMS_AGENT.equals("PPURIO")) {
				// 뿌리오 문자 API 연동 기능 영역
			} else {
				this.sendMessage(message);
			}
		}

		//log.info("메시지 전송 처리를 완료했습니다.");
	}

	/**
	 * 메시지 구분코드에 따라 적절한 메일 발송 빈을 반환한다.
	 * @param message
	 * @return
	 */
	private SendService chooseSender(LogMsgTransLogVO trans) {
		if(trans.getMessage() == null || trans.getMessage().getMsgDivCd() == null)
			throw new MessageNotificationException("전송 메시지가 설정되지 않았습니다.");

		if(trans.getMessage().getMsgDivCd().equals("EMAIL")) {
			return this.sendMailService;
		} else if(trans.getMessage().getMsgDivCd().equals("SMS")) {
			return this.sendSmsService;
		}

		throw new MessageNotificationException("메시지 종류가 지정되지 않았습니다.");
	}

	/**
	 * 메시지 구분코드가 EMAIL인지 확인하고 데코레이터를 주입한다.
	 * @param trans
	 * @return
	 */
	private LogMsgTransLogVO chooseDecorator(LogMsgTransLogVO trans) {
		if(!trans.getMessage().getMsgDivCd().equals("EMAIL"))
			return trans;

		// 기본형 데코레이터 주입
		trans.getMessage().setMessageDecorator(new MailDecorator());
		return trans;
	}


	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listSendMsgList(LogMsgLogVO vo, int pageIndex, int listScale, int pageScale) {
		ProcessResultListVO<LogMsgLogVO> resultList = new ProcessResultListVO<LogMsgLogVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logMsgLogMapper.countSendMsg(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogMsgLogVO> msgList =  logMsgLogMapper.listSendMsgPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(msgList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listSendMsgList(LogMsgLogVO vo, int pageIndex, int listScale) {
		return this.listSendMsgList(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listSendMsgList(LogMsgLogVO vo, int pageIndex) {
		return this.listSendMsgList(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listRecvMsgList(LogMsgLogVO vo, int pageIndex, int listScale, int pageScale) {
		ProcessResultListVO<LogMsgLogVO> resultList = new ProcessResultListVO<LogMsgLogVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logMsgLogMapper.countRecvMsg(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogMsgLogVO> msgList =  logMsgLogMapper.listRecvMsgPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(msgList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listRecvMsgList(LogMsgLogVO vo, int pageIndex, int listScale) {
		return this.listRecvMsgList(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listRecvMsgList(LogMsgLogVO vo, int pageIndex) {
		return this.listRecvMsgList(vo, pageIndex, vo.getListScale(), vo.getPageScale());
	}

	/**
	 * 읽지 않은 메시지 카운트
	 * @param messageDTO
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public int msgCount(LogMsgLogVO vo) throws DataAccessException, Exception {
		return (int)logMsgLogMapper.countMsg(vo);
	}

	/**
	 * 메시지 읽음 표시
	 * transSts, transDttm, errorCd만 수정이 가능하다.
	 * @param messageTransDTO
	 * @return
	 */
	@Override
	public int readCheck(LogMsgTransLogVO vo) throws Exception {
		return (int)logMsgTransLogMapper.updateRead(vo);
	}
	
	@Override
	public int deleteMsg(LogMsgTransLogVO vo) throws Exception {
		return logMsgTransLogMapper.deleteByRecvAddr(vo);
	}

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listTopRecvMsg(LogMsgLogVO vo) {
		ProcessResultListVO<LogMsgLogVO> resultList = new ProcessResultListVO<LogMsgLogVO>(); 
		try {
			List<LogMsgLogVO> msgList =  logMsgLogMapper.listTopRecvMsg(vo);
			resultList.setResult(1);
			resultList.setReturnList(msgList);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * [CRM] 메시지 리스트 조회
	 * @param LogMsgLogVO
	 * @return ProcessResultList
	 */
	@Override
	public ProcessResultListVO<LogMsgLogVO> listPageingForCRM(LogMsgLogVO vo) {
		ProcessResultListVO<LogMsgLogVO> resultList = new ProcessResultListVO<>();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = logMsgLogMapper.countForCRM(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<LogMsgLogVO> msgList = logMsgLogMapper.listPageingForCRM(vo);
		
		resultList.setReturnList(msgList);
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}

}
