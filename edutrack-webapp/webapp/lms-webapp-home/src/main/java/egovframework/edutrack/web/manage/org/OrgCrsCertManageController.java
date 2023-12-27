package egovframework.edutrack.web.manage.org;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/crsCert")
public class OrgCrsCertManageController extends GenericController {

	@Autowired @Qualifier("orgCrsCertService")
	private OrgCrsCertService 		orgCrsCertService;

	@Autowired @Qualifier("sysFileService")
	private SysFileService 		sysFileService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 과정 수료증 관리 메인 화면으로 이동한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/crscert/edit_cert_main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editCertMain")
	public String main(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		try {
			vo = orgCrsCertService.view(vo);
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			vo.setPrnDirec("VER"); //-- 세로를 기본으로 함.
			request.setAttribute("gubun", "A");
		}
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		request.setAttribute("a4horizontal", "Y");
		
		
		
		return "mng/org/crscert/edit_cert_main";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 기관의 수료증을 등록 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 기관의 수료증을 수정 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 기관의 수료증을 삭제 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : preview
     * @Method 설명 : 기관 과정 수료증 관리 메인 화면으로 이동한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/crscert/editCertForm.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/preview")
	public String preview(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 배경 이미지 파일DTO
		SysFileVO fileVo = new SysFileVO();
		fileVo.setFileSn(vo.getImgFileSn());
		fileVo = sysFileService.getFile(fileVo);

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode(getMessage(request, "org.title.crscert"), "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
		response.setHeader("Content-Transper-Encoding", "binary");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

    	// step 1 : Document 생성
		Document document;
		if("HOR".equals(vo.getPrnDirec())) {
			document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 용지 및 여백 설정
		} else {
			document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
		}

		// step 2 : 백그라운드 이미지 설정
		String imgFilePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/" + fileVo.getSaveFilePath();
		Image background = Image.getInstance(imgFilePath);
		float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();

        // step 3 : PdfWriter 생성
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        // step 4 : Document Open
        document.open();

        // step 5 : Background Image 등록
		writer.getDirectContentUnder().addImage(background, width, 0, 0, height, 0, 0);

        // Measuring a String in Helvetica
        String fontPath = "";
        String userName = "";
        String locale = UserBroker.getLocaleKey(request);
        BaseFont bf_font;
        if("ko".equals(locale)) {
        	fontPath = request.getSession().getServletContext().getRealPath("/font/Batang.ttf");
        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); // IDENTITY_V, IDENTITY_H, WINANSI, UniKS-UCS2-H, UniGB-UCS2-H
        	userName = "홍 길 동";
        } else if("jp".equals(locale)) {
        	fontPath = request.getSession().getServletContext().getRealPath("/font/MSMINCHO.TTF");
        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        	userName = "秋田";
        } else {
            Font helvetica = new Font(FontFamily.HELVETICA, 12);
            bf_font = helvetica.getCalculatedBaseFont(false);
            userName = "Brad Pitt";
        }

        // step 6
		String args[] = new String[1];
		args[0]  = "2016-001-000001";
        String cpltNo 		= getMessage(request, "org.title.certificate.cpltno", args);
        String userNm 		= getMessage(request, "org.title.certificate.username")+" : "+userName;
        String birthDay		= getMessage(request, "org.title.certificate.birthday")+" : 2000.07.01";
        String crsNm 		= getMessage(request, "org.title.certificate.course")+" : Example Course";
        String crsPeriod 	= getMessage(request, "org.title.certificate.period")+" : 2016.07.12 ~ 2016.08.10";
        String crsTime		= getMessage(request, "org.title.certificate.time")+" : 10"+getMessage(request, "common.title.time");
        String prnDay 		= "2016.11.23";
        
        long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy.MM.dd");
		prnDay = dayTime.format(new Date(time));


        // Drawing lines to see where the text is added
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginText();
        canvas.setFontAndSize(bf_font, 13);
        //수료번호
        //canvas.showTextAligned(Element.ALIGN_LEFT, cpltNo, vo.getCpltnoX(), vo.getCpltnoY(), 0);
        canvas.setFontAndSize(bf_font, 19);
        int margin = 0;
        //출력일
        canvas.showTextAligned(Element.ALIGN_LEFT, prnDay, vo.getPrndayX(), vo.getPrndayY(), 0);
        //과정명
        //canvas.showTextAligned(Element.ALIGN_LEFT, crsNm, vo.getCrsNmX(), vo.getCrsNmY(), 0);
        //지식명
        canvas.setFontAndSize(bf_font, 25);
        canvas.showTextAligned(Element.ALIGN_CENTER, crsNm, vo.getCrsNmX(), vo.getCrsNmY(), 0);
        canvas.endText();

        document.close();
		writer.close();

		return null;
	}	
}
