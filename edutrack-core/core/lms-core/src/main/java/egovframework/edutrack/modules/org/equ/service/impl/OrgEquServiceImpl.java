package egovframework.edutrack.modules.org.equ.service.impl;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.code.impl.RentStatusCode;
import egovframework.edutrack.comm.event.AddEquRentEvent;
import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HttpRequestUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.PageUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.equ.service.OrgEquItemVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentDetailVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquService;
import egovframework.edutrack.modules.org.equ.service.OrgEquVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("orgEquService")
public class OrgEquServiceImpl implements OrgEquService {
	
	private final class EquNestedFileHandler implements FileHandler<OrgEquVO> {
		@Override
		public String getPK(OrgEquVO vo) {
			return vo.getEquCd();
		}
		@Override
		public String getRepoCd() {
			return "EQU_INFO";
		}
		@Override
		public List<SysFileVO> getFiles(OrgEquVO vo) {
			List<SysFileVO> fileList = new ArrayList<>();
			fileList.addAll(vo.getAttachFiles());
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}
		@Override
		public OrgEquVO setFiles(OrgEquVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("file"));
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
	@Resource(name= "orgEquMapper")
	private OrgEquMapper orgEquMapper;
	
	@Resource(name= "orgEquItemMapper")
	private OrgEquItemMapper orgEquItemMapper;
	
	@Resource(name= "orgEquRentMapper")
	private OrgEquRentMapper orgEquRentMapper;
	
	@Autowired
	private SysFileService 		sysFileService;
	
	@Autowired
	private ApplicationEventPublisher mailEventPublisher;

	/**
	 * 장비 정보 추가
	 */
	@Override
	public int addEquipment(OrgEquVO vo) throws Exception {
		vo.setEquCd(orgEquMapper.selectKey());
		int result = orgEquMapper.insert(vo);
		sysFileService.bindFile(vo, new EquNestedFileHandler());
		return result;
	}

	/**
	 * 장비 정보 조회
	 */
	@Override
	public OrgEquVO viewEquipment(OrgEquVO vo) throws Exception {
		OrgEquVO equ = orgEquMapper.select(vo);
		if(equ == null) throw new Exception("해당하는 장비 정보가 존재하지 않습니다.");
		sysFileService.getFile(equ, new EquNestedFileHandler());
		return equ;
	}

	/**
	 * 장비 정보 페이징 목록 조회
	 */
	@Override
	public ProcessResultListVO<OrgEquVO> listPagingEquipment(OrgEquVO vo) {
		return listPagingEquipment(vo, false);
	}
	@Override
	public ProcessResultListVO<OrgEquVO> listPagingEquipment(OrgEquVO vo, boolean fileIn) {
		ProcessResultListVO<OrgEquVO> resultList = new ProcessResultListVO<>();
		try {
			PaginationInfo paginationInfo = PageUtil.getDefaultPaginationInfo(vo);
			int totalCount = orgEquMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgEquVO> equList = orgEquMapper.listPaging(vo);
			if(fileIn) {
				for(OrgEquVO equ : equList) {
					sysFileService.getFile(equ, new EquNestedFileHandler());
				}
			}
			resultList.setReturnList(equList);
			resultList.setPageInfo(paginationInfo);		
		} catch(Exception e) {
			resultList.setReturnList(new ArrayList<>());
		}
		return resultList;
	}

	/**
	 * 장비 정보 업데이트
	 */
	@Override
	public int updateEquipment(OrgEquVO vo) throws Exception {
		int result = orgEquMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new EquNestedFileHandler());
		return result;
	}
	
	@Override
	public int updateEquipmentUse(OrgEquVO vo){
		return orgEquMapper.updateUseYn(vo);
	}

	/**
	 * 장비 정보 삭제
	 */
	@Override
	public int deleteEquipment(OrgEquVO vo) throws Exception {
		sysFileService.removeFile(vo, new EquNestedFileHandler());
		return orgEquMapper.delete(vo);
	}

