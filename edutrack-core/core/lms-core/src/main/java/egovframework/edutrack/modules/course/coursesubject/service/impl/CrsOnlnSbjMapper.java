package egovframework.edutrack.modules.course.coursesubject.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 과정 온라인 과목 정보의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 온라인 과목 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("crsOnlnSbjMapper")
public interface CrsOnlnSbjMapper {

	/**
	 * 과정 온라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public List<CrsOnlnSbjVO> list(CrsOnlnSbjVO VO)   ;

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
	public List<CrsOnlnSbjVO> listPageing(CrsOnlnSbjVO VO);
	
	/**
	 * 과정 온라인 과목 목록수 반환
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public int listPageingCount(CrsOnlnSbjVO VO);
	
	/**
	 * 과정 온라인 과목 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CrsOnlnSbjVO
	 * @return
	 */
	public CrsOnlnSbjVO select(CrsOnlnSbjVO VO)   ;

	/**
	 * 과정 온라인 과목 정보를 Insert하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CrsOnlnSbjVO VO)   ;

	/**
	 * 과정 온라인 과목 정보의 단일 레코드를 Update하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int update(CrsOnlnSbjVO VO)    ;

    /**
     * 과정 온라인 과목 정보의 복수의 레코드를 Update하고 결과를 반환한다.
     * @param List<WordDictCtgrVO>
     */
	public int[] updateBatch(List<CrsOnlnSbjVO> itemArray)   ;

	/**
	 * 과정 온라인 과목의 단일항목 정보를 삭제하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CrsOnlnSbjVO VO)   ;

	/**
	 * 과정의 모든 온라인 과목 정보를 삭제하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(CrsOnlnSbjVO VO)   ;

	/**
	 * 온라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회, 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public List<OnlineSubjectVO> listSearch(CrsOnlnSbjVO VO)   ;

	/**
	 * 온라인 과목 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo), 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public List<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO);

	/**
	 * 온라인 과목 목록수 반환
	 * : 페이징 정보 포함(pageInfo), 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	public int listSearchPageingCount(CrsOnlnSbjVO VO);
	
	/**
	 * 공개과정 온라인 과목 정보를 Insert하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int openCourseInsert(CrsOnlnSbjVO VO)   ;

	/**
	 * 공개과정 온라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회, 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public List<OnlineSubjectVO> openCourseListSearch(CrsOnlnSbjVO VO)   ;


}
