package egovframework.edutrack.modules.course.createcoursesubject.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOfflineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("createCourseOfflineSubjectMapper")
public interface CreateCourseOfflineSubjectMapper {


	/**
	 *  개설 과정 오프라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateOfflineSubjectVO> list(CreateOfflineSubjectVO iOfflineSubjectVO)   ;



	/**
	 * 개설 과정 오프라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public CreateOfflineSubjectVO select(CreateOfflineSubjectVO iOfflineSubjectVO)   ;


	/**
	 * 개설 과정 오프라인 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CreateOfflineSubjectVO iOfflineSubjectVO)    ;


	/**
	 * 개설 과정 오프라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(CreateOfflineSubjectVO iOfflineSubjectVO)   ;

	/**
     * 개설 과정 오프라인 과목 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<CreateOfflineSubjectVO> subjectArray)   ;

	/**
	 * 개설 과정 오프라인 과목 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(CreateOfflineSubjectVO iOfflineSubjectVO)   ;


	/**
	 * 과정에 등록되어 있는 오르파인 과목을 개설 과정의 과목으로
	 * 카피하고 결과를 리턴한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCopy(CreateOfflineSubjectVO iOfflineSubjectVO)   ;

	/**
	 * 오프라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회, 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<OfflineSubjectVO> listSearch(CreateOfflineSubjectVO VO)   ;

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
	
	public List<OfflineSubjectVO> listSearchPageing(CreateOfflineSubjectVO VO)
			;

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
	
	public int count(CreateOfflineSubjectVO VO)
			;



}
