package egovframework.edutrack.modules.log.sitestat.service.impl;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.log.sitestat.service.SiteStatService;
import egovframework.edutrack.modules.log.sitestat.service.SiteStatVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>공동활용 사이트 통계 - 사이트 운영 현황</b> 영역  Service 클래스
 * @author kjbg
 *
 */
@Service("siteStatService")
public class SiteStatServiceImpl
	extends EgovAbstractServiceImpl implements SiteStatService {

	/** Mapper */
    @Resource(name="siteStatMapper")
    private SiteStatMapper 		siteStatMapper;
    
	/** MessageSource */
	@Resource(name = "messageSource")
	private MessageSource messageSource;
    
    /*
	 * 사이트 운영 현황 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SiteStatVO> list(SiteStatVO vo) throws Exception{
		ProcessResultListVO<SiteStatVO> resultList = new ProcessResultListVO<SiteStatVO>();
		List<SiteStatVO> logList  = siteStatMapper.list(vo);
		resultList.setResult(1);
		resultList.setReturnList(logList);		
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<SiteStatVO> resultList = new ProcessResultListVO<SiteStatVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = siteStatMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<SiteStatVO> logList =  siteStatMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(logList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	@Override
	public ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	@Override
	public void listSiteStatExcelDownload(SiteStatVO vo, ServletOutputStream os, HttpServletRequest request) throws Exception {
		
		List<SiteStatVO> resultList  = siteStatMapper.list(vo);
		Locale locale = LocaleUtil.getLocale(request);
		
		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet(UserBroker.getMenuName(request), 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum, "center", UserBroker.getMenuName(request))); //1열
			//-- 제목줄 병합
			sheet.mergeCells(0, 0, 11, 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);
			
			//각 셀의 넓이 세팅
			sheet.setColumnView(0, 40);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			sheet.setColumnView(8, 20);
			sheet.setColumnView(9, 20);
			sheet.setColumnView(10, 30);
			sheet.setColumnView(11, 30);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0, rowNum, messageSource.getMessage("log.title.site.name", null, locale)));//사이트명
			sheet.mergeCells(0, 1, 0, 2);
			
			sheet.addCell(ExcelUtil.createHeader(1, rowNum, messageSource.getMessage("log.title.org.user", null, locale)));//사용자
			sheet.mergeCells(1, 1, 4, 1);
			
			sheet.addCell(ExcelUtil.createHeader(5, rowNum, messageSource.getMessage("log.title.know.reg.status", null, locale)));//지식 등록현황
			sheet.mergeCells(5, 1, 9, 1);
			
			sheet.addCell(ExcelUtil.createHeader(10, rowNum, messageSource.getMessage("log.title.know.study.cnt", null, locale)));//지식학습수
			sheet.mergeCells(10, 1, 10, 2);
			
			sheet.addCell(ExcelUtil.createHeader(11, rowNum, messageSource.getMessage("log.title.share.know.use.cnt", null, locale)));//공유지식활용수
			sheet.mergeCells(11, 1, 11, 2);
			
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(1, rowNum, messageSource.getMessage("log.title.org.user.active", null, locale)));//사용
			sheet.addCell(ExcelUtil.createHeader(2, rowNum, messageSource.getMessage("log.title.org.user.pause", null, locale)));//일시정지
			sheet.addCell(ExcelUtil.createHeader(3, rowNum, messageSource.getMessage("log.title.org.user.dormant", null, locale)));//휴면
			sheet.addCell(ExcelUtil.createHeader(4, rowNum, messageSource.getMessage("log.title.org.user.withdrawn", null, locale)));//탈퇴
			sheet.addCell(ExcelUtil.createHeader(5, rowNum, messageSource.getMessage("log.title.know.type.mov", null, locale)));//동영상	
			sheet.addCell(ExcelUtil.createHeader(6, rowNum, messageSource.getMessage("log.title.know.type.doc", null, locale)));//문서
			sheet.addCell(ExcelUtil.createHeader(7, rowNum, messageSource.getMessage("log.title.know.type.img", null, locale)));//이미지
			sheet.addCell(ExcelUtil.createHeader(8, rowNum, messageSource.getMessage("log.title.know.type.html", null, locale)));//HTML
			sheet.addCell(ExcelUtil.createHeader(9, rowNum, messageSource.getMessage("log.title.know.type.link", null, locale)));//LINK

			rowNum++;
			for(int i=0; i<resultList.size(); i++){

				SiteStatVO siteStatVO = resultList.get(i);
				int coloumIdx = 0;
				
				sheet.addCell(ExcelUtil.createText(coloumIdx++, rowNum, "center", siteStatVO.getOrgNm()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getUserStatUCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getUserStatCCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getUserStatFCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getUserStatDCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowMovCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowDocCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowImgCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowHtmlCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowLinkCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getKnowStudyCnt()));
				sheet.addCell(ExcelUtil.createNumber(coloumIdx++, rowNum, "center", siteStatVO.getShareKnowCnt()));

				rowNum++;
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
		
	}
}
