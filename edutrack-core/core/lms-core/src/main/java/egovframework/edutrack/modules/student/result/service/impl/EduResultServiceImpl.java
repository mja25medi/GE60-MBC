package egovframework.edutrack.modules.student.result.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.edutrack.modules.student.worklog.service.impl.StdEduRsltWorkLogMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service("eduResultService")
public class EduResultServiceImpl
	extends EgovAbstractServiceImpl implements EduResultService {


	/** Mapper */
	@Resource(name="eduResultMapper")
	private EduResultMapper 		eduResultMapper;
	
	@Resource(name="stdEduRsltWorkLogMapper")
	private StdEduRsltWorkLogMapper 		stdEduRsltWorkLogMapper;

	/**
	 * 수강 결과 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<EduResultVO> listResult(EduResultVO vo) throws Exception {
		ProcessResultListVO<EduResultVO> resultList = new ProcessResultListVO<EduResultVO>(); 
		try {
			List<EduResultVO> returnList =  eduResultMapper.listResult(vo);
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
	 * 수강 결과 목록 조회(페이징)
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<EduResultVO> listResultPaging(EduResultVO vo ) throws Exception {
		ProcessResultListVO<EduResultVO> resultList = new ProcessResultListVO<EduResultVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = eduResultMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EduResultVO> returnList =  eduResultMapper.listResultPaging(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}

	/**
	 * 수강 결과 정보 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<EduResultVO> viewResult(EduResultVO vo) throws Exception {
		ProcessResultVO<EduResultVO> resultVO = new ProcessResultVO<EduResultVO>(); 
		try {
			EduResultVO returnVO =  eduResultMapper.selectResult(vo);
			resultVO.setResult(1);
			resultVO.setReturnVO(returnVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
		
	}
	
	/**
	 * crm 성적 정보 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<EduResultVO> listCrmEduResultPaging(EduResultVO vo) throws Exception {
		ProcessResultListVO<EduResultVO> resultList = new ProcessResultListVO<EduResultVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = eduResultMapper.countCrmEduResult(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EduResultVO> returnList =  eduResultMapper.listCrmEduResultPaging(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<EduResultVO> addResult(EduResultVO vo, StdEduRsltWorkLogVO lvo) throws Exception {
		//-- 작동 로그를 남기자.
		stdEduRsltWorkLogMapper.insert(lvo);
		//-- 기존 값 가져오기
		eduResultMapper.insertResult(vo);
		//eduResultMapper.autoInserteduRslt(vo);
		return ProcessResultVO.success();
	}


	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<EduResultVO> addResultAll(EduResultVO vo, String strStdNo,
			String strPrgrScore, String strAttdScore, String strExamScore, String strSemiExamScore, String strAsmtScore,
			String strForumScore, String strJoinScore, String strEtcScore,
			String strTotScore, String strConvScore, StdEduRsltWorkLogVO lvo ) throws Exception{

		//-- 작동 로그를 남기자.
		stdEduRsltWorkLogMapper.insert(lvo);

		String[] stdNo = StringUtil.split(strStdNo, "|");
		String[] prgrScore = StringUtil.split(strPrgrScore, "|");
		String[] attdScore = StringUtil.split(strAttdScore, "|");
		String[] examScore = StringUtil.split(strExamScore, "|");
		String[] semiExamScore = StringUtil.split(strSemiExamScore, "|");
		String[] asmtScore = StringUtil.split(strAsmtScore, "|");
		String[] forumScore = StringUtil.split(strForumScore, "|");
		String[] joinScore = StringUtil.split(strJoinScore, "|");
		String[] etcScore = StringUtil.split(strEtcScore, "|");
		String[] totScore = StringUtil.split(strTotScore, "|");
		String[] convScore = StringUtil.split(strConvScore, "|");

		for(int i=0; i < stdNo.length; i++) {

			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(vo.getCrsCreCd());
			eduResultVO.setSbjCd(vo.getSbjCd());
			eduResultVO.setStdNo(stdNo[i]);
			eduResultVO.setPrgrScore(Double.parseDouble(prgrScore[i]));
			eduResultVO.setAttdScore(Double.parseDouble(attdScore[i]));
			eduResultVO.setExamScore(Double.parseDouble(examScore[i]));
			eduResultVO.setSemiExamScore(Double.parseDouble(semiExamScore[i]));
			eduResultVO.setAsmtScore(Double.parseDouble(asmtScore[i]));
			eduResultVO.setForumScore(Double.parseDouble(forumScore[i]));
			eduResultVO.setJoinScore(Double.parseDouble(joinScore[i]));
			eduResultVO.setEtcScore(Double.parseDouble(etcScore[i]));
			eduResultVO.setTotScore(Double.parseDouble(totScore[i]));
			eduResultVO.setConvScore(Double.parseDouble("0.0"));

			eduResultMapper.insertResult(eduResultVO);
			//eduResultMapper.autoInserteduRslt(eduResultVO);
		}
		return ProcessResultVO.success();
	}

	public void listExcelDownload(EduResultVO eduResultVO, CreateCourseVO createCourseVO, OutputStream os) throws Exception {

		List<EduResultVO> resultList = this.listResult(eduResultVO).getReturnList();

		try {
			int rowNum = 0;
			
			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("수강생 성적");
			
			// 페이지 제목줄
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			pageRow1.setHeight((short)550);
			String info = "수강생 성적 리스트 ("+createCourseVO.getCrsCreNm()+")";
			POIExcelUtil.createMergeCell(info, pageRow1, 0, 6, "center");
						
			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("과정명", titleRow1, 0);
			POIExcelUtil.createContentCell(createCourseVO.getCrsCreNm(), titleRow1, 1,"left");
			POIExcelUtil.createTitleCell("기수명", titleRow1, 2);
			POIExcelUtil.createContentCell(createCourseVO.getCreYear()+"년도 "+createCourseVO.getCreTerm()+"기수", titleRow1, 3,"left");
			POIExcelUtil.createTitleCell("학습기간", titleRow1, 4);
			POIExcelUtil.createMergeCell(createCourseVO.getEnrlStartDttm()+" ~ "+ createCourseVO.getEnrlEndDttm(), titleRow1, 5, 6, "left");
			
			rowNum++;
			XSSFRow titleRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("수료기준", titleRow2, 0);
			POIExcelUtil.createContentCell("출석(진도율)", titleRow2, 1,"left");
			POIExcelUtil.createContentCell("80% 이상", titleRow2, 2,"left");
			POIExcelUtil.createContentCell("총점", titleRow2, 3,"left");
			POIExcelUtil.createContentCell("60점 이상", titleRow2, 4,"left");
			
			rowNum++;
			XSSFRow titleRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("평가비율", titleRow3, 0);
			POIExcelUtil.createContentCell("시험", titleRow3, 1,"left");
			POIExcelUtil.createContentCell(createCourseVO.getExamRatio().toString()+"%", titleRow3, 2,"left");
			POIExcelUtil.createContentCell("진행단계평가", titleRow3, 3,"left");
			POIExcelUtil.createContentCell(createCourseVO.getSemiExamRatio().toString()+"%", titleRow3, 4,"left");
			POIExcelUtil.createContentCell("과제", titleRow3, 5,"left");
			POIExcelUtil.createContentCell(createCourseVO.getAsmtRatio().toString()+"%", titleRow3, 6,"left");
			
			Integer prgrRatio = createCourseVO.getPrgrRatio();
			if(prgrRatio > 0) {
				POIExcelUtil.createContentCell("진도", titleRow3, 7,"left");
				POIExcelUtil.createContentCell(prgrRatio.toString()+"%", titleRow3, 8,"left");				
			}
			
			rowNum++;
			rowNum++;
			//-- 컬럼 제목줄 만들기
			XSSFRow titleRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("번호", titleRow4, 0);
			POIExcelUtil.createTitleCell("이름", titleRow4, 1);
			POIExcelUtil.createTitleCell("아이디", titleRow4, 2);
			POIExcelUtil.createTitleCell("소속기업", titleRow4, 3);
			POIExcelUtil.createTitleCell("분반", titleRow4, 4);
			POIExcelUtil.createTitleCell("진도율", titleRow4, 5);
			POIExcelUtil.createTitleCell("시험", titleRow4, 6);
			POIExcelUtil.createTitleCell("진행단계평가", titleRow4, 7);
			POIExcelUtil.createTitleCell("과제", titleRow4, 8);
			POIExcelUtil.createTitleCell("기타1", titleRow4, 9);
			POIExcelUtil.createTitleCell("기타2", titleRow4, 10);
			POIExcelUtil.createTitleCell("기타3", titleRow4, 11);
			POIExcelUtil.createTitleCell("기타4", titleRow4, 12);
			POIExcelUtil.createTitleCell("기타5", titleRow4, 13);
			POIExcelUtil.createTitleCell("총점", titleRow4, 14);
			POIExcelUtil.createTitleCell("수료여부", titleRow4, 15);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 800);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(14, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(15, sheet.getDefaultColumnWidth() * 400);
			

			for (int i=0; i<resultList.size();i++) {
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				EduResultVO vo = resultList.get(i);
				
				POIExcelUtil.createContentCell(i+1, row, 0, "center");
				POIExcelUtil.createContentCell(vo.getUserNm(), row, 1, "center");
				POIExcelUtil.createContentCell(vo.getUserId(), row, 2, "center");
				POIExcelUtil.createContentCell(vo.getDeptNm(), row, 3, "center");
				POIExcelUtil.createContentCell(vo.getDeclsNo().toString(), row, 4, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getPrgrRate()), row, 5, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getExamScore()), row, 6, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getSemiExamScore()), row, 7, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getAsmtScore()), row, 8, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getEtcScore()), row, 9, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getEtcScore2()), row, 10, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getEtcScore3()), row, 11, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getEtcScore4()), row, 12, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getEtcScore5()), row, 13, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(vo.getTotScore()), row, 14, "center");
				POIExcelUtil.createContentCell(vo.getEnrlStsNm(), row, 15, "center");
				
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
			
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}

	/**
	 * 엑셀 파일 업로드 (성적 저장)
	 */
	@SuppressWarnings("unused")
	public ProcessResultVO<EduResultVO> addExcelUpload(EduResultVO vo, CreateCourseVO createCourseVO, String fileName, String filePath) throws ServiceProcessException {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(filePath+"/"+fileName));
			Sheet sheet = workbook.getSheet(0);

			int rowNum = 0;
			String errMsg = "";
			for(int j=2; j<sheet.getRows(); j++){
				rowNum++;
				Cell cellRowNum 		= sheet.getCell(0 ,j);	//번호
				Cell cellUserNm 		= sheet.getCell(1 ,j);	//이름
				Cell cellUserId 		= sheet.getCell(2 ,j);	//아이디
				Cell cellStdNo 			= sheet.getCell(3 ,j);	//수강번호
				Cell cellPrgrScore 		= sheet.getCell(4 ,j);	//진도점수
				Cell cellAttdScore 		= sheet.getCell(5 ,j);	//출석점수
				Cell cellExamScore 		= sheet.getCell(6 ,j);	//시험점수
				Cell cellAsmtScore 		= sheet.getCell(7 ,j);	//과제점수
				Cell cellForumScore 	= sheet.getCell(8 ,j);	//포럼점수
				Cell cellPrjtScore 		= sheet.getCell(9 ,j);	//프로젝트점수
				Cell cellJoinScore 		= sheet.getCell(10 ,j);	//참여점수
				Cell cellEtcScore 		= sheet.getCell(11 ,j);	//기타점수
				Cell cellTotScore 		= sheet.getCell(12 ,j);	//총점수

				int isCheckTrue = 0;

				if(!StringUtil.isNumber(cellPrgrScore.getContents().trim()) || Integer.parseInt(cellPrgrScore.getContents().trim()) > createCourseVO.getPrgrRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 진도 점수 오류 (숫자가 아니거나 진도 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellAttdScore.getContents().trim()) || Integer.parseInt(cellAttdScore.getContents().trim()) > createCourseVO.getAttdRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 출석 점수 오류 (숫자가 아니거나 출석 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellExamScore.getContents().trim()) || Integer.parseInt(cellExamScore.getContents().trim()) > createCourseVO.getExamRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 시험 점수 오류 (숫자가 아니거나 시험 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellAsmtScore.getContents().trim()) || Integer.parseInt(cellAsmtScore.getContents().trim()) > createCourseVO.getAsmtRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 과제 점수 오류 (숫자가 아니거나 과제 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellForumScore.getContents().trim()) || Integer.parseInt(cellForumScore.getContents().trim()) > createCourseVO.getForumRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 포럼 점수 오류 (숫자가 아니거나 포럼 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellPrjtScore.getContents().trim()) || Integer.parseInt(cellPrjtScore.getContents().trim()) > createCourseVO.getPrjtRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 프로젝트 점수 오류 (숫자가 아니거나 프로젝트 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellJoinScore.getContents().trim()) || Integer.parseInt(cellJoinScore.getContents().trim()) > createCourseVO.getJoinRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 참여 점수 오류 (숫자가 아니거나 참여 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellEtcScore.getContents().trim()) || Integer.parseInt(cellEtcScore.getContents().trim()) > createCourseVO.getEtcRatio()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 기타 점수1 오류 (숫자가 아니거나 기타 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellEtcScore.getContents().trim()) || Integer.parseInt(cellEtcScore.getContents().trim()) > createCourseVO.getEtcRatio2()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 기타 점수2 오류 (숫자가 아니거나 기타 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellEtcScore.getContents().trim()) || Integer.parseInt(cellEtcScore.getContents().trim()) > createCourseVO.getEtcRatio3()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 기타 점수3 오류 (숫자가 아니거나 기타 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellEtcScore.getContents().trim()) || Integer.parseInt(cellEtcScore.getContents().trim()) > createCourseVO.getEtcRatio4()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 기타 점수4 오류 (숫자가 아니거나 기타 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellEtcScore.getContents().trim()) || Integer.parseInt(cellEtcScore.getContents().trim()) > createCourseVO.getEtcRatio5()) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 기타 점수5 오류 (숫자가 아니거나 기타 점수 비율 이상으로 입력 되었습니다.\n";
				}
				if(!StringUtil.isNumber(cellTotScore.getContents().trim()) || Integer.parseInt(cellTotScore.getContents().trim()) > 100) {
					isCheckTrue++;
					errMsg += cellRowNum.getContents()+". 라인 : 총 점수 오류 (숫자가 아니거나 기타 100점 이상으로 입력 되었습니다.\n";
				}
				if(isCheckTrue == 0) {
					EduResultVO eduResultVO = new EduResultVO();
					eduResultVO.setCrsCreCd(vo.getCrsCreCd());
					eduResultVO.setStdNo(cellStdNo.getContents());
					eduResultVO.setPrgrScore(Integer.parseInt(cellPrgrScore.getContents().trim()));
					eduResultVO.setAttdScore(Integer.parseInt(cellAttdScore.getContents().trim()));
					eduResultVO.setExamScore(Integer.parseInt(cellExamScore.getContents().trim()));
					eduResultVO.setAsmtScore(Integer.parseInt(cellAsmtScore.getContents().trim()));
					eduResultVO.setForumScore(Integer.parseInt(cellForumScore.getContents().trim()));
					eduResultVO.setPrjtScore(Integer.parseInt(cellPrjtScore.getContents().trim()));
					eduResultVO.setJoinScore(Integer.parseInt(cellJoinScore.getContents().trim()));
					eduResultVO.setEtcScore(Integer.parseInt(cellEtcScore.getContents().trim()));
					eduResultVO.setTotScore(Integer.parseInt(cellTotScore.getContents().trim()));

					//-- 기존 값 가져오기
					try{
						eduResultMapper.insertResult(eduResultVO);
					}
					catch (DuplicateKeyException e) {
						eduResultMapper.updateResult(eduResultVO);
					}
				}
			}

			ProcessResultVO<EduResultVO> resultVO = new ProcessResultVO<EduResultVO>();
			resultVO.setMessage(errMsg);
			resultVO.setResultSuccess();

			return resultVO;
		} catch (Exception e) {
			throw new ServiceProcessException("excel 파일 처리 실패", e);
		} finally {
			//-- 사용한 파일 지움.
			FileUtil.delFile(filePath, fileName);
		}
	}
	
	@Override
	public void listExcelDownloadForMngStd(EduResultVO vo, CreateCourseVO ccvo ,OutputStream os) throws Exception {
		List<EduResultVO> resultList = eduResultMapper.listExelStudentScore(vo);
		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);
			String refundYn = "Y".equals(ccvo.getRefundYn()) ? "(환급)" : "(비환급)"; 
			String title = ccvo.getCreTerm() +"기 " +  ccvo.getCrsCreNm() + refundYn + " 수강생 성적";
			
			WritableSheet sheet = workbook.createSheet(title, 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum, title)); //1열
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, 5, 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);
			
			rowNum++;
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"이름(ID)"));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,"시험"));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,"과제"));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,"진도"));
			sheet.addCell(ExcelUtil.createHeader(4,rowNum,"진행단게평가"));
			sheet.addCell(ExcelUtil.createHeader(5,rowNum,"합계"));

			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 15);

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 400);

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				EduResultVO result = resultList.get(i);

				sheet.addCell(ExcelUtil.createText(0,rowNum,"left",result.getUserNm() + "(" + result.getUserId() + ")"));
				sheet.addCell(ExcelUtil.createText(1,rowNum,"left",result.getExamScore() + "점"));
				sheet.addCell(ExcelUtil.createText(2,rowNum,"left",result.getAsmtScore() + "점"));
				sheet.addCell(ExcelUtil.createText(3,rowNum,"left",result.getPrgrScore() + "점"));
				sheet.addCell(ExcelUtil.createText(4,rowNum,"left",result.getSemiExamScore() + "점"));
				sheet.addCell(ExcelUtil.createText(5,rowNum,"left",result.getTotScore() + "점"));
				sheet.setRowView(rowNum, 300);
			}
			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","수강생이 존재하지 않습니다.")); //1열
				sheet.mergeCells(0, 2, 5, 0);
				sheet.setRowView(0, 500);
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}

	/**
	 * 과정 수강생의 성적을 자동으로 생성한다.
	 * 시험, 과제 등의 성적을 가져와 다시 셋팅한다.
	 * 임의로 입력된 값이 초기화 된다.
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<EduResultVO> addAutoResult(EduResultVO vo, StdEduRsltWorkLogVO lvo) throws Exception {
		//-- 작동 로그를 남기자.
		stdEduRsltWorkLogMapper.insert(lvo);
		eduResultMapper.insertEduResultCrsSp(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public ProcessResultVO<EduResultVO> transactionRollbackMethod(EduResultVO vo) throws Exception {
		this.addResult(vo,new StdEduRsltWorkLogVO());
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}
	
	/**
	 * 수강생 성적 프로시저(단일) 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@HrdApiStdStd(SyncType.UPDATE)
	@HrdApiScore
	public ProcessResultVO<EduResultVO> addEduResultSp(EduResultVO vo, Object param) {
		eduResultMapper.insertEduResultSp(vo);
		return ProcessResultVO.success();
	}
}
