package egovframework.edutrack.modules.lecture.objt.service.impl;

import java.util.List;
import java.util.Optional;

import egovframework.edutrack.modules.lecture.objt.service.LecObjtVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("lecObjtMapper")
public interface LecObjtMapper {
	
	public int selectKey();
	
	public int insert(LecObjtVO vo);
	
	public LecObjtVO selectBySn(LecObjtVO vo);
	
	public LecObjtVO select(LecObjtVO vo);
	
	public List<LecObjtVO> listByCreCd(LecObjtVO vo);
	
	public List<LecObjtVO> listPageingByCreCd(LecObjtVO vo);
	
	public int updateSts(LecObjtVO vo);
	
	public int hitsup(LecObjtVO vo);
	
	public int count(LecObjtVO vo);
	
	public int update(LecObjtVO vo);
	
	public int delete(LecObjtVO vo);
}
