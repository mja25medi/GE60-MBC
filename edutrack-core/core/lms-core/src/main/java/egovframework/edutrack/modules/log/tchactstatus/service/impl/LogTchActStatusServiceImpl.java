package egovframework.edutrack.modules.log.tchactstatus.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusDetailVO;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusService;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("logTchActStatusService")
public class LogTchActStatusServiceImpl
		extends EgovAbstractServiceImpl implements LogTchActStatusService {

	@Resource(name="logTchActStatusMapper")
    private LogTchActStatusMapper logTchActStatusMapper;	
	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	@Override
	public ProcessResultListVO<LogTchActStatusVO> list(LogTchActStatusVO vo) throws Exception {

		ProcessResultListVO<LogTchActStatusVO> resultList = new ProcessResultListVO<LogTchActStatusVO>();
		try {
			List<LogTchActStatusVO> returnList = logTchActStatusMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;

	}

	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	@Override
	public ProcessResultListVO<LogTchActStatusVO> listPageing(LogTchActStatusVO vo) throws Exception {
		ProcessResultListVO<LogTchActStatusVO> resultList = new ProcessResultListVO<LogTchActStatusVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logTchActStatusMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogTchActStatusVO> returnList = logTchActStatusMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	@Override
	public ProcessResultListVO<LogTchActStatusDetailVO> listCourse(LogTchActStatusDetailVO vo) throws Exception {
		ProcessResultListVO<LogTchActStatusDetailVO> resultList = new ProcessResultListVO<LogTchActStatusDetailVO>();
		try {
			List<LogTchActStatusDetailVO> returnList = logTchActStatusMapper.listCourse(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;

	}
	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	@Override
	public ProcessResultVO<LogTchActStatusVO> view(LogTchActStatusVO vo) throws Exception {

		ProcessResultVO<LogTchActStatusVO> resultVO = new ProcessResultVO<LogTchActStatusVO>();
		try {
			LogTchActStatusVO returnVO = logTchActStatusMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;

	}

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	@Override
	public ProcessResultListVO<LogTchActStatusDetailVO> listCoursePageing(LogTchActStatusDetailVO vo) throws Exception {
		ProcessResultListVO<LogTchActStatusDetailVO> resultList = new ProcessResultListVO<LogTchActStatusDetailVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logTchActStatusMapper.courseCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogTchActStatusDetailVO> returnList = logTchActStatusMapper.listCoursePageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param TchActStatusDTO
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	@Override
	public ProcessResultListVO<LogTchActStatusDetailVO> listCourseStatistics(LogTchActStatusDetailVO vo) {
		ProcessResultListVO<LogTchActStatusDetailVO> resultList = new ProcessResultListVO<LogTchActStatusDetailVO>();
		try {
			List<LogTchActStatusDetailVO> returnList = null;

			if("MONTH".equals(vo.getViewType())) {
				returnList = logTchActStatusMapper.listMonth(vo);
			} else if("DAY".equals(vo.getViewType())) {
				returnList = logTchActStatusMapper.listDay(vo);
			} else if("WEEK".equals(vo.getViewType())){
				returnList = logTchActStatusMapper.listWeek(vo);
			} else{
				returnList = logTchActStatusMapper.listDay(vo);
			}
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		
		return resultList;

	}
	/**
	 * 강사 활동내역 엑셀 다운로드
	 * @param os
	 * @throws ServiceProcessException
	 */
	public void listExcelDownload(LogTchActStatusVO statusDTO, String orgNm, ArrayList<String> titleList,	OutputStream os) throws Exception {

		List<LogTchActStatusVO> resultList = logTchActStatusMapper.list(statusDTO);

		int colNum = 7;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(orgNm+" "+titleList.get(0), pageRow1, 0, colNum);

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
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 400);

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				LogTchActStatusVO dto =  resultList.get(i);

				POIExcelUtil.createContentCell((i+1), row, 0, "center");
				POIExcelUtil.createContentCell(dto.getUserNm(), row, 1, "center");
				POIExcelUtil.createContentCell(dto.getUserId(), row, 2, "center");
				POIExcelUtil.createContentCell(dto.getEmail(), row, 3, "center");
				POIExcelUtil.createContentCell(dto.getMobileNo(), row, 4, "center");
				POIExcelUtil.createContentCell(dto.getEndCrsCnt(), row, 5, "center");
				POIExcelUtil.createContentCell(dto.getIngCrsCnt(), row, 6, "center");
				POIExcelUtil.createContentCell(dto.getBefCrsCnt(), row, 7, "center");
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
			throw new Exception("Excel 생성 실패", e);
		}

	}

}
