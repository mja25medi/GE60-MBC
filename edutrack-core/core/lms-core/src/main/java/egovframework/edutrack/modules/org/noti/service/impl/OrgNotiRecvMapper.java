package egovframework.edutrack.modules.org.noti.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
	
@Mapper("orgNotiRecvMapper")
public interface OrgNotiRecvMapper {
	public OrgNotiRecvVO select(OrgNotiRecvVO vo);
	
	public List<OrgNotiRecvVO> list(OrgNotiRecvVO vo);
	
	public int insert(OrgNotiRecvVO vo);
	
	public int update(OrgNotiRecvVO vo);
	
	public int delete(OrgNotiRecvVO vo);
}
