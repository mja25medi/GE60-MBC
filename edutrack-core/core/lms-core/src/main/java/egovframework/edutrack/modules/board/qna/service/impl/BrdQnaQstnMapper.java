package egovframework.edutrack.modules.board.qna.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("brdQnaQstnMapper")
public interface BrdQnaQstnMapper {
	
	public int selectKey();

	public List<BrdQnaQstnVO> list(BrdQnaQstnVO vo);
	
	public List<BrdQnaQstnVO> listPageing(BrdQnaQstnVO vo);
	
	public int count(BrdQnaQstnVO vo);
	
	public BrdQnaQstnVO select(BrdQnaQstnVO vo) throws Exception;
	
	public BrdQnaQstnVO lecSelect(BrdQnaQstnVO vo);
	
	public int insert(BrdQnaQstnVO vo) throws Exception;
	
	public int update(BrdQnaQstnVO vo) throws Exception;
	
	public int updateQnaSts(BrdQnaQstnVO vo) throws Exception;
	
	public int delete(BrdQnaQstnVO vo) throws Exception;
	
	public int manageCount(BrdQnaQstnVO vo);
	
	public int manageStsCount(BrdQnaQstnVO vo);
	
	public List<BrdQnaQstnVO> listManage(BrdQnaQstnVO vo);
	
	public List<BrdQnaQstnVO> listManagePageing(BrdQnaQstnVO vo);

	public List<BrdQnaQstnVO> parCntsListByCreCd(BrdQnaQstnVO vo);
	
	public List<BrdQnaQstnVO> lecList(BrdQnaQstnVO vo);
	
	public List<BrdQnaQstnVO> lecListPageing(BrdQnaQstnVO vo);
}
