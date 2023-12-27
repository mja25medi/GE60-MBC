package egovframework.edutrack.comm.service.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 *  <b>공통 - 공통  </b> 영역  Mapper 클래스
 * @author im
 *
 */
@Mapper("commonMapper")
public interface CommonMapper {

    /**
	 * DB의 시간을 가져온다. 
	 * @throws Exception
	 */
	public Date selectCurrentDbTime() throws Exception;
}
