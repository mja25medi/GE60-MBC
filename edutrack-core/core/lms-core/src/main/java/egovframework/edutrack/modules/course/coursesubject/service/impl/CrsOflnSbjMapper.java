package egovframework.edutrack.modules.course.coursesubject.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 과정 오프라인 과목 정보의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 오프라인 과목 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("crsOflnSbjMapper")
public interface  CrsOflnSbjMapper {

	/**
	 * 과정 온라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public List<CrsOflnSbjVO> list(CrsOflnSbjVO VO)    ;

	/**
	 * 과정 온라인 과목 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public List<CrsOflnSbjVO> listPageing(CrsOflnSbjVO VO);

	/**
	 * 과정 온라인 과목 목록수를 반환
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public int listPageingCount(CrsOflnSbjVO VO);
	
	/**
	 * 과정 온라인 과목 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CrsOflnSbjVO
	 * @return
	 */
	public CrsOflnSbjVO select(CrsOflnSbjVO VO)    ;

	/**
	 * 과정 온라인 과목 정보를 Insert하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CrsOflnSbjVO VO)    ;
	/**
	 * 과정 온라인 과목 정보의 단일 레코드를 Update하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int update(CrsOflnSbjVO VO)   ;

    /**
     * 과정 온라인 과목 정보의 복수의 레코드를 Update하고 결과를 반환한다.
     * @param List<WordDictCtgrVO>
     */
	public int updateBatch(List<CrsOflnSbjVO> itemArray)   ;



	/**
	 * 과정 온프라인 과목 정보를 삭제하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CrsOflnSbjVO VO)   ;

	/**
	 * 과정의 모든 온프라인 과목 정보를 삭제하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(CrsOflnSbjVO VO)   ;

	/**
	 * 오프라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회, 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public List<OfflineSubjectVO> listSearch(CrsOflnSbjVO VO)   ;

	/**
	 * 오프라인 과목 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo), 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public List<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO);

	/**
	 * 오프라인 과목 목록수 반환
	 * : 페이징 정보 포함(pageInfo), 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public int listSearchPageingCount(CrsOflnSbjVO VO);



}
