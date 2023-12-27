package egovframework.edutrack.modules.course.crsbbs.cmnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.crsbbs.cmnt.service.CrsBbsCmntService;
import egovframework.edutrack.modules.course.crsbbs.cmnt.service.CrsBbsCmntVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("crsBbsCmntService")
public class CrsBbsCmntServiceImpl
	extends EgovAbstractServiceImpl implements  CrsBbsCmntService {

	/** Mapper */
	@Resource(name="crsBbsCmntMapper")
	private CrsBbsCmntMapper 		crsBbsCmntMapper;
	
	/**
	 * 게시글의 댓글 목록을 조회하여 반환한다.
	 * @param CrsBbsCmntVO.atclSn
	 * @return ProcessResultListVO<CrsBbsCmntVO>
	 */
	@Override
	public ProcessResultListVO<CrsBbsCmntVO> list(CrsBbsCmntVO VO) throws Exception {
		ProcessResultListVO<CrsBbsCmntVO> resultList = new ProcessResultListVO<CrsBbsCmntVO>();
		try {
			List<CrsBbsCmntVO> returnList = crsBbsCmntMapper.list(VO);
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
	 * 게시글의 댓글 목록을 조회하여 반환한다.(페이징)
	 * @param CrsBbsCmntVO.atclSn
	 * @return ProcessResultListVO<CrsBbsCmntVO>
	 */
	@Override
	public ProcessResultListVO<CrsBbsCmntVO> listPageing(CrsBbsCmntVO VO) throws Exception {

		ProcessResultListVO<CrsBbsCmntVO> resultList = new ProcessResultListVO<CrsBbsCmntVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(VO.getPageScale());
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		
		
		try {
			// 전체 목록 수
			int totalCount = crsBbsCmntMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CrsBbsCmntVO> returnList = crsBbsCmntMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 게시글의 댓글 단일 레코드를 조회하여 반환한다.(수정전 자료 조회용)
	 * @param CrsBbsCmntVO.atclSn
	 * @param CrsBbsCmntVO.cmntSn
	 * @return
	 */
	@Override
	public ProcessResultVO<CrsBbsCmntVO> view(CrsBbsCmntVO VO) throws Exception {
		ProcessResultVO<CrsBbsCmntVO> resultVO = new ProcessResultVO<CrsBbsCmntVO>();
		try {
			CrsBbsCmntVO returnVO = crsBbsCmntMapper.select(VO);
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
	 * 게시글의 댓글 항목을 추가하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsCmntVO> add(CrsBbsCmntVO VO) throws Exception {
		
		ProcessResultVO<CrsBbsCmntVO> resultVO = new ProcessResultVO<CrsBbsCmntVO>();
		try {
			VO.setCmntSn(crsBbsCmntMapper.selectKey());
			crsBbsCmntMapper.insert(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 게시글의 댓글 항목을 수정하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsCmntVO> edit(CrsBbsCmntVO VO) throws Exception {
		ProcessResultVO<CrsBbsCmntVO> resultVO = new ProcessResultVO<CrsBbsCmntVO>();
		try {
			crsBbsCmntMapper.update(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		return resultVO;
	}

	/**
	 * 게시글의 댓글 항목을 삭제하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO.atclSn
	 * @param CrsBbsCmntVO.cmntSn
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	@Override
	public ProcessResultVO<?> remove(CrsBbsCmntVO VO) throws Exception {
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			crsBbsCmntMapper.delete(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		return resultVO;
	}
	
	/**
	 * 게시글에 적용된 댓글들을 삭제한다.
	 *
	 * */
	@Override
	public ProcessResultVO<CrsBbsCmntVO> removeCmntNtcAll(CrsBbsCmntVO VO) throws Exception {
		ProcessResultVO<CrsBbsCmntVO> resultVO = new ProcessResultVO<CrsBbsCmntVO>();
		try {
			crsBbsCmntMapper.deleteCmntNtcAll(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

}
