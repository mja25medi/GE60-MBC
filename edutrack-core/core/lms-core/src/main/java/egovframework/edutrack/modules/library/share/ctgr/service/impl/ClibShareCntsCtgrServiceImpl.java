package egovframework.edutrack.modules.library.share.ctgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("clibShareCntsCtgrService")
public class ClibShareCntsCtgrServiceImpl extends EgovAbstractServiceImpl implements ClibShareCntsCtgrService{

	/** Mapper */
	@Resource(name="clibShareCntsCtgrMapper")
	private ClibShareCntsCtgrMapper 	clibShareCntsCtgrMapper;

	/**
	 * 콘텐츠 분류를 트리 형태의 목록으로 가져온다.
	 * @param ClibShareCntsCtgrVO.orgCd
	 * @param ClibShareCntsCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareCntsCtgrVO> list(ClibShareCntsCtgrVO vo) throws Exception {
		ProcessResultListVO<ClibShareCntsCtgrVO> result = new ProcessResultListVO<ClibShareCntsCtgrVO>();
		List<ClibShareCntsCtgrVO> resultListVO = clibShareCntsCtgrMapper.list(vo);

		//최상단용 VO 만들기
		ClibShareCntsCtgrVO cccvo = new ClibShareCntsCtgrVO();

		//-- 트리형태로 목록을 구성하여 내보낸다.
		for (ClibShareCntsCtgrVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParCtgrCd())) {
				cccvo.addSubVO(parent);
			}
			for (ClibShareCntsCtgrVO child : resultListVO) {
				if(parent.getCtgrCd().equals(child.getParCtgrCd())) {
					parent.addSubVO(child);
				}
			}
		}
		//반환할 리스트 만들기
		result.setReturnList(cccvo.getSubList());
		return result;
	}

	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param ClibShareCntsCtgrVO.orgCd
	 * @param ClibShareCntsCtgrVO.userNo
	 * @param ClibShareCntsCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareCntsCtgrVO> listSub(ClibShareCntsCtgrVO vo) throws Exception  {
		ProcessResultListVO<ClibShareCntsCtgrVO> resultList = new ProcessResultListVO<ClibShareCntsCtgrVO>();
		try {
			List<ClibShareCntsCtgrVO> returnList = clibShareCntsCtgrMapper.listSub(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
//			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}

	/**
	 * 분류 정보를 조회한다.
	 * @param ClibShareCntsCtgrVO.orgCd
	 * @param ClibShareCntsCtgrVO.userNo
	 * @param ClibShareCntsCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareCntsCtgrVO> view(ClibShareCntsCtgrVO vo)  throws Exception {
		ProcessResultVO<ClibShareCntsCtgrVO> resultVO = new ProcessResultVO<ClibShareCntsCtgrVO>();
		try {
			ClibShareCntsCtgrVO returnVO = clibShareCntsCtgrMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
//			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareCntsCtgrVO> add(ClibShareCntsCtgrVO vo) throws Exception {
		ProcessResultVO<ClibShareCntsCtgrVO> resultVO = new ProcessResultVO<ClibShareCntsCtgrVO>();
		
		try {
			//---- 분류 코드 생성
			ClibShareCntsCtgrVO ocvo = clibShareCntsCtgrMapper.selectCtgrCd();

			//---- 신규 분류 코드 세팅
			vo.setCtgrCd(ocvo.getCtgrCd());

			String ctgrPath = "";
			String ctgrPathNm = "";
			int ctgrLvl = 0;
			try {
				//---- 상위 분류 정보 검색
				ClibShareCntsCtgrVO pocvo = new ClibShareCntsCtgrVO();
				pocvo.setOrgCd(vo.getOrgCd());
				pocvo.setCtgrCd(vo.getParCtgrCd());
				pocvo = clibShareCntsCtgrMapper.select(pocvo);

				ctgrPath = pocvo.getCtgrPath();
				ctgrPathNm = pocvo.getCtgrPathNm();
				ctgrLvl = pocvo.getCtgrLvl();
			} catch (Exception e) {	}

			//---- 메뉴 레벨 : 상위 메뉴 레벨 + 1
			vo.setCtgrLvl(ctgrLvl+1);
			vo.setCtgrPath(ctgrPath+"/"+vo.getCtgrCd());
			vo.setCtgrPathNm(ctgrPathNm+"/"+vo.getCtgrNm());
			
			clibShareCntsCtgrMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
//			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		return resultVO;
	}

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareCntsCtgrVO> edit(ClibShareCntsCtgrVO vo)  throws Exception {
		String ctgrPathNm = "";
		ProcessResultVO<ClibShareCntsCtgrVO> resultVO = new ProcessResultVO<ClibShareCntsCtgrVO>();
		
		try {
			if(ValidationUtils.isNotEmpty(vo.getParCtgrCd())) {
				ClibShareCntsCtgrVO cccvo = new ClibShareCntsCtgrVO();
				cccvo.setCtgrCd(vo.getParCtgrCd());
				cccvo.setOrgCd(vo.getOrgCd());
				cccvo = clibShareCntsCtgrMapper.select(cccvo);
				ctgrPathNm = cccvo.getCtgrPathNm();
			}
			ctgrPathNm += "/"+vo.getCtgrNm();
			vo.setCtgrPathNm(ctgrPathNm);

			clibShareCntsCtgrMapper.update(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
//			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		return resultVO;
	}

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO.orgCd
	 * @param ClibShareCntsCtgrVO.userNo
	 * @param ClibShareCntsCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareCntsCtgrVO> delete(ClibShareCntsCtgrVO vo) throws Exception {
		ProcessResultVO<ClibShareCntsCtgrVO> resultVO = new ProcessResultVO<ClibShareCntsCtgrVO>();
		try {
			clibShareCntsCtgrMapper.delete(vo);
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
	 * @param ClibShareCntsCtgrVO.orgCd
	 * @param ClibShareCntsCtgrVO.userNo
	 * @param ClibShareCntsCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareCntsCtgrVO> deleteSub(ClibShareCntsCtgrVO vo) throws Exception {
		ProcessResultVO<ClibShareCntsCtgrVO> resultVO = new ProcessResultVO<ClibShareCntsCtgrVO>();
		try {
			clibShareCntsCtgrMapper.deleteSub(vo);
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
	 * 콘텐츠  분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibShareCntsCtgrVO vo, String moveType)  throws Exception {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibShareCntsCtgrVO> olcCtgrList = clibShareCntsCtgrMapper.listSub(vo);
		List<ClibShareCntsCtgrVO> newCtgrList = new ArrayList<ClibShareCntsCtgrVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibShareCntsCtgrVO ocsccvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocsccvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ClibShareCntsCtgrVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newCtgrList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newCtgrList.add(ocsccvo);
					newCtgrList.add(socvo);
				} else {
					newCtgrList.add(ocsccvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			ClibShareCntsCtgrVO ncsccvo = null;

			for(ClibShareCntsCtgrVO ocvo : olcCtgrList) {
				if(ocvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ncsccvo = ocvo;
				} else {
					newCtgrList.add(ocvo);
					if(ValidationUtils.isNotEmpty(ncsccvo)) {
						newCtgrList.add(ncsccvo);
						ncsccvo = null;
					}
				}
			}
		}
		int order = 0;
		for(ClibShareCntsCtgrVO dvo : newCtgrList) {
			order++;
			dvo.setCtgrOdr(order);
		}
		clibShareCntsCtgrMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}
}
