package egovframework.edutrack.modules.lecture.score.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("stdScoreService")
public class StdScoreServiceImpl extends EgovAbstractServiceImpl implements StdScoreService {
	
	@Resource(name="stdScoreMapper")
	private StdScoreMapper stdScoreMapper;
	
	/**
	 * 과정 학습자의 점수를 목록으로 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
		public	ProcessResultListVO<StdScoreVO> list(StdScoreVO vo) throws Exception{
			ProcessResultListVO<StdScoreVO> resultList = new ProcessResultListVO<StdScoreVO>();
			try {
				List<StdScoreVO> returnList =  stdScoreMapper.list(vo);
				resultList.setResult(1);
				resultList.setReturnList(returnList);
			} catch (Exception e){
				e.printStackTrace();
				resultList.setResult(-1);
				resultList.setMessage(e.getMessage());
			}

		return resultList;
		
	}
	
	/**
	 * 학습자의 점수를 단일레코드로 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
		public	ProcessResultVO<StdScoreVO> view(StdScoreVO vo) throws Exception{
		ProcessResultVO<StdScoreVO> resultVO = new ProcessResultVO<StdScoreVO>();
		try {
			StdScoreVO returnVO = stdScoreMapper.select(vo);
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
	 * 과정 학습자의 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
		public	ProcessResultListVO<EgovMap> listCreStd(StdScoreVO vo) throws Exception{
			ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>();
			try {
				List<EgovMap> returnList =  stdScoreMapper.listCreStd(vo);
				resultList.setResult(1);
				resultList.setReturnList(returnList);
			} catch (Exception e){
				e.printStackTrace();
				resultList.setResult(-1);
				resultList.setMessage(e.getMessage());
			}

		return resultList;
		
	}
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
	public	ProcessResultListVO<EgovMap> listEndCreStd(StdScoreVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>();
		try {
			List<EgovMap> returnList =  stdScoreMapper.listEndCreStd(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
		
	}
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다 - 페이징
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
	public	ProcessResultListVO<EgovMap> listEndCreStdPageing(StdScoreVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			
			// 전체 목록 수
			int totalCount = stdScoreMapper.myListCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
						
			List<EgovMap> returnList =  stdScoreMapper.listEndCreStdPageing(vo);
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

	
}
