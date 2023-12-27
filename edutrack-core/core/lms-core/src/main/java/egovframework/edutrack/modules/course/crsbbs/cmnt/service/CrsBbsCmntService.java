package egovframework.edutrack.modules.course.crsbbs.cmnt.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface CrsBbsCmntService {

	/**
	 * 게시글의 댓글 목록을 조회하여 반환한다.
	 * @param CrsBbsCmntVO.atclSn
	 * @return ProcessResultListVO<CrsBbsCmntVO>
	 */
	public abstract ProcessResultListVO<CrsBbsCmntVO> list(CrsBbsCmntVO VO) throws Exception;

	/**
	 * 게시글의 댓글 목록을 조회하여 반환한다.(페이징)
	 * @param CrsBbsCmntVO.atclSn
	 * @return ProcessResultListVO<CrsBbsCmntVO>
	 */
	public abstract ProcessResultListVO<CrsBbsCmntVO> listPageing(
			CrsBbsCmntVO VO) throws Exception;

	/**
	 * 게시글의 댓글 단일 레코드를 조회하여 반환한다.(수정전 자료 조회용)
	 * @param CrsBbsCmntVO.atclSn
	 * @param CrsBbsCmntVO.cmntSn
	 * @return
	 */
	public abstract ProcessResultVO<CrsBbsCmntVO> view(CrsBbsCmntVO VO) throws Exception;

	/**
	 * 게시글의 댓글 항목을 추가하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	public abstract ProcessResultVO<CrsBbsCmntVO> add(CrsBbsCmntVO VO) throws Exception;

	/**
	 * 게시글의 댓글 항목을 수정하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	public abstract ProcessResultVO<CrsBbsCmntVO> edit(CrsBbsCmntVO VO) throws Exception;

	/**
	 * 게시글의 댓글 항목을 삭제하고, 결과를 반환한다.
	 * @param CrsBbsCmntVO.atclSn
	 * @param CrsBbsCmntVO.cmntSn
	 * @return ProcessResultVO<CrsBbsCmntVO>
	 */
	public abstract ProcessResultVO<?> remove(CrsBbsCmntVO VO) throws Exception;
	
	/**
	 * 게시글에 적용된 댓글들을 삭제한다.
	 *
	 * */
	public ProcessResultVO<CrsBbsCmntVO> removeCmntNtcAll(CrsBbsCmntVO VO) throws Exception;

}