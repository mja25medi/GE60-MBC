package egovframework.edutrack.modules.olc.olcctgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrService;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("olcCtgrService")
public class OlcCtgrServiceImpl
	extends EgovAbstractServiceImpl implements OlcCtgrService {

	/** Mapper */
	@Resource(name="olcCtgrMapper")
	private OlcCtgrMapper olcCtgrMapper;

	/**
	 * OLC 분류를 TREE 형 목록으로 조회
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (선택적으로 입력, 최상위 분류부터 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcCtgrVO> listTree(OlcCtgrVO vo) throws Exception {
		ProcessResultListVO<OlcCtgrVO> resultList = new ProcessResultListVO<OlcCtgrVO>();
		try {
			List<OlcCtgrVO> returnList = olcCtgrMapper.listTree(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * OLC 분류의 하위 분류를 목록으로 가져온다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcCtgrVO> listSub(OlcCtgrVO vo) throws Exception {
		ProcessResultListVO<OlcCtgrVO> resultList = new ProcessResultListVO<OlcCtgrVO>();
		try {
			List<OlcCtgrVO> returnList = olcCtgrMapper.listSub(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 분류 정보를 조회한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcCtgrVO> view(OlcCtgrVO vo) throws Exception {
		ProcessResultVO<OlcCtgrVO> resultVO = new ProcessResultVO<OlcCtgrVO>();
		try {
			OlcCtgrVO returnVO = olcCtgrMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcCtgrVO> add(OlcCtgrVO vo) throws Exception {
		//---- 분류 코드 생성
		OlcCtgrVO ocvo = olcCtgrMapper.selectCtgrCd();

		//---- 신규 메뉴코드 세팅
		vo.setCtgrCd(ocvo.getCtgrCd());

		String ctgrPath = "";
		String ctgrPathNm = "";
		int ctgrLvl = 0;
		try {
			//---- 상위 분류 정보 검색
			OlcCtgrVO pocvo = new OlcCtgrVO();
			pocvo.setOrgCd(vo.getOrgCd());
			pocvo.setUserNo(vo.getUserNo());
			pocvo.setCtgrCd(vo.getParCtgrCd());
			pocvo = olcCtgrMapper.select(pocvo);

			ctgrPath = pocvo.getCtgrPath();
			ctgrPathNm = pocvo.getCtgrPathNm();
			ctgrLvl = pocvo.getCtgrLvl();
		} catch (Exception e) {	}

		//---- 메뉴 레벨 : 상위 메뉴 레벨 + 1
		vo.setCtgrLvl(ctgrLvl+1);
		vo.setCtgrPath(ctgrPath+"/"+vo.getCtgrCd());
		vo.setCtgrPathNm(ctgrPathNm+"/"+vo.getCtgrNm());
		
		ProcessResultVO<OlcCtgrVO> resultVO = new ProcessResultVO<OlcCtgrVO>();
		try {
			olcCtgrMapper.insert(vo);
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
	 * 분류를 수정하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcCtgrVO> edit(OlcCtgrVO vo) throws Exception {
		ProcessResultVO<OlcCtgrVO> resultVO = new ProcessResultVO<OlcCtgrVO>();
		try {
			olcCtgrMapper.update(vo);
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
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcCtgrVO> delete(OlcCtgrVO vo) throws Exception {
		ProcessResultVO<OlcCtgrVO> resultVO = new ProcessResultVO<OlcCtgrVO>();
		try {
			olcCtgrMapper.delete(vo);
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
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcCtgrVO> deleteSub(OlcCtgrVO vo) throws Exception {
		ProcessResultVO<OlcCtgrVO> resultVO = new ProcessResultVO<OlcCtgrVO>();
		try {
			olcCtgrMapper.deleteSub(vo);
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
	 * OLC 분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(OlcCtgrVO vo, String moveType) throws Exception {

		//-- 해당 카테고리 내의 모든 OLC 목록을 구한다.
		List<OlcCtgrVO> olcCtgrList = olcCtgrMapper.listSub(vo);
		List<OlcCtgrVO> newCtgrList = new ArrayList<OlcCtgrVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(OlcCtgrVO ocvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocvo.getCtgrCd().equals(vo.getCtgrCd())) {
					OlcCtgrVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newCtgrList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newCtgrList.add(ocvo);
					newCtgrList.add(socvo);
				} else {
					newCtgrList.add(ocvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			OlcCtgrVO nocvo = null;

			for(OlcCtgrVO ocvo : olcCtgrList) {
				if(ocvo.getCtgrCd().equals(vo.getCtgrCd())) {
					nocvo = ocvo;
				} else {
					newCtgrList.add(ocvo);
					if(ValidationUtils.isNotEmpty(nocvo)) {
						newCtgrList.add(nocvo);
						nocvo = null;
					}
				}
			}
		}
		int order = 0;
		for(OlcCtgrVO dvo : newCtgrList) {
			order++;
			dvo.setCtgrOdr(order);
		}
		olcCtgrMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}
}
