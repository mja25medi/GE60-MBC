package egovframework.edutrack.modules.course.crstch.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.crstch.service.CrsTchService;
import egovframework.edutrack.modules.course.crstch.service.CrsTchVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 과정 강사의 입.출력 처리를 하는 서비스 클래스
 * 
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 강사
 * </pre>
 * 
 * @author MediopiaTech
 */
@Service("crsTchService")
public class CrsTchServiceImpl 
	extends EgovAbstractServiceImpl implements CrsTchService {
	
	/** Mapper */
	@Resource(name="crsTchMapper")
	private CrsTchMapper crsTchMapper;
	
	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<CrsTchVO> list(CrsTchVO VO) throws Exception {
		ProcessResultListVO<CrsTchVO> resultList = new ProcessResultListVO<CrsTchVO>();
		try {
			List<CrsTchVO> returnList = crsTchMapper.list(VO);
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
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO, int curPage, int listScale, int pageScale) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		ProcessResultListVO<CrsTchVO> resultList = new ProcessResultListVO<CrsTchVO>();
		try {
			// 전체 목록 수
			int totalCount = crsTchMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CrsTchVO> returnList = crsTchMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		resultList.setPageInfo(paginationInfo);
		return resultList;
	}
	
	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 과정 강사의 단일 레코드를 조회하여 반환한다.
	 * @param CrsTchVO.atclSn
	 * @return ProcessResultVO<CrsTchVO>
	 */
	@Override
	public ProcessResultVO<CrsTchVO> view(CrsTchVO VO) throws Exception {
		ProcessResultVO<CrsTchVO> resultVO = new ProcessResultVO<CrsTchVO>();
		try {
			CrsTchVO returnVO = crsTchMapper.select(VO);
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
	 * 회차 강사와 교사를 등록하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultVO<CrsTchVO>
	 */
	@Override
	public ProcessResultVO<CrsTchVO> add(CrsTchVO VO) throws Exception {
		ProcessResultVO<CrsTchVO> resultVO = new ProcessResultVO<CrsTchVO>();
		try {
			crsTchMapper.insert(VO);
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
	 * 과정 강사를 수정하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultVO<CrsTchVO>
	 */
	@Override
	public ProcessResultVO<CrsTchVO> edit(CrsTchVO VO) throws Exception {
		ProcessResultVO<CrsTchVO> resultVO = new ProcessResultVO<CrsTchVO>();
		try {
			crsTchMapper.update(VO);
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
	 * 과정 강사의 순서를 변경하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(CrsTchVO VO) throws Exception {

		String[] itemList = StringUtil.split(VO.getUserNo(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<CrsTchVO> itemArray = crsTchMapper.list(VO);

		for (CrsTchVO ctVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(ctVO.getUserNo().equals(itemList[order])) {
					ctVO.setTchOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		crsTchMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}
	
	/**
	 * 과정 강사를 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CrsTchVO VO) throws Exception {
		crsTchMapper.delete(VO); // 분류 삭제
		return ProcessResultVO.success();
	}
	
	/**
	 * 강사/튜터의 권한이 있는 사용자중 과정 튜터/강사로 등록이 되어 있지 않은 
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listSearch(CrsTchVO VO) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
			List<UsrUserInfoVO> returnList = crsTchMapper.listSearch(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

}
