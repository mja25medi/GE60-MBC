package egovframework.edutrack.modules.student.student.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.student.student.service.StudentPDFService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserAuthGrpMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoChgHstyMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrLoginMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("studentPDFService")
public class StudentPDFServiceImpl
	extends EgovAbstractServiceImpl implements StudentPDFService {

	@Resource(name="sysCodeService")
	private SysCodeService			sysCodeService;
	
	/** Mapper */
	@Resource(name="usrLoginMapper")
	private UsrLoginMapper 		usrLoginMapper;
	
	@Resource(name="usrUserInfoChgHstyMapper")
	private UsrUserInfoChgHstyMapper 		usrUserInfoChgHstyMapper;

	@Resource(name="studentMapper")
	private StudentMapper 		studentMapper;

	@Resource(name="usrUserAuthGrpMapper")
	private UsrUserAuthGrpMapper 		usrUserAuthGrpMapper;
	
	@Resource(name="usrUserInfoMapper")
	private UsrUserInfoMapper 		usrUserInfoMapper;

	/**
	 * 수강생/수료생/신청자 명단 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	@Override
	public void listPDFDownload(StudentVO studentVO, CreateCourseVO createCourseVO, String sheetName, String labelName,
			String condition, String excelHeader, ArrayList<String> titleList, HttpServletRequest request, OutputStream os) throws ServiceProcessException {
		List<StudentVO> resultList = null;
		try {
			resultList = studentMapper.listStudent(studentVO);
		} catch (Exception e) {
			throw new ServiceProcessException("error", e);
		}

		String[] headers = StringUtil.split(excelHeader, "@$");

		int colNum = headers.length - 1;

		String locale = UserBroker.getLocaleKey(request);

		try {
			// step 1 : Document 생성
			Document document;
			document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 용지 및 여백 설정

			// step 2 : PdfWriter 생성
			PdfWriter writer = PdfWriter.getInstance(document, os);

			String fontPath = "";
			BaseFont bf_font;
			if("ko".equals(locale)) {
				fontPath = request.getSession().getServletContext().getRealPath("/font/Batang.ttf");
				bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); // IDENTITY_V, IDENTITY_H, WINANSI, UniKS-UCS2-H, UniGB-UCS2-H
			} else if("jp".equals(locale)) {
				fontPath = request.getSession().getServletContext().getRealPath("/font/MSMINCHO.TTF");
				bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			} else {
			    Font helvetica = new Font(FontFamily.HELVETICA, 12);
			    bf_font = helvetica.getCalculatedBaseFont(false);
			}
			Font fontTitle = new Font(bf_font, 16);
			Font fontBody = new Font(bf_font,10);

			// step 3 : Document Open
			document.open();

			// 제목줄 등록
            Paragraph paragraphTitle = new Paragraph();
            paragraphTitle.setSpacingAfter(25);
            paragraphTitle.setSpacingBefore(25);
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
            paragraphTitle.setFont(fontTitle);

            Chunk titleStr = new Chunk(labelName);
            paragraphTitle.add(titleStr);

            document.add(paragraphTitle);

            // 출력일자
            Paragraph paragraphCondition = new Paragraph();
            paragraphCondition.setAlignment(Element.ALIGN_RIGHT);
            paragraphCondition.setFont(fontBody);
            paragraphCondition.setSpacingAfter(10);

            Chunk conditionStr = new Chunk(condition);
            paragraphCondition.add(condition);

            document.add(paragraphCondition);


            PdfPTable table = new PdfPTable(headers.length); // 3 columns.

            //제목줄 셋팅
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
				if("APLCDTTM".equals(headers[i])) headerName = titleList.get(11);
				if("PAYMMTHD".equals(headers[i])) headerName = titleList.get(12);
				if("PAYMPRICE".equals(headers[i])) headerName = titleList.get(13);
				if("PAYMSTS".equals(headers[i])) headerName = titleList.get(14);
				if("GETSCORE".equals(headers[i])) headerName = titleList.get(15);
				if("SCOREECLT".equals(headers[i])) headerName = titleList.get(16);
				if("CPLTNO".equals(headers[i])) headerName = titleList.get(17);
				if("ENRLSTS".equals(headers[i])) headerName = titleList.get(18);
				
				PdfPCell cell = new PdfPCell(new Paragraph(headerName));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); //-- 가로 중간
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //-- 세로 중간
				cell.setBackgroundColor(new BaseColor(211,211,211)); //-- 회색으로 배경색 설정
				cell.setPadding(5);
				table.addCell(cell);
			}

			//-- 검색된 학습자가 없는 경우 표시
			if(resultList.size() == 0) {
				PdfPCell cell = new PdfPCell(new Paragraph("* There is no data."));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER); //-- 가로 중간
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //-- 세로 중간
				cell.setPadding(5);
				cell.setColspan(colNum);
				table.addCell(cell);
			}

			//-- 실 데이터
			for(int i=0; i<resultList.size(); i++){
				StudentVO sVO = resultList.get(i);
				for(int j = 0; j < headers.length; j++) {
					String cellStr = "";
					int horAlign = Element.ALIGN_CENTER;
					if("NO".equals(headers[j])) {
						cellStr = Integer.toString(i + 1);
					}
					if("DECLS".equals(headers[j])) {
						cellStr = StringUtil.nvl(sVO.getDeclsNo(),"0");
					}
					if("USERNM".equals(headers[j])) {
						cellStr = sVO.getUserNm();
					}
					if("USERID".equals(headers[j])) {
						cellStr = sVO.getUserId();
					}
					if("EMAIL".equals(headers[j])) {
						cellStr = sVO.getEmail();
						horAlign = Element.ALIGN_LEFT;
					}
					if("BIRTH".equals(headers[j])) {
						String birth = sVO.getBirth();
						if(ValidationUtils.isNotEmpty(birth)) {
							birth = DateTimeUtil.getDateType(1, birth);
						}
						cellStr = birth;
					}
					if("SEX".equals(headers[j])) {
						String sexNm = "";
						try {
							SysCodeVO codeVO = sysCodeService.viewCode("SEX_CD", sVO.getSex());
							sexNm = codeVO.getCodeNm();
							for(SysCodeLangVO cldto : codeVO.getCodeLangList()) {
								if(locale.equals(cldto.getLangCd())) sexNm = cldto.getCodeNm();
							}
						} catch (Exception e) {
							sexNm = sVO.getSex();
						}
						cellStr = sexNm;
					}
					if("PHONENO".equals(headers[j])) cellStr = sVO.getHomePhoneno();
					if("MOBILENO".equals(headers[j])) cellStr = sVO.getMobileNo();
					if("FAXNO".equals(headers[j])) cellStr = sVO.getCompFaxNo();
					if("DEPT".equals(headers[j])) {
						cellStr = sVO.getDeptNm();
						horAlign = Element.ALIGN_LEFT;
					}
					if("APLCDTTM".equals(headers[j])) cellStr = DateTimeUtil.getDateType(0, sVO.getEnrlAplcDttm());
					if("PAYMMTHD".equals(headers[j])) {
						String payMthdNm = "";
						try {
							SysCodeVO codeVO = sysCodeService.viewCode("PAYM_MTHD_CD", sVO.getPaymMthdCd());
							payMthdNm = codeVO.getCodeNm();
							for(SysCodeLangVO cldto : codeVO.getCodeLangList()) {
								if(locale.equals(cldto.getLangCd())) payMthdNm = cldto.getCodeNm();
							}
						} catch (Exception e) {
							payMthdNm = sVO.getPaymMthdCd();
						}
						cellStr = payMthdNm;
					}
					if("PAYMPRICE".equals(headers[j])) {
						cellStr = Integer.toString(sVO.getPaymPrice());
						horAlign = Element.ALIGN_RIGHT;
					}
					if("PAYMSTS".equals(headers[j])) cellStr = sVO.getPaymStsCd();
					if("GETSCORE".equals(headers[j])) {
						cellStr = Integer.toString(sVO.getTotScore());
						horAlign = Element.ALIGN_RIGHT;
					}
					if("SCOREECLT".equals(headers[j])) cellStr = sVO.getScoreEcltYn();
					if("CPLTNO".equals(headers[j])) cellStr = sVO.getCpltNo();
					if("ENRLSTS".equals(headers[j])) {
						String enrlSts = "";
						try {
							SysCodeVO codeVO = sysCodeService.viewCode("ENRL_STS", sVO.getEnrlSts());
							enrlSts = codeVO.getCodeNm();
							for(SysCodeLangVO cldto : codeVO.getCodeLangList()) {
								if(locale.equals(cldto.getLangCd())) enrlSts = cldto.getCodeNm();
							}
						} catch (Exception  e) {
							enrlSts = sVO.getEnrlSts();
						}
						cellStr = enrlSts;
					}
					PdfPCell cell = new PdfPCell(new Paragraph(cellStr));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //-- 세로 중간
					cell.setHorizontalAlignment(horAlign); //-- 가로 중간
					cell.setPadding(5);
					table.addCell(cell);
				}
			}
			document.add(table);

	        document.close();
			writer.close();

		} catch (Exception e) {
			throw new ServiceProcessException("PDF file creation failed!", e);
		}
	}

}
