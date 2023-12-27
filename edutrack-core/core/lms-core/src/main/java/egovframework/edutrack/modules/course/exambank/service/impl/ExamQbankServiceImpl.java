package egovframework.edutrack.modules.course.exambank.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankCtgrVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankService;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;




@Service("examQbankService")
public class ExamQbankServiceImpl
	extends EgovAbstractServiceImpl implements ExamQbankService {

	/** Mapper */
	@Resource(name="examQbankCtgrMapper")
	private ExamQbankCtgrMapper examQbankCtgrMapper;

	@Resource(name="examQbankQstnMapper")
	private ExamQbankQstnMapper examQbankQstnMapper;

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;


	private final class NestedFileHandler
		implements FileHandler<ExamQbankQstnVO> {

		@Override
		public String getPK(ExamQbankQstnVO VO) {
			return VO.getQstnSn().toString();
		}

		@Override
		public String getRepoCd() {
			return "EXAM_BANK";
		}

		@Override
		public List<SysFileVO> getFiles(ExamQbankQstnVO VO) {
			List<SysFileVO> fileList = VO.getAttachImages();
			return fileList;
		}

		@Override
		public ExamQbankQstnVO setFiles(ExamQbankQstnVO VO, FileListVO fileListVO) {
			VO.setAttachImages(fileListVO.getFiles("image"));
			return VO;
		}
	}

	/**
	 * 시험 문제은행 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ExamQbankCtgrVO> listCtgr(ExamQbankCtgrVO vo) throws Exception {
		ProcessResultListVO<ExamQbankCtgrVO> resultList = new ProcessResultListVO<ExamQbankCtgrVO>();
		try {
			List<ExamQbankCtgrVO> returnList = examQbankCtgrMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 시험 문제은행 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ExamQbankCtgrVO> subListCtgr(String parCtgrCd) throws Exception {
		ExamQbankCtgrVO iExamQbankCategoryVO = new ExamQbankCtgrVO();
		iExamQbankCategoryVO.setParCtgrCd(parCtgrCd);
		ProcessResultListVO<ExamQbankCtgrVO> resultList = new ProcessResultListVO<ExamQbankCtgrVO>();
		try {
			List<ExamQbankCtgrVO> returnList = examQbankCtgrMapper.subList(iExamQbankCategoryVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	
	
	/**
	 * 시험 문제은행 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankCtgrVO> viewCtgr(String ctgrCd) throws Exception {
		ExamQbankCtgrVO iExamQbankCategoryVO = new ExamQbankCtgrVO();
		iExamQbankCategoryVO.setCtgrCd(ctgrCd);
		
		ProcessResultVO<ExamQbankCtgrVO> resultVO = new ProcessResultVO<ExamQbankCtgrVO>();
		try {
			ExamQbankCtgrVO returnVO = examQbankCtgrMapper.select(iExamQbankCategoryVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		return resultVO;
	}

	/**
	 * 시험 문제은행 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankCtgrVO> addCtgr(ExamQbankCtgrVO iExamQbankCategoryVO) throws Exception {

		ProcessResultVO<ExamQbankCtgrVO> resultVO = new ProcessResultVO<ExamQbankCtgrVO>();
		
		try {
			if(StringUtil.isNull(iExamQbankCategoryVO.getCtgrCd())) {
				//---- 분류 코드 생성
				ExamQbankCtgrVO examQbankCategoryVO = examQbankCtgrMapper.selectCd();
				resultVO.setReturnVO(examQbankCategoryVO);

				//---- 신규 코드 세팅
				iExamQbankCategoryVO.setCtgrCd(examQbankCategoryVO.getCtgrCd());
			}
			if(iExamQbankCategoryVO.getParCtgrCd() != null) {
				iExamQbankCategoryVO.setParCtgrLvl(1);
			}
			
			iExamQbankCategoryVO.setCtgrLvl(StringUtil.nvl(iExamQbankCategoryVO.getParCtgrLvl(), 0)+1);
			
			examQbankCtgrMapper.insert(iExamQbankCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		

		return resultVO;
	}

	/**
	 * 시험 문제은행 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankCtgrVO> editCtgr(ExamQbankCtgrVO VO) throws Exception {
		ProcessResultVO<ExamQbankCtgrVO> resultVO = new ProcessResultVO<ExamQbankCtgrVO>();
		try {
			examQbankCtgrMapper.update(VO);
			examQbankCtgrMapper.updateSubNames(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}


	/**
	 * 시험 문제은행 분류 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankCtgrVO> deleteCtgr(String ctgrCd) throws Exception {
		
		ProcessResultVO<ExamQbankCtgrVO> resultVO = new ProcessResultVO<ExamQbankCtgrVO>();
		try {
			//-- 등록된 시험 문제 삭제후 분류를 삭제한다.
			
			List<String> subCtgrCdList = examQbankCtgrMapper.selectUpperQbankCtgr(ctgrCd);
			for(String subCtgrCd : subCtgrCdList) {
				ExamQbankQstnVO eqqVO = new ExamQbankQstnVO();
				eqqVO.setCtgrCd(subCtgrCd);
				examQbankQstnMapper.deleteAll(eqqVO);
				
				ExamQbankCtgrVO iExamQbankCategoryVO = new ExamQbankCtgrVO();
				iExamQbankCategoryVO.setCtgrCd(subCtgrCd);

				examQbankCtgrMapper.delete(iExamQbankCategoryVO);
			}
			
			ExamQbankCtgrVO iExamQbankCategoryVO = new ExamQbankCtgrVO();
			iExamQbankCategoryVO.setCtgrCd(ctgrCd);

			examQbankCtgrMapper.delete(iExamQbankCategoryVO);
			
			resultVO.setReturnVO(iExamQbankCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		
		return resultVO;
	}

	/**
	 * 시험 문제은행 문제 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ExamQbankQstnVO> listQstn(String ctgrCd, String parCtgrCd, String qstnType, String searchKey) throws Exception {
		ExamQbankQstnVO qstnVO = new ExamQbankQstnVO();
		qstnVO.setCtgrCd(ctgrCd);
		qstnVO.setParCtgrCd(parCtgrCd);
		qstnVO.setQstnType(qstnType);
		qstnVO.setSearchKey(searchKey);
		ProcessResultListVO<ExamQbankQstnVO> resultList = new ProcessResultListVO<ExamQbankQstnVO>();
		try {
			List<ExamQbankQstnVO> returnList = examQbankQstnMapper.list(qstnVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 시험 문제은행 문제 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> viewQstn(ExamQbankQstnVO VO) throws Exception {

		ExamQbankQstnVO qeustion = examQbankQstnMapper.select(VO);
		qeustion = sysFileService.getFile(qeustion, new NestedFileHandler());
		this.atclUrlToView(qeustion);

		return new ProcessResultVO<ExamQbankQstnVO>()
				.setResultSuccess()
				.setReturnVO(qeustion);

	}

	/**
	 * 시험 문제은행 문제 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> addQstn(ExamQbankQstnVO VO) throws Exception {

		if(VO.getQstnSn() == null || VO.getQstnSn() == 0) {

			//---- 신규 코드 세팅
			VO.setQstnSn(examQbankQstnMapper.selectKey());
		}

		// 첨부 이미지 URL 변경
		this.atclUrlToPersist(VO);
		//--코드를 세팅한 후에 글을 등록한다.
		examQbankQstnMapper.insert(VO);
    	// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(VO, new NestedFileHandler());

    	return ProcessResultVO.success();
	}

	/**
	 * 시험 문제은행 문제 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> editQstn(ExamQbankQstnVO VO) throws Exception {
		this.atclUrlToPersist(VO);	// URL 변환
		examQbankQstnMapper.update(VO);
		sysFileService.bindFileUpdate(VO, new NestedFileHandler());

		return ProcessResultVO.success();
	}


	/**
	 * 시험 문제은행 문제 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> deleteQstn(ExamQbankQstnVO VO) throws Exception {
		sysFileService.removeFile(VO, new NestedFileHandler()); // 파일정보 삭제..
		examQbankQstnMapper.delete(VO);

		return ProcessResultVO.success();
	}


	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> getArticle(ExamQbankQstnVO VO) throws Exception {
		ExamQbankQstnVO question = examQbankQstnMapper.select(VO);

		// 첨부파일 정보 조회
//		question = this.listAttachFiles(examQbankQuestionVO).getReturnVO();
		return new ProcessResultVO<ExamQbankQstnVO>()
				.setResultSuccess()
				.setReturnVO(question);
	}

	private void atclUrlToPersist(ExamQbankQstnVO VO) {
		VO.setQstnCts(StringUtil.replaceUrlToPersist(VO.getQstnCts()));
	}
	private void atclUrlToView(ExamQbankQstnVO VO) {
		VO.setQstnCts(StringUtil.replaceUrlToBrowser(VO.getQstnCts()));
	}

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(HashMap<String, String> titles, OutputStream os, String orgCd) throws ServiceProcessException {
		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, "QuestionBank");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info1"), pageRow1, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info2"), pageRow2, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info3"), pageRow3, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info4"), pageRow4, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow5 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info5"), pageRow5, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow6 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info6"), pageRow6, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow7 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info7"), pageRow7, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow8 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info8"), pageRow8, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow9 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info9"), pageRow9, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow10 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info10"), pageRow10, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow11 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info11"), pageRow11, 0, 9, "left");
			rowNum++;
			XSSFRow pageRow12 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info12"), pageRow12, 0, 9, "left");

			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);


			POIExcelUtil.createTitleCell(titles.get("type"), titleRow, 0);
			POIExcelUtil.createTitleCell(titles.get("title"), titleRow, 1);
			POIExcelUtil.createTitleCell(titles.get("qstncts"), titleRow, 2);
			POIExcelUtil.createTitleCell(titles.get("item1"), titleRow, 3);
			POIExcelUtil.createTitleCell(titles.get("item2"), titleRow, 4);
			POIExcelUtil.createTitleCell(titles.get("item3"), titleRow, 5);
			POIExcelUtil.createTitleCell(titles.get("item4"), titleRow, 6);
			POIExcelUtil.createTitleCell(titles.get("item5"), titleRow, 7);
			POIExcelUtil.createTitleCell(titles.get("rightansr"), titleRow, 8);
			POIExcelUtil.createTitleCell(titles.get("comment"), titleRow, 9);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1200);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 800);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 800);

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}

		}catch (Exception e) {
			throw new ServiceProcessException("Failed! Make excel file.", e);
		}
	}

	@Override
	public ProcessResultListVO<ExamQbankQstnVO> excelUploadValidationCheck(String fileName,
			String filePath) throws ServiceProcessException {

		ProcessResultListVO<ExamQbankQstnVO> resultVO = new ProcessResultListVO<ExamQbankQstnVO>();

		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		FileInputStream fis = null;
		try {
			fis=new FileInputStream(filePath + "/" + fileName);
			workbook= new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (IOException ex2) {
			// TODO Auto-generated catch block
			throw new ServiceProcessException("Failed read excel : " + ex2.getMessage(), ex2);
		}finally {
	      if (fis != null) {
	    	  	try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				} 
	        }
		}
		XSSFRow dfltRow = sheet.getRow(12);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		if(cellCount != 10) {
			resultVO.setResult(-1);
			resultVO.setMessage("ERROR_CNT");
			throw new ServiceProcessException("Failed read excel : Invalid file.");
		} else {
			List<ExamQbankQstnVO> questionBankList = new ArrayList<ExamQbankQstnVO>();
			String regex = "^[a-zA-Z가-힣0-9,|]*$";
			int rows=sheet.getPhysicalNumberOfRows();
			int lineNo = 0;
			for (int rowIndex = 13; rowIndex < rows; rowIndex++) {
			    XSSFRow row=sheet.getRow(rowIndex);
			    if(row != null) {
			    	lineNo++;
			    	String errorCode = "";
			    	ExamQbankQstnVO qstnVO = new ExamQbankQstnVO();
			    	qstnVO.setLineNo(lineNo+"");
					qstnVO.setQstnType(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(0)).toUpperCase()));
					qstnVO.setTitle(POIExcelUtil.getCellValue(row.getCell(1)));
					qstnVO.setQstnCts(POIExcelUtil.getCellValue(row.getCell(2)));
					qstnVO.setView1(POIExcelUtil.getCellValue(row.getCell(3),true));
					qstnVO.setView2(POIExcelUtil.getCellValue(row.getCell(4),true));
					qstnVO.setView3(POIExcelUtil.getCellValue(row.getCell(5),true));
					qstnVO.setView4(POIExcelUtil.getCellValue(row.getCell(6),true));
					qstnVO.setView5(POIExcelUtil.getCellValue(row.getCell(7),true));

					if("S".equals(qstnVO.getQstnType()) ){
						qstnVO.setRgtAnsr(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(8)).toUpperCase()));
					} else if("K".equals(qstnVO.getQstnType())){
						// 객관식의 경우 전각으로 숫자가 들어오면 반각으로 변경한다.
						qstnVO.setRgtAnsr(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(8),true).toUpperCase()));
					} else {
						qstnVO.setRgtAnsr(POIExcelUtil.getCellValue(row.getCell(8)));
					}
					qstnVO.setQstnExpl(POIExcelUtil.getCellValue(row.getCell(9)));

					if(!"J".equals(qstnVO.getQstnType()) && !"K".equals(qstnVO.getQstnType())
						&& !"S".equals(qstnVO.getQstnType()) && !"D".equals(qstnVO.getQstnType())	){
						errorCode += "|"+"TYPEQSTNTYPE";
					}
					if(ValidationUtils.isEmpty(qstnVO.getTitle())){
						errorCode += "|"+"EMPTYTITLE";
					}
					if(ValidationUtils.isEmpty(qstnVO.getQstnCts())){
						errorCode += "|"+"EMPTYQSTNCTS";
					}
					if(ValidationUtils.isEmpty(qstnVO.getRgtAnsr())){
						errorCode += "|"+"EMPTYRGTANSR";
					}
					if(ValidationUtils.isEmpty(qstnVO.getQstnExpl())){
						//errorCode += "|"+"EMPTYQSTNEXPL";
					}

					if("K".equals(qstnVO.getQstnType()) ){
						if(ValidationUtils.isEmpty(qstnVO.getView1())){
							errorCode += "|"+"EMPTYVIEW1";
						}
						if(ValidationUtils.isEmpty(qstnVO.getView2())){
							errorCode += "|"+"EMPTYVIEW2";
						}
						if(ValidationUtils.isEmpty(qstnVO.getView3())){
							errorCode += "|"+"EMPTYVIEW3";
						}
						if(ValidationUtils.isEmpty(qstnVO.getView4())){
							errorCode += "|"+"EMPTYVIEW4";
						}
						if(!StringUtil.isNumber(qstnVO.getRgtAnsr())){
							errorCode += "|"+"TYPERGTANSR";
						} else {
							if(Integer.parseInt(qstnVO.getRgtAnsr()) > 5){
								errorCode += "|"+"TYPERGTANSR";
							}
							if(ValidationUtils.isEmpty(qstnVO.getView5()) && Integer.parseInt(qstnVO.getRgtAnsr()) > 4 ){
								errorCode += "|"+"TYPERGTANSR";
							}
						}

					}else if("S".equals(qstnVO.getQstnType()) ){
						if(!"O".equals(qstnVO.getRgtAnsr()) && !"X".equals(qstnVO.getRgtAnsr())){
							errorCode += "|"+"TYPERGTANSR";
						}
					} else if("D".equals(qstnVO.getQstnType()) ){
						/*
						if(!qstnVO.getRgtAnsr().matches(regex)){
							errorCode += "|"+"TYPERGTANSR";
						}
						*/
					}
					qstnVO.setErrorCode(errorCode);
					questionBankList.add(qstnVO);
			    }
			}
			resultVO.setReturnList(questionBankList);
			resultVO.setResult(1);
		}
		return resultVO;
	}

	/**
	 * 시험 문제은행 문제 일괄 등록
	 * @param List<UserInfoVO> userList
	 * @return
	 */
	@Override
	public ProcessResultVO<ExamQbankQstnVO> addQstnBatch(
			List<ExamQbankQstnVO> questionBankList) {
		for(ExamQbankQstnVO VO : questionBankList) {
			if(VO.getQstnSn() == null || VO.getQstnSn() == 0) {
				//---- 문제 코드 생성
				//---- 신규 코드 세팅
				VO.setQstnSn(examQbankQstnMapper.selectKey());
			}
			examQbankQstnMapper.insert(VO);
		}
		return new ProcessResultVO<ExamQbankQstnVO>().setResultSuccess().setReturnVO(new ExamQbankQstnVO());
	}


	/**
	 * 시험 문제은행 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ExamQbankCtgrVO> subListCtgrUseY(ExamQbankCtgrVO vo) throws Exception {
		ExamQbankCtgrVO iExamQbankCategoryVO = new ExamQbankCtgrVO();
		iExamQbankCategoryVO.setParCtgrCd(vo.getParCtgrCd());
		iExamQbankCategoryVO.setUseYn(vo.getUseYn());
		ProcessResultListVO<ExamQbankCtgrVO> resultList = new ProcessResultListVO<ExamQbankCtgrVO>();
		try {
			List<ExamQbankCtgrVO> returnList = examQbankCtgrMapper.subList(iExamQbankCategoryVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 시험 문제은행 문제 사용 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ExamQbankQstnVO> listQstnUseY(String ctgrCd, String parCtgrCd, String qstnType, String searchKey, String useYn) throws Exception {
		ExamQbankQstnVO qstnVO = new ExamQbankQstnVO();
		qstnVO.setCtgrCd(ctgrCd);
		qstnVO.setParCtgrCd(parCtgrCd);
		qstnVO.setQstnType(qstnType);
		qstnVO.setSearchKey(searchKey);
		qstnVO.setUseYn(useYn);
		ProcessResultListVO<ExamQbankQstnVO> resultList = new ProcessResultListVO<ExamQbankQstnVO>();
		try {
			List<ExamQbankQstnVO> returnList = examQbankQstnMapper.list(qstnVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
}