package egovframework.edutrack.comm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>공통 - 공통 코드 메모리 서비스</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysCodeMemService")
public class SysCodeMemServiceImpl extends EgovAbstractServiceImpl implements SysCodeMemService {

	/**
	 * 메뉴값을 저장하는 내부 변수 [캐쉬 저장소]
	 * key : codeCtgrCd
	 * value : 해당되는 codeDTO
	 */
	private final HashMap<String, List<SysCodeVO>> codeCache = new HashMap<String, List<SysCodeVO>>();

	/**
	 * 캐쉬저장소의 유효성을 판단하는 버젼값
	 */
	private int codeVersion = -1;
	private String codeVerDate = "19000101000001";
	
	@Resource(name="sysCodeService")
	private SysCodeService 		sysCodeService;

	/**
	 * 시스템 코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	@Override
	public synchronized List<SysCodeVO> getSysCodeList(String codeCtgrCd) throws Exception {
		// 변경이 감지되면 캐쉬를 비운다.

		if(DateTimeUtil.getIntervalSecond(this.codeVerDate) > 360) {
			//-- 케시 검사한지 1시간이 지난 경우, 코드 검사 시간이 1시간이 지나지 않은 경우는 코드 버전을 체크 하지 않는다.
			if(isCodeChanged()) {
				this.codeCache.clear();
				this.codeVersion = sysCodeService.selectCodeVersion();
				//log.debug("[ 코드 변경내용 감지.. 캐쉬를 초기화 합니다. ]");
			}
			this.codeVerDate = DateTimeUtil.getCurrentString(); //-- 현재의 시간을 셋팅함.
		}

		// 메모리에 로드되어 있지 않으면 DB에서 로딩..
		if(!this.codeCache.containsKey(codeCtgrCd)) {
			this.codeCache.put(codeCtgrCd, sysCodeService.listCode(codeCtgrCd, true).getReturnList());
			//log.debug("캐쉬 적중 실패 DB에서 직접 CODE를 조회합니다. codeCtgrCd [" + codeCtgrCd + "]");
		} else {
			//log.debug("캐쉬 적중 성공 메모리에서 CODE를 불러옵니다. codeCtgrCd [" + codeCtgrCd + "]");
		}
		return this.codeCache.get(codeCtgrCd);		
	}
	/**
	 * 시스템 코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	@Override
	public synchronized List<SysCodeVO> getSysCodeList(String codeCtgrCd, String locale) throws Exception {
		List<SysCodeVO> resultList = null;
		resultList = getSysCodeList(codeCtgrCd);
		for(SysCodeVO scvo : resultList) {
        	for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
        		if(locale.equals(sclvo.getLangCd())) scvo.setCodeNm(sclvo.getCodeNm());
        	}
        }
		
		return resultList;
	}
	
	/**
	 * 메뉴의 버젼값을 DB와 비교한다.
	 * @return true:변경됨, false:변경되지 않음
	 * @throws Exception  
	 */
	private boolean isCodeChanged() throws Exception {
		return (this.codeVersion != sysCodeService.selectCodeVersion()) ? true : false;
	}
	
	/**
	 * 메모리에서 시스템 코드 정보를 검색하여 반환한다.
	 *
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public SysCodeVO getCode(String codeCtgrCd, String codeCd) throws Exception {
		List<SysCodeVO> codeList = getSysCodeList(codeCtgrCd);
		SysCodeVO returnVO = null;
		for (SysCodeVO codeVO :  codeList) {
			if ((codeVO.getCodeCd()).equals(codeCd)) {
				returnVO = codeVO;
				break;
			}
		}
		return returnVO;
	}	
	/**
	 * 시스템 코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	@Override
	public synchronized SysCodeVO getCode(String codeCtgrCd, String codeCd, String locale) throws Exception {
		SysCodeVO sysCodeVO = getCode(codeCtgrCd, codeCd);
		for(SysCodeLangVO sclvo : sysCodeVO.getCodeLangList()) {
       		if(locale.equals(sclvo.getLangCd())) sysCodeVO.setCodeNm(sclvo.getCodeNm());
        }
		
		return sysCodeVO;
	}

}
