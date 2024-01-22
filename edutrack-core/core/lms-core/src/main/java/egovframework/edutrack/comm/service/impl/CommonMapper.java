package egovframework.edutrack.comm.service.impl;

import java.util.Date;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>공통 - 공통  </b> 영역  Mapper 클래스
 * @author im
 *
 */
@Mapper("commonMapper")
public interface CommonMapper {

    /**
	 * DB의 시간을 가져온다. 
	 * @
	 */
	public Date selectCurrentDbTime() ;
}
