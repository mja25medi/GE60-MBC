package egovframework.edutrack.modules.log.connect.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.log.connect.service.ConnectLogService;
import egovframework.edutrack.modules.log.connect.service.ConnectLogVO;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.edutrack.modules.log.exception.service.impl.LogExceptionMapper;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service(value = "connectLogService")
public class ConnectLogServiceImpl extends EgovAbstractServiceImpl  implements ConnectLogService {
	/** Mapper */
    @Resource(name="connectLogMapper")
    private ConnectLogMapper 		connectLogMapper;

	public	ProcessResultListVO<ConnectLogVO> listLog(ConnectLogVO vo){
		if("MONTH".equals(vo.getViewType())) {
			return connectLogMapper.listMonth(vo);
		} else if("DAY".equals(vo.getViewType())) {
			return connectLogMapper.listDay(vo);
		} else if("WEEK".equals(vo.getViewType())){
			return connectLogMapper.listWeek(vo);
		} else {
			return connectLogMapper.listHour(vo);
		}
	}

	public	ProcessResultListVO<ConnectLogVO> listLogByDay(ConnectLogVO vo){
		return connectLogMapper.listHourByDay(vo);
	}

	public	ProcessResultListVO<ConnectLogVO> listLogByWeek(ConnectLogVO vo){
		return connectLogMapper.listDayByWeek(vo);
	}

	public	ProcessResultListVO<ConnectLogVO> listLogByMonth(ConnectLogVO vo){
		return connectLogMapper.listDayByMonth(vo);
	}

	public	ProcessResultVO<ConnectLogVO> addConnectLog(ConnectLogVO vo)  {
		ProcessResultVO<ConnectLogVO> result = null;
		try {
			result = connectLogMapper.merge(vo);
			//result = connectLogVO.insert(vo);
		} catch (DuplicateKeyException ex) {
			result = connectLogMapper.update(vo);
		}
		return result;
		//return connectLogVO.merge();
	}

	public	ProcessResultVO<ConnectLogVO> viewAutoDate(ConnectLogVO vo) {
		return connectLogMapper.selectAutoDate(vo);
	}

	/**
	 * 홈페이지 접속 통계 엑셀다운로드
	 *
	 * @return  ProcessResultVO
	 */
	public void listExcelDownload(ConnectLogVO ConnectLogVO, OutputStream os) throws ServiceProcessException {

		List<ConnectLogVO> resultList;
		if("MONTH".equals(ConnectLogVO.getViewType())) {
			resultList = connectLogMapper.listMonth(ConnectLogVO).getReturnList();
		} else if("DAY".equals(ConnectLogVO.getViewType())) {
			resultList = connectLogMapper.listDay(ConnectLogVO).getReturnList();
		} else if("WEEK".equals(ConnectLogVO.getViewType())){
			resultList = connectLogMapper.listWeek(ConnectLogVO).getReturnList();
		} else {
			resultList = connectLogMapper.listHour(ConnectLogVO).getReturnList();
		}

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("홈페이지 접속 통계", 0);
			if(!"".equals(ConnectLogVO.getStartDt())){
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 접속 통계"+ConnectLogVO.getStartDt()+"~"+ConnectLogVO.getEndDt())); //1열
			}else {
				sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center","홈페이지 접속 통계")); //1열
			}
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, resultList.size(), 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,ConnectLogVO.getViewType()));
			sheet.setColumnView(0, 15);
			for(int i=0; i<resultList.size(); i++){

				ConnectLogVO vo = resultList.get(i);

				sheet.addCell(ExcelUtil.createHeader(i+1,rowNum,"center",vo.getCodeNm()));

				//-- 셀의 넓이 조절
				sheet.setColumnView(i, 8);
			}
			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속건수"));
			int connectSum = 0;
			for(int i=0; i<resultList.size(); i++){

				ConnectLogVO vo = resultList.get(i);
				sheet.addCell(ExcelUtil.createNumber(i+1,rowNum,"center",vo.getConnCnt()));
				connectSum = connectSum + vo.getConnCnt();
			}
			sheet.setRowView(rowNum, 300);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"center","접속율"));
			for(int i=0; i<resultList.size(); i++){

				ConnectLogVO vo = resultList.get(i);

				float connectRatio = 0;
				if(vo.getConnCnt()> 0 && connectSum > 0)
					connectRatio = Float.valueOf(vo.getConnCnt()) / Float.valueOf(connectSum) * 100;
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
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}
}