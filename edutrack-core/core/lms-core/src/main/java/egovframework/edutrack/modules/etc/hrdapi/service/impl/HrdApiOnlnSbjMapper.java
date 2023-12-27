package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiOnlnSbjMapper")
public interface HrdApiOnlnSbjMapper {

	public List<EgovMap> list(HrdApiOnlnSbjVO vo);
	
	public List<EgovMap> listPageing(HrdApiOnlnSbjVO vo);
	
	public List<EgovMap> listForSync(HrdApiOnlnSbjVO vo);
	
	public int count(HrdApiOnlnSbjVO vo);
	
	public int insert(HrdApiOnlnSbjVO vo);
	
	public int updateSyncStatus(HrdApiOnlnSbjVO vo);
	
	public int delete(HrdApiOnlnSbjVO vo);

	public HrdApiOnlnSbjVO selectRecentOne(HrdApiOnlnSbjVO vo);
}

