package egovframework.edutrack.modules.lecture.project.board.atcl.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface PrjBbsAtclService {

	/**
	 * 팀프로젝트 게시글 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 */
	public ProcessResultListVO<PrjBbsAtclVO> listPageing(PrjBbsAtclVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 게시글 정보 조회
	 * @author twkim 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsAtclVO>
	 */
	public ProcessResultVO<PrjBbsAtclVO> view(PrjBbsAtclVO vo) throws Exception;
	
	public ProcessResultVO<PrjBbsAtclVO> view(PrjBbsAtclVO vo,boolean hitsup) throws Exception;
	
	/**
	 * 게시글 정보 등록
	 * @author twkim 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsAtclVO>
	 */
	public ProcessResultVO<PrjBbsAtclVO> add(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 게시글 정보 수정
	 * @author twkim 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsAtclVO>
	 */
	public ProcessResultVO<PrjBbsAtclVO> edit(PrjBbsAtclVO vo) throws Exception;

	/**
	 * 게시글 정보 삭제
	 * @author twkim 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public ProcessResultVO<?> remove(PrjBbsAtclVO vo) throws Exception;

}