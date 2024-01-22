package egovframework.edutrack.modules.student.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.impl.CreateCourseMapper;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.student.payment.service.PaymentVO;
import egovframework.edutrack.modules.student.payment.service.impl.PaymentMapper;
import egovframework.edutrack.modules.student.student.service.StudentExcelService;
import egovframework.edutrack.modules.student.student.service.StudentExcelVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.ValidateRollbackStudentException;
import egovframework.edutrack.modules.student.student.service.ValidateStudentException;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserAuthGrpMapper;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoChgHstyVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoChgHstyMapper;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrLoginMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 교육생 서비스중 엑셀과 관련된 처리를 전담하는 클래스.
 * StudentService에 트랜잭션을 일임하며, 일괄등록과 같은 특정 매소드에 Transaction을 명시한다.
 *
 * @author SungKook
 */
@Service("studentExcelService")
public class StudentExcelServiceImpl
		extends EgovAbstractServiceImpl implements StudentExcelService {


	/** Mapper */
	@Resource(name="usrLoginMapper")
	private UsrLoginMapper 		usrLoginMapper;
	
	@Resource(name="usrUserInfoChgHstyMapper")
	private UsrUserInfoChgHstyMapper 		usrUserInfoChgHstyMapper;

	@Resource(name="studentMapper")
	private StudentMapper 		studentMapper;
	
	@Resource(name="studentService")
	private StudentService 	 studentService;

	@Resource(name="usrUserAuthGrpMapper")	
	private UsrUserAuthGrpMapper 		usrUserAuthGrpMapper;
	
	@Resource(name="usrUserInfoMapper")
	private UsrUserInfoMapper 		usrUserInfoMapper;

	@Resource(name="sysCodeService")
	private SysCodeService			sysCodeService;

	@Resource(name="orgCodeService")
	private OrgCodeService 	 orgCodeService;
	
	@Resource(name="paymentMapper")
	private PaymentMapper paymentMapper;
	
	@Resource(name="createCourseMapper")
	private CreateCourseMapper createCourseMapper;
	
	/**
	 * 엑셀파일을 로딩하여 상태를 체크하고 리턴한다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultListVO<StudentExcelVO> listStudentExcel(String crsCreCd, String fileName,
			String filePath) throws ServiceProcessException, ValidateRollbackStudentException {

		ProcessResultListVO<StudentExcelVO> resultList = new ProcessResultListVO<StudentExcelVO>();

		Workbook workbook	= null;
		Sheet sheet			= null;

		String errMsg = "";

			try {
				workbook = Workbook.getWorkbook(new File(filePath + "/" + fileName));
			} catch (BiffException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			} catch (IOException ex1) {
				// TODO Auto-generated catch block
				ex1.printStackTrace();
			}
			sheet = workbook.getSheet(0);
			try {
		} catch (Exception e) {
			throw new ServiceProcessException("excel 파일 처리 실패 : " + e.getMessage(), e);
		}

		List<StudentExcelVO> studentExcelList = new ArrayList<StudentExcelVO>();
		for (int j = 3; j < sheet.getRows(); j++) {
			if(ValidationUtils.isNotEmpty(sheet.getCell(0, j).getContents().trim())) {
				//-- 4번째 라인부터 읽어 온다.
				StudentExcelVO sedto = new StudentExcelVO();
				sedto.setUserId(sheet.getCell(0, j).getContents().trim());
				sedto.setUserNm(sheet.getCell(1, j).getContents().trim());
				sedto.setUserPass(sheet.getCell(2, j).getContents().trim());
//				sedto.setSsn(sheet.getCell(3, j).getContents().trim());
				sedto.setCompNm(sheet.getCell(3, j).getContents().trim());
				sedto.setBirth(sheet.getCell(4, j).getContents().trim());
				sedto.setSexCd(sheet.getCell(5, j).getContents().trim());
				sedto.setEmail(sheet.getCell(6, j).getContents().trim());
				sedto.setMobileNo(sheet.getCell(7, j).getContents().trim());
				sedto.setHomePhoneno(sheet.getCell(8, j).getContents().trim());
				sedto.setHomeAddrDivCd(sheet.getCell(9, j).getContents().trim());
				sedto.setHomePostNo(sheet.getCell(10, j).getContents().trim());
				sedto.setHomeAddr1(sheet.getCell(11, j).getContents().trim());
				sedto.setHomeAddr2(sheet.getCell(12, j).getContents().trim());

				//-- userId로 사용자 정보를 가져온다.
				ProcessResultVO<UsrUserInfoVO> result = null;
				try{
					UsrUserInfoVO uidto = new UsrUserInfoVO();
					uidto.setUserId(sedto.getUserId());
					UsrUserInfoVO selUidto = usrUserInfoMapper.selectByUserId(uidto);
					if(ValidationUtils.isNotEmpty(selUidto)) {
						uidto = selUidto;
					}
					result.setResultSuccess();
					result.setReturnVO(uidto);
				}catch(Exception e){
					result.setResultFailed();
					result.setMessage(e.getMessage());
				}

				if(ValidationUtils.isEmpty(sedto.getUserId())) {
					sedto.setErrorCode("Err-1100"); //-- Validation Error 사용자 아이디 없음.
				} else if(ValidationUtils.isEmpty(sedto.getUserNm())) {
					sedto.setErrorCode("Err-1200"); //-- Validation Error 사용자 아이디 없음.
				} else if(ValidationUtils.isEmpty(sedto.getUserPass()) && result == null ) {
					sedto.setErrorCode("Err-1300"); //-- Validation Error 사용자 아이디 없음.
				} else {
					try {
						//-- userId로 사용자 정보를 가져온다.
						UsrUserInfoVO uidto = new UsrUserInfoVO();
						uidto.setUserId(sedto.getUserId());
						UsrUserInfoVO selUidto = usrUserInfoMapper.selectByUserId(uidto);
						if(ValidationUtils.isNotEmpty(selUidto)) {
							uidto = selUidto;
						}

						//-- 기존 아이디가 있는 경우 이름으로 동일 사용자 인지 확인한다.
						if(uidto.getUserNm().equals(sedto.getUserNm())) {
							sedto.setUserSts("OLD");
							StudentVO sdto = new StudentVO();
							sdto.setCrsCreCd(crsCreCd);
							sdto.setUserNo(uidto.getUserNo());
							sdto = studentMapper.isEnroll(sdto);
							//-- 기존 사용자인 경우 해당 개설 과정에 수강신청이 되어 있는지 확인한다.
							if("Y".equals(sdto.getStdYn())) {
								sedto.setErrorCode("Err-2100"); //-- 이미 수강신청이 되어 있습니다.
							}
						} else {
							sedto.setUserSts("EXT");
							sedto.setErrorCode("Err-2200"); //-- 기존에 등록된 아이디 입니다.
						}
					} catch (Exception e) {
						sedto.setUserSts("NEW"); //-- 기존 사용자 없음
					}
				}
				studentExcelList.add(sedto);
			}
		}
		resultList.setResult(1);
		resultList.setReturnList(studentExcelList);
		return resultList;
	}

	/**
	 * 교육생 엑셀 업로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultVO<StudentVO> addStudentExcel(List<StudentExcelVO> studentList, StudentVO sdto)
			throws ValidateStudentException {

		ProcessResultVO<StudentVO> resultVO = ProcessResultVO.success();
		try {

			for(StudentExcelVO sedto : studentList) {
				String userNo = "";
				//-- 사용자를 신규로 등록하여야 하는 경우 처리
				if("NEW".equals(sedto.getUserSts())) {
					UsrUserInfoVO uidto = new UsrUserInfoVO();
					try {
						BeanUtils.copyProperties(uidto, sedto);
					} catch (Exception ex) {
						throw new ValidateStudentException("학생정보 추출과정에서 오류 발생..");
					}
	
					uidto.setUserDivCd("C"); //-- 일반회원으로 셋팅
					//uidto.setRealnmCertYn("N"); //-- 실명인증 받지 않음.
					uidto.setEmailRecv("Y");
					uidto.setSmsRecv("Y");
					uidto.setMsgRecv("Y");
					uidto.setTempYn("Y"); //-- 임시 등록
					uidto.setUserNo(usrUserInfoMapper.selectKey());
					usrUserInfoMapper.insert(uidto);
	
					userNo = uidto.getUserNo();
	
					UsrLoginVO loginVO = new UsrLoginVO();
					loginVO.setUserNo(uidto.getUserNo());
					loginVO.setUserId(sedto.getUserId());
					loginVO.setUserPass(sedto.getUserPass());
					loginVO.setAdminLoginAcptDivCd("ACNT");
					loginVO.setUserSts("U");
					loginVO.setRegNo(sdto.getRegNo());
					loginVO.setModNo(sdto.getModNo());
					usrLoginMapper.insert(loginVO);
	
					UsrUserInfoChgHstyVO uichdto = new UsrUserInfoChgHstyVO();
					uichdto.setUserNo(uidto.getUserNo());
					uichdto.setRegNo(sdto.getRegNo());
					uichdto.setUserInfoChgDivCd("AI"); //-- 관리자 등록
					uichdto.setUserInfoCts(JsonUtil.getJsonString(uidto));
					usrUserInfoChgHstyMapper.insert(uichdto);
	
					//-- 사용자 회원 권한 부여.
					UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
					uagVO.setMenuType("HOME");
					uagVO.setAuthGrpCd("MEMBER");
					uagVO.setUserNo(uidto.getUserNo());
					uagVO.setRegNo(sdto.getRegNo());
					uagVO.setModNo(sdto.getModNo());
					usrUserAuthGrpMapper.insert(uagVO);
				} else {
					//-- 기존 사용자인 경우 사용자 정보 가져온다.
					UsrUserInfoVO uidto = new UsrUserInfoVO();
					uidto.setUserId(sedto.getUserId());
					UsrUserInfoVO selUidto = usrUserInfoMapper.selectByUserId(uidto);
					if(ValidationUtils.isNotEmpty(selUidto)) {
						uidto = selUidto;
					}
					userNo = uidto.getUserNo();
				}
	
				//-- 수강등록.
				StudentVO studto = new StudentVO();
				studto.setCrsCreCd(sdto.getCrsCreCd());
				studto.setUserNo(userNo);
				studto.setEnrlSts("S"); //-- 수강인증으로 셋팅
				studto.setPaymMthdCd("PAYM003");
				studto.setPaymStsCd("N");
				studto.setPaymPrice(0);
				studto.setRegNo(sdto.getRegNo());
	
				studentMapper.insertStudentSp(studto);
			}
		
		} catch (Exception ex) {
			throw new ValidateStudentException("엑셀 업로드 진행중 오류 발생..");
		}

		return resultVO;
	}

	/**
	 * 출석부 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public void listExcelDownloadAttend(StudentVO studentVO, CreateCourseVO createCourseVO, OutputStream os) throws ServiceProcessException {


		try {
			List<StudentVO> resultList = studentMapper.listStudent(studentVO);
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("출석부", 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center",createCourseVO.getCrsCreNm()+"("+createCourseVO.getCreTerm()+") 출석부 ")); //1열
			//-- 제목줄 병합
			//sheet.mergeCells(0, rowNum, 2+createCourseVO.getEnrlGapDate(), rowNum); //-- 병합
			sheet.mergeCells(0, rowNum, 18, rowNum); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 700);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"교번"));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,"이름"));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,"아이디"));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,"소속센터"));
			for(int i = 0; i < createCourseVO.getEnrlGapDate(); i++) {
				sheet.addCell(ExcelUtil.createHeader(4+i,rowNum,(i+1)+"일차"));
			}

			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			for(int i = 0; i < createCourseVO.getEnrlGapDate(); i++) {
				sheet.setColumnView(4+i, 30);
			}

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 600);

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				StudentVO sVO = resultList.get(i);

				String posngNm = StringUtil.nvl(sVO.getPosngNm());
				if(!"".equals(StringUtil.nvl(sVO.getPosnNm()))) posngNm = posngNm+"\n("+sVO.getPosnNm()+")";

				if(sVO.getEduNo() == null){
					sVO.setEduNo(0);
				}
				sheet.addCell(ExcelUtil.createNumber(0,rowNum,"center",sVO.getEduNo()));
				sheet.addCell(ExcelUtil.createText(1,rowNum,"center",sVO.getUserNm()));
				sheet.addCell(ExcelUtil.createText(2,rowNum,"center",sVO.getUserId()));
				sheet.addCell(ExcelUtil.createText(3,rowNum,"center",sVO.getDeptOrgNm()));
				for(int j = 0; j < createCourseVO.getEnrlGapDate(); j++) {
					sheet.addCell(ExcelUtil.createText(4+j,rowNum,"center",""));
					sheet.setColumnView(4+j, 10);
				}
				sheet.setRowView(rowNum, 500);
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}

	/**
	 * 수강생/수료생/신청자 명단 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public void listExcelDownload(StudentVO studentVO, CreateCourseVO createCourseVO, String sheetName, String labelName,
			String condition, String excelHeader, ArrayList<String> titleList, HttpServletRequest request, OutputStream os, HashMap<String, String> titles) throws ServiceProcessException {


		String[] headers = StringUtil.split(excelHeader, "@$");

		int colNum = headers.length - 1;

		String locale = UserBroker.getLocaleKey(request);

		try {
			List<StudentVO> resultList = studentMapper.listStudent(studentVO);
			
			
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, "users");

			// 페이지 제목줄 ..
			XSSFRow pageRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(labelName, pageRow, 0, colNum);
			rowNum++;

			// 출력일자 줄
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(condition, pageRow2, 0, colNum, "right");
			rowNum++;

			//-- header를 만든다.
			int fieldWidth = 400;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			for(int i=0; i < headers.length; i++) {
				String headerName = "";
				if("NO".equals(headers[i])) {
					headerName = titleList.get(0);
					fieldWidth = 200;
				} else if("DECLS".equals(headers[i])) {
					headerName = titleList.get(1);
					fieldWidth = 200;
				} else if("USERNM".equals(headers[i])) {
					headerName = titleList.get(2);
					fieldWidth = 500;
				} else if("USERID".equals(headers[i])) {
					headerName = titleList.get(3);
					fieldWidth = 500;
				} else if("EMAIL".equals(headers[i])) {
					headerName = titleList.get(4);
					fieldWidth = 800;
				} else if("BIRTH".equals(headers[i])) {
					headerName = titleList.get(5);
					fieldWidth = 400;
				} else if("SEX".equals(headers[i])) {
					headerName = titleList.get(6);
					fieldWidth = 400;
				} else if("PHONENO".equals(headers[i])) {
					headerName = titleList.get(7);
					fieldWidth = 500;
				} else if("MOBILENO".equals(headers[i])) {
					headerName = titleList.get(8);
					fieldWidth = 500;
				} else if("FAXNO".equals(headers[i])) {
					headerName = titleList.get(9);
					fieldWidth = 500;
				} else if("DEPT".equals(headers[i])) {
					headerName = titleList.get(10);
					fieldWidth = 400;
				} else if("USERNMKANA".equals(headers[i])) {
					headerName = titleList.get(11);
					fieldWidth = 500;
				} else if("USERNMENG".equals(headers[i])) {
					headerName = titleList.get(12);
					fieldWidth = 500;
				} else if("AREA".equals(headers[i])) {
					headerName = titleList.get(13);
					fieldWidth = 400;
				} else if("USERDIV".equals(headers[i])) {
					headerName = titleList.get(14);
					fieldWidth = 400;
				} else if("COMPPHONE".equals(headers[i])) {
					headerName = titleList.get(15);
					fieldWidth = 500;
				} else if("ETCPHONE".equals(headers[i])) {
					headerName = titleList.get(16);
					fieldWidth = 500;
				} else if("COMPADDR".equals(headers[i])) {
					headerName = titleList.get(17);
					fieldWidth = 1000;
				} else if("HOMEADDR".equals(headers[i])) {
					headerName = titleList.get(18);
					fieldWidth = 1000;
				} else if("DISABLILITY".equals(headers[i])) {
					headerName = titleList.get(19);
					fieldWidth = 400;
				} else if("INTEREST".equals(headers[i])) {
					headerName = titleList.get(20);
					fieldWidth = 400;
				} else if("MEMO".equals(headers[i])) {
					headerName = titleList.get(21);
					fieldWidth = 800;
				} else if("APLCDTTM".equals(headers[i])) {
					headerName = titleList.get(22);
					fieldWidth = 500;
				} else if("PAYMMTHD".equals(headers[i])) {
					headerName = titleList.get(23);
					fieldWidth = 400;
				} else if("PAYMPRICE".equals(headers[i])) {
					headerName = titleList.get(24);
					fieldWidth = 400;
				} else if("PAYMSTS".equals(headers[i])) {
					headerName = titleList.get(25);
					fieldWidth = 400;
				} else if("GETSCORE".equals(headers[i])) {
					headerName = titleList.get(26);
					fieldWidth = 200;
				} else if("SCOREECLT".equals(headers[i])) {
					headerName = titleList.get(27);
					fieldWidth = 200;
				} else if("CPLTNO".equals(headers[i])) {
					headerName = titleList.get(28);
					fieldWidth = 400;
				} else if("TOT_RATIO".equals(headers[i])) {
					headerName = titleList.get(29);
					fieldWidth = 200;
				} else if("TOT_SCORE".equals(headers[i])) {
					headerName = titleList.get(30);
					fieldWidth = 200;
				} else if("ENRLSTS".equals(headers[i])) {
					headerName = titleList.get(31);
					fieldWidth = 400;
				}
				sheet.setColumnWidth(i, sheet.getDefaultColumnWidth() * fieldWidth); // 비밀번호
				POIExcelUtil.createTitleCell(headerName, titleRow, i);
			}

			if(resultList.size() == 0) {
				rowNum++;
				XSSFRow nodataRow = sheet.createRow((short)rowNum);
				POIExcelUtil.createMergeCell("* There is no data.", nodataRow, 0, colNum, "center");
			}

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				StudentVO sVO = resultList.get(i);
				XSSFRow cntsRow = sheet.createRow((short)rowNum);

				for(int j = 0; j < headers.length; j++) {
					if("NO".equals(headers[j])) POIExcelUtil.createContentCell((i+1), cntsRow, j, "center");
					else if("DECLS".equals(headers[j])) POIExcelUtil.createContentCell(StringUtil.nvl(sVO.getDeclsNo(),1), cntsRow, j, "center");
					else if("USERNM".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getUserNm(), cntsRow, j, "left");
					else if("USERID".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getUserId(), cntsRow, j, "left");
					else if("EMAIL".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getEmail(), cntsRow, j, "left");
					else if("BIRTH".equals(headers[j])) {
						String birth = sVO.getBirth();
						if(ValidationUtils.isNotEmpty(birth)) {
							birth = DateTimeUtil.getDateType(1, birth);
						}
						POIExcelUtil.createContentCell(birth, cntsRow, j, "center");
					}
					else if("SEX".equals(headers[j])) {
						String sexNm = "";
						if("M".equals(sVO.getSexCd())){
							sexNm = titles.get("sexNmM");
						} else if("F".equals(sVO.getSexCd())) {
							sexNm = titles.get("sexNmF");
						}
						POIExcelUtil.createContentCell(sexNm, cntsRow, j, "center");
					}
					else if("PHONENO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getHomePhoneno(), cntsRow, j, "left");
					else if("MOBILENO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getMobileNo(), cntsRow, j, "left");
					else if("FAXNO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getCompFaxNo(), cntsRow, j, "left");
					else if("DEPT".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getDeptNm(), cntsRow, j, "center");
					else if("USERNMKANA".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getUserNmGana(), cntsRow, j, "left");
					else if("USERNMENG".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getUserNmEng(), cntsRow, j, "left");
					else if("AREA".equals(headers[j])){
						String areaNm = "";
						if(StringUtil.isNotNull(sVO.getUserAreaCd())){
							try {
								OrgCodeVO orgCodeVO =  new OrgCodeVO();
								orgCodeVO.setCodeCtgrCd("AREA_CD");
								orgCodeVO.setOrgCd(studentVO.getOrgCd());
								orgCodeVO.setCodeCd(sVO.getUserAreaCd());
								orgCodeVO = orgCodeService.viewCode(orgCodeVO);
								areaNm = orgCodeVO.getCodeNm();
//								for(OrgCodeLangVO cldto : orgCodeVO.getCodeLangList()) {
//									if(locale.equals(cldto.getLangCd())) areaNm = cldto.getCodeNm();
//								}
							} catch (Exception e) {
								areaNm = sVO.getUserAreaCd();
							}
						}
						POIExcelUtil.createContentCell(areaNm, cntsRow, j, "center");
					}
					else if("USERDIV".equals(headers[j])){
						String divNm = "";
						if(StringUtil.isNotNull(sVO.getUserDivCd())){
							try {
								OrgCodeVO orgCodeVO =  new OrgCodeVO();
								orgCodeVO.setCodeCtgrCd("USER_DIV_CD");
								orgCodeVO.setOrgCd(studentVO.getOrgCd());
								orgCodeVO.setCodeCd(sVO.getUserDivCd());
								orgCodeVO = orgCodeService.viewCode(orgCodeVO);
								divNm = orgCodeVO.getCodeNm();
//								for(OrgCodeLangVO cldto : orgCodeVO.getCodeLangList()) {
//									if(locale.equals(cldto.getLangCd())) divNm = cldto.getCodeNm();
//								}
							} catch (Exception e) {
								divNm = sVO.getUserDivCd();
							}
						}
						POIExcelUtil.createContentCell(divNm, cntsRow, j, "center");
					}
					else if("INTEREST".equals(headers[j])){
						String interestNm = "";
						if(StringUtil.isNotNull(sVO.getInterestFieldCd())){
							try {
								OrgCodeVO orgCodeVO =  new OrgCodeVO();
								orgCodeVO.setCodeCtgrCd("INTEREST_FIELD_CD");
								orgCodeVO.setOrgCd(studentVO.getOrgCd());
								orgCodeVO.setCodeCd(sVO.getInterestFieldCd());
								orgCodeVO = orgCodeService.viewCode(orgCodeVO);
								interestNm = orgCodeVO.getCodeNm();
//								for(OrgCodeLangVO cldto : orgCodeVO.getCodeLangList()) {
//									if(locale.equals(cldto.getLangCd())) interestNm = cldto.getCodeNm();
//								}
							} catch (Exception e) {
								interestNm = sVO.getInterestFieldCd();
							}
						}
						POIExcelUtil.createContentCell(interestNm, cntsRow, j, "center");
					}
					else if("COMPPHONE".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getCompPhoneno(), cntsRow, j, "left");
					else if("ETCPHONE".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getEtcPhoneno(), cntsRow, j, "left");
					else if("COMPADDR".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getCompAddr1(), cntsRow, j, "left");
					else if("HOMEADDR".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getHomeAddr1(), cntsRow, j, "left");
					else if("DISABLILITY".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getDisablilityYn(), cntsRow, j, "center");
					else if("MEMO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getMemo(), cntsRow, j, "left");
					else if("APLCDTTM".equals(headers[j])) POIExcelUtil.createContentCell(DateTimeUtil.getDateType(0, sVO.getEnrlAplcDttm()), cntsRow, j, "left");
					else if("PAYMMTHD".equals(headers[j])) {
						String payMthdNm = "";
						try {
							SysCodeVO codeVO = sysCodeService.viewCode("PAYM_MTHD_CD", sVO.getPaymMthdCd());
							payMthdNm = codeVO.getCodeNm();
							for(SysCodeLangVO cldto : codeVO.getCodeLangList()) {
								if(locale.equals(cldto.getLangCd())) payMthdNm = cldto.getCodeNm();
							}
						} catch (Exception e) {
							payMthdNm = sVO.getPaymMthdCd();
						}
						POIExcelUtil.createContentCell(payMthdNm, cntsRow, j, "center");
					}
					else if("PAYMPRICE".equals(headers[j])) {
						if("FREE".equals(sVO.getPaymMthdCd())) {
							POIExcelUtil.createContentCell("-", cntsRow, j, "right");
						} else {
							POIExcelUtil.createContentCell(StringUtil.nvl(sVO.getPaymPrice(),""), cntsRow, j, "right");
						}
					}
					else if("PAYMSTS".equals(headers[j])){
						String PaymStsNm = "";
						if("FREE".equals(sVO.getPaymMthdCd())) {
							PaymStsNm = "-";
						} else {
							if("Y".equals(sVO.getPaymStsCd())){
								PaymStsNm = titles.get("paymStsNmY");
							} else {
								PaymStsNm = titles.get("paymStsNmN");
							}
						}
						POIExcelUtil.createContentCell(PaymStsNm, cntsRow, j, "center");
					}
					else if("GETSCORE".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getTotScore(), cntsRow, j, "center");
					else if("SCOREECLT".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getScoreEcltYn(), cntsRow, j, "center");
					else if("CPLTNO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getCpltNo(), cntsRow, j, "left");
					else if("TOT_RATIO".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getTotRatio(), cntsRow, j, "center");
					else if("TOT_SCORE".equals(headers[j])) POIExcelUtil.createContentCell(sVO.getTotScore(), cntsRow, j, "center");
					else if("ENRLSTS".equals(headers[j])) {
						String enrlSts = "";
						try {
							SysCodeVO codeVO = sysCodeService.viewCode("ENRL_STS", sVO.getEnrlSts());
							enrlSts = codeVO.getCodeNm();
							for(SysCodeLangVO cldto : codeVO.getCodeLangList()) {
								if(locale.equals(cldto.getLangCd())) enrlSts = cldto.getCodeNm();
							}
						} catch (Exception  e) {
							enrlSts = sVO.getEnrlSts();
						}
						 POIExcelUtil.createContentCell(enrlSts, cntsRow, j, "center");
					}
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
		} catch (Exception e) {
			throw new ServiceProcessException("Excel file creation failed!", e);
		}
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> addStudentPaymentExcel(StudentVO vo, String fileName, String filePath) {

		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>(new StudentVO());
		resultVO.setResultSuccess();
		
		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath + "/" + fileName);
			workbook =  new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (NotOfficeXmlFileException noxfe) {
			throw new ServiceProcessException("엑셀파일만 업로드 가능합니다.");
		} catch (IOException ex2) {
			throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
		} finally{
			try {
				if (fis != null) {	fis.close(); }
			} catch (Exception e) {
				throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
			}
		}
		
		XSSFRow dfltRow = sheet.getRow(1);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		
		int rowNum = 0;
		int rows = sheet.getPhysicalNumberOfRows();//행 갯수
		
		if(rows == 2) {
			throw new ServiceProcessException("3번째 줄부터 정보 입력 바랍니다.\n혹은 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");//1번째 줄 : 설명 , 2번째 줄 : 제목줄 , 3번째 줄부터 입력
		}
		
		for(int rowIndex = 2; rowIndex < rows; rowIndex++) {
			
			XSSFRow row=sheet.getRow(rowIndex);
			cellCount = row.getPhysicalNumberOfCells();
			
			if(cellCount < 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n회원아이디, 개설과정코드는 필수 값입니다.");
			} else if(cellCount > 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n엑셀 업로드는 회원아이디, 개설과정코드만 입력가능합니다. 그 외 다른 내용, 공백을 입력했거나 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");
			}
			
			String userId = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(0)));
			String crsCreCd = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(1)));
			
			if("".equals(userId) || "".equals(crsCreCd)) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n회원 아이디, 개설과정코드는 필수 값입니다.");
			}
			
			/*if(!crsCreCd.startsWith("CE") || crsCreCd.length() != 10) {
				throw new ServiceProcessException((rowIndex + 1) + "라인 (개설과정코드 : " + crsCreCd + ")\n개설과정코드의 형태나 길이가 올바르지 않습니다. 개설과정코드를 확인바랍니다.");
			}*/
			
			//userId로 유효한 회원인지 조회
			UsrUserInfoVO infoVO = new UsrUserInfoVO();
			infoVO.setUserId(userId);
			infoVO.setOrgCd(vo.getOrgCd());
			UsrUserInfoVO resultInfoVO = usrUserInfoMapper.selectUserCheckByUserIdOrgCd(infoVO);
			
			if(resultInfoVO == null) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n사용중인 상태의 회원 정보가 없습니다. 회원 정보(회원 가입 여부, 상태 등)를 확인바랍니다.");
			} else if(ValidationUtils.isEmpty(resultInfoVO.getUserNo())) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n사용중인 상태의 회원 정보가 없습니다. 회원 정보(회원 가입 여부, 상태 등)를 확인바랍니다.");
			} else if(ValidationUtils.isEmpty(resultInfoVO.getDeptCd())) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n회원의 기업정보가 없습니다. 회원 정보를 확인바랍니다.");
			}
			
			//crsCreCd로 개설과정 및 기수 기간 조회
			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setCrsCreCd(crsCreCd);
			createCourseVO = createCourseMapper.selectCreateCourseForEnroll(createCourseVO);
			
			if(createCourseVO  == null) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n개설과정이 조회되지 않습니다. 유효한 개설과정 코드인지 확인바랍니다");
			} else if(ValidationUtils.isEmpty(createCourseVO.getCrsCreCd())) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n개설과정이 조회되지 않습니다. 유효한 개설과정 코드인지 확인바랍니다");
			} 
			//else if(!resultInfoVO.getDeptCd().equals(createCourseVO.getDeptCd())){ throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n개설과정의 기업과 회원의 기업이 일치하지 않습니다. 개설과정과 회원정보를 확인바랍니다.");} 
			else if(ValidationUtils.isEmpty(createCourseVO.getEnrlAplcStartDttm()) 
					|| (ValidationUtils.isEmpty(createCourseVO.getEnrlAplcEndDttm()))
					|| (ValidationUtils.isEmpty(createCourseVO.getEnrlStartDttm()))
					|| (ValidationUtils.isEmpty(createCourseVO.getEnrlEndDttm()))
					//|| (ValidationUtils.isEmpty(createCourseVO.getTermEndDttm()))
					) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n개설과정의 기수 등록기간, 수강기간, 종강일 입력을 확인바랍니다.");
			} /*
				 * else if(createCourseVO.getEduPrice() == null || createCourseVO.getEduPrice()
				 * == 0) { throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " +
				 * userId + ", 개설과정코드 : " + crsCreCd +
				 * ")\n개설과정의 가격 정보는 0 보다 커야 합니다. 개설과정 정보를 확인바랍니다."); }
				 */
			
			//userNo와 crsCreCd로 수강중인 강의 확인
			StudentVO stuVO = new StudentVO();
			stuVO.setUserNo(resultInfoVO.getUserNo());
			stuVO.setCrsCreCd(crsCreCd);
			
			if("Y".equals(studentMapper.isEnroll(stuVO).getStdYn())){
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n" + "회원이 해당 강의 이미 수강신청하였습니다.\n회원의 수강정보 확인바랍니다.\n이상이 없는 경우 업로드 엑셀 파일에 동일한 내용을 여러 번 작성했는지 확인바랍니다.");
			}
			
			//관리자 입금
			PaymentVO paymentVO = new PaymentVO();
			String paymNo = paymentMapper.selectKey();
			paymentVO.setPaymNo(paymNo);
			paymentVO.setPaymMthdCd("PAYM004");//관리자입금
			paymentVO.setUserNo(resultInfoVO.getUserNo());
			paymentVO.setPaymStsCd("DF");//입금완료
			paymentVO.setPaymPrice(createCourseVO.getEduPrice());
			//paymentVO.setPaymDttm("");//현재일
			paymentVO.setRegIp(vo.getRegIp());
			paymentVO.setRegNo(vo.getRegNo());
			paymentMapper.insertPayInfo(paymentVO);
			
			//수강생 등록
			stuVO.setStdNo(studentMapper.selectKey());
			stuVO.setDeclsNo(1);
			stuVO.setUserNo(resultInfoVO.getUserNo());
			stuVO.setEnrlSts("S");
			stuVO.setEnrlStartDttm(createCourseVO.getEnrlStartDttm());
			stuVO.setEnrlEndDttm(createCourseVO.getEnrlEndDttm());
			stuVO.setAuditEndDttm(createCourseVO.getTermEndDttm());
			//stuVO.setEnrlAplcDttm("");
			stuVO.setPaymNo(paymNo);
			stuVO.setOrgCd(vo.getOrgCd());
			stuVO.setRegNo(vo.getRegNo());
			stuVO.setStdPrice(createCourseVO.getEduPrice());
			studentService.addStudentForHrdApi(stuVO);
			resultVO.getReturnVO().getStdNoList().add(stuVO.getStdNo());
		}

		return resultVO;
	}
	
	/**
	 * [HRD] 회차>수강생관리>IDE엑셀업로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> addStudentIdeUrlExcel(StudentVO vo, String fileName, String filePath) {

		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>(new StudentVO());
		resultVO.setResultSuccess();
		
		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath + "/" + fileName);
			workbook =  new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (NotOfficeXmlFileException noxfe) {
			throw new ServiceProcessException("엑셀파일만 업로드 가능합니다.");
		} catch (IOException ex2) {
			throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
		} finally{
			try {
				if (fis != null) {	fis.close(); }
			} catch (Exception e) {
				throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
			}
		}
		
		XSSFRow dfltRow = sheet.getRow(1);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		
		int rowNum = 0;
		int rows = sheet.getPhysicalNumberOfRows();//행 갯수
		
		if(rows == 2) {
			throw new ServiceProcessException("3번째 줄부터 정보 입력 바랍니다.\n혹은 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");//1번째 줄 : 설명 , 2번째 줄 : 제목줄 , 3번째 줄부터 입력
		}
		
		for(int rowIndex = 2; rowIndex < rows; rowIndex++) {
			
			XSSFRow row=sheet.getRow(rowIndex);
			cellCount = row.getPhysicalNumberOfCells();
			
			if(cellCount < 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n회원아이디, IDE URL은 필수 값입니다.");
			} else if(cellCount > 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n엑셀 업로드는 회원아이디, IDE URL만 입력가능합니다. 그 외 다른 내용, 공백을 입력했거나 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");
			}
			
			String userId = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(0)));
			String ideUrl = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(1)));
			String crsCreCd = StringUtil.nvl(vo.getCrsCreCd());
			
			if(!"".equals(userId) && !"".equals(ideUrl) ) {	
				if("".equals(userId) || "".equals(ideUrl)) {
					throw new ServiceProcessException((rowIndex + 1) + "라인\n회원 아이디, IDE URL은 필수 값입니다.");
				}			

				//userId로 stdNo조회
				vo.setUserId(userId);
				StudentVO resultInfoVO = studentMapper.selectStdNo(vo);

				if(resultInfoVO == null) {
					throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n수강중인 회원 정보가 없습니다. 수강 정보(수강 여부, 상태 등)를 확인바랍니다.");
				} else if(ValidationUtils.isEmpty(resultInfoVO.getStdNo())) {
					throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n수강중인 회원 정보가 없습니다. 수강 정보(수강 여부, 상태 등)를 확인바랍니다.");
				}	
			
				//IDE URL 유무체크
				vo.setIdeUrl(ideUrl);
				int result = studentMapper.selectCreIdeUrl(vo);
				
				if(result == 0) {
					throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId  + ", 개설과정코드 : " + crsCreCd + ")\n 해당 IDE URL은 없는 주소입니다. 확인바랍니다.");
				}

				//IDE URL 업데이트
				StudentVO stuVO = new StudentVO();
				stuVO.setStdNo(resultInfoVO.getStdNo());
				stuVO.setCrsCreCd(crsCreCd);
				stuVO.setIdeUrl(ideUrl);
				stuVO.setModNo(vo.getModNo());
				
				studentService.addStudentIdeUrl(stuVO);
				resultVO.getReturnVO();
			}		
		}

		return resultVO;
	}
	
	/**
	 * 자격증 응시생 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(HashMap<String, String> titles, OutputStream os) throws ServiceProcessException {
		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, "Student");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info1"), pageRow1, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info2"), pageRow2, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info3"), pageRow3, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info4"), pageRow4, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow5 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info5"), pageRow5, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow6 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info6"), pageRow6, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow7 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info7"), pageRow7, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow8 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info8"), pageRow8, 0, 2, "left");
			rowNum++;
			XSSFRow pageRow9 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("info9"), pageRow9, 0, 2, "left");
			rowNum++;
			
			//-- 컬럼 제목줄 만들기
			XSSFRow titleRow = sheet.createRow((short)rowNum);

			POIExcelUtil.createTitleCell(titles.get("userId"), titleRow, 0);
			POIExcelUtil.createTitleCell(titles.get("certScore"), titleRow, 1);
			POIExcelUtil.createTitleCell(titles.get("certPassYn"), titleRow, 2);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1200);

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
	
	/**
	 * 자격증 응시생 엑셀업로드
	 * @throws ServiceProcessException
	 */
	public ProcessResultListVO<StudentExcelVO> excelUploadValidationCheck(String fileName,
			String filePath) throws ServiceProcessException {

		ProcessResultListVO<StudentExcelVO> resultVO = new ProcessResultListVO<StudentExcelVO>();

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
		XSSFRow dfltRow = sheet.getRow(9);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		
		int rowNum = 0;
		int rows = sheet.getPhysicalNumberOfRows();//행 갯수
		
		if(rows <= 10) {
			resultVO.setResult(-1);
			resultVO.setMessage("ERROR_CNT");
			throw new ServiceProcessException("11번째 줄부터 정보 입력 바랍니다.\n혹은 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");//
		} else {
			List<StudentExcelVO> certStudentList = new ArrayList<StudentExcelVO>();
			String regex = "^[a-zA-Z가-힣0-9,|]*$";
			rows=sheet.getPhysicalNumberOfRows();
			int lineNo = 0;
			for (int rowIndex = 10; rowIndex < rows; rowIndex++) {
			    XSSFRow row=sheet.getRow(rowIndex);
			    if(row != null) {
			    	lineNo++;
			    	StudentExcelVO sevo = new StudentExcelVO();
			    	sevo.setLineNo(lineNo+"");
					sevo.setUserId(POIExcelUtil.getCellValue(row.getCell(0)));
					sevo.setCertScore(Integer.valueOf(POIExcelUtil.getCellValue(row.getCell(1),true)));
					sevo.setCertPassYn(POIExcelUtil.getCellValue(row.getCell(2),true));
					certStudentList.add(sevo);
			    }
			}
			resultVO.setReturnList(certStudentList);
			resultVO.setResult(1);
		}
		return resultVO;
	}
	
	/**
	 * 자격증 응시생 엑셀 업로드 수강데이터 등록
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultVO<StudentVO> addCertStudentExcel(List<StudentExcelVO> studentList, StudentVO sdto)
			throws ValidateStudentException {

		ProcessResultVO<StudentVO> resultVO = ProcessResultVO.success();
		try {

			for(StudentExcelVO sedto : studentList) {
				String userNo = "";
				//-- 기존 사용자인 경우 사용자 정보 가져온다.
				UsrUserInfoVO uidto = new UsrUserInfoVO();
				uidto.setUserId(sedto.getUserId());
				UsrUserInfoVO selUidto = usrUserInfoMapper.selectByUserId(uidto);
				if(ValidationUtils.isNotEmpty(selUidto)) {
					uidto = selUidto;
				}
				userNo = uidto.getUserNo();
	
				//-- 수강등록.
				StudentVO studto = new StudentVO();
				studto.setStdNo(studentMapper.selectKey());
				studto.setCrsCreCd(sdto.getCrsCreCd());
				studto.setEnrlStartDttm(sdto.getEnrlStartDttm());
				studto.setEnrlEndDttm(sdto.getEnrlEndDttm());
				studto.setCertScore(sedto.getCertScore());
				studto.setCertPassYn(sedto.getCertPassYn());
				studto.setUserNo(userNo);
				studto.setEnrlSts("S"); //-- 수강인증으로 셋팅
				studto.setRegNo(sdto.getRegNo());
				studentMapper.insertStudent(studto);
			}
			
			resultVO.setMessage("응시생 등록이 완료되었습니다. 등록된 응시생수 : "+studentList.size()+"명");
		
		} catch (Exception ex) {
			throw new ValidateStudentException("엑셀 업로드 진행중 오류 발생..");
		}

		return resultVO;
	}

}
