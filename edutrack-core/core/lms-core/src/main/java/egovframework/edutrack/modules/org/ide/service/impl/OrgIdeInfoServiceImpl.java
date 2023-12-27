package egovframework.edutrack.modules.org.ide.service.impl;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.org.ide.service.OrgIdeInfoService;
import egovframework.edutrack.modules.org.ide.service.OrgIdeInfoVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("orgIdeInfoService")
public class OrgIdeInfoServiceImpl extends EgovAbstractServiceImpl implements OrgIdeInfoService{
	
	/** Mapper */
	@Resource(name="orgIdeInfoMapper")
	private OrgIdeInfoMapper 		orgIdeInfoMapper;
	
    @Resource(name="orgIdeInfoService")
    private OrgIdeInfoService		orgIdeInfoService;
	
	@Override
	public ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgIdeInfoVO> resultList = new ProcessResultListVO<OrgIdeInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgIdeInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgIdeInfoVO> connIpList =  orgIdeInfoMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(connIpList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	@Override
	public ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	

	@Override
	public int add(OrgIdeInfoVO vo) {
		return orgIdeInfoMapper.insert(vo);
		
	}
	
	@Override
	public int editUseYn(OrgIdeInfoVO vo) throws Exception {
		return orgIdeInfoMapper.updateUseYn(vo);
	}
	
	@Override
	public int remove(OrgIdeInfoVO vo) throws Exception {
		return orgIdeInfoMapper.delete(vo);
	}

	/**
	 * 사용자 등록 샘플 엑셀 파일 다운로드
	 * @param outputStream
	 * @param String
	 */
	@Override
	public void sampleExcelDownloadForIdeAdd(OutputStream os, String orgCd) throws Exception {
		int rowNum = 0;
		
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet();
		
		wbook.setSheetName(0, "IDE URL 일괄 등록");
		
		XSSFRow pageRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createPageTitleCell("IDE URL 일괄 등록", pageRow, 0, 9);
		
		rowNum++;
		XSSFRow descriptionRow = sheet.createRow((short)rowNum);
		String description = "* 샘플 데이터와 같은 형식으로 샘플 파일에 작성하여 업로드해야 정상적으로 등록됩니다." + System.lineSeparator(); 
		POIExcelUtil.createMergeCell(description, descriptionRow, 0, 9, "left");
		descriptionRow.setHeight((short)(sheet.getDefaultRowHeight() * 6));
		rowNum++;
		rowNum++;
		
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("IDE URL",titleColumnRow,0);
		POIExcelUtil.createTitleCell("사용여부(사용:Y,미사용:N)",titleColumnRow,1);
		
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 800);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
		
		rowNum++;
		XSSFRow contentRow = sheet.createRow((short)rowNum);
		
		POIExcelUtil.createContentCell("http://211.60.125.130:28080", contentRow, 0, "left");
		POIExcelUtil.createContentCell("Y", contentRow, 1, "left");
		
		
		UsrDeptInfoVO udivo = new UsrDeptInfoVO();
		udivo.setOrgCd(orgCd);
		
		wbook.write(os);
	}
	@SuppressWarnings("resource")
	@Override
	public ProcessResultListVO<OrgIdeInfoVO> excelUploadIdeAdd(OrgIdeInfoVO vo, String fileName, String filePath, String orgCd) throws Exception {
		int mainSheetIndex = 0;
		int titleColumnCount = 2;
		int titleRowIndex = 3;
		int contentStartRowIndex = 4;
		
		ProcessResultListVO<OrgIdeInfoVO> resultVO = new ProcessResultListVO<OrgIdeInfoVO>();

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
		List<OrgIdeInfoVO> ideList = new ArrayList<>();
		int contentRows = sheet.getPhysicalNumberOfRows() - 2;
		int rowCount = 0;
		for(int rowIndex = contentStartRowIndex; rowCount < contentRows; rowCount++,rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row != null) {
				OrgIdeInfoVO ide = getIdeInfoFromRow(row);
				validateIdeInfo(ide);
				
				ide.setOrgCd(orgCd);
				ide.setRegNo(vo.getRegNo());
				orgIdeInfoService.add(ide);

				ideList.add(ide);
			}
		}
		resultVO.setResult(1);
		resultVO.setReturnList(ideList);
		
		return resultVO;
	}
	
	// 셀에서 IDE 정보 가져오기
	private OrgIdeInfoVO getIdeInfoFromRow(XSSFRow row) {
		OrgIdeInfoVO ideVO = new OrgIdeInfoVO();
		ideVO.setLineNo("" + (row.getRowNum() - 3));
		ideVO.setIdeUrl(POIExcelUtil.getCellValue(row.getCell(0)));
		ideVO.setUseYn(POIExcelUtil.getCellValue(row.getCell(1)));
		
		return ideVO;
	}
	
	//  IDE 유효성 검사
		private void validateIdeInfo(OrgIdeInfoVO vo) throws Exception{
			String lineNo = vo.getLineNo();
			String ideUrl = StringUtil.nvl(vo.getIdeUrl());
			String useYn = StringUtil.nvl(vo.getUseYn());
			
			if("N".equals(orgIdeInfoMapper.selectIdeUrlCheck(vo))) {
				throw new ServiceProcessException(String.format("중복된 IDE URL : [%S]행 (%s)", lineNo, ideUrl));
			}
			if(!"YN".contains(useYn)) {
				throw new ServiceProcessException(String.format("잘못된 사용여부 값 : [%S]행 (%s)", lineNo, useYn));
			}
			
		}
}
