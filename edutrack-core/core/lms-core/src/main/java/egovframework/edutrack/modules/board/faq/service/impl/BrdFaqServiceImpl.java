package egovframework.edutrack.modules.board.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqService;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>게시판 - 게시판 FAQ 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("brdFaqService")
public class BrdFaqServiceImpl 
	extends EgovAbstractServiceImpl implements BrdFaqService {

	private final class NestedFileHandler
		implements FileHandler<BrdFaqAtclVO> {
	
		@Override
		public String getPK(BrdFaqAtclVO dto) {
			return dto.getAtclSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "FAQ_ATCL";
		}
	
		@Override
		public List<SysFileVO> getFiles(BrdFaqAtclVO dto) {
			List<SysFileVO> fileList = dto.getAttachImages();
			fileList.addAll(dto.getAttachFiles());
			return fileList;
		}
	
		@Override
		public BrdFaqAtclVO setFiles(BrdFaqAtclVO dto, FileListVO fileListVO) {
			dto.setAttachImages(fileListVO.getFiles("image"));
			dto.setAttachFiles(fileListVO.getFiles("file"));
			return dto;
		}
	}
	
    @Resource(name="brdFaqCtgrMapper")
    private BrdFaqCtgrMapper 		brdFaqCtgrMapper;
    
    @Resource(name="brdFaqAtclMapper")
    private BrdFaqAtclMapper 		brdFaqAtclMapper;

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listCtgr(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO)
	 */
	@Override
	public ProcessResultListVO<BrdFaqCtgrVO> listCtgr(BrdFaqCtgrVO vo) throws Exception {
		ProcessResultListVO<BrdFaqCtgrVO> resultList = new ProcessResultListVO<BrdFaqCtgrVO>(); 
		try {
			List<BrdFaqCtgrVO> faqCtgrList =  brdFaqCtgrMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(faqCtgrList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listCtgrPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO, int, int, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(BrdFaqCtgrVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdFaqCtgrVO> resultList = new ProcessResultListVO<BrdFaqCtgrVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdFaqCtgrMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdFaqCtgrVO> faqCtgrList =  brdFaqCtgrMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(faqCtgrList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listCtgrPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO, int, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(BrdFaqCtgrVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listCtgrPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(BrdFaqCtgrVO vo, 
			int pageIndex) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#viewCtgr(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO)
	 */
	@Override
	public BrdFaqCtgrVO viewCtgr(BrdFaqCtgrVO vo) throws Exception {
		return brdFaqCtgrMapper.select(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#addCtgr(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO)
	 */
	@Override
	public String addCtgr(BrdFaqCtgrVO vo) throws Exception {
		if("Y".equals(vo.getAutoMakeYn())) {
			vo.setCtgrCd(brdFaqCtgrMapper.selectKey(vo));
		}
		int insert = brdFaqCtgrMapper.insert(vo);
		String result = Integer.toString(insert);
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#editCtgr(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO)
	 */
	@Override
	public int editCtgr(BrdFaqCtgrVO vo) throws Exception {
		return brdFaqCtgrMapper.update(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#removeCtgr(egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO)
	 */
	@Override
	public int removeCtgr(BrdFaqCtgrVO vo) throws Exception {
		return brdFaqCtgrMapper.delete(vo);
	}
	
	@Override
	public int sortCtgr(BrdFaqCtgrVO vo) throws Exception {
		int result = 1;		
		String[] itemList = StringUtil.split(vo.getCtgrCd(),"|");
		// 하위 코드 목록을 한꺼번에 조회
		List<BrdFaqCtgrVO> itemArray = brdFaqCtgrMapper.list(vo);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (BrdFaqCtgrVO svo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(svo.getCtgrCd().equals(itemList[order])) {
					svo.setCtgrOdr(order+1);	// 1부터 차례로 순서값을 지정
					brdFaqCtgrMapper.update(svo);
				}
			}
		}
		return result;
	}
	
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public ProcessResultListVO<BrdFaqAtclVO> listAtcl(BrdFaqAtclVO vo) throws Exception {
		ProcessResultListVO<BrdFaqAtclVO> resultList = new ProcessResultListVO<BrdFaqAtclVO>(); 
		try {
			List<BrdFaqAtclVO> faqAtclList =  brdFaqAtclMapper.list(vo);
//			for(BrdFaqAtclVO svo : faqAtclList) {
//				svo = sysFileService.getFile(svo, new NestedFileHandler());
//			}			
			resultList.setResult(1);
			resultList.setReturnList(faqAtclList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listAtclPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO, int, int, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(BrdFaqAtclVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdFaqAtclVO> resultList = new ProcessResultListVO<BrdFaqAtclVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdFaqAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			
			List<BrdFaqAtclVO> returnList =  brdFaqAtclMapper.listPageing(vo);
			List<BrdFaqAtclVO> faqAtclList = new ArrayList<BrdFaqAtclVO>();
			for(BrdFaqAtclVO svo : returnList) {
				svo = sysFileService.getFile(svo, new NestedFileHandler());
				this.atclUrlToView(svo);
				faqAtclList.add(svo);
			}			
			resultList.setResult(1);
			resultList.setReturnList(faqAtclList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listAtclPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO, int, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(BrdFaqAtclVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listAtclPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#listAtclPageing(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO, int)
	 */
	@Override
	public ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(BrdFaqAtclVO vo, 
			int pageIndex) throws Exception {
		return this.listAtclPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#viewAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public BrdFaqAtclVO viewAtcl(BrdFaqAtclVO vo) throws Exception {
		vo = brdFaqAtclMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		this.atclUrlToView(vo);		
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#addAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public String addAtcl(BrdFaqAtclVO vo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getAtclSn())) {
			int atclSn = brdFaqAtclMapper.selectKey();
			vo.setAtclSn(atclSn);
		}
		// 첨부 이미지 URL 변경
		this.atclUrlToPersist(vo);
		int insert = brdFaqAtclMapper.insert(vo);
		String result = Integer.toString(insert);
		
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());		
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#editAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public int editAtcl(BrdFaqAtclVO vo) throws Exception {
		this.atclUrlToPersist(vo);	// URL 변환
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return brdFaqAtclMapper.update(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#sortAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public int sortAtcl(BrdFaqAtclVO vo) throws Exception {
		int result = 1;		
		String[] itemList = StringUtil.split(vo.getAtclSns(),"|");
		// 하위 코드 목록을 한꺼번에 조회
		List<BrdFaqAtclVO> itemArray = brdFaqAtclMapper.list(vo);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (BrdFaqAtclVO svo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(svo.getAtclSn().equals(Integer.parseInt(itemList[order]))) {
					svo.setAtclOdr(order+1);	// 1부터 차례로 순서값을 지정
					brdFaqAtclMapper.update(svo);
				}
			}
		}
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.board.faq.service.impl.BrdFaqService#removeAtcl(egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO)
	 */
	@Override
	public int removeAtcl(BrdFaqAtclVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		return brdFaqAtclMapper.delete(vo);
	}
	
	
	
	private void atclUrlToPersist(BrdFaqAtclVO vo) {
		vo.setAtclCts(StringUtil.replaceUrlToPersist(vo.getAtclCts()));
	}

	private void atclUrlToView(BrdFaqAtclVO vo) {
		vo.setAtclCts(StringUtil.replaceUrlToBrowser(vo.getAtclCts()));
	}
	
}
