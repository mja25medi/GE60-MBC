package egovframework.edutrack.modules.org.noti.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceIllegalArgumentException;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvService;
import egovframework.edutrack.modules.org.noti.service.OrgNotiRecvVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("orgNotiRecvService")
public class OrgNotiRecvServiceImpl extends EgovAbstractServiceImpl implements OrgNotiRecvService {
	
	@Resource(name="orgNotiRecvMapper")
	private OrgNotiRecvMapper 		orgNotiRecvMapper;

	@Override
	public OrgNotiRecvVO view(OrgNotiRecvVO vo) throws Exception {
		OrgNotiRecvVO notiRecv = orgNotiRecvMapper.select(vo);
		if(notiRecv == null) throw new ServiceIllegalArgumentException("해당하는 수신 정보가 없습니다.");
		
		return notiRecv;
	}

	@Override
	public List<OrgNotiRecvVO> recvList(OrgNotiRecvVO vo) {
		return orgNotiRecvMapper.list(vo);
	}

	@Override
	public int addRecv(OrgNotiRecvVO vo) throws Exception {
		return orgNotiRecvMapper.insert(vo);
	}

	@Override
	public int updateRecv(OrgNotiRecvVO vo) throws Exception {
		return orgNotiRecvMapper.update(vo);
	}

	@Override
	public int deleteRecv(OrgNotiRecvVO vo) throws Exception {
		return orgNotiRecvMapper.delete(vo);
	}
}