	/**
	 * 상품 정보 추가
	 */
	@Override
	public int addItem(OrgEquItemVO vo) {
		vo.setItemCd(orgEquItemMapper.selectKey());
		return orgEquItemMapper.insert(vo);
	}

	/**
	 * 상품 정보 조회
	 */
	@Override
	public OrgEquItemVO viewItem(OrgEquItemVO vo) throws Exception {
		OrgEquItemVO item = orgEquItemMapper.select(vo);
		if(item == null) throw new Exception("해당하는 상품이 존재하지 않습니다.");
		return item;
	}
	
	/**
	 * 상품 정보 목록 조회
	 */
	@Override
	public List<OrgEquItemVO> listItem(OrgEquItemVO vo) {
		return orgEquItemMapper.list(vo);
	}
	
	/**
	 * 상품 정보 페이징 목록 조회
	 */
	@Override
	public List<OrgEquItemVO> validListItem(OrgEquItemVO vo) {
		return orgEquItemMapper.validItemList(vo);
	}
	@Override
	public ProcessResultListVO<OrgEquItemVO> listPagingItem(OrgEquItemVO vo) {
		ProcessResultListVO<OrgEquItemVO> result = new ProcessResultListVO<>();
		try {
			PaginationInfo paginationInfo = PageUtil.getDefaultPaginationInfo(vo);
			int totalCount = orgEquItemMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgEquItemVO> itemList = orgEquItemMapper.listPaging(vo);
			result.setReturnList(itemList);
			result.setPageInfo(paginationInfo);			
			
		} catch(Exception e) {
			result.setReturnList(new ArrayList<>());
		}
		
		return result;
	}

	/**
	 * 상품 정보 업데이트
	 */
	@Override
	public int updateItem(OrgEquItemVO vo) {
		return orgEquItemMapper.update(vo);
	}
	
	/**
	 * 상품 사용 정보 업데이트
	 */
	@Override
	public int updateItemUse(OrgEquItemVO vo){
		return orgEquItemMapper.updateUseYn(vo);
	}

	/**
	 * 상품 정보 삭제
	 */
	@Override
	public int deleteItem(OrgEquItemVO vo) {
		return orgEquItemMapper.delete(vo);
	}

	/**
	 * 장비 대여 신청
	 */
	@Override
	public int addRent(OrgEquRentVO vo) {
		validateRentVO(vo);
		vo.setRentCd(orgEquRentMapper.selectRentKey());
		vo.setRentSts(RentStatusCode.REQUEST);
		
		int result = orgEquRentMapper.insert(vo);
		
		OrgEquRentVO rentInfo = orgEquRentMapper.select(vo);
		String menuCd = UserBroker.getMenuCode(HttpRequestUtil.getRequest());
		mailEventPublisher.publishEvent(new AddEquRentEvent(menuCd, "EMAIL", rentInfo));
		
		return result;
	}
	
	/**
	 * 예약 유효성 체크
	 */
	private void validateRentVO(OrgEquRentVO vo) {
		
		String plainDttm = vo.getRentStartDttm().trim().replaceAll("[^0-9]", "") + "00";
		if(DateTimeUtil.chkDateTimeNowAfter(plainDttm)) 
			throw new ServiceProcessException("예약 시작 시간이 현재 시각 보다 빠릅니다.");
		
		if(ValidationUtils.isEmpty(vo.getUserNo())) throw new ServiceProcessException("세션이 만료되었습니다.");
		if(chkEnableItemCnt(vo) < vo.getRentCnt()) throw new ServiceProcessException("대여가능한 장비 갯수를 초과 하였습니다.");
	}
	
