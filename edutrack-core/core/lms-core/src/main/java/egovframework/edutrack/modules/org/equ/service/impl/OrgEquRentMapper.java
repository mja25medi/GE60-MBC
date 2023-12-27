package egovframework.edutrack.modules.org.equ.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.equ.service.OrgEquItemVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentDetailVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgEquRentMapper")
public interface OrgEquRentMapper {

	public String selectRentKey();
	
	public OrgEquRentVO select(OrgEquRentVO vo);
	
	public int count(OrgEquRentVO vo);
	
	public List<OrgEquRentVO> list(OrgEquRentVO vo);
	
	public List<OrgEquRentVO> listPaging(OrgEquRentVO vo);
	
	public int insert(OrgEquRentVO vo);
	
	public int update(OrgEquRentVO vo);
	
	public int delete(OrgEquRentVO vo);
	
	public OrgEquRentDetailVO selectDetail(OrgEquRentDetailVO vo);
	
	public List<OrgEquRentDetailVO> listDetail(OrgEquRentDetailVO vo);
	
	public int insertDetail(OrgEquRentDetailVO vo);
	
	public int updateDetail(OrgEquRentDetailVO vo);
	
	public int deleteDetailByRentCd(OrgEquRentDetailVO vo);
	
	public int deleteDetail(OrgEquRentDetailVO vo);
}
