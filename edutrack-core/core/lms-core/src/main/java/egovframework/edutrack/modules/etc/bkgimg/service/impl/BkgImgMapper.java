package egovframework.edutrack.modules.etc.bkgimg.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("bkgImgMapper")
public interface  BkgImgMapper{

	/**
	 * 과제 번호 조회
	 */
	public int selectKey() ;
	/**
     * 배경이미지 전체 목록을 반환한다.
     * @param BkgImgVO.useYn
     * @return ProcessReslutListVO
     */
	public List<BkgImgVO> list(BkgImgVO VO) ;

	/**
     * 시스템 템플릿 전테 목록을 반환한다.
     * @param BkgImgVO.useYn
     * @param curPage
     * @param listScale
     * @param pageScale
     * @return ProcessReslutListVO
     */
	public List<BkgImgVO> listPageing(BkgImgVO VO) ;

	/**
     * 시스템 템플릿 전테 목록을 반환한다.
     * @param BkgImgVO.useYn
     * @param curPage
     * @param listScale
     * @param pageScale
     * @return ProcessReslutListVO
     */
	public int count(BkgImgVO VO) ;
	
	/**
     * 배경이미지의 단일 항목 정보를 반환한다.
     * @param BkgImgVO.bkgrImgSn
     * @return ProcessResultVO
     */
	public BkgImgVO select(BkgImgVO VO) ;



	/**
	 * 배경이미지를 등록하고 결과를 반환한다.
	 * @param BkgImgVO
	 * @return
	 */
	public int insert(BkgImgVO VO) ;


    /**
     * 배경이미지 정보를 수정하고 결과를 반환한다.
     * @param BkgImgVO
     * @reurn ProcessResultVO
     */
	public int update(BkgImgVO VO) ;
	
	/**
     * 배경이미지 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<BkgImgVO> codeArray) ;



    /**
     * 시스템 코드 삭제
     *
     * @reurn ProcessResultVO
     */
	public int delete(BkgImgVO VO);


}
