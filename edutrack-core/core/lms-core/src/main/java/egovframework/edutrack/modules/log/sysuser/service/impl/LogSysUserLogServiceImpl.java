package egovframework.edutrack.modules.log.sysuser.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogService;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>로그 - 시스템 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logSysUserLogService")
public class LogSysUserLogServiceImpl
	extends EgovAbstractServiceImpl implements LogSysUserLogService {

	/** Mapper */
    @Resource(name="logSysUserLogMapper")
    private LogSysUserLogMapper 		logSysUserLogMapper;
    
    /**
     * 보기 유형에 따른 시스템 접속 현황을 가져온다.
     * @param vo
     * @return
     * @throws Exception
     */
	@Override
	public ProcessResultListVO<LogSysUserLogVO> listLog(LogSysUserLogVO vo) throws Exception {
		ProcessResultListVO<LogSysUserLogVO> resultList = new ProcessResultListVO<LogSysUserLogVO>();
		List<LogSysUserLogVO> logList;
		if("COUNTRY".equals(vo.getViewType())) {
			logList  = logSysUserLogMapper.listCountry(vo);
		} else if("SEX".equals(vo.getViewType())) {
			logList  = logSysUserLogMapper.listSex(vo);
		} else if("SNS".equals(vo.getViewType())){
			logList  = logSysUserLogMapper.listSns(vo);
		} else {
			logList  = logSysUserLogMapper.listAge(vo);
		}
		resultList.setResult(1);
		resultList.setReturnList(logList);
		return resultList;
	}

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public LogSysUserLogVO viewAutoDate(LogSysUserLogVO vo) throws Exception {
		return logSysUserLogMapper.selectAutoDate(vo);
	}

	/**
	 * 사용자 가입 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	@Override
	public void listExcelDownload(LogSysUserLogVO vo, OutputStream os) throws Exception {
		
		List<LogSysUserLogVO> resultList;
		if("COUNTRY".equals(vo.getViewType())) {
			resultList = logSysUserLogMapper.listCountry(vo);
		} else if("SEX".equals(vo.getViewType())) {
			resultList = logSysUserLogMapper.listSex(vo);
		} else if("SNS".equals(vo.getViewType())){
			resultList = logSysUserLogMapper.listSns(vo);
		} else {
			resultList = logSysUserLogMapper.listAge(vo);
		}

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("사용자 가입 통계", 0);
			if(!"".equals(vo.getStartDt())){
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","사용자 가입 통계"+vo.getStartDt()+"~"+vo.getEndDt())); //1열
			}else {
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","사용자 가입 통계")); //1열
			}
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, resultList.size(), 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,vo.getViewType()));
			sheet.setColumnView(0, 15);
			for(int i=0; i<resultList.size(); i++){

				LogSysUserLogVO iLogSysUserLogVO = resultList.get(i);

				sheet.addCell(ExcelUtil.createHeader(i+1,rowNum,"center",iLogSysUserLogVO.getCodeNm()));

				//-- 셀의 넓이 조절
				sheet.setColumnView(i, 8);
			}
			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속건수"));
			int userSum = 0;
			for(int i=0; i<resultList.size(); i++){

				LogSysUserLogVO iLogSysUserLogVO = resultList.get(i);
				sheet.addCell(ExcelUtil.createNumber(i+1,rowNum,"center",iLogSysUserLogVO.getUserCnt()));
				userSum = userSum + iLogSysUserLogVO.getUserCnt();
			}
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속율"));
			for(int i=0; i<resultList.size(); i++){

				LogSysUserLogVO lsllvo = resultList.get(i);

				float userRatio = 0;
				if(lsllvo.getUserCnt()> 0 && userSum > 0)
					userRatio = Float.valueOf(lsllvo.getUserCnt()) / Float.valueOf(userSum) * 100;
				String userRatioStr = String.format("%.2f", userRatio);
				sheet.addCell(ExcelUtil.createText(i+1,rowNum,"center",userRatioStr+"%"));
			}
			sheet.setRowView(rowNum, 300);

			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","사용자 가입 통계가 존재하지 않습니다.")); //1열
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
