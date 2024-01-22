package egovframework.edutrack.modules.course.subject.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.subject.service.LecRoomVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("subjectOnlineMapper")
public interface SubjectOnlineMapper {

	/**
	 * Select Key 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectKey()   ;
	
	/**
	 * Select Key 정보 조회2
	 *
	 * @return ProcessResultVO
	 */
	public String selectLecRoomKey()   ;
	
	/**
	 * 온라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<OnlineSubjectVO> list(OnlineSubjectVO iOnlineSubjectVO)    ;

	/**
	 * 온라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<OnlineSubjectVO> listPageing(OnlineSubjectVO iOnlineSubjectVO)   ;

	/**
	 * 온라인 과목 목록 조회수 반환
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int count(OnlineSubjectVO iOnlineSubjectVO)   ;
	
	/**
	 * 온라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public OnlineSubjectVO select(OnlineSubjectVO iOnlineSubjectVO)   ;

	/**
	 * 온라인 과목 목록 조회 (개설과정에 속하지 않은 과목만 검색 함)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<OnlineSubjectVO> listSearch(OnlineSubjectVO iOnlineSubjectVO)   ;

	/**
	 * 온라인 과목 목록 조회 (공개과정 용)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<OnlineSubjectVO> listSearchOpen(OnlineSubjectVO iOnlineSubjectVO)    ;


	/**
	 * 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(OnlineSubjectVO iOnlineSubjectVO)   ;

	/**
	 * 온라인 과목 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(OnlineSubjectVO iOnlineSubjectVO)   ;


	/**
	 * 온라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(OnlineSubjectVO iOnlineSubjectVO)    ;
	
	/**
	 * 강의실 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int lecRoomInsert(LecRoomVO lecRoomVO)   ;
	
	/**
	 * 강의실 목록 조회수 반환
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int lecRoomCount(LecRoomVO lecRoomVO)   ;
	
	/**
	 * 강의실 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<LecRoomVO> lecListPageing(LecRoomVO lecRoomVO)   ;
	
	/**
	 * 강의실 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public LecRoomVO getLecRoom(LecRoomVO lecRoomVO)   ;
	
	/**
	 * 강의실 과목 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateLecRoom(LecRoomVO lecRoomVO)   ;
	
	/**
	 * 강의실 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteLecRoom(LecRoomVO lecRoomVO)  ;

}
