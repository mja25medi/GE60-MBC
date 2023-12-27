package egovframework.edutrack.modules.log.userupdate.service.impl;

import egovframework.edutrack.modules.log.userupdate.service.LogUserUpdateLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("logUserUpdateLogMapper")
public interface LogUserUpdateLogMapper {

	public int insert(LogUserUpdateLogVO vo);
}
