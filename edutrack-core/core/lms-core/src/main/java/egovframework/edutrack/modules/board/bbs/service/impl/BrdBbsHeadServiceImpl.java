package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>게시판 - 게시판 머릿말 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("brdBbsHeadService")
public class BrdBbsHeadServiceImpl 
	extends EgovAbstractServiceImpl implements BrdBbsHeadService {

	/** Mapper */
    @Resource(name="brdBbsHeadMapper")
    private BrdBbsHeadMapper 		brdBbsHeadMapper;
    
    /**
	 *  게시판 머릿말 전체 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsHeadVO> list(BrdBbsHeadVO vo) throws Exception {
		ProcessResultListVO<BrdBbsHeadVO> resultList = new ProcessResultListVO<BrdBbsHeadVO>(); 
		try {
			List<BrdBbsHeadVO> bbsHeadList =  brdBbsHeadMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsHeadList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsHeadVO> listPageing(BrdBbsHeadVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdBbsHeadVO> resultList = new ProcessResultListVO<BrdBbsHeadVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdBbsHeadMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdBbsHeadVO> bbsHeadList =  brdBbsHeadMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsHeadList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsHeadVO> listPageing(BrdBbsHeadVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsHeadVO> listPageing(BrdBbsHeadVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 게시판 머릿말 상세 정보를 조회한다.
	 * @param BrdBbsHeadVO
	 * @return BrdBbsHeadVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsHeadVO view(BrdBbsHeadVO vo) throws Exception {
		return brdBbsHeadMapper.select(vo);
	}
	
	/**
	 * 게시판 머릿말 정보를 등록한다.
	 * @param BrdBbsHeadVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String add(BrdBbsHeadVO vo) throws Exception {
		if("Y".equals(vo.getAutoMakeYn())) {
			vo.setHeadCd(brdBbsHeadMapper.selectKey());
		}
		int insert = brdBbsHeadMapper.insert(vo);
		String result = Integer.toString(insert);
		return result;
	}	
	
	/**
	 * 게시판 머릿말 정보를 수정한다.
	 * @param BrdBbsHeadVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(BrdBbsHeadVO vo) throws Exception {
		return brdBbsHeadMapper.update(vo);
	}
	
	/**
	 * 게시판 머릿말 정보를 삭제 한다.
	 * @param BrdBbsHeadVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(BrdBbsHeadVO vo) throws Exception {
		return brdBbsHeadMapper.delete(vo);
	}
}
