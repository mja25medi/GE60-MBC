package egovframework.edutrack.modules.course.research.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

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
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankService;
import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("researchBankService")
public class ResearchBankServiceImpl
	extends EgovAbstractServiceImpl implements ResearchBankService {

	private final class NestedFileHandler
		implements FileHandler<ResearchBankItemVO> {

		@Override
		public String getPK(ResearchBankItemVO VO) {
			return VO.getReshItemSn()+"";
		}

		@Override
		public String getRepoCd() {
			return "RESH_BANK";
		}

		@Override
		public List<SysFileVO> getFiles(ResearchBankItemVO VO) {
			List<SysFileVO> fileList = VO.getAttachImages();
			fileList.addAll(VO.getAttachFiles());
			return fileList;
		}

		@Override
		public ResearchBankItemVO setFiles(ResearchBankItemVO VO, FileListVO fileListVO) {
			VO.setAttachImages(fileListVO.getFiles("image"));
			VO.setAttachFiles(fileListVO.getFiles("file"));
			return VO;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/** Mapper */
	@Resource(name="researchBankMapper")
	private ResearchBankMapper researchBankMapper;

	@Resource(name="researchBankItemMapper")
	private ResearchBankItemMapper researchBankItemMapper;

	/**
	 * 설문 목록 조회
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> listResearch(ResearchBankVO VO) throws Exception {
		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		try {
			List<ResearchBankVO> returnList = researchBankMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO VO, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = researchBankMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ResearchBankVO> returnList = researchBankMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO VO, int curPage, int listScale) throws Exception {
		
		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		resultList.setReturnList(researchBankMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;

	}

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO VO, int curPage) throws Exception {
		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());

		resultList.setReturnList(researchBankMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}

	/**
	 * 설문 목록 조회(설문명 검색)
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> searchListResearch(ResearchBankVO VO) throws Exception {
		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		try {
			List<ResearchBankVO> returnList = researchBankMapper.searchList(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 설문 정보 조회
	 */
	@Override
	public	ProcessResultVO<ResearchBankVO> viewResearch(ResearchBankVO VO) throws Exception {
		ProcessResultVO<ResearchBankVO> resultVO = new ProcessResultVO<ResearchBankVO>();
		try {
			ResearchBankVO returnVO = researchBankMapper.select(VO);
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
	 * 설문 정보 등록
	 */
	@Override
	public	ProcessResultVO<ResearchBankVO> addResearch(ResearchBankVO VO) throws Exception {
		ProcessResultVO<ResearchBankVO> resultVO = new ProcessResultVO<ResearchBankVO>();
		try {
			VO.setReshSn(researchBankMapper.selectKey());
			researchBankMapper.insert(VO);
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
	 * 서룸 정보 수정
	 */
	@Override
	public	ProcessResultVO<ResearchBankVO> editResearch(ResearchBankVO VO) throws Exception {
		ProcessResultVO<ResearchBankVO> resultVO = new ProcessResultVO<ResearchBankVO>();
		try {
			researchBankMapper.update(VO);
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
	 * 서룸 정보 삭제
	 */
	@Override
	public	ProcessResultVO<ResearchBankVO> deleteResearch(ResearchBankVO VO) throws Exception {
		ProcessResultVO<ResearchBankVO> resultVO = new ProcessResultVO<ResearchBankVO>();
		try {
			researchBankMapper.delete(VO);
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
	 * 설문 문제 목록 조회
	 */
	@Override
	public	ProcessResultListVO<ResearchBankItemVO> listItem(ResearchBankVO VO) throws Exception {
		ProcessResultListVO<ResearchBankItemVO> resultList = new ProcessResultListVO<ResearchBankItemVO>();
		try {
			List<ResearchBankItemVO> returnList = researchBankItemMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;

	}

	/**
	 * 설문 문제 정보 조회
	 */
	@Override
	public	ProcessResultVO<ResearchBankItemVO> viewItem(ResearchBankItemVO VO) throws Exception {
		VO = researchBankItemMapper.select(VO);
		VO = sysFileService.getFile(VO, new NestedFileHandler());
		this.atclUrlToView(VO);
		return new ProcessResultVO<ResearchBankItemVO>().setResultSuccess()
				.setReturnVO(VO);
	}

	/**
	 * 설문 문제 정보 등록
	 */
	@Override
	public	ProcessResultVO<ResearchBankItemVO> addItem(ResearchBankItemVO VO) throws Exception {
		this.atclUrlToPersist(VO);
		VO.setReshItemSn(researchBankItemMapper.selectKey());
		researchBankItemMapper.insert(VO);
		sysFileService.bindFile(VO, new NestedFileHandler());
		return ProcessResultVO.success();
		//return new ProcessResultVO<ResearchBankItemVO>().setReturnVO(VO).setResultSuccess(); responseJson으로 반환시 오류를 발생시킴.
	}

	/**
	 * 설문 문제 정보 수정
	 */
	@Override
	public	ProcessResultVO<ResearchBankItemVO> editItem(ResearchBankItemVO VO) throws Exception {
		this.atclUrlToPersist(VO);	// URL 변환
		researchBankItemMapper.update(VO);
		sysFileService.bindFileUpdate(VO, new NestedFileHandler());
		return ProcessResultVO.success();
		//return new ProcessResultVO<ResearchBankItemVO>().setReturnVO(VO).setResultSuccess(); responseJson으로 반환시 오류를 발생시킴.
	}

	/**
	 * 설문 문제 정보 삭제
	 */
	@Override
	public ProcessResultVO<ResearchBankItemVO> deleteItem(ResearchBankItemVO VO) throws Exception {
		sysFileService.removeFile(VO, new NestedFileHandler()); // 파일정보 삭제..
		researchBankItemMapper.delete(VO);	// 게시글을 삭제
		return ProcessResultVO.success();
	}

	private void atclUrlToPersist(ResearchBankItemVO VO) {
		VO.setReshItemCts(StringUtil.replaceUrlToPersist(VO.getReshItemCts()));
	}

	private void atclUrlToView(ResearchBankItemVO VO) {
		VO.setReshItemCts(StringUtil.replaceUrlToBrowser(VO.getReshItemCts()));
	}

	/**
	 * 강의실 설문등록 폼에서 설문은행 목록 조회시 기등록된 설문은 제외하다.
	 */
	@Override
	public	ProcessResultListVO<ResearchBankVO> listResearchPreclusive(ResearchBankVO VO) throws Exception {
		ProcessResultListVO<ResearchBankVO> resultList = new ProcessResultListVO<ResearchBankVO>();
		try {
			List<ResearchBankVO> returnList = researchBankMapper.listResearchPreclusive(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	@Override
	public ProcessResultVO<?> sortReserchItem(ResearchBankItemVO VO) {
		String[] itemList = StringUtil.split(VO.getReshItemSns(),"|");
		// 하위 코드 목록을 한꺼번에 조회List<ResearchBankItemVO> itemArray
		List<ResearchBankItemVO> itemArray = researchBankItemMapper.list(VO);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (ResearchBankItemVO sVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(sVO.getReshItemSn() == Integer.parseInt(itemList[order]) ) {
					sVO.setItemOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		// 변경된 시스템코드 어래이를 일괄 저장.
		researchBankItemMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 설문 문제 목록 조회
	 */
	@Override
	public	ProcessResultListVO<ResearchBankItemVO> listItem(ResearchBankItemVO VO) throws Exception {
		ProcessResultListVO<ResearchBankItemVO> resultList = new ProcessResultListVO<ResearchBankItemVO>();
		try {
			List<ResearchBankItemVO> returnList = researchBankItemMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;

	}

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(HashMap<String, String> titles, OutputStream os) throws ServiceProcessException {

		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, "ResearchBank");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment1"), pageRow1, 0, 23, "left");
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment2"), pageRow2, 0, 23, "left");
			rowNum++;
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment3"), pageRow3, 0, 23, "left");
			rowNum++;
			XSSFRow pageRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment2"), pageRow4, 0, 23, "left");
			rowNum++;
			XSSFRow pageRow5 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment2"), pageRow5, 0, 23, "left");

			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);

			POIExcelUtil.createTitleCell(titles.get("reshItemTypeCd"), titleRow, 0);
			POIExcelUtil.createTitleCell(titles.get("emplViewType"), titleRow, 1);
			POIExcelUtil.createTitleCell(titles.get("emplCnt"), titleRow, 2);
			POIExcelUtil.createTitleCell(titles.get("reshItemCts"), titleRow, 3);
			POIExcelUtil.createTitleCell(titles.get("item1"), titleRow, 4);
			POIExcelUtil.createTitleCell(titles.get("item2"), titleRow, 5);
			POIExcelUtil.createTitleCell(titles.get("item3"), titleRow, 6);
			POIExcelUtil.createTitleCell(titles.get("item4"), titleRow, 7);
			POIExcelUtil.createTitleCell(titles.get("item5"), titleRow, 8);
			POIExcelUtil.createTitleCell(titles.get("item6"), titleRow, 9);
			POIExcelUtil.createTitleCell(titles.get("item7"), titleRow, 10);
			POIExcelUtil.createTitleCell(titles.get("item8"), titleRow, 11);
			POIExcelUtil.createTitleCell(titles.get("item9"), titleRow, 12);
			POIExcelUtil.createTitleCell(titles.get("item10"), titleRow, 13);
			POIExcelUtil.createTitleCell(titles.get("item11"), titleRow, 14);
			POIExcelUtil.createTitleCell(titles.get("item12"), titleRow, 15);
			POIExcelUtil.createTitleCell(titles.get("item13"), titleRow, 16);
			POIExcelUtil.createTitleCell(titles.get("item14"), titleRow, 17);
			POIExcelUtil.createTitleCell(titles.get("item15"), titleRow, 18);
			POIExcelUtil.createTitleCell(titles.get("item16"), titleRow, 19);
			POIExcelUtil.createTitleCell(titles.get("item17"), titleRow, 20);
			POIExcelUtil.createTitleCell(titles.get("item18"), titleRow, 21);
			POIExcelUtil.createTitleCell(titles.get("item19"), titleRow, 22);
			POIExcelUtil.createTitleCell(titles.get("item20"), titleRow, 23);

			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 800);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(14, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(15, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(16, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(17, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(18, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(19, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(20, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(21, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(22, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(23, sheet.getDefaultColumnWidth() * 200);

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		} catch (Exception e) {
			throw new ServiceProcessException("Failed! Make excel file.", e);
		}
	}

	/**
	 * Upload 된 Excel의 정보를 확인하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultListVO<ResearchBankItemVO> excelUploadValidationCheck(String fileName,
			String filePath) throws ServiceProcessException {

		ProcessResultListVO<ResearchBankItemVO> resultVO = new ProcessResultListVO<ResearchBankItemVO>();

		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;

		try {
			FileInputStream fis=new FileInputStream(filePath + "/" + fileName);
			workbook= new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (IOException ex2) {
			// TODO Auto-generated catch block
			ex2.printStackTrace();
			throw new ServiceProcessException("Failed read excel : " + ex2.getMessage(), ex2);
		}
		XSSFRow dfltRow = sheet.getRow(5);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		if(cellCount != 24) {
			resultVO.setResult(-1);
			resultVO.setMessage("ERROR_CNT");
			throw new ServiceProcessException("Failed read excel : Invalid file.");
		} else {
			List<ResearchBankItemVO> researchBankList = new ArrayList<ResearchBankItemVO>();
			int rows=sheet.getPhysicalNumberOfRows();
			int lineNo = 0;
			for (int rowIndex = 6; rowIndex < rows; rowIndex++) {
				//행을 읽는다 UserInfoVO에 담는다.
			    XSSFRow row=sheet.getRow(rowIndex);
			    if(row != null) {
			    	String errorCode = "";
			    	lineNo++;
					ResearchBankItemVO rbVO = new ResearchBankItemVO();
					rbVO.setLineNo(Integer.toString(lineNo));
			    	rbVO.setReshItemTypeCd(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(0)).toUpperCase()));
			    	rbVO.setEmplViewType(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(1)).toUpperCase()));
			    	rbVO.setEmplCnt(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(2),true),"0"))));
					rbVO.setReshItemCts(POIExcelUtil.getCellValue(row.getCell(3)));
					rbVO.setEmpl1(POIExcelUtil.getCellValue(row.getCell(4)));
					rbVO.setEmpl2(POIExcelUtil.getCellValue(row.getCell(5)));
					rbVO.setEmpl3(POIExcelUtil.getCellValue(row.getCell(6)));
					rbVO.setEmpl4(POIExcelUtil.getCellValue(row.getCell(7)));
					rbVO.setEmpl5(POIExcelUtil.getCellValue(row.getCell(8)));
					rbVO.setEmpl6(POIExcelUtil.getCellValue(row.getCell(9)));
					rbVO.setEmpl7(POIExcelUtil.getCellValue(row.getCell(10)));
					rbVO.setEmpl8(POIExcelUtil.getCellValue(row.getCell(11)));
					rbVO.setEmpl9(POIExcelUtil.getCellValue(row.getCell(12)));
					rbVO.setEmpl10(POIExcelUtil.getCellValue(row.getCell(13)));
					rbVO.setEmpl11(POIExcelUtil.getCellValue(row.getCell(14)));
					rbVO.setEmpl12(POIExcelUtil.getCellValue(row.getCell(15)));
					rbVO.setEmpl13(POIExcelUtil.getCellValue(row.getCell(16)));
					rbVO.setEmpl14(POIExcelUtil.getCellValue(row.getCell(17)));
					rbVO.setEmpl15(POIExcelUtil.getCellValue(row.getCell(18)));
					rbVO.setEmpl16(POIExcelUtil.getCellValue(row.getCell(19)));
					rbVO.setEmpl17(POIExcelUtil.getCellValue(row.getCell(20)));
					rbVO.setEmpl18(POIExcelUtil.getCellValue(row.getCell(21)));
					rbVO.setEmpl19(POIExcelUtil.getCellValue(row.getCell(22)));
					rbVO.setEmpl20(POIExcelUtil.getCellValue(row.getCell(23)));

					if(!"K".equals(rbVO.getReshItemTypeCd()) && !"D".equals(rbVO.getReshItemTypeCd())){
						errorCode += "|"+"RESHITEMTYPECD";
					}
					if(ValidationUtils.isEmpty(rbVO.getReshItemCts())){
						errorCode += "|"+"RESHITEMCTS";
					}
					if("K".equals(rbVO.getReshItemTypeCd()) ){
						if(!"H".equals(rbVO.getEmplViewType()) && !"W".equals(rbVO.getEmplViewType())){
							errorCode += "|"+"EMPLVIEWTYPE";
						}
						if(ValidationUtils.isZeroNull(rbVO.getEmplCnt())){
							errorCode += "|"+"EMPLCNT";
						}else {
							if(rbVO.getEmplCnt() > 20 ){
								errorCode += "|"+"EMPLCNT";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl1()) || rbVO.getEmpl1().getBytes().length > 1000){
								errorCode += "|"+"EMPL1";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl2()) || rbVO.getEmpl2().getBytes().length > 1000){
								errorCode += "|"+"EMPL2";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl3()) || rbVO.getEmpl3().getBytes().length > 1000){
								errorCode += "|"+"EMPL3";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl4()) || rbVO.getEmpl4().getBytes().length > 1000){
								errorCode += "|"+"EMPL4";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl5()) || rbVO.getEmpl5().getBytes().length > 1000){
								errorCode += "|"+"EMPL5";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl6()) || rbVO.getEmpl6().getBytes().length > 1000){
								errorCode += "|"+"EMPL6";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl7()) || rbVO.getEmpl7().getBytes().length > 1000){
								errorCode += "|"+"EMPL7";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl8()) || rbVO.getEmpl8().getBytes().length > 1000){
								errorCode += "|"+"EMPL8";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl9()) || rbVO.getEmpl9().getBytes().length > 1000){
								errorCode += "|"+"EMPL9";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl10()) || rbVO.getEmpl10().getBytes().length > 1000){
								errorCode += "|"+"EMPL10";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl11()) || rbVO.getEmpl11().getBytes().length > 1000){
								errorCode += "|"+"EMPL11";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl12()) || rbVO.getEmpl12().getBytes().length > 1000){
								errorCode += "|"+"EMPL12";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl13()) || rbVO.getEmpl13().getBytes().length > 1000){
								errorCode += "|"+"EMPL13";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl14()) || rbVO.getEmpl14().getBytes().length > 1000){
								errorCode += "|"+"EMPL14";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl15()) || rbVO.getEmpl15().getBytes().length > 1000){
								errorCode += "|"+"EMPL15";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl16()) || rbVO.getEmpl16().getBytes().length > 1000){
								errorCode += "|"+"EMPL16";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl17()) || rbVO.getEmpl17().getBytes().length > 1000){
								errorCode += "|"+"EMPL17";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl18()) || rbVO.getEmpl18().getBytes().length > 1000){
								errorCode += "|"+"EMPL18";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl19()) || rbVO.getEmpl19().getBytes().length > 1000){
								errorCode += "|"+"EMPL19";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmpl20()) || rbVO.getEmpl20().getBytes().length > 1000){
								errorCode += "|"+"EMPL20";
							}
							if(ValidationUtils.isNotZeroNull(rbVO.getEmplCnt())){
								for(int i=20; i>rbVO.getEmplCnt(); i--){
									errorCode = errorCode.replace("|EMPL"+i, "");
								}
							}
						}
					}
					rbVO.setErrorCode(errorCode);
					researchBankList.add(rbVO);
			    }
			}
			resultVO.setReturnList(researchBankList);
			resultVO.setResult(1);
		}
		return resultVO;
	}

	/**
	 * 설문 문제 목록 조회
	 */
	@Override
	public	ProcessResultVO<ResearchBankItemVO> addResearchBankItemBatch(ResearchBankItemVO VO) throws Exception {
		VO.setReshItemSn(researchBankItemMapper.selectKey());
		researchBankItemMapper.insert(VO);
		return new ProcessResultVO<ResearchBankItemVO>().setResultSuccess().setReturnVO(new ResearchBankItemVO());

	}

	@Override
	public ProcessResultVO<ResearchBankItemVO> addResearchBankItemBatch(
			List<ResearchBankItemVO> researchBankList) throws Exception {
		for(ResearchBankItemVO VO : researchBankList) {
			VO.setReshItemSn(researchBankItemMapper.selectKey());
			researchBankItemMapper.insert(VO);
		}
		return new ProcessResultVO<ResearchBankItemVO>().setResultSuccess().setReturnVO(new ResearchBankItemVO());

	}

}


