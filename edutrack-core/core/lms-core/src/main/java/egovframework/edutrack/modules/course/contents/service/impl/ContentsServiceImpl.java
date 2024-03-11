package egovframework.edutrack.modules.course.contents.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.contents.service.ContentsFileVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.impl.SubjectOnlineMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("contentsService")
public class ContentsServiceImpl 
		extends EgovAbstractServiceImpl implements ContentsService {

	/** Mapper */
	@Resource(name="contentsMapper")
	private ContentsMapper 		contentsMapper;

	@Resource(name="subjectOnlineMapper")
	private SubjectOnlineMapper 		subjectOnlineMapper;


	/**
	 * 교재 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listContents(ContentsVO vo)  throws Exception {
		
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(vo);
			}else {
				returnList = contentsMapper.listContents(vo);
			}
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}	
	@Override
	public ProcessResultListVO<ContentsVO> listContentsList(String sbjCd, String parUnitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setParUnitCd(parUnitCd);
		
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(ctsVO);
			}else {
				returnList = contentsMapper.listContents(ctsVO);
			}
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	@Override
	public ContentsVO listContentsTree(String sbjCd, String parUnitCd)  throws Exception {
		//---- 과목 관련 정보 검색
		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(sbjCd);
		onsbjVO = subjectOnlineMapper.select(onsbjVO);

		ContentsVO contentsVO = new ContentsVO();
		contentsVO.setSbjCd(sbjCd);
		contentsVO.setUnitType("R");
		contentsVO.setUnitNm(onsbjVO.getSbjNm());

		//listContentsTreeMake(sbjCd, parUnitCd, contentsVO);
		List<ContentsVO> subList  = new ArrayList<ContentsVO>();
		listContentsTreeMake2(sbjCd, parUnitCd, contentsVO,subList);


		return contentsVO;
	}

	/**
	 * 교재 목록 만들기 (트리형태의 목록 만들기) 2012.04.04 twkim 수정 (하단의 listContentsTreeMake는 재귀함수 잘못처리됨)
	 * @param sbjCd
	 * @param parUnitCd
	 * @param iContentsVO
	 */
	@Override
	public void listContentsTreeMake2(String sbjCd, String parUnitCd, ContentsVO iContentsVO, List<ContentsVO> subList)   throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setParUnitCd(parUnitCd);
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(ctsVO);
			}else {
				returnList = contentsMapper.listContents(ctsVO);
			}
			
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}

		if("C".equals(iContentsVO.getUnitType()))subList.add(iContentsVO);

		for(int i=0; i < resultListVO.getReturnList().size(); i++) {
			ContentsVO contentsVO = resultListVO.getReturnList().get(i);
			if(contentsVO.getSubCnt() > 0) {
				listContentsTreeMake2(sbjCd, contentsVO.getUnitCd(), contentsVO, subList);
			}
			if(!"C".equals(contentsVO.getUnitType()))subList.add(contentsVO);
		}
		iContentsVO.setSubList(subList);
	}


	/**
	 * 교재 목록 만들기 (트리형태의 목록 만들기)
	 * @param sbjCd
	 * @param parUnitCd
	 * @param iContentsVO
	 */
	@Override
	public void listContentsTreeMake(String sbjCd, String parUnitCd, ContentsVO iContentsVO)   throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setParUnitCd(parUnitCd);
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(ctsVO);
			}else {
				returnList = contentsMapper.listContents(ctsVO);
			}
			
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}

		List<ContentsVO> subList = new ArrayList<ContentsVO>();

		for(int i=0; i < resultListVO.getReturnList().size(); i++) {
			ContentsVO contentsVO = resultListVO.getReturnList().get(i);
			if(contentsVO.getSubCnt() > 0) {
				listContentsTreeMake(sbjCd, contentsVO.getUnitCd(), contentsVO);
			}
			subList.add(contentsVO);
		}
		iContentsVO.setSubList(subList);
	}

	/**
	 * 교재 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> viewContents(String sbjCd, String unitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setUnitCd(unitCd);

		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			resultVO.setReturnVO(contentsMapper.selectContents(ctsVO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}
	
	/**
	 * 교재 정보 조회 (회차)
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> viewCreContents(String sbjCd,String crsCreCd, String unitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setUnitCd(unitCd);
		ctsVO.setCrsCreCd(crsCreCd);

		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			resultVO.setReturnVO(contentsMapper.selectCreContents(ctsVO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}

	/**
	 * 교재 정보 조회 (회차)
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> viewCreContents(String sbjCd,String crsCreCd, String unitCd, String stdNo)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setUnitCd(unitCd);
		ctsVO.setCrsCreCd(crsCreCd);
		ctsVO.setStdNo(stdNo);

		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			resultVO.setReturnVO(contentsMapper.selectCreContents(ctsVO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}
	
	/**
	 * 교재 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> addContents(ContentsVO iContentsVO)  throws Exception {
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();

		//---- 상위 코드가 있는 교재 등록인 경우 상위 코드는 쳅터로 변경
		if(!"".equals(StringUtil.nvl(iContentsVO.getParUnitCd())) && !"CNTSROOT".equals(iContentsVO.getParUnitCd())) {
			//---- 상위 코드 값 검색
			resultVO.setReturnVO(contentsMapper.selectContents(iContentsVO));
			String prgrChkType = resultVO.getReturnVO().getPrgrChkType();
			iContentsVO.setPrgrChkType(prgrChkType);
			ContentsVO parentContentsVO = resultVO.getReturnVO();
			parentContentsVO.setUnitType("C");
			parentContentsVO.setUnitFilePath("");
			parentContentsVO.setAtchFilePath("");
			//---- ocu-hrd에서는 챕터걔념보다는 하나의컨텐츠에 하위의 요소형식들의 개념으로 하위까지 하나의 컨텐츠로 본다. 하위요소들은 상위속성들을 기준으로 하지만 하위컬럼들을 바꿔주진않는다. 고로 유지속성 진도처리체크와 진도체크방법은 유지해야된다.
			//parentContentsVO.setPrgrChkType("");
			//parentContentsVO.setPrgrChkYn("");
			//parentContentsVO.setCntsTypeCd("");
			parentContentsVO.setCritTm(0);
			parentContentsVO.setTotalPage(0);
			contentsMapper.updateContents(parentContentsVO);
		}
		if("CNTSROOT".equals(iContentsVO.getParUnitCd())) {
			//-- 최상위에 등록하는 경우는 parUnitCd를 비운다.
			iContentsVO.setParUnitCd("");
		}
		
		try {
			if(ValidationUtils.isEmpty(iContentsVO.getUnitCd())) {
				iContentsVO.setUnitCd(contentsMapper.selectUnitCd());
			}
			
			contentsMapper.insertContents(iContentsVO);
			
			resultVO.setReturnVO(iContentsVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	/**
	 * 교재 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> editContents(ContentsVO iContentsVO)  throws Exception {
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			int totalPage = contentsMapper.countChildContents(iContentsVO);
			iContentsVO.setTotalPage(totalPage);
			contentsMapper.updateContents(iContentsVO);
			
			resultVO.setReturnVO(iContentsVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 교제 전체 삭제
	 * @param sbjCd
	 * @return
	 */
	private ProcessResultVO<ContentsVO> deleteAllContents(String sbjCd)  throws Exception {
/*		ProcessResultListVO<ContentsVO> resultListVO = this.listContents(sbjCd, "");
		for(int i=0; i < resultListVO.getReturnList().size(); i++) {
			ContentsVO contentsVO = resultListVO.getReturnList().get(i);
			deleteContents(contentsVO);
		}*/
		return ProcessResultVO.success();
	}

	/**
	 * 교재 삭제
	 * @param
	 * @return
	 */
	@Override
	public ProcessResultVO<ContentsVO> deleteContents(ContentsVO iContentsVO)  throws Exception {

		//-- 교제 목차 삭제 (하위 목차 포함 삭제함)
		deleteContentsSub(iContentsVO.getSbjCd(), iContentsVO.getUnitCd(), iContentsVO.getParUnitCd());

		//-- 상위 목차자 같은 리스트 뽑기
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = null;
			if(iContentsVO.getParUnitCd() != null && iContentsVO.getParUnitCd() !="" ) {
				returnList = contentsMapper.parListContents(iContentsVO);				
			}else {
				returnList = contentsMapper.listContentsDel(iContentsVO);	
			}
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		List<ContentsVO> resultList = resultListVO.getReturnList();

//		int i = 0;
/*		for(ContentsVO contentsVO : resultList) {
			i++;
			contentsVO.setUnitOdr((i));
		}*/
		if(resultList.size() > 0) {
		contentsMapper.updateContentsBatch(resultList);
		}
		//-- 최상위 목록이 아닌경우 상위 목차의 단원 유형 변경
		if(!"".equals(StringUtil.nvl(iContentsVO.getParUnitCd()))) {
			ContentsVO parContentsVO = contentsMapper.selectContents(iContentsVO);
			if(parContentsVO.getSubCnt() > 0) {
				parContentsVO.setUnitType("C");
			} else {
				parContentsVO.setUnitType("L");
			}
			contentsMapper.updateContents(parContentsVO);
		}


		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}

	private void deleteContentsSub(String sbjCd, String unitCd, String parUnitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setParUnitCd(unitCd);
		ctsVO.setUnitCd(unitCd);
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = contentsMapper.listContentsDel(ctsVO);
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		List<ContentsVO> resultList = resultListVO.getReturnList();

		for(int i =0; i < resultList.size(); i++) {
			ContentsVO contentsVO = resultList.get(i);
			//--- 하위에 값이 있는 경우 삭제
//			deleteContentsSub(contentsVO.getSbjCd(), contentsVO.getUnitCd(), contentsVO.getParUnitCd());
			contentsMapper.deleteContents(contentsVO);
			//-- 하위 값이 있는 경우 하위 값 삭제
		}
		contentsMapper.deleteContents(ctsVO);
		//-- 하위 값이 있는 경우 하위 값 삭제
	}

	/**
	 * 교제 순서 변경
	 * @param
	 * @return
	 */
	@Override
	public ProcessResultVO<?> moveContents(ContentsVO iContentsVO)   throws Exception {

		//-- 상위 목차자 같은 리스트 뽑기
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(iContentsVO);
			}else {
				returnList = contentsMapper.listContents(iContentsVO);
			}
			
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		List<ContentsVO> resultList = resultListVO.getReturnList();

		int i = 0;
		for(ContentsVO contentsVO : resultList) {
			i++;
			if("up".equals(iContentsVO.getMoveType())) { //-- 순서를 위로 올리는 경우
				if(contentsVO.getUnitOdr() == (iContentsVO.getUnitOdr()-1)) { //--자신 보다 하나 빠른 순서를 자신의 순서로
					contentsVO.setUnitOdr(iContentsVO.getUnitOdr());
				} else if(contentsVO.getUnitOdr() == iContentsVO.getUnitOdr()) { //--자신의 순서는 하나 빠르게
					contentsVO.setUnitOdr((iContentsVO.getUnitOdr()-1));
				}
			} else { //-- 순서 다운인 경우
				if(contentsVO.getUnitOdr() == (iContentsVO.getUnitOdr()+1)) { //--자신 보다 하나 느린 순서를 자신의 순서로
					contentsVO.setUnitOdr(iContentsVO.getUnitOdr());
				} else if(contentsVO.getUnitOdr() == iContentsVO.getUnitOdr()) { //--자신의 순서는 하나 느리게
					contentsVO.setUnitOdr((iContentsVO.getUnitOdr()+1));
				}
			}
		}
		contentsMapper.updateContentsBatch(resultList);

		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}

	/**
	 * 교제 순서 변경
	 * @param
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortContents(ContentsVO iContentsVO)  throws Exception {

		//-- 기존 콘텐츠의 정보를 구한다.
		ContentsVO sVO = contentsMapper.selectContents(iContentsVO);
		String originParUnitCd = StringUtil.nvl(sVO.getParUnitCd(),"");
		String changeParUnitCd = iContentsVO.getParUnitCd();

		if(!originParUnitCd.equals(changeParUnitCd)) {
			//-- parUnitCd가 변경된 경우 parUnitCd를 변경하여 수정한다.
			sVO.setParUnitCd(changeParUnitCd);
			contentsMapper.updateContents(sVO);

			if(ValidationUtils.isNotEmpty(changeParUnitCd)) {
				//-- 변경된 parUnitCd의 정보를 가져온다.
				ContentsVO ctsVO = new ContentsVO();
				ctsVO.setSbjCd(iContentsVO.getSbjCd());
				ctsVO.setParUnitCd(changeParUnitCd);

				ContentsVO chgVO = contentsMapper.selectContents(ctsVO);
				if("L".equals(chgVO.getUnitType())) {
					// 기존의 unitType 이 lesson인 경우 Chapter로 변경한다.
					chgVO.setUnitType("C");
					chgVO.setCntsTypeCd("");
					chgVO.setUnitFilePath("");
					chgVO.setAtchFilePath("");
					chgVO.setPrgrChkType("");
					chgVO.setPrgrChkYn("");
					chgVO.setCritTm(0);
					chgVO.setTotalPage(0);
					contentsMapper.updateContents(chgVO);
				}
			}

			//-- 변경전 parUnitCd의 정보를 가져온다. 최상위 목차가 아니었을 경우만 작동.
			if(ValidationUtils.isNotEmpty(originParUnitCd)) {
				ContentsVO ctsVO = new ContentsVO();
				ctsVO.setSbjCd(iContentsVO.getSbjCd());
				ctsVO.setParUnitCd(originParUnitCd);
				ContentsVO orgVO = contentsMapper.selectContents(ctsVO);
				if(orgVO.getSubCnt() == 0) {
					orgVO.setUnitType("L");
					contentsMapper.updateContents(orgVO);
				}
			}
		}

		//-- 상위 목차자 같은 리스트 뽑기
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(iContentsVO);
			}else {
				returnList = contentsMapper.listContents(iContentsVO);
			}
			
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		List<ContentsVO> resultList = resultListVO.getReturnList();
		String[] unitCdArr = StringUtil.split(iContentsVO.getUnitCdStr(),"|");

		//-- 변경된 parUnitCd 하위의 순서 변경
		for(ContentsVO ssVO : resultList) {
			for(int i=0; i < unitCdArr.length; i++) {
				String orgUnitCd = ssVO.getUnitCd();
				String tarUnitCd = unitCdArr[i];
				if(tarUnitCd.equals(orgUnitCd)) {
					ssVO.setUnitOdr(i+1);
				}
			}
		}
		contentsMapper.updateContentsBatch(resultList);

		////-- parUnitCd가 변경된 경우 기존 parUnitCd의 하위 레코드의 순서를 변경한다.
		if(!originParUnitCd.equals(changeParUnitCd)) {
			//-- 상위 목차자 같은 리스트 뽑기
			ContentsVO ctsVO = new ContentsVO();
			ctsVO.setSbjCd(iContentsVO.getSbjCd());
			ctsVO.setParUnitCd(originParUnitCd);
		
			List<ContentsVO> oldList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				oldList = contentsMapper.listContentsVer5(ctsVO);
			}else {
				oldList = contentsMapper.listContents(ctsVO);
			}
			
			int order = 0;
			for(ContentsVO ssVO : resultList) {
				order++;
				ssVO.setUnitOdr(order);
			}
			contentsMapper.updateContentsBatch(oldList);
		}

		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}

	/**
	 * 다른 과목 교제 카피
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> copyOtherContents(ContentsVO sourceContentsVO, ContentsVO targetContentsVO)  throws Exception {

		//--- 소스 교재의 정보를 가져온다.
		sourceContentsVO = contentsMapper.selectContents(sourceContentsVO);
		listContentsTreeMake(sourceContentsVO.getSbjCd(), sourceContentsVO.getUnitCd(), sourceContentsVO);

		List<ContentsVO> contentsList = new ArrayList<ContentsVO>();

		//-- 소스 컨텐츠의 unitCd 및 parUnitCd를 신규 생성 및 arraylist 로 반환.
		//if("CNTSROOT".equals(targetContentsVO.getParUnitCd())) targetContentsVO.setParUnitCd(""); //-- 최상위에 콘텐츠 등록시 parUnitCd를 비운다.
		if("CNTSROOT".equals(targetContentsVO.getUnitCd())) targetContentsVO.setUnitCd(""); //-- 최상위에 콘텐츠 등록시 parUnitCd를 비운다.
		this.addOtherContents(sourceContentsVO, targetContentsVO.getSbjCd(), targetContentsVO.getUnitCd(), targetContentsVO.getUnitLvl(), contentsList);

		//-- 일괄등록 처리.
		int[] result = contentsMapper.insertContentsBatch(contentsList);

		//-- 반환할 콘텐츠 VO를 생성
		ContentsVO cVO = null;
		for(ContentsVO scVO : contentsList) {
			//-- 첫번째 콘텐츠를 반환한다.
			cVO = scVO;
			break;
		}
		if(cVO == null) cVO = new ContentsVO();

		//---- 상위 코드가 있는 교재 등록인 경우 상위 코드는 쳅터로 변경
		if(!"".equals(StringUtil.nvl(targetContentsVO.getUnitCd())) && !"CNTSROOT".equals(targetContentsVO.getParUnitCd())) {
			//---- 상위 코드 값 검색
			ContentsVO resultVO = contentsMapper.selectContents(targetContentsVO);
			resultVO.setUnitType("C");
			resultVO.setUnitFilePath("");
			resultVO.setPrgrChkType("");
			resultVO.setPrgrChkYn("");
			resultVO.setCritTm(0);
			resultVO.setTotalPage(0);
			contentsMapper.updateContents(resultVO);
		}

		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		resultVO.setResultSuccess();
		resultVO.setReturnVO(cVO);
		return resultVO;
	}


	private void addOtherContents(ContentsVO iContentsVO, String sbjCd, String parUnitCd, int targetUnitLvl, List<ContentsVO> contentsList)   throws Exception {
		String unitCd = contentsMapper.selectUnitCd();
		iContentsVO.setParUnitCd(parUnitCd);
		iContentsVO.setSbjCd(sbjCd);
		iContentsVO.setUnitCd(unitCd);
		iContentsVO.setUnitLvl(iContentsVO.getUnitLvl()+targetUnitLvl);

		contentsList.add(iContentsVO);

		List<ContentsVO> subList = iContentsVO.getSubList();
		if(!subList.isEmpty()) {
			//--- 하위 목록이 있는 경우
			for(int i=0; i < subList.size(); i++) {
				ContentsVO contentsVO = subList.get(i);
				addOtherContents(contentsVO, sbjCd, iContentsVO.getUnitCd(), targetUnitLvl, contentsList);
			}
		}
	}

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	public ProcessResultVO<ContentsVO> transactionRollbackMethod(ContentsVO iContentsVO) throws Exception {
		this.addContents(iContentsVO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}

	/**
	 * 폴더 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ContentsFileVO listDir(String sbjCd, String orgCd) throws Exception {

		String contentsDir =  Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + "/" + sbjCd;

		ContentsFileVO cfVO = new ContentsFileVO();
		cfVO.setFileName(sbjCd);
		cfVO.setFileType("R");

		dirList(contentsDir, cfVO);

		return cfVO;
	}

	/**
	 * 입력된 폴더 하위의 폴더 및 파일 목록 조회
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<ContentsFileVO> listFile(String filePath, String parentId, String orgCd) throws Exception {
		String contentsPath = Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + "/" +filePath;

		File file = new File(contentsPath);
		File[] list = file.listFiles();
		//-- 폴더 먼저 담고, 파일을 담자
		List<ContentsFileVO> fileList = new ArrayList<ContentsFileVO>();
		int fileNo = 0;
		if(list != null) {
			FileUtil.sort(list);
			if(StringUtil.isNotNull(parentId)) parentId = parentId+"_";
			for(File file2 : list) {
				if(file2.isDirectory()) {
					fileNo++;
					String linkDir = StringUtil.ReplaceAll(filePath, Constants.CONTENTS_STORAGE_PATH + "/" + orgCd , "");
					ContentsFileVO SysFileVO = new ContentsFileVO();
					SysFileVO.setFileId(parentId+Integer.toString(fileNo));
					SysFileVO.setFileName(file2.getName());
					SysFileVO.setFileDir(filePath +"/"+ file2.getName());
					SysFileVO.setLinkDir(linkDir);
					SysFileVO.setFileType("D");
					fileList.add(SysFileVO);
				}
			}
			for(File file2 : list) {
				if(file2.isFile()) {
					fileNo++;
					String linkDir = StringUtil.ReplaceAll(filePath, Constants.CONTENTS_STORAGE_PATH + "/" + orgCd , "");
					ContentsFileVO SysFileVO = new ContentsFileVO();
					SysFileVO.setFileId(parentId+Integer.toString(fileNo));
					SysFileVO.setFileName(file2.getName());
					SysFileVO.setFileDir(filePath +"/"+ file2.getName());
					SysFileVO.setLinkDir(linkDir);
					SysFileVO.setFileType("F");
					fileList.add(SysFileVO);
				}
			}
		}
		ProcessResultListVO<ContentsFileVO> resultVO = new ProcessResultListVO<ContentsFileVO>(fileList);
		return resultVO;
	}

	/**
	 * 폴더 목록 트리 형태로 반환
	 */
	private static void dirList(String filePath, ContentsFileVO cfVO) throws Exception {

		ContentsFileVO SysFileVO = null;
		File	 file		=	new File(filePath);
		//String[] list		=	file.list();
		File[] list = file.listFiles();

		FileUtil.sort(list);

		List<ContentsFileVO> subList = new ArrayList<ContentsFileVO>();

		String contentsDir =  Constants.CONTENTS_STORAGE_PATH;

		int dirRow = 0;
		//---- 디렉토리 담기
		//for (int i=0; i<list.length; i++) {
		//	File file2 = new File(filePath+"/"+list[i]);
		for(File file2 : list) {
			if(file2.isDirectory()) {
				String linkDir = StringUtil.ReplaceAll(filePath, contentsDir, "");
				SysFileVO = new ContentsFileVO();
				SysFileVO.setFileName(file2.getName());
				SysFileVO.setFileDir(filePath);
				SysFileVO.setLinkDir(linkDir);
				SysFileVO.setFileType("D");
				dirList(filePath+"/"+file2.getName(), SysFileVO);
				dirRow++;
				subList.add(SysFileVO);
			}
		}
		//---- 파일 담기
		//for (int i=0; i<list.length; i++) {
		//	File file2 = new File(filePath+"/"+list[i]);
		for(File file2 : list) {
			if(file2.isFile()) {
				String linkDir = StringUtil.ReplaceAll(filePath, contentsDir, "");
				SysFileVO = new ContentsFileVO();
				SysFileVO.setFileName(file2.getName());
				SysFileVO.setFileDir(filePath);
				SysFileVO.setLinkDir(linkDir);
				SysFileVO.setFileType("F");
				dirRow++;
				subList.add(SysFileVO);
			}
		}

		cfVO.setSubList(subList);
	}

	/**
	 * 파일 및 폴더 삭제
	 */
	@Override
	public ProcessResultVO<?> deleteFile(String sbjCd, String fileDir, String orgCd) throws Exception {

		//-- 파일 경로 수정 (풀페스 잡기 위함)
		if("/contents".startsWith(fileDir)) fileDir = StringUtil.substring(fileDir, 8, fileDir.length());
		//fileDir = StringUtil.ReplaceAll(fileDir, "/contents", "");

		//-- 파일 풀페스
		String contentsDir =  Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + fileDir;

		File file = new File(contentsDir);
		if(file.isDirectory()) {
			FileUtil.delDirectory(contentsDir);
		} else if(file.isFile()) {
			FileUtil.delFile(contentsDir);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 파일 압축 풀기
	 */
	@Override
	public ProcessResultVO<?> unzipFile(String sbjCd, String fileDir, String fileName, String orgCd) throws Exception {

		//-- 파일 경로 수정 (풀페스 잡기 위함)
		if(fileDir.startsWith("/contents"))
			fileDir = fileDir.substring(9);

		//-- 파일 풀페스
		String contentsDir =  Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + fileDir;

		//2016.01.14 zip파일명으로 폴더를 생성하고 압축해제되도록 수정
		String zipFileFolderName = "";
		if(ValidationUtils.isNotEmpty(fileName)){
			zipFileFolderName = fileName.substring(0,fileName.lastIndexOf("."));
		}

		String sourcePath = contentsDir + File.separator+ fileName;
		//String upZipFolderName = fileName.substring(0,fileName.indexOf("."));
		String targetPath = contentsDir + File.separator +zipFileFolderName+File.separator;

		if(FileUtil.unZipFile(sourcePath, targetPath)) {
			return ProcessResultVO.success();
		} else {
			return ProcessResultVO.failed();
		}
	}

	/**
	 * 붙여넣기
	 */
	@Override
	public ProcessResultVO<?> pasteFile(String sbjCd, String tarFileDir,
			String cutFileDir, String cutFileName, String cutType, String orgCd) throws Exception {

		tarFileDir = StringUtil.ReplaceAll(tarFileDir, "/contents", "");
		cutFileDir = StringUtil.ReplaceAll(cutFileDir, "/contents", "");

		String source = Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + cutFileDir + "/" + cutFileName;
		String target = Constants.CONTENTS_STORAGE_PATH + "/" + orgCd + tarFileDir + "/" + cutFileName;

		File sourceFile = new File(source);
		File targetFile = new File(target);

		ProcessResultVO<ContentsVO> returnVO = new ProcessResultVO<ContentsVO>();
		if(targetFile.exists()) {
			int[] retrunCnt = {-1};
			returnVO.setRetrunCnt(retrunCnt);
			return returnVO;
		} else {
			if("cut".equals(cutType)) {
				if(!sourceFile.renameTo(targetFile)) return ProcessResultVO.failed();
			} else {
				if(!FileUtil.fileCopy(source, target)) return ProcessResultVO.failed();
			}
		}
		return ProcessResultVO.success();
	}

	/**
	 * 파일, 폴더명 변경
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsFileVO> renameFile(ContentsFileVO iContentsFileVO)  throws Exception {

		String fileDir = StringUtil.ReplaceAll(iContentsFileVO.getFileDir(), "/contents", "");
		String fullPath = Constants.CONTENTS_STORAGE_PATH  + "/" + iContentsFileVO.getOrgCd() + fileDir;


		File sourceFile = new File(fullPath+"/"+iContentsFileVO.getLinkDir());
		File targetFile = null;
		//2014.11.17 파일과 폴더의 이름바꾸기 기준이 틀려 구분함.
		if(iContentsFileVO.getGubun().equals("FOLDER")){
			targetFile = new File(Constants.CONTENTS_STORAGE_PATH + "/" +  iContentsFileVO.getOrgCd() +iContentsFileVO.getFileName());
		}else{
			targetFile = new File(fullPath+"/"+iContentsFileVO.getFileName());
		}

		if(!targetFile.exists()) {
			if(!sourceFile.renameTo(targetFile)) return ProcessResultVO.failed();
		} else {
			return ProcessResultVO.failed();
		}

		return ProcessResultVO.success();
	}

	/**
	 * 폴더 생성
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsFileVO> addDir(ContentsFileVO iContentsFileVO)  throws Exception {

		String fileDir = StringUtil.ReplaceAll(iContentsFileVO.getFileDir(), "/contents", "");
		String fullPath = Constants.CONTENTS_STORAGE_PATH + "/" +  iContentsFileVO.getOrgCd() + fileDir;

		String newDirPath = fullPath + "/"+iContentsFileVO.getFileName();

		File newFileDir = new File(newDirPath);

		if(!newFileDir.exists()) {
			if(!FileUtil.setDirectory(newDirPath)) return ProcessResultVO.failed();
		} else {
			return ProcessResultVO.failed();
		}
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 목록 조회 (하위 컨텐츠가 있는 목록만)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listContentsSort(ContentsVO vo) throws Exception {
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listSortVer5(vo);
			}else {
				returnList = contentsMapper.listSort(vo);
			}
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listContentsSub(ContentsVO vo) throws Exception {
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = contentsMapper.listSub(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 과목 분류 정렬 순서 변경
	 * @param iSubjectCategoryVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> contentsSort(ContentsVO vo) throws Exception {

		String[] contentsList = StringUtil.split(vo.getUnitCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<ContentsVO> contentsArray = new ArrayList<ContentsVO>();
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			contentsArray = contentsMapper.listContentsVer5(vo);
		}else {
			contentsArray = contentsMapper.listContents(vo);
		}
		
		// 이중 포문으로 categoryArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (ContentsVO contentsVO : contentsArray) {
			for (int order = 0; order < contentsList.length; order++) {
				if(contentsVO.getUnitCd().equals(contentsList[order])) {
					contentsVO.setUnitOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		contentsMapper.updateBatch(contentsArray);

		return ProcessResultVO.success();
	}
	
	@Override
	public List<ContentsVO> listAttendContents(ContentsVO vo) throws Exception {
		return contentsMapper.listAttendContents(vo);
	}
	
	/**
	 * 회차별 교재 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listCreateContents(ContentsVO vo)  throws Exception {
		
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = contentsMapper.listContentsVer5(vo);
			}else {
				returnList = contentsMapper.listCreateContents(vo);
			}
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}
	/**
	 * 회차별 교재 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> editCreateContents(ContentsVO iContentsVO)  throws Exception {
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			int totalPage = contentsMapper.countChildContents(iContentsVO);
			iContentsVO.setTotalPage(totalPage);
			contentsMapper.updateCreateContents(iContentsVO);
			
			resultVO.setReturnVO(iContentsVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 회차별 교재 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> viewCreateContents(String crsCreCd, String sbjCd, String unitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setCrsCreCd(crsCreCd);
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setUnitCd(unitCd);

		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		try {
			resultVO.setReturnVO(contentsMapper.selectCreateContents(ctsVO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}
	
	
	@Override
	public void deleteAllCreateContents(ContentsVO vo) throws Exception {
		contentsMapper.deleteAllCreateContents(vo);
		
	}
	
	/**
	 * 회차 교재 삭제
	 * @param
	 * @return
	 */
	@Override
	public ProcessResultVO<ContentsVO> deleteCreateContents(ContentsVO iContentsVO)  throws Exception {

		//-- 교제 목차 삭제 (하위 목차 포함 삭제함)
		deleteCreateContentsSub(iContentsVO.getCrsCreCd(), iContentsVO.getSbjCd(), iContentsVO.getUnitCd(), iContentsVO.getParUnitCd());

		//-- 상위 목차자 같은 리스트 뽑기
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = null;
			if(iContentsVO.getParUnitCd() != null && iContentsVO.getParUnitCd() !="" ) {
				returnList = contentsMapper.parListCreateContents(iContentsVO);				
			}else {
				returnList = contentsMapper.listCreateContentsDel(iContentsVO);	
			}
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		List<ContentsVO> resultList = resultListVO.getReturnList();

//		int i = 0;
/*		for(ContentsVO contentsVO : resultList) {
			i++;
			contentsVO.setUnitOdr((i));
		}*/
		if(resultList.size() > 0) {
		contentsMapper.updateContentsBatch(resultList);
		}
		//-- 최상위 목록이 아닌경우 상위 목차의 단원 유형 변경
		if(!"".equals(StringUtil.nvl(iContentsVO.getParUnitCd()))) {
			ContentsVO parContentsVO = contentsMapper.selectCreateContents(iContentsVO);
			if(parContentsVO.getSubCnt() > 0) {
				parContentsVO.setUnitType("C");
			} else {
				parContentsVO.setUnitType("L");
			}
			contentsMapper.updateCreateContents(parContentsVO);
		}


		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}
	
	private void deleteCreateContentsSub(String crsCreCd, String sbjCd, String unitCd, String parUnitCd)  throws Exception {
		ContentsVO ctsVO = new ContentsVO();
		ctsVO.setCrsCreCd(crsCreCd);
		ctsVO.setSbjCd(sbjCd);
		ctsVO.setParUnitCd(unitCd);
		ctsVO.setUnitCd(unitCd);
		
		ProcessResultListVO<ContentsVO> resultListVO = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = contentsMapper.listCreateContentsDel(ctsVO);
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		List<ContentsVO> resultList = resultListVO.getReturnList();

		for(int i =0; i < resultList.size(); i++) {
			ContentsVO contentsVO = resultList.get(i);
			//--- 하위에 값이 있는 경우 삭제
//			deleteContentsSub(contentsVO.getSbjCd(), contentsVO.getUnitCd(), contentsVO.getParUnitCd());
			contentsMapper.deleteCreateContents(contentsVO);
			//-- 하위 값이 있는 경우 하위 값 삭제
		}
		contentsMapper.deleteCreateContents(ctsVO);
		//-- 하위 값이 있는 경우 하위 값 삭제
	}
	
	/**
	 * 회차 단일 목차 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ContentsVO> addCreateContents(ContentsVO iContentsVO)  throws Exception {
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();

		//---- 상위 코드가 있는 교재 등록인 경우 상위 코드는 쳅터로 변경
		if(!"".equals(StringUtil.nvl(iContentsVO.getParUnitCd())) && !"CNTSROOT".equals(iContentsVO.getParUnitCd())) {
			//---- 상위 코드 값 검색
			resultVO.setReturnVO(contentsMapper.selectCreateContents(iContentsVO));
			String prgrChkType = resultVO.getReturnVO().getPrgrChkType();
			iContentsVO.setPrgrChkType(prgrChkType);
			ContentsVO parentContentsVO = resultVO.getReturnVO();
			parentContentsVO.setUnitType("C");
			parentContentsVO.setUnitFilePath("");
			parentContentsVO.setAtchFilePath("");
			//---- ocu-hrd에서는 챕터걔념보다는 하나의컨텐츠에 하위의 요소형식들의 개념으로 하위까지 하나의 컨텐츠로 본다. 하위요소들은 상위속성들을 기준으로 하지만 하위컬럼들을 바꿔주진않는다. 고로 유지속성 진도처리체크와 진도체크방법은 유지해야된다.
			//parentContentsVO.setPrgrChkType("");
			//parentContentsVO.setPrgrChkYn("");
			//parentContentsVO.setCntsTypeCd("");
			parentContentsVO.setCritTm(0);
			parentContentsVO.setTotalPage(0);
			contentsMapper.updateCreateContents(parentContentsVO);
		}
		if("CNTSROOT".equals(iContentsVO.getParUnitCd())) {
			//-- 최상위에 등록하는 경우는 parUnitCd를 비운다.
			iContentsVO.setParUnitCd("");
		}
		
		try {
			if(ValidationUtils.isEmpty(iContentsVO.getUnitCd())) {
				iContentsVO.setUnitCd(contentsMapper.selectUnitCd());
			}
			
			contentsMapper.insertCreateContents(iContentsVO);
			
			resultVO.setReturnVO(iContentsVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}
	
	/**
	 * 회차 콘텐츠 목록 조회 (하위 컨텐츠가 있는 목록만)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listCreateContentsSort(ContentsVO vo) throws Exception {
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = new ArrayList<ContentsVO>();
			returnList = contentsMapper.listCreCntSort(vo);
			
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 회차 하위 컨텐츠 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ContentsVO> listCreateContentsSub(ContentsVO vo) throws Exception {
		ProcessResultListVO<ContentsVO> resultList = new ProcessResultListVO<ContentsVO>();
		try {
			List<ContentsVO> returnList = contentsMapper.listCreCntSub(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 회차 컨텐츠 정렬순서 변경
	 * @param iSubjectCategoryVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> CreateContentsSort(ContentsVO vo) throws Exception {

		String[] contentsList = StringUtil.split(vo.getUnitCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<ContentsVO> contentsArray = new ArrayList<ContentsVO>();
		contentsArray = contentsMapper.listCreateContents(vo);
		
		// 이중 포문으로 categoryArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (ContentsVO contentsVO : contentsArray) {
			for (int order = 0; order < contentsList.length; order++) {
				if(contentsVO.getUnitCd().equals(contentsList[order])) {
					contentsVO.setUnitOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		contentsMapper.updateCreBatch(contentsArray);

		return ProcessResultVO.success();
	}
}
