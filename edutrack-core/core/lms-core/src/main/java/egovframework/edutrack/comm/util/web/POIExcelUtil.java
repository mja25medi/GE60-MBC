package egovframework.edutrack.comm.util.web;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIExcelUtil {

	public POIExcelUtil () {

	}

	/**
	 * 병합된 문서 제목 Cell을 반환한다.
	 * @param row
	 * @param nStart
	 * @param nEnd
	 * @param font
	 * @param bgColor
	 * @param hAlign
	 * @param border
	 * @return
	 */
	public static XSSFCell createPageTitleCell(String value, XSSFRow row, int nStart, int nEnd) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_NONE;

		XSSFCell returnCell = null;
		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)20); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, "center", Color.WHITE, border, font, format.getFormat("@"));

	    //셀 생성
	    for (int i = nStart; i <= nEnd; i++) {
	    	XSSFCell cell = row.createCell(i);
	    	if (i == nStart) {
	    		returnCell = cell;
	    	}
	    	cell.setCellStyle(cs);
	    }

	    //병합 영역 설정
	    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), nStart, nEnd));
	    returnCell.setCellValue(value);
	    return returnCell;
	}

	/**
	 * 병합된 문서 제목 Cell을 반환한다.
	 * @param row
	 * @param nStart
	 * @param nEnd
	 * @param font
	 * @param bgColor
	 * @param hAlign
	 * @param border
	 * @return
	 */
	public static XSSFCell createMergeCell(String value, XSSFRow row, int nStart, int nEnd, String align) {
		return createMergeCell(value, row, nStart, nEnd, align, false);
	}

	/**
	 * 병합된 문서 제목 Cell을 반환한다.
	 * @param row
	 * @param nStart
	 * @param nEnd
	 * @param font
	 * @param bgColor
	 * @param hAlign
	 * @param border
	 * @return
	 */
	public static XSSFCell createMergeCell(String value, XSSFRow row, int nStart, int nEnd, String align, boolean isBorderUse) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border;
		if(isBorderUse) {
			border = CellStyle.BORDER_THIN;
		} else {
			border = CellStyle.BORDER_NONE;
		}

		XSSFCell returnCell = null;
		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat("@"));

	    //셀 생성
	    for (int i = nStart; i <= nEnd; i++) {
	    	XSSFCell cell = row.createCell(i);
	    	if (i == nStart) {
	    		returnCell = cell;
	    	}
	    	cell.setCellStyle(cs);
	    }

	    //병합 영역 설정
	    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), nStart, nEnd));
	    returnCell.setCellValue(value);
	    return returnCell;
	}

	/**
	 * 타이틀 Cell을 반환하낟.
	 * @param String value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createTitleCell(String value, XSSFRow row, int col) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;

		XSSFCell returnCell = null;
		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, "center", Color.LIGHT_GRAY, border, font, format.getFormat("@"));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param String value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createContentCell(String value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "left"; // 숫자형은 기본은 Left

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat("@") );

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param Double value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createContentCell(Double value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "right"; // 숫자형은 기본은 Right

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat(""));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param int value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createContentCell(int value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "right"; // 숫자형은 기본은 Right

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat("0"));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param boolean value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	/*public static XSSFCell createContentCell(boolean value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "center";

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat(""));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue (value);
	    return cell;
	}*/

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param Calendar value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createContentCell(Calendar value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "center"; // 숫자형은 기본은 Right

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat(""));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 콘텐트 Cell을 반환하낟.
	 * @param Date value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createContentCell(Date value, XSSFRow row, int col, String align) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;
		if(ValidationUtils.isEmpty(align)) align = "center";

		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.WHITE, border, font, format.getFormat(""));

	    //셀 생성
		XSSFCell cell = row.createCell(col);
		cell.setCellStyle(cs);
		cell.setCellValue(value);
	    return cell;
	}

	/**
	 * 셀의 스타일을 반환한다.
	 * @param wb
	 * @param vAlign
	 * @param hAlign
	 * @param bgColor
	 * @param border
	 * @param font
	 * @return
	 */
	public static XSSFCellStyle createCellStyle(XSSFWorkbook wb, short vAlign, String align, Color bgColor, short border, Font font, short dataformat) {
		XSSFCellStyle returnStyle = wb.createCellStyle();

		// 가로 정렬
		short hAlign = CellStyle.ALIGN_LEFT;
		if("center".equals(align)) hAlign = CellStyle.ALIGN_CENTER;
		else if("right".equals(align)) hAlign = CellStyle.ALIGN_RIGHT;
		returnStyle.setAlignment(hAlign);

	    // 세로 정렬
		returnStyle.setVerticalAlignment(vAlign);

	    //테두리 설정
		returnStyle.setBorderTop(border);
		returnStyle.setBorderRight(border);
		returnStyle.setBorderBottom(border);
		returnStyle.setBorderLeft(border);

		//data format
		returnStyle.setDataFormat(dataformat);

	    //글꼴 설정
		returnStyle.setFont(font);

	    //배경색 설정
	    returnStyle.setFillForegroundColor(new XSSFColor(bgColor));
	    returnStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

	    //셀의 넓이에 맞게 문자를 개행한다.
	    returnStyle.setWrapText(true);

		return returnStyle;
	}

	public static String getCellValue(XSSFCell cell) {
		return getCellValue(cell, false);
	}

	public static String getCellValue(XSSFCell cell, boolean isIntVal) {
		String retVal = "";
		if(cell==null) {
			retVal = "";
		} else {
			//타입별로 내용 읽기
			switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_FORMULA:
					retVal=cell.getCellFormula();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if(isIntVal)
						retVal=(int)cell.getNumericCellValue()+"";
					else
						retVal=cell.getNumericCellValue()+"";
					break;
				case XSSFCell.CELL_TYPE_STRING:
					retVal=cell.getStringCellValue()+"";
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					//retVal=cell.getBooleanCellValue()+"";
					retVal = "";
					break;
				case XSSFCell.CELL_TYPE_ERROR:
					retVal=cell.getErrorCellValue()+"";
					break;
			}
		}
		return retVal;
	}
	/**
	 * 콘텐트 Cell 가로 병합 반환하낟.
	 * @param String value
	 * @param XSSFRow row
	 * @param int col
	 * @return
	 */
	public static XSSFCell createMergeWidthContentCell(String value, XSSFRow row, int nStart, int nEnd) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border = CellStyle.BORDER_THIN;

		XSSFCell returnCell = null;
		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();
		
		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, "center", Color.WHITE, border, font, format.getFormat("@"));

	    //셀 생성
	    for (int i = nStart; i <= nEnd; i++) {
	    	XSSFCell cell = row.createCell(i);
	    	if (i == nStart) {
	    		returnCell = cell;
	    	}
	    	cell.setCellStyle(cs);
	    }

	    //병합 영역 설정
	    returnCell.setCellStyle(cs);
	    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), nStart, nEnd));
	    returnCell.setCellValue(value);
	    return returnCell;
	}
	/**
	 * cell title 세로 병합 반환
	 * @param row
	 * @param nStart
	 * @param nEnd
	 * @param font
	 * @param bgColor
	 * @param hAlign
	 * @param border
	 * @return
	 */
	public static XSSFCell createMergeLengthTitleCell(String value, XSSFRow row,int lStart,int lEnd, int nStart, int nEnd, String align) {
		return createMergeLengthTitleCell(value, row, lStart, lEnd, nStart, nEnd, align, true);
	}
	/**
	 * cell 타이틀 세로 병합
	 * @param row
	 * @param nStart
	 * @param nEnd
	 * @param font
	 * @param bgColor
	 * @param hAlign
	 * @param border
	 * @return
	 */
	public static XSSFCell createMergeLengthTitleCell(String value, XSSFRow row, int lStart, int lEnd, int nStart, int nEnd, String align, boolean isBorderUse) {
		short vAlign = CellStyle.VERTICAL_CENTER;
		short border;
		if(isBorderUse) {
			border = CellStyle.BORDER_THIN;
		} else {
			border = CellStyle.BORDER_NONE;
		}

		XSSFCell returnCell = null;
		XSSFSheet sheet = row.getSheet();
		XSSFWorkbook wb = sheet.getWorkbook();

		XSSFDataFormat format = wb.createDataFormat(); //data format
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)11); //글자 크기

		XSSFCellStyle cs = createCellStyle(wb, vAlign, align, Color.LIGHT_GRAY, border, font, format.getFormat("@"));
	    //셀 생성
	    for (int i = nStart; i <= nEnd; i++) {
	    	XSSFCell cell = row.createCell(i);
	    	if (i == nStart) {
	    		returnCell = cell;
	    	}
	    	cell.setCellStyle(cs);
	    }

	    //병합 영역 설정
	    returnCell.setCellStyle(cs);
	    sheet.addMergedRegion(new CellRangeAddress(lStart, lEnd, nStart, nEnd));
	    returnCell.setCellValue(value);
	    return returnCell;
	}

}
