package egovframework.edutrack.modules.org.fac.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.code.impl.ReserveStatusCode;
import egovframework.edutrack.comm.event.AddFacResEvent;
import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HttpRequestUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.PageUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.fac.service.OrgFacCtgrVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacInfoVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacService;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileMapper;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("orgFacService")
public class OrgFacServiceImpl extends EgovAbstractServiceImpl implements OrgFacService {
	
	private final class FacNestedFileHandler implements FileHandler<OrgFacInfoVO> {
		@Override
		public String getPK(OrgFacInfoVO vo) {
			return vo.getFacCd();
		}
		@Override
		public String getRepoCd() {
			return "FAC_INFO";
		}
		@Override
		public List<SysFileVO> getFiles(OrgFacInfoVO vo) {
			List<SysFileVO> fileList = new ArrayList<>();
			fileList.addAll(vo.getAttachFiles());
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}
		@Override
		public OrgFacInfoVO setFiles(OrgFacInfoVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("file"));
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
    @Resource(name="orgFacMapper")
    private OrgFacMapper 	orgFacMapper;
    
    @Resource(name="sysFileMapper")
    private SysFileMapper sysFileMapper;
    
	@Autowired
	private SysFileService 		sysFileService;
	
	@Autowired
	private ApplicationEventPublisher mailEventPublisher;
	
	/**
	 * 시설 정보 추가
	 */
	@Override
	public String addFac(OrgFacInfoVO vo) throws Exception {
		String key = orgFacMapper.selectFacKey();
		vo.setFacCd(key);
		orgFacMapper.addFac(vo);
		sysFileService.bindFile(vo, new FacNestedFileHandler());
		return key;
	}
	
	/**
	 * 시설 정보 업데이트
	 */
	@Override
	public int updateFac(OrgFacInfoVO vo) throws Exception {
		int result = orgFacMapper.facInfoUpdate(vo);
		sysFileService.bindFileUpdate(vo, new FacNestedFileHandler());
		return result;
	}
	
	/**
	 * 시설 사용 여부 업데이트
	 */
	@Override
	public int updateFacUse(OrgFacInfoVO vo) throws Exception {
		return orgFacMapper.facUseUpdate(vo);
	}
	
	/**
	 * 시설 정보 삭제
	 */
	@Override
	public int deleteFac(OrgFacInfoVO vo) throws Exception {
		sysFileService.removeFile(vo, new FacNestedFileHandler());
		return orgFacMapper.facRemove(vo); 
	}
	
	/**
	 * 시설 정보 조회
	 */
	@Override
	public OrgFacInfoVO viewFac(OrgFacInfoVO vo) throws Exception {
		OrgFacInfoVO fac = orgFacMapper.selectFacInfo(vo);
		if(fac == null) throw new ServiceProcessException("해당하는 시설이 존재하지 않습니다.");
		sysFileService.getFile(fac, new FacNestedFileHandler());
 		return fac;
	}
	
	/**
	 * 시설 목록 조회
	 */
	@Override
	public ProcessResultListVO<OrgFacInfoVO> listFac(OrgFacInfoVO vo) throws Exception {
		ProcessResultListVO<OrgFacInfoVO> resultList = new ProcessResultListVO<>(); 
		List<OrgFacInfoVO> facList = orgFacMapper.listFac(vo);
		resultList.setReturnList(facList);
 		return resultList;
	}
	
