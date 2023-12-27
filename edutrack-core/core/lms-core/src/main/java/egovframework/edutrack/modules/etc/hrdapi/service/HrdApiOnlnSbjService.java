package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *  [HRD API] 과정 정보 연계
 */
public interface HrdApiOnlnSbjService {

    /**
	 * api용 과정 정보 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> getList(HrdApiOnlnSbjVO vo) throws Exception;
	
    /**
	 * api 발송용 과정 정보 리스트 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> getListForSync(HrdApiOnlnSbjVO vo) throws Exception;
	
    /**
	 * api용 발송용 과정 정보 페이징 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return ProcessResultListVO<EgovMap>
	 */
	public abstract ProcessResultListVO<EgovMap> getListPageing(HrdApiOnlnSbjVO vo) throws Exception;

    /**
	 * 과정 정보 내역 발송
	 * @param HrdApiOnlnSbjVO vo
	 * @return ProcessResultListVO<HrdApiVO>
	 */
	public abstract ProcessResultListVO<HrdApiVO> callCourseInfoHrdApi(List<EgovMap> courseList, HrdApiOnlnSbjVO vo) throws Exception ;
}