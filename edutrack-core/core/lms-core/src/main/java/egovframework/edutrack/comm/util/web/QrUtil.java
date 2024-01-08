package egovframework.edutrack.comm.util.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import egovframework.edutrack.Constants;

public class QrUtil {
	
	
	public static String getCreateQrImage(String crsCreCd, String gubun, String orgCd) {
		
		String savePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/QR";
		//String savePath = "D:/EDUTRACK_GE60/DevelWAS/hrd/sm_hrd/WebRepository/attachfiles";
		String content = Constants.PRODUCT_DOMAIN;
        
        String fileName = "";
        try {
            //QR 생성
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            if(gubun.equals("in")) {
            	content = "/lec/attend/enterClass?crsCreCd="+crsCreCd;
            } else if (gubun.equals("out")) {
            	content = "/lec/attend/quitClass?crsCreCd="+crsCreCd;
            }
            
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 100, 100);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            fileName = crsCreCd + "_" + gubun;
            
            File file = new File(savePath);
            //파일 경로가 없으면 파일 생성
            if (!file.exists()) {
                file.mkdirs();
            }
            //파일 생성
            File temp = new File(savePath + "/" + fileName + ".png");

            //ImageIO를 사용하여 파일쓰기
            ImageIO.write(bufferedImage, "png", temp);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
        
		return "/attachfiles/"+orgCd+"/QR/"+ fileName+ ".png";
	}
	
	public static String getCreateQrNo(int loop,int nextInt) {
		 Random random = new Random();
	     String result = "";
	     
	     for(int i=0; i < loop; i++){
	    	 result +=  String.valueOf(random.nextInt(nextInt));
	     }
	     return result;
	}
	
}
