package egovframework.edutrack.modules.lecture.project.board.cmnt.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface PrjBbsCmntService {
	
	
	/**
	 * 게시글 댓글 목록 paging
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<PrjBbsCmntVO>
	 */
	public ProcessResultListVO<PrjBbsCmntVO> listPageing(PrjBbsCmntVO vo, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * 게시글 댓글 조회
	 * @author mhShin 
	 * @date 2013. 12. 02.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 */
	public ProcessResultVO<PrjBbsCmntVO> view(PrjBbsCmntVO vo) throws Exception;
	
	/**
	 * 게시글 댓글 등록
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 */
	public ProcessResultVO<PrjBbsCmntVO> add(PrjBbsCmntVO vo) throws Exception;
	
	/**
	 * 게시글 댓글 삭제
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public ProcessResultVO<?> remove(PrjBbsCmntVO vo) throws Exception;

}
