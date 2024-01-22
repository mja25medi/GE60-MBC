package egovframework.edutrack.modules.lecture.bbs.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.bbs.service.LecBbsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("lecBbsMapper")
public interface LecBbsMapper {

    /**
	 * 게시판의 종류를 가져온다
	 * @parma BbsVO
	 *
	 */
	public LecBbsVO select(LecBbsVO vo) ;

	/**
	 * 게시판 정보를 등록한다.
	 */
	public int insert(LecBbsVO vo) ;

	/**
	 * 개설 과정 게시판 정보를 삭제한다.
	 */
	public int delete(LecBbsVO vo) ;

	/**
	 * 개설 과정의 모든 게시판 정보를 삭제한다.
	 */
	public int deleteAll(LecBbsVO vo)  ;

}