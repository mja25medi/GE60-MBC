
package egovframework.edutrack.modules.org.noti.service;

import java.util.List;

public interface OrgNotiRecvService {
	
	public OrgNotiRecvVO view(OrgNotiRecvVO vo) throws Exception;
	
	public List<OrgNotiRecvVO> recvList(OrgNotiRecvVO vo);
	
	public int addRecv(OrgNotiRecvVO vo) throws Exception;
	
	public int updateRecv(OrgNotiRecvVO vo) throws Exception;

	public int deleteRecv(OrgNotiRecvVO vo) throws Exception;
}