package egovframework.edutrack.modules.user.info.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeLangVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.config.service.impl.OrgUserInfoCfgMapper;
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
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoChgHstyVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
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
@Service("usrUserAuthGrpService")
public class UsrUserAuthGrpServiceImpl 
	extends EgovAbstractServiceImpl implements UsrUserAuthGrpService {

	/** Mapper */
	   @Resource(name="usrUserAuthGrpMapper")
	    private UsrUserAuthGrpMapper 		usrUserAuthGrpMapper;

    /**
	 * 사용자 정보 전체 목록 조회
	 * @param UsrUserInfoVO vo
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UsrUserAuthGrpVO> list(UsrUserAuthGrpVO vo) throws Exception {
		ProcessResultListVO<UsrUserAuthGrpVO> resultList = new ProcessResultListVO<UsrUserAuthGrpVO>();
		try {
			List<UsrUserAuthGrpVO> userList = usrUserAuthGrpMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(userList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

 
}
