package egovframework.edutrack.modules.org.ide.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.ide.service.OrgIdeInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgIdeInfoMapper")
public interface OrgIdeInfoMapper {

	public List<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo);

	public int count(OrgIdeInfoVO vo);

	public int insert(OrgIdeInfoVO vo);

	public int delete(OrgIdeInfoVO vo) ;

	public int updateUseYn(OrgIdeInfoVO vo) ;

	public String selectIdeUrlCheck(OrgIdeInfoVO vo);

}
