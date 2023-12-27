package egovframework.edutrack.modules.log.message.service;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.notification.MessageNotificationException;

public interface LogMsgLogService {

	/**
	 * 메시지 목록을 조회한다.<br>
	 * 필수 조회 항목 : msgDivCd<br>
	 * 선택 조회 항목 : msgSn
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listMessage(
			LogMsgLogVO vo);

	/**
	 * 메시지 전송 세부내역 목록 조회
	 * @param request
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgTransLogVO> listMessageTrans(
			LogMsgTransLogVO vo, int pageIndex);

	/**
	 * 회원정보에서 해당 사용자의 연락처 목록을 조회한다.
	 * targetVo의 항목에는 ','로 구분된 고유번호 String이 필요하다.
	 * @param targetVo
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgTransLogVO> listReceiver(LogMsgLogVO vo);

	/**
	 * 메시지 전송 정보를 저장한다.
	 * 수신자 목록이 비어있을 경우 transVO의 값을 토대로 수신자 목록을 생성해서 저장한다.<br>
	 * @param vo
	 * @return
	 */
	public abstract LogMsgLogVO addMessage(LogMsgLogVO vo) throws Exception;

	/**
	 * 메시지 재전송 정보를 저장한다.
	 * @param vo
	 * @return
	 */
	public abstract int addResendMessage(LogMsgLogVO vo) throws Exception;

	/**
	 * 메시지 전송단위로 수신자 목록을 조회한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param LogMsgTransLogVO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgTransLogVO> listMessageTrans(
			LogMsgTransLogVO vo);

	/**
	 * 메시지 전송단일 항목을 조회한다. 수신자 목록을 포함한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param vo
	 * @return
	 */
	public abstract LogMsgLogVO viewMessage(LogMsgLogVO vo) throws Exception;

	/**
	 * 메시지 전송단일 항목을 조회한다. 수신자 목록을 포함한다.
	 * 필수 파라매터 : msgDivCd, msgSn
	 * @param vo
	 * @return
	 */
	public abstract LogMsgLogVO pageViewMessage(LogMsgLogVO vo, int curPage)
			throws Exception;

	public abstract boolean isPossibleSendSmsByMonthLimit();

	/**
	 * 메시지 전송 결과를 저장처리 한다.
	 * @param vo
	 */
	public abstract void updateMessageTrans(LogMsgTransLogVO vo)
			throws Exception;

	/**
	 * 단일 항목 MessageTrans를 조회한다. (LogMsgLogVO 항목을 포함한다.)
	 * @param vo
	 * @return
	 */
	public abstract LogMsgTransLogVO viewMessage(LogMsgTransLogVO vo)
			throws Exception;

	/**
	 * 단건 메시지를 실제로 전송한다.
	 * @param trans 메시지
	 */
	public abstract void sendMessageTrans(LogMsgTransLogVO trans)
			throws MessageNotificationException, Exception;

	/**
	 * 메시지를 등록하고 즉시 전송한다. 즉시 처리가 필요한 서비스에서만 사용한다.<br>
	 *
	 * @param vo
	 * @throws MessageNotificationException
	 */
	public abstract LogMsgLogVO addMessageWithSend(LogMsgLogVO vo)
			throws MessageNotificationException, Exception;

	/**
	 * 단위 메시지의 복수개 수신자 목록을 일괄로 전송한다.
	 * @param message
	 * @throws MessageNotificationException
	 */
	public abstract void sendMessage(LogMsgLogVO message)
			throws MessageNotificationException, Exception;

	/**
	 * 테이블에서 발신되지 않은 모든 메시지를 목록을 찾아서 메시지 송신을 시도한다.
	 * @param msgDivCd (EMAIL, SMS) 발신을 처리할 메시지 종류
	 * @throws MessageNotificationException
	 */
	public abstract void sendMessageDivCd(String msgDivCd)
			throws MessageNotificationException, Exception;

	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listSendMsgList(
			LogMsgLogVO vo, int pageIndex, int listScale, int pageScale);

	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listSendMsgList(
			LogMsgLogVO vo, int pageIndex, int listScale);

	/**
	 * 보낸 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listSendMsgList(
			LogMsgLogVO vo, int pageIndex);

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listRecvMsgList(
			LogMsgLogVO vo, int pageIndex, int listScale, int pageScale);

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listRecvMsgList(
			LogMsgLogVO vo, int pageIndex, int listScale);

	/**
	 * 받은 쪽지함 목록
	 * @param messageDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listRecvMsgList(
			LogMsgLogVO vo, int pageIndex);

	/**
	 * 읽지 않은 메시지 카운트
	 * @param messageDTO
	 * @return
	 * @throws DataAccessException
	 */
	public abstract int msgCount(LogMsgLogVO vo) throws DataAccessException,
			Exception;

	/**
	 * 메시지 읽음 표시
	 * transSts, transDttm, errorCd만 수정이 가능하다.
	 * @param messageTransDTO
	 * @return
	 */
	public abstract int readCheck(LogMsgTransLogVO vo) throws Exception;
	
	/**
	 * 메시지 읽음 표시
	 * transSts, transDttm, errorCd만 수정이 가능하다.
	 * @param messageTransDTO
	 * @return
	 */
	public abstract int deleteMsg(LogMsgTransLogVO vo) throws Exception;
	
	/**
	 * 최근 메시지 목록 조회
	 * @param LogMsgLogVO
	 * @return ProcessResultListVO<LogMsgLogVO>
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listTopRecvMsg(LogMsgLogVO vo) throws Exception;
	
	/**
	 * [CRM] 메시지 리스트 조회
	 * @param LogMsgLogVO
	 * @return ProcessResultList
	 */
	public abstract ProcessResultListVO<LogMsgLogVO> listPageingForCRM(LogMsgLogVO vo);
}