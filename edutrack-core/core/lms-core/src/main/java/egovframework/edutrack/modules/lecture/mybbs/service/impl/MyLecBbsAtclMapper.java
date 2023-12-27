package egovframework.edutrack.modules.lecture.mybbs.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.mybbs.service.MyLecBbsAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("myLecBbsAtclMapper")
public interface MyLecBbsAtclMapper {

	/**
	 * 게시글 레코드 목록을 조회한다.(페이징 포함)
	 * @param articleParam
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MyLecBbsAtclVO> listPageing(MyLecBbsAtclVO vo) throws DataAccessException;

	/**
	 * 게시글 레코드 목록수 반환
	 * @param articleParam
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(MyLecBbsAtclVO vo) throws DataAccessException;
	
	/**
	 * 단일 게시물 조회.
	 * @param crsCreCd
	 * @param bbsSn
	 * @param arclSn
	 * @return BbsArticleVO
	 */
	public MyLecBbsAtclVO select(MyLecBbsAtclVO vo) throws Exception;

}
