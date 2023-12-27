package egovframework.edutrack.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;

/**
 * 메시지와 SMS 테이블을 조회해서 발신대기중인 메시지들을 전송 처리하고 결과를 기록한다.
 *
 * @author SungKook
 */
@Component
public class MessageBatch extends GenericController {

	private static final String	SYS_CONFIG_CATEGORY	= "BATCH";
	private static final String	SYS_CONFIG_CODE		= "D2MSG_STS";

	private static final String	BATCH_STATUS_UP		= "UP";
	private static final String	BATCH_STATUS_DOWN	= "DN";

	@Autowired
	private SysCfgService		configService;
	
	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService			logMsgLogService;
	
	// 종료후 2분마다 실행 (1000ms * 60 * 2 )
	//@Scheduled(fixedDelay=1200000)
	//@Scheduled(fixedDelay=600000)
	@Scheduled(fixedDelay=120000)
	public synchronized void attendProcess() {

		// 배치 프로세서의 가동상태를 표한하는 Config개체를 설정
		SysCfgVO configVO = new SysCfgVO();
		configVO.setCfgCtgrCd(SYS_CONFIG_CATEGORY);
		configVO.setCfgCd(SYS_CONFIG_CODE);
		System.out.println("배치..... 작업 수행 중.........");
		try {
			configVO = configService.viewCfg(configVO);
		} catch (Exception ex) {
			log.error("배치 데몬 실행 중 오류가 발생했습니다.", ex);
		}
		

		// 배치가 휴면상태가 아닐 경우 실행을 취소한다.
		if(isOthserSystemBatchRunning(configVO)) {
			log.info("다른 시스템" + configVO.getCfgVal() + "에서 배치가 가동중임을 감지하고 배치 실행을 취소합니다.");
		} else {
			try {
				log.info("메시지 전송프로세스를 시작합니다.");
				// 배치가 가동중임을 기록한다.
				semaphoreSetsUp(configVO);
				logMsgLogService.sendMessageDivCd("EMAIL");
				if(Constants.MSG_SMS.equals("Y")) {
					logMsgLogService.sendMessageDivCd("SMS");
				}
			} catch (Exception ex) {
				log.error("배치 메시지 전송중 오류가 발생했습니다.", ex);
			} finally {
				log.info("메시지 전송프로세스를 종료합니다.");
				// 휴면상태로 설정
				semaphoreSetsDown(configVO);
			}
		}

	}

	/**
	 * 다른 시스템에서 배치가 가동중이면 true 아니면 false.
	 * @param configDTO
	 * @return
	 */
	private boolean isOthserSystemBatchRunning(SysCfgVO configVO) {
		String value = configVO.getCfgVal();

		// 값이 없거나 첫글자가 DOWN이 아니면 무조건 가동중으로 판단
		if(value == null || value.indexOf(BATCH_STATUS_DOWN) != 0) return true;

		return false;
	}

	private void semaphoreSetsDown(SysCfgVO configVO) {
		configVO.setCfgVal(BATCH_STATUS_DOWN + getIdentityString());
		try {
			configService.editCfg(configVO);
		} catch (Exception ex) {
			log.error("배치 데몬 실행 중 오류가 발생했습니다.", ex);
		}
		
	}

	private void semaphoreSetsUp(SysCfgVO configVO) {
		configVO.setCfgVal(BATCH_STATUS_UP + getIdentityString());
		try {
			configService.editCfg(configVO);
		} catch (Exception ex) {
			log.error("배치 데몬 실행 중 오류가 발생했습니다.", ex);
		}

	}

	private String getIdentityString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		return "_" + Constants.HOST_NAME + "_" + formatter.format(new Date());
	}
	
}
