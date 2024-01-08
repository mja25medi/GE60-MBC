package egovframework.edutrack.modules.user.info.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo;
import egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo.Type;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.code.service.OrgCodeLangVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.config.service.impl.OrgUserInfoCfgMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.dept.service.impl.UsrDeptInfoMapper;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoChgHstyVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *  <b>사용자 - 사용자의 회원 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("usrUserInfoService")
public class UsrUserInfoServiceImpl 
	extends EgovAbstractServiceImpl implements UsrUserInfoService {

	/** Mapper */
    @Resource(name="usrUserInfoMapper")
    private UsrUserInfoMapper 			usrUserInfoMapper;
    
    @Resource(name="usrLoginMapper")
    private UsrLoginMapper 			usrLoginMapper;

    @Resource(name="usrUserAuthGrpMapper")
    private UsrUserAuthGrpMapper 		usrUserAuthGrpMapper;
    
    @Resource(name="orgUserInfoCfgMapper")
    private OrgUserInfoCfgMapper 		orgUserInfoCfgMapper;
    
    @Resource(name="usrUserInfoChgHstyMapper")
    private UsrUserInfoChgHstyMapper 	usrUserInfoChgHstyMapper;

    @Resource(name="usrDeptInfoMapper")
    private UsrDeptInfoMapper			usrDeptInfoMapper;
    
    @Resource(name="sysCodeService")
    private SysCodeService 			sysCodeService;
    
    @Resource(name="orgCodeService")
    private OrgCodeService 			orgCodeService;

    @Resource(name="sysFileService")
    private SysFileService 			sysFileService;
    
    @Resource(name="usrUserInfoService")
    private UsrUserInfoService		usrUserInfoService;
    
	@Resource(name="studentMapper")
	private StudentMapper 		studentMapper;
    
	private final class NestedFileHandler 
		implements FileHandler<UsrUserInfoVO> {
		
		@Override
		public String getPK(UsrUserInfoVO vo){
			return vo.getPhotoFileSn().toString();
		}

		@Override
		public String getRepoCd() {
			return "USR_PHOTO";
		}

		@Override
		public List<SysFileVO> getFiles(UsrUserInfoVO vo){
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getPhotoFileSn()))
				fileList.add(vo.getPhotoFile());
			return fileList;
		}

		@Override
		public UsrUserInfoVO setFiles(UsrUserInfoVO vo, FileListVO fileListVO) {
			vo.setPhotoFile(fileListVO.getFile("usrPhoto"));
			return vo;
		}
	}
	
    /**
	 * 사용자 정보 전체 목록 조회
	 * @param UsrUserInfoVO vo
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> list(UsrUserInfoVO vo) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
			List<UsrUserInfoVO> userList = usrUserInfoMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(userList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	
	/**
	 * 사용자 상담 정보 목록 조회
	 * @param UsrUserInfoVO vo
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listConsultPaging(UsrUserInfoVO vo) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		
		try {
			/* start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = usrUserInfoMapper.countConsult(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UsrUserInfoVO> userList = usrUserInfoMapper.listConsultPaging(vo);
			resultList.setResult(1);
			resultList.setReturnList(userList);
			resultList.setPageInfo(paginationInfo);
			} catch (Exception e){
				e.printStackTrace();
				resultList.setResult(-1);
			}		
			return resultList;
		
	}

    /**
	 * 사용자 정보 페이징 목록 조회
	 * @param UsrUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listPageing(UsrUserInfoVO vo) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
		/* start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = usrUserInfoMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<UsrUserInfoVO> userList = usrUserInfoMapper.listPageing(vo);
		userList.forEach(UsrUserInfoVO::decryptJuminNo);
		resultList.setResult(1);
		resultList.setReturnList(userList);
		resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	

	/**
	 * 사용자 정보 정보 조회
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO view(UsrUserInfoVO vo) throws Exception {
		String orgCd = vo.getOrgCd();
		
		vo = usrUserInfoMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		
		if(orgCd == null || "".equals(orgCd)){
			orgCd = vo.getOrgCd();
		}
		
		this.getAuthGrp(vo);
		vo.decryptJuminNo();
		return vo;
	}

	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO viewForLogin(UsrUserInfoVO vo) throws Exception {
		//SNS로그인
		if(ValidationUtils.isNotEmpty(vo.getSnsKey()) && ValidationUtils.isNotEmpty(vo.getSnsDiv())){
			vo = usrUserInfoMapper.selectForLogin(vo);
			//sns 사용자는 패스워드가 없으므로 로직을 통과하기위해 강제로 같게 만듬
			//vo.setUserPass("0");
			//vo.setEncUserPass("0");
		}else{
			String encUserPass = 
					//KISASeed.seed256HashEncryption(vo.getUserPass());
					usrLoginMapper.getEncryptPassword(vo.getUserPass());
			UsrUserInfoVO userVO = usrUserInfoMapper.selectForLogin(vo);
			if(ValidationUtils.isNotEmpty(userVO)) {
					vo = userVO;
			}
			vo.setEncUserPass(encUserPass);			
		}
		this.getAuthGrp(vo);
		return vo;
	}
	
	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO viewForSsoLogin(UsrUserInfoVO vo) throws Exception {
		//SNS로그인
		if(ValidationUtils.isNotEmpty(vo.getSnsKey()) && ValidationUtils.isNotEmpty(vo.getSnsDiv())){
			vo.setUserPass("");
			vo = usrUserInfoMapper.selectForSsoLogin(vo);
			//sns 사용자는 패스워드가 없으므로 로직을 통과하기위해 강제로 같게 만듬
			vo.setUserPass("0");
			vo.setEncUserPass("0");
		}else{
//			String encUserPass = 
//					KISASeed.seed256HashEncryption(vo.getUserPass());
//					vo.getUserPass();
			vo = usrUserInfoMapper.selectForSsoLogin(vo);
			vo.setEncUserPass(vo.getUserPass());
		}
		this.getAuthGrp(vo);
		return vo;
	}

	/**
	 * 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.CREATE)
	public int add(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		//-- 사용자 키를 생성하여 셋팅한다.
		String userNo = usrUserInfoMapper.selectKey();
		vo.setUserNo(userNo);
		
		vo.encryptJuminNo();
		
		//-- 사용자 정보를 저장한다.
		//vo.setOrgCd(null);//????시스템관리자 등록 용으로는 맞는데 사이트 회원은 orgCd가 필요함.
		int result = usrUserInfoMapper.insert(vo);
		
		//-- 회원가입인 경우 RegNo와 ModNo가 비어 있음, 이경우 등록된 사용자 아이디로 교체....
		if(ValidationUtils.isEmpty(vo.getRegNo())) vo.setRegNo(vo.getUserNo());
		if(ValidationUtils.isEmpty(vo.getModNo())) vo.setModNo(vo.getUserNo());
		UsrLoginVO ulvo = new UsrLoginVO();
		ulvo.setUserNo(vo.getUserNo());
		ulvo.setUserId(vo.getUserId());
		ulvo.setUserPass(vo.getUserPass());
//		ulvo.setUserPass( KISASeed.seed256HashEncryption(vo.getUserPass()) );
		ulvo.setAdminLoginAcptDivCd("ACNT");
		ulvo.setUserSts("U");
		ulvo.setRegNo(vo.getRegNo());
		ulvo.setModNo(vo.getModNo());
		ulvo.setPhoneVeriYn(vo.getPhoneVeriYn());
		
		String snsDiv = StringUtil.nvl(vo.getSnsCode(), "");
		if(!snsDiv.equals("")){
			if(snsDiv.equals("K")){
				snsDiv = "KAKAO";
			}else if(snsDiv.equals("F")){
				snsDiv = "FACEBOOK";
			}else if(snsDiv.equals("N")){
				snsDiv ="NAVER";
			}else if(snsDiv.equals("G")){
				snsDiv ="GOOGLE";
			}else if(snsDiv.equals("D")){
				snsDiv ="DAUM";
			}
			ulvo.setSnsDiv(snsDiv);
			ulvo.setSnsKey(vo.getUserId());
		}
		
		usrLoginMapper.insert(ulvo);

		UsrUserInfoChgHstyVO uichvo = new UsrUserInfoChgHstyVO();
		uichvo.setUserNo(vo.getUserNo());
		uichvo.setRegNo(vo.getRegNo());
		uichvo.setUserInfoChgDivCd(userInfoChgDivCd); //-- 관리자 등록
		uichvo.setUserInfoCts(vo.getString());
		usrUserInfoChgHstyMapper.insert(uichvo);
		String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
		String[] admAuth = StringUtil.split(vo.getAdminAuthGrpCd(),"/");
		String[] mngAuth = StringUtil.split(vo.getMngAuthGrpCd(),"/");
		for(int i=1; i < mngAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("MANAGE");
			uuagvo.setAuthGrpCd(mngAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
			
			// 담임 or 보조담임 권한의 경우 강의실 접속권한 (teacher) 을 부여해준다. 
			// 강의실 접속 용 (2023.11.06)
			if(uuagvo.getAuthGrpCd().equals("TCHMGR") || uuagvo.getAuthGrpCd().equals("ASSTCHMGR")) {
				if(vo.getWwwAuthGrpCd().indexOf("TEACHER") <0)
					wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd() + "/TEACHER","/");
			}
		}
		for(int i=1; i < wwwAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("HOME");
			uuagvo.setAuthGrpCd(wwwAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		for(int i=1; i < admAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("ADMIN");
			uuagvo.setAuthGrpCd(admAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}
	
	/**
	 * 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.CREATE)
	public int ssoAdd(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		//-- 사용자 키를 생성하여 셋팅한다.
		String userNo = usrUserInfoMapper.selectKey();
		vo.setUserNo(userNo);
		
		vo.encryptJuminNo();
		//-- 사용자 정보를 저장한다.
		int result = usrUserInfoMapper.insert(vo);
		
		//-- 회원가입인 경우 RegNo와 ModNo가 비어 있음, 이경우 등록된 사용자 아이디로 교체....
		if(ValidationUtils.isEmpty(vo.getRegNo())) vo.setRegNo(vo.getUserNo());
		if(ValidationUtils.isEmpty(vo.getModNo())) vo.setModNo(vo.getUserNo());
		UsrLoginVO ulvo = new UsrLoginVO();
		ulvo.setUserNo(vo.getUserNo());
		ulvo.setUserId(vo.getUserId());
		ulvo.setUserPass("");
		ulvo.setAdminLoginAcptDivCd("ACNT");
		ulvo.setUserSts("U");
		ulvo.setRegNo(vo.getRegNo());
		ulvo.setModNo(vo.getModNo());
		
		ulvo.setSnsDiv("SSO");
		ulvo.setSnsKey(vo.getUserId());
		
		usrLoginMapper.insert(ulvo);

		UsrUserInfoChgHstyVO uichvo = new UsrUserInfoChgHstyVO();
		uichvo.setUserNo(vo.getUserNo());
		uichvo.setRegNo(vo.getRegNo());
		uichvo.setUserInfoChgDivCd(userInfoChgDivCd); //-- 관리자 등록
		uichvo.setUserInfoCts(vo.getString());
		usrUserInfoChgHstyMapper.insert(uichvo);

		String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
		String[] admAuth = StringUtil.split(vo.getAdminAuthGrpCd(),"/");
		String[] mngAuth = StringUtil.split(vo.getMngAuthGrpCd(),"/");
		for(int i=1; i < wwwAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("MANAGE");
			uuagvo.setAuthGrpCd(wwwAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		for(int i=1; i < admAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("ADMIN");
			uuagvo.setAuthGrpCd(admAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		for(int i=1; i < mngAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("MANAGE");
			uuagvo.setAuthGrpCd(mngAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		return result;
	}
	
	/**
	 * 사용자 상담 등록
	 * @param UsrUserInfoVO vo
	 * @param int
	 * @return  ProcessResultDTO
	 */
	@Override
	public AbstractResult addConsult(UsrUserInfoVO vo) throws Exception {
		AbstractResult result = new AbstractResult();
		String cnstNo = usrUserInfoMapper.selectConsultKey();
		vo.setCnstCd(cnstNo);
		try {
			usrUserInfoMapper.insertConsult(vo);
			result.setResult(1);
			result.setMessage("상담을 등록 했습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("상담 등록 중 오류가 발생했습니다.");
		}
		return result;
	}

	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @param int
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.UPDATE)
	public int edit(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		vo.encryptJuminNo();
		int result = usrUserInfoMapper.update(vo);

		UsrLoginVO ulvo = new UsrLoginVO();
		ulvo.setUserNo(vo.getUserNo());
		ulvo = usrLoginMapper.select(ulvo);

		ulvo.setUserPass(vo.getUserPass());
		if(ValidationUtils.isNotEmpty(vo.getUserSts())) {
			ulvo.setUserSts(vo.getUserSts());
		}
		usrLoginMapper.update(ulvo);

		UsrUserInfoChgHstyVO uichvo = new UsrUserInfoChgHstyVO();
		uichvo.setUserNo(vo.getUserNo());
		uichvo.setRegNo(vo.getRegNo());
		uichvo.setUserInfoChgDivCd(userInfoChgDivCd); //-- 관리자 수정
		uichvo.setUserInfoCts(vo.getString());
		usrUserInfoChgHstyMapper.insert(uichvo);

		UsrUserAuthGrpVO uuagVO = new UsrUserAuthGrpVO();
		uuagVO.setUserNo(vo.getUserNo());
		usrUserAuthGrpMapper.deleteAllUser(uuagVO);

		vo.setWwwAuthGrpCd(StringUtil.ReplaceAll(vo.getWwwAuthGrpCd(), "|", "/"));
		vo.setAdminAuthGrpCd(StringUtil.ReplaceAll(vo.getAdminAuthGrpCd(), "|", "/"));
		vo.setMngAuthGrpCd(StringUtil.ReplaceAll(vo.getMngAuthGrpCd(), "|", "/"));

		String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
		String[] admAuth = StringUtil.split(vo.getAdminAuthGrpCd(),"/");
		String[] mngAuth = StringUtil.split(vo.getMngAuthGrpCd(),"/");
		for(int i=1; i < mngAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("MANAGE");
			uuagvo.setAuthGrpCd(mngAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
			// 담임 or 보조담임 권한의 경우 강의실 접속권한 (teacher) 을 부여해준다. 
			// 강의실 접속 용 (2023.11.06)
			if(uuagvo.getAuthGrpCd().equals("TCHMGR") || uuagvo.getAuthGrpCd().equals("ASSTCHMGR")) {
				if(vo.getWwwAuthGrpCd().indexOf("TEACHER") <0)
					wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd() + "/TEACHER","/");
			}
		}

		for(int i=1; i < wwwAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("HOME");
			uuagvo.setAuthGrpCd(wwwAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		for(int i=1; i < admAuth.length; i++) {
			UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
			uuagvo.setMenuType("ADMIN");
			uuagvo.setAuthGrpCd(admAuth[i]);
			uuagvo.setUserNo(vo.getUserNo());
			uuagvo.setRegNo(vo.getRegNo());
			uuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(uuagvo);
		}
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		//사용자 댓글 메모 merge
		/*UserCmntDTO cmntDTO = new UserCmntDTO();
		cmntDTO.setUserNo(vo.getUserNo());
		cmntDTO.setCmntCts(vo.getMemo());
		cmntDTO.setRegNo(vo.getRegNo());
		cmntDTO.setModNo(vo.getModNo());
		userCmntDAO.merge(cmntDTO);*/
		
		return result;
	}

	/**
	 * 사용자 정보 삭제
	 * @param UsrUserInfoVO vo
	 * @return
	 */
	@Override
	@HrdApiUsrUserInfo(Type.DELETE)
	public int remove(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.delete(vo);
	}

	private void getAuthGrp(UsrUserInfoVO vo) throws Exception {
		UsrUserAuthGrpVO uuavo = new UsrUserAuthGrpVO();
		if(ValidationUtils.isNotEmpty(vo.getUserNo())) {
			uuavo.setUserNo(vo.getUserNo());
			List<UsrUserAuthGrpVO> authGrpList = usrUserAuthGrpMapper.list(uuavo);
			String wwwAuthGrp = "";
			String admAuthGrp = "";
			String mngAuthGrp = "";
			for(int i=0; i < authGrpList.size(); i++) {
				UsrUserAuthGrpVO iuagvo = authGrpList.get(i);
				if("ADMIN".equals(iuagvo.getMenuType())) {
					admAuthGrp = admAuthGrp+"|"+iuagvo.getAuthGrpCd();
				} else if("MANAGE".equals(iuagvo.getMenuType())) {
					mngAuthGrp = mngAuthGrp+"|"+iuagvo.getAuthGrpCd();
				} else {
					wwwAuthGrp = wwwAuthGrp+"|"+iuagvo.getAuthGrpCd();
				}
			}
			vo.setAdminAuthGrpCd(admAuthGrp);
			vo.setWwwAuthGrpCd(wwwAuthGrp);
			vo.setMngAuthGrpCd(mngAuthGrp);
		}
	}

	/**
	 * 일반 사용자 일괄 등록
	 * @param List<UsrUserInfoVO> userList
	 * @return
	 */
	@Override
	public int addBatch(List<UsrUserInfoVO> userList) throws Exception {
		for(UsrUserInfoVO vo : userList) {
			String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
			usrUserInfoMapper.insert(vo);

			//사용자 댓글 메모 merge
			/*UserCmntDTO cmntDTO = new UserCmntDTO();
			cmntDTO.setUserNo(vo.getUserNo());
			cmntDTO.setCmntCts(vo.getMemo());
			cmntDTO.setRegNo(vo.getRegNo());
			cmntDTO.setModNo(vo.getModNo());
			userCmntDAO.insert(cmntDTO);*/

			UsrLoginVO ulvo = new UsrLoginVO();
			ulvo.setUserNo(vo.getUserNo());
			ulvo.setUserId(vo.getUserId());
			ulvo.setUserPass(vo.getUserPass());
			ulvo.setAdminLoginAcptDivCd("ACNT");
			ulvo.setGpkiInfo(vo.getGpkiInfo());
			ulvo.setUserSts("U");
			ulvo.setRegNo(vo.getUserNo());
			ulvo.setModNo(vo.getUserNo());
			usrLoginMapper.insert(ulvo);

			for(int i=1; i < wwwAuth.length; i++) {
				UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
				uuagvo.setMenuType("HOME");
				uuagvo.setAuthGrpCd(wwwAuth[i]);
				uuagvo.setUserNo(vo.getUserNo());
				uuagvo.setRegNo(vo.getUserNo());
				uuagvo.setModNo(vo.getUserNo());
				usrUserAuthGrpMapper.insert(uuagvo);
			}
		}
		return 1;
	}

	/**
	 * 사용자 이름과 이메일을 가지고 사용자의 ID를 검색한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO selectSearchId(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.selectSearchId(vo);
	}
	
	/**
	 * 사용자의 패스워드를 조회한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO selectSearchPass(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.selectSearchPass(vo);
	}
	
	/**
	 * 사용자의 권한을 삭제후 등록 한다.
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.UPDATE)
	public int editAuth(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {

		UsrUserInfoChgHstyVO uichvo = new UsrUserInfoChgHstyVO();
		uichvo.setUserNo(vo.getUserNo());
		uichvo.setRegNo(vo.getRegNo());
		uichvo.setUserInfoChgDivCd(userInfoChgDivCd); //-- 관리자 수정
		uichvo.setUserInfoCts(JsonUtil.getJsonString(vo));
		usrUserInfoChgHstyMapper.insert(uichvo);

		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());
		usrUserAuthGrpMapper.deleteAllUser(uuagvo);

		String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
		String[] admAuth = StringUtil.split(vo.getAdminAuthGrpCd(),"/");
		String[] mngAuth = StringUtil.split(vo.getMngAuthGrpCd(),"/");
		for(int i=1; i < wwwAuth.length; i++) {
			UsrUserAuthGrpVO iuagvo = new UsrUserAuthGrpVO();
			iuagvo.setMenuType("HOME");
			iuagvo.setAuthGrpCd(wwwAuth[i]);
			iuagvo.setUserNo(vo.getUserNo());
			iuagvo.setRegNo(vo.getRegNo());
			iuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(iuagvo);
		}
		for(int i=1; i < admAuth.length; i++) {
			UsrUserAuthGrpVO iuagvo = new UsrUserAuthGrpVO();
			iuagvo.setMenuType("ADMIN");
			iuagvo.setAuthGrpCd(admAuth[i]);
			iuagvo.setUserNo(vo.getUserNo());
			iuagvo.setRegNo(vo.getRegNo());
			iuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(iuagvo);
		}
		for(int i=1; i < mngAuth.length; i++) {
			UsrUserAuthGrpVO iuagvo = new UsrUserAuthGrpVO();
			iuagvo.setMenuType("MANAGE");
			iuagvo.setAuthGrpCd(mngAuth[i]);
			iuagvo.setUserNo(vo.getUserNo());
			iuagvo.setRegNo(vo.getRegNo());
			iuagvo.setModNo(vo.getModNo());
			usrUserAuthGrpMapper.insert(iuagvo);
		}
		return 1;

	}

	/**
	 * 회원탈퇴 처리
	 * @param vo
	 * @return
	 */
	@Override
	@HrdApiUsrUserInfo(Type.DELETE)
	public int joinOutMember(UsrUserInfoVO vo) throws Exception {
		//-- 사용자의 권한을 삭제한다.
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());
		usrUserAuthGrpMapper.deleteAllUser(uuagvo);

		//-- 로그인 정보를 삭제로 변경한다.
		UsrLoginVO ulvo = new UsrLoginVO();
		ulvo.setUserNo(vo.getUserNo());
		usrLoginMapper.updateWithdrawal(ulvo);

		//-- 회원정보를 삭제 처리 한다.
		int result = usrUserInfoMapper.updateWithdrawal(vo);

		return result;
	}

	/**
	 * DUP_INFO키를 이용하여 중복확인.
	 * @param vo
	 * @return
	 */
	@Override
	public int viewDupulicate(UsrUserInfoVO vo) throws Exception {
		//return usrUserInfoMapper.selectDuplicate(vo);
		return 1;
	}

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(HashMap<String, String> titles, OutputStream os, String orgCd) throws Exception {

		try {
			int rowNum = 0;
			int tileNo = 1;
			int columnNo = 1;
			int cellNo = 1;
			int totalCount = 1;
			String headerTitle = "";


			OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
			ouicvo.setOrgCd(orgCd);
			List<OrgUserInfoCfgVO> resultCfgList = orgUserInfoCfgMapper.list(ouicvo);
			for(OrgUserInfoCfgVO souicvo : resultCfgList) {
				if(!souicvo.getFieldNm().equals("PHOTO") && !souicvo.getFieldNm().equals("MESSAGE") && souicvo.getUseYn().equals("Y") ) {
					totalCount++;
				}
			}

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("users", 0); //-- 시트 만들기

			//첫번째열 문서 제목줄 만들기
			sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center",titles.get("exceltitle")));
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, totalCount, rowNum); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 700);



			//-- 타이틀 만들기 user.title.userinfo.userid
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"No"));

			for(OrgUserInfoCfgVO ouicv : resultCfgList) {
				if(!ouicv.getFieldNm().equals("PHOTO") && !ouicv.getFieldNm().equals("MESSAGE") && ouicv.getUseYn().equals("Y") ){
					if(ouicv.getFieldNm().equals("USERID")){ headerTitle="userid"; }
					if(ouicv.getFieldNm().equals("USERNM")){ headerTitle="usernm"; }
					if(ouicv.getFieldNm().equals("BIRTH")){ headerTitle="birth"; }
					if(ouicv.getFieldNm().equals("USERNMKANA")){ headerTitle="usernmkana"; }
					if(ouicv.getFieldNm().equals("SEX")){ headerTitle="sex"; }
					if(ouicv.getFieldNm().equals("USERNMENG")){ headerTitle="usernmeng"; }
					if(ouicv.getFieldNm().equals("AREA")){ headerTitle="area"; }
					if(ouicv.getFieldNm().equals("USERDIV")){ headerTitle="userdiv"; }
					if(ouicv.getFieldNm().equals("EMAIL")){ headerTitle="email"; }
					if(ouicv.getFieldNm().equals("DEPT")){ headerTitle="dept"; }
					if(ouicv.getFieldNm().equals("MOBILENO")){ headerTitle="mobileno"; }
					if(ouicv.getFieldNm().equals("PHONENO")){ headerTitle="phoneno"; }
					if(ouicv.getFieldNm().equals("FAXNO")){ headerTitle="faxno"; }
					if(ouicv.getFieldNm().equals("COMPPHONE")){ headerTitle="compphone"; }
					if(ouicv.getFieldNm().equals("MESSAGE")){ headerTitle="message"; }
					if(ouicv.getFieldNm().equals("ETCPHONE")){ headerTitle="etcphone"; }
					if(ouicv.getFieldNm().equals("COMPADDR")){ headerTitle="compaddr"; }
					if(ouicv.getFieldNm().equals("HOMEADDR")){ headerTitle="homeaddr"; }
					if(ouicv.getFieldNm().equals("DISABLILITY")){ headerTitle="disablility"; }
					if(ouicv.getFieldNm().equals("INTEREST")){ headerTitle="interest"; }
					if(ouicv.getFieldNm().equals("MEMO")){ headerTitle="memo"; }

					if(ouicv.getFieldNm().equals("USERID")){
						sheet.addCell(ExcelUtil.createHeader(tileNo,rowNum,titles.get("useird")));
						tileNo++;
						sheet.addCell(ExcelUtil.createHeader(tileNo,rowNum,titles.get("password")));
					} else {
						sheet.addCell(ExcelUtil.createHeader(tileNo,rowNum,titles.get(headerTitle)));
					}
					tileNo++;
				}
			}

			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 8);
			for(OrgUserInfoCfgVO ouicv : resultCfgList) {
				if(!ouicv.getFieldNm().equals("PHOTO") && !ouicv.getFieldNm().equals("MESSAGE") && ouicv.getUseYn().equals("Y") ){
					if(ouicv.getFieldNm().equals("USERID")){
						sheet.setColumnView(columnNo, 15);
						columnNo++;
						sheet.setColumnView(columnNo, 15);
					} else {
						sheet.setColumnView(columnNo, 15);
					}
					columnNo++;
				}
			}

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 600);

			//-- 빈라인 10줄 만들기
			for(int i=0; i< 10; i++) {
				rowNum++;
				sheet.addCell(ExcelUtil.createNumber(0,rowNum,"center",(i+1)));
				for(OrgUserInfoCfgVO ouicv : resultCfgList) {
					if(!ouicv.getFieldNm().equals("PHOTO") && !ouicv.getFieldNm().equals("MESSAGE") && ouicv.getUseYn().equals("Y") ){
						if(ouicv.getFieldNm().equals("USERID")){
							sheet.addCell(ExcelUtil.createText(cellNo,rowNum,"center",""));
							cellNo++;
							sheet.addCell(ExcelUtil.createText(cellNo,rowNum,"center",""));
						} else {
							sheet.addCell(ExcelUtil.createText(cellNo,rowNum,"center",""));
						}
						cellNo++;
					}
				}
				cellNo = 1;
				sheet.setRowView(rowNum, 500);
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new Exception("Failed! Make excel file.", e);
		}
	}

	/**
	 * Upload 사용자 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> excelUploadValidationCheck(String fileName,
			String filePath, String orgCd, List<SysCodeVO> moblieCode) throws Exception {

		ProcessResultListVO<UsrUserInfoVO> resultVO = new ProcessResultListVO<UsrUserInfoVO>();

		Workbook workbook	= null;
		Sheet sheet			= null;

		String errMsg = "";
		try {
			workbook = Workbook.getWorkbook(new File(filePath + "/" + fileName));
		} catch (BiffException ex1) {
			// TODO Auto-generated catch block
			ex1.printStackTrace();
		} catch (IOException ex2) {
			// TODO Auto-generated catch block
			ex2.printStackTrace();
		}

		sheet = workbook.getSheet(0);
		try {

		} catch (Exception e) {
			throw new Exception("Failed read excel : " + e.getMessage(), e);
		}

		List<UsrUserInfoVO> userList = new ArrayList<UsrUserInfoVO>();

		OrgUserInfoCfgVO nouicv = new OrgUserInfoCfgVO();
		nouicv.setOrgCd(orgCd);
		List<OrgUserInfoCfgVO> resultCfgList = orgUserInfoCfgMapper.list(nouicv);

		int useCount = 0;
		int cellCount = sheet.getColumns()-2;
		for(OrgUserInfoCfgVO ouicv : resultCfgList) {
			if(!ouicv.getFieldNm().equals("PHOTO") && !ouicv.getFieldNm().equals("MESSAGE") && ouicv.getUseYn().equals("Y") ){
				useCount++;
			}
		}

		if(useCount != cellCount){
			resultVO.setResult(-1);
			resultVO.setMessage("ERROR_CNT");
		} else {

			int cellNo = 1;
			for (int j = 2; j < sheet.getRows(); j++) {
				Cell cellLineNo = sheet.getCell(0, j); 			// 라인 번호
				Cell cellUserId = null;
				Cell cellPasswd = null; 			// 비밀번호
				Cell cellUserNm = null;
				Cell cellBirth = null;
				Cell cellUserNmkana = null;
				Cell cellSexCd = null;
				Cell cellUserNmeng = null;
				Cell cellArea = null;
				Cell cellUserdiv = null;
				Cell cellEmail = null;
				Cell cellDept = null;
				Cell cellMobileno = null;
				Cell cellPhoneno = null;
				Cell cellFaxNo = null;
				Cell cellCompphone = null;
				//Cell cellMessage = null;
				Cell cellEtcphone = null;
				Cell cellCompAddr = null;
				Cell cellHomeAddr = null;
				Cell cellDisablility = null;
				Cell cellInterest = null;
				Cell cellMemo = null;



				for(OrgUserInfoCfgVO ouicvo : resultCfgList) {
					if(!ouicvo.getFieldNm().equals("PHOTO") && !ouicvo.getFieldNm().equals("MESSAGE") && ouicvo.getUseYn().equals("Y") ){
						if(ouicvo.getFieldNm().equals("USERID")){
							cellUserId = sheet.getCell(cellNo, j);
							cellNo++;
							cellPasswd = sheet.getCell(cellNo, j);
						} else {
							if(ouicvo.getFieldNm().equals("USERNM")){
								cellUserNm = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("BIRTH")){
								cellBirth = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("USERNMKANA")){
								cellUserNmkana = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("SEX")){
								cellSexCd = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("USERNMENG")){
								cellUserNmeng = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("AREA")){
								cellArea = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("USERDIV")){
								cellUserdiv = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("EMAIL")){
								cellEmail = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("DEPT")){
								cellDept = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("MOBILENO")){
								cellMobileno = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("PHONENO")){
								cellPhoneno = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("FAXNO")){
								cellFaxNo = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("COMPPHONE")){
								cellCompphone = sheet.getCell(cellNo, j);
							}
							
						/*if(svo.getFieldNm().equals("MESSAGE")){
							cellMessage = sheet.getCell(cellNo, j);
						}*/
							 
							if(ouicvo.getFieldNm().equals("ETCPHONE")){
								cellEtcphone = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("COMPADDR")){
								cellCompAddr = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("HOMEADDR")){
								cellHomeAddr = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("DISABLILITY")){
								cellDisablility = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("INTEREST")){
								cellInterest = sheet.getCell(cellNo, j);
							}
							if(ouicvo.getFieldNm().equals("MEMO")){
								cellMemo = sheet.getCell(cellNo, j);
							}
						}
						cellNo++;
					}
				}
				cellNo = 1;
				String errorCode = "";
				String regex = "^[0-9,-]*$";
				String regexId = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,15}$";
				String regexPass = "^[a-z0-9,!,@,#,$,%,^,&,*,?,_,~]{4,15}$";
				String regexMail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
				boolean areaCd = false;
				boolean divCd = false;
				boolean interestCd = false;
				boolean deptCd = false;
				boolean mobileCheck = false;

				UsrDeptInfoVO udivo = new UsrDeptInfoVO();
				udivo.setOrgCd(orgCd);
				udivo.setUseYn("Y");

				List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd, "AREA_CD").getReturnList();
				List<OrgCodeVO> divCode = orgCodeService.listCode(orgCd, "USER_DIV__CD").getReturnList();
				List<OrgCodeVO> interestCode = orgCodeService.listCode(orgCd, "INTEREST_FIELD_CD").getReturnList();
				
				List<UsrDeptInfoVO> deptCode = usrDeptInfoMapper.list(udivo);

				UsrUserInfoVO uivo = new UsrUserInfoVO();
				uivo.setLineNo(cellLineNo.getContents().trim());
				for(OrgUserInfoCfgVO ouicvo : resultCfgList) {
					if(!ouicvo.getFieldNm().equals("PHOTO") && !ouicvo.getFieldNm().equals("MESSAGE") && ouicvo.getUseYn().equals("Y") ){
						if(ouicvo.getFieldNm().equals("USERID")){
							uivo.setUserId(cellUserId.getContents().trim());
							uivo.setUserPass(cellPasswd.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("USERNM")){
							uivo.setUserNm(cellUserNm.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("BIRTH")){
							uivo.setBirth(cellBirth.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("USERNMKANA")){
							uivo.setUserNmGana(cellUserNmkana.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("SEX")){
							uivo.setSexCd(cellSexCd.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("USERNMENG")){
							uivo.setUserNmEng(cellUserNmeng.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("AREA")){
							uivo.setAreaCd(cellArea.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("USERDIV")){
							uivo.setUserDivCd(cellUserdiv.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("EMAIL")){
							uivo.setEmail(cellEmail.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("DEPT")){
							uivo.setDeptCd(cellDept.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("MOBILENO")){
							uivo.setMobileNo(cellMobileno.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("PHONENO")){
							uivo.setHomePhoneno(cellPhoneno.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("FAXNO")){
							uivo.setCompFaxNo(cellFaxNo.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("COMPPHONE")){
							uivo.setCompPhoneno(cellCompphone.getContents().trim());
						}
						
					/*if(svo.getFieldNm().equals("MESSAGE")){
						uivo.setMsgRecv(cellMessage.getContents().trim());
					}*/
						 
						if(ouicvo.getFieldNm().equals("ETCPHONE")){
							uivo.setEtcPhoneno(cellEtcphone.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("COMPADDR")){
							uivo.setCompAddr1(cellCompAddr.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("HOMEADDR")){
							uivo.setHomeAddr1(cellHomeAddr.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("DISABLILITY")){
							uivo.setDisablilityYn(cellDisablility.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("INTEREST")){
							uivo.setInterestFieldCd(cellInterest.getContents().trim());
						}
						if(ouicvo.getFieldNm().equals("MEMO")){
							uivo.setMemo(cellMemo.getContents().trim());
						}
					}
				}


				if(ValidationUtils.isEmpty(uivo.getUserId())) {
					//-- 사용자 아이디가 없는 경우는 Array Seting 하지 않음.
				} else {
					//-- 사용자 ID Validation : 4~15자 사이에서 영문 및 숫자 포함.
					//-- 사용자 ID 중복 체크
					UsrLoginVO ulvo = new UsrLoginVO();
					ulvo.setUserId(uivo.getUserId());
					String isUseable = usrLoginMapper.selectIdCheck(ulvo);
					if("N".equals(isUseable)) {
						//-- 중복 ID
						errorCode += "|"+"DUPUSERID";
					}

					if(!uivo.getUserId().matches(regexId)){
						errorCode += "|"+"TYPEUSERID";
					}
					//--- 사용자 PASS Validation : 4~15자 사이의 영문 및 숫자 포함.
					if(ValidationUtils.isEmpty(uivo.getUserPass())) {
						errorCode += "|"+"EMPTYPASSWD";
					}
					for(OrgUserInfoCfgVO ouicvo : resultCfgList) {
						mobileCheck = false;
						if(!ouicvo.getFieldNm().equals("PHOTO") && !ouicvo.getFieldNm().equals("MESSAGE") && ouicvo.getUseYn().equals("Y") ){
							if(ouicvo.getFieldNm().equals("USERNM")){
								if(ValidationUtils.isEmpty(uivo.getUserNm()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYUSERNM";
								}
								if(uivo.getUserNm().length() > 14) {
									errorCode += "|"+"TYPEUSERNM";
								}
							}
							if(ouicvo.getFieldNm().equals("BIRTH") ){
								if(ValidationUtils.isEmpty(uivo.getBirth()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYBIRTH";
								}
								if(ValidationUtils.isNotEmpty(uivo.getBirth())){
									if(!StringUtil.isNumber(uivo.getBirth())){
										errorCode += "|"+"TYPEBIRTH";
									}
									if(uivo.getBirth().length() > 8 ){
										errorCode += "|"+"TYPEBIRTH";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("USERNMKANA") ){
								if(ValidationUtils.isEmpty(uivo.getUserNmGana()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYKANA";
								}
								if(ValidationUtils.isNotEmpty(uivo.getUserNmGana())){
									if(uivo.getUserNmGana().length() > 14) {
										errorCode += "|"+"TYPEKANA";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("SEX") ){
								if(ValidationUtils.isEmpty(uivo.getSexCd()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYSEXCD";
								}
								if(ValidationUtils.isNotEmpty(uivo.getSexCd())){
									if(!"M".equals(uivo.getSexCd()) && !"F".equals(uivo.getSexCd())){
										errorCode += "|"+"TYPESEXCD";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("USERNMENG") ){
								if(ValidationUtils.isEmpty(uivo.getUserNmEng()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYENG";
								}
								if(ValidationUtils.isNotEmpty(uivo.getUserNmEng())){
									if(uivo.getUserNmEng().length() > 14) {
										errorCode += "|"+"TYPEENG";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("AREA") ){
								if(ValidationUtils.isEmpty(uivo.getAreaCd()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYAREA";
								}
								if(ValidationUtils.isNotEmpty(uivo.getAreaCd())){
									for(OrgCodeVO socvo : areaCode) {
										if(socvo.getCodeCd().equals(uivo.getAreaCd()) ){
											areaCd = true;
											uivo.setAreaNm(socvo.getCodeNm());
										}
									}
									if(!areaCd){
										errorCode += "|"+"TYPEAREACD";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("USERDIV") ){
								if(ValidationUtils.isEmpty(uivo.getUserDivCd()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYUSERDIV";
								}
								if(ValidationUtils.isNotEmpty(uivo.getUserDivCd())){
									for(OrgCodeVO socvo : divCode) {
										if(socvo.getCodeCd().equals(uivo.getUserDivCd()) ){
											divCd = true;
											uivo.setUserDivNm(socvo.getCodeNm());
										}
									}
									if(!divCd){
										errorCode += "|"+"TYPEUSERDIV";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("EMAIL") ){
								if(ValidationUtils.isEmpty(uivo.getEmail()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYEMAIL";
								}
								if(ValidationUtils.isNotEmpty(uivo.getEmail())){
									if(!uivo.getEmail().matches(regexMail)){
										errorCode += "|"+"TYPEEMAIL";
									}
									if(uivo.getEmail().length() > 255){
										errorCode += "|"+"TYPEEMAIL";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("DEPT") ){
								if(ValidationUtils.isEmpty(uivo.getDeptCd()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYDEPT";
								}
								if(ValidationUtils.isNotEmpty(uivo.getDeptCd())){
									for(UsrDeptInfoVO deptvo : deptCode) {
										if(deptvo.getDeptCd().equals(uivo.getDeptCd()) ){
											deptCd = true;
											uivo.setDeptNm(deptvo.getDeptNm());
										}
									}
									if(!deptCd){
										errorCode += "|"+"TYPEDEPT";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("MOBILENO") ){
								if(ValidationUtils.isEmpty(uivo.getMobileNo()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYMOBILE";
								}
								if(ValidationUtils.isNotEmpty(uivo.getMobileNo())){
									if(!uivo.getMobileNo().matches(regex)){
										errorCode += "|"+"TYPEMOBILE";
									}
									if(uivo.getMobileNo().length() > 14){
										errorCode += "|"+"TYPEMOBILE";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("PHONENO") ){
								if(ValidationUtils.isEmpty(uivo.getHomePhoneno()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYPHONENO";
								}
								if(ValidationUtils.isNotEmpty(uivo.getHomePhoneno())){
									if(!uivo.getHomePhoneno().matches(regex)){
										errorCode += "|"+"TYPEPHONENO";
									}
									if(uivo.getHomePhoneno().length() > 14){
										errorCode += "|"+"TYPEPHONENO";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("FAXNO") ){
								if(ValidationUtils.isEmpty(uivo.getCompFaxNo()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYFAXNO";
								}
								if(ValidationUtils.isNotEmpty(uivo.getCompFaxNo())){
									if(!uivo.getCompFaxNo().matches(regex)){
										errorCode += "|"+"TYPEFAXNO";
									}
									if(uivo.getCompFaxNo().length() > 14){
										errorCode += "|"+"TYPEFAXNO";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("COMPPHONE") ){
								if(ValidationUtils.isEmpty(uivo.getCompPhoneno()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYCOMPPHONE";
								}
								if(ValidationUtils.isNotEmpty(uivo.getCompPhoneno())){
									if(!uivo.getCompPhoneno().matches(regex)){
										errorCode += "|"+"TYPECOMPPHONE";
									}
									if(uivo.getCompPhoneno().length() > 14){
										errorCode += "|"+"TYPECOMPPHONE";
									}
								}
							}
							
						/*if(svo.getFieldNm().equals("MESSAGE") && svo.getRequiredYn().equals("Y") ){
							if(ValidationUtils.isEmpty(uivo.getMsgRecv())) {
								errorCode += "|"+"EMPTYMESSAGE";
							}
						}*/
							 
							if(ouicvo.getFieldNm().equals("ETCPHONE") ){
								if(ValidationUtils.isEmpty(uivo.getEtcPhoneno()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYETCPHONE";
								}
								if(ValidationUtils.isNotEmpty(uivo.getEtcPhoneno())){
									if(!uivo.getEtcPhoneno().matches(regex)){
										errorCode += "|"+"TYPEETCPHONE";
									}
									if(uivo.getEtcPhoneno().length() > 14){
										errorCode += "|"+"TYPEETCPHONE";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("COMPADDR") ){
								if(ValidationUtils.isEmpty(uivo.getCompAddr1()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYCOMPADDR";
								}
								if(ValidationUtils.isNotEmpty(uivo.getCompAddr1())){
									if(uivo.getCompAddr1().length() > 255){
										errorCode += "|"+"TYPECOMPADDR";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("HOMEADDR") ){
								if(ValidationUtils.isEmpty(uivo.getHomeAddr1()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYHOMEADDR";
								}
								if(ValidationUtils.isNotEmpty(uivo.getHomeAddr1())){
									if(uivo.getHomeAddr1().length() > 255){
										errorCode += "|"+"TYPEHOMEADDR";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("DISABLILITY") ){
								if(ValidationUtils.isEmpty(uivo.getDisablilityYn()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYDISABLILITY";
								}
								if(ValidationUtils.isNotEmpty(uivo.getDisablilityYn())){
									if(!"Y".equals(uivo.getDisablilityYn()) && !"N".equals(uivo.getDisablilityYn())){
										errorCode += "|"+"TYPEDISABLILITY";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("INTEREST") ){
								if(ValidationUtils.isEmpty(uivo.getInterestFieldCd()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYINTEREST";
								}
								if(ValidationUtils.isNotEmpty(uivo.getInterestFieldCd())){
									for(OrgCodeVO socvo : interestCode) {
										if(socvo.getCodeCd().equals(uivo.getInterestFieldCd()) ){
											interestCd = true;
											uivo.setInterestFieldNm(socvo.getCodeNm());
										}
									}
									if(!interestCd){
										errorCode += "|"+"TYPEINTEREST";
									}
								}
							}
							if(ouicvo.getFieldNm().equals("MEMO") ){
								if(ValidationUtils.isEmpty(uivo.getMemo()) && ouicvo.getRequiredYn().equals("Y") ) {
									errorCode += "|"+"EMPTYMEMO";
								}
								if(ValidationUtils.isNotEmpty(uivo.getMemo())){

								}
							}
						}
					}
					uivo.setErrorCode(errorCode);
					userList.add(uivo);
				}
			}
			resultVO.setReturnList(userList);
			resultVO.setResult(1);
			resultVO.setMessage("SUCCESS");
		}
		return resultVO;
	}
	
	/**
	 * 엑셀 파일을 통한 유저 등록
	 * @param UsrUserInfoVO
	 * @param String
	 * @param String
	 * @param String
	 * @return ProcessResultListVO<UsrUserInfoVO>
	 */
	@SuppressWarnings("resource")
	@Override
	public ProcessResultListVO<UsrUserInfoVO> excelUploadUserAdd(UsrUserInfoVO vo ,String fileName, String filePath, String orgCd) throws Exception {
		int mainSheetIndex = 0;
		int titleColumnCount = 13;
		int titleRowIndex = 3;
		int contentStartRowIndex = 4;
		
		ProcessResultListVO<UsrUserInfoVO> resultVO = new ProcessResultListVO<UsrUserInfoVO>();

		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		FileInputStream fis = null;
		fis= new FileInputStream(filePath + "/" + fileName);
		workbook= new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(mainSheetIndex);

		XSSFRow titleRow = sheet.getRow(titleRowIndex);
		int cellCount = titleRow.getPhysicalNumberOfCells();
		if(cellCount != titleColumnCount) {
			throw new ServiceProcessException("엑셀 컬럼 수 오류");
		} 
		List<UsrUserInfoVO> userList = new ArrayList<>();
		int contentRows = sheet.getPhysicalNumberOfRows() - 2;
		int rowCount = 0;
		for(int rowIndex = contentStartRowIndex; rowCount < contentRows; rowCount++,rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row != null) {
				UsrUserInfoVO user = getUserInfoFromRow(row);
				validateUserInfo(user);
				setBirthAndSexCdFromPlainJumin(user);
				
				user.setOrgCd(orgCd);
				user.setRegNo(vo.getRegNo());
				user.setModNo(vo.getRegNo());
				user.setUserPass("1234");		 // 초기 비밀번호
				user.setWwwAuthGrpCd("/MEMBER"); // 기본 회원 권한
				user.setMngAuthGrpCd("");
				user.setAdminAuthGrpCd("");
				user.setEmailRecv("Y");
				user.setSmsRecv("Y");
				user.setMsgRecv("Y");
				user.setPhoneVeriYn("N");
				usrUserInfoService.add(user, "UI");

				userList.add(user);
			}
		}
		resultVO.setResult(1);
		resultVO.setReturnList(userList);
		
		return resultVO;
	}
	
	// 셀에서 유저 정보 가져오기
	private UsrUserInfoVO getUserInfoFromRow(XSSFRow row) {
		UsrUserInfoVO userVO = new UsrUserInfoVO();
		userVO.setLineNo("" + (row.getRowNum() - 3));
		userVO.setUserId(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(0))).trim());
		userVO.setUserNm(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(1))).trim());
		userVO.setJuminNo(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(2))).replaceAll("-", ""));
		userVO.setMobileNo(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(3))).replaceAll("-", ""));
		userVO.setHomePhoneno(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(4))).replaceAll("-", ""));
		userVO.setEmail(POIExcelUtil.getCellValue(row.getCell(5)));
		userVO.setHomePostNo(POIExcelUtil.getCellValue(row.getCell(6)));
		userVO.setHomeAddr1(POIExcelUtil.getCellValue(row.getCell(7)));
		userVO.setHomeAddr2(POIExcelUtil.getCellValue(row.getCell(8)));
		userVO.setDeptCd(POIExcelUtil.getCellValue(row.getCell(9)));
		userVO.setStdDivCd(POIExcelUtil.getCellValue(row.getCell(10)));
		userVO.setNonReguDivCd(POIExcelUtil.getCellValue(row.getCell(11)));
		userVO.setCostCompNo(StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(12))).replaceAll("-",""));
		return userVO;
	}
	
	// 입력 정보 유효성 검사
	// 형식에 맞지 않으면 ServiceProcessException 발생
	private void validateUserInfo(UsrUserInfoVO vo) {
		String emailChkRegx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
		String lineNo = vo.getLineNo();
		String mobileNo = vo.getMobileNo();
		String email = vo.getEmail();
		String deptCd = vo.getDeptCd();
		String userId = vo.getUserId();
		
		UsrDeptInfoVO chkDeptVO = new UsrDeptInfoVO();
		chkDeptVO.setDeptCd(vo.getDeptCd());
		
		if(mobileNo.length() != 11) {
			throw new ServiceProcessException(String.format("잘못된 휴대폰 번호 형식 : [%S]행 (%s)", lineNo, mobileNo));
		}
		if(!Pattern.matches(emailChkRegx, email)) {
			throw new ServiceProcessException(String.format("잘못된 이메일 형식 : [%S]행 (%s)", lineNo, email));
		}
		if("N".equals(usrUserInfoMapper.selectEmailCheck(vo))) {
			throw new ServiceProcessException(String.format("중복된 이메일 : [%S]행 (%s)", lineNo, email));
		}
		
		UsrDeptInfoVO deptVO = usrDeptInfoMapper.select(chkDeptVO);
		if(deptVO == null) {
			throw new ServiceProcessException(String.format("잘못된 기업코드 : [%S]행 (%s)", lineNo, deptCd));
		}
		vo.setDeptNm(deptVO.getDeptNm());
		
		if("N".equals(usrLoginMapper.selectIdCheck(vo))) {
			throw new ServiceProcessException(String.format("중복된 아이디 : [%S]행 (%s)", lineNo, userId));
		}
	}
	
	private void setBirthAndSexCdFromPlainJumin(UsrUserInfoVO vo) {
		String sBirth = vo.getJuminNo().substring(0,6);
		String sSexCd = vo.getJuminNo().substring(6,7);
		int chk = Integer.parseInt(sSexCd);

		String sexCd = chk%2 == 0 ? "F" : "M";
		vo.setSexCd(sexCd);
		
		String extraYear = ((chk+1)/2)%2 == 0 ? "20" : "19";
		vo.setBirth(extraYear + sBirth);
	}

	/**
	 * 사용자 명단 다운로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public void listExcelDownload(UsrUserInfoVO userInfoDTO, String sheetName, String labelName, String condition, String excelHeader,
			ArrayList<String> titleList, HttpServletRequest request, OutputStream os, HashMap<String, String> titles)
			throws Exception {

		List<UsrUserInfoVO> resultList = usrUserInfoMapper.list(userInfoDTO);

		String[] headers = StringUtil.split(excelHeader, "@$");

		int colNum = headers.length - 1;

		String locale = UserBroker.getLocaleKey(request);

		try{
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum,"center", labelName)); //1열

			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, colNum, rowNum); //-- 병합
			sheet.setRowView(rowNum, 700);

			//-- 검색 조건 줄
			rowNum++;
			sheet.addCell(ExcelUtil.createLabel2(0,rowNum,"right", condition)); //1열
			sheet.mergeCells(0, rowNum, colNum, rowNum); //-- 병합
			sheet.setRowView(rowNum, 500);

			//-- header를 만든다.
			rowNum++;
			for(int i=0; i < headers.length; i++) {
				String headerName = "";
				if("NO".equals(headers[i])) headerName = titleList.get(0);
				if("DECLS".equals(headers[i])) headerName = titleList.get(1);
				if("USERNM".equals(headers[i])) headerName = titleList.get(2);
				if("USERID".equals(headers[i])) headerName = titleList.get(3);
				if("EMAIL".equals(headers[i])) headerName = titleList.get(4);
				if("BIRTH".equals(headers[i])) headerName = titleList.get(5);
				if("SEX".equals(headers[i])) headerName = titleList.get(6);
				if("PHONENO".equals(headers[i])) headerName = titleList.get(7);
				if("MOBILENO".equals(headers[i])) headerName = titleList.get(8);
				if("FAXNO".equals(headers[i])) headerName = titleList.get(9);
				if("DEPT".equals(headers[i])) headerName = titleList.get(10);
				if("USERNMKANA".equals(headers[i])) headerName = titleList.get(11);
				if("USERNMENG".equals(headers[i])) headerName = titleList.get(12);
				if("AREA".equals(headers[i])) headerName = titleList.get(13);
				if("USERDIV".equals(headers[i])) headerName = titleList.get(14);
				if("COMPPHONE".equals(headers[i])) headerName = titleList.get(15);
				if("ETCPHONE".equals(headers[i])) headerName = titleList.get(16);
				if("COMPADDR".equals(headers[i])) headerName = titleList.get(17);
				if("HOMEADDR".equals(headers[i])) headerName = titleList.get(18);
				if("DISABLILITY".equals(headers[i])) headerName = titleList.get(19);
				if("INTEREST".equals(headers[i])) headerName = titleList.get(20);
				if("MEMO".equals(headers[i])) headerName = titleList.get(21);
				sheet.addCell(ExcelUtil.createHeader(i,rowNum,headerName));
			}

			//-- 셀의 넓이 조절
			for(int i=0; i < headers.length; i++) {
				int fieldWidth = 10;
				if("NO".equals(headers[i])) fieldWidth = 8;
				if("DECLS".equals(headers[i])) fieldWidth = 8;
				if("USERNM".equals(headers[i])) fieldWidth = 14;
				if("USERID".equals(headers[i])) fieldWidth = 14;
				if("EMAIL".equals(headers[i])) fieldWidth = 28;
				if("BIRTH".equals(headers[i])) fieldWidth = 12;
				if("SEX".equals(headers[i])) fieldWidth = 12;
				if("PHONENO".equals(headers[i])) fieldWidth = 16;
				if("MOBILENO".equals(headers[i])) fieldWidth = 16;
				if("FAXNO".equals(headers[i])) fieldWidth = 16;
				if("DEPT".equals(headers[i])) fieldWidth = 26;
				if("USERNMKANA".equals(headers[i])) fieldWidth = 26;
				if("USERNMENG".equals(headers[i])) fieldWidth = 26;
				if("AREA".equals(headers[i])) fieldWidth = 26;
				if("USERDIV".equals(headers[i])) fieldWidth = 26;
				if("COMPPHONE".equals(headers[i])) fieldWidth = 26;
				if("ETCPHONE".equals(headers[i])) fieldWidth = 26;
				if("COMPADDR".equals(headers[i])) fieldWidth = 26;
				if("HOMEADDR".equals(headers[i])) fieldWidth = 26;
				if("DISABLILITY".equals(headers[i])) fieldWidth = 26;
				if("INTEREST".equals(headers[i])) fieldWidth = 26;
				if("MEMO".equals(headers[i])) fieldWidth = 26;
				if("APLCDTTM".equals(headers[i])) fieldWidth = 20;
				if("PAYMMTHD".equals(headers[i])) fieldWidth = 20;
				if("PAYMPRICE".equals(headers[i])) fieldWidth = 20;
				if("PAYMSTS".equals(headers[i])) fieldWidth = 15;
				if("GETSCORE".equals(headers[i])) fieldWidth = 12;
				if("SCOREECLT".equals(headers[i])) fieldWidth = 15;
				if("CPLTNO".equals(headers[i])) fieldWidth = 26;
				if("ENRLSTS".equals(headers[i])) fieldWidth = 12;
				sheet.setColumnView(i, fieldWidth);
			}

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				UsrUserInfoVO suuivo = resultList.get(i);

				for(int j = 0; j < headers.length; j++) {
					if("NO".equals(headers[j])) sheet.addCell(ExcelUtil.createNumber(j,rowNum,"center", (i+1)));
					//if("DECLS".equals(headers[j])) sheet.addCell(ExcelUtil.createNumber(j,rowNum,"center",StringUtil.nvl(uDTO.getDeclsNo(),0)));
					if("USERNM".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"center",suuivo.getUserNm()));
					if("USERID".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"center",suuivo.getUserId()));
					if("EMAIL".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getEmail()));
					if("BIRTH".equals(headers[j])) {
						String birth = suuivo.getBirth();
						if(ValidationUtils.isNotEmpty(birth)) {
							birth = DateTimeUtil.getDateType(1, birth);
						}
						sheet.addCell(ExcelUtil.createText(j,rowNum,"center", birth));
					}
					if("SEX".equals(headers[j])) {
						String sexNm = "";
						if("M".equals(suuivo.getSexCd())){
							sexNm = titles.get("sexNmM");
						} else if("F".equals(suuivo.getSexCd())) {
							sexNm = titles.get("sexNmF");
						}
						try {
							SysCodeVO scvo = sysCodeService.viewCode("SEX_CD", suuivo.getSexCd());
							sexNm = scvo.getCodeNm();
							for(SysCodeLangVO sclvo : scvo.getCodeLangList()) {
								if(locale.equals(sclvo.getLangCd())) sexNm = sclvo.getCodeNm();
							}
						} catch (Exception e) {
							sexNm = suuivo.getSexCd();
						}
						sheet.addCell(ExcelUtil.createText(j,rowNum,"center",sexNm));
					}
					if("PHONENO".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"center",suuivo.getHomePhoneno()));
					if("MOBILENO".equals(headers[j]))sheet.addCell(ExcelUtil.createText(j,rowNum,"center",suuivo.getMobileNo()));
					if("FAXNO".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"center",suuivo.getCompFaxNo()));
					if("DEPT".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getDeptNm()));

					if("USERNMKANA".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getUserNmGana()));
					if("USERNMENG".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getUserNmEng()));

					if("AREA".equals(headers[j])){
						String areaNm = "";
						if(StringUtil.isNotNull(suuivo.getAreaCd())){
							try {
								OrgCodeVO ocvo =  orgCodeService.viewCode(userInfoDTO.getOrgCd(), "AREA_CD", suuivo.getAreaCd());
								areaNm = ocvo.getCodeNm();
								for(OrgCodeLangVO clvo : ocvo.getCodeLangList()) {
									if(locale.equals(clvo.getLangCd())) areaNm = clvo.getCodeNm();
								}
							} catch (Exception e) {
								areaNm = suuivo.getAreaCd();
							}
						}

						sheet.addCell(ExcelUtil.createText(j,rowNum,"left",areaNm));
					}

					if("USERDIV".equals(headers[j])){
						String divNm = "";
						if(StringUtil.isNotNull(suuivo.getUserDivCd())){
							try {
								OrgCodeVO orgCodeVO = orgCodeService.viewCode(userInfoDTO.getOrgCd(), "USER_DIV_CD", suuivo.getUserDivCd());
								divNm = orgCodeVO.getCodeNm();
								for(OrgCodeLangVO clvo : orgCodeVO.getCodeLangList()) {
									if(locale.equals(clvo.getLangCd())) divNm = clvo.getCodeNm();
								}
							} catch (Exception e) {
								divNm = suuivo.getUserDivCd();
							}
						}
						sheet.addCell(ExcelUtil.createText(j,rowNum,"left",divNm));
					}
					if("INTEREST".equals(headers[j])){
						String interestNm = "";
						if(StringUtil.isNotNull(suuivo.getInterestFieldCd())){
							try {
								OrgCodeVO orgCodeVO = orgCodeService.viewCode(userInfoDTO.getOrgCd(), "INTEREST_FIELD_CD", suuivo.getInterestFieldCd()); 
								interestNm = orgCodeVO.getCodeNm();
								for(OrgCodeLangVO clvo : orgCodeVO.getCodeLangList()) {
									if(locale.equals(clvo.getLangCd())) interestNm = clvo.getCodeNm();
								}
							} catch (Exception e) {
								interestNm = suuivo.getInterestFieldCd();
							}
						}
						sheet.addCell(ExcelUtil.createText(j,rowNum,"left",interestNm));
					}

					if("COMPPHONE".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getCompPhoneno()));
					if("ETCPHONE".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getEtcPhoneno()));
					if("COMPADDR".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getCompAddr1()));
					if("HOMEADDR".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getHomeAddr1()));
					if("DISABLILITY".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getDisablilityYn()));
					if("MEMO".equals(headers[j])) sheet.addCell(ExcelUtil.createText(j,rowNum,"left",suuivo.getMemo()));
				}
				sheet.setRowView(rowNum, 500);
			}
			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw new Exception("Excel file creation failed!", e);
		}
	}
	
    /**
	 * 사용자의 권한 목록을 조회 한다.
	 * @param UsrUserAuthGrpVO vo
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UsrUserAuthGrpVO> listUserAuthGrp(UsrUserAuthGrpVO vo) throws Exception {
		ProcessResultListVO<UsrUserAuthGrpVO> resultList = new ProcessResultListVO<UsrUserAuthGrpVO>();
		List<UsrUserAuthGrpVO> userList = usrUserAuthGrpMapper.list(vo);
		resultList.setResult(1);
		resultList.setReturnList(userList);
		return resultList;
	}

	
	/**
	 * SNS 를 통하여 사용자 등록
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.CREATE)
	public String addUserSns(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		
		String userNo = null;
		
		//계정이 없으면 등록 로직
		if(usrUserInfoMapper.selectUserSns(vo) == null){
			//-- 사용자 키를 생성
			userNo = usrUserInfoMapper.selectKey();
			vo.setUserNo(userNo);
			vo.encryptJuminNo();
			//-- 사용자 정보 테이블에 저장
			usrUserInfoMapper.insert(vo);
			
			//-- 회원가입인 경우 RegNo와 ModNo가 비어 있음, 이경우 등록된 사용자 아이디로 교체....
			if(ValidationUtils.isEmpty(vo.getRegNo())) vo.setRegNo(vo.getUserNo());
			if(ValidationUtils.isEmpty(vo.getModNo())) vo.setModNo(vo.getUserNo());
			
			vo.setUserSts("U");
			//사용자 로그인 테이블에 저장 (다로 객체 생성하지 않음 UsrUserInfoVO extends UsrLoginVO)
			usrLoginMapper.insert(vo);
	
			UsrUserInfoChgHstyVO uichvo = new UsrUserInfoChgHstyVO();
			uichvo.setUserNo(vo.getUserNo());
			uichvo.setRegNo(vo.getRegNo());
			uichvo.setUserInfoChgDivCd(userInfoChgDivCd);
			uichvo.setUserInfoCts(vo.getString());
			usrUserInfoChgHstyMapper.insert(uichvo);
			
			// 권한 부여
			vo.setWwwAuthGrpCd("/MEMBER");
	
			String[] wwwAuth = StringUtil.split(vo.getWwwAuthGrpCd(),"/");
			for(int i=1; i < wwwAuth.length; i++) {
				UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
				uuagvo.setMenuType("HOME");
				uuagvo.setAuthGrpCd(wwwAuth[i]);
				uuagvo.setUserNo(vo.getUserNo());
				uuagvo.setRegNo(vo.getRegNo());
				uuagvo.setModNo(vo.getModNo());
				usrUserAuthGrpMapper.insert(uuagvo);
			}
		}
		
		return userNo;
	}
	
	/**
	 * 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.UPDATE)
	public String updateSnsDiv(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		
		UsrLoginVO ulvo = new UsrLoginVO();
		ulvo.setUserId(vo.getUserId());
		
		String snsDiv = StringUtil.nvl(vo.getSnsCode(), "");
		if(!snsDiv.equals("")){
			if(snsDiv.equals("K")){
				snsDiv = "KAKAO";
			}else if(snsDiv.equals("F")){
				snsDiv = "FACEBOOK";
			}else if(snsDiv.equals("N")){
				snsDiv ="NAVER";
			}else if(snsDiv.equals("G")){
				snsDiv ="GOOGLE";
			}else if(snsDiv.equals("D")){
				snsDiv ="DAUM";
			}
			ulvo.setSnsDiv(snsDiv);
			ulvo.setSnsKey(vo.getUserId());
		}
		
		String result = usrLoginMapper.updateSnsDiv(ulvo);
		return result;
	}
	
	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @param int
	 * @return  ProcessResultDTO
	 */
	@Override
	@HrdApiUsrUserInfo(Type.UPDATE)
	public int editSso(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception {
		UsrUserInfoVO uuvo = new UsrUserInfoVO();
		uuvo = usrUserInfoMapper.selectForSsoLogin(vo);
		vo.setUserNo(uuvo.getUserNo());
		int result = usrUserInfoMapper.update(vo);
		if(result<0)TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return result;
	}
	
	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @return AbstractResult
	 */
	@Override
	@HrdApiUsrUserInfo(Type.UPDATE)
	public int editUserInfo(UsrUserInfoVO vo) throws Exception {
		vo.encryptJuminNo();
		return usrUserInfoMapper.update(vo);
	}
	
	/**
	 * 사용자 아바타 정보 수정
	 * @param UsrUserInfoVO vo
	 * @return AbstractResult
	 */
	@Override
	public int editUserAvatar(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.updateAvatar(vo);
	}
	
	/**
	 * 사용자 등록 샘플 엑셀 파일 다운로드
	 * @param outputStream
	 * @param String
	 */
	@Override
	public void sampleExcelDownloadForUserAdd(OutputStream os, String orgCd) throws Exception {
		int rowNum = 0;
		
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet();
		
		wbook.setSheetName(0, "회원 일괄 등록");
		
		XSSFRow pageRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createPageTitleCell("회원 일괄 등록", pageRow, 0, 12);
		
		rowNum++;
		XSSFRow descriptionRow = sheet.createRow((short)rowNum);
		String description = "* 샘플 데이터와 같은 형식으로 샘플 파일에 작성하여 업로드해야 정상적으로 등록됩니다." + System.lineSeparator() 
				+ "* 아이디와 아메일은 중복 되지 않도록 주의하여 주십시오." + System.lineSeparator()
				+ "* 추가적인 사용자 정보의 등록 및 수정은 회원관리 메뉴에서 할 수 있습니다." + System.lineSeparator()
				+ "* 기업코드, 훈련생 구분코드, 비정규직 구분코드 등은 코드 목록 시트에서 확인 할 수 있습니다." + System.lineSeparator()
				+ "* 초기 비밀번호는 1234 입니다." ;
		POIExcelUtil.createMergeCell(description, descriptionRow, 0, 12, "left");
		descriptionRow.setHeight((short)(sheet.getDefaultRowHeight() * 6));
		rowNum++;
		rowNum++;
		
		XSSFRow titleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("아이디",titleColumnRow,0);
		POIExcelUtil.createTitleCell("이름",titleColumnRow,1);
		POIExcelUtil.createTitleCell("주민등록번호",titleColumnRow,2);
		POIExcelUtil.createTitleCell("휴대폰번호",titleColumnRow,3);
		POIExcelUtil.createTitleCell("전화번호",titleColumnRow,4);
		POIExcelUtil.createTitleCell("이메일",titleColumnRow,5);
		POIExcelUtil.createTitleCell("우편번호",titleColumnRow,6);
		POIExcelUtil.createTitleCell("주소",titleColumnRow,7);
		POIExcelUtil.createTitleCell("상세주소",titleColumnRow,8);
		POIExcelUtil.createTitleCell("기업코드",titleColumnRow,9);
		POIExcelUtil.createTitleCell("훈련생 구분코드",titleColumnRow,10);
		POIExcelUtil.createTitleCell("비정규직 구분코드",titleColumnRow,11);
		POIExcelUtil.createTitleCell("비용수급사업장번호",titleColumnRow,12);
		
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(8, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(9, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(10, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(11, sheet.getDefaultColumnWidth() * 500);
		sheet.setColumnWidth(12, sheet.getDefaultColumnWidth() * 500);
		
		rowNum++;
		XSSFRow contentRow = sheet.createRow((short)rowNum);
		
		POIExcelUtil.createContentCell("test1", contentRow, 0, "left");
		POIExcelUtil.createContentCell("홍길동", contentRow, 1, "left");
		POIExcelUtil.createContentCell("950101-1010101", contentRow, 2, "left");
		POIExcelUtil.createContentCell("010-1234-5678", contentRow, 3, "left");
		POIExcelUtil.createContentCell("", contentRow, 4, "left");
		POIExcelUtil.createContentCell("", contentRow, 5, "left");
		POIExcelUtil.createContentCell("", contentRow, 6, "left");
		POIExcelUtil.createContentCell("", contentRow, 7, "left");
		POIExcelUtil.createContentCell("", contentRow, 8, "left");
		POIExcelUtil.createContentCell("DEPT000260", contentRow, 9, "left");
		POIExcelUtil.createContentCell("002", contentRow, 10, "left");
		POIExcelUtil.createContentCell("000", contentRow, 11, "left");
		POIExcelUtil.createContentCell("000000000000", contentRow, 12, "left");
		
		
		sheet = wbook.createSheet();
		wbook.setSheetName(1, "코드 목록");
		
		rowNum = 0;
		XSSFRow codetitleColumnRow = sheet.createRow((short)rowNum);
		POIExcelUtil.createTitleCell("훈련생 구분코드",codetitleColumnRow,0);
		POIExcelUtil.createTitleCell("코드",codetitleColumnRow,1);
		POIExcelUtil.createTitleCell("비정규직 구분코드",codetitleColumnRow,3);
		POIExcelUtil.createTitleCell("코드",codetitleColumnRow,4);
		POIExcelUtil.createTitleCell("기업코드",codetitleColumnRow,6);
		POIExcelUtil.createTitleCell("코드",codetitleColumnRow,7);
		
		sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 700);
		sheet.setColumnWidth(7, sheet.getDefaultColumnWidth() * 700);
		
		List<SysCodeVO> stdDivCdList = sysCodeService.listCode("STD_DIV_CD").getReturnList();
		List<SysCodeVO> nonReguDivCdList = sysCodeService.listCode("NON_REGU_DIV_CD").getReturnList();
		
		UsrDeptInfoVO udivo = new UsrDeptInfoVO();
		udivo.setOrgCd(orgCd);
		udivo.setSortKey("DEPT_NM_ASC");
		List<UsrDeptInfoVO> deptList = usrDeptInfoMapper.list(udivo);
		
		rowNum = 1;
		for(SysCodeVO code : stdDivCdList) {
			contentRow = sheet.createRow(rowNum);
			POIExcelUtil.createContentCell(code.getCodeNm(), contentRow, 0, "center");
			POIExcelUtil.createContentCell(code.getCodeCd(), contentRow, 1, "center");
			rowNum++;
		}
		rowNum = 1;
		for(SysCodeVO code : nonReguDivCdList) {
			contentRow = sheet.getRow(rowNum);
			if(contentRow == null) {
				contentRow = sheet.createRow(rowNum);
			}
			POIExcelUtil.createContentCell(code.getCodeNm(), contentRow, 3, "center");
			POIExcelUtil.createContentCell(code.getCodeCd(), contentRow, 4, "center");
			rowNum++;
		}
		rowNum = 1;
		for(UsrDeptInfoVO dept : deptList) {
			contentRow = sheet.getRow(rowNum);
			if(contentRow == null) {
				contentRow = sheet.createRow(rowNum);
			}
			POIExcelUtil.createContentCell(dept.getDeptNm(), contentRow, 6, "center");
			POIExcelUtil.createContentCell(dept.getDeptCd(), contentRow, 7, "center");
			rowNum++;
		}
		wbook.write(os);
	}
	
	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrUserInfoVO viewForLoginCheck(UsrUserInfoVO vo) throws Exception {
		//SNS로그인
		if(ValidationUtils.isNotEmpty(vo.getSnsKey()) && ValidationUtils.isNotEmpty(vo.getSnsDiv())){
			vo = usrUserInfoMapper.selectForLoginCheck(vo);
			//sns 사용자는 패스워드가 없으므로 로직을 통과하기위해 강제로 같게 만듬
			vo.setUserPass("0");
			vo.setEncUserPass("0");
		}else{
			vo = usrUserInfoMapper.selectForLoginCheck(vo);
			
			if(ValidationUtils.isEmpty(vo)) {
					throw new ServiceProcessException();
			}
		}
		this.getAuthGrp(vo);
		return vo;
	}

	/**
	 * 이메일 중복 체크
	 * @param UsrUserInfoVO
	 * @return String
	 */
	@Override
	public String emailCheck(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.selectEmailCheck(vo);
	}

	@Override
	public String selectExceptionIdCheck(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.selectExceptionIdCheck(vo);
	}
	
	@Override
	public int initVerify(UsrUserInfoVO vo) throws Exception {
		usrUserInfoMapper.updateNiceCheckInfo(vo);
		return usrLoginMapper.updatePassAndVerified(vo);
	}
	
	/**
	 * api용 회원정보 조회
	 * @param UsrUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> selectUserInfoApi(UsrUserInfoVO vo) throws Exception {
		return usrUserInfoMapper.selectUserInfoApi(vo);
	}

	/**
	 * 소셜 회원가입 체크
	 */
	@Override
	public int oauthCheckId(Map<String, Object> paramMap) throws Exception {
		return usrUserInfoMapper.oauthCheckId(paramMap);
	}

	/**
	 * 소셜 로그인
	 */
	@Override
	public UsrUserInfoVO oauthLogin(Map<String, Object> paramMap) throws Exception {
		return usrUserInfoMapper.oauthLogin(paramMap);
	}
	
	/**
	 * 강사 ide 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<UsrUserInfoVO> editTeacherUrl(UsrUserInfoVO vo) throws Exception {
		ProcessResultVO<UsrUserInfoVO> resultVO = new ProcessResultVO<UsrUserInfoVO>();
		int iResult = 0;
		
		try {
			
			if(vo.getUserNo() != null) {
				for(int i=0; i < vo.getUserNos().length; i++) {
					UsrUserInfoVO pUsrUserInfoVO = new UsrUserInfoVO();
					
					pUsrUserInfoVO.setUserNo(vo.getUserNos()[i]);
					pUsrUserInfoVO.setIdeUrl(vo.getIdeUrls()[i]);
					iResult += usrUserInfoMapper.updateTeacherIde(pUsrUserInfoVO);
					
				}
			}
			
			if(iResult > 0) {
				resultVO.setReturnVO(vo);
				resultVO.setResultSuccess();
			}
			
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * [HRD] 강사 >IDE엑셀업로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	@Transactional
	public ProcessResultVO<UsrUserInfoVO> addTeacherIdeUrlExcel(UsrUserInfoVO vo, String fileName, String filePath) {

		ProcessResultVO<UsrUserInfoVO> resultVO = new ProcessResultVO<>(new UsrUserInfoVO());
		resultVO.setResultSuccess();
		
		XSSFWorkbook workbook	= null;
		XSSFSheet sheet = null;
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath + "/" + fileName);
			workbook =  new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (NotOfficeXmlFileException noxfe) {
			throw new ServiceProcessException("엑셀파일만 업로드 가능합니다.");
		} catch (IOException ex2) {
			throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
		} finally{
			try {
				if (fis != null) {	fis.close(); }
			} catch (Exception e) {
				throw new ServiceProcessException("엑셀파일 읽기 실패하였습니다.");
			}
		}
		
		XSSFRow dfltRow = sheet.getRow(1);
		int cellCount = dfltRow.getPhysicalNumberOfCells();
		
		int rowNum = 0;
		int rows = sheet.getPhysicalNumberOfRows();//행 갯수
		
		if(rows == 2) {
			throw new ServiceProcessException("3번째 줄부터 정보 입력 바랍니다.\n혹은 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");//1번째 줄 : 설명 , 2번째 줄 : 제목줄 , 3번째 줄부터 입력
		}
		
		for(int rowIndex = 2; rowIndex < rows; rowIndex++) {
			
			XSSFRow row=sheet.getRow(rowIndex);
			cellCount = row.getPhysicalNumberOfCells();
			
			if(cellCount < 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n회원아이디, IDE URL은 필수 값입니다.");
			} else if(cellCount > 2) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n엑셀 업로드는 회원아이디, IDE URL만 입력가능합니다. 그 외 다른 내용, 공백을 입력했거나 다른 엑셀파일을 업로드하는게 아닌지 확인바랍니다.");
			}
			
			String userId = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(0)));
			String ideUrl = StringUtil.nvl(POIExcelUtil.getCellValue(row.getCell(1)));

			if("".equals(userId) || "".equals(ideUrl)) {
				throw new ServiceProcessException((rowIndex + 1) + "라인\n회원 아이디, IDE URL은 필수 값입니다.");
			}
			
			//아이디 체크
			vo.setUserId(userId);
			vo.setUserSts("U"); // 사용중인 사용자만 가져오기 (U, N)
			vo.setSearchAuthGrp("TEACHER"); // 강사체크
			UsrUserInfoVO resultInfoVO = usrUserInfoMapper.selectUserNo(vo);
			
			if(resultInfoVO == null) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId + ")\n 사용중인 상태의 회원 정보가 없습니다. 회원 정보(회원 가입 여부, 상태 , 강사여부 등)를 확인바랍니다.");
			}
			
			//IDE URL 유무체크
			StudentVO sVo = new StudentVO();
			sVo.setIdeUrl(ideUrl);
			int result = studentMapper.selectCreIdeUrl(sVo);
			
			if(result == 0) {
				throw new ServiceProcessException((rowIndex + 1) + "라인(아이디 : " + userId + ")\n 해당 IDE URL은 없는 주소입니다. 확인바랍니다.");
			}

			//IDE URL 업데이트
			UsrUserInfoVO uVO = new UsrUserInfoVO();
			uVO.setUserNo(resultInfoVO.getUserNo());
			uVO.setIdeUrl(ideUrl);
			
			usrUserInfoMapper.addTeacherIdeUrl(uVO);
			resultVO.getReturnVO();
		}

		return resultVO;
	}

}
