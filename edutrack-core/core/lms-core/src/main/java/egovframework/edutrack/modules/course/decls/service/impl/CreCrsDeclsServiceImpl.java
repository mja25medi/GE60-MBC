package egovframework.edutrack.modules.course.decls.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개설 개설 과정 분반 분반 정보의 입.출력 처리를 하는 서비스 클래스
 *
 * <pre>
 * <b>업  무 :</b> 개설 과정 분반 - 개설 개설 과정 분반 분반 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Service("creCrsDeclsService")
public class CreCrsDeclsServiceImpl
	extends EgovAbstractServiceImpl implements CreCrsDeclsService {

	/** Mapper */
	@Resource(name="creCrsDeclsMapper")
	private CreCrsDeclsMapper creCrsDeclsMapper;

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * @param CreCrsDeclsVO
	 * @return ProcessResultListVO<CreCrsDeclsVO>
	 */
	@Override
	public ProcessResultListVO<CreCrsDeclsVO> list(CreCrsDeclsVO VO) throws Exception {
		ProcessResultListVO<CreCrsDeclsVO> resultList = new ProcessResultListVO<CreCrsDeclsVO>();
		try {
			List<CreCrsDeclsVO> returnList = creCrsDeclsMapper.list(VO);
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
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CreCrsDeclsVO>
	 */
	@Override
	public ProcessResultListVO<CreCrsDeclsVO> listPageing(CreCrsDeclsVO VO, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<CreCrsDeclsVO> resultList = new ProcessResultListVO<CreCrsDeclsVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = creCrsDeclsMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreCrsDeclsVO> returnList = creCrsDeclsMapper.listPageing(VO);
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
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CreCrsDeclsVO> listPageing(CreCrsDeclsVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CreCrsDeclsVO> listPageing(CreCrsDeclsVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 개설 과정 분반 정보의 단일 레코드를 조회하여 반환한다.
	 * @param CreCrsDeclsVO.atclSn
	 * @return ProcessResultVO<CreCrsDeclsVO>
	 */
	@Override
	public ProcessResultVO<CreCrsDeclsVO> view(CreCrsDeclsVO VO) throws Exception {
		ProcessResultVO<CreCrsDeclsVO> resultVO = new ProcessResultVO<CreCrsDeclsVO>();
		try {
			CreCrsDeclsVO returnVO = creCrsDeclsMapper.select(VO);
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
	 * 개설 과정 분반 정보를 등록하고 결과를 반환한다.
	 * @param CreCrsDeclsVO
	 * @return ProcessResultVO<CreCrsDeclsVO>
	 */
	@Override
	public ProcessResultVO<CreCrsDeclsVO> add(CreCrsDeclsVO VO) throws Exception {
		ProcessResultVO<CreCrsDeclsVO> resultVO = new ProcessResultVO<CreCrsDeclsVO>();
		try {
			creCrsDeclsMapper.insert(VO);
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
	 * 개설 과정 분반 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CreCrsDeclsVO VO) throws Exception {
		creCrsDeclsMapper.declsUpdate(VO); // 삭제되는 분반수강생으로 1분만으로 변경
		creCrsDeclsMapper.delete(VO); // 분류 삭제
		return ProcessResultVO.success();
	}

	/**
	 * 개설 과정 분반 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> removeAll(CreCrsDeclsVO VO) throws Exception {
		creCrsDeclsMapper.deleteAll(VO); // 분류 삭제
		return ProcessResultVO.success();
	}

	/**
	 * 개설 과정의 분반수를 반환한다.
	 * @param CreCrsDeclsVO VO
	 * @return
	 */
	@Override
	public int getCount(CreCrsDeclsVO VO) throws Exception {
		return creCrsDeclsMapper.getCount(VO);
	}

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	@Deprecated
	public ProcessResultVO<CreCrsDeclsVO> transactionRollbackMethod(CreCrsDeclsVO VO) throws Exception {
		this.add(VO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}

}