	/**
	 * 시설 정보 페이징 목록 조회
	 */
	@Override
	public ProcessResultListVO<OrgFacInfoVO> listPageingFac(OrgFacInfoVO vo) throws Exception {
		return this.listInfoPageing(vo, false);
	}
	@Override
	public ProcessResultListVO<OrgFacInfoVO> listInfoPageing(OrgFacInfoVO vo, boolean fileIn) throws Exception {
		ProcessResultListVO<OrgFacInfoVO> resultList = new ProcessResultListVO<>();
		try {
			PaginationInfo paginationInfo = PageUtil.getDefaultPaginationInfo(vo);
			int totalCount = orgFacMapper.infoCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgFacInfoVO> list =  orgFacMapper.listInfoPageing(vo);
			if(fileIn) {
				for(OrgFacInfoVO fac : list) {
					sysFileService.getFile(fac, new FacNestedFileHandler());
				}
			}
			resultList.setResult(1);
			resultList.setReturnList(list);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 시설 예약 신청
	 */
	@Override
	public int addRes(OrgFacResVO vo) throws Exception {
		validateReserveTime(vo);
		vo.setResCd(orgFacMapper.selectResKey());
		vo.setResSts(ReserveStatusCode.WAIT);
		
		int result = orgFacMapper.addRes(vo);
		
		OrgFacResVO resInfo = orgFacMapper.selectFacRes(vo);
		String menuCd = UserBroker.getMenuCode(HttpRequestUtil.getRequest());
		mailEventPublisher.publishEvent(new AddFacResEvent(menuCd, "EMAIL", resInfo));
		
		return result;
	}
	
	@Override
	public void validateReserveTime(OrgFacResVO vo) {
		if(ValidationUtils.isEmpty(vo.getResDt())) throw new AjaxIllegalFormatException("예약 날짜를 선택해주세요.");
		if(ValidationUtils.isEmpty(vo.getResStm()) || ValidationUtils.isEmpty(vo.getResEtm())) throw new AjaxIllegalFormatException("시간을 선택해주세요.");
		if(!chkTodayTime(vo)) throw new ServiceProcessException("예약 시작 시간이 현재 시각 보다 빠릅니다.");
		if(!chkResTime(vo)) throw new ServiceProcessException("해당 시간대에 예약이 존재합니다.");
	}
	
	private boolean chkTodayTime(OrgFacResVO vo) {
		String plainDate = vo.getResDt().replaceAll("[^0-9]", "");
		String plainStartTime = vo.getResStm().replaceAll("[^0-9]", "") + "00";
		if(DateTimeUtil.getDate().equals(plainDate)
				&& DateTimeUtil.chkDateTimeNowAfter(plainDate + plainStartTime)) {
			return false;
		}
		return true;
	}

	private boolean chkResTime(OrgFacResVO vo){
		List<OrgFacResVO> result = orgFacMapper.resListForTimeChk(vo).stream()
									.filter(OrgFacResVO::isValidRes)
									.collect(Collectors.toList());
		
		return result.size() == 0;
	}
	
	/**
	 * 시설 예약 정보 업데이트
	 */
	@Override
	public int updateRes(OrgFacResVO vo) throws Exception {
		return orgFacMapper.facResUpdate(vo);
	}

	/**
	 * 시설 예약 정보 조회
	 */
	@Override
	public OrgFacResVO viewRes(OrgFacResVO vo) throws Exception {
		OrgFacResVO res = orgFacMapper.selectFacRes(vo);
		if(res == null) throw new ServiceProcessException("해당하는 예약 내역이 존재하지 않습니다.");
 		return res;
	}

	/**
	 * 시설 예약 취소
	 */
	@Override
	public int cancelRes(OrgFacResVO vo) throws Exception {
		OrgFacResVO resInfo = orgFacMapper.selectFacRes(vo);
		if(resInfo == null) throw new ServiceProcessException("해당 예약 내역이 존재하지 않습니다.");
		if(!isCancelable(resInfo)) throw new ServiceProcessException("예약 취소가 가능한 상태가 아닙니다");
		
		vo.setResSts(ReserveStatusCode.CANCEL);
		return orgFacMapper.facResUpdate(vo);
	}
	/**
	 * 시설 예약 취소 가능 상태값 체크
	 */
	private boolean isCancelable(OrgFacResVO vo) {
		ReserveStatusCode resSts = vo.getResSts();
		return ReserveStatusCode.WAIT.equals(resSts)
				|| ReserveStatusCode.RESERVED.equals(resSts);
	}

	/**
	 * 시설 예약 목록 조회
	 */
	@Override
	public List<OrgFacResVO> listRes(OrgFacResVO vo) throws Exception {
 		return  orgFacMapper.list(vo);
	}
	/**
	 * 시설 예약 정보 페이징 목록 조회
	 */
	@Override
	public ProcessResultListVO<OrgFacResVO> listPageingRes(OrgFacResVO vo) throws Exception {
		ProcessResultListVO<OrgFacResVO> resultList = new ProcessResultListVO<>();
		try {
			PaginationInfo paginationInfo = PageUtil.getDefaultPaginationInfo(vo);
			int totalCount = orgFacMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgFacResVO> list =  orgFacMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(list);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;	
	}
	
	/**
	 * 시설 분류 추가
	 */
	@Override
	public String addFacCtgr(OrgFacCtgrVO vo) throws Exception {
		String key = orgFacMapper.selectCtgrKey();
		vo.setFacCtgrCd(key);
		orgFacMapper.addFacCtgr(vo);
		return key;
	}
	
	/**
	 * 시설 분류 조회
	 */
	@Override
	public OrgFacCtgrVO viewFacCtgr(OrgFacCtgrVO vo) throws Exception {
		OrgFacCtgrVO facCtgr = orgFacMapper.selectFacCtgr(vo);
		if(facCtgr == null) throw new ServiceProcessException("해당하는 시설 분류가 없습니다.");
		return facCtgr;
	}
	
	/**
	 * 시설 분류 목록 조회
	 */
	@Override
	public List<OrgFacCtgrVO> listFacCtgr(OrgFacCtgrVO vo) throws Exception {
		return orgFacMapper.listFacCtgr(vo);
	}
	
	/**
	 * 시설 분류 업데이트
	 */
	@Override
	public int updateFacCtgr(OrgFacCtgrVO vo) throws Exception {
		return orgFacMapper.updateFacCtgr(vo);
	}
	
	/**
	 * 시설 분류 삭제
	 */
	@Override
	public int deleteFacCtgr(OrgFacCtgrVO vo) throws Exception {
		
		OrgFacInfoVO infoVO = new OrgFacInfoVO();
		infoVO.setFacCtgrCd(vo.getFacCtgrCd());
		if(orgFacMapper.listInfo(infoVO).size() > 0) 
			throw new ServiceProcessException("사용중인 시설이 있습니다.\n시설 삭제 후 시도해주세요.");
		
		return orgFacMapper.deleteFacCtgr(vo);
	}

	@Override
	public int updateFacInfoOdr(OrgFacInfoVO vo) throws Exception {
		OrgFacInfoVO fac = orgFacMapper.selectFacInfo(vo);
		
		vo.setFacOdr(vo.getFacOdr()-1);
		OrgFacInfoVO swapFac = orgFacMapper.selectForSwap(vo);
		
		vo.setFacOdr(swapFac.getFacOdr());
		if(fac.getFacOdr() > vo.getFacOdr()) orgFacMapper.increaseOtherFacOdr(vo);
		else orgFacMapper.decreaseOtherFacOdr(vo);
		
		return orgFacMapper.updateFacOdr(vo);
	}
	

	@Override
	public void resListExcelDownload(OrgFacResVO vo, OutputStream os) throws Exception {
		int rowNum = 0;
		
		@SuppressWarnings("resource")
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet();
		
		wbook.setSheetName(0, "시설 예약 현황");
		
		XSSFRow pageRow = sheet.createRow((short)rowNum);
		String title = String.format("시설 예약 현황 [%s ~ %s]", vo.getWorkStartDate(), vo.getWorkEndDate());
		POIExcelUtil.createPageTitleCell(title, pageRow, 0, 9);
		
		rowNum++;
		
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("연번",titleColumnRow,0);
		POIExcelUtil.createTitleCell("신청자",titleColumnRow,1);
		POIExcelUtil.createTitleCell("연락처",titleColumnRow,2);
		POIExcelUtil.createTitleCell("이메일",titleColumnRow,3);
		POIExcelUtil.createTitleCell("시설명",titleColumnRow,4);
		POIExcelUtil.createTitleCell("참석인원",titleColumnRow,5);
		POIExcelUtil.createTitleCell("사용목적",titleColumnRow,6);
		POIExcelUtil.createTitleCell("예약일자",titleColumnRow,7);
		POIExcelUtil.createTitleCell("예약시간",titleColumnRow,8);
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
		
		List<OrgFacResVO> resList = orgFacMapper.list(vo);
		
		for (int i = 0; i < resList.size(); i++) {
			rowNum++;
			OrgFacResVO res = resList.get(i);
			
			XSSFRow contentRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createContentCell(i+1 , contentRow, 0, "center");
			POIExcelUtil.createContentCell(res.getUserNm(), contentRow, 1, "center");
			POIExcelUtil.createContentCell(res.getMobileNo(), contentRow, 2, "center");
			POIExcelUtil.createContentCell(res.getEmail(), contentRow, 3, "center");
			POIExcelUtil.createContentCell(res.getFacCtgrNm() + " - " + res.getFacNm(), contentRow, 4, "center");
			POIExcelUtil.createContentCell(res.getAttCnt(), contentRow, 5, "center");
			POIExcelUtil.createContentCell(res.getEventDesc(), contentRow, 6, "center");
			POIExcelUtil.createContentCell(res.getResDt(), contentRow, 7, "center");
			POIExcelUtil.createContentCell(res.getResStm() + " ~ " + res.getResEtm(), contentRow, 8, "center");
			POIExcelUtil.createContentCell(res.getResSts().getCodeNm(), contentRow, 9, "center");
		}
		wbook.write(os);
	}

}

