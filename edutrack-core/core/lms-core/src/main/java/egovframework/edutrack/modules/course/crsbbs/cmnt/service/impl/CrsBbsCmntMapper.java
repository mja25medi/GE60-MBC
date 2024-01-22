package egovframework.edutrack.modules.course.crsbbs.cmnt.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.crsbbs.cmnt.service.CrsBbsCmntVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("crsBbsCmntMapper")
public interface CrsBbsCmntMapper {

	/**
	 * select Key를 조회한다.
	 * @return 조회결과를 포함한 {@code ProcessResultVO<CrsBbsCmntVO>}
	 * @
	 */
	public int selectKey() ;
	/**
	 * 게시물에 첨부된 덧글 목록을 조회한다.
	 * @param CrsBbsCmntVO
	 * @return
	 * @
	 */
	
	public List<CrsBbsCmntVO> list(CrsBbsCmntVO VO) ;

	/**
	 * 게시물에 첨부된 덧글 목록을 조회한다.(페이징 정보 포함)
	 * @param CrsBbsCmntVO
	 * @return
	 * @
	 */
	
	public List<CrsBbsCmntVO> listPageing(CrsBbsCmntVO VO) ;
	
	/**
	 * 게시물에 첨부된 덧글 목록을 조회한다.(페이징 정보 포함)
	 * @param CrsBbsCmntVO
	 * @return
	 * @
	 */
	
	public int count(CrsBbsCmntVO VO) ;
	

	/**
	 * 게시물 덧글의 단일항목을 조회한다.
	 * @param CrsBbsCmntVO (atclSn, cmntSn 이 포함된 예제 클래스)
	 * @return 조회결과를 포함한 {@code ProcessResultVO<CrsBbsCmntVO>}
	 * @
	 */
	public CrsBbsCmntVO select(CrsBbsCmntVO VO) ;

	/**
	 * 게시물에 덧글을 저장한다.
	 * @param CrsBbsCmntVO
	 * @return cmntSn 주키가 주입된 결과를 담은 {@code ProcessResultVO<CrsBbsCmntVO>}
	 * @
	 */
	public int insert(CrsBbsCmntVO VO) ;

	/**
	 * 게시물에 덧글을 수정한다.
	 * @param CrsBbsCmntVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @
	 */
	public int update(CrsBbsCmntVO CrsBbsCmntVO) ;

	/**
	 * 게시물에 덧글을 삭제한다.
	 * @param CrsBbsCmntVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @
	 */
	public int delete(CrsBbsCmntVO CrsBbsCmntVO) ;
	/**
	 * 게시글에 적용된 댓글들을 삭제한다.
	 *
	 * */
	public int deleteCmntNtcAll(CrsBbsCmntVO CrsBbsCmntVO) ;
}
