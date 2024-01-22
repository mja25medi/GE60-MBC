package egovframework.edutrack.modules.course.createcoursesubject.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("createCourseOnlineSubjectMapper")
public interface CreateCourseOnlineSubjectMapper {

	/**
	 * 개설 과정 온라인 과목 목록 조회
	 *
	 * @reurn ProcessResultVO
	 */
	
	public List<CreateOnlineSubjectVO> list(CreateOnlineSubjectVO iOnlineSubjectVO)    ;

	/**
	 * 개설 과정 온라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public CreateOnlineSubjectVO select(CreateOnlineSubjectVO iOnlineSubjectVO)    ;



	/**
	 * 개설 과정 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CreateOnlineSubjectVO iOnlineSubjectVO)    ;
	/**
	 * 개설 과정 온라인 과목 삭제 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(CreateOnlineSubjectVO iOnlineSubjectVO)    ;

    /**
     * 개설과정 온라인 과목 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<CreateOnlineSubjectVO> subjectArray)   ;

	/**
	 * 개설 과정 온라인 과목 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(CreateOnlineSubjectVO iOnlineSubjectVO)    ;


	/**
	 * 과정에 등록된 온라인 과목을 개설과정의 과목 정보로
	 * 카피한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCopy(CreateOnlineSubjectVO iOnlineSubjectVO)    ;
	/**
	 * 온라인 과목 목록을 조회하여 반환한다.
	 * : 전체 목록 조회, 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<OnlineSubjectVO> listSearch(CreateOnlineSubjectVO VO)   ;

	/**
	 * 온라인 과목 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo), 개설 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public List<OnlineSubjectVO> listSearchPageing(CreateOnlineSubjectVO VO)
			 ;

	/**
	 * 온라인 과목 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo), 개설 과정에 등록된 과목 제외
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public int count(CreateOnlineSubjectVO VO)
			 ;
	
	/**
	 * 2015.11.06 김현욱 추가
	 * 개설 과정 온라인 과목 정보 조회(TB_CRS_CRS_ONLN_SBJ)
	 *
	 * @return ProcessResultVO
	 */
	public CreateOnlineSubjectVO selectMaster(CreateOnlineSubjectVO iOnlineSubjectVO)    ;

}
