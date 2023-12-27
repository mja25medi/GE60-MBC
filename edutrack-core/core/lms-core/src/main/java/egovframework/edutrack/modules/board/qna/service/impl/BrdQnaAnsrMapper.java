package egovframework.edutrack.modules.board.qna.service.impl;

import egovframework.edutrack.modules.board.qna.service.BrdQnaAnsrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("brdQnaAnsrMapper")
public interface BrdQnaAnsrMapper {
	public BrdQnaAnsrVO select(BrdQnaAnsrVO vo);
	
	public int insert(BrdQnaAnsrVO vo);
	
	public int update(BrdQnaAnsrVO vo);
	
	public int delete(BrdQnaAnsrVO vo); 
}
