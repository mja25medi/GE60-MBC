package egovframework.edutrack.web.lecture.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/user")
public class UserLectureController extends GenericController{

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService	usrUserInfoService;

	@Autowired @Qualifier("usrLoginService")
	private UsrLoginService		usrLoginService;

	@Autowired @Qualifier("usrUserAuthGrpService")
	private UsrUserAuthGrpService usrUserAuthGrpService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService		sysCfgService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

//	@Autowired
//	private IMessageService		messageService;

	/**
	 * 회원정보수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value="/editUserForm")
	public String editUserForm( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		
		vo.setUserNo(userNo);

		UsrUserInfoVO resultVO = usrUserInfoService.view(vo);
		vo = resultVO;
		
		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(vo.getUserNo());

		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(uagVO).getReturnList();

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType()))
				wwwAuthGrpCd += "/"+iagVO.getAuthGrpCd();
			if("ADMIN".equals(iagVO.getMenuType()))
				adminAuthGrpCd += "/"+iagVO.getAuthGrpCd();
			if("MANAGE".equals(iagVO.getMenuType()))
				manageAuthGrpCd += "/"+iagVO.getAuthGrpCd();
		}
		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);

		//-- 전화번호 및 이메일
		String[] homePhone = StringUtil.split(StringUtil.nvl(vo.getHomePhoneno(),""), "-");
    	String[] mobileNo = StringUtil.split(StringUtil.nvl(vo.getMobileNo(),""), "-");
		String[] email = StringUtil.split(StringUtil.nvl(vo.getEmail(),""), "@");
		String[] compPhone = StringUtil.split(StringUtil.nvl(vo.getCompPhoneno(),""), "-");
		String[] compFax = StringUtil.split(StringUtil.nvl(vo.getCompFaxNo(),""), "-");

		if(!"".equals(StringUtil.nvl(vo.getHomePhoneno(),"")) && homePhone.length > 0) {
			request.setAttribute("homePhonenoF", homePhone[0]);
			request.setAttribute("homePhonenoM", homePhone[1]);
			request.setAttribute("homePhonenoL", homePhone[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getMobileNo(),"")) && mobileNo.length > 0) {
			request.setAttribute("mobileNoF", mobileNo[0]);
			request.setAttribute("mobileNoM", mobileNo[1]);
			request.setAttribute("mobileNoL", mobileNo[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getEmail(),"")) && email.length > 0) {
			request.setAttribute("emailF", email[0]);
			request.setAttribute("emailL", email[1]);
		}
		if(!"".equals(StringUtil.nvl(vo.getCompPhoneno(),"")) && compPhone.length > 0) {
			request.setAttribute("compPhonenoF", compPhone[0]);
			request.setAttribute("compPhonenoM", compPhone[1]);
			request.setAttribute("compPhonenoL", compPhone[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getCompFaxNo(),"")) && compFax.length > 0) {
			request.setAttribute("compFaxNoF", compFax[0]);
			request.setAttribute("compFaxNoM", compFax[1]);
			request.setAttribute("compFaxNoL", compFax[2]);
		}

		String curYear = DateTimeUtil.getYear();

		//-- 시작년도 가져오기
		SysCfgVO sysCfgVO = new SysCfgVO();
		sysCfgVO.setCfgCtgrCd("JOINUS");
		sysCfgVO.setCfgCd("BIRTHYEAR");
		sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		request.setAttribute("yearList", yearList);

		List<SysCodeVO> emailList = sysCodeMemService.getSysCodeList("EMAILSELECT");
		request.setAttribute("emailList", emailList);

		List<SysCodeVO> phoneList = sysCodeMemService.getSysCodeList("PHONEAREA");
		request.setAttribute("phoneList", phoneList);

		List<SysCodeVO> mobileList = sysCodeMemService.getSysCodeList("MOBILEAREA");
		request.setAttribute("mobileList", mobileList);


    	//메세지 초기화
    	//vo.setMessage("");
	    request.setAttribute("usrUserInfoVO", vo);
	    request.setAttribute("gubun", "E");
		return "home/lecture/user/user_info_edit_pop";
	}

	/**
	 * 회원정보수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editUserForm2")
	public String editUserForm2( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);

		UsrUserInfoVO resultVO = usrUserInfoService.view(vo);
		vo = resultVO;

		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(vo.getUserNo());

		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(uagVO).getReturnList();

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType()))
				wwwAuthGrpCd += "/"+iagVO.getAuthGrpCd();
			if("ADMIN".equals(iagVO.getMenuType()))
				adminAuthGrpCd += "/"+iagVO.getAuthGrpCd();
			if("MANAGE".equals(iagVO.getMenuType()))
				manageAuthGrpCd += "/"+iagVO.getAuthGrpCd();
		}
		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);

		//-- 전화번호 및 이메일
		String[] homePhone = StringUtil.split(StringUtil.nvl(vo.getHomePhoneno(),""), "-");
    	String[] mobileNo = StringUtil.split(StringUtil.nvl(vo.getMobileNo(),""), "-");
		String[] email = StringUtil.split(StringUtil.nvl(vo.getEmail(),""), "@");
		String[] compPhone = StringUtil.split(StringUtil.nvl(vo.getCompPhoneno(),""), "-");
		String[] compFax = StringUtil.split(StringUtil.nvl(vo.getCompFaxNo(),""), "-");

		if(!"".equals(StringUtil.nvl(vo.getHomePhoneno(),"")) && homePhone.length > 0) {
			request.setAttribute("homePhonenoF", homePhone[0]);
			request.setAttribute("homePhonenoM", homePhone[1]);
			request.setAttribute("homePhonenoL", homePhone[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getMobileNo(),"")) && mobileNo.length > 0) {
			request.setAttribute("mobileNoF", mobileNo[0]);
			request.setAttribute("mobileNoM", mobileNo[1]);
			request.setAttribute("mobileNoL", mobileNo[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getEmail(),"")) && email.length > 0) {
			request.setAttribute("emailF", email[0]);
			request.setAttribute("emailL", email[1]);
		}
		if(!"".equals(StringUtil.nvl(vo.getCompPhoneno(),"")) && compPhone.length > 0) {
			request.setAttribute("compPhonenoF", compPhone[0]);
			request.setAttribute("compPhonenoM", compPhone[1]);
			request.setAttribute("compPhonenoL", compPhone[2]);
		}
		if(!"".equals(StringUtil.nvl(vo.getCompFaxNo(),"")) && compFax.length > 0) {
			request.setAttribute("compFaxNoF", compFax[0]);
			request.setAttribute("compFaxNoM", compFax[1]);
			request.setAttribute("compFaxNoL", compFax[2]);
		}

		String curYear = DateTimeUtil.getYear();

		//-- 시작년도 가져오기
		SysCfgVO sysCfgVO = new SysCfgVO();
		sysCfgVO.setCfgCtgrCd("JOINUS");
		sysCfgVO.setCfgCd("BIRTHYEAR");
		sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		request.setAttribute("yearList", yearList);

		List<SysCodeVO> emailList = sysCodeMemService.getSysCodeList("EMAILSELECT");
		request.setAttribute("emailList", emailList);

		List<SysCodeVO> phoneList = sysCodeMemService.getSysCodeList("PHONEAREA");
		request.setAttribute("phoneList", phoneList);

		List<SysCodeVO> mobileList = sysCodeMemService.getSysCodeList("MOBILEAREA");
		request.setAttribute("mobileList", mobileList);


    	//메세지 초기화
    	//userInfoVO.setMessage("");
	    request.setAttribute("usrUserInfoVO", vo);
	    request.setAttribute("gubun", "E");
		return "home/lecture/user/user_info_edit";
	}

	/**
	 * 회원 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editUser")
	public String editUser( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request); //세션에서 logNO 롤 받아온다.

		vo.setUserNo(userNo);

		if(ValidationUtils.isEmpty(vo.getEmailRecv())) vo.setEmailRecv("Y");
		if(ValidationUtils.isEmpty(vo.getSmsRecv())) vo.setSmsRecv("Y");
		if(ValidationUtils.isEmpty(vo.getMsgRecv())) vo.setMsgRecv("Y");

		// 디비 정보 추출
		UsrUserInfoVO returnVO = usrUserInfoService.view(vo);


		// 세션의 정보를 userInfoVO에 병합시킨다.
		BeanUtils.mergeBean(returnVO,vo); //이전에 등록된데이터에 신규로 추가된 값을 merge 시켜서 등록한다.

		return JsonUtil
				.responseJson(response, usrUserInfoService.edit(returnVO,"IE"));
	}

	/**
	 * 사용자 암호 초기화
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/resetPass")
	public String resetPass( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String[] userNoArr = StringUtil.split(request.getParameter("userNos"),",");

		UsrLoginVO loginVO = new UsrLoginVO();

		int errCnt = 0;
		for(int i=0; i < userNoArr.length; i++) {
			String userNo = userNoArr[i];
			//-- 사용자 정보를 받아온다.
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(userNo);
			userInfoVO = usrUserInfoService.view(userInfoVO);

			UsrLoginVO lgVO = new UsrLoginVO();
			lgVO.setUserNo(userNo);
			lgVO.setModNo(UserBroker.getUserNo(request));

			//-- 아이디와 동일하게 변경.
			lgVO.setUserPass(userInfoVO.getUserId());

			String newUserPass = lgVO.getUserPass();

			try {
				usrLoginService.editPass(lgVO);
			} catch(Exception e) {
				errCnt++;
			}

			String emailName = sysCfgService.getValue("MESSAGE", "EMAIL_NAME");
			String emailAddr = sysCfgService.getValue("MESSAGE", "EMAIL_ADDR");

//			MessageTransVO trans = new MessageTransVO();
//
//			trans.setRecvAddr(userInfoVO.getEmail());
//			trans.setRecvNm(userInfoVO.getUserNm());
//			trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y
//
//			MessageVO message = trans.getMessage();
//			message.setSendMenuCd(UserBroker.getMenuCode(request));
//			message.setTitle("안녕하세요 "+emailName+"입니다.");
//			message.setCts("안녕하십니까? "+emailName+"입니다.\n\n "+userInfoVO.getUserId()+"회원님의 PASSWORD는 "
//							+newUserPass+"입니다. \n\n 현재 비밀번호는 임시 비밀번호 이니 로그인후 반드시 변경하여 주시기 바랍니다. .\n\n\n\n\n\n\n\n\n\n\n * 본메일은 발신전용입니다.");
//			message.setMsgDivCd("EMAIL");
//			message.setSendAddr(emailAddr);
//			message.setSendNm(emailName);
//
//			message.addSubTrans(trans);
//
//			try{
//				messageService.addMessageWithSend(message);
//			}catch (MessageNotificationException ex) {
//				errCnt++;
//			}
		}

		if(errCnt > 0) {
			loginVO.setMessage("ERROR");
			loginVO.setMessageDetail("비밀번호 초기화에 실패하였습니다.");
		} else {
			loginVO.setMessage("OK");
			loginVO.setMessageDetail("선택한 사용자들의 비밀번호를  초기화 하였습니다.");
		}
		return JsonUtil.responseJson(response, loginVO);
	}
}
