package egovframework.edutrack.modules.log.login.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>로그 - 시스템 로그인 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logSysLoginLogService")
public class LogSysLoginLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogSysLoginLogService{

	/** dao */
    @Resource(name="logSysLoginLogMapper")
    private LogSysLoginLogMapper 		logSysLoginLogMapper;
    
    /**
     * 보기 유형에 따른 시스템 로그인 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysLoginLogVO> listLog(LogSysLoginLogVO vo) throws Exception {
		ProcessResultListVO<LogSysLoginLogVO> resultList = new ProcessResultListVO<LogSysLoginLogVO>();
		List<LogSysLoginLogVO> logList;
		if("MONTH".equals(vo.getViewType())) {
			logList  = logSysLoginLogMapper.listMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			logList  = logSysLoginLogMapper.listDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			logList  = logSysLoginLogMapper.listWeek(vo);
		} else {
			logList  = logSysLoginLogMapper.listHour(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}
	
	/**
     * 보기 유형에 따른 모든 시스템 로그인 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysLoginLogVO> AllListLog(LogSysLoginLogVO vo) throws Exception {
		ProcessResultListVO<LogSysLoginLogVO> resultList = new ProcessResultListVO<LogSysLoginLogVO>();
		List<LogSysLoginLogVO> logList;
		if("MONTH".equals(vo.getViewType())) {
			logList  = logSysLoginLogMapper.AllListMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			logList  = logSysLoginLogMapper.AllListDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			logList  = logSysLoginLogMapper.AllListWeek(vo);
		} else {
			logList  = logSysLoginLogMapper.AllListHour(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}

	/**
	 * 일별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysLoginLogVO> listLogByDay(LogSysLoginLogVO vo) throws Exception{
		ProcessResultListVO<LogSysLoginLogVO> resultList = new ProcessResultListVO<LogSysLoginLogVO>();
		List<LogSysLoginLogVO> logList  = logSysLoginLogMapper.listHourByDay(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 주별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysLoginLogVO> listLogByWeek(LogSysLoginLogVO vo) throws Exception{
		ProcessResultListVO<LogSysLoginLogVO> resultList = new ProcessResultListVO<LogSysLoginLogVO>();
		List<LogSysLoginLogVO> logList  = logSysLoginLogMapper.listDayByWeek(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 월별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysLoginLogVO> listLogByMonth(LogSysLoginLogVO vo) throws Exception{
		ProcessResultListVO<LogSysLoginLogVO> resultList = new ProcessResultListVO<LogSysLoginLogVO>();
		List<LogSysLoginLogVO> logList  = logSysLoginLogMapper.listDayByMonth(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 시스템 로그인 로그 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public int add(LogSysLoginLogVO vo) throws Exception {
		int result = logSysLoginLogMapper.insert(vo);
		return result;
	}

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public LogSysLoginLogVO viewAutoDate(LogSysLoginLogVO vo) throws Exception {
		return logSysLoginLogMapper.selectAutoDate(vo);
	}

	/**
	 * 홈페이지 로그인 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	@Override
	public void listExcelDownload(LogSysLoginLogVO vo, OutputStream os) throws Exception {
		
		List<LogSysLoginLogVO> resultList;
		if("MONTH".equals(vo.getViewType())) {
			resultList = logSysLoginLogMapper.listMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			resultList = logSysLoginLogMapper.listDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			resultList = logSysLoginLogMapper.listWeek(vo);
		} else {
			resultList = logSysLoginLogMapper.listHour(vo);
		}

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("홈페이지 로그인 통계", 0);
			if(!"".equals(vo.getStartDt())){
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 로그인 통계"+vo.getStartDt()+"~"+vo.getEndDt())); //1열
			} else {
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 로그인 통계")); //1열
			}
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, resultList.size(), 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,vo.getViewType()));
			sheet.setColumnView(0, 15);
			for(int i=0; i<resultList.size(); i++){

				LogSysLoginLogVO iLogSysLoginLogVO = resultList.get(i);

				sheet.addCell(ExcelUtil.createHeader(i+1,rowNum,iLogSysLoginLogVO.getCodeNm()));

				//-- 셀의 넓이 조절
				sheet.setColumnView(i, 8);
			}
			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 400);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속건수"));
			int connectSum = 0;
			for(int i=0; i<resultList.size(); i++){

				LogSysLoginLogVO iLogSysLoginLogVO = resultList.get(i);
				sheet.addCell(ExcelUtil.createNumber(i+1,rowNum,"center",iLogSysLoginLogVO.getLoginCnt()));
				connectSum = connectSum + iLogSysLoginLogVO.getLoginCnt();
			}
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속율"));
			for(int i=0; i<resultList.size(); i++){

				LogSysLoginLogVO lsllvo = resultList.get(i);

				float connectRatio = 0;
				if(lsllvo.getLoginCnt()> 0 && connectSum > 0)
					connectRatio = Float.valueOf(lsllvo.getLoginCnt()) / Float.valueOf(connectSum) * 100;
				String connectRatioStr = String.format("%.2f", connectRatio);
				sheet.addCell(ExcelUtil.createText(i+1,rowNum,"center",connectRatioStr+"%"));
			}
			sheet.setRowView(rowNum, 300);

			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","홈페이지 로그인 통계가 존재하지 않습니다.")); //1열
				sheet.mergeCells(0, 2, 7, 2);
				sheet.setRowView(0, 500);
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new Exception("Excel 생성 실패", e);
		}
	}
}
