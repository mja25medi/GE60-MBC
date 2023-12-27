package egovframework.edutrack.modules.course.coursesubject.service.impl;

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
import egovframework.edutrack.modules.board.bbs.service.impl.BrdBbsInfoMapper;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjService;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 과정 오프라인 과목의 입.출력 처리를 하는 서비스 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 오프라인 과목
 * </pre>
 *
 * @author MediopiaTech
 */
@Service("crsOflnSbjService")
public class CrsOflnSbjServiceImpl
	extends EgovAbstractServiceImpl implements CrsOflnSbjService {

	/** Mapper */
	@Resource(name="crsOflnSbjMapper")
	private CrsOflnSbjMapper 		crsOflnSbjMapper;


	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultListVO<CrsOflnSbjVO>
	 */
	@Override
	public ProcessResultListVO<CrsOflnSbjVO> list(CrsOflnSbjVO VO) throws Exception {
		ProcessResultListVO<CrsOflnSbjVO> resultList = new ProcessResultListVO<CrsOflnSbjVO>();
		try {
			List<CrsOflnSbjVO> returnList =  crsOflnSbjMapper.list(VO);
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
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOflnSbjVO>
	 */
	@Override
	public ProcessResultListVO<CrsOflnSbjVO> listPageing(CrsOflnSbjVO VO, int pageIndex, int listScale, int pageScale) throws Exception {
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		
		ProcessResultListVO<CrsOflnSbjVO> resultList = new ProcessResultListVO<CrsOflnSbjVO>();
		try {
			// 전체 목록 수
			int totalCount = crsOflnSbjMapper.listPageingCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CrsOflnSbjVO> returnList =  crsOflnSbjMapper.listPageing(VO);
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
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsOflnSbjVO> listPageing(CrsOflnSbjVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsOflnSbjVO> listPageing(CrsOflnSbjVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 오프라인 과목의 단일 레코드를 조회하여 반환한다.
	 * @param CrsOflnSbjVO.atclSn
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOflnSbjVO> view(CrsOflnSbjVO VO) throws Exception {
		ProcessResultVO<CrsOflnSbjVO> resultVO = new ProcessResultVO<CrsOflnSbjVO>();
		try {
			CrsOflnSbjVO returnVO = crsOflnSbjMapper.select(VO);
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
	 * 과정 오프라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOflnSbjVO> add(CrsOflnSbjVO VO) throws Exception {
		ProcessResultVO<CrsOflnSbjVO> resultVO = new ProcessResultVO<CrsOflnSbjVO>();
		try {
			crsOflnSbjMapper.insert(VO);
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
	 * 과정 오프라인 과목을 수정하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOflnSbjVO> edit(CrsOflnSbjVO VO) throws Exception {
		String eduMthdCd = VO.getEduMthdCd();
		String modNo = VO.getModNo();
		
		ProcessResultVO<CrsOflnSbjVO> resultVO = new ProcessResultVO<CrsOflnSbjVO>();
		try {
			VO = crsOflnSbjMapper.select(VO);
			VO.setEduMthdCd(eduMthdCd);
			VO.setModNo(modNo);
			
			crsOflnSbjMapper.update(VO);
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
	 * 과정 오프라인 과목의 순서를 변경하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(CrsOflnSbjVO VO) throws Exception {

		String[] itemList = StringUtil.split(VO.getSbjCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<CrsOflnSbjVO> itemArray = crsOflnSbjMapper.list(VO);

		for (CrsOflnSbjVO ctVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(ctVO.getSbjCd().equals(itemList[order])) {
					ctVO.setSbjOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		crsOflnSbjMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 과정 오프라인 과목을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CrsOflnSbjVO VO) throws Exception {
		crsOflnSbjMapper.delete(VO); // 분류 삭제
		return ProcessResultVO.success();
	}

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listSearch(CrsOflnSbjVO VO) throws Exception {

		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		try {
			List<OfflineSubjectVO> returnList =  crsOflnSbjMapper.listSearch(VO);
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
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int pageIndex, int listScale, int pageScale) throws Exception {
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		try {
			// 전체 목록 수
			int totalCount = crsOflnSbjMapper.listSearchPageingCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OfflineSubjectVO> returnList =  crsOflnSbjMapper.listSearchPageing(VO);
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
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int curPage, int listScale) throws Exception {
		return this.listSearchPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int curPage) throws Exception {
		return this.listSearchPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

}
