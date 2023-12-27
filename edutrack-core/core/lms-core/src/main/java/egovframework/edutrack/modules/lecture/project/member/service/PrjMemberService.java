package egovframework.edutrack.modules.lecture.project.member.service;


import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface PrjMemberService {

	/**
	 * 팀관리 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultListVO<PrjMemberVO>
	 */
	public ProcessResultListVO<PrjMemberVO> list(PrjMemberVO vo) throws Exception;

	/**
	 * 팀원 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultListVO<PrjMemberVO>
	 */
	public ProcessResultListVO<PrjMemberVO> stdList(PrjMemberVO vo) throws Exception;
	
	/**
	 * 총학생수
	 * @author mhShin 
	 * @date 2013. 11. 04.
	 * @param vo
	 * @return ProcessResultVO<MemberVO>
	 */
	public ProcessResultVO<PrjMemberVO> selectLearnerCnt(PrjMemberVO vo) throws Exception;

	/**
	 * 팀원 등록
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 */
	public ProcessResultVO<PrjMemberVO> add(PrjMemberVO vo) throws Exception;
	
	/**
	 * 팀장 선정
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 */
	public ProcessResultVO<PrjMemberVO> edit(PrjMemberVO vo) throws Exception;

	/**
	 * 역할명세서 수정
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 */
	public ProcessResultVO<PrjMemberVO> editPrjMbr(PrjMemberVO vo) throws Exception;

	
	/**
	 * 팀원 삭제
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 */
	public ProcessResultVO<PrjMemberVO> remove(PrjMemberVO vo) throws Exception;

}