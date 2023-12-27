package egovframework.edutrack.modules.org.equ.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.equ.service.OrgEquItemVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgEquItemMapper")
public interface OrgEquItemMapper {
	
	public String selectKey();
	
	public OrgEquItemVO select(OrgEquItemVO vo);
	
	public int count(OrgEquItemVO vo);
	
	public List<OrgEquItemVO> list(OrgEquItemVO vo);
	
	public List<OrgEquItemVO> validItemList(OrgEquItemVO vo);
	
	public List<OrgEquItemVO> listPaging(OrgEquItemVO vo);
	
	public int insert(OrgEquItemVO vo);
	
	public int update(OrgEquItemVO vo);
	
	public int updateUseYn(OrgEquItemVO vo);
	
	public int delete(OrgEquItemVO vo);
}
