package egovframework.edutrack.modules.course.contents.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("contentsMapper")
public interface ContentsMapper  {

	/**
	 * select Key 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectUnitCd()  ;
	/**
	 * 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listContents(ContentsVO ctsVO)  ;
	
	/**
	 * 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listContentsVer5(ContentsVO ctsVO)  ;

	/**
	 * 삭제 교재 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listContentsDel(ContentsVO ctsVO)  ;
	
	/**
	 * 교재 목차 상위 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> parListContents(ContentsVO ctsVO)  ;
	/**
	 * 교재 목차 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public ContentsVO selectContents(ContentsVO ctsVO)  ;
	
	/**
	 * 교재 목차 정보 조회 (회차)
	 *
	 * @return ProcessResultVO
	 */
	public ContentsVO selectCreContents(ContentsVO ctsVO)  ;

	/**
	 * 교재 정보 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertContents(ContentsVO iContentsVO)  ;

	/**
	 * 교재 정보 등록 (배치)
	 * @return
	 */
	public int[] insertContentsBatch(List<ContentsVO> contentsArray)  ;


	/**
	 * 단원 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateContents(ContentsVO iContentsVO)  ;

    /**
     * 단원 정보 일괄 수정
     * @param contentsArray
     */
	public int updateContentsBatch(List<ContentsVO> contentsArray)  ;

	/**
	 * 단원 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteContents(ContentsVO ctsVO)  ;


	/**
	 * 단원 정보 일괄 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteAllContents(String sbjCd)  ;

	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listSort(ContentsVO vo)   ;
	
	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listSortVer5(ContentsVO vo)   ;
	
	/**
	 * 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listSub(ContentsVO vo)   ;

	/**
	 * 컨텐츠 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ContentsVO> contentsArray)   ;

	/**
	 * 교재 목차 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public int countChildContents(ContentsVO ctsVO)  ;
	
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
	public List<ContentsVO> listCreateContents(ContentsVO vo)  ;
	
	/**
	 * 회차별 교재 목차 정보 수정
	 *
	 * @return ProcessReslutListVO
	 */
	public int updateCreateContents(ContentsVO iContentsVO) ;
	
	/**
	 * 회차별 교재 정보 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public ContentsVO selectCreateContents(ContentsVO ctsVO) ;
	
	/**
	 * 회차 교재 정보 일괄 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteAllCreateContents(ContentsVO ctsVO)  ;
	
	/**
	 * 회차 교재 삭제 목차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listCreateContentsDel(ContentsVO ctsVO)  ;
	
	/**
	 * 회차 단원 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCreateContents(ContentsVO ctsVO)  ;
	
	/**
	 * 회차 교재 목차 상위 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> parListCreateContents(ContentsVO ctsVO)  ;
	
	/**
	 * 회차 단일 목차 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCreateContents(ContentsVO iContentsVO)  ;
	
	/**
	 * 회차 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listCreCntSub(ContentsVO vo)   ;
	
	/**
	 * 회차 컨텐츠 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCreBatch(List<ContentsVO> contentsArray)   ;
	
	/**
	 * 컨텐츠 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	public List<ContentsVO> listCreCntSort(ContentsVO vo)   ;

}