	/**
	 * 장비 대여 가능 상품 수량 조회
	 */
	@Override
	public int chkEnableItemCnt(OrgEquRentVO vo) {
		
		if(ValidationUtils.isEmpty(vo.getRentStartDttm()) || ValidationUtils.isEmpty(vo.getRentEndDttm())) 
			throw new AjaxIllegalFormatException("대여 기간을 선택해주세요.");
		String plainDttm = vo.getRentStartDttm().trim().replaceAll("[^0-9]", "") + "00";
		if(DateTimeUtil.chkDateTimeNowAfter(plainDttm)) 
			throw new ServiceProcessException("예약 시작 시간이 현재 시각 보다 빠릅니다.");
		
		OrgEquItemVO itemVO = new OrgEquItemVO();
		itemVO.setEquCd(vo.getEquCd());
		itemVO.setUseYn("Y");
		int itemCnt = orgEquItemMapper.count(itemVO);
		
		OrgEquRentVO rentVO = new OrgEquRentVO();
		rentVO.setEquCd(vo.getEquCd());
		rentVO.setRentStartDttm(vo.getRentStartDttm());
		rentVO.setRentEndDttm(vo.getRentEndDttm());
		int rentCnt = orgEquRentMapper.list(rentVO).stream()
						.filter(OrgEquRentVO::isValidRent)
						.mapToInt(OrgEquRentVO::getRentCnt)
						.sum();
		
		return itemCnt-rentCnt;
	}


	/**
	 * 장비 대여 정보 조회
	 */
	@Override
	public OrgEquRentVO viewRent(OrgEquRentVO vo) throws Exception {
		OrgEquRentVO rentInfo = orgEquRentMapper.select(vo);
		if(rentInfo == null) throw new Exception("해당하는 대여 내역이 존재하지 않습니다.");
		
		OrgEquRentDetailVO detailVO = new OrgEquRentDetailVO();
		detailVO.setRentCd(rentInfo.getRentCd());
		rentInfo.setDetailList(orgEquRentMapper.listDetail(detailVO));
		
		return rentInfo;
	}
	
	/**
	 * 장비 대여 정보 목록 조회
	 */
	@Override
	public List<OrgEquRentVO> listRent(OrgEquRentVO vo) {
		return orgEquRentMapper.list(vo);
	}

	/**
	 * 장비 대여 정보 페이징 목록 조회
	 */
	@Override
	public ProcessResultListVO<OrgEquRentVO> listPagingRent(OrgEquRentVO vo) {
		ProcessResultListVO<OrgEquRentVO> result = new ProcessResultListVO<>();
		try {
			PaginationInfo paginationInfo = PageUtil.getDefaultPaginationInfo(vo);
			int totalCount = orgEquRentMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgEquRentVO> rentInfoList = orgEquRentMapper.listPaging(vo);
			result.setReturnList(rentInfoList);
			result.setPageInfo(paginationInfo);		
		} catch (Exception e) {
			result.setReturnList(new ArrayList<>());
		}
		return result;
	}

	/**
	 * 장비 대여 정보 업데이트
	 */
	@Override
	public int updateRent(OrgEquRentVO vo) {
		OrgEquRentDetailVO detailVO = new OrgEquRentDetailVO();
		detailVO.setRentCd(vo.getRentCd());
		orgEquRentMapper.deleteDetailByRentCd(detailVO);
		
		if(!chkStsForDetail(vo)) {
			vo.getDetailList().stream().forEach(orgEquRentMapper::insertDetail);
		}
		return orgEquRentMapper.update(vo);
	}
	
	/**
	 * 대여 상세 정보 저장여부 상태값 체크
	 */
	private boolean chkStsForDetail(OrgEquRentVO vo) {
		RentStatusCode rentSts = vo.getRentSts();
		return RentStatusCode.RENT_CANCEL.equals(rentSts) 
			|| RentStatusCode.REQ_CANCEL.equals(rentSts)
			|| RentStatusCode.REQUEST.equals(rentSts);
	}

