package egovframework.edutrack.modules.course.crsbbs.atcl.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface CrsBbsAtclService {

	/**
	 * 과정 게시물의 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsAtclVO> list(CrsBbsAtclVO VO) throws Exception ;

	/**
	 * 과정 게시물의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsAtclVO> listPageing(CrsBbsAtclVO VO) throws Exception ;


	/**
	 * 게시판의 게시글글 단일 레코드를 조회하여 반환한다.
	 * @param CrsBbsAtclVO.atclSn
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	public abstract ProcessResultVO<CrsBbsAtclVO> view(CrsBbsAtclVO VO) throws Exception ;

	/**
	 * 게시판의 게시글글 단일 레코드를 조회하여 반환한다.
	 * @param CrsBbsAtclVO.atclSn
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	public abstract ProcessResultVO<CrsBbsAtclVO> view(CrsBbsAtclVO VO,
			boolean hitsup) throws Exception ;

	/**
	 * 게시판 게시글을 등록하고 결과를 반환한다.
	 * @param CrsBbsAtclVO
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	public abstract ProcessResultVO<CrsBbsAtclVO> add(CrsBbsAtclVO VO) throws Exception ;

	/**
	 * 게시판 게시글을 수정하고 결과를 반환한다.
	 * @param CrsBbsAtclVO
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	public abstract ProcessResultVO<CrsBbsAtclVO> edit(CrsBbsAtclVO VO) throws Exception ;

	/**
	 * 게시판 게시글을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CrsBbsAtclVO VO) throws Exception ;

}