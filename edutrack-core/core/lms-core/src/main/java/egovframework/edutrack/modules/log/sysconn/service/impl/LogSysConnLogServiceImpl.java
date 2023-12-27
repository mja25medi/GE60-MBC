package egovframework.edutrack.modules.log.sysconn.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogService;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>로그 - 시스템 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logSysConnLogService")
public class LogSysConnLogServiceImpl
	extends EgovAbstractServiceImpl implements LogSysConnLogService {

	/** Mapper */
    @Resource(name="logSysConnLogMapper")
    private LogSysConnLogMapper 		logSysConnLogMapper;
    
    /**
     * 보기 유형에 따른 시스템 접속 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> listLog(LogSysConnLogVO vo) throws Exception {
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList;
		if("MONTH".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.listMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.listDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			logList  = logSysConnLogMapper.listWeek(vo);
		} else {
			logList  = logSysConnLogMapper.listHour(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}
	
	/**
     * 보기 유형에 따른 시스템 모든 접속 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> AllListLog(LogSysConnLogVO vo) throws Exception {
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList;
		if("MONTH".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.AllListMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.AllListDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			logList  = logSysConnLogMapper.AllListWeek(vo);
		} else {
			logList  = logSysConnLogMapper.AllListHour(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}
	
	/**
     * 보기 유형에 따른 시스템 모든 접속 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> sysListLog(LogSysConnLogVO vo) throws Exception {
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList;
		if("MONTH".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.sysListMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			logList  = logSysConnLogMapper.sysListDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			logList  = logSysConnLogMapper.sysListWeek(vo);
		} else {
			logList  = logSysConnLogMapper.sysListHour(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}

	/**
	 * 일별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> listLogByDay(LogSysConnLogVO vo) throws Exception{
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList  = logSysConnLogMapper.listHourByDay(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 주별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> listLogByWeek(LogSysConnLogVO vo) throws Exception{
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList  = logSysConnLogMapper.listDayByWeek(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 월별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogSysConnLogVO> listLogByMonth(LogSysConnLogVO vo) throws Exception{
		ProcessResultListVO<LogSysConnLogVO> resultList = new ProcessResultListVO<LogSysConnLogVO>();
		List<LogSysConnLogVO> logList  = logSysConnLogMapper.listDayByMonth(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}

	/**
	 * 시스템 접속 로그 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public String add(LogSysConnLogVO vo) throws Exception {
		String result = Integer.toString(logSysConnLogMapper.insert(vo));
		return result;
	}

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public LogSysConnLogVO viewAutoDate(LogSysConnLogVO vo) throws Exception {
		return logSysConnLogMapper.selectAutoDate(vo);
	}

	/**
	 * 홈페이지 접속 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	@Override
	public void listExcelDownload(LogSysConnLogVO vo, OutputStream os) throws Exception {
		
		List<LogSysConnLogVO> resultList;
		if("MONTH".equals(vo.getViewType())) {
			resultList = logSysConnLogMapper.listMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			resultList = logSysConnLogMapper.listDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			resultList = logSysConnLogMapper.listWeek(vo);
		} else {
			resultList = logSysConnLogMapper.listHour(vo);
		}

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("홈페이지 접속 통계", 0);
			if(!"".equals(vo.getStartDt())){
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 접속 통계"+vo.getStartDt()+"~"+vo.getEndDt())); //1열
			}else {
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 접속 통계")); //1열
			}
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, resultList.size(), 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,vo.getViewType()));
			sheet.setColumnView(0, 15);
			for(int i=0; i<resultList.size(); i++){

				LogSysConnLogVO iLogSysConnLogVO = resultList.get(i);

				sheet.addCell(ExcelUtil.createHeader(i+1,rowNum,"center",iLogSysConnLogVO.getCodeNm()));

				//-- 셀의 넓이 조절
				sheet.setColumnView(i, 8);
			}
			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속건수"));
			int connectSum = 0;
			for(int i=0; i<resultList.size(); i++){

				LogSysConnLogVO iLogSysConnLogVO = resultList.get(i);
				sheet.addCell(ExcelUtil.createNumber(i+1,rowNum,"center",iLogSysConnLogVO.getConnCnt()));
				connectSum = connectSum + iLogSysConnLogVO.getConnCnt();
			}
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속율"));
			for(int i=0; i<resultList.size(); i++){

				LogSysConnLogVO lsllvo = resultList.get(i);

				float connectRatio = 0;
				if(lsllvo.getConnCnt()> 0 && connectSum > 0)
					connectRatio = Float.valueOf(lsllvo.getConnCnt()) / Float.valueOf(connectSum) * 100;
				String connectRatioStr = String.format("%.2f", connectRatio);
				sheet.addCell(ExcelUtil.createText(i+1,rowNum,"center",connectRatioStr+"%"));
			}
			sheet.setRowView(rowNum, 300);

			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","홈페이지 접속 통계가 존재하지 않습니다.")); //1열
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
