package egovframework.edutrack.modules.org.config.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>교육기관 - 교육기관 사용자 정보 설정 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgUserInfoCfgService")
public class OrgUserInfoCfgServiceImpl 
	extends EgovAbstractServiceImpl implements OrgUserInfoCfgService {

/*    @Resource(name="orgUserInfoCfgDAO")
    private OrgUserInfoCfgMapper orgUserInfoCfgMapper;*/
	
	/** Mapper */
	@Resource(name="orgUserInfoCfgMapper")
	private OrgUserInfoCfgMapper 		orgUserInfoCfgMapper;
    
    /**
 	 * 교육기관 설정 값 전체 목록을 조회한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<OrgUserInfoCfgVO> list(OrgUserInfoCfgVO vo) throws Exception {
 		ProcessResultListVO<OrgUserInfoCfgVO> resultList = new ProcessResultListVO<OrgUserInfoCfgVO>(); 
 		List<OrgUserInfoCfgVO> cfgCtgrList =  orgUserInfoCfgMapper.list(vo);
 		resultList.setResult(1);
 		resultList.setReturnList(cfgCtgrList);
 		return resultList;
 	}
 	
 	/**
 	 * 교육기관 설정 값 상세 정보를 조회한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return OrgUserInfoCfgVO
 	 * @throws Exception
 	 */
	@Override
	public OrgUserInfoCfgVO view(OrgUserInfoCfgVO vo) throws Exception {
 		return orgUserInfoCfgMapper.select(vo);
 	}
 	
 	/**
 	 * 교육기관 설정 값 정보를 등록한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return String
 	 * @throws Exception
 	 */
	@Override
	public int add(OrgUserInfoCfgVO vo) throws Exception {
 		return orgUserInfoCfgMapper.insert(vo);
 	}	
 	
 	/**
 	 * 교육기관 설정 값 정보를 수정한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int edit(OrgUserInfoCfgVO vo) throws Exception {
 		return orgUserInfoCfgMapper.update(vo);
 	}

 	/**
 	 * 교육기관 설정 값의 순서를 변경한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int sort(OrgUserInfoCfgVO vo) throws Exception {

		String[] itemList = StringUtil.split(vo.getFieldNms().toString(),"|");
		
		// 하위 코드 목록을 한꺼번에 조회
		List<OrgUserInfoCfgVO> itemArray = orgUserInfoCfgMapper.list(vo);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for(OrgUserInfoCfgVO svo : itemArray){
			for(int order = 0; order < itemList.length; order++){
				if(svo.getFieldNm().equals(itemList[order])  ){
					svo.setViewOdr(order+1);
					orgUserInfoCfgMapper.update(svo);
				}
			}
		}
		return 1;
 	}
	
 	/**
 	 * 교육기관 설정 값 정보를 삭제 한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int remove(OrgUserInfoCfgVO vo) throws Exception {
 		return orgUserInfoCfgMapper.delete(vo);
 	}
	
 	/**
 	 * 교육기관 설정 값 정보를 삭제 한다.
 	 * @param OrgUserInfoCfgVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int removeAll(OrgUserInfoCfgVO vo) throws Exception {
 		return orgUserInfoCfgMapper.deleteAll(vo);
 	}
	
	@Override
	public ProcessResultListVO<OrgUserInfoCfgVO> listForJoin(OrgUserInfoCfgVO vo) throws Exception {
 		ProcessResultListVO<OrgUserInfoCfgVO> resultList = new ProcessResultListVO<OrgUserInfoCfgVO>(); 
 		List<OrgUserInfoCfgVO> cfgCtgrList =  orgUserInfoCfgMapper.listForJoin(vo);
 		resultList.setResult(1);
 		resultList.setReturnList(cfgCtgrList);
 		return resultList;
 	}
}
