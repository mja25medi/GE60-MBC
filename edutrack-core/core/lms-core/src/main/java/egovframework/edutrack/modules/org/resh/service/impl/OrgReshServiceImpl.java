package egovframework.edutrack.modules.org.resh.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.page.service.impl.OrgPageServiceImpl;
import egovframework.edutrack.modules.org.resh.service.OrgReshAnsrVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshItemVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshResultVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshService;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("orgReshService")
public class OrgReshServiceImpl 
	extends EgovAbstractServiceImpl implements OrgReshService {
	protected static final Log log = LogFactory.getLog(OrgReshServiceImpl.class);
	
	private final class NestedFileHandler
	implements FileHandler<OrgReshItemVO> {

	@Override
	public String getPK(OrgReshItemVO VO) {
		return VO.getReshItemSn()+"";
	}

	@Override
	public String getRepoCd() {
		return "ORG_RESH";
	}

	@Override
	public List<SysFileVO> getFiles(OrgReshItemVO VO) {
		List<SysFileVO> fileList = VO.getAttachImages();
		fileList.addAll(VO.getAttachFiles());
		return fileList;
	}

	@Override
	public OrgReshItemVO setFiles(OrgReshItemVO VO, FileListVO fileListVO) {
		VO.setAttachImages(fileListVO.getFiles("image"));
		VO.setAttachFiles(fileListVO.getFiles("file"));
		return VO;
	}
}
	
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
	
	@Resource(name="orgReshMapper")
	private OrgReshMapper	orgReshMapper;

	@Resource(name="orgReshItemMapper")
	private OrgReshItemMapper	orgReshItemMapper;
	
	@Resource(name="orgReshAnsrMapper")
	private OrgReshAnsrMapper	orgReshAnsrMapper;
	
	/**
	 * 일반 설문 목록 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> listResh(OrgReshVO VO) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		try {
			List<OrgReshVO> returnList = orgReshMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	/**
	 * 나의 일반 설문 목록 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> listMyResh(OrgReshVO VO) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		try {
			List<OrgReshVO> returnList = orgReshMapper.myList(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	/**
	 * 나의 일반 설문 목록 조회 - 페이징
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> listMyReshPageing(OrgReshVO vo) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = orgReshMapper.myListCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgReshVO> returnList = orgReshMapper.myListPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			log.error("Exception occurred");
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
	public	ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = orgReshMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgReshVO> returnList = orgReshMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			log.error("Exception occurred");
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
	public	ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage, int listScale) throws Exception {
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		
		try {
			// 전체 목록 수
			int totalCount = orgReshMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			List<OrgReshVO> returnList = orgReshMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		}catch (Exception e) {
			log.error("Exception occurred");
		}
		return resultList;

	}
	
	/**
	 * 일반 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());

		resultList.setReturnList(orgReshMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 일반 설문 목록 조회(설문명 검색)
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> searchListResearch(OrgReshVO VO) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		try {
			List<OrgReshVO> returnList = orgReshMapper.searchList(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 일반 설문 정보 조회
	 */
	@Override
	public	ProcessResultVO<OrgReshVO> viewResearch(OrgReshVO VO) throws Exception {
		ProcessResultVO<OrgReshVO> resultVO = new ProcessResultVO<OrgReshVO>();
		try {
			OrgReshVO returnVO = orgReshMapper.select(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;

	}
	
	/**
	 * 일반 설문 정보 등록
	 */
	@Override
	public	ProcessResultVO<OrgReshVO> addResearch(OrgReshVO VO) throws Exception {
		ProcessResultVO<OrgReshVO> resultVO = new ProcessResultVO<OrgReshVO>();
		try {
			VO.setReshSn(orgReshMapper.selectKey());
			orgReshMapper.insert(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 일반 설문 정보 수정
	 */
	@Override
	public	ProcessResultVO<OrgReshVO> editResearch(OrgReshVO VO) throws Exception {
		ProcessResultVO<OrgReshVO> resultVO = new ProcessResultVO<OrgReshVO>();
		try {
			orgReshMapper.update(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 일반 설문 정보 삭제
	 */
	@Override
	public	ProcessResultVO<OrgReshVO> deleteResearch(OrgReshVO VO) throws Exception {
		ProcessResultVO<OrgReshVO> resultVO = new ProcessResultVO<OrgReshVO>();
		try {
			orgReshMapper.delete(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;

	}
	
	/**
	 * 일반 질문 목록 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listItem(OrgReshVO VO) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;

	}
	
	/**
	 * 설문 문제 정보 조회
	 */
	@Override
	public	ProcessResultVO<OrgReshItemVO> viewItem(OrgReshItemVO VO) throws Exception {
		VO = orgReshItemMapper.select(VO);
		VO = sysFileService.getFile(VO, new NestedFileHandler());
		this.atclUrlToView(VO);
		return new ProcessResultVO<OrgReshItemVO>().setResultSuccess()
				.setReturnVO(VO);
	}
	

	/**
	 * 설문 문제 정보 등록
	 */
	@Override
	public	ProcessResultVO<OrgReshItemVO> addItem(OrgReshItemVO VO) throws Exception {
		this.atclUrlToPersist(VO);
		VO.setReshItemSn(orgReshItemMapper.selectKey());
		orgReshItemMapper.insert(VO);
		sysFileService.bindFile(VO, new NestedFileHandler());
		return ProcessResultVO.success();
		//return new ProcessResultVO<OrgReshItemVO>().setReturnVO(VO).setResultSuccess(); responseJson으로 반환시 오류를 발생시킴.
	}

	/**
	 * 설문 문제 정보 수정
	 */
	@Override
	public	ProcessResultVO<OrgReshItemVO> editItem(OrgReshItemVO VO) throws Exception {
		this.atclUrlToPersist(VO);	// URL 변환
		orgReshItemMapper.update(VO);
		sysFileService.bindFileUpdate(VO, new NestedFileHandler());
		return ProcessResultVO.success();
		//return new ProcessResultVO<OrgReshItemVO>().setReturnVO(VO).setResultSuccess(); responseJson으로 반환시 오류를 발생시킴.
	}

	/**
	 * 설문 문제 정보 삭제
	 */
	@Override
	public ProcessResultVO<OrgReshItemVO> deleteItem(OrgReshItemVO VO) throws Exception {
		sysFileService.removeFile(VO, new NestedFileHandler()); // 파일정보 삭제..
		orgReshItemMapper.delete(VO);	// 게시글을 삭제
		return ProcessResultVO.success();
	}

	private void atclUrlToPersist(OrgReshItemVO VO) {
		VO.setReshItemCts(StringUtil.replaceUrlToPersist(VO.getReshItemCts()));
	}

	private void atclUrlToView(OrgReshItemVO VO) {
		VO.setReshItemCts(StringUtil.replaceUrlToBrowser(VO.getReshItemCts()));
	}

	/**
	 * 강의실 설문등록 폼에서 설문은행 목록 조회시 기등록된 설문은 제외하다.
	 */
	@Override
	public	ProcessResultListVO<OrgReshVO> listResearchPreclusive(OrgReshVO VO) throws Exception {
		ProcessResultListVO<OrgReshVO> resultList = new ProcessResultListVO<OrgReshVO>();
		try {
			List<OrgReshVO> returnList = orgReshMapper.listResearchPreclusive(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	@Override
	public ProcessResultVO<?> sortReserchItem(OrgReshItemVO VO) {
		String[] itemList = StringUtil.split(VO.getReshItemSns(),"|");
		// 하위 코드 목록을 한꺼번에 조회List<OrgReshItemVO> itemArray
		List<OrgReshItemVO> itemArray = orgReshItemMapper.list(VO);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (OrgReshItemVO sVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(sVO.getReshItemSn() == Integer.parseInt(itemList[order]) ) {
					sVO.setItemOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		// 변경된 시스템코드 어래이를 일괄 저장.
		orgReshItemMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 설문 문제 목록 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listItem(OrgReshItemVO VO) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
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
			wbook.setSheetName(0, "OrgResearch");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment1"), pageRow1, 0, 43, "left");
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment2"), pageRow2, 0, 43, "left");
			rowNum++;
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment3"), pageRow3, 0, 43, "left");
			rowNum++;
			XSSFRow pageRow4 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment4"), pageRow4, 0, 43, "left");
			rowNum++;
			XSSFRow pageRow5 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titles.get("comment5"), pageRow5, 0, 43, "left");

			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);

			POIExcelUtil.createTitleCell(titles.get("reshItemTypeCd"), titleRow, 0);
			POIExcelUtil.createTitleCell(titles.get("emplViewType"), titleRow, 1);
			POIExcelUtil.createTitleCell(titles.get("emplCnt"), titleRow, 2);
			POIExcelUtil.createTitleCell(titles.get("reshItemCts"), titleRow, 3);
			POIExcelUtil.createTitleCell(titles.get("item1"), titleRow, 4);
			POIExcelUtil.createTitleCell(titles.get("score1"), titleRow, 5);
			POIExcelUtil.createTitleCell(titles.get("item2"), titleRow, 6);
			POIExcelUtil.createTitleCell(titles.get("score2"), titleRow, 7);
			POIExcelUtil.createTitleCell(titles.get("item3"), titleRow, 8);
			POIExcelUtil.createTitleCell(titles.get("score3"), titleRow, 9);
			POIExcelUtil.createTitleCell(titles.get("item4"), titleRow, 10);
			POIExcelUtil.createTitleCell(titles.get("score4"), titleRow, 11);
			POIExcelUtil.createTitleCell(titles.get("item5"), titleRow, 12);
			POIExcelUtil.createTitleCell(titles.get("score5"), titleRow, 13);
			POIExcelUtil.createTitleCell(titles.get("item6"), titleRow, 14);
			POIExcelUtil.createTitleCell(titles.get("score6"), titleRow, 15);
			POIExcelUtil.createTitleCell(titles.get("item7"), titleRow, 16);
			POIExcelUtil.createTitleCell(titles.get("score7"), titleRow, 17);
			POIExcelUtil.createTitleCell(titles.get("item8"), titleRow, 18);
			POIExcelUtil.createTitleCell(titles.get("score8"), titleRow, 19);
			POIExcelUtil.createTitleCell(titles.get("item9"), titleRow, 20);
			POIExcelUtil.createTitleCell(titles.get("score9"), titleRow, 21);
			POIExcelUtil.createTitleCell(titles.get("item10"), titleRow, 22);
			POIExcelUtil.createTitleCell(titles.get("score10"), titleRow, 23);
			POIExcelUtil.createTitleCell(titles.get("item11"), titleRow, 24);
			POIExcelUtil.createTitleCell(titles.get("score11"), titleRow, 25);
			POIExcelUtil.createTitleCell(titles.get("item12"), titleRow, 26);
			POIExcelUtil.createTitleCell(titles.get("score12"), titleRow, 27);
			POIExcelUtil.createTitleCell(titles.get("item13"), titleRow, 28);
			POIExcelUtil.createTitleCell(titles.get("score13"), titleRow, 29);
			POIExcelUtil.createTitleCell(titles.get("item14"), titleRow, 30);
			POIExcelUtil.createTitleCell(titles.get("score14"), titleRow, 31);
			POIExcelUtil.createTitleCell(titles.get("item15"), titleRow, 32);
			POIExcelUtil.createTitleCell(titles.get("score15"), titleRow, 33);
			POIExcelUtil.createTitleCell(titles.get("item16"), titleRow, 34);
			POIExcelUtil.createTitleCell(titles.get("score16"), titleRow, 35);
			POIExcelUtil.createTitleCell(titles.get("item17"), titleRow, 36);
			POIExcelUtil.createTitleCell(titles.get("score17"), titleRow, 37);
			POIExcelUtil.createTitleCell(titles.get("item18"), titleRow, 38);
			POIExcelUtil.createTitleCell(titles.get("score18"), titleRow, 39);
			POIExcelUtil.createTitleCell(titles.get("item19"), titleRow, 40);
			POIExcelUtil.createTitleCell(titles.get("score19"), titleRow, 41);
			POIExcelUtil.createTitleCell(titles.get("item20"), titleRow, 42);
			POIExcelUtil.createTitleCell(titles.get("score20"), titleRow, 43);

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
			sheet.setColumnWidth(24, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(25, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(26, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(27, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(28, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(29, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(30, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(31, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(32, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(33, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(34, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(35, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(36, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(37, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(38, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(39, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(40, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(41, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(42, sheet.getDefaultColumnWidth() * 200);
			sheet.setColumnWidth(43, sheet.getDefaultColumnWidth() * 200);

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}finally{
				try {
					if (os != null) {	os.close(); }
				} catch (Exception e) {
					log.error("Exception occurred");
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
	public ProcessResultListVO<OrgReshItemVO> excelUploadValidationCheck(String fileName,
			String filePath) throws ServiceProcessException {

		ProcessResultListVO<OrgReshItemVO> resultVO = new ProcessResultListVO<OrgReshItemVO>();

		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;

		FileInputStream fis= null;
		try {
			fis=new FileInputStream(filePath + "/" + fileName);
			workbook= new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (IOException ex2) {
			// TODO Auto-generated catch block
			log.error("IOException occurred");
			throw new ServiceProcessException("Failed read excel : " + ex2.getMessage(), ex2);
		}finally{
			try {
				if (fis != null) {	fis.close(); }
			} catch (Exception e) {
				log.error("Exception occurred");
			}
		}
		XSSFRow dfltRow = sheet.getRow(5);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		if(cellCount != 44) {
			resultVO.setResult(-1);
			resultVO.setMessage("ERROR_CNT");
			throw new ServiceProcessException("Failed read excel : Invalid file.");
		} else {
			List<OrgReshItemVO> researchBankList = new ArrayList<OrgReshItemVO>();
			int rows=sheet.getPhysicalNumberOfRows();
			int lineNo = 0;
			for (int rowIndex = 6; rowIndex < rows; rowIndex++) {
				//행을 읽는다 UserInfoVO에 담는다.
			    XSSFRow row=sheet.getRow(rowIndex);
			    
			    if(row != null) {
			    	String errorCode = "";
			    	lineNo++;
					OrgReshItemVO rbVO = new OrgReshItemVO();
					rbVO.setLineNo(Integer.toString(lineNo));
			    	rbVO.setReshItemTypeCd(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(0)).toUpperCase()));
			    	rbVO.setEmplViewType(StringUtil.toHalfChar(POIExcelUtil.getCellValue(row.getCell(1)).toUpperCase()));
			    	rbVO.setEmplCnt(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(2),true),"0"))));
					rbVO.setReshItemCts(POIExcelUtil.getCellValue(row.getCell(3)));
					rbVO.setEmpl1(POIExcelUtil.getCellValue(row.getCell(4)));
					rbVO.setEmplScore1(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(5),true),"0"))));
					rbVO.setEmpl2(POIExcelUtil.getCellValue(row.getCell(6)));
					rbVO.setEmplScore2(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(7),true),"0"))));
					rbVO.setEmpl3(POIExcelUtil.getCellValue(row.getCell(8)));
					rbVO.setEmplScore3(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(9),true),"0"))));
					rbVO.setEmpl4(POIExcelUtil.getCellValue(row.getCell(10)));
					rbVO.setEmplScore4(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(11),true),"0"))));
					rbVO.setEmpl5(POIExcelUtil.getCellValue(row.getCell(12)));
					rbVO.setEmplScore5(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(13),true),"0"))));
					rbVO.setEmpl6(POIExcelUtil.getCellValue(row.getCell(14)));
					rbVO.setEmplScore6(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(15),true),"0"))));
					rbVO.setEmpl7(POIExcelUtil.getCellValue(row.getCell(16)));
					rbVO.setEmplScore7(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(17),true),"0"))));
					rbVO.setEmpl8(POIExcelUtil.getCellValue(row.getCell(18)));
					rbVO.setEmplScore8(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(19),true),"0"))));
					rbVO.setEmpl9(POIExcelUtil.getCellValue(row.getCell(20)));
					rbVO.setEmplScore9(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(21),true),"0"))));
					rbVO.setEmpl10(POIExcelUtil.getCellValue(row.getCell(22)));
					rbVO.setEmplScore10(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(23),true),"0"))));
					rbVO.setEmpl11(POIExcelUtil.getCellValue(row.getCell(24)));
					rbVO.setEmplScore11(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(25),true),"0"))));
					rbVO.setEmpl12(POIExcelUtil.getCellValue(row.getCell(26)));
					rbVO.setEmplScore12(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(27),true),"0"))));
					rbVO.setEmpl13(POIExcelUtil.getCellValue(row.getCell(28)));
					rbVO.setEmplScore13(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(29),true),"0"))));
					rbVO.setEmpl14(POIExcelUtil.getCellValue(row.getCell(30)));
					rbVO.setEmplScore14(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(31),true),"0"))));
					rbVO.setEmpl15(POIExcelUtil.getCellValue(row.getCell(32)));
					rbVO.setEmplScore15(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(33),true),"0"))));
					rbVO.setEmpl16(POIExcelUtil.getCellValue(row.getCell(34)));
					rbVO.setEmplScore16(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(35),true),"0"))));
					rbVO.setEmpl17(POIExcelUtil.getCellValue(row.getCell(36)));
					rbVO.setEmplScore17(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(37),true),"0"))));
					rbVO.setEmpl18(POIExcelUtil.getCellValue(row.getCell(38)));
					rbVO.setEmplScore18(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(39),true),"0"))));
					rbVO.setEmpl19(POIExcelUtil.getCellValue(row.getCell(40)));
					rbVO.setEmplScore19(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(41),true),"0"))));
					rbVO.setEmpl20(POIExcelUtil.getCellValue(row.getCell(42)));
					rbVO.setEmplScore20(Integer.parseInt(StringUtil.toHalfChar(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(43),true),"0"))));

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
							
							if(ValidationUtils.isEmpty(rbVO.getEmplScore1()) ){
								errorCode += "|"+"EMPLSCORE1";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore2()) ){
								errorCode += "|"+"EMPLSCORE2";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore3()) ){
								errorCode += "|"+"EMPLSCORE3";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore4()) ){
								errorCode += "|"+"EMPLSCORE4";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore5()) ){
								errorCode += "|"+"EMPLSCORE5";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore6()) ){
								errorCode += "|"+"EMPLSCORE6";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore7()) ){
								errorCode += "|"+"EMPLSCORE7";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore8()) ){
								errorCode += "|"+"EMPLSCORE8";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore9()) ){
								errorCode += "|"+"EMPLSCORE9";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore10()) ){
								errorCode += "|"+"EMPLSCORE10";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore11()) ){
								errorCode += "|"+"EMPLSCORE11";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore12()) ){
								errorCode += "|"+"EMPLSCORE12";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore13()) ){
								errorCode += "|"+"EMPLSCORE13";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore14()) ){
								errorCode += "|"+"EMPLSCORE14";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore15()) ){
								errorCode += "|"+"EMPLSCORE15";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore16()) ){
								errorCode += "|"+"EMPLSCORE16";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore17()) ){
								errorCode += "|"+"EMPLSCORE17";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore18()) ){
								errorCode += "|"+"EMPLSCORE18";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore19()) ){
								errorCode += "|"+"EMPLSCORE19";
							}
							if(ValidationUtils.isEmpty(rbVO.getEmplScore20()) ){
								errorCode += "|"+"EMPLSCORE20";
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
	public	ProcessResultVO<OrgReshItemVO> addOrgReshItemBatch(OrgReshItemVO VO) throws Exception {
		VO.setReshItemSn(orgReshItemMapper.selectKey());
		orgReshItemMapper.insert(VO);
		return new ProcessResultVO<OrgReshItemVO>().setResultSuccess().setReturnVO(new OrgReshItemVO());

	}

	@Override
	public ProcessResultVO<OrgReshItemVO> addOrgReshItemBatch(
			List<OrgReshItemVO> orgReshList) throws Exception {
		for(OrgReshItemVO VO : orgReshList) {
			VO.setReshItemSn(orgReshItemMapper.selectKey());
			orgReshItemMapper.insert(VO);
		}
		return new ProcessResultVO<OrgReshItemVO>().setResultSuccess().setReturnVO(new OrgReshItemVO());

	}
	
	/**
	 * 일반 설문 답변 저장
	 */
	@Override
	public int addAnsr(OrgReshAnsrVO VO) throws Exception {
		int result = 0;
		try {
			result = orgReshAnsrMapper.insert(VO);
		} catch (Exception e) {
			log.error("Exception occurred");
			result = -1;
		}
		return result;
		
	}
	
	@Override
	public ProcessResultListVO<OrgReshAnsrVO> getAnsrList(OrgReshAnsrVO vo) throws Exception {
		ProcessResultListVO<OrgReshAnsrVO> resultList = new ProcessResultListVO<OrgReshAnsrVO>();
		try {
			List<OrgReshAnsrVO> ansrList = orgReshAnsrMapper.selectAsrList(vo);
			resultList.setResult(1);
			resultList.setReturnList(ansrList);
		} catch (Exception e){
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	/**
	 * 개설 과정 설문 정보 등록
	 */
	@Override
	public	ProcessResultVO<OrgReshAnsrVO> addReshAnsr(OrgReshAnsrVO VO) throws Exception {
		String[] ansrArr = StringUtil.split(VO.getReshAnsrStr(), "|");
		for(int i=1; i<ansrArr.length; i++) {
			String[] ansrStr = StringUtil.split(ansrArr[i],"/");
			OrgReshAnsrVO iVO = new OrgReshAnsrVO();
			iVO.setOrgCd(VO.getOrgCd());
			iVO.setReshSn(VO.getReshSn());
			iVO.setUserNo(VO.getUserNo());
			iVO.setRegNo(VO.getRegNo());
			iVO.setModNo(VO.getModNo());
			iVO.setReshItemSn(Integer.parseInt(ansrStr[0]));
			if("K".equals(ansrStr[3])) {
				iVO.setReshAnsr(ansrStr[1]);
			} else {
				iVO.setReshAnsr(ansrStr[2]);
			}
			orgReshAnsrMapper.insert(iVO);
		}
		return new ProcessResultVO().setResultSuccess();
	}
	/**
	 * 개설 과정 설문 결과 목록, 교육생 설문 응시 답 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshResultVO> listReshAnsr(OrgReshResultVO vo) throws Exception {
		ProcessResultListVO<OrgReshResultVO> resultList = new ProcessResultListVO<OrgReshResultVO>();

		try {
			List<OrgReshResultVO> returnList = orgReshAnsrMapper.listReshAnsr(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	
	/**
	 * 일반 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	@Override
	public	ProcessResultListVO<OrgReshAnsrVO> listExpulsionPageing(OrgReshAnsrVO vo) throws Exception {
		ProcessResultListVO<OrgReshAnsrVO> resultList = new ProcessResultListVO<OrgReshAnsrVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());

		// 전체 목록 수
		int totalCount = orgReshMapper.countPaging(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(orgReshMapper.listExpulsionStdPageing(vo)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 퇴교 설문 답안 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshAnsrVO> reshAnsrList(OrgReshAnsrVO VO) throws Exception {
		ProcessResultListVO<OrgReshAnsrVO> resultList = new ProcessResultListVO<OrgReshAnsrVO>();
		try {
			List<OrgReshAnsrVO> returnList = orgReshAnsrMapper.listAnsrList(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	/**
	 * 일반 설문 통계 리스트 조회
	 * @date 2022. 05. 30.
	 * Jeong Seon joong
	 * @param vo
	 * @return ProcessResultVO<OrgReshItemVO>
	 * @throws Exception 
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listStatResearch(OrgReshItemVO vo) throws Exception {
		
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.listStatResearch(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
			log.error("Exception occurred");
		}
		
		return resultList;

	}
	
	/**
	 * 과목 설문 통계 리스트 조회
	 * @date 2022. 05. 31.
	 * Jeong Seon joong
	 * @param vo
	 * @return ProcessResultVO<OrgReshItemVO>
	 * @throws Exception 
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listStatCrsResearch(OrgReshItemVO vo) throws Exception {
		
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.listStatCrsResearch(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
			log.error("Exception occurred");
		}
		
		return resultList;

	}
	
	
	/**
	 * 일반 설문 은행 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listStatCrsResearchCrsCre(OrgReshItemVO VO) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.listStatCrsResearchCrsCre(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 일반 설문 평가현황 다운로드
	 */
	@Override
	public void listExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os)
			throws Exception {
		// TODO Auto-generated method stub
		List<OrgReshItemVO> resultList = orgReshItemMapper.listStatResearchStdNmExcel(vo);

		OrgReshItemVO itemVo = new OrgReshItemVO();
		List<OrgReshItemVO> resultItemList = new ArrayList<OrgReshItemVO>();
		// 첫 과정 설문 항목 테이블 조회
		if (resultList != null && resultList.size() > 0) {
			itemVo.setReshSn(resultList.get(0).getReshSn());
			resultItemList = orgReshItemMapper.listReshItem(itemVo);
		}

		int colNum = 9;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(orgNm+" "+titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(titleList.size()-1), pageRow2, 0, colNum, "right");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("기수", titleRow, 0);
			POIExcelUtil.createTitleCell("트랙", titleRow, 1);
			POIExcelUtil.createTitleCell("교육지역", titleRow, 2);
			POIExcelUtil.createTitleCell("반", titleRow, 3);
			POIExcelUtil.createTitleCell("에이블ID", titleRow, 4);
			
			POIExcelUtil.createTitleCell("이름", titleRow, 5);
			POIExcelUtil.createTitleCell("교육상태", titleRow, 6);
			//POIExcelUtil.createTitleCell("과목명", titleRow, 7);
			POIExcelUtil.createTitleCell("설문명", titleRow, 7);
			POIExcelUtil.createTitleCell("제출일", titleRow, 8);
			
			POIExcelUtil.createTitleCell("제출상태", titleRow, 9);
			
			/*POIExcelUtil.createTitleCell("문항1제목", titleRow, 10);
			POIExcelUtil.createTitleCell("문항2제목", titleRow, 11);
			POIExcelUtil.createTitleCell("문항3제목", titleRow, 12);
			POIExcelUtil.createTitleCell("문항4제목", titleRow, 13);
			
			POIExcelUtil.createTitleCell("문항5제목", titleRow, 14);
			POIExcelUtil.createTitleCell("문항6제목", titleRow, 15);
			POIExcelUtil.createTitleCell("문항7제목", titleRow, 16);
			POIExcelUtil.createTitleCell("문항8제목", titleRow, 17);
			POIExcelUtil.createTitleCell("문항9제목", titleRow, 18);
			
			POIExcelUtil.createTitleCell("문항10제목", titleRow, 19);
			POIExcelUtil.createTitleCell("문항11제목", titleRow, 20);
			POIExcelUtil.createTitleCell("문항12제목", titleRow, 21);
			POIExcelUtil.createTitleCell("문항13제목", titleRow, 22);
			POIExcelUtil.createTitleCell("문항14제목", titleRow, 23);
			
			POIExcelUtil.createTitleCell("문항15제목", titleRow, 24);
			POIExcelUtil.createTitleCell("문항16제목", titleRow, 25);
			POIExcelUtil.createTitleCell("문항17제목", titleRow, 26);
			POIExcelUtil.createTitleCell("문항18제목", titleRow, 27);
			POIExcelUtil.createTitleCell("문항19제목", titleRow, 28);
			
			POIExcelUtil.createTitleCell("문항20제목", titleRow, 29);
			*/
			
			// 문항 제목 다시 설정
			int startIndex = 10;
			for(int i=0; i<resultItemList.size(); i++){
				POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(i).getReshItemCts()), titleRow, startIndex+(i*2));	//문항제목 찾아 변경
				POIExcelUtil.createTitleCell( "점수", titleRow, startIndex+(i*2)+1);	//문항제목 찾아 변경
			}

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 700);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 150);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 350);
			
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			//sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 300);
			
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 400);		// 제출상태
			/*
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(14, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(15, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(16, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(17, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(18, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(19, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(20, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(21, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(22, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(23, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(24, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(25, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(26, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(27, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(28, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(29, sheet.getDefaultColumnWidth() * 1000);
			*/
			
			//기본 width 세팅 : 20문항 전제
			for(int i = 0; i<20; i++) {
				sheet.setColumnWidth(startIndex + (i*2), sheet.getDefaultColumnWidth() * 1000);//제목 width
				sheet.setColumnWidth(startIndex + (i*2) + 1, sheet.getDefaultColumnWidth() * 200);//점수 width
				
			}

			for(int i=0; i<resultList.size(); i++){
				OrgReshItemVO excelVo = resultList.get(i);
				
				// 설문이 변경되면 과정 설문 항목 테이블 재 조회
				if ( excelVo.getReshSn() != itemVo.getReshSn() ) {
					itemVo.setReshSn(excelVo.getReshSn());
					resultItemList = orgReshItemMapper.listReshItem(itemVo);
					// 설문 head 다시 만들기
					//-- 제목 줄 만들기
					rowNum++;
					XSSFRow secondTitleRow = sheet.createRow((short)rowNum);
					POIExcelUtil.createTitleCell("기수", secondTitleRow, 0);
					POIExcelUtil.createTitleCell("트랙", secondTitleRow, 1);
					POIExcelUtil.createTitleCell("교육지역", secondTitleRow, 2);
					POIExcelUtil.createTitleCell("반", secondTitleRow, 3);
					POIExcelUtil.createTitleCell("에이블ID", secondTitleRow, 4);
					
					POIExcelUtil.createTitleCell("이름", secondTitleRow, 5);
					POIExcelUtil.createTitleCell("교육상태", secondTitleRow, 6);
					//POIExcelUtil.createTitleCell("과목명", secondTitleRow, 7);
					POIExcelUtil.createTitleCell("설문명", secondTitleRow, 7);
					POIExcelUtil.createTitleCell("제출일", secondTitleRow, 8);
					
					POIExcelUtil.createTitleCell("제출상태", secondTitleRow, 9);
					/*
					POIExcelUtil.createTitleCell("문항1제목", secondTitleRow, 10);
					POIExcelUtil.createTitleCell("문항2제목", secondTitleRow, 11);
					POIExcelUtil.createTitleCell("문항3제목", secondTitleRow, 12);
					POIExcelUtil.createTitleCell("문항4제목", secondTitleRow, 13);
					
					POIExcelUtil.createTitleCell("문항5제목", secondTitleRow, 14);
					POIExcelUtil.createTitleCell("문항6제목", secondTitleRow, 15);
					POIExcelUtil.createTitleCell("문항7제목", secondTitleRow, 16);
					POIExcelUtil.createTitleCell("문항8제목", secondTitleRow, 17);
					POIExcelUtil.createTitleCell("문항9제목", secondTitleRow, 18);
					
					POIExcelUtil.createTitleCell("문항10제목", secondTitleRow, 19);
					POIExcelUtil.createTitleCell("문항11제목", secondTitleRow, 20);
					POIExcelUtil.createTitleCell("문항12제목", secondTitleRow, 21);
					POIExcelUtil.createTitleCell("문항13제목", secondTitleRow, 22);
					POIExcelUtil.createTitleCell("문항14제목", secondTitleRow, 23);
					
					POIExcelUtil.createTitleCell("문항15제목", secondTitleRow, 24);
					POIExcelUtil.createTitleCell("문항16제목", secondTitleRow, 25);
					POIExcelUtil.createTitleCell("문항17제목", secondTitleRow, 26);
					POIExcelUtil.createTitleCell("문항18제목", secondTitleRow, 27);
					POIExcelUtil.createTitleCell("문항19제목", secondTitleRow, 28);
					
					POIExcelUtil.createTitleCell("문항20제목", secondTitleRow, 29);
					*/
					// 문항 제목 다시 설정
					for(int l=0; l<resultItemList.size(); l++){
						POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(l).getReshItemCts()), secondTitleRow, startIndex+(l*2));	//문항제목 찾아 변경
						POIExcelUtil.createTitleCell( "점수", secondTitleRow, startIndex+(l*2)+1);	//점수
					}
					
				}
				
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				
				POIExcelUtil.createContentCell(vo.getSearchValue(), row, 0, "center");	//기수명
				POIExcelUtil.createContentCell(excelVo.getCrsCurriNm(), row, 1, "center");
				POIExcelUtil.createContentCell(excelVo.getAreaNm(), row, 2, "center");
				POIExcelUtil.createContentCell(excelVo.getDeclsNoInt()+"반", row, 3, "right");
				POIExcelUtil.createContentCell(excelVo.getUserId(), row, 4, "center");	//에이블ID
				
				POIExcelUtil.createContentCell(excelVo.getUserNm(), row, 5, "center");	//이름
				POIExcelUtil.createContentCell(excelVo.getEnrlStsNm(), row, 6, "left");		//교육상태
				//POIExcelUtil.createContentCell(excelVo.getCrsCreNm(), row, 7, "left");		//과목명
				POIExcelUtil.createContentCell(excelVo.getReshTitle(), row, 7, "left");		//설문명
				POIExcelUtil.createContentCell(excelVo.getAnsrRegDttm(), row, 8, "center");	//제출일
				
				POIExcelUtil.createContentCell((StringUtil.isNull(excelVo.getAnsrUserNo())  ? "미제출" : "제출"), row, 9, "center");	//제출상태
				
				/*POIExcelUtil.createContentCell("", row, 10, "center");	//문항1 답변
				POIExcelUtil.createContentCell("", row, 11, "center");	//문항2 답변
				POIExcelUtil.createContentCell("", row, 12, "center");	//문항3 답변
				POIExcelUtil.createContentCell("", row, 13, "center");	//문항4 답변
				
				POIExcelUtil.createContentCell("", row, 14, "center");	//문항5 답변
				POIExcelUtil.createContentCell("", row, 15, "center");	//문항6 답변
				POIExcelUtil.createContentCell("", row, 16, "center");	//문항7 답변
				POIExcelUtil.createContentCell("", row, 17, "center");	//문항8 답변
				POIExcelUtil.createContentCell("", row, 18, "center");	//문항9 답변
				
				POIExcelUtil.createContentCell("", row, 19, "center");	//문항10 답변
				POIExcelUtil.createContentCell("", row, 20, "center");	//문항11 답변
				POIExcelUtil.createContentCell("", row, 21, "center");	//문항12 답변
				POIExcelUtil.createContentCell("", row, 22, "center");	//문항13 답변
				POIExcelUtil.createContentCell("", row, 23, "center");	//문항14 답변
				
				POIExcelUtil.createContentCell("", row, 24, "center");	//문항15 답변
				POIExcelUtil.createContentCell("", row, 25, "center");	//문항16 답변
				POIExcelUtil.createContentCell("", row, 26, "center");	//문항17 답변
				POIExcelUtil.createContentCell("", row, 27, "center");	//문항18 답변
				POIExcelUtil.createContentCell("", row, 28, "center");	//문항19 답변
				
				POIExcelUtil.createContentCell("", row, 29, "center");	//문항20 답변
				*/
				
				//답변 넣기 START
				int startIndexArr = 10;
				String tempItemStr [] = excelVo.getReshItemSnArr();
				String tempAnsrStr [] = excelVo.getReshAnsrArr();
				if (tempItemStr != null && tempAnsrStr != null) {
					for (int j = 0; j < tempItemStr.length; j++) {
						for (int k = 0; k < resultItemList.size(); k++) {
							OrgReshItemVO tempItem = resultItemList.get(k);
							if(tempItem.getReshSn() == excelVo.getReshSn() && (tempItem.getReshItemSns()).equals(tempItemStr[j])) {
								String tempItemTypeCd = tempItem.getReshItemTypeCd();
								String tempAnsr = StringUtil.isNotNull(tempAnsrStr[j]) ? tempAnsrStr[j] : "";
								String tempScore = tempItem.getScore(tempAnsr);
								if (tempItemTypeCd.equals("K") && StringUtil.isNotNull(tempScore) && !tempScore.equals("0")) {
									POIExcelUtil.createContentCell(tempAnsr, row, startIndexArr+(j*2), "center");	//선택형 답변
									POIExcelUtil.createContentCell(tempScore, row, startIndexArr+(j*2)+1, "center");	//점수
								} else {
									POIExcelUtil.createContentCell(tempAnsr, row, startIndexArr+(j*2), "center");	//서술형 답변
									POIExcelUtil.createContentCell("", row, startIndexArr+(j*2)+1, "center");	//점수
								}
								break;
							}
						}
						
					}
				}
				// 답변 넣기 END
				
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
			throw new Exception("Failed! Create Excel", e);
		}

	}
	
	/**
	 * 과목 설문 평가현황 다운로드
	 */
	@Override
	public void listCrsExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<OrgReshItemVO> resultList = orgReshItemMapper.listStatCrsResearchStdNmExcel(vo);
		
		OrgReshItemVO itemVo = new OrgReshItemVO();
		List<OrgReshItemVO> resultItemList = new ArrayList<OrgReshItemVO>();
		// 첫 과정 설문 항목 테이블 조회
		if (resultList != null && resultList.size() > 0) {
			itemVo.setReshSn(resultList.get(0).getReshSn());
			resultItemList = orgReshItemMapper.listCrsReshItem(itemVo);
		}

		int colNum = 10;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(orgNm+" "+titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(titleList.size()-1), pageRow2, 0, colNum, "right");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			
			POIExcelUtil.createTitleCell("기수", titleRow, 0);
			POIExcelUtil.createTitleCell("트랙", titleRow, 1);
			POIExcelUtil.createTitleCell("교육지역", titleRow, 2);
			POIExcelUtil.createTitleCell("반", titleRow, 3);
			POIExcelUtil.createTitleCell("에이블ID", titleRow, 4);
			
			POIExcelUtil.createTitleCell("이름", titleRow, 5);
			POIExcelUtil.createTitleCell("교육상태", titleRow, 6);
			POIExcelUtil.createTitleCell("과목명", titleRow, 7);
			POIExcelUtil.createTitleCell("설문명", titleRow, 8);
			POIExcelUtil.createTitleCell("제출일", titleRow, 9);
			
			POIExcelUtil.createTitleCell("제출상태", titleRow, 10);
			
			/*POIExcelUtil.createTitleCell("문항1제목", titleRow, 11);
			POIExcelUtil.createTitleCell("문항2제목", titleRow, 12);
			POIExcelUtil.createTitleCell("문항3제목", titleRow, 13);
			POIExcelUtil.createTitleCell("문항4제목", titleRow, 14);
			
			POIExcelUtil.createTitleCell("문항5제목", titleRow, 15);
			POIExcelUtil.createTitleCell("문항6제목", titleRow, 16);
			POIExcelUtil.createTitleCell("문항7제목", titleRow, 17);
			POIExcelUtil.createTitleCell("문항8제목", titleRow, 18);
			POIExcelUtil.createTitleCell("문항9제목", titleRow, 19);
			
			POIExcelUtil.createTitleCell("문항10제목", titleRow, 20);
			POIExcelUtil.createTitleCell("문항11제목", titleRow, 21);
			POIExcelUtil.createTitleCell("문항12제목", titleRow, 22);
			POIExcelUtil.createTitleCell("문항13제목", titleRow, 23);
			POIExcelUtil.createTitleCell("문항14제목", titleRow, 24);
			
			POIExcelUtil.createTitleCell("문항15제목", titleRow, 25);
			POIExcelUtil.createTitleCell("문항16제목", titleRow, 26);
			POIExcelUtil.createTitleCell("문항17제목", titleRow, 27);
			POIExcelUtil.createTitleCell("문항18제목", titleRow, 28);
			POIExcelUtil.createTitleCell("문항19제목", titleRow, 29);
			
			POIExcelUtil.createTitleCell("문항20제목", titleRow, 30);
			*/
			
			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 700);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 150);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 350);
			
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 300);
			
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 400);		// 제출상태
			
			/*sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(14, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(15, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(16, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(17, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(18, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(19, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(20, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(21, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(22, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(23, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(24, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(25, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(26, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(27, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(28, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(29, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(30, sheet.getDefaultColumnWidth() * 1000);
			*/
			
			int startIndex = 11;
			
			//기본 width 세팅 : 20문항 전제
			for(int i = 0; i<20; i++) {
				sheet.setColumnWidth(startIndex + (i*2), sheet.getDefaultColumnWidth() * 1000);//제목 width
				sheet.setColumnWidth(startIndex + (i*2) + 1, sheet.getDefaultColumnWidth() * 200);//점수 width
				
			}
			
			// 문항 제목 다시 설정
			for(int i=0; i<resultItemList.size(); i++){
//				if(!"D".equals(resultItemList.get(i).getReshItemTypeCd())) {//K : 선택형, D : 서술형 -> 설문이 일반형이거나 문항이 서술형일 때 점수 필요 없음
//				}
				
				//POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(i).getReshItemCts()), titleRow, startIndex+i);	//문항제목 찾아 변경
				//POIExcelUtil.createTitleCell( "점수" , titleRow, startIndex+i+1);	//문항제목 찾아 변경
				
				POIExcelUtil.createTitleCell("문항"+ (i*2) +" 제목", titleRow, startIndex + (i*2));
				POIExcelUtil.createTitleCell("점수", titleRow, startIndex + (i*2) + 1);
				
			}
			
			for(int i=0; i<resultList.size(); i++){
				OrgReshItemVO excelVo = resultList.get(i);
				
				// 설문이 변경되면 과정 설문 항목 테이블 재 조회
				if ( excelVo.getReshSn() != itemVo.getReshSn() ) {
					itemVo.setReshSn(excelVo.getReshSn());
					resultItemList = orgReshItemMapper.listCrsReshItem(itemVo);
					// 설문 head 다시 만들기
					//-- 제목 줄 만들기
					rowNum++;
					XSSFRow secondTitleRow = sheet.createRow((short)rowNum);
					POIExcelUtil.createTitleCell("기수", secondTitleRow, 0);
					POIExcelUtil.createTitleCell("트랙", secondTitleRow, 1);
					POIExcelUtil.createTitleCell("교육지역", secondTitleRow, 2);
					POIExcelUtil.createTitleCell("반", secondTitleRow, 3);
					POIExcelUtil.createTitleCell("에이블ID", secondTitleRow, 4);
					
					POIExcelUtil.createTitleCell("이름", secondTitleRow, 5);
					POIExcelUtil.createTitleCell("교육상태", secondTitleRow, 6);
					POIExcelUtil.createTitleCell("과목명", secondTitleRow, 7);
					POIExcelUtil.createTitleCell("설문명", secondTitleRow, 8);
					POIExcelUtil.createTitleCell("제출일", secondTitleRow, 9);
					
					POIExcelUtil.createTitleCell("제출상태", secondTitleRow, 10);
					
					//설문 문항이 없는데 기본 20문항 세팅하는게 맞는건지?
					/*POIExcelUtil.createTitleCell("문항1제목", secondTitleRow, 11);
					POIExcelUtil.createTitleCell("문항2제목", secondTitleRow, 12);
					POIExcelUtil.createTitleCell("문항3제목", secondTitleRow, 13);
					POIExcelUtil.createTitleCell("문항4제목", secondTitleRow, 14);
					
					POIExcelUtil.createTitleCell("문항5제목", secondTitleRow, 15);
					POIExcelUtil.createTitleCell("문항6제목", secondTitleRow, 16);
					POIExcelUtil.createTitleCell("문항7제목", secondTitleRow, 17);
					POIExcelUtil.createTitleCell("문항8제목", secondTitleRow, 18);
					POIExcelUtil.createTitleCell("문항9제목", secondTitleRow, 19);
					
					POIExcelUtil.createTitleCell("문항10제목", secondTitleRow, 20);
					POIExcelUtil.createTitleCell("문항11제목", secondTitleRow, 21);
					POIExcelUtil.createTitleCell("문항12제목", secondTitleRow, 22);
					POIExcelUtil.createTitleCell("문항13제목", secondTitleRow, 23);
					POIExcelUtil.createTitleCell("문항14제목", secondTitleRow, 24);
					
					POIExcelUtil.createTitleCell("문항15제목", secondTitleRow, 25);
					POIExcelUtil.createTitleCell("문항16제목", secondTitleRow, 26);
					POIExcelUtil.createTitleCell("문항17제목", secondTitleRow, 27);
					POIExcelUtil.createTitleCell("문항18제목", secondTitleRow, 28);
					POIExcelUtil.createTitleCell("문항19제목", secondTitleRow, 29);
					
					POIExcelUtil.createTitleCell("문항20제목", secondTitleRow, 30);
					*/
					//POIExcelUtil.createTitleCell("문항"+ i +" 제목", titleRow, startIndex + i);
					
					// 문항 제목 다시 설정
					for(int l=0; l<resultItemList.size(); l++){
						/*if(!"D".equals(resultItemList.get(l).getReshItemTypeCd())) {//서술형일 때, 
							titleIndex = startIndex+(l*2);
						}else {
							titleIndex = startIndex+(l*2);
							scoreIndex = startIndex+(l*2) + 1;
						}*/
						
						
						POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(l).getReshItemCts()) , secondTitleRow, startIndex+(l*2));	//문항제목 찾아 변경
						POIExcelUtil.createTitleCell( "점수" , secondTitleRow, startIndex+(l*2)+1);	//문항점수
					}
					
				}
				
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				
				POIExcelUtil.createContentCell(vo.getSearchValue(), row, 0, "center");	//기수명
				POIExcelUtil.createContentCell(excelVo.getCrsCurriNm(), row, 1, "center");
				POIExcelUtil.createContentCell(excelVo.getAreaNm(), row, 2, "center");
				POIExcelUtil.createContentCell(excelVo.getDeclsNoInt()+"반", row, 3, "right");
				POIExcelUtil.createContentCell(excelVo.getUserId(), row, 4, "center");	//에이블ID
				
				POIExcelUtil.createContentCell(excelVo.getUserNm(), row, 5, "center");	//이름
				POIExcelUtil.createContentCell(excelVo.getEnrlStsNm(), row, 6, "left");		//교육상태
				POIExcelUtil.createContentCell(excelVo.getCrsCreNm(), row, 7, "left");		//과목명
				POIExcelUtil.createContentCell(excelVo.getReshTitle(), row, 8, "left");		//설문명
				POIExcelUtil.createContentCell(excelVo.getAnsrRegDttm(), row, 9, "center");	//제출일
				
				POIExcelUtil.createContentCell((StringUtil.isNull(excelVo.getAnsrUserNo())  ? "미제출" : "제출"), row, 10, "center");	//제출상태
				
				//기본 답변 세팅 필요 없어 보임.
				/*POIExcelUtil.createContentCell("", row, 11, "center");	//문항1 답변
				POIExcelUtil.createContentCell("", row, 12, "center");	//문항2 답변
				POIExcelUtil.createContentCell("", row, 13, "center");	//문항3 답변
				POIExcelUtil.createContentCell("", row, 14, "center");	//문항4 답변
				
				POIExcelUtil.createContentCell("", row, 15, "center");	//문항5 답변
				POIExcelUtil.createContentCell("", row, 16, "center");	//문항6 답변
				POIExcelUtil.createContentCell("", row, 17, "center");	//문항7 답변
				POIExcelUtil.createContentCell("", row, 18, "center");	//문항8 답변
				POIExcelUtil.createContentCell("", row, 19, "center");	//문항9 답변
				
				POIExcelUtil.createContentCell("", row, 20, "center");	//문항10 답변
				POIExcelUtil.createContentCell("", row, 21, "center");	//문항11 답변
				POIExcelUtil.createContentCell("", row, 22, "center");	//문항12 답변
				POIExcelUtil.createContentCell("", row, 23, "center");	//문항13 답변
				POIExcelUtil.createContentCell("", row, 24, "center");	//문항14 답변
				
				POIExcelUtil.createContentCell("", row, 25, "center");	//문항15 답변
				POIExcelUtil.createContentCell("", row, 26, "center");	//문항16 답변
				POIExcelUtil.createContentCell("", row, 27, "center");	//문항17 답변
				POIExcelUtil.createContentCell("", row, 28, "center");	//문항18 답변
				POIExcelUtil.createContentCell("", row, 29, "center");	//문항19 답변
				
				POIExcelUtil.createContentCell("", row, 30, "center");	//문항20 답변
				*/
				//답변 넣기 START
				int startIndexArr = 11;
				String tempItemStr [] = excelVo.getReshItemSnArr();
				String tempAnsrStr [] = excelVo.getReshAnsrArr();
				if (tempItemStr != null && tempAnsrStr != null) {
					for (int j = 0; j < tempItemStr.length; j++) {
						for (int k = 0; k < resultItemList.size(); k++) {
							OrgReshItemVO tempItem = resultItemList.get(k);
							if(tempItem.getReshSn() == excelVo.getReshSn() && (tempItem.getReshItemSns()).equals(tempItemStr[j])) {
								String tempItemTypeCd = tempItem.getReshItemTypeCd();
								String tempAnsr = StringUtil.isNotNull(tempAnsrStr[j]) ? tempAnsrStr[j] : "";
								String tempScore = tempItem.getScore(tempAnsr);
								
								if (tempItemTypeCd.equals("K") && StringUtil.isNotNull(tempScore) && !tempScore.equals("0")) {
									POIExcelUtil.createContentCell(tempAnsr, row, startIndexArr+(j*2), "center");	//선택형 답변
									POIExcelUtil.createContentCell(tempScore, row, startIndexArr+(j*2)+1, "center");	//점수
								} else {
									POIExcelUtil.createContentCell(tempAnsr, row, startIndexArr+(j*2), "center");	//서술형 답변
									POIExcelUtil.createContentCell("", row, startIndexArr+(j*2)+1, "center");	
								}
								
								break;
							}
						}
						
					}
				}
				// 답변 넣기 END
				
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
			throw new Exception("Failed! Create Excel", e);
		}

	}
	
	/**
	 * 기수의 퇴교 설문 조회 
	 */
	@Override
	public	ProcessResultVO<OrgReshVO> expulsionResh(OrgReshVO VO) throws Exception {
		ProcessResultVO<OrgReshVO> resultVO = new ProcessResultVO<OrgReshVO>();
		try {
			OrgReshVO returnVO = orgReshMapper.expulsionResh(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 설문결과 점수결과보기
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listRsltScore(OrgReshItemVO vo) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.listRsltScore(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 설문결과 결과보기
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listRslt(OrgReshItemVO vo) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList = orgReshItemMapper.listRsltScore(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 설문결과 점수 다운로드
	 */
	@Override
	public void listExcelScoreDownload(OrgReshItemVO vo, String orgNm, OutputStream os) throws Exception {
		// TODO Auto-generated method stub
		List<OrgReshItemVO> resultList = orgReshItemMapper.listReshScoreExcel(vo);

		List<OrgReshItemVO> resultItemList = new ArrayList<OrgReshItemVO>();
		// 과정 설문 항목 테이블 조회
		resultItemList = orgReshItemMapper.listReshItem(vo);

		int colNum = 1+(resultItemList != null ? resultItemList.size()*2 : 0);

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+"설문 응답 점수 결과");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(vo.getReshTitle()+" "+"[설문 응답 결과]", pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(vo.getSearchValue(), pageRow2, 0, colNum, "left");
			
			rowNum++;	
			XSSFRow pageRow3 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell("총 " +  (resultItemList != null ? resultItemList.size() : 0) + "문항, 총 " + vo.getReshCnt() + "명 응시 ", pageRow3, 0, colNum, "left");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			
			POIExcelUtil.createMergeLengthTitleCell("응시자", titleRow, 3,4,0,0,"center");
			POIExcelUtil.createMergeLengthTitleCell("제출일시", titleRow,3,4,1,1,"center");
			
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			
			int j=0;
			
			for(int i=0; i<resultItemList.size(); i++) {
				POIExcelUtil.createMergeWidthContentCell(String.valueOf(i+1), titleRow, i+j+2, i+j+3);
				sheet.setColumnWidth(i+j+2, sheet.getDefaultColumnWidth() * 1200);
				sheet.setColumnWidth(i+j+3, sheet.getDefaultColumnWidth() * 200);
				j++;
			}
			
			rowNum++;
			
			XSSFRow titleRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createContentCell("", titleRow2, 0, "center");
			POIExcelUtil.createContentCell("", titleRow2, 1, "center");
			
			j=0;
			
			for(int i=0; i<resultItemList.size(); i++) {
				OrgReshItemVO tempVo = resultItemList.get(i);
				POIExcelUtil.createContentCell(HtmlCleaner.cleanEntityRefs(tempVo.getReshItemCts()), titleRow2, i+j+2, "center");
				POIExcelUtil.createContentCell("점수", titleRow2, i+j+3, "center");
				j++;
			}
			
			XSSFRow row = null;
			for(int i=0; i<resultList.size(); i++){
				OrgReshItemVO excelVo = resultList.get(i);
				if (i==0 || !excelVo.getUserNo().equals(resultList.get(i-1).getUserNo())) {
					rowNum++;
					row = sheet.createRow((short)rowNum);
					POIExcelUtil.createContentCell(excelVo.getUserNm(), row, 0, "center");		//이름
					POIExcelUtil.createContentCell(excelVo.getAnsrRegDttm(), row, 1, "center");	//제출일
					for(int o=0; o<resultItemList.size(); o++){
						POIExcelUtil.createContentCell("", row, 2+(o*2), "center");				//문항 빈칸 만들기(단순 테두리 선 그리기)
						POIExcelUtil.createContentCell("", row, 3+(o*2), "center");				//점수 빈칸 만들기(단순 테두리 선 그리기)
					}
				}
				for(int o=0; o<resultItemList.size(); o++){
					OrgReshItemVO itemVo = resultItemList.get(o);
					
					if ( excelVo.getItemOdr().equals(itemVo.getItemOdr()) ) {
						String tempScore = "";
						POIExcelUtil.createContentCell(excelVo.getReshAnsr(), row, 2+(o*2), "center");	//문항 답변
						
						if (excelVo.getReshType().equals("I") && excelVo.getReshItemTypeCd().equals("K") && excelVo.getReshAnsr() != null) {	//점수 선택형일 경우만 점수 찾아서 출력
							if (excelVo.getReshAnsr().equals("1")) { tempScore = itemVo.getEmplScore1().toString();
							} else if (excelVo.getReshAnsr().equals("2")) { tempScore = itemVo.getEmplScore2().toString();
							} else if (excelVo.getReshAnsr().equals("3")) { tempScore = itemVo.getEmplScore3().toString();
							} else if (excelVo.getReshAnsr().equals("4")) { tempScore = itemVo.getEmplScore4().toString();
							} else if (excelVo.getReshAnsr().equals("5")) { tempScore = itemVo.getEmplScore5().toString();
							} else if (excelVo.getReshAnsr().equals("6")) { tempScore = itemVo.getEmplScore6().toString();
							} else if (excelVo.getReshAnsr().equals("7")) { tempScore = itemVo.getEmplScore7().toString();
							} else if (excelVo.getReshAnsr().equals("8")) { tempScore = itemVo.getEmplScore8().toString();
							} else if (excelVo.getReshAnsr().equals("9")) { tempScore = itemVo.getEmplScore9().toString();
							} else if (excelVo.getReshAnsr().equals("10")) { tempScore = itemVo.getEmplScore10().toString();
							} else if (excelVo.getReshAnsr().equals("11")) { tempScore = itemVo.getEmplScore11().toString();
							} else if (excelVo.getReshAnsr().equals("12")) { tempScore = itemVo.getEmplScore12().toString();
							} else if (excelVo.getReshAnsr().equals("13")) { tempScore = itemVo.getEmplScore13().toString();
							} else if (excelVo.getReshAnsr().equals("14")) { tempScore = itemVo.getEmplScore14().toString();
							} else if (excelVo.getReshAnsr().equals("15")) { tempScore = itemVo.getEmplScore15().toString();
							} else if (excelVo.getReshAnsr().equals("16")) { tempScore = itemVo.getEmplScore16().toString();
							} else if (excelVo.getReshAnsr().equals("17")) { tempScore = itemVo.getEmplScore17().toString();
							} else if (excelVo.getReshAnsr().equals("18")) { tempScore = itemVo.getEmplScore18().toString();
							} else if (excelVo.getReshAnsr().equals("19")) { tempScore = itemVo.getEmplScore19().toString();
							} else if (excelVo.getReshAnsr().equals("20")) { tempScore = itemVo.getEmplScore20().toString();
							}
							POIExcelUtil.createContentCell(tempScore, row, 3+(o*2), "center");	//문항 점수
						}
						
					} // end if
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
			throw new Exception("Failed! Create Excel", e);
		}

	}
	
	/**
	 * 교육생 목록 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws Exception 
	 */
	@Override
	public void listOutExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception {

		List<OrgReshItemVO> resultList = orgReshItemMapper.listStatOutResearchStdNmExcel(vo);

		OrgReshItemVO itemVo = new OrgReshItemVO();
		List<OrgReshItemVO> resultItemList = new ArrayList<OrgReshItemVO>();
		// 첫 과정 설문 항목 테이블 조회
		if (resultList != null && resultList.size() > 0) {
			itemVo.setReshSn(resultList.get(0).getReshSn());
			resultItemList = orgReshItemMapper.listReshItem(itemVo);
		}

		int colNum = 9;

		try{
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();

			//-- 첫번째 시트명 셋팅
			wbook.setSheetName(0, orgNm+" "+titleList.get(0));

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell(orgNm+" "+titleList.get(0), pageRow1, 0, colNum);

			//-- 검색 조건 줄
			rowNum++;
			XSSFRow pageRow2 = sheet.createRow((short)rowNum);
			POIExcelUtil.createMergeCell(titleList.get(titleList.size()-1), pageRow2, 0, colNum, "right");

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("기수", titleRow, 0);
			POIExcelUtil.createTitleCell("트랙", titleRow, 1);
			POIExcelUtil.createTitleCell("교육지역", titleRow, 2);
			POIExcelUtil.createTitleCell("반", titleRow, 3);
			POIExcelUtil.createTitleCell("에이블ID", titleRow, 4);
			
			POIExcelUtil.createTitleCell("이름", titleRow, 5);
			POIExcelUtil.createTitleCell("교육상태", titleRow, 6);
			POIExcelUtil.createTitleCell("퇴교신청일자", titleRow, 7);
			POIExcelUtil.createTitleCell("퇴교일자", titleRow, 8);
			
			/*
			POIExcelUtil.createTitleCell("문항1제목", titleRow, 9);
			POIExcelUtil.createTitleCell("문항2제목", titleRow, 10);
			POIExcelUtil.createTitleCell("문항3제목", titleRow, 11);
			POIExcelUtil.createTitleCell("문항4제목", titleRow, 12);
			POIExcelUtil.createTitleCell("문항5제목", titleRow, 13);
			POIExcelUtil.createTitleCell("문항6제목", titleRow, 14);
			POIExcelUtil.createTitleCell("문항7제목", titleRow, 15);
			POIExcelUtil.createTitleCell("문항8제목", titleRow, 16);
			POIExcelUtil.createTitleCell("문항9제목", titleRow, 17);
			POIExcelUtil.createTitleCell("문항10제목", titleRow, 18);
			POIExcelUtil.createTitleCell("문항11제목", titleRow, 19);
			POIExcelUtil.createTitleCell("문항12제목", titleRow, 20);
			POIExcelUtil.createTitleCell("문항13제목", titleRow, 21);
			POIExcelUtil.createTitleCell("문항14제목", titleRow, 22);
			POIExcelUtil.createTitleCell("문항15제목", titleRow, 23);
			POIExcelUtil.createTitleCell("문항16제목", titleRow, 24);
			POIExcelUtil.createTitleCell("문항17제목", titleRow, 25);
			POIExcelUtil.createTitleCell("문항18제목", titleRow, 26);
			POIExcelUtil.createTitleCell("문항19제목", titleRow, 27);
			POIExcelUtil.createTitleCell("문항20제목", titleRow, 28);
			*/
			
			// 문항 제목 다시 설정
			int startIndex = 9;
			for(int i=0; i<resultItemList.size(); i++){
				if(ValidationUtils.isNotEmpty(resultItemList.get(i).getReshItemCts())) {
					POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(i).getReshItemCts()), titleRow, startIndex);	//문항제목 찾아 변경
					startIndex++;
				}
			}
			POIExcelUtil.createTitleCell("담당매니저", titleRow, startIndex);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 700);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 150);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 350);
			
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 400);
			sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 300);
			
			sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 400);		// 제출상태
			sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(13, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(14, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(15, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(16, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(17, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(18, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(19, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(20, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(21, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(22, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(23, sheet.getDefaultColumnWidth() * 1000);
			
			sheet.setColumnWidth(24, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(25, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(26, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(27, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(28, sheet.getDefaultColumnWidth() * 1000);
			
			for(int i=0; i<resultList.size(); i++){
				OrgReshItemVO excelVo = resultList.get(i);
				
				// 설문이 변경되면 과정 설문 항목 테이블 재 조회
				if ( excelVo.getReshSn() != itemVo.getReshSn() ) {
					itemVo.setReshSn(excelVo.getReshSn());
					resultItemList = orgReshItemMapper.listReshItem(itemVo);
					// 설문 head 다시 만들기
					//-- 제목 줄 만들기
					rowNum++;
					XSSFRow secondTitleRow = sheet.createRow((short)rowNum);
					POIExcelUtil.createTitleCell("기수", secondTitleRow, 0);
					POIExcelUtil.createTitleCell("트랙", secondTitleRow, 1);
					POIExcelUtil.createTitleCell("교육지역", secondTitleRow, 2);
					POIExcelUtil.createTitleCell("반", secondTitleRow, 3);
					POIExcelUtil.createTitleCell("에이블ID", secondTitleRow, 4);
					
					POIExcelUtil.createTitleCell("이름", secondTitleRow, 5);
					POIExcelUtil.createTitleCell("교육상태", secondTitleRow, 6);
					POIExcelUtil.createTitleCell("퇴교신청일자", secondTitleRow, 7);
					POIExcelUtil.createTitleCell("퇴교일자", secondTitleRow, 8);
					
					/*
					POIExcelUtil.createTitleCell("문항1제목", secondTitleRow, 9);
					POIExcelUtil.createTitleCell("문항2제목", secondTitleRow, 10);
					POIExcelUtil.createTitleCell("문항3제목", secondTitleRow, 11);
					POIExcelUtil.createTitleCell("문항4제목", secondTitleRow, 12);
					POIExcelUtil.createTitleCell("문항5제목", secondTitleRow, 13);
					POIExcelUtil.createTitleCell("문항6제목", secondTitleRow, 14);
					POIExcelUtil.createTitleCell("문항7제목", secondTitleRow, 15);
					POIExcelUtil.createTitleCell("문항8제목", secondTitleRow, 16);
					POIExcelUtil.createTitleCell("문항9제목", secondTitleRow, 17);
					POIExcelUtil.createTitleCell("문항10제목", secondTitleRow, 18);
					POIExcelUtil.createTitleCell("문항11제목", secondTitleRow, 19);
					POIExcelUtil.createTitleCell("문항12제목", secondTitleRow, 20);
					POIExcelUtil.createTitleCell("문항13제목", secondTitleRow, 21);
					POIExcelUtil.createTitleCell("문항14제목", secondTitleRow, 22);
					POIExcelUtil.createTitleCell("문항15제목", secondTitleRow, 23);
					POIExcelUtil.createTitleCell("문항16제목", secondTitleRow, 24);
					POIExcelUtil.createTitleCell("문항17제목", secondTitleRow, 25);
					POIExcelUtil.createTitleCell("문항18제목", secondTitleRow, 26);
					POIExcelUtil.createTitleCell("문항19제목", secondTitleRow, 27);
					POIExcelUtil.createTitleCell("문항20제목", secondTitleRow, 28);
					*/
					
					// 문항 제목 다시 설정
					startIndex = 9;
					for(int l=0; l<resultItemList.size(); l++){
						if(ValidationUtils.isNotEmpty(resultItemList.get(l).getReshItemCts())) {
							POIExcelUtil.createTitleCell( HtmlCleaner.cleanEntityRefs(resultItemList.get(l).getReshItemCts()), secondTitleRow, startIndex);	//문항제목 찾아 변경
							startIndex++;
						}
					}
					POIExcelUtil.createTitleCell("담당매니저", secondTitleRow, startIndex);
				}
				
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				
				POIExcelUtil.createContentCell(vo.getSearchValue(), row, 0, "center");	//기수명
				POIExcelUtil.createContentCell(excelVo.getCrsCurriNm(), row, 1, "center");
				POIExcelUtil.createContentCell(excelVo.getAreaNm(), row, 2, "center");
				POIExcelUtil.createContentCell(excelVo.getDeclsNoInt()+"반", row, 3, "right");
				POIExcelUtil.createContentCell(excelVo.getUserId(), row, 4, "center");	//에이블ID
				
				POIExcelUtil.createContentCell(excelVo.getUserNm(), row, 5, "center");	//이름
				POIExcelUtil.createContentCell(excelVo.getEnrlStsNm(), row, 6, "left");		//교육상태
				POIExcelUtil.createContentCell("퇴교신청일자", row, 7, "left");		//설문명
				POIExcelUtil.createContentCell("퇴교일자", row, 8, "center");	//제출일
				
				/*
				POIExcelUtil.createContentCell("", row, 9, "center");	//문항1 답변
				POIExcelUtil.createContentCell("", row, 10, "center");	//문항2 답변
				POIExcelUtil.createContentCell("", row, 11, "center");	//문항3 답변
				POIExcelUtil.createContentCell("", row, 12, "center");	//문항4 답변
				POIExcelUtil.createContentCell("", row, 13, "center");	//문항5 답변
				POIExcelUtil.createContentCell("", row, 14, "center");	//문항6 답변
				POIExcelUtil.createContentCell("", row, 15, "center");	//문항7 답변
				POIExcelUtil.createContentCell("", row, 16, "center");	//문항8 답변
				POIExcelUtil.createContentCell("", row, 17, "center");	//문항9 답변
				POIExcelUtil.createContentCell("", row, 18, "center");	//문항10 답변
				POIExcelUtil.createContentCell("", row, 19, "center");	//문항11 답변
				POIExcelUtil.createContentCell("", row, 20, "center");	//문항12 답변
				POIExcelUtil.createContentCell("", row, 21, "center");	//문항13 답변
				POIExcelUtil.createContentCell("", row, 22, "center");	//문항14 답변
				POIExcelUtil.createContentCell("", row, 23, "center");	//문항15 답변
				POIExcelUtil.createContentCell("", row, 24, "center");	//문항16 답변
				POIExcelUtil.createContentCell("", row, 25, "center");	//문항17 답변
				POIExcelUtil.createContentCell("", row, 26, "center");	//문항18 답변
				POIExcelUtil.createContentCell("", row, 27, "center");	//문항19 답변
				POIExcelUtil.createContentCell("", row, 28, "center");	//문항20 답변
				*/
				
				//답변 넣기 START
				int startIndexArr = 9;
				String tempItemStr [] = excelVo.getReshItemSnArr();
				String tempAnsrStr [] = excelVo.getReshAnsrArr();
				if (tempItemStr != null && tempAnsrStr != null) {
					for (int j = 0; j < tempItemStr.length; j++) {
						for (int k = 0; k < resultItemList.size(); k++) {
							OrgReshItemVO tempItem = resultItemList.get(k);
							if(tempItem.getReshSn() == excelVo.getReshSn() && (tempItem.getReshItemSns()).equals(tempItemStr[j])) {
								String tempItemTypeCd = tempItem.getReshItemTypeCd();
								String tempAnsr = StringUtil.isNotNull(tempAnsrStr[j]) ? tempAnsrStr[j] : "";
								String tempScore = tempItem.getScore(tempAnsr);
								if (tempItemTypeCd.equals("K") && StringUtil.isNotNull(tempScore) && !tempScore.equals("0")) {
									POIExcelUtil.createContentCell(tempAnsr+"("+tempScore+")", row, startIndexArr, "center");	//선택형 답변
								} else {
									POIExcelUtil.createContentCell(tempAnsr, row, startIndexArr, "center");	//서술형 답변
								}
								startIndexArr++;
								break;
							}
						}
						
					}
				}
				
				String mng = "";
				if(ValidationUtils.isNotEmpty(excelVo.getMngNo())) {
					mng = excelVo.getMngNm()+"("+excelVo.getMngId()+")";
				}
				POIExcelUtil.createContentCell(mng, row, startIndexArr, "center");	//제출일
				// 답변 넣기 END
				
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
			throw new Exception("Failed! Create Excel", e);
		}
	}
	
	/**
	 * 개설 일반 설문 의견 응답 목록 조회
	 */
	@Override
	public	ProcessResultListVO<OrgReshItemVO> listOpinion(OrgReshItemVO VO) throws Exception {
		ProcessResultListVO<OrgReshItemVO> resultList = new ProcessResultListVO<OrgReshItemVO>();
		try {
			List<OrgReshItemVO> returnList =  orgReshItemMapper.listOpinion(VO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}
	
}