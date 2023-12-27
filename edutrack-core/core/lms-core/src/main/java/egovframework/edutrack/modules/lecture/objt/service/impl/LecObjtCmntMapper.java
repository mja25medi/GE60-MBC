package egovframework.edutrack.modules.lecture.objt.service.impl;

import java.util.List;
import java.util.Optional;

import egovframework.edutrack.modules.lecture.objt.service.LecObjtCmntVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("lecObjtCmntMapper")
public interface LecObjtCmntMapper {
	
	public int selectKey();

	public int insert(LecObjtCmntVO vo);
	
	public LecObjtCmntVO selectBySn(LecObjtCmntVO vo);
	
	public List<LecObjtCmntVO> listByObjtSn(LecObjtCmntVO vo);
	
	public List<LecObjtCmntVO> listByObjtSnVer5(LecObjtCmntVO vo);
	
	public int update(LecObjtCmntVO vo);
	
	public int delete(LecObjtCmntVO vo);
}