	/**
	 * 장비 대여 정보 삭제
	 */
	@Override
	public int deleteRent(OrgEquRentVO vo) {
		return orgEquRentMapper.delete(vo);
	}
	
	/**
	 * 장비 대여 요청 취소
	 */
	@Override
	public int cancelRent(OrgEquRentVO vo) {
		OrgEquRentVO rentInfo = orgEquRentMapper.select(vo);
		if(rentInfo == null) throw new ServiceProcessException("해당 예약 내역이 존재하지 않습니다.");
		if(!isCancelable(rentInfo)) throw new ServiceProcessException("예약취소가 가능한 상태가 아닙니다.");
		
		if(RentStatusCode.APPROVED.equals(rentInfo.getRentSts())) {
			OrgEquRentDetailVO detailVO = new OrgEquRentDetailVO();
			detailVO.setRentCd(vo.getRentCd());
			orgEquRentMapper.deleteDetailByRentCd(detailVO);
		}
		
		vo.setRentSts(RentStatusCode.REQ_CANCEL);
		
		return orgEquRentMapper.update(vo);
	}
	
	/**
	 * 장비 대여 취소 가능 상태값 체크
	 */
	private boolean isCancelable(OrgEquRentVO vo) {
		RentStatusCode rentSts = vo.getRentSts();
		return RentStatusCode.REQUEST.equals(rentSts)
				|| RentStatusCode.APPROVED.equals(rentSts);
	}

	@Override
	public int updateEquOdr(OrgEquVO vo) {
		OrgEquVO equ = orgEquMapper.select(vo);
		
		vo.setEquOdr(vo.getEquOdr()-1);
		OrgEquVO swapEqu = orgEquMapper.selectForSwap(vo);
		
		vo.setEquOdr(swapEqu.getEquOdr());
		
		if(equ.getEquOdr() > vo.getEquOdr()) orgEquMapper.increaseOtherEquOdr(vo);
		else orgEquMapper.decreaseOtherEquOdr(vo);
		
		return orgEquMapper.updateEquOdr(vo);
	}
	
	@Override
	public void sampleExcelDownload(OutputStream os) throws Exception {
		int rowNum = 0;
		
		@SuppressWarnings("resource")
		XSSFWorkbook wbook = new XSSFWorkbook();
		
		XSSFSheet sheet = wbook.createSheet();
		
		wbook.setSheetName(0, "상품 일괄 등록");
		
		XSSFRow pageRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createPageTitleCell("상품 일괄 등록", pageRow, 0, 1);
		
		rowNum++;
		XSSFRow descriptionRow = sheet.createRow((short)rowNum);
		String description = "* 샘플 데이터와 같은 형식으로 샘플 파일에 작성하여 업로드해야 정상적으로 등록됩니다." + System.lineSeparator() 
				+ "* 상품명과 상풍설명을 입력해주세요." + System.lineSeparator()
				+ "* 사용여부는 사용함으로 설정 됩니다.";
		POIExcelUtil.createMergeCell(description, descriptionRow, 0, 1, "left");
		descriptionRow.setHeight((short)(sheet.getDefaultRowHeight() * 6));
		rowNum++;
		rowNum++;
		
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("상품명",titleColumnRow,0);
		POIExcelUtil.createTitleCell("상품설명",titleColumnRow,1);
		
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 2500);
		
		rowNum++;
		XSSFRow contentRow = sheet.createRow((short)rowNum);
		
		POIExcelUtil.createContentCell("테스트 상품1", contentRow, 0, "left");
		POIExcelUtil.createContentCell("이 상품에 대한 설명입니다.", contentRow, 1, "left");
		
