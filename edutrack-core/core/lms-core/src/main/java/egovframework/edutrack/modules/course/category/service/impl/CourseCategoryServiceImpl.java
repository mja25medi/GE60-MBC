package egovframework.edutrack.modules.course.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.board.bbs.service.impl.BrdBbsInfoMapper;
import egovframework.edutrack.modules.course.category.service.CourseCategoryService;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



@Service("courseCategoryService")
public class CourseCategoryServiceImpl 
	extends EgovAbstractServiceImpl implements CourseCategoryService {

	/** Mapper */
	@Resource(name="courseCategoryMapper")
	private CourseCategoryMapper 		courseCategoryMapper;
	
	/**
	 * 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CourseCategoryVO> listCategory(String orgCd, String parCrsCtgrCd)  throws Exception {
		CourseCategoryVO ctgrVO = new CourseCategoryVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setParCrsCtgrCd(parCrsCtgrCd);
		ctgrVO.setUseYn("");
		ProcessResultListVO<CourseCategoryVO> resultList = new ProcessResultListVO<CourseCategoryVO>();
		try {
			List<CourseCategoryVO> returnList = new ArrayList<CourseCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = courseCategoryMapper.listCategoryVer5(ctgrVO);
			}else {
				// 과정분류 관리일 경우 전체 목록조회
				ctgrVO.setUseYn("ALL");
				returnList = courseCategoryMapper.listCategory(ctgrVO);
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
	 * 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CourseCategoryVO> listCategory(String orgCd, String parCrsCtgrCd, String useYn)  throws Exception {
		CourseCategoryVO ctgrVO = new CourseCategoryVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setParCrsCtgrCd(parCrsCtgrCd);
		ctgrVO.setUseYn(useYn);
		ProcessResultListVO<CourseCategoryVO> resultList = new ProcessResultListVO<CourseCategoryVO>();
		try {
			List<CourseCategoryVO> returnList = new ArrayList<CourseCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = courseCategoryMapper.listCategoryVer5(ctgrVO);
			}else {
				returnList = courseCategoryMapper.listCategory(ctgrVO);
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
	 * 과정 분류 목록 조회 (하위 과정 분류 있는 목록만)
	 * 
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CourseCategoryVO> listCategorySort(String orgCd, String parCrsCtgrCd)  throws Exception {
		CourseCategoryVO ctgrVO = new CourseCategoryVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setParCrsCtgrCd(parCrsCtgrCd);
		ProcessResultListVO<CourseCategoryVO> resultList = new ProcessResultListVO<CourseCategoryVO>();
		try {
			if(("").equals(ctgrVO.getParCrsCtgrCd())) {
				ctgrVO.setParCrsCtgrCd(null);
			}
			
			List<CourseCategoryVO> returnList = new ArrayList<CourseCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = courseCategoryMapper.listCategoryVer5(ctgrVO);
			}else {
				returnList = courseCategoryMapper.listCategorySort(ctgrVO);
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
	 * 하위 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CourseCategoryVO> listCategorySub(String orgCd, String parCrsCtgrCd) throws Exception {
		CourseCategoryVO ctgrVO = new CourseCategoryVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setParCrsCtgrCd(parCrsCtgrCd);
		ProcessResultListVO<CourseCategoryVO> resultList = new ProcessResultListVO<CourseCategoryVO>();
		try {
			List<CourseCategoryVO> returnList = new ArrayList<CourseCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = courseCategoryMapper.listCategoryVer5(ctgrVO);
			}else {
				returnList = courseCategoryMapper.listCategorySub(ctgrVO);
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
	 * 과정 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CourseCategoryVO> viewCategory(String crsCtgrCd) throws Exception {
		ProcessResultVO<CourseCategoryVO> resultVO = new ProcessResultVO<CourseCategoryVO>();
		try {
			resultVO.setReturnVO(courseCategoryMapper.selectCategory(crsCtgrCd));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		return resultVO;
	}
	
	/**
	 * 과정 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CourseCategoryVO> addCategory(CourseCategoryVO iCourseCategoryVO) throws Exception {
		ProcessResultVO<CourseCategoryVO> resultVO = new ProcessResultVO<CourseCategoryVO>();
		//---- 과정 분류 코드 생성
		CourseCategoryVO courseCategoryVO = new CourseCategoryVO();
		courseCategoryVO.setCrsCtgrCd(courseCategoryMapper.selectCategoryCd());
		//---- 신규 메뉴코드 세팅
		iCourseCategoryVO.setCrsCtgrCd(courseCategoryVO.getCrsCtgrCd());
		//---- 메뉴 레벨 : 상위 메뉴 레벨 + 1
		iCourseCategoryVO.setCrsCtgrLvl(iCourseCategoryVO.getParCrsCtgrLvl()+1);
		//---- 메뉴 경로 : 상위 메뉴 경로 + 현제 메뉴 코드
		iCourseCategoryVO.setCrsCtgrPath(iCourseCategoryVO.getParCrsCtgrPath()+"/"+courseCategoryVO.getCrsCtgrCd());
		
		if(("").equals(iCourseCategoryVO.getParCrsCtgrCd())) {
			iCourseCategoryVO.setParCrsCtgrCd(null);
		}
		String crsCtgrPath = "";
		String crsCtgrPathNm = "";
		try {
			CourseCategoryVO VO = courseCategoryMapper.selectCategory(iCourseCategoryVO.getParCrsCtgrCd());
			crsCtgrPath = VO.getCrsCtgrPath();
			crsCtgrPathNm = VO.getCrsCtgrPathNm();
		} catch (Exception e) {	}
		iCourseCategoryVO.setCrsCtgrPath(crsCtgrPath+"/"+iCourseCategoryVO.getCrsCtgrCd());
		iCourseCategoryVO.setCrsCtgrPathNm(crsCtgrPathNm+"/"+iCourseCategoryVO.getCrsCtgrNm());
		try {
			courseCategoryMapper.insertCategory(iCourseCategoryVO);
			resultVO.setReturnVO(iCourseCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}	
		
		
		return resultVO;
	}
	
	/**
	 * 과정 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CourseCategoryVO> editCategory(CourseCategoryVO iCourseCategoryVO) throws Exception {
		ProcessResultVO<CourseCategoryVO> resultVO = new ProcessResultVO<CourseCategoryVO>();
		try {
			if(("").equals(iCourseCategoryVO.getParCrsCtgrCd())) {
				iCourseCategoryVO.setParCrsCtgrCd(null);
			}
			courseCategoryMapper.updateCategory(iCourseCategoryVO);
			resultVO.setReturnVO(iCourseCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}	

		return resultVO;
	}
	
	/**
	 * 과정 분류 삭제
	 * @param crsCtgrCd 삭제 대상 코드값
	 * @return
	 */
	@Override
	public ProcessResultVO<?> deleteCategory(String crsCtgrCd) throws Exception {
		courseCategoryMapper.deleteCategory(crsCtgrCd);
		
		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}
	
	/**
	 * 과정 분류 정렬 순서 변경
	 * @param iCourseCategoryVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortCategory(CourseCategoryVO iCourseCategoryVO) throws Exception {
		
		String[] categoryList = StringUtil.split(iCourseCategoryVO.getCrsCtgrCd(),"|");
		
		CourseCategoryVO ctgrVO = new CourseCategoryVO();
		ctgrVO.setOrgCd(iCourseCategoryVO.getOrgCd());
		ctgrVO.setParCrsCtgrCd("");
		ctgrVO.setUseYn("");
		
		// 하위 코드 목록을 한꺼번에 조회
		List<CourseCategoryVO> categoryArray = new ArrayList<CourseCategoryVO>();
		
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			categoryArray = courseCategoryMapper.listCategoryVer5(ctgrVO);
		}else {
			categoryArray = courseCategoryMapper.listCategory(ctgrVO);
		}
		
		// 이중 포문으로 categoryArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (CourseCategoryVO courseCategoryVO : categoryArray) {
			for (int order = 0; order < categoryList.length; order++) {
				if(courseCategoryVO.getCrsCtgrCd().equals(categoryList[order])) {
					courseCategoryVO.setCrsCtgrOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		
		// 변경된 시스템코드 어래이를 일괄 저장.
		
		courseCategoryMapper.updateCategoryBatch(categoryArray);
		
		
		return ProcessResultVO.success();
	}
	
	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	public ProcessResultVO<CourseCategoryVO> transactionRollbackMethod(CourseCategoryVO iCourseCategoryVO) throws Exception {
		this.addCategory(iCourseCategoryVO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}
}
