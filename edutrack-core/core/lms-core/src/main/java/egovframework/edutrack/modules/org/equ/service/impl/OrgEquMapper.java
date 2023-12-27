package egovframework.edutrack.modules.org.equ.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.equ.service.OrgEquVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgEquMapper")
public interface OrgEquMapper {

	public String selectKey();
	
	public OrgEquVO select(OrgEquVO vo);
	
	public int count(OrgEquVO vo);
	
	public List<OrgEquVO> list(OrgEquVO vo);
	
	public List<OrgEquVO> listPaging(OrgEquVO vo);
	
	public int insert(OrgEquVO vo);
	
	public int update(OrgEquVO vo);
	
	public int updateUseYn(OrgEquVO vo);
	
	public int delete(OrgEquVO vo);
	
	public int updateEquOdr(OrgEquVO vo);
	
	public OrgEquVO selectForSwap(OrgEquVO vo);
	
	public int increaseOtherEquOdr(OrgEquVO vo);
	
	public int decreaseOtherEquOdr(OrgEquVO vo);
}
