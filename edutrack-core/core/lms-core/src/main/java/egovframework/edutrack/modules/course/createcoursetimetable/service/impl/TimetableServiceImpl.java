package egovframework.edutrack.modules.course.createcoursetimetable.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableService;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;




@Service(value ="timetableService")
public class TimetableServiceImpl extends EgovAbstractServiceImpl implements TimetableService {
	
	/** Mapper */
	@Resource(name="timetableMapper")
	private TimetableMapper timetableMapper;
	
	/**
	 * 개설 과정 시간표 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<TimetableVO> listTimetable(TimetableVO iTimetableVO) throws Exception {
		ProcessResultListVO<TimetableVO> resultList = new ProcessResultListVO<TimetableVO>();
		try {
			List<TimetableVO> returnList = timetableMapper.listTimetable(iTimetableVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 개설 과정 시간표 일차 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<TimetableVO> listTimetableDay(TimetableVO iTimetableVO) throws Exception {
		
		ProcessResultListVO<TimetableVO> resultList = new ProcessResultListVO<TimetableVO>();
		try {
			List<TimetableVO> returnList = timetableMapper.listTimetableDay(iTimetableVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 개설 과정 시간표 정보 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultVO<TimetableVO> viewTimetable(TimetableVO iTimetableVO) throws Exception {
		ProcessResultVO<TimetableVO> resultVO = new ProcessResultVO<TimetableVO>();
		try {
			TimetableVO returnVO = timetableMapper.selectTimetable(iTimetableVO);
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
	 * 개설 과정 시간표 등록
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TimetableVO> addTimetable(TimetableVO iTimetableVO) throws Exception {
		
		ProcessResultVO<TimetableVO> resultVO = new ProcessResultVO<TimetableVO>();
		try {
			//-- Timetable sn 셋팅;
			iTimetableVO.setTmtabSn(timetableMapper.selectTmtabSn().getTmtabSn());
			timetableMapper.insertTimetable(iTimetableVO);
			resultVO.setReturnVO(iTimetableVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		
		return resultVO;
	}
	
	/**
	 * 개설 과정 시간표 수정
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TimetableVO> editTimetable(TimetableVO iTimetableVO) throws Exception {
		ProcessResultVO<TimetableVO> resultVO = new ProcessResultVO<TimetableVO>();
		try {
			timetableMapper.updateTimetable(iTimetableVO);
			resultVO.setReturnVO(iTimetableVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 시간표 순서 변경
	 * @return
	 */
	@Override
	public ProcessResultVO<TimetableVO> sortTimetable(TimetableVO iTimetableVO)  throws Exception {
		
		String[] list = StringUtil.split(iTimetableVO.getStrTmtabSn(),"|");
		
		// 하위 코드 목록을 한꺼번에 조회
		List<TimetableVO> tmArray = timetableMapper.listTimetable(iTimetableVO);
		
		// 이중 포문으로 categoryArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (TimetableVO timetableVO : tmArray) {
			for (int order = 0; order < list.length; order++) {
				if(timetableVO.getTmtabSn() == Integer.parseInt(list[order])) {
					timetableVO.setTmtabOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		
		// 변경된 시스템코드 어래이를 일괄 저장.
		timetableMapper.updateTimetableBatch(tmArray);
		
		return ProcessResultVO.success();
	}	

	/**
	 * 개설 과정 시간표 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<TimetableVO> deleteTimetable(TimetableVO iTimetableVO) throws Exception {
		
		ProcessResultVO<TimetableVO> resultVO = new ProcessResultVO<TimetableVO>();
		try {
			timetableMapper.deleteTimetable(iTimetableVO);
			resultVO.setReturnVO(iTimetableVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
		
	}
	
	/** 
	 * 시간표 엑셀 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	@Override
	public void listExcelDownload(TimetableVO timetableVO, CreateCourseVO createCourseVO, OutputStream os) throws ServiceProcessException {
		List<TimetableVO> timeList = null;
		List<TimetableVO> dayList = null;
		try {
			timeList = this.listTimetable(timetableVO).getReturnList(); 
			dayList = this.listTimetableDay(timetableVO).getReturnList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		try {
			int rowNum = 0;
	
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			WritableSheet sheet = workbook.createSheet(createCourseVO.getCrsCreNm()+" 시간표", 0);
			sheet.addCell(ExcelUtil.createLabel(0, rowNum, "center", createCourseVO.getCrsCreNm()+"시간표 ")); //1열
			//-- 제목줄 병합
			sheet.mergeCells(0, rowNum, 4, rowNum); //-- 병합
			//-- 제목줄 셀의 높이 조절
			sheet.setRowView(rowNum, 700);
			
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,"일차"));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,"시간"));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,"교과목/활동"));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,"강사"));
			sheet.addCell(ExcelUtil.createHeader(4,rowNum,"비고"));
			
			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 7);
			sheet.setColumnView(1, 12);
			sheet.setColumnView(2, 50);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 650);
			int startNum = rowNum+1;
			for(int i=0; i<timeList.size(); i++){
				
				rowNum++;
				TimetableVO stimetabletVO = timeList.get(i);
				
				sheet.addCell(ExcelUtil.createText(0,rowNum,"center",stimetabletVO.getTmtabDay()+"일차\n"+stimetabletVO.getTmtabMonthday()));
				sheet.addCell(ExcelUtil.createText(1,rowNum,"left",stimetabletVO.getEduTm()));
				if("LEC".equals(stimetabletVO.getTmtabType())) {
					sheet.addCell(ExcelUtil.createText(2,rowNum,"center",stimetabletVO.getSbjNm()));
					sheet.addCell(ExcelUtil.createText(3,rowNum,"center",stimetabletVO.getUserNm()+"\n"+StringUtil.nvl(stimetabletVO.getAddTch())));
					sheet.addCell(ExcelUtil.createText(4,rowNum,"center",""));
				} else {
					sheet.addCell(ExcelUtil.createText(2,rowNum,"center",stimetabletVO.getEduCts()));
					//-- 줄병함
					sheet.mergeCells(2, rowNum, 4, rowNum); //-- 병합
				}
				//-- 셀 높이 지정
				sheet.setRowView(rowNum, 600);
			}
			//--세로셀 병합을 위해 루핑 한번더
			for(int i=0; i < dayList.size(); i++) {
				TimetableVO dayVO = dayList.get(i);
				//-- 세로셀 병합
				sheet.mergeCells(0, startNum, 0, startNum+dayVO.getSubCnt()-1); //-- 병합
				startNum = startNum+dayVO.getSubCnt();
			}
			
			workbook.write(); 
			workbook.close();
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}
	
}
