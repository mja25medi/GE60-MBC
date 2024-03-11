package egovframework.edutrack.web.manage.org;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.crscert.service.OrgCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCertVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/crsCert")
public class OrgCrsCertManageController extends GenericController {

	@Autowired @Qualifier("orgCertService")
	private OrgCertService 		orgCertService;

	@Autowired @Qualifier("sysFileService")
	private SysFileService 		sysFileService;
	
	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;
	
	@Autowired @Qualifier("studentService")
	private StudentService studentService;
	
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 과정 수료증 관리 메인 화면으로 이동한다. 
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/crscert/edit_cert_main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editCertMain")
	public String main(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		List<OrgCodeVO> certContnetList = orgCodeService.listCode(vo.getOrgCd(), "CERT_CONTENT_CD").getReturnList();
		request.setAttribute("certContnetList", certContnetList);
		
		vo = orgCertService.view(vo);
		if(vo != null) {
			request.setAttribute("gubun", "E");
		}else {
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
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCertVO> result = new ProcessResultVO<OrgCertVO>();
		try {
			orgCertService.add(vo);
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
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCertVO> result = new ProcessResultVO<OrgCertVO>();
		try {
			orgCertService.edit(vo);
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
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCertVO> result = new ProcessResultVO<OrgCertVO>();
		try {
			orgCertService.edit(vo);
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
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/crscert/editCertForm.jsp"
	 * @throws Exception
	 * 현재 사용x
	 */
	@RequestMapping(value="/previewX")
	public String preview(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 배경 이미지 파일DTO
		SysFileVO fileVo = new SysFileVO();
		fileVo.setFileSn(vo.getFileSn());
		fileVo = sysFileService.getFile(fileVo);

		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode(getMessage(request, "org.title.crscert"), "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
		response.setHeader("Content-Transper-Encoding", "binary");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

    	// step 1 : Document 생성
		Document document = null;
		
		 if("HOR".equals(vo.getPrintDirec())) { 
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
      //  canvas.showTextAligned(Element.ALIGN_LEFT, prnDay, vo.getPrndayX(), vo.getPrndayY(), 0);
        //과정명
        //canvas.showTextAligned(Element.ALIGN_LEFT, crsNm, vo.getCrsNmX(), vo.getCrsNmY(), 0);
        //지식명
        canvas.setFontAndSize(bf_font, 25);
       // canvas.showTextAligned(Element.ALIGN_CENTER, crsNm, vo.getCrsNmX(), vo.getCrsNmY(), 0);
        canvas.endText();

        document.close();
		writer.close();

		return null;
	}	
	
	/**
     * @Method Name : preview
     * @Method 설명 : 기관 과정 수료증 미리보기
	 * @param OrgCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/crscert/editCertForm.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/preview")
	public String previewTest(OrgCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		/*수료증 정보*/
		OrgCertVO orgCertVO = new OrgCertVO();
		orgCertVO.setOrgCd(orgCd);
		orgCertVO = orgCertService.view(orgCertVO);
		
		StudentVO studentVO = new StudentVO();
		
		/*수료증 내용 JSON*/
		String certContentJson = orgCertVO.getCertContentJson();
	
		/*학습자 정보 Map */
		HashMap<String, Object> stdCertInfoMap = studentService.selectStdCertInfo(studentVO);
		
		try {
			// 파일 다운로드 설정
			String fileName = URLEncoder.encode("수료증", "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
			response.setHeader("Content-Transper-Encoding", "binary");
			response.setContentType("application/pdf");
			//response.setContentType("application/octet-stream"); // 다운로드 형태로 변경
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
			
			// step 1 : Document 생성
			Document document;
			if("HOR".equals(orgCertVO.getPrintDirec())) {
				document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 용지 및 여백 설정
			} else {
				document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
			}
			
			// step 2 : 백그라운드 이미지 설정
			String filePathBack = "";
			String repoPathBack = "";
			String fileSaveNmBack = "";
			
			List<SysFileVO> fileListBack = orgCertVO.getAttachFiles();
			
			for(int j = 0; j < fileListBack.size(); j++) {
				 SysFileVO sfvoBack = fileListBack.get(j);
				 filePathBack = sfvoBack.getFilePath();
				 repoPathBack = sfvoBack.getRepoPath();
				 fileSaveNmBack = sfvoBack.getFileSaveNm();
			}
			
			String backImgFilePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/" + repoPathBack  + filePathBack + "/"  + fileSaveNmBack; 
			Image background = Image.getInstance(backImgFilePath);
			float widthBack = document.getPageSize().getWidth();
	        float heightBack = document.getPageSize().getHeight();
	
	        // step 3 : PdfWriter 생성
	        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
	        
	        // step 4 : Document Open
	        document.open();
	               
	        // Measuring a String in Helvetica
	        String fontPath = "";
	        String locale = UserBroker.getLocaleKey(request);
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
	        
	        document.newPage();
	        writer.getDirectContentUnder().addImage(background, widthBack, 0, 0, heightBack, 0, 0);
	        
	        // Drawing lines to see where the text is added
	        PdfContentByte canvas = writer.getDirectContent();
	        canvas.beginText();
	        	     	     
			/* 수료증내용과 학습자 정보 셋팅 */
			Iterator<String> iteratorK = stdCertInfoMap.keySet().iterator();
			
			while (iteratorK.hasNext()) {
				
				String key = iteratorK.next();
				String value = (String) stdCertInfoMap.get(key);
				
				JSONParser jsonParser = new JSONParser();
				
				Object obj = jsonParser.parse(certContentJson);
				
				JSONArray jsonArr = (JSONArray)obj;
				
				for(int i=0;i<jsonArr.size();i++){
	
					JSONObject jsonObj = (JSONObject)jsonArr.get(i);
					
					if(jsonObj.get(key) == null){
						continue;
					}
					
					String certContent = jsonObj.get(key).toString();
					
					JSONParser jsonParser2 = new JSONParser();
					JSONObject jsonObj2 = (JSONObject)jsonParser2.parse(certContent);
					
					String displayName;
					
					if(key.equals("cpltNo")) {
						displayName = "제"+ value + "호";
					}else if(key.equals("cpltDttm")) {
						String year = StringUtil.substring(value, 0, 4);
						String month = StringUtil.substring(value, 4, 6);
						String day = StringUtil.substring(value, 6, 8);
						displayName = year+"년 "+month+"월 "+day+"일";
					}else if(key.equals("onlnEduTm")) {
						displayName = (String)jsonObj2.get("displayName") + " : " + value + "시간";
					}else {
						displayName = (String)jsonObj2.get("displayName") + " : " +  value;
					}
							
					String width = (String)jsonObj2.get("width");
					String height = (String)jsonObj2.get("height");
					String fontSize = (String)jsonObj2.get("fontSize");
				
					canvas.setFontAndSize(bf_font, Float.parseFloat(fontSize));
					canvas.showTextAligned(Element.ALIGN_LEFT, displayName, Float.parseFloat(width), document.top()-Float.parseFloat(height), 0);
					
				} 
			}
			
			//직인
	        String filePathStamp = "";
			String repoPathStamp = "";
			String fileSaveNmStamp = "";
			
			List<SysFileVO> fileListStamp = orgCertVO.getAttachFiles2();
			
			for(int j = 0; j < fileListStamp.size(); j++) {
				
				SysFileVO sfvoStamp = fileListStamp.get(j);
				filePathStamp = sfvoStamp.getFilePath();
				repoPathStamp = sfvoStamp.getRepoPath();
				fileSaveNmStamp = sfvoStamp.getFileSaveNm();
			}
			
			String stampImgFilePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/" + repoPathStamp + filePathStamp + "/"  + fileSaveNmStamp; 
			Image stamp = Image.getInstance(stampImgFilePath);
			float widthStamp = document.getPageSize().getWidth();
	        float heightStamp = document.getPageSize().getHeight();
	        
	        //수료증 기관 코드
	        List<OrgCodeVO> certOrgList = orgCodeService.listCode(orgCertVO.getOrgCd(), "CERT_ORG_CD").getReturnList();
			
	        //수료증 상단 타이틀
	        
	        if("HOR".equals(orgCertVO.getPrintDirec())) {
	        	canvas.addImage(stamp, 80, 0, 0, 80, 460, 40);
		        canvas.setFontAndSize(bf_font, 30);
		        canvas.showTextAligned(Element.ALIGN_LEFT,  "수 료 증", 365, 500, 0);
		        
		        for(int i=0; i < certOrgList.size(); i++) {
		        	String codeCd = certOrgList.get(i).getCodeCd();
		        	String codeOptn = certOrgList.get(i).getCodeOptn();
		        	
		        	if("certOrgCts".equals(codeCd)) {	 
		        		//수료증 문구
		        		canvas.setFontAndSize(bf_font, 15);
		     	       // canvas.showTextAligned(Element.ALIGN_LEFT, codeOptn, 150, 220, 0);
		     	        
		 	        		String proofCts[] = null;
			        		int proofCtsPostion = 220;
		 	        		if(codeOptn.indexOf("<BR/>") >-1) {
			        			proofCts = codeOptn.split("<BR/>");
			        			for(int k=0;k<proofCts.length;k++) {
			        				canvas.showTextAligned(Element.ALIGN_LEFT, proofCts[k], 150, proofCtsPostion, 0);
			        				proofCtsPostion=proofCtsPostion-20;
			        			}
			        		}else {
			        			canvas.showTextAligned(Element.ALIGN_LEFT, codeOptn, 150, 220, 0);
			        		}
		        	}else if("certOrgNm".equals(codeCd)) {	 
		        		//수료증 발급기관
		        		canvas.setFontAndSize(bf_font, 30);
		     	        canvas.showTextAligned(Element.ALIGN_LEFT, codeOptn, 300, 100, 0);
		        	}
		        }
	        }else {
	        	canvas.addImage(stamp, 80, 0, 0, 80, 360, 70);
	        	canvas.setFontAndSize(bf_font, 35);
	            canvas.showTextAligned(Element.ALIGN_LEFT, "수 료 증", 235, 630, 0);
	             
	            for(int i=0; i < certOrgList.size(); i++) {
	            	String codeCd = certOrgList.get(i).getCodeCd();
	 	        	String codeOptn = certOrgList.get(i).getCodeOptn();
	 	        	
	 	        	if("certOrgCts".equals(codeCd)) {	 
	 	        		//수료증 문구
	 	        		canvas.setFontAndSize(bf_font, 15);
	 	        		String proofCts[] = null;
		        		int proofCtsPostion = 290;
	 	        		if(codeOptn.indexOf("<BR/>") >-1) {
		        			proofCts = codeOptn.split("<BR/>");
		        			for(int k=0;k<proofCts.length;k++) {
		        				canvas.showTextAligned(Element.ALIGN_LEFT, proofCts[k], 60, proofCtsPostion, 0);
		        				proofCtsPostion=proofCtsPostion-20;
		        			}
		        		}else {
		        			canvas.showTextAligned(Element.ALIGN_CENTER, codeOptn, 300, 400, 0);
		        		}
	 	        	}else if("certOrgNm".equals(codeCd)) {	 
	 	        		//수료증 발급기관
	 	        		canvas.setFontAndSize(bf_font, 30);
	 	        		canvas.showTextAligned(Element.ALIGN_LEFT, codeOptn, 185, 100, 0);
	 	        	}
	 	        }
	        }
       		canvas.endText();
       		document.close();
       		writer.close();
       		return null;
       		
		} catch (Exception e) {
			throw new ServiceProcessException("PDF file creation failed!", e);
		}
	}
}
