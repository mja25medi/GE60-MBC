package egovframework.edutrack.modules.course.createcourseteacher.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



@Service("createCourseTeacherService")
public class CreteCourseTeacherServiceImpl extends EgovAbstractServiceImpl implements CreateCourseTeacherService {
	
	/** Mapper */
	@Resource(name="createCourseTeacherMapper")
	private CreateCourseTeacherMapper createCourseTeacherMapper;
	
	/**
	 * 개설 과정 강사 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<TeacherVO> listTeacher(TeacherVO iTeacherVO) throws Exception {
		ProcessResultListVO<TeacherVO> resultList = new ProcessResultListVO<TeacherVO>();
		try {
			List<TeacherVO> returnList = createCourseTeacherMapper.listTeacher(iTeacherVO);
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
	 * 개설 과정 강사 정보 조회
	 * 
	 * @return ProcessReslutVO
	 */
	@Override
	public ProcessResultVO<TeacherVO> viewTeacher(TeacherVO iTeacherVO) throws Exception {
		
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			TeacherVO returnVO = createCourseTeacherMapper.selectTeacher(iTeacherVO);
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
	 * 회차 강사 등록
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TeacherVO> addTeacher(TeacherVO iTeacherVO) throws Exception {
		
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			createCourseTeacherMapper.insertTeacher(iTeacherVO);
			resultVO.setReturnVO(iTeacherVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 개설 과정 강사 
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TeacherVO> editTeacher(TeacherVO iTeacherVO) throws Exception {
		
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			createCourseTeacherMapper.updateTeacher(iTeacherVO);
			resultVO.setReturnVO(iTeacherVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 과정 강사의 순서를 변경하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortTeacher(TeacherVO VO) throws Exception {

		String[] itemList = StringUtil.split(VO.getUserNo(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<TeacherVO> itemArray = createCourseTeacherMapper.listTeacher(VO);

		for (TeacherVO tchVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(tchVO.getUserNo().equals(itemList[order])) {
					tchVO.setTchOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		createCourseTeacherMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}
	

	/**
	 * 개설 과정 강사 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TeacherVO> deleteTeacher(TeacherVO iTeacherVO) throws Exception {
		
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			createCourseTeacherMapper.deleteTeacher(iTeacherVO);
			resultVO.setReturnVO(iTeacherVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
		
	}
	
	/**
	 * 개설 과정 강사 인지 확인
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TeacherVO> isTeacher(TeacherVO iTeacherVO) throws Exception {
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			TeacherVO returnVO = createCourseTeacherMapper.isTeacher(iTeacherVO);
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
	 * 강사/튜터의 권한이 있는 사용자중 과정 튜터/강사로 등록이 되어 있지 않은 
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listSearch(TeacherVO VO) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
			List<UsrUserInfoVO> returnList = createCourseTeacherMapper.listSearch(VO);
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
	 * 강사 튜터 권한이 있는 모든 사용자 조회
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	@Override
	public ProcessResultListVO<UsrUserInfoVO> listAllSearch(TeacherVO VO) throws Exception {
		ProcessResultListVO<UsrUserInfoVO> resultList = new ProcessResultListVO<UsrUserInfoVO>();
		try {
			List<UsrUserInfoVO> returnList = createCourseTeacherMapper.listAllSearch(VO);
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
	public ProcessResultVO<TeacherVO> updateCrsCreTeacher(TeacherVO teacherVO) {
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			createCourseTeacherMapper.updateCrsCreTeacher(teacherVO);
			resultVO.setReturnVO(teacherVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	@Override
	public ProcessResultVO<TeacherVO> deleteTeacherAll(TeacherVO vo) {
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		try {
			createCourseTeacherMapper.deleteTeacherAll(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
}
