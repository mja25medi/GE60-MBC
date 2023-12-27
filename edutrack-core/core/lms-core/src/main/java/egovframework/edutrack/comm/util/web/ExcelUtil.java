package egovframework.edutrack.comm.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

/**
 * @author chorongjang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExcelUtil {

	/**
	 *
	 */
	public ExcelUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Label createLabel(int x, int y, String name) throws Exception{
		return createLabel(x, y, "center", name);
	}

	public static Label createLabel(int x, int y, String align, String name) throws Exception{
	    WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
                16,
                WritableFont.BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);
	    WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
	    labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		Label label = new Label(x, y, name,labelFormat);

		if(label != null) return label;
		else return new Label(x, y, name);
	}

	public static Label createLabel2(int x, int y, String align, String name) throws Exception{
	    WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE,
                WritableFont.NO_BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);
	    WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
	    labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		Label label = new Label(x, y, name,labelFormat);

		if(label != null) return label;
		else return new Label(x, y, name);
	}

	public static Label createHeader(int x, int y, String name) throws Exception{
		return createHeader(x, y, "center", name);
	}

	public static Label createHeader(int x, int y, String align, String name) throws Exception{
	    WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
                11,
                WritableFont.BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);
	    WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
	    labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	    labelFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	    labelFormat.setBackground(Colour.GRAY_25);
		Label label = new Label(x, y, name,labelFormat);

		if(label != null) return label;
		else return new Label(x, y, name);
	}

	public static  Label createText(int x, int y, String align, String name) throws Exception{
        WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE,
                WritableFont.NO_BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);
   		WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
   		labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	    labelFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		Label label = new Label(x, y, name,labelFormat);

		if(label != null) return label;
		else return new Label(x, y, name);
	}

	public static  Label createComment(int x, int y, String align, String name) throws Exception{
        WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE,
                WritableFont.NO_BOLD,
                false,
                UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);
   		WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
   		labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		Label label = new Label(x, y, name,labelFormat);

		if(label != null) return label;
		else return new Label(x, y, name);
	}

	public static  Number createNumber(int x, int y, String align, double num) throws Exception{
        WritableFont fontFormat = new WritableFont(WritableFont.ARIAL,
            WritableFont.DEFAULT_POINT_SIZE,
            WritableFont.NO_BOLD,
            false,
            UnderlineStyle.NO_UNDERLINE,
            Colour.BLACK);
		WritableCellFormat labelFormat = new WritableCellFormat(fontFormat);
		labelFormat.setWrap(true);
	    labelFormat.setAlignment(getAlign(align));
	    labelFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	    labelFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	    Number number = new Number(x, y, num,labelFormat);

		if(number != null) return number;
		else return new Number(x, y, num);
	}

	public static  Alignment getAlign(String align){
		if(align.toLowerCase().equals("center")) return Alignment.CENTRE;
		else if(align.toLowerCase().equals("left")) return Alignment.LEFT;
		else if(align.toLowerCase().equals("right")) return Alignment.RIGHT;
		else return Alignment.CENTRE;
	}

	public static void excelInfoSet(HttpServletRequest request, HttpServletResponse response, String fileName){
		response.reset();
		response.setContentType("application/x-msdownload;charset="+request.getCharacterEncoding());
		response.setHeader("Content-Disposition","attachment; filename="+fileName);
		response.setHeader("Cache-Control", "no-cache");
	}
}
