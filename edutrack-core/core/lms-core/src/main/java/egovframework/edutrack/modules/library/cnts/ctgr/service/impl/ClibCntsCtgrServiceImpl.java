package egovframework.edutrack.modules.library.cnts.ctgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.cnts.ctgr.service.impl.ClibCntsCtgrMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("clibCntsCtgrService")
public class ClibCntsCtgrServiceImpl extends EgovAbstractServiceImpl implements ClibCntsCtgrService {

	/** Mapper */
	@Resource(name="clibCntsCtgrMapper")
	private ClibCntsCtgrMapper 	clibCntsCtgrMapper;

	/**
	 * 콘텐츠 분류를 트리 형태의 목록으로 가져온다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibCntsCtgrVO> list(ClibCntsCtgrVO vo)  throws Exception{
		ProcessResultListVO<ClibCntsCtgrVO> resultList = new ProcessResultListVO<ClibCntsCtgrVO>();
		List<ClibCntsCtgrVO> resultListVO = clibCntsCtgrMapper.list(vo);

		//최상단용 VO 만들기
		ClibCntsCtgrVO cccvo = new ClibCntsCtgrVO();

		//-- 트리형태로 목록을 구성하여 내보낸다.
		for (ClibCntsCtgrVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParCtgrCd())) {
				cccvo.addSubVO(parent);
			}
			for (ClibCntsCtgrVO child : resultListVO) {
				if(parent.getCtgrCd().equals(child.getParCtgrCd())) {
					parent.addSubVO(child);
				}
			}
		}
		//반환할 리스트 만들기
		resultList.setReturnList(cccvo.getSubList());
		return resultList;
	}

	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibCntsCtgrVO> listSub(ClibCntsCtgrVO vo)  throws Exception{
		ProcessResultListVO<ClibCntsCtgrVO> resultList = new ProcessResultListVO<ClibCntsCtgrVO>();
		try {
			List<ClibCntsCtgrVO> returnList = clibCntsCtgrMapper.listSub(vo);
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
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ClibCntsCtgrVO> view(ClibCntsCtgrVO vo) throws Exception {
		ProcessResultVO<ClibCntsCtgrVO> resultVO = new ProcessResultVO<ClibCntsCtgrVO>();
		try {
			ClibCntsCtgrVO returnVO = clibCntsCtgrMapper.select(vo);
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
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ClibCntsCtgrVO> add(ClibCntsCtgrVO vo) throws Exception{
		
		ProcessResultVO<ClibCntsCtgrVO> resultVO = new ProcessResultVO<ClibCntsCtgrVO>();
		try {
			//---- 분류 코드 생성
			ClibCntsCtgrVO ocvo = clibCntsCtgrMapper.selectCtgrCd();

			//---- 신규 분류 코드 세팅
			vo.setCtgrCd(ocvo.getCtgrCd());

			String ctgrPath = "";
			String ctgrPathNm = "";
			int ctgrLvl = 0;
			try {
				//---- 상위 분류 정보 검색
				ClibCntsCtgrVO pocvo = new ClibCntsCtgrVO();
				pocvo.setOrgCd(vo.getOrgCd());
				pocvo.setUserNo(vo.getUserNo());
				pocvo.setCtgrCd(vo.getParCtgrCd());
				pocvo = clibCntsCtgrMapper.select(pocvo);

				ctgrPath = pocvo.getCtgrPath();
				ctgrPathNm = pocvo.getCtgrPathNm();
				ctgrLvl = pocvo.getCtgrLvl();
			} catch (Exception e) {	}

			//---- 메뉴 레벨 : 상위 메뉴 레벨 + 1
			vo.setCtgrLvl(ctgrLvl+1);
			vo.setCtgrPath(ctgrPath+"/"+vo.getCtgrCd());
			vo.setCtgrPathNm(ctgrPathNm+"/"+vo.getCtgrNm());
			
			clibCntsCtgrMapper.insert(vo);
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
	 * @param ClibCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsCtgrVO> edit(ClibCntsCtgrVO vo) throws Exception {
		
		ProcessResultVO<ClibCntsCtgrVO> resultVO = new ProcessResultVO<ClibCntsCtgrVO>();
		try {
			String ctgrPathNm = "";
			if(ValidationUtils.isNotEmpty(vo.getParCtgrCd())) {
				ClibCntsCtgrVO cccvo = new ClibCntsCtgrVO();
				cccvo.setCtgrCd(vo.getParCtgrCd());
				cccvo.setOrgCd(vo.getOrgCd());
				cccvo.setUserNo(vo.getUserNo());
				cccvo = clibCntsCtgrMapper.select(cccvo);
				ctgrPathNm = cccvo.getCtgrPathNm();
			}
			ctgrPathNm += "/"+vo.getCtgrNm();
			vo.setCtgrPathNm(ctgrPathNm);
			clibCntsCtgrMapper.update(vo);
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
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsCtgrVO> delete(ClibCntsCtgrVO vo) throws Exception{
		ProcessResultVO<ClibCntsCtgrVO> resultVO = new ProcessResultVO<ClibCntsCtgrVO>();
		try {
			clibCntsCtgrMapper.delete(vo);
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
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsCtgrVO> deleteSub(ClibCntsCtgrVO vo) throws Exception{
		ProcessResultVO<ClibCntsCtgrVO> resultVO = new ProcessResultVO<ClibCntsCtgrVO>();
		try {
			clibCntsCtgrMapper.deleteSub(vo);
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
	public ProcessResultVO<?> move(ClibCntsCtgrVO vo, String moveType) throws Exception {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibCntsCtgrVO> olcCtgrList = clibCntsCtgrMapper.listSub(vo);
		List<ClibCntsCtgrVO> newCtgrList = new ArrayList<ClibCntsCtgrVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibCntsCtgrVO ocvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ClibCntsCtgrVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
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
			ClibCntsCtgrVO nocvo = null;

			for(ClibCntsCtgrVO ocvo : olcCtgrList) {
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
		for(ClibCntsCtgrVO dvo : newCtgrList) {
			order++;
			dvo.setCtgrOdr(order);
		}
		clibCntsCtgrMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}
}
