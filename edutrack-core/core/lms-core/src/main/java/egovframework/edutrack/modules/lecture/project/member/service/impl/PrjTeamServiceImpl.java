
package egovframework.edutrack.modules.lecture.project.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("prjTeamService")
public class PrjTeamServiceImpl extends EgovAbstractServiceImpl implements PrjTeamService {
	
	@Resource(name="prjTeamMapper")
	private PrjTeamMapper prjTeamMapper;
	
	@Resource(name="prjMemberMapper")
	private PrjMemberMapper prjMemberMapper;
	
	/**
	 * 팀관리 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 23.
	 * @param vo
	 * @return ProcessResultListVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjTeamVO> list(PrjTeamVO vo) throws Exception {
		ProcessResultListVO<PrjTeamVO> resultList = new ProcessResultListVO<PrjTeamVO>();
		try {
			List<PrjTeamVO> returnList =  prjTeamMapper.list(vo);
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
	 * 팀 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> view(PrjTeamVO vo) throws Exception {
		ProcessResultVO<PrjTeamVO> resultVO = new ProcessResultVO<PrjTeamVO>();
		try {
			PrjTeamVO returnVO = prjTeamMapper.select(vo);
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
	 * 팀 정보 등록
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> add(PrjTeamVO vo) throws Exception {
		ProcessResultVO<PrjTeamVO> resultVO = new ProcessResultVO<PrjTeamVO>();
		try {
			prjTeamMapper.insert(vo);
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
	 * 팀 정보 등록
	 * @author mhShin 
	 * @date 2013. 11. 04.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> addTeamCreate(PrjTeamVO vo) throws Exception {
		
		ProcessResultListVO<PrjMemberVO> resultListVO = new ProcessResultListVO<PrjMemberVO>();
		
		PrjMemberVO prjMemberVO = new PrjMemberVO();
		prjMemberVO.setCrsCreCd(vo.getCrsCreCd());
		prjMemberVO.setPrjtSn(vo.getPrjtSn());
		
		prjMemberMapper.deletePrjAll(prjMemberVO);
		prjTeamMapper.deleteAll(vo);
		
		int teamCnt = vo.getTeamCnt();
		int learnerCnt = vo.getLearnerCnt();
		

		List<PrjMemberVO> returnList =  prjMemberMapper.learnerList(prjMemberVO);
		resultListVO.setReturnList(returnList);
		
		ArrayList<PrjMemberVO> learnerList = (ArrayList<PrjMemberVO>)resultListVO.getReturnList();
		
		if("random".equals(vo.getLearnerAssign())){
			
			//팀원 순서 섞기
			int x,y;										//난수가 저장될 변수
			int mixCnt = 1000;								//섞는 횟수
			PrjMemberVO tempVO = new PrjMemberVO();	//임시로 저장할 VO
	
			for(int i=0; i<mixCnt; i++){
				x = (int)(Math.random()*learnerCnt);	//난수 x
				y = (int)(Math.random()*learnerCnt);	//난수 y
				
				//x,y값이 같지 않을 때		
				if(x != y){
					tempVO = (PrjMemberVO)learnerList.get(x);			//x위치 회원정보 ==> 임시VO
					learnerList.set(x,(PrjMemberVO)learnerList.get(y));	//y위치 회원정보 ==> x위치 회원정보
					learnerList.set(y,tempVO);								//임시VO ==> y위치 회원정보
				}
			}
		}
		
		PrjMemberVO prjMemberVO1 = null;
		
		int j = 0;					//팀순번
		boolean firstCnt = true;	//초기 1회 CYCLE 판단 변수

		for(int i=0; i<learnerCnt; i++){			
			
			j++; //팀순번 증가
			
			if(j < 10){
				vo.setPrjtTeamNm("TEAM_0"+(j));
			}else{
				vo.setPrjtTeamNm("TEAM_"+(j));
			}
			vo.setPrjtTeamSn(Integer.toString(j));
			
			prjMemberVO1 = (PrjMemberVO)learnerList.get(i);
			prjMemberVO1.setPrjtSn(vo.getPrjtSn());
			prjMemberVO1.setPrjtTeamSn(vo.getPrjtTeamSn());

			//최초 CYCLE 팀생성
			if(firstCnt){	
				prjMemberVO1.setTeamLeaderDiv("Y");	//팀장선정
				prjTeamMapper.insert(vo);	
			}

			prjMemberMapper.insert(prjMemberVO1);	//팀원추가

			if(j == teamCnt){
				j = 0;				//팀순번 초기화
				firstCnt = false;	//최초 CYCLE 종료
			}

		}

		return ProcessResultVO.success();
	}
	
	/**
	 * 팀 정보 수정
	 * @author mhShin 
	 * @date 2013. 11. 06.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> addProjectTeamCopy(PrjTeamVO vo) throws Exception {
		
		PrjMemberVO prjMemberVO = new PrjMemberVO();
		prjMemberVO.setCrsCreCd(vo.getCrsCreCd());
		prjMemberVO.setPrjtSn(vo.getPrjtSn());
		prjMemberVO.setSelPrjtSn(vo.getSelPrjtSn());
		
		prjMemberMapper.deletePrjAll(prjMemberVO);
		prjTeamMapper.deleteAll(vo);

		prjTeamMapper.insertTeam(vo);
		prjMemberMapper.insertMember(prjMemberVO);
		
		return ProcessResultVO.success();
	}
	
	/**
	 * 팀 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> edit(PrjTeamVO vo) throws Exception {
		ProcessResultVO<PrjTeamVO> resultVO = new ProcessResultVO<PrjTeamVO>();
		try {
			prjTeamMapper.update(vo);
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
	 * 팀 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjTeamVO> remove(PrjTeamVO vo) throws Exception {
		
		String[] teamList = StringUtil.split(vo.getPrjtTeamSn(),"|");
		List<PrjTeamVO> teamArray = new ArrayList<PrjTeamVO>();
		
		for (int i = 0; i < teamList.length; i++) {
			PrjMemberVO prjMemberVO = new PrjMemberVO();
			prjMemberVO.setPrjtTeamSn(teamList[i]);
			prjMemberMapper.deletePrjTeamAll(prjMemberVO);
			
			PrjTeamVO PrjTeamVO = new PrjTeamVO();
			PrjTeamVO.setCrsCreCd(vo.getCrsCreCd());
			PrjTeamVO.setPrjtSn(vo.getPrjtSn());
			PrjTeamVO.setPrjtTeamSn(teamList[i]);
			teamArray.add(PrjTeamVO);
		}
		
		prjTeamMapper.delete(teamArray);
		
		return ProcessResultVO.success();
	}

	
	
	
	
}
