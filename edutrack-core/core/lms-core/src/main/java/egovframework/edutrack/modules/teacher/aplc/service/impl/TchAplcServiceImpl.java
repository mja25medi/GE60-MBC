package egovframework.edutrack.modules.teacher.aplc.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.edutrack.modules.teacher.aplc.service.TchAplcService;
import egovframework.edutrack.modules.teacher.aplc.service.TchAplcVO;
import egovframework.edutrack.modules.teacher.field.service.TchFieldVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * <strong>강사 - 강사 코드</strong> 영역 서비스 구현체.<br>
 * 트랜잭션의 경계
 * @author SungKook
 */
@Service("tchAplcService")
public class TchAplcServiceImpl extends EgovAbstractServiceImpl implements TchAplcService {

	
	/** Mapper */
	@Resource(name="tchAplcMapper")
	private TchAplcMapper 		tchAplcMapper;

	@Resource(name="usrUserInfoMapper")
	private UsrUserInfoMapper usrUserInfoMapper;

	/**
	 * 강사 신청 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchAplcVO> listTeacherAplc(TchAplcVO teacherAplcVO, int curPage) throws Exception {
		ProcessResultListVO<TchAplcVO> resultList = new ProcessResultListVO<TchAplcVO>();
		
		try {
			List<TchAplcVO> returnList = tchAplcMapper.listTeacherAplc(teacherAplcVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	/**
	 *강사 신청 목록 조회(엑셀 download)
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchAplcVO> listTeacherAplcResult(TchAplcVO teacherAplcVO) throws Exception {
		
		ProcessResultListVO<TchAplcVO> resultList = new ProcessResultListVO<TchAplcVO>(); 
		try {
			List<TchAplcVO> returnList =  tchAplcMapper.listTeacherAplcResult(teacherAplcVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}

	/**
	 * 강사신청
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchAplcVO> addTeacherAplc(TchAplcVO teacherAplcVO) throws Exception{
		teacherAplcVO.setAprvSts("A");
		tchAplcMapper.insertTeacherAplc(teacherAplcVO);
		return ProcessResultVO.success();
	}

	/**
	 * 강사승인
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchAplcVO> editTeacherAplc(TchAplcVO teacherAplcVO) throws Exception{

		String[] userList = teacherAplcVO.getUserList();

		for(int i=0; i< userList.length;i++){

			teacherAplcVO.setUserNo(userList[i]);
			teacherAplcVO.setAprvSts("R");
			tchAplcMapper.updateTeacherAplc(teacherAplcVO);
			teacherAplcVO.setTchCtgrCd("T02");
			tchAplcMapper.insertTeacherInfo(teacherAplcVO);

			//강사권한 변경
			UsrUserInfoVO userDto = new UsrUserInfoVO();
			userDto.setUserNo(teacherAplcVO.getUserNo());
			userDto.setWwwAuthGrpCd("TCH02"); //외부강사권한
			//userInfoDao.updateTeacherGrp(userDto);

		}

		return ProcessResultVO.success();
	}


	/**
	 * 강사승인취소
	 * @param userSn 삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchAplcVO> editTeacherCancel(TchAplcVO teacherAplcVO) throws Exception{

		String[] userList = teacherAplcVO.getUserList();

		for(int i=0; i< userList.length;i++){

			teacherAplcVO.setUserNo(userList[i]);
			teacherAplcVO.setAprvSts("C");

			tchAplcMapper.updateTeacherAplc(teacherAplcVO);
			tchAplcMapper.deleteTeacherInfo(teacherAplcVO);

			//강사권한 변경
			UsrUserInfoVO userDto = new UsrUserInfoVO();
			userDto.setUserNo(teacherAplcVO.getUserNo());
			userDto.setWwwAuthGrpCd("MEMBER");
			//userDao.updateTeacherGrp(userDto);

		}

		return  ProcessResultVO.success();
	}

	/**
	 * 강사신청 유무확인
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchAplcVO> selectProfReg(TchAplcVO teacherAplcVO) throws Exception{
		ProcessResultVO<TchAplcVO> resultVO = new ProcessResultVO<TchAplcVO>(); 
		try {
			TchAplcVO returnVO =  tchAplcMapper.selectProfReg(teacherAplcVO);
			resultVO.setResult(1);
			resultVO.setReturnVO(returnVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
		
	}

	/**
	 * 강사신청 엑셀다운로드
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public void listTeacherAplcExcelDownload(TchAplcVO teacherAplcVO, OutputStream os) throws Exception {

		List<TchAplcVO> resultList = this.listTeacherAplcResult(teacherAplcVO).getReturnList();

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("강사신청관리", 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum,"left","강사신청 리스트 ")); //1열
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, 11, 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);

			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"번호"));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,"이름"));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,"아이디"));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,"성별"));
			sheet.addCell(ExcelUtil.createHeader(4,rowNum,"핸드폰번호"));
			sheet.addCell(ExcelUtil.createHeader(5,rowNum,"이메일"));
			sheet.addCell(ExcelUtil.createHeader(6,rowNum,"신청구분"));
			sheet.addCell(ExcelUtil.createHeader(7,rowNum,"강사신청일"));
			sheet.addCell(ExcelUtil.createHeader(8,rowNum,"신청상태"));

			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 30);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 15);
			sheet.setColumnView(8, 15);

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 400);

			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				TchAplcVO aplcVO = resultList.get(i);

				sheet.addCell(ExcelUtil.createNumber(0,rowNum,"left",i+1));
				sheet.addCell(ExcelUtil.createText(1,rowNum,"left",aplcVO.getUserNm()));
				sheet.addCell(ExcelUtil.createText(2,rowNum,"left",aplcVO.getUserId()));
				sheet.addCell(ExcelUtil.createText(3,rowNum,"left",aplcVO.getSex()));
				sheet.addCell(ExcelUtil.createText(4,rowNum,"left",aplcVO.getMobileNo()));
				sheet.addCell(ExcelUtil.createText(5,rowNum,"left",aplcVO.getEmail()));
				sheet.addCell(ExcelUtil.createText(6,rowNum,"left",aplcVO.getTchDivCdNm()));
				sheet.addCell(ExcelUtil.createText(7,rowNum,"left",aplcVO.getAplcDt()));
				sheet.addCell(ExcelUtil.createText(8,rowNum,"left",aplcVO.getAprvStsNm()));
				sheet.setRowView(rowNum, 300);
			}

			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","강사신청 정보가 존재하지 않습니다.")); //1열
				sheet.mergeCells(0, 2, 11, 0);
				sheet.setRowView(0, 500);
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}
}
