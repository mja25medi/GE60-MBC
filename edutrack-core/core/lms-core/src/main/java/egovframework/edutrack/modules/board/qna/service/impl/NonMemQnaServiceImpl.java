package egovframework.edutrack.modules.board.qna.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("nonMemQnaService")
public class NonMemQnaServiceImpl 
	extends EgovAbstractServiceImpl implements NonMemQnaService {
	
	/** Mapper */
	@Resource(name="nonMemQnaMapper")
	private NonMemQnaMapper nonMemQnaMapper;

	
	/**
	 * 비회원 상담 신청
	 *
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<NonMemQnaVO> addQna(NonMemQnaVO iNonMemQnaVO) throws Exception {
		ProcessResultVO<NonMemQnaVO> resultVO = new ProcessResultVO<NonMemQnaVO>();
		try {
			iNonMemQnaVO.setQnaSn(nonMemQnaMapper.selectKey());
			nonMemQnaMapper.insertNonMemQna(iNonMemQnaVO);
			resultVO.setReturnVO(iNonMemQnaVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 비회원 상담 목록 페이징 조회
	 * @param NonMemQnaVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<NonMemQnaVO> nonMemListPageing(NonMemQnaVO vo) throws Exception {
		ProcessResultListVO<NonMemQnaVO> resultList = new ProcessResultListVO<NonMemQnaVO>(); 
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = nonMemQnaMapper.countNonMemQna(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<NonMemQnaVO> qstnList = new ArrayList<>();
		qstnList = nonMemQnaMapper.listPageing(vo);
		
		resultList.setResult(1);
		resultList.setReturnList(qstnList);
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 비회원 상담 상세조회
	 * @param NonMemQnaVO
	 * @return NonMemQnaVO
	 * @throws Exception
	 */
	@Override
	public NonMemQnaVO viewNonMemQstn(NonMemQnaVO vo) throws Exception {
		NonMemQnaVO qstn = nonMemQnaMapper.selectNonMemQna(vo);
		if(qstn == null) {
			throw new Exception("해당하는 문의가 존재하지 않습니다.");
		}
		return qstn;
	}
	
	/**
	 * 비회원 상담 답변 작성
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int writeNonMemAnsr(NonMemQnaVO vo) throws Exception {
		return nonMemQnaMapper.addNonMemAns(vo);
	}

	/**
	 * 비회원 상담 답변 수정
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editNonMemAnsr(NonMemQnaVO vo) throws Exception {
		return nonMemQnaMapper.updateNonMemAns(vo);
	}
	
	/**
	 * 비회원상담 답변 상세 조회
	 * @param NonMemQnaVO
	 * @return ProcessResultVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO<NonMemQnaVO> viewNonMemAnsr(NonMemQnaVO vo) throws Exception {
		ProcessResultVO<NonMemQnaVO> result = new ProcessResultVO<>();
		NonMemQnaVO ansr = nonMemQnaMapper.selectNonMemQna(vo);
		
		return result.setReturnVO(ansr);
	}
	
	/**
	 * 비회원 상담 삭제
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int deleteNonMem(NonMemQnaVO vo) throws Exception {
		return nonMemQnaMapper.deleteNonMemQna(vo);
	}
}
