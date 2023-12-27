package egovframework.edutrack.modules.lecture.project.board.cmnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntService;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
@Service("prjBbsCmntService")
public class PrjBbsCmntServiceImpl extends EgovAbstractServiceImpl implements PrjBbsCmntService {
	
	@Resource(name="prjBbsCmntMapper")
	private PrjBbsCmntMapper prjBbsCmntMapper;

	/**
	 * 게시글 댓글 목록 paging
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<PrjBbsCmntVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjBbsCmntVO> listPageing(PrjBbsCmntVO vo, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<PrjBbsCmntVO> resultList = new ProcessResultListVO<PrjBbsCmntVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = prjBbsCmntMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<PrjBbsCmntVO> returnList =  prjBbsCmntMapper.listPageing(vo);
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
	 * 게시글 댓글 조회
	 * @author mhShin 
	 * @date 2013. 12. 02.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsCmntVO> view(PrjBbsCmntVO vo) throws Exception {
		ProcessResultVO<PrjBbsCmntVO> resultVO = new ProcessResultVO<PrjBbsCmntVO>();
		try {
			PrjBbsCmntVO returnVO = prjBbsCmntMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 게시글 댓글 등록
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsCmntVO> add(PrjBbsCmntVO vo) throws Exception {
		ProcessResultVO<PrjBbsCmntVO> resultVO = new ProcessResultVO<PrjBbsCmntVO>();
		try {
			//---- 신규 코드 세팅
			vo.setCmntSn(prjBbsCmntMapper.selectKey());
			
			prjBbsCmntMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 게시글 댓글 삭제
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<?>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<?> remove(PrjBbsCmntVO vo) throws Exception {
		ProcessResultVO<PrjBbsCmntVO> resultVO = new ProcessResultVO<PrjBbsCmntVO>();
		try {
			prjBbsCmntMapper.delete(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

}
