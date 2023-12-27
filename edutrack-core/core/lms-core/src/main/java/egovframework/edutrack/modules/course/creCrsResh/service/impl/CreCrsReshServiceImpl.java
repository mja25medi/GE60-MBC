package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("creCrsReshService")
public class CreCrsReshServiceImpl
	extends EgovAbstractServiceImpl implements  CreCrsReshService {

	/** Mapper */
	@Resource(name="creCrsReshMapper")
	private CreCrsReshMapper 	creCrsReshMapper;

	/**
	 * 개설 과정 설문 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CreCrsReshVO> list(CreCrsReshVO VO) throws Exception {
		ProcessResultListVO<CreCrsReshVO> resultList = new ProcessResultListVO<CreCrsReshVO>();
		try {
			List<CreCrsReshVO> returnList = creCrsReshMapper.list(VO);
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
	 * 개설 과정 설문 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CreCrsReshVO> listPageing(CreCrsReshVO VO,
			int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<CreCrsReshVO> resultList = new ProcessResultListVO<CreCrsReshVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		try {
			// 전체 목록 수
			int totalCount = creCrsReshMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreCrsReshVO> returnList = creCrsReshMapper.listPageing(VO);
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
	 * 개설 과정 설문 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CreCrsReshVO> listPageing(CreCrsReshVO VO,
			int curPage, int listScale) throws Exception {

		ProcessResultListVO<CreCrsReshVO> resultList = new ProcessResultListVO<CreCrsReshVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		try {
			// 전체 목록 수
			int totalCount = creCrsReshMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreCrsReshVO> returnList = creCrsReshMapper.listPageing(VO);
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
	 * 개설 과정 설문 페이징 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CreCrsReshVO> listPageing(CreCrsReshVO VO,
			int curPage) throws Exception {

		ProcessResultListVO<CreCrsReshVO> resultList = new ProcessResultListVO<CreCrsReshVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		try {
			// 전체 목록 수
			int totalCount = creCrsReshMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreCrsReshVO> returnList = creCrsReshMapper.listPageing(VO);
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
	 * 개설 과정 설문 정보 조회
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> view(CreCrsReshVO VO) throws Exception {
		
		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			CreCrsReshVO returnVO = creCrsReshMapper.select(VO);
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
	 * 개설 과정 설문 정보 등록
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> add(CreCrsReshVO VO) throws Exception {

		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			if(VO.getStartHour().length() ==1){
				VO.setStartHour("0"+VO.getStartHour());
			}
			if(VO.getStartMin().length() ==1){
				VO.setStartMin("0"+VO.getStartMin());
			}

			if(VO.getEndHour().length() ==1){
				VO.setEndHour("0"+VO.getEndHour());
			}
			if(VO.getEndMin().length() ==1){
				VO.setEndMin("0"+VO.getEndMin());
			}
			//-- 시간 관련 처리
			String startDttm = StringUtil.ReplaceAll(VO.getStartDttm(),".","")+VO.getStartHour()+VO.getStartMin()+"01";
			String endDttm = StringUtil.ReplaceAll(VO.getEndDttm(),".","")+VO.getEndHour()+VO.getEndMin()+"59";

			VO.setStartDttm(startDttm);
			VO.setEndDttm(endDttm);

			
			creCrsReshMapper.insert(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		

		return resultVO;
	}

	/**
	 * 개설 과정 설문 정보 수정
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> edit(CreCrsReshVO VO) throws Exception {

		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			if(VO.getStartHour().length() ==1){
				VO.setStartHour("0"+VO.getStartHour());
			}
			if(VO.getStartMin().length() ==1){
				VO.setStartMin("0"+VO.getStartMin());
			}

			if(VO.getEndHour().length() ==1){
				VO.setEndHour("0"+VO.getEndHour());
			}
			if(VO.getEndMin().length() ==1){
				VO.setEndMin("0"+VO.getEndMin());
			}
			//-- 시간 관련 처리
			String startDttm = StringUtil.ReplaceAll(VO.getStartDttm(),".","")+VO.getStartHour()+VO.getStartMin()+"01";
			String endDttm = StringUtil.ReplaceAll(VO.getEndDttm(),".","")+VO.getEndHour()+VO.getEndMin()+"59";

			VO.setStartDttm(startDttm);
			VO.setEndDttm(endDttm);
			creCrsReshMapper.update(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}


		return resultVO;
	}

	/**
	 * 개설 과정 설문 정보 삭제
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> remove(CreCrsReshVO VO) throws Exception {
		
		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			creCrsReshMapper.delete(VO);
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
	 * 개설 과정 설문 답변 갯수 반환
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> ansrCount(CreCrsReshVO VO) throws Exception {
		
		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			int ansrCount = creCrsReshMapper.ansrCount(VO);
			VO.setAnsrCnt(ansrCount);
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
	 * 개설 과정 설문 갯수 반환
	 */
	@Override
	public	ProcessResultVO<CreCrsReshVO> itemCount(CreCrsReshVO VO) throws Exception {
		
		ProcessResultVO<CreCrsReshVO> resultVO = new ProcessResultVO<CreCrsReshVO>();
		try {
			int itemCount = creCrsReshMapper.itemCount(VO);
			VO.setItemCnt(itemCount);
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
