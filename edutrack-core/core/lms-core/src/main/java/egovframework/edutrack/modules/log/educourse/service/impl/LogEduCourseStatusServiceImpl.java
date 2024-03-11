package egovframework.edutrack.modules.log.educourse.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusService;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduOrgStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduTeamStatusVO;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("logEduCourseStatusService")
public class LogEduCourseStatusServiceImpl 
	extends EgovAbstractServiceImpl implements LogEduCourseStatusService {

	/** mapper */
    @Resource(name="logEduCourseStatusMapper")
    private LogEduCourseStatusMapper 		logEduCourseStatusMapper;
    
	/**
	 * 팀별 /년도 월에 대한 과정 운영 현황 목록
	 * @param LogEduTeamStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogEduTeamStatusVO> listTeamStatus(LogEduTeamStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduTeamStatusVO> resultList = new ProcessResultListVO<LogEduTeamStatusVO>(); 
 		try {
 			List<LogEduTeamStatusVO> logList =  logEduCourseStatusMapper.listTeamStatus(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
 	}
	
	/**
	 * 팀별, 년,월별 개설과정 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listTeamStatus(LogEduCourseStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>(); 
 		try {
 			List<LogEduCourseStatusVO> logList =  logEduCourseStatusMapper.listTeamStatus(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
 	}
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> deptStatusList(LogEduCourseStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>(); 
 		try {
 			List<LogEduCourseStatusVO> logList =  logEduCourseStatusMapper.deptStatusList(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
		return resultList ;
	}
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listCourseStatus(LogEduCourseStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>(); 
 		try {
 			List<LogEduCourseStatusVO> logList =  logEduCourseStatusMapper.listCourseStatus(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
		return resultList ;
	}
	
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listCourseStatusPageing(LogEduCourseStatusVO VO, int pageIndex, int listScale, int pageScale)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>();
		try {
			// 전체 목록 수
			int totalCount = logEduCourseStatusMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogEduCourseStatusVO> returnList = logEduCourseStatusMapper.listCourseStatusPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * 과정 운영 현황(기업관리자) - 개설 과정 목록을 반환한다.
	 * (페이징 정보 포함)
	 * @param LogEduCourseStatusVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<LogEduCourseStatusVO>
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listCourseStatusDeptMngPageing(LogEduCourseStatusVO VO, int pageIndex, int listScale, int pageScale)  throws Exception {
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>();
		try {
			// 전체 목록 수
			int totalCount = logEduCourseStatusMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogEduCourseStatusVO> returnList = logEduCourseStatusMapper.listCourseStatusDeptMngPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	/**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listCourseResult(LogEduCourseStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>(); 
 		try {
 			List<LogEduCourseStatusVO> logList =  logEduCourseStatusMapper.listCourseResult(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
 	}
	
	/**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogEduCourseStatusVO> listCourseResultDash(LogEduCourseStatusVO vo) throws Exception {
 		ProcessResultListVO<LogEduCourseStatusVO> resultList = new ProcessResultListVO<LogEduCourseStatusVO>(); 
 		try {
 			List<LogEduCourseStatusVO> logList =  logEduCourseStatusMapper.listCourseResultDash(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
 	}
	
	/**
	 * 
	 * @param LogEduOrgStatusVO
	 * @return LogAdminConnLogVO
	 * @throws Exception
	 */
	@Override
	public LogEduOrgStatusVO viewResult(LogEduOrgStatusVO vo) throws Exception {
 		return logEduCourseStatusMapper.selectResult(vo);
 	}	
	
	/**
	 * 과정 운영 현황 다운로드
	 * @param os
	 * @throws ServiceProcessException
	 */
	@Override
	public void listExcelDownload(LogEduCourseStatusVO vo, String orgNm, ArrayList<String> titleList, OutputStream os)
			throws Exception {
		// TODO Auto-generated method stub

		List<LogEduCourseStatusVO> resultList = logEduCourseStatusMapper.listCourseStatus(vo);

		int colNum = 7;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(9), pageRow2, 0, colNum, "right");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell(titleList.get(1), titleRow, 0);
			POIExcelUtil.createTitleCell(titleList.get(2), titleRow, 1);
			POIExcelUtil.createTitleCell(titleList.get(3), titleRow, 2);
			POIExcelUtil.createTitleCell(titleList.get(4), titleRow, 3);
			POIExcelUtil.createTitleCell(titleList.get(5), titleRow, 4);
			POIExcelUtil.createTitleCell(titleList.get(6), titleRow, 5);
			POIExcelUtil.createTitleCell(titleList.get(7), titleRow, 6);
			POIExcelUtil.createTitleCell(titleList.get(8), titleRow, 7);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 400);

			int totEnrlCnt = 0;
			int totCpltCnt = 0;
			int totFailCnt = 0;
			double cpltPercent = 0.0;
			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				LogEduCourseStatusVO courseVO = resultList.get(i);
			/*	totEnrlCnt = totEnrlCnt + courseVO.getEnrlCnt();
				totCpltCnt = totCpltCnt + courseVO.getCpltCnt();
				totFailCnt = totFailCnt + courseVO.getFailCnt();*/
				if(courseVO.getEnrlCnt() != null && courseVO.getEnrlCnt() != 0) {
					cpltPercent = (double)(courseVO.getCpltCnt()) / courseVO.getEnrlCnt() * 100;
				}else {
					cpltPercent = 0.0;
				}
				POIExcelUtil.createContentCell(courseVO.getCrsNm(), row, 0, "center");
				POIExcelUtil.createContentCell(courseVO.getCrsCreNm(), row, 1, "center");
				POIExcelUtil.createContentCell(courseVO.getDeptNm(), row, 2, "center");
				if(courseVO.getEnrlStartDttm() != null &&courseVO.getEnrlStartDttm().length() > 10 && courseVO.getEnrlEndDttm() != null && courseVO.getEnrlEndDttm().length() > 10){
					String enrlDate = courseVO.getEnrlStartDttm().substring(0, 4)+"."+courseVO.getEnrlStartDttm().substring(4, 6)+"."+courseVO.getEnrlStartDttm().substring(6, 8)
							+"~"+courseVO.getEnrlEndDttm().substring(0, 4)+"."+courseVO.getEnrlEndDttm().substring(4, 6)+"."+courseVO.getEnrlEndDttm().substring(6, 8);
					POIExcelUtil.createContentCell(enrlDate, row, 3, "center");
				} else {
					String enrlDate = StringUtil.nvl(courseVO.getEnrlStartDttm())+"~"+StringUtil.nvl(courseVO.getEnrlEndDttm());
					POIExcelUtil.createContentCell(enrlDate, row, 3, "center");
				}
				POIExcelUtil.createContentCell(courseVO.getEnrlCnt(), row, 4, "right");
				POIExcelUtil.createContentCell(courseVO.getCpltCnt(), row, 5, "right");
				POIExcelUtil.createContentCell(courseVO.getFailCnt(), row, 6, "right");
				POIExcelUtil.createContentCell(cpltPercent, row, 7, "right");
			}
			rowNum++;
			XSSFRow totalRow = sheet.createRow((short)rowNum);
		/*	POIExcelUtil.createMergeCell(titleList.get(9), totalRow, 0, 4, "right");
			POIExcelUtil.createContentCell(totEnrlCnt, totalRow, 5, "right");
			POIExcelUtil.createContentCell(totCpltCnt, totalRow, 6, "right");
			POIExcelUtil.createContentCell(totFailCnt, totalRow, 7, "right");*/

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		} catch (Exception e) {
			throw new Exception("Failed! Create Excel", e);
		}

	}

	public void listStdResultExcelDownload(LogEduCourseStatusVO vo, ArrayList<String> titleList, OutputStream os) throws Exception {

		List<EduResultVO> resultList = logEduCourseStatusMapper.listStdEduResultExcel(vo);

		int colNum = 13;
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();
			
			wbook.setSheetName(0, titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(15), pageRow2, 0, colNum, "right");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell(titleList.get(1), titleRow, 0);
			POIExcelUtil.createTitleCell(titleList.get(2), titleRow, 1);
			POIExcelUtil.createTitleCell(titleList.get(3), titleRow, 2);
			POIExcelUtil.createTitleCell(titleList.get(4), titleRow, 3);
			POIExcelUtil.createTitleCell(titleList.get(5), titleRow, 4);
			POIExcelUtil.createTitleCell(titleList.get(6), titleRow, 5);
			POIExcelUtil.createTitleCell(titleList.get(7), titleRow, 6);
			POIExcelUtil.createTitleCell(titleList.get(8), titleRow, 7);
			POIExcelUtil.createTitleCell(titleList.get(9), titleRow, 8);
			POIExcelUtil.createTitleCell(titleList.get(10), titleRow, 9);
			POIExcelUtil.createTitleCell(titleList.get(11), titleRow, 10);
			POIExcelUtil.createTitleCell(titleList.get(12), titleRow, 11);
			POIExcelUtil.createTitleCell(titleList.get(13), titleRow, 12);
			POIExcelUtil.createTitleCell(titleList.get(14), titleRow, 13);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 800);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1200);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 500);
			
			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				EduResultVO resultVo = resultList.get(i);
				
				POIExcelUtil.createContentCell(i+1, row, 0, "center");
				POIExcelUtil.createContentCell(resultVo.getCrsNm(), row, 1, "center");
				POIExcelUtil.createContentCell(resultVo.getCrsCreNm(), row, 2, "center");
				POIExcelUtil.createContentCell(resultVo.getDeptNm(), row, 3, "center");
				POIExcelUtil.createContentCell(resultVo.getEnrlDuration(), row, 4, "center");
				POIExcelUtil.createContentCell(resultVo.getUserId(), row, 5, "center");
				POIExcelUtil.createContentCell(resultVo.getUserNm(), row, 6, "center");
				POIExcelUtil.createContentCell(resultVo.getDeclsNo().toString(), row, 7, "center");
				POIExcelUtil.createContentCell(Double.valueOf(resultVo.getPrgrRate())+"%", row, 8, "right");
				POIExcelUtil.createContentCell(resultVo.getExamScore(), row, 9, "right");
				POIExcelUtil.createContentCell(resultVo.getSemiExamScore(), row, 10, "right");
				POIExcelUtil.createContentCell(resultVo.getAsmtScore(), row, 11, "right");
				POIExcelUtil.createContentCell(resultVo.getTotScore(), row, 12, "right");
				POIExcelUtil.createContentCell(resultVo.getEnrlStsNm(), row, 13, "center");
			}
			rowNum++;
			XSSFRow totalRow = sheet.createRow((short)rowNum);

			if(resultList.size() == 0){
				rowNum++;
				XSSFRow pageRow3 = sheet.createRow((short)rowNum);
				POIExcelUtil.createMergeCell("수강생이 존재하지 않습니다.", pageRow3, 0, colNum, "right");
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		} catch (Exception e) {
			throw new Exception("Failed! Create Excel", e);
		}
	}
	

	/**
	 * 과정 총괄 현황 다운로드
	 * @param os
	 * @throws ServiceProcessException
	 */
	@Override
	public void listExcelDownloadResult(LogEduCourseStatusVO vo, String orgNm, ArrayList<String> titleList, OutputStream os)
			throws Exception {
		// TODO Auto-generated method stub

		List<LogEduCourseStatusVO> resultList = logEduCourseStatusMapper.listCourseResult(vo);

		int colNum = 11;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(orgNm+" "+titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(18), pageRow2, 0, colNum, "right");

			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 600);

			POIExcelUtil.createTitleCell(titleList.get(1), titleRow, 0);
			POIExcelUtil.createTitleCell(titleList.get(2), titleRow, 1);
			POIExcelUtil.createTitleCell(titleList.get(3), titleRow, 2);
			POIExcelUtil.createTitleCell(titleList.get(4), titleRow, 3);
			POIExcelUtil.createTitleCell(titleList.get(5), titleRow, 4);
			POIExcelUtil.createTitleCell(titleList.get(6), titleRow, 6);
			POIExcelUtil.createTitleCell(titleList.get(7), titleRow, 8);
			POIExcelUtil.createTitleCell(titleList.get(8), titleRow, 10);
			POIExcelUtil.createTitleCell(titleList.get(9), titleRow, 11);

			// 셀 병합 CellRangeAddress (row, row, cell, cell)
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 0, 0)); // 개설년도
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 1, 1)); // 과정명
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 2, 2)); // 교육방법
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 3, 3)); // 과정유형
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 4, 5)); // 계획
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 6, 7)); // 실적
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 8, 9)); // 진도(%)
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 10, 10)); // 년인원
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 11, 11)); // 수익금

			rowNum++;
			titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell(titleList.get(10), titleRow, 4);
			POIExcelUtil.createTitleCell(titleList.get(11), titleRow, 5);
			POIExcelUtil.createTitleCell(titleList.get(10), titleRow, 6);
			POIExcelUtil.createTitleCell(titleList.get(11), titleRow, 7);
			POIExcelUtil.createTitleCell(titleList.get(10), titleRow, 8);
			POIExcelUtil.createTitleCell(titleList.get(11), titleRow, 9);

			int totPlanCreCnt = 0;
			int totPlanEnrlCnt = 0;
			int totCreCnt = 0;
			int totEnrlCnt = 0;
			double totCreRatio = 0;
			double totEduRatio = 0;
			int totEduPrice = 0;
			String CrsOperMthd = "";
			String CrsOperType = "";

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				LogEduCourseStatusVO courseVO = resultList.get(i);

				totPlanCreCnt = totPlanCreCnt + courseVO.getPlanCreCnt();
				totPlanEnrlCnt = totPlanEnrlCnt + courseVO.getPlanEnrlCnt();
				totCreCnt = totCreCnt + courseVO.getCreCnt();
				totEnrlCnt = totEnrlCnt + courseVO.getEnrlCnt();
				totCreRatio = totCreRatio + courseVO.getCreRatio();
				totEduRatio = totEduRatio + courseVO.getEduRatio();
				totEnrlCnt = totEnrlCnt + courseVO.getEnrlCnt();
				totEduPrice = totEduPrice + courseVO.getEduPrice();

				if(courseVO.getCrsOperMthd().equals("ON")){
					CrsOperMthd = titleList.get(13);	//온라인
				} else if(courseVO.getCrsOperMthd().equals("OF")){
					CrsOperMthd = titleList.get(14);	//오프라인
				} else if(courseVO.getCrsOperMthd().equals("BL")) {
					CrsOperMthd = titleList.get(15);	//혼합과정
				}

				if(courseVO.getCrsOperType().equals("R")){
					CrsOperType = titleList.get(16);	//정규과정
				} else if(courseVO.getCrsOperType().equals("S")){
					CrsOperType = titleList.get(17);	//상시과정
				}

				POIExcelUtil.createContentCell(vo.getCreYear(), row, 0, "center");
				POIExcelUtil.createContentCell(courseVO.getCrsCreNm(), row, 1, "left");
				POIExcelUtil.createContentCell(CrsOperMthd, row, 2, "center");
				POIExcelUtil.createContentCell(CrsOperType, row, 3, "center");
				POIExcelUtil.createContentCell(courseVO.getPlanCreCnt(), row, 4, "right");
				POIExcelUtil.createContentCell(courseVO.getPlanEnrlCnt(), row, 5, "right");
				POIExcelUtil.createContentCell(courseVO.getCreCnt(), row, 6, "right");
				POIExcelUtil.createContentCell(courseVO.getEnrlCnt(), row, 7, "right");
				POIExcelUtil.createContentCell(courseVO.getCreRatio(), row, 8, "right");
				POIExcelUtil.createContentCell(courseVO.getEduRatio(), row, 9, "right");
				POIExcelUtil.createContentCell(courseVO.getEnrlCnt(), row, 10, "right");
				POIExcelUtil.createContentCell(courseVO.getEduPrice(), row, 11, "right");
			}

			if(totPlanCreCnt > 0){
				totCreRatio = (double)totCreCnt / (double)totPlanCreCnt * 100;
			} else {
				totCreRatio = 0;
			}

			if(totPlanEnrlCnt > 0){
				totEduRatio = (double)totEnrlCnt / (double)totPlanEnrlCnt * 100;
			} else {
				totEduRatio = 0;
			}

			rowNum++;
			XSSFRow totalRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(12), totalRow, 0, 3, "right");
			POIExcelUtil.createContentCell(totPlanCreCnt, totalRow, 4, "right");
			POIExcelUtil.createContentCell(totPlanEnrlCnt, totalRow, 5, "right");
			POIExcelUtil.createContentCell(totCreCnt, totalRow, 6, "right");
			POIExcelUtil.createContentCell(totEnrlCnt, totalRow, 7, "right");
			POIExcelUtil.createContentCell(Double.parseDouble(String.format("%.2f",totCreRatio)), totalRow, 8, "right");
			POIExcelUtil.createContentCell(Double.parseDouble(String.format("%.2f",totEduRatio)), totalRow, 9, "right");
			POIExcelUtil.createContentCell(totEnrlCnt, totalRow, 10, "right");
			POIExcelUtil.createContentCell(totEduPrice, totalRow, 11, "right");

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		} catch (Exception e) {
			throw new Exception("Excel 생성 실패", e);
		}

	}

	@Override
	public String getDeptCd(String userNo) throws Exception {
		return logEduCourseStatusMapper.selectDeptCd(userNo);
	}
}
