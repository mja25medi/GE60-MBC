package egovframework.edutrack.modules.lecture.project.member.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("prjMemberService")
public class PrjMemberServiceImpl extends EgovAbstractServiceImpl implements PrjMemberService {
	
	@Resource(name="prjMemberMapper")
	private PrjMemberMapper prjMemberMapper;
	
	/**
	 * 팀관리 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultListVO<PrjMemberVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjMemberVO> list(PrjMemberVO vo) throws Exception {
		ProcessResultListVO<PrjMemberVO> resultList = new ProcessResultListVO<PrjMemberVO>();
		try {
			List<PrjMemberVO> returnList =  prjMemberMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}
	
	/**
	 * 팀원 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultListVO<PrjMemberVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjMemberVO> stdList(PrjMemberVO vo) throws Exception {
		ProcessResultListVO<PrjMemberVO> resultList = new ProcessResultListVO<PrjMemberVO>();
		try {
			List<PrjMemberVO> returnList =  prjMemberMapper.stdList(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 총 학습자 수 
	 * @author mhShin 
	 * @date 2013. 11. 04.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 * @throws Exception 
	 */
	public ProcessResultVO<PrjMemberVO> selectLearnerCnt(PrjMemberVO vo) throws Exception{
		ProcessResultVO<PrjMemberVO> resultVO = new ProcessResultVO<PrjMemberVO>();
		try {
			PrjMemberVO returnVO = prjMemberMapper.selectLearnerCnt(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 팀원 등록
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 * @throws Exception 
	 */
	public ProcessResultVO<PrjMemberVO> add(PrjMemberVO vo) throws Exception{
		
		String[] teamMbrList = StringUtil.split(vo.getStdNo(),"|");
		
		for (int i = 0; i < teamMbrList.length; i++) {
			PrjMemberVO PrjMemberVO = new PrjMemberVO();
			PrjMemberVO.setCrsCreCd(vo.getCrsCreCd());
			PrjMemberVO.setPrjtSn(vo.getPrjtSn());
			PrjMemberVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			PrjMemberVO.setStdNo(teamMbrList[i]);
			prjMemberMapper.insert(PrjMemberVO);
		}
		
		return ProcessResultVO.success();
	}
	
	/**
	 * 팀장 선정
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 * @throws Exception 
	 */
	public ProcessResultVO<PrjMemberVO> edit(PrjMemberVO vo) throws Exception{
		ProcessResultVO<PrjMemberVO> resultVO = new ProcessResultVO<PrjMemberVO>();
		try {
			prjMemberMapper.update(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
	
		return resultVO;
	}

	/**
	 * 역할 명세서 수정
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 * @throws Exception 
	 */
	public ProcessResultVO<PrjMemberVO> editPrjMbr(PrjMemberVO vo) throws Exception{
		
		String[] teamMbrList = StringUtil.split(vo.getPrjtMbrSn(),"|");
		String[] mbrRoleList = StringUtil.split(vo.getMbrRole(),"|");
	
		List<PrjMemberVO> teamMbrArray = new ArrayList<PrjMemberVO>();
		
		for (int i = 0; i < teamMbrList.length; i++) {
			PrjMemberVO PrjMemberVO = new PrjMemberVO();
			PrjMemberVO.setCrsCreCd(vo.getCrsCreCd());
			PrjMemberVO.setPrjtSn(vo.getPrjtSn());
			PrjMemberVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			PrjMemberVO.setPrjtMbrSn(teamMbrList[i]);
			PrjMemberVO.setMbrRole(mbrRoleList[i]);
			teamMbrArray.add(PrjMemberVO);
		}
		prjMemberMapper.updatePrjMbr(teamMbrArray);
		
		
		return ProcessResultVO.success();
	}
	

	/**
	 * 팀원 삭제
	 * @author mhShin 
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<PrjMemberVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjMemberVO> remove(PrjMemberVO vo) throws Exception {
		String[] teamMbrList = StringUtil.split(vo.getPrjtMbrSn(),"|");
		List<PrjMemberVO> teamMbrArray = new ArrayList<PrjMemberVO>();
		
		for (int i = 0; i < teamMbrList.length; i++) {
			PrjMemberVO PrjMemberVO = new PrjMemberVO();
			PrjMemberVO.setCrsCreCd(vo.getCrsCreCd());
			PrjMemberVO.setPrjtSn(vo.getPrjtSn());
			PrjMemberVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			PrjMemberVO.setPrjtMbrSn(teamMbrList[i]);
			teamMbrArray.add(PrjMemberVO);
		}
		prjMemberMapper.delete(teamMbrArray);
		
		
		return ProcessResultVO.success();
	}



}

