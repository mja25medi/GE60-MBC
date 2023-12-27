package egovframework.edutrack.modules.teacher.sche.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.modules.teacher.sche.service.CalendarVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleDetailVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleForm;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleProfVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleService;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


@Service("scheduleService")
public class ScheduleServiceImpl extends EgovAbstractServiceImpl implements ScheduleService {
	
	/** Mapper */
	@Resource(name="scheduleMapper")
	private ScheduleMapper 		scheduleMapper;
	
	/**
	 *  등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<ScheduleForm> addSchedule(ScheduleForm scheduleForm) throws Exception{
		
		ScheduleVO scheduleVO = scheduleForm.getScheduleVO();
		ScheduleDetailVO scheduleDetailVO = scheduleForm.getScheduleDetailVO();
		
		
		scheduleVO.setOtsdLecGoingDttm(scheduleVO.getOtsdLecGoingDttmDay().replace(".", "")+scheduleVO.getOtsdLecGoingDttmHour()+scheduleVO.getOtsdLecGoingDttmMinute());

		
		scheduleVO.setOtsdLecReturnDttm(scheduleVO.getOtsdLecReturnDttmDay().replace(".", "")+scheduleVO.getOtsdLecReturnDttmHour()+scheduleVO.getOtsdLecReturnDttmMinute());

		scheduleDetailVO.setStartTm(scheduleDetailVO.getStartHour()+scheduleDetailVO.getStartMinute());
		scheduleDetailVO.setEndTm(scheduleDetailVO.getEndHour()+scheduleDetailVO.getEndMinute());
		scheduleDetailVO.setStartDay(scheduleDetailVO.getStartDay().replace(".", ""));		
		scheduleDetailVO.setEndDay(scheduleDetailVO.getEndDay().replace(".", ""));

		scheduleVO.setSchSn(scheduleMapper.selectSchSnKey()) ;         
		
		scheduleMapper.insertSchedule(scheduleVO);
		scheduleDetailVO.setUserNo(scheduleVO.getUserNo());
		scheduleDetailVO.setSchSn(scheduleVO.getSchSn());
		
		ProcessResultListVO<ScheduleDetailVO>  resultListVO = new ProcessResultListVO<ScheduleDetailVO>(); 
		List<ScheduleDetailVO> resultList = new ArrayList<ScheduleDetailVO>();
		try {
			resultList =  scheduleMapper.scheduleListContents(scheduleDetailVO);
			resultListVO.setResult(1);
			resultListVO.setReturnList(resultList);
		} catch (Exception e){
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}

		//-- 상위 목차자 같은 리스트 뽑기
		
		int i = 0;
		for(ScheduleDetailVO contentsVO : resultList) {
			i++;
			contentsVO.setUserNo(scheduleVO.getUserNo());
			contentsVO.setSchSn(scheduleVO.getSchSn());
			contentsVO.setYear(contentsVO.getYear());
			contentsVO.setMonth(contentsVO.getMonth());
			contentsVO.setDay(contentsVO.getDay());
			contentsVO.setStartTm(scheduleDetailVO.getStartTm());
			contentsVO.setEndTm(scheduleDetailVO.getEndTm());
			contentsVO.setdDay(scheduleDetailVO.getdDay());
			contentsVO.setSchDetlSn(scheduleMapper.selectSchDetlSnKey()) ; 
		}
		scheduleMapper.updateScheduleBatch(resultList);
		
		return ProcessResultVO.success();
	}
	
	
	
	public ProcessResultListVO<ScheduleProfVO> profList() throws Exception {
		
		ProcessResultListVO<ScheduleProfVO> resultList = new ProcessResultListVO<ScheduleProfVO>(); 
		try {
			List<ScheduleProfVO> returnList =  scheduleMapper.profList();
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
	 * month 목록 조회
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<CalendarVO> listScheMonth(CalendarVO calendarDto) throws Exception {
		ProcessResultListVO<CalendarVO> resultList = new ProcessResultListVO<CalendarVO>(); 
		try {
			List<CalendarVO> returnList =  scheduleMapper.listSchedule(calendarDto);;
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	public ProcessResultVO<ScheduleVO> selectSchedule(ScheduleVO scheduleVO) throws Exception {
		
		ProcessResultVO<ScheduleVO> resultVO = new ProcessResultVO<ScheduleVO>(); 
		try {
			ScheduleVO returnVO =  scheduleMapper.selectSchedule(scheduleVO);
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
	 *교수일정 목록 조회(엑셀 download)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<ScheduleVO> listScheduleResult(ScheduleVO scheduleVO) throws Exception {
		ProcessResultListVO<ScheduleVO> resultList = new ProcessResultListVO<ScheduleVO>(); 
		try {
			List<ScheduleVO> returnList =  scheduleMapper.listScheduleResult(scheduleVO);
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
	 * month 목록 조회(개인)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<CalendarVO> listScheMonthMy(CalendarVO calendarDto) throws Exception {
		ProcessResultListVO<CalendarVO> resultList = new ProcessResultListVO<CalendarVO>(); 
		try {
			List<CalendarVO> returnList =  scheduleMapper.listScheduleMy(calendarDto);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	
	public ProcessResultVO<ScheduleVO> editSchedule(ScheduleVO scheduleVO) throws Exception{
		scheduleVO.setStartTm(scheduleVO.getStartHour()+scheduleVO.getStartMinute());
		scheduleVO.setEndTm(scheduleVO.getEndHour()+scheduleVO.getEndMinute());
		String startDay = scheduleVO.getStartDay().replace(".", "");
		String year = startDay.substring(0,4);
		String month =  startDay.substring(4,6);
		String day =  startDay.substring(6,8);
		scheduleVO.setYear(year);
		scheduleVO.setMonth(month);
		scheduleVO.setDay(day);
		scheduleMapper.editSchedule(scheduleVO);
		return ProcessResultVO.success();
	}
	
	
	/**
	 * 일정정보 삭제
	 * @param userSn 삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<ScheduleVO> deleteSchedule(ScheduleVO scheduleVO) throws Exception {
		scheduleMapper.deleteSchedule(scheduleVO);
		return ProcessResultVO.success();
	}
	
	/**
	 * 일정정보 전체삭제
	 * @param userSn 삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<ScheduleVO> deleteAllSchedule(ScheduleVO scheduleVO) throws Exception {
		scheduleMapper.deleteSchedule(scheduleVO);
		scheduleMapper.deleteAllSchedule(scheduleVO);
		return ProcessResultVO.success();
	}
	
	/**
	 * 교수일정 엑셀다운로드
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public void listScheMonthExcelDownload(ScheduleVO scheduleVO, OutputStream os) throws Exception {
		List<ScheduleVO> resultList = this.listScheduleResult(scheduleVO).getReturnList();

		try {
			int rowNum = 0;
	
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			WritableSheet sheet = workbook.createSheet("원내교수일정관리", 0);
			sheet.addCell(ExcelUtil.createLabel(0,rowNum,"left","원내교수일정 리스트 ")); //1열
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, 11, 0); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 500);
			
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"일자"));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,"기간/시간"));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,"이름"));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,"목적"));
			sheet.addCell(ExcelUtil.createHeader(4,rowNum,"요청기관/부서"));
			sheet.addCell(ExcelUtil.createHeader(5,rowNum,"장소(소재지)"));
			sheet.addCell(ExcelUtil.createHeader(6,rowNum,"교육과정"));		
			sheet.addCell(ExcelUtil.createHeader(7,rowNum,"교육대상"));
			sheet.addCell(ExcelUtil.createHeader(8,rowNum,"강의주제"));
			sheet.addCell(ExcelUtil.createHeader(9,rowNum,"교육인원"));
			sheet.addCell(ExcelUtil.createHeader(10,rowNum,"연락처"));
			sheet.addCell(ExcelUtil.createHeader(11,rowNum,"공문서수령"));
			sheet.addCell(ExcelUtil.createHeader(12,rowNum,"외부강의신고 해당여부"));
			sheet.addCell(ExcelUtil.createHeader(13,rowNum,"외부강의신고 제출여부"));
			sheet.addCell(ExcelUtil.createHeader(14,rowNum,"결재여부"));
			sheet.addCell(ExcelUtil.createHeader(15,rowNum,"비고"));
			sheet.addCell(ExcelUtil.createHeader(16,rowNum,"대외출강결과보고서제출여부"));
			
			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 10);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 30);
			sheet.setColumnView(6, 30);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 30);
			sheet.setColumnView(13, 30);
			sheet.setColumnView(14, 20);
			sheet.setColumnView(15, 40);
			sheet.setColumnView(16, 40);
			
			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 400);
			
			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				ScheduleVO calDto = resultList.get(i);
				
				sheet.addCell(ExcelUtil.createText(0,rowNum,"left",calDto.getStartDay()));
				sheet.addCell(ExcelUtil.createText(1,rowNum,"left",calDto.getStartTm()));
				sheet.addCell(ExcelUtil.createText(2,rowNum,"left",calDto.getUserNm()));
				sheet.addCell(ExcelUtil.createText(3,rowNum,"left",calDto.getLecDivCd()));
				sheet.addCell(ExcelUtil.createText(4,rowNum,"left",calDto.getReqOrgNm()));
				sheet.addCell(ExcelUtil.createText(5,rowNum,"left",calDto.getPlace()));
				sheet.addCell(ExcelUtil.createText(6,rowNum,"left",calDto.getTrainingCrs()));
				sheet.addCell(ExcelUtil.createText(7,rowNum,"left",calDto.getEduTarget()));
				sheet.addCell(ExcelUtil.createText(8,rowNum,"left",calDto.getLecTitle()));
				sheet.addCell(ExcelUtil.createNumber(9,rowNum,"left",calDto.getEduNopCnt()));
				sheet.addCell(ExcelUtil.createText(10,rowNum,"left",calDto.getOrgChrgPrsnPhoneno()));
				sheet.addCell(ExcelUtil.createText(11,rowNum,"left",calDto.getOfficialDocRecvYn()));
				sheet.addCell(ExcelUtil.createText(12,rowNum,"left",calDto.getOtsdLecReptRelevYn()));
				sheet.addCell(ExcelUtil.createText(13,rowNum,"left",calDto.getOtsdLecReptDataSendYn()));
				sheet.addCell(ExcelUtil.createText(14,rowNum,"left",calDto.getApprovalYn()));
				sheet.addCell(ExcelUtil.createText(15,rowNum,"left",calDto.getSchDesc()));
				sheet.addCell(ExcelUtil.createText(16,rowNum,"left",calDto.getExtrnLecRsltReptSendYn()));
				sheet.setRowView(rowNum, 400);
			}
			
			if(resultList.size() == 0){
				sheet.addCell(ExcelUtil.createText(0,2,"center","교수일정 정보가 존재하지 않습니다.")); //1열
				sheet.mergeCells(0, 2, 16, 0);
				sheet.setRowView(0, 400);
			}
			
			workbook.write(); 
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}
	
}
