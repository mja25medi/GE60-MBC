package egovframework.edutrack.modules.log.menuconn.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.menuconn.service.LogWwwMenuConnLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 홈페이지 메뉴별 접속 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logWwwMenuConnLogMapper")
public interface LogWwwMenuConnLogMapper {

    /**
     * 오류 로그의 상세 정보를 등록한다.  
     * @param  LogWwwMenuConnLogVO
     * @return String
     * @
     */
    public void insert(LogWwwMenuConnLogVO vo)  ;
}
