package egovframework.edutrack.modules.lecture.project.board.cmnt.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("prjBbsCmntMapper")
public interface PrjBbsCmntMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	
	/**
	 * 게시글 댓글 목록 paging
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return List<PrjBbsCmntVO>
	 */
	@SuppressWarnings("unchecked")
	public List<PrjBbsCmntVO> listPageing(PrjBbsCmntVO vo) throws Exception;

	/**
	 * 게시글 댓글 목록수 반환
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return List<PrjBbsCmntVO>
	 */
	@SuppressWarnings("unchecked")
	public int count(PrjBbsCmntVO vo) throws Exception;
	
	/**
	 * 게시글 댓글 조회
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 */
	public PrjBbsCmntVO select(PrjBbsCmntVO vo) throws Exception;

	/**
	 * 게시글 댓글 등록
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsCmntVO>
	 */
	public int insert(PrjBbsCmntVO vo) throws Exception;
	
	/**
	 * 게시글 댓글 삭제
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public int delete(PrjBbsCmntVO vo) throws Exception;
	
	/**
	 * 게시글 댓글 전체 삭제
	 * @author mhShin 
	 * @date 2013. 11. 1.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public int deleteAtclAll(PrjBbsCmntVO vo) throws Exception;
	public int deleteBbsAll(PrjBbsCmntVO vo) throws Exception;
	public int deletePrjAll(PrjBbsCmntVO vo) throws Exception;

}
