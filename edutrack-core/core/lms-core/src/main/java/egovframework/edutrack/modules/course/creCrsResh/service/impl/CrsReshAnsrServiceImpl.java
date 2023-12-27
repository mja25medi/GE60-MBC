package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.impl.ResearchBankItemMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("crsReshAnsrService")
public class CrsReshAnsrServiceImpl
	extends EgovAbstractServiceImpl implements CrsReshAnsrService {

	/** Mapper */
	@Resource(name="crsReshAnsrMapper")
	private CrsReshAnsrMapper 		crsReshAnsrMapper;

	@Resource(name="crsReshResultMapper")
	private CrsReshResultMapper 		crsReshResultMapper;

	@Resource(name="researchBankItemMapper")
	private ResearchBankItemMapper 	researchBankItemMapper;

	@Resource(name="studentMapper")
	private StudentMapper 			studentMapper;

	/**
	 * 개설 과정 설문 응답 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> list(CrsReshAnsrVO VO) throws Exception {
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		try {
			List<CrsReshAnsrVO> returnList =  crsReshAnsrMapper.list(VO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> listPageing(CrsReshAnsrVO VO,
			int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());	
		
		// 전체 목록 수
		int totalCount = crsReshAnsrMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsReshAnsrMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> listPageing(CrsReshAnsrVO VO,
			int curPage, int listScale) throws Exception {
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		

		// 전체 목록 수
		int totalCount = crsReshAnsrMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsReshAnsrMapper.listPageing(VO));  
		resultList.setPageInfo(paginationInfo);
		return resultList;
	}

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> listPageing(CrsReshAnsrVO VO,
			int curPage) throws Exception {
		
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());	
		
		// 전체 목록 수
		int totalCount = crsReshAnsrMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsReshAnsrMapper.listPageing(VO));
		resultList.setPageInfo(paginationInfo);
		return resultList;
	}

	/**
	 * 개설 과정 설문 응답 정보 조회
	 */
	@Override
	public	ProcessResultVO<CrsReshAnsrVO> view(CrsReshAnsrVO VO) throws Exception {
		
		ProcessResultVO<CrsReshAnsrVO> resultVO = new ProcessResultVO<CrsReshAnsrVO>();
		try {
			CrsReshAnsrVO returnVO = crsReshAnsrMapper.select(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;

	}

	/**
	 * 개설 과정 설문 정보 등록
	 */
	@Override
	public	ProcessResultVO<CrsReshAnsrVO> add(CrsReshAnsrVO VO) throws Exception {
		String[] ansrArr = StringUtil.split(VO.getReshAnsrStr(), "|");
		for(int i=1; i<ansrArr.length; i++) {
			String[] ansrStr = StringUtil.split(ansrArr[i],"/");
			CrsReshAnsrVO iVO = new CrsReshAnsrVO();
			iVO.setCrsCreCd(VO.getCrsCreCd());
			iVO.setReshSn(VO.getReshSn());
			iVO.setStdNo(VO.getStdNo());
			iVO.setRegNo(VO.getRegNo());
			iVO.setModNo(VO.getModNo());
			iVO.setReshItemSn(Integer.parseInt(ansrStr[0]));
			if("K".equals(ansrStr[3])) {
				iVO.setReshAnsr(ansrStr[1]);
			} else {
				iVO.setReshAnsr(ansrStr[2]);
			}
			crsReshAnsrMapper.insert(iVO);
		}
		return new ProcessResultVO().setResultSuccess();
	}

	/**
	 * 개설 과정 설문 응답 정보 수정
	 */
	@Override
	public	ProcessResultVO<CrsReshAnsrVO> edit(CrsReshAnsrVO VO) throws Exception {
		ProcessResultVO<CrsReshAnsrVO> resultVO = new ProcessResultVO<CrsReshAnsrVO>();
		try {
			crsReshAnsrMapper.update(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 개설 과정 설문 응답 정보 삭제
	 */
	@Override
	public	ProcessResultVO<CrsReshAnsrVO> remove(CrsReshAnsrVO VO) throws Exception {
		
		ProcessResultVO<CrsReshAnsrVO> resultVO = new ProcessResultVO<CrsReshAnsrVO>();
		try {
			crsReshAnsrMapper.delete(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;

	}

	/**
	 * 개설 과정 설문 의견 응답 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> listOpinion(CrsReshAnsrVO VO) throws Exception {
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		try {
			List<CrsReshAnsrVO> returnList =  crsReshAnsrMapper.listOpinion(VO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}

	/**
	 * 개설 과정 설문 응답 목록 조회(설문번호 없이)
	 */
	@Override
	public	ProcessResultListVO<CrsReshAnsrVO> list_noReshSn(CrsReshAnsrVO VO) throws Exception {
		ProcessResultListVO<CrsReshAnsrVO> resultList = new ProcessResultListVO<CrsReshAnsrVO>();
		try {
			List<CrsReshAnsrVO> returnList =  crsReshAnsrMapper.listNoReshSn(VO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}


	/**
	 * 개설 과정 설문 응답 건수
	 */
	@Override
	public	ProcessResultVO<CrsReshAnsrVO> listCount(CrsReshAnsrVO VO) throws Exception {
		
		ProcessResultVO<CrsReshAnsrVO> resultVO = new ProcessResultVO<CrsReshAnsrVO>();
		try {
			CrsReshAnsrVO returnVO = crsReshAnsrMapper.listCount(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;

	}

	/**
	 * 개설과정관리/설문결과 다운로드
	 * @return  ProcessResultVO
	 */
	@Override
	public void listExcelDownload(CreCrsReshVO creCrsReshVO, String sheetName, String labelName,
			String condition, String excelHeader, HashMap<String, String> titleMap, HttpServletRequest request, ServletOutputStream os) throws ServiceProcessException {

		// 문제 목록을 가져온다.
		ResearchBankItemVO rbiVO = new ResearchBankItemVO();
		rbiVO.setReshSn(creCrsReshVO.getReshSn());
		List<ResearchBankItemVO> qstnList = researchBankItemMapper.list(rbiVO);

		List<StudentVO> stdList = null;
		List<CrsReshAnsrVO> resultList = null;
		try {
			// 수강생의 목록을 가져온다.
			StudentVO sVO = new StudentVO();
			sVO.setCrsCreCd(creCrsReshVO.getCrsCreCd());
			sVO.setEnrlSts("|S|C|F|");
			stdList = studentMapper.listStudent(sVO);
			
			
			//-- 학습자의 답안 목록을 가져온다.
			CrsReshAnsrVO craVO = new CrsReshAnsrVO();
			craVO.setCrsCreCd(creCrsReshVO.getCrsCreCd());
			craVO.setReshSn(creCrsReshVO.getReshSn());
			resultList = crsReshAnsrMapper.listExcel(craVO);

		} catch(Exception ex) {
			ex.printStackTrace();
		}

		int itemCnt = creCrsReshVO.getItemCnt(); // 문항수
		int margeCell = itemCnt + 2;

		String locale = UserBroker.getLocaleKey(request);

		//-- 설문기간
		String startDttm = creCrsReshVO.getStartDttm().substring(0,4)+"."+creCrsReshVO.getStartDttm().substring(4,6)+"."+creCrsReshVO.getStartDttm().substring(6,8);
        String endDttm = creCrsReshVO.getEndDttm().substring(0,4)+"."+creCrsReshVO.getEndDttm().substring(4,6)+"."+creCrsReshVO.getEndDttm().substring(6,8);


		try {

			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, sheetName);

			// 페이지 제목줄
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(labelName, pageRow1, 0, margeCell);

			//-- 과정명
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleMap.get("course_name")+" : "+creCrsReshVO.getCrsCreNm(), pageRow2, 0, margeCell, "left");

			//-- 설문명
			rowNum++;
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleMap.get("resh_title")+" : "+creCrsReshVO.getReshTitle(), pageRow3, 0, margeCell, "left");

			rowNum++;
			XSSFRow pageRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleMap.get("duration")+" : "+startDttm+"~"+endDttm, pageRow4, 0, margeCell, "left");

			rowNum++;
			XSSFRow pageRow5 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(condition, pageRow5, 0, margeCell, "right");

			//-- header를 만든다.
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell(titleMap.get("no"), titleRow, 0);
			POIExcelUtil.createTitleCell(titleMap.get("user_id"), titleRow, 1);
			POIExcelUtil.createTitleCell(titleMap.get("user_name"), titleRow, 2);
			for(int i=1; i <= itemCnt; i++) {
				POIExcelUtil.createTitleCell(titleMap.get("question")+i, titleRow, i+2);
			}

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
			for(int i=1; i <= itemCnt; i++) {
				sheet.setColumnWidth(i+2, sheet.getDefaultColumnWidth() * 800);
			}

			if(stdList.size() == 0) {
				rowNum++;
				XSSFRow pageRow6 = sheet.createRow((short)rowNum);
				POIExcelUtil.createMergeCell("* There is no data.", pageRow6, 0, margeCell, "left");
			}

			int lineNum = 0;
			for(StudentVO stdVO : stdList) {
				rowNum++;
				lineNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				POIExcelUtil.createContentCell(lineNum, row, 0, "center");
				POIExcelUtil.createContentCell(stdVO.getUserId(), row, 1, "center");
				POIExcelUtil.createContentCell(stdVO.getUserNm(), row, 2, "center");
				int cellNum = 2;
				for(ResearchBankItemVO qstn : qstnList) {
					cellNum++;
					String ansrStr = "";
					for(CrsReshAnsrVO ansr : resultList) {
						if(stdVO.getStdNo().equals(ansr.getStdNo()) && qstn.getReshSn() == ansr.getReshSn() && qstn.getReshItemSn() == ansr.getReshItemSn()) {
							ansrStr = ansr.getReshAnsr();
						}
					}
					POIExcelUtil.createContentCell(ansrStr, row, cellNum, "center");
				}
			}

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		}catch (Exception e) {
			throw new ServiceProcessException("Excel file creation failed!", e);
		}

	}
}
