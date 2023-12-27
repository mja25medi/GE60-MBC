package egovframework.edutrack.modules.user.dept.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.code.service.impl.OrgCodeMapper;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.code.service.impl.SysCodeMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>사용자 - 사용자 부서 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("usrDeptInfoService")
public class UsrDeptInfoServiceImpl 
	extends EgovAbstractServiceImpl implements UsrDeptInfoService {
   
	/** Mapper */
	@Resource(name="usrDeptInfoMapper")
	private UsrDeptInfoMapper 		usrDeptInfoMapper;
	@Resource(name="orgCodeMapper")
	private OrgCodeMapper 		orgCodeMapper;
    @Resource(name="sysCodeMapper")
    private SysCodeMapper sysCodeMapper;

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
    @Resource(name="sysCodeService")
    private SysCodeService 			sysCodeService;
    @Resource(name="usrDeptInfoService")
    private UsrDeptInfoService		usrDeptInfoService;
	
	private final class NestedFileHandler 
	implements FileHandler<UsrDeptInfoVO> {
	
	@Override
	public String getPK(UsrDeptInfoVO vo){
		return vo.getDeptCd().toString();
	}

	@Override
	public String getRepoCd() {
		return "USR_PHOTO";
	}

	@Override
	public List<SysFileVO> getFiles(UsrDeptInfoVO vo){
		List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		if(ValidationUtils.isNotZeroNull(vo.getBsnsLcnsFileSn()))
			fileList.add(vo.getBsnsLcnsFile());
		return fileList;
	}

	@Override
	public UsrDeptInfoVO setFiles(UsrDeptInfoVO vo, FileListVO fileListVO) {
		vo.setBsnsLcnsFile(fileListVO.getFile("usrPhoto"));
		return vo;
	}
}
	
	/**
	 *  기관 정보 전체 목록을 조회한다.
	 * @param UsrDeptInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<UsrDeptInfoVO> list(UsrDeptInfoVO vo) throws Exception {
		ProcessResultListVO<UsrDeptInfoVO> resultList = new ProcessResultListVO<UsrDeptInfoVO>(); 
		try {
			List<UsrDeptInfoVO> deptInfoList =  usrDeptInfoMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(deptInfoList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param UsrDeptInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<UsrDeptInfoVO> listPageing(UsrDeptInfoVO vo) throws Exception {
		ProcessResultListVO<UsrDeptInfoVO> resultList = new ProcessResultListVO<UsrDeptInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = usrDeptInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UsrDeptInfoVO> deptInfoList =  usrDeptInfoMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(deptInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	

	/**
	 * 기관 정보 상세 정보를 조회한다.
	 * @param UsrDeptInfoVO
	 * @return UsrDeptInfoVO
	 * @throws Exception
	 */
	@Override
	public UsrDeptInfoVO view(UsrDeptInfoVO vo) throws Exception {
		vo = usrDeptInfoMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	@Override
	public UsrDeptInfoVO search(UsrDeptInfoVO vo) throws Exception {
		return usrDeptInfoMapper.select(vo);
	}
	
	/**
	 * 기관 정보 정보를 등록한다.
	 * @param UsrDeptInfoVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(UsrDeptInfoVO vo) throws Exception {
		//-- 키 정보가 없을 경우 생성
		if(ValidationUtils.isEmpty(vo.getDeptCd())) {
			vo.setDeptCd(usrDeptInfoMapper.selectKey());
		}
		setDateConvert(vo);
		int insert = usrDeptInfoMapper.insert(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return insert;
	}	
	
	/**
	 * 기관 정보 정보를 수정한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(UsrDeptInfoVO vo) throws Exception {
		setDateConvert(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return usrDeptInfoMapper.update(vo);
	}
	
	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(UsrDeptInfoVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler());
		return usrDeptInfoMapper.delete(vo);
	}

	/**
	 * 사용자 아이디 중복 체크
	 * @param UsrLoginVO
	 * @return  ProcessResultDTO
	 */
	@Override
	public String deptCdCheck(UsrDeptInfoVO vo) throws Exception {
		return (String)usrDeptInfoMapper.selectDeptCdCheck(vo);
	}
	
	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(OutputStream os) throws Exception {
		int rowNum = 0;
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet();

		//-- 첫번째 시트명 셋팅
		wbook.setSheetName(0, "기업 일괄 등록");

		XSSFRow pageRow = sheet.createRow((short)rowNum);
		// 페이지 제목줄 ..
		POIExcelUtil.createPageTitleCell("기업 일괄 등록", pageRow, 0, 9);
		
		rowNum++;
		XSSFRow descriptionRow = sheet.createRow((short)rowNum);
		String description = "* 샘플 데이터와 같은 형식으로 샘플 파일에 작성하여 업로드해야 정상적으로 등록됩니다." + System.lineSeparator() 
				+ "* 유효한 사업자 등록 번호를 입력해 주십시오." + System.lineSeparator()
				+ "* 추가적인 기업 정보의 등록 및 수정은 기업관리 메뉴에서 할 수 있습니다." + System.lineSeparator()
				+ "* 기업종류 코드, 업종 코드 등은 코드 목록 시트에서 확인 할 수 있습니다.";
		POIExcelUtil.createMergeCell(description, descriptionRow, 0, 9, "left");
		descriptionRow.setHeight((short)(sheet.getDefaultRowHeight() * 6));
	

		//-- 타이틀 만들기
		rowNum++;
		rowNum++;
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("사용여부(사용:Y,미사용:N)",titleColumnRow,0);
		POIExcelUtil.createTitleCell("기업종류 코드",titleColumnRow,1);
		POIExcelUtil.createTitleCell("법인명",titleColumnRow,2);
		POIExcelUtil.createTitleCell("대표자명",titleColumnRow,3);
		POIExcelUtil.createTitleCell("이메일",titleColumnRow,4);
		POIExcelUtil.createTitleCell("전화번호",titleColumnRow,5);
		POIExcelUtil.createTitleCell("사업자등록번호",titleColumnRow,6);
		POIExcelUtil.createTitleCell("우편번호",titleColumnRow,7);
		POIExcelUtil.createTitleCell("주소",titleColumnRow,8);
		POIExcelUtil.createTitleCell("상세주소",titleColumnRow,9);

		//-- 셀의 넓이 조절
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 800);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 500);

		rowNum++;
		XSSFRow contentRow = sheet.createRow((short)rowNum);
		
		POIExcelUtil.createContentCell("Y", contentRow, 0, "left");
		POIExcelUtil.createContentCell("D03", contentRow, 1, "left");
		POIExcelUtil.createContentCell("샘플기업", contentRow, 2, "left");
		POIExcelUtil.createContentCell("홍길동", contentRow, 3, "left");
		POIExcelUtil.createContentCell("test12@gmail.com", contentRow, 4, "left");
		POIExcelUtil.createContentCell("02-1111-2345", contentRow, 5, "left");
		POIExcelUtil.createContentCell("1234567891", contentRow, 6, "left");
		POIExcelUtil.createContentCell("01234", contentRow, 7, "left");
		POIExcelUtil.createContentCell("서울특별시 중랑구 망우로 353 C동 9층", contentRow, 8, "left");
		POIExcelUtil.createContentCell("901호", contentRow, 9, "left");
		
		sheet = wbook.createSheet();
		wbook.setSheetName(1, "코드 목록");
		
		rowNum = 0;
		XSSFRow codetitleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("기업종류 코드",codetitleColumnRow,0);
		POIExcelUtil.createTitleCell("코드",codetitleColumnRow,1);

		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 700);
		
		List<SysCodeVO> deptTypeCdList = sysCodeService.listCode("DEPT_TYPE_CD").getReturnList();
		
		rowNum = 1;
		for(SysCodeVO code : deptTypeCdList) {
			contentRow = sheet.createRow(rowNum);
			POIExcelUtil.createContentCell(code.getCodeNm(), contentRow, 0, "center");
			POIExcelUtil.createContentCell(code.getCodeCd(), contentRow, 1, "center");
			rowNum++;
		}
		wbook.write(os);
	}
	/**
	 * Upload 사용자 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultListVO<UsrDeptInfoVO> excelUploadValidationCheck(
			String fileName, String filePath, String orgCd) throws Exception {

		ProcessResultListVO<UsrDeptInfoVO> resultDTO = new ProcessResultListVO<UsrDeptInfoVO>();

		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;

		try {
			FileInputStream fis=new FileInputStream(filePath + "/" + fileName);
			workbook= new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (IOException ex2) {
			// TODO Auto-generated catch block
			ex2.printStackTrace();
			throw new Exception("Failed read excel : " + ex2.getMessage(), ex2);
		}

		String regex = "^[0-9,-]*$";
		boolean areaCd = false;
		List<UsrDeptInfoVO> deptList = new ArrayList<UsrDeptInfoVO>();

		//-- 셀 체크를 통해서 올바른 파일인지 확인한다.
		XSSFRow dfltRow = sheet.getRow(1);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		if(cellCount != 6) {
			resultDTO.setResult(-1);
			resultDTO.setMessage("ERROR_CNT");
			throw new Exception("Failed read excel : Invalid file.");
		} else {
			int rows=sheet.getPhysicalNumberOfRows();
			for(int rowindex=2; rowindex<rows; rowindex++) {
				XSSFRow row=sheet.getRow(rowindex);
			    if(row != null) {
			    	String errorCode = "";
			    	UsrDeptInfoVO dpdto = new UsrDeptInfoVO();
					dpdto.setLineNo(POIExcelUtil.getCellValue(row.getCell(0),true));
					dpdto.setDeptNm(POIExcelUtil.getCellValue(row.getCell(1)));
					dpdto.setDeptAddr(POIExcelUtil.getCellValue(row.getCell(2)));
					dpdto.setAreaNm(POIExcelUtil.getCellValue(row.getCell(3)));
					dpdto.setPhoneno(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(4))));
					dpdto.setFaxNo(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(5))));

					if(ValidationUtils.isNotEmpty(dpdto.getDeptNm()) || ValidationUtils.isNotEmpty(dpdto.getDeptAddr())) {
						//-- 부서명과 부서주소중 하나라도 있으면...
						if(ValidationUtils.isEmpty(dpdto.getDeptNm())) {
							errorCode +="|EMPTYDEPTNM";
						}
						if(dpdto.getDeptNm().length() > 50) {
							errorCode +="|TYPEDEPTNM";
						}
						if(ValidationUtils.isEmpty(dpdto.getDeptAddr())) {
							errorCode += "|EMPTYDEPTADDR";
						}
						if(dpdto.getDeptAddr().length() > 500) {
							errorCode +="|TYPEDEPTADDR";
						}
						if(ValidationUtils.isEmpty(dpdto.getPhoneno())) {
							errorCode += "|EMPTYPHONENO";
						}
						/*if(ValidationUtils.isEmpty(dpdto.getFaxNo())) {
							errorCode += "|EMPTYFAXNO";
						}*/
						if(!dpdto.getPhoneno().matches(regex)){
							errorCode += "|TYPEPHONENO";
						}
						if(dpdto.getPhoneno().length() > 14) {
							errorCode +="|TYPEPHONENO";
						}
						if(ValidationUtils.isNotEmpty(dpdto.getFaxNo()) && !dpdto.getFaxNo().matches(regex)){
							errorCode += "|TYPEFAXNO";
						}
						if(ValidationUtils.isNotEmpty(dpdto.getFaxNo()) && dpdto.getFaxNo().length() > 14){
							errorCode += "|TYPEFAXNO";
						}

						//List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd,"AREA_CD").getReturnList();
						OrgCodeVO codeVO = new OrgCodeVO();
						codeVO.setCodeCtgrCd("AREA_CD");
						codeVO.setOrgCd(orgCd);
						List<OrgCodeVO> areaCode = orgCodeMapper.list(codeVO);

						for(OrgCodeVO sdto : areaCode) {
							if(sdto.getCodeNm().equals(dpdto.getAreaNm().trim()) ){
								areaCd = true;
								dpdto.setAreaCd(sdto.getCodeCd());
							}
						}
						if(!areaCd){
							errorCode += "|TYPEAREACD";
						}
						if(ValidationUtils.isEmpty(dpdto.getAreaCd())) {
							errorCode += "|EMPTYAREACD";
						}
						dpdto.setErrorCode(errorCode);
						deptList.add(dpdto);
					}
			    }
			}

			resultDTO.setReturnList(deptList);
			resultDTO.setResult(1);
		}
		return resultDTO;
	}
	/**
	 * 일반 사용자 일괄 등록
	 * @param List<OrgCodeVO> codeList
	 * @return
	 */
	@Override
	public ProcessResultVO<UsrDeptInfoVO> addDeptBatch(	List<UsrDeptInfoVO> deptList) throws Exception {
		for(UsrDeptInfoVO dto : deptList){
			usrDeptInfoMapper.insert(dto);
		}
		return new ProcessResultVO<UsrDeptInfoVO>().setResultSuccess().setReturnVO(new UsrDeptInfoVO());
	}
	/**
	 * 엑셀 파일을 통한 기업 등록
	 * @param UsrDeptInfoVO
	 * @param String
	 * @param String
	 * @param String
	 * @return ProcessResultListVO<UsrDeptInfoVO>
	 */
	@SuppressWarnings("resource")
	@Override
	public ProcessResultListVO<UsrDeptInfoVO> excelUploadDeptAdd(UsrDeptInfoVO vo, String fileName, String filePath, String orgCd) throws Exception {
		int mainSheetIndex = 0;
		int titleColumnCount = 10;
		int titleRowIndex = 3;
		int contentStartRowIndex = 4;
		
		ProcessResultListVO<UsrDeptInfoVO> resultVO = new ProcessResultListVO<UsrDeptInfoVO>();
	
		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		FileInputStream fis = null;
		fis= new FileInputStream(filePath + "/" + fileName);
		workbook= new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(mainSheetIndex);

		XSSFRow titleRow = sheet.getRow(titleRowIndex);
		int cellCount = titleRow.getPhysicalNumberOfCells();
		if(cellCount != titleColumnCount) {
			throw new ServiceProcessException("엑셀 컬럼 수 오류");
		}
		
		List<UsrDeptInfoVO> deptList = new ArrayList<>();
		int contentsRows = sheet.getPhysicalNumberOfRows() -2;
		int rowCount = 0;
		for(int rowIndex = contentStartRowIndex; rowCount < contentsRows; rowCount++, rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row != null) {
				UsrDeptInfoVO dept = getDeptInfoFromRow(row);
				validateDeptInfo(dept);
				dept.setRegNo(vo.getRegNo());
				dept.setModNo(vo.getRegNo());
				dept.setOrgCd(orgCd);
				usrDeptInfoService.add(dept);
				
				deptList.add(dept);
			}
		}
		resultVO.setResult(1);
		resultVO.setReturnList(deptList);
		
		return resultVO;
	}
	
	// 셀에서 기업 정보 얻기
	private UsrDeptInfoVO getDeptInfoFromRow(XSSFRow row) {
		UsrDeptInfoVO deptVO = new UsrDeptInfoVO();
		deptVO.setLineNo("" + (row.getRowNum() - 3));
		deptVO.setUseYn(POIExcelUtil.getCellValue(row.getCell(0)));
		deptVO.setDeptTypeCd(POIExcelUtil.getCellValue(row.getCell(1)));
		deptVO.setDeptNm(POIExcelUtil.getCellValue(row.getCell(2)));
		deptVO.setCeoNm(POIExcelUtil.getCellValue(row.getCell(3)));
		deptVO.setDeptEmail(POIExcelUtil.getCellValue(row.getCell(4)));
		deptVO.setPhoneno(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(5))).replaceAll("-", ""));
		deptVO.setRegNum(POIExcelUtil.getCellValue(row.getCell(6)));
		deptVO.setDeptPostNo(POIExcelUtil.getCellValue(row.getCell(7)));
		deptVO.setDeptAddr1(POIExcelUtil.getCellValue(row.getCell(8)));
		deptVO.setDeptAddr2(POIExcelUtil.getCellValue(row.getCell(9)));

		return deptVO;
	}
	
	// 기업 정보 유효성 검사
	private void validateDeptInfo(UsrDeptInfoVO vo) throws Exception{
		String emailChkRegx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		SysCodeVO chkCodeVO = new SysCodeVO();
		
		String lineNo = vo.getLineNo();
		String useYn = StringUtil.nvl(vo.getUseYn());
		String deptEmail = vo.getDeptEmail();
		String regNum = vo.getRegNum();
		String deptTypeCd = vo.getDeptTypeCd();
		
		if(!"YN".contains(useYn)) {
			throw new ServiceProcessException(String.format("잘못된 사용여부 값 : [%S]행 (%s)", lineNo, useYn));
		}
		if(!Pattern.matches(emailChkRegx, deptEmail)) {
			throw new ServiceProcessException(String.format("잘못된 이메일 형식 : [%S]행 (%s)", lineNo, deptEmail));
		}
		if(!checkRegNum(regNum)) {
			throw new ServiceProcessException(String.format("잘못된 사업자등록번호 형식 : [%S]행 (%s)", lineNo, regNum));
		}
		
		chkCodeVO.setCodeCtgrCd("DEPT_TYPE_CD");
		chkCodeVO.setCodeCd(deptTypeCd);
		if(sysCodeMapper.select(chkCodeVO) == null) {
			throw new ServiceProcessException(String.format("잘못된 기업종류 코드 : [%S]행 (%s)", lineNo, deptTypeCd));
		}		
	}
	
	// 사업자 등록번호 유효성 검사
	private boolean checkRegNum(String regNumber) {
		if("0123456789".equals(regNumber)) return true;
		
		int[] keyNumArr = {1,3,7,1,3,7,1,3,5};
		char[] cNumArr = regNumber.toCharArray();
		int chkValue = 0;
		
		if(cNumArr.length != 10) return false;

		for(int i = 0 ; i < keyNumArr.length ; i++) {
			int cNum = Character.getNumericValue(cNumArr[i]);
			chkValue += cNum * keyNumArr[i];
		}
		chkValue += (keyNumArr[8] * Character.getNumericValue(cNumArr[8]))/10 ;
		chkValue = (10 - (chkValue % 10)) % 10;
		return Character.getNumericValue(cNumArr[9]) == chkValue;
	}

	private void setDateConvert(UsrDeptInfoVO vo) throws Exception {

		//-- 시간값 변경
		String expStartDate = "";
		String expEndDate = "";
		if(!"".equals(StringUtil.nvl(vo.getExpStartDate())))
			expStartDate = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getExpStartDate(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(vo.getExpEndDate())))
			expEndDate = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getExpEndDate(), ".", ""),"-",""),"/","")+"235959";

		vo.setExpStartDate(expStartDate);
		vo.setExpEndDate(expEndDate);
	}
	
	/**
	 * 기업관리자 권한 리스트
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listSearch(UsrDeptInfoVO vo) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
			List<UsrUserInfoVO> returnList = usrDeptInfoMapper.listSearch(vo);
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
	 * 기업 목록을 반환한다.
	 * @param UsrDeptInfoVO
	 * @return ProcessResultListVO<UsrDeptInfoVO>
	 */
	@Override
	public ProcessResultListVO<UsrDeptInfoVO> listForSearch(UsrDeptInfoVO vo) throws Exception {
		ProcessResultListVO<UsrDeptInfoVO> resultList = new ProcessResultListVO<>();
		try {
			List<UsrDeptInfoVO> returnList = usrDeptInfoMapper.searchList(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
}
