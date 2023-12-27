package egovframework.edutrack.modules.lecture.objt.service;

import java.util.List;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface LecObjtService {

	public abstract int makeObjection(LecObjtVO vo) throws Exception;
	
	public abstract ProcessResultListVO<LecObjtVO> getObjectionList(LecObjtVO vo); 
	
	public abstract LecObjtVO getObjection(LecObjtVO vo) throws Exception;
	
	public abstract int modifyObjection(LecObjtVO vo);
	
	public abstract int deleteObjection(LecObjtVO vo);
	
	public abstract List<LecObjtCmntVO> getCmntList(LecObjtVO vo);
	
	public abstract int makeObjectionCmnt(LecObjtCmntVO vo);
	
	public abstract int deleteCmnt(LecObjtCmntVO vo);
}