		wbook.write(os);
	}
	
	@Override
	@SuppressWarnings("resource")
	public ProcessResultListVO<OrgEquItemVO> excelUploadItemAdd(OrgEquItemVO vo, String fileName, String filePath, String equCd) throws Exception {
		int mainSheetIndex = 0;
		int titleColumnCount = 2;
		int titleRowIndex = 3;
		int contentStartRowIndex = 4;
		
		ProcessResultListVO<OrgEquItemVO> resultVO = new ProcessResultListVO<>();

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
		List<OrgEquItemVO> itemList = new ArrayList<>();
		int contentRows = sheet.getPhysicalNumberOfRows() - 2;
		int rowCount = 0;
		for(int rowIndex = contentStartRowIndex; rowCount < contentRows; rowCount++,rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row != null) {
				OrgEquItemVO itemVO = new OrgEquItemVO();
				itemVO.setItemCd(orgEquItemMapper.selectKey());
				itemVO.setEquCd(equCd);
				itemVO.setItemNm(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(0))).trim());
				itemVO.setItemDesc(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(1))).trim());
				itemVO.setUseYn("Y");
				
				orgEquItemMapper.insert(itemVO);
				itemList.add(itemVO);
			}
		}
		resultVO.setResult(1);
		resultVO.setReturnList(itemList);
		
		return resultVO;
	}
	

	@Override
	public void rentListExcelDownload(OrgEquRentVO vo, OutputStream os) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd / HH:mm");
		int rowNum = 0;
		
		@SuppressWarnings("resource")
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet();
		
		wbook.setSheetName(0, "장비 예약 현황");
		
		XSSFRow pageRow = sheet.createRow((short)rowNum);
		String title = String.format("장비 예약 현황 [%s ~ %s]", vo.getRentStartDttm(), vo.getRentEndDttm());
		POIExcelUtil.createPageTitleCell(title, pageRow, 0, 9);
		
		rowNum++;
		
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("연번",titleColumnRow,0);
		POIExcelUtil.createTitleCell("신청자",titleColumnRow,1);
		POIExcelUtil.createTitleCell("연락처",titleColumnRow,2);
		POIExcelUtil.createTitleCell("이메일",titleColumnRow,3);
		POIExcelUtil.createTitleCell("장비명",titleColumnRow,4);
		POIExcelUtil.createTitleCell("장비대여수",titleColumnRow,5);
		POIExcelUtil.createTitleCell("사용목적",titleColumnRow,6);
		POIExcelUtil.createTitleCell("대여기간",titleColumnRow,7);
		POIExcelUtil.createTitleCell("반납기간",titleColumnRow,8);
		POIExcelUtil.createTitleCell("상태",titleColumnRow,9);
		
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 300);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 300);
		sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 800);
		sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 1300);
		sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 400);
		sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 1100);
		sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 800);
		sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 800);
		sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 400);
		
		List<OrgEquRentVO> rentList = orgEquRentMapper.list(vo);
		
		for (int i = 0; i < rentList.size(); i++) {
			rowNum++;
			OrgEquRentVO rent = rentList.get(i);
			rent.parseRentDttmForExcel(formatter);
			
			XSSFRow contentRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createContentCell(i+1 , contentRow, 0, "center");
			POIExcelUtil.createContentCell(rent.getUserNm(), contentRow, 1, "center");
			POIExcelUtil.createContentCell(rent.getMobileNo(), contentRow, 2, "center");
			POIExcelUtil.createContentCell(rent.getEmail(), contentRow, 3, "center");
			POIExcelUtil.createContentCell(rent.getEquNm(), contentRow, 4, "center");
			POIExcelUtil.createContentCell(rent.getRentCnt(), contentRow, 5, "center");
			POIExcelUtil.createContentCell(rent.getRentDesc(), contentRow, 6, "center");
			POIExcelUtil.createContentCell(rent.getParseStartDttm(), contentRow, 7, "center");
			POIExcelUtil.createContentCell(rent.getParseEndDttm(), contentRow, 8, "center");
			POIExcelUtil.createContentCell(rent.getRentSts().getCodeNm(), contentRow, 9, "center");
		}

		wbook.write(os);
	}
}
