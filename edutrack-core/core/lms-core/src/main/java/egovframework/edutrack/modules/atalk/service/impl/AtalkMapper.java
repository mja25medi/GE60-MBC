package egovframework.edutrack.modules.atalk.service.impl;

import egovframework.edutrack.modules.atalk.AtalkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("atalkMapper")
public interface AtalkMapper {
	
	
    /**
     * 알림톡을 등록한다.  
     * @param  AtalkVO
     * @return String
     * @throws Exception
     */
    public int insert(AtalkVO vo) throws Exception;
    
}
