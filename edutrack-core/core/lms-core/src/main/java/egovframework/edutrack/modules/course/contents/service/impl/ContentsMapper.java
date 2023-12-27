package egovframework.edutrack.modules.course.contents.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("contentsMapper")
public interface ContentsMapper  {

	/**
	 * select Key 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectUnitCd()  throws Exception;
	/**
	 * 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listContents(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listContentsVer5(ContentsVO ctsVO)  throws Exception;

	/**
	 * 삭제 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listContentsDel(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 교재 목차 상위 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> parListContents(ContentsVO ctsVO)  throws Exception;
	/**
	 * 교재 목차 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public ContentsVO selectContents(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 교재 목차 정보 조회 (회차)
	 *
	 * @return ProcessResultVO
	 */
	public ContentsVO selectCreContents(ContentsVO ctsVO)  throws Exception;

	/**
	 * 교재 정보 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertContents(ContentsVO iContentsVO)  throws Exception;

	/**
	 * 교재 정보 등록 (배치)
	 * @return
	 */
	public int[] insertContentsBatch(List<ContentsVO> contentsArray)  throws Exception;


	/**
	 * 단원 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateContents(ContentsVO iContentsVO)  throws Exception;

    /**
     * 단원 정보 일괄 수정
     * @param contentsArray
     */
	public int updateContentsBatch(List<ContentsVO> contentsArray)  throws Exception;

	/**
	 * 단원 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteContents(ContentsVO ctsVO)  throws Exception;


	/**
	 * 단원 정보 일괄 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteAllContents(String sbjCd)  throws Exception;

	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listSort(ContentsVO vo)  throws DataAccessException ;
	
	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listSortVer5(ContentsVO vo)  throws DataAccessException ;
	
	/**
	 * 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listSub(ContentsVO vo)  throws DataAccessException ;

	/**
	 * 컨텐츠 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ContentsVO> contentsArray)  throws DataAccessException ;

	/**
	 * 교재 목차 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public int countChildContents(ContentsVO ctsVO)  throws Exception;
	
	public List<ContentsVO> listAttendContents(ContentsVO vo);
	

	/**
	 * 목차 복사
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCopy(ContentsVO cntVO);
	/**
	 * 회차별 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listCreateContents(ContentsVO vo)  throws Exception;
	
	/**
	 * 회차별 교재 목차 정보 수정
	 *
	 * @return ProcessReslutListVO
	 */
	public int updateCreateContents(ContentsVO iContentsVO) throws Exception;
	
	/**
	 * 회차별 교재 정보 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public ContentsVO selectCreateContents(ContentsVO ctsVO) throws Exception;
	
	/**
	 * 회차 교재 정보 일괄 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteAllCreateContents(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 회차 교재 삭제 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listCreateContentsDel(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 회차 단원 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCreateContents(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 회차 교재 목차 상위 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> parListCreateContents(ContentsVO ctsVO)  throws Exception;
	
	/**
	 * 회차 단일 목차 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCreateContents(ContentsVO iContentsVO)  throws Exception;
	
	/**
	 * 회차 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listCreCntSub(ContentsVO vo)  throws DataAccessException ;
	
	/**
	 * 회차 컨텐츠 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCreBatch(List<ContentsVO> contentsArray)  throws DataAccessException ;
	
	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ContentsVO> listCreCntSort(ContentsVO vo)  throws DataAccessException ;

}
