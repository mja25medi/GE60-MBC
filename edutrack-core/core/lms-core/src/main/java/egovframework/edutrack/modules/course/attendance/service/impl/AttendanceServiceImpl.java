package egovframework.edutrack.modules.course.attendance.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.collections.ListUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.attendance.service.AttendanceService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.impl.CreateCourseMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service("attendanceService")
public class AttendanceServiceImpl extends EgovAbstractServiceImpl implements AttendanceService {
	
	/** Mapper */
	@Resource(name="attendanceMapper")
	private AttendanceMapper 		attendanceMapper;

	@Override
	public void insertAttendDttm(AttendanceVO avo) throws Exception{
		
	}

	/**
	 * 출석표 조회
	 *
	 * @return  ProcessResultListVO
	 */

	@Override
	public ProcessResultListVO<AttendanceVO> listAttendance(AttendanceVO vo) throws Exception {
		ProcessResultListVO<AttendanceVO> resultList = new ProcessResultListVO<AttendanceVO>();
		try {
			List<AttendanceVO> returnList = attendanceMapper.listAttendance(vo);
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
	public ProcessResultListVO<AttendanceVO> listPeriodMonDay(AttendanceVO vo) throws Exception {
		ProcessResultListVO<AttendanceVO> resultList = new ProcessResultListVO<AttendanceVO>();
		try {
			List<AttendanceVO> returnList = attendanceMapper.listPeriodMonDay(vo);
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
	public String periodDateStr(String attendDttm) throws Exception {
		String returnString = attendanceMapper.periodDateStr(attendDttm);
		return returnString;
	}

	/**
	 * 출석 기간 조회
	 *
	 * @return  ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<AttendanceVO> listPeriod(AttendanceVO vo) throws Exception {
		ProcessResultListVO<AttendanceVO> resultList = new ProcessResultListVO<AttendanceVO>();
		try {
			List<AttendanceVO> returnList = attendanceMapper.listPeriod(vo);
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
	 * 유저 상세 출석 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<AttendanceVO> viewAttend(AttendanceVO vo) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		try {
			AttendanceVO returnVO = attendanceMapper.viewAttend(vo);
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
	 * 출석 수정 로그 조회
	 *
	 * @return  ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<AttendanceVO> selectUpdateLog(AttendanceVO vo) throws Exception {
		ProcessResultListVO<AttendanceVO> resultList = new ProcessResultListVO<AttendanceVO>();
		try {
			List<AttendanceVO> returnList = attendanceMapper.selectUpdateLog(vo);
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
	 * 유저 출석 상태 변경
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<AttendanceVO> updateAttendStat(AttendanceVO vo) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		
		AttendanceVO exVO = attendanceMapper.viewAttend(vo);
		//datetime형식을 맞추기 위해 추가
		vo.setAttendDttm(vo.getAttendDttm()+"000001");
		//수정 내역 있을 시 로그 등록, null값일시 공백 치환
		if(!vo.getClassStat1().equals(null2void(exVO.getClassStat1()))) {
			vo.setClassTime("1");
			vo.setClassStat(vo.getClassStat1());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat2().equals(null2void(exVO.getClassStat2()))) {
			vo.setClassTime("2");
			vo.setClassStat(vo.getClassStat2());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat3().equals(null2void(exVO.getClassStat3()))) {
			vo.setClassTime("3");
			vo.setClassStat(vo.getClassStat3());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat4().equals(null2void(exVO.getClassStat4()))) {
			vo.setClassTime("4");
			vo.setClassStat(vo.getClassStat4());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat5().equals(null2void(exVO.getClassStat5()))) {
			vo.setClassTime("5");
			vo.setClassStat(vo.getClassStat5());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat6().equals(null2void(exVO.getClassStat6()))) {
			vo.setClassTime("6");
			vo.setClassStat(vo.getClassStat6());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat7().equals(null2void(exVO.getClassStat7()))) {
			vo.setClassTime("7");
			vo.setClassStat(vo.getClassStat7());
			attendanceMapper.insertUpdateLog(vo);
		}
		if(!vo.getClassStat8().equals(null2void(exVO.getClassStat8()))) {
			vo.setClassTime("8");
			vo.setClassStat(vo.getClassStat8());
			attendanceMapper.insertUpdateLog(vo);
		}
		
		attendanceMapper.updateAttendStat(vo);
		resultVO.setReturnVO(vo);
		resultVO.setResultSuccess();
		
		return resultVO;
	}
	
	public static String null2void(Object param) {
		String str = new String();
		if (param == null) {
		   return "";
		}
		if (param instanceof String) {
		   str = (String) param;
		} else if (param instanceof String[]) {
		   str = ((String[]) param)[0];
		} else if (param instanceof Date) {
		   str = ((Date)param).toString();
		} else {
		   str = String.valueOf(param);
		}
		if (str.equals("")) {
		   return "";
		} else {
		   return str.trim();
		}
	}

	/**
	 * 입실하기(출석)
	 */
	@Override
	public ProcessResultVO<AttendanceVO> enterClass(AttendanceVO vo) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		
		AttendanceVO exVO = attendanceMapper.viewAttend(vo);
		if(exVO.getEnterFlag() == null  || !exVO.getEnterFlag().equals("E")) {
			vo.setEnterTime(DateTimeUtil.getDateTime().toString());
			String enterTime = vo.getEnterTime().substring(8);
			if(enterTime.compareTo("091000") > 0) {
				vo.setClassStat1("T");
			} 
			if (enterTime.compareTo("100000") > 0) {
				vo.setClassStat2("T");
			} 
			if (enterTime.compareTo("110000") > 0) {
				vo.setClassStat3("T");
			}
			if (enterTime.compareTo("130000") > 0) {
				vo.setClassStat4("T");
			}
			if (enterTime.compareTo("140000") > 0) {
				vo.setClassStat5("T");
			}
			if (enterTime.compareTo("150000") > 0) {
				vo.setClassStat6("T");
			}
			if (enterTime.compareTo("160000") > 0) {
				vo.setClassStat7("T");
			}
			if (enterTime.compareTo("170000") > 0) {
				vo.setClassStat8("T");
			}
			vo.setEnterFlag("E");
			attendanceMapper.enterClass(vo);
			resultVO.setResult(1);
			resultVO.setMessage("입실 처리되었습니다");
		} else {
			resultVO.setResult(-1);
			resultVO.setMessage("이미 처리되었습니다");
		}
		return resultVO;
	}

	/**
	 * 퇴실하기(출석상태 코드 일괄 업데이트)
	 */
	@Override
	public ProcessResultVO<AttendanceVO> quitClass(AttendanceVO vo) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		AttendanceVO exVO = attendanceMapper.viewAttend(vo);
		
		if(exVO.getEnterFlag().equals("E")) {
			vo.setQuitTime(DateTimeUtil.getDateTime().toString());
			vo.setEnterFlag("Q");
			
			if(!exVO.getClassStat1().equals(null)) {
				vo.setClassStat1(exVO.getClassStat1());
			} else {
				vo.setClassStat1("S");
			}if(!exVO.getClassStat2().equals(null)) {
				vo.setClassStat2(exVO.getClassStat2());
			} else {
				vo.setClassStat2("S");
			}
			if(!exVO.getClassStat3().equals(null)) {
				vo.setClassStat3(exVO.getClassStat3());
			} else {
				vo.setClassStat3("S");
			}
			if(!exVO.getClassStat4().equals(null)) {
				vo.setClassStat4(exVO.getClassStat4());
			} else {
				vo.setClassStat4("S");
			}
			if(!exVO.getClassStat5().equals(null)) {
				vo.setClassStat5(exVO.getClassStat5());
			} else {
				vo.setClassStat5("S");
			}
			if(!exVO.getClassStat6().equals(null)) {
				vo.setClassStat6(exVO.getClassStat6());
			} else {
				vo.setClassStat6("S");
			}
			if(!exVO.getClassStat7().equals(null)) {
				vo.setClassStat7(exVO.getClassStat7());
			} else {
				vo.setClassStat7("S");
			}
			if(!exVO.getClassStat8().equals(null)) {
				vo.setClassStat8(exVO.getClassStat8());
			} else {
				vo.setClassStat8("S");
			}
			
			attendanceMapper.quitClass(vo);
			resultVO.setResult(1);
			resultVO.setMessage("퇴실 처리되었습니다");
		} else {
			resultVO.setResult(-1);
			resultVO.setMessage("처리할 수 없습니다. 관리자에 문의 바랍니다");
		}
		return resultVO;
	}

	/**
	 * 조퇴
	 * 출석 시 출석한 교시의 데이터만 기록하기 때문에 데이터가 외출이나 지각데이터가 있는 경우가 아니면 S출석으로 기록한다
	 */
	@Override
	public ProcessResultVO<AttendanceVO> leftClass(AttendanceVO vo, int leftTime) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		AttendanceVO exVO = attendanceMapper.viewAttend(vo);
		if(exVO.getEnterFlag().equals("E")) {
			vo.setQuitTime(DateTimeUtil.getDateTime().toString());
			vo.setEnterFlag("Q");
				switch (leftTime) {
				case 9:{
					vo.setClassStat1("L");
					vo.setClassStat2("L");
					vo.setClassStat3("L");
					vo.setClassStat4("L");
					vo.setClassStat5("L");
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 10:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}
					vo.setClassStat2("L");
					vo.setClassStat3("L");
					vo.setClassStat4("L");
					vo.setClassStat5("L");
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 11:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat1());
					} else {
						vo.setClassStat2("S");
					}
					vo.setClassStat3("L");
					vo.setClassStat4("L");
					vo.setClassStat5("L");
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 13:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat2());
					} else {
						vo.setClassStat2("S");
					}
					if(!exVO.getClassStat3().equals(null)) {
						vo.setClassStat3(exVO.getClassStat3());
					} else {
						vo.setClassStat3("S");
					}
					vo.setClassStat4("L");
					vo.setClassStat5("L");
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 14:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat2());
					} else {
						vo.setClassStat2("S");
					}
					if(!exVO.getClassStat3().equals(null)) {
						vo.setClassStat3(exVO.getClassStat3());
					} else {
						vo.setClassStat3("S");
					}
					if(!exVO.getClassStat4().equals(null)) {
						vo.setClassStat4(exVO.getClassStat4());
					} else {
						vo.setClassStat4("S");
					}
					vo.setClassStat5("L");
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 15:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat2());
					} else {
						vo.setClassStat2("S");
					}
					if(!exVO.getClassStat3().equals(null)) {
						vo.setClassStat3(exVO.getClassStat3());
					} else {
						vo.setClassStat3("S");
					}
					if(!exVO.getClassStat4().equals(null)) {
						vo.setClassStat4(exVO.getClassStat4());
					} else {
						vo.setClassStat4("S");
					}
					if(!exVO.getClassStat5().equals(null)) {
						vo.setClassStat5(exVO.getClassStat5());
					} else {
						vo.setClassStat5("S");
					}
					vo.setClassStat6("L");
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 16:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat2());
					} else {
						vo.setClassStat2("S");
					}
					if(!exVO.getClassStat3().equals(null)) {
						vo.setClassStat3(exVO.getClassStat3());
					} else {
						vo.setClassStat3("S");
					}
					if(!exVO.getClassStat4().equals(null)) {
						vo.setClassStat4(exVO.getClassStat4());
					} else {
						vo.setClassStat4("S");
					}
					if(!exVO.getClassStat5().equals(null)) {
						vo.setClassStat5(exVO.getClassStat5());
					} else {
						vo.setClassStat5("S");
					}
					if(!exVO.getClassStat6().equals(null)) {
						vo.setClassStat6(exVO.getClassStat5());
					} else {
						vo.setClassStat6("S");
					}
					vo.setClassStat7("L");
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				
				case 17:{
					if(!exVO.getClassStat1().equals(null)) {
						vo.setClassStat1(exVO.getClassStat1());
					} else {
						vo.setClassStat1("S");
					}if(!exVO.getClassStat2().equals(null)) {
						vo.setClassStat2(exVO.getClassStat2());
					} else {
						vo.setClassStat2("S");
					}
					if(!exVO.getClassStat3().equals(null)) {
						vo.setClassStat3(exVO.getClassStat3());
					} else {
						vo.setClassStat3("S");
					}
					if(!exVO.getClassStat4().equals(null)) {
						vo.setClassStat4(exVO.getClassStat4());
					} else {
						vo.setClassStat4("S");
					}
					if(!exVO.getClassStat5().equals(null)) {
						vo.setClassStat5(exVO.getClassStat5());
					} else {
						vo.setClassStat5("S");
					}
					if(!exVO.getClassStat6().equals(null)) {
						vo.setClassStat6(exVO.getClassStat6());
					} else {
						vo.setClassStat6("S");
					}
					if(!exVO.getClassStat7().equals(null)) {
						vo.setClassStat7(exVO.getClassStat7());
					} else {
						vo.setClassStat7("S");
					}
					vo.setClassStat8("L");
					attendanceMapper.leftClass(vo);
					resultVO.setResult(1);
					resultVO.setMessage("조퇴 처리되었습니다");
				} break;
				default:{//조퇴X
					resultVO.setResult(-1);
					resultVO.setMessage("처리할 수 없습니다. 관리자에 문의 바랍니다");
				} break;
			}
		} else {
			resultVO.setResult(-1);
			resultVO.setMessage("처리할 수 없습니다. 관리자에 문의 바랍니다");
		}
		return resultVO;
	}

	@Override
	public ProcessResultVO<AttendanceVO> outClass(AttendanceVO vo, int outTime, int inTime) throws Exception {
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		AttendanceVO exVO = attendanceMapper.viewAttend(vo);
		if(exVO.getEnterFlag().equals("E")) {
			String [] statArr = new String[8];
			while(outTime<inTime) {
				statArr[outTime] = "O";
				outTime++;
			}
			vo.setClassStat1(statArr[0]);
			vo.setClassStat2(statArr[1]);
			vo.setClassStat3(statArr[2]);
			vo.setClassStat4(statArr[3]);
			vo.setClassStat5(statArr[4]);
			vo.setClassStat6(statArr[5]);
			vo.setClassStat7(statArr[6]);
			vo.setClassStat8(statArr[7]);
			attendanceMapper.outClass(vo);
			resultVO.setResult(1);
			resultVO.setMessage("외출 처리되었습니다.");
		} else {
			resultVO.setResult(-1);
			resultVO.setMessage("처리할 수 없습니다. 관리자에 문의 바랍니다");
		}
		return resultVO;
	}

	@Override
	public void excelDownloadAttendList(AttendanceVO vo, CreateCourseVO ccvo, ServletOutputStream outputStream)
			throws ServiceProcessException {
		//전체 기간 배열
		List<AttendanceVO> pList = attendanceMapper.listPeriod(vo);
		List<String> tArray = new ArrayList<>();
		for(int i=0; i<pList.size(); i++) {
			tArray.add(pList.get(i).getAttendDttm());
		}
		List<List<String>> List1 = Stream.of(tArray).collect(Collectors.toList());

		try {
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
			WritableSheet sheet = workbook.createSheet("출석부", 0);
			sheet.addCell(ExcelUtil.createText(0, rowNum, "center","훈련기관명")); //1열
			sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
			sheet.mergeCells(9, rowNum, 16, rowNum);
			sheet.mergeCells(17, rowNum, 24, rowNum); 
			sheet.mergeCells(25, rowNum, 32, rowNum);
			sheet.mergeCells(33, rowNum, 47, rowNum);
			sheet.addCell(ExcelUtil.createText(1, rowNum, "center", "메디오피아테크"));
			sheet.addCell(ExcelUtil.createText(9, rowNum, "center", "훈련과정명"));
			sheet.addCell(ExcelUtil.createText(17, rowNum, "center", ccvo.getCrsCreNm()+"("+ccvo.getCreTerm()+") 회차 "));
			sheet.addCell(ExcelUtil.createText(25, rowNum, "center", "훈련기간"));
			sheet.addCell(ExcelUtil.createText(33, rowNum, "center", ccvo.getEnrlStartDttm() + "~" + ccvo.getEnrlEndDttm()));
			
			rowNum++;
			sheet.addCell(ExcelUtil.createText(0, rowNum, "center","날짜"));
			sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
			sheet.mergeCells(9, rowNum, 16, rowNum);
			sheet.mergeCells(17, rowNum, 24, rowNum); 
			sheet.mergeCells(25, rowNum, 32, rowNum);
			sheet.mergeCells(33, rowNum, 40, rowNum);
			sheet.addCell(ExcelUtil.createText(1, rowNum, "center","11.06(월)"));
			sheet.addCell(ExcelUtil.createText(9, rowNum, "center","11.07(화)"));
			sheet.addCell(ExcelUtil.createText(17, rowNum, "center","11.08(수)"));
			sheet.addCell(ExcelUtil.createText(25, rowNum, "center","11.09(목)"));
			sheet.addCell(ExcelUtil.createText(33, rowNum, "center","11.10(금)"));
			
			for(int i=41; i<48; i++ ) {
				sheet.mergeCells(i, 1, i, 3);
			}
			
			sheet.addCell(ExcelUtil.createText(41, rowNum, "center", "소정출석일"));
			sheet.addCell(ExcelUtil.createText(42, rowNum, "center", "실제출석일"));
			sheet.addCell(ExcelUtil.createText(43, rowNum, "center", "결석"));
			sheet.addCell(ExcelUtil.createText(44, rowNum, "center", "지각"));
			sheet.addCell(ExcelUtil.createText(45, rowNum, "center", "조퇴"));
			sheet.addCell(ExcelUtil.createText(46, rowNum, "center", "외출"));
			sheet.addCell(ExcelUtil.createText(47, rowNum, "center", "확인"));
			
			rowNum++;
			sheet.addCell(ExcelUtil.createText(0, rowNum, "center", "결재"));
			sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
			sheet.mergeCells(9, rowNum, 16, rowNum);
			sheet.mergeCells(17, rowNum, 24, rowNum);
			sheet.mergeCells(25, rowNum, 32, rowNum);
			sheet.mergeCells(33, rowNum, 40, rowNum);
			sheet.addCell(ExcelUtil.createText(1, rowNum, "center", ""));
			sheet.addCell(ExcelUtil.createText(9, rowNum, "center", ""));
			sheet.addCell(ExcelUtil.createText(17, rowNum, "center", ""));
			sheet.addCell(ExcelUtil.createText(25, rowNum, "center", ""));
			sheet.addCell(ExcelUtil.createText(33, rowNum, "center", ""));
			
			rowNum++;
			sheet.addCell(ExcelUtil.createText(0, rowNum, "center", "성명"));
			
			int col = 1;
			while(col<40) {
				for (int j=1; j<9; j++) {
					sheet.addCell(ExcelUtil.createText(col, rowNum, "center", Integer.toString(j)));
					sheet.setColumnView(col, 5);
					col++;
				}
			}
			
			rowNum++;
			//데이터 시작
			
			
			
			workbook.write();
			workbook.close();
					
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
		
	}

}
