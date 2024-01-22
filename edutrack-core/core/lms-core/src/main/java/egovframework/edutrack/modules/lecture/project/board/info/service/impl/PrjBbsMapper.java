package egovframework.edutrack.modules.lecture.project.board.info.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("prjBbsMapper")
public interface PrjBbsMapper {
	
	/**
	 * 팀프로젝트 게시판 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<PrjBbsVO> list(PrjBbsVO vo) ;

	/**
	 * 팀프로젝트 게시판 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<PrjBbsVO> listBbs(PrjBbsVO vo) ;

	/**
	 * 팀프로젝트 게시판 코드 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public String selectProjectBoardBbsCd() ;

	/**
	 * 팀프로젝트 게시판 등록 
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int insert(PrjBbsVO vo) ;

	/**
	 * 팀프로젝트 게시판 정보 조회
	 * @param vo
	 * @return ProcessResultVO
	 */
	public PrjBbsVO select(PrjBbsVO vo) ;
	
	/**
	 * 팀프로젝트 게시판 정보 수정
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int update(PrjBbsVO vo) ;

	/**
	 * 팀프로젝트 게시판 정보 삭제
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int delete(PrjBbsVO vo) ;

	/**
	 * 팀프로젝트 게시판 전체 삭제
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int deleteAll(PrjBbsVO vo) ;

}