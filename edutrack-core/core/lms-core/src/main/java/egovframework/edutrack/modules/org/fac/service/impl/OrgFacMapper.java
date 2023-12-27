package egovframework.edutrack.modules.org.fac.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.fac.service.OrgFacCtgrVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacInfoVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgFacMapper")
public interface OrgFacMapper {
	public int facResUpdate(OrgFacResVO vo);
	
	public int facInfoUpdate(OrgFacInfoVO vo);
	
	public int facUseUpdate(OrgFacInfoVO vo);
	
	public int facRemove(OrgFacInfoVO vo);
	
	public String selectFacKey();
	
	public String selectResKey();
	
	public String selectCtgrKey();
	
	public int addFacCtgr(OrgFacCtgrVO vo);
	
	public OrgFacResVO selectFacRes(OrgFacResVO vo);
	
	public OrgFacInfoVO selectFacInfo(OrgFacInfoVO vo);
	
	public List<OrgFacInfoVO> listFac(OrgFacInfoVO vo);
	
	public List<OrgFacResVO> list(OrgFacResVO vo);
	
	public List<OrgFacInfoVO> listInfo(OrgFacInfoVO vo);
	
	public List<OrgFacResVO> listPageing(OrgFacResVO vo);
	
	public List<OrgFacInfoVO> listInfoPageing(OrgFacInfoVO vo);
	
	public int count(OrgFacResVO vo);
	
	public int infoCount(OrgFacInfoVO vo);
	
	public int addRes(OrgFacResVO vo);
	
	public int addFac(OrgFacInfoVO vo);
	
	public OrgFacCtgrVO selectFacCtgr(OrgFacCtgrVO vo);
	
	public List<OrgFacCtgrVO> listFacCtgr(OrgFacCtgrVO vo);
	
	public int updateFacCtgr(OrgFacCtgrVO vo);
	
	public int deleteFacCtgr(OrgFacCtgrVO vo);
	
	public List<OrgFacResVO> resListForTimeChk(OrgFacResVO vo);
	
	public int updateFacOdr(OrgFacInfoVO vo);
	
	public OrgFacInfoVO selectForSwap(OrgFacInfoVO vo);
	
	public int increaseOtherFacOdr(OrgFacInfoVO vo);
	
	public int decreaseOtherFacOdr(OrgFacInfoVO vo);
}
