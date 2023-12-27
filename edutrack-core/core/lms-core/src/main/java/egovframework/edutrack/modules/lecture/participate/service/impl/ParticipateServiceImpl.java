package egovframework.edutrack.modules.lecture.participate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.participate.service.ParticipateService;
import egovframework.edutrack.modules.lecture.participate.service.ParticipateVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("participateService")
public class ParticipateServiceImpl
	extends EgovAbstractServiceImpl implements ParticipateService {

	@Resource(name="participateMapper")
	private ParticipateMapper participateMapper;

	/**
	 * 학습참여현황 목록 조회
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<ParticipateVO> listPageing(ParticipateVO vo) throws Exception{

		ProcessResultListVO<ParticipateVO> resultList = new ProcessResultListVO<ParticipateVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = participateMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ParticipateVO> returnList =  participateMapper.listPageing(vo);
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
	 * 점수저장
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ParticipateVO> addScore(ParticipateVO vo) throws Exception{
		try {
			participateMapper.insertScore(vo);
		} catch (DuplicateKeyException e){
			participateMapper.updateScore(vo);
		}

		return ProcessResultVO.success();
	}

	/**
	 * 점수저장(복수)
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ParticipateVO> addScoreAll(ParticipateVO vo, String strStdNo, String strJoinScore) throws Exception{
		String[] strStdNoArray = StringUtil.split(strStdNo,"|");
		String[] strJoinScoreArray = StringUtil.split(strJoinScore,"|");
		for(int i = 0; i < strStdNoArray.length; i++) {
			vo.setStdNo(strStdNoArray[i]);
			vo.setJoinScore(Float.parseFloat(strJoinScoreArray[i]));
			this.addScore(vo);
		}
		return ProcessResultVO.success();
	}
}
