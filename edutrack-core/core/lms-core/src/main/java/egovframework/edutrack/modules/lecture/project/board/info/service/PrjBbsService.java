package egovframework.edutrack.modules.lecture.project.board.info.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface PrjBbsService {

	/**
	 * 팀프로젝트 게시판 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsVO>
	 */
	public ProcessResultListVO<PrjBbsVO> list(PrjBbsVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시판 목록 조회 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsVO>
	 */
	public ProcessResultListVO<PrjBbsVO> listBbs(PrjBbsVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시판 등록 
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 */
	public ProcessResultVO<PrjBbsVO> add(PrjBbsVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시판 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 */
	public ProcessResultVO<PrjBbsVO> view(PrjBbsVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시판 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 */
	public ProcessResultVO<PrjBbsVO> edit(PrjBbsVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시판 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 */
	public ProcessResultVO<?> remove(PrjBbsVO vo) throws Exception;
	

}