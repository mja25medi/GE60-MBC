package egovframework.edutrack.comm.util.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.Constants;


/**
 * @author chorongjang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileUtil {

	static long result = 0;
	/**
	 *
	 */
	public FileUtil() {

	}

//	public static UploadFiles upload(HttpServletRequest req,String directory,String userid) throws Exception {
//	    return upload(req,directory,userid,"A");
//	}
//
//
//	/**
//	 * 파일 업로드 처
//	 * @param req
//	 * @param directory
//	 * @param userid
//	 * @return
//	 * @throws Exception
//	 */
//	public static UploadFiles upload(HttpServletRequest req,String directory,String userid, String namemode) throws Exception {
//	    return upload(req,directory,userid,namemode, ENCODING, MAX_POST_SIZE);
//	}
//	/**
//	 * 파일 업로드 처리
//	 * @param req
//	 * @param directory
//	 * @param userid
//	 * @param namemode
//	 * @param encoding
//	 * @return
//	 * @throws Exception
//	 */
//	public static UploadFiles upload(HttpServletRequest req,String directory,String userid, String namemode, String encoding) throws Exception {
//	    return upload(req,directory,userid,namemode, encoding, MAX_POST_SIZE);
//	}
//
//	/**
//	 * 파일업로드 처리
//	 */
//	public static UploadFiles upload(HttpServletRequest req,String directory,String userid, String namemode, String encoding, int maxsize) throws Exception {
//		// set multipart request
//		String uploadPath = directory;
//		String realPath = UPLOAD_PATH + uploadPath;
//		boolean check = setDirectory( realPath );
//		MultipartRequest multipartRequest = null;
//
//		// 업로드 파일에 대한 상태값과 파일 정보를 담아올 객체를 생성한다.
//		UploadFiles files = new UploadFiles();
//
//
//		try {
//			multipartRequest = new MultipartRequest( req, realPath, maxsize*1024*1024, encoding, new ByTimestampFileRenamePolicy(userid,namemode));
//			String fileName1 = "";
//			String fileSystemName1 = "";
//			String fileSystemNameExt1 = "";
//			File file1 = null;
//			UploadFile fileEntity1 = null;
//			Enumeration attachedFiles1 = multipartRequest.getFileNames();
//			while(attachedFiles1.hasMoreElements()){
//				fileName1 = (String)attachedFiles1.nextElement();
//				fileEntity1 = new UploadFile();
//				fileEntity1.setObjName(fileName1);
//				file1 = multipartRequest.getFile(fileName1);
//				if(file1 == null) continue;
//
//				fileSystemName1 = multipartRequest.getFilesystemName(fileName1);
//	    		// 업로드 할수 없는 확장자 체크
//	    		fileSystemNameExt1 = fileSystemName1.substring(fileSystemName1.lastIndexOf('.') + 1).toLowerCase().trim();
//				if (fileSystemNameExt1.equals("properties") || fileSystemNameExt1.equals("xml")
//						|| fileSystemNameExt1.equals("jsp") || fileSystemNameExt1.equals("java")
//						|| fileSystemNameExt1.equals("class") || fileSystemNameExt1.equals("sh")) {
//
//					System.out.println("특정파일업로드 시도[" + fileSystemName1 + "]: "+ req.getRemoteAddr());
//
//					//업로드 할 수 없는 파일이면 삭제한다.
//				    files.setStatus("E");
//				    delFile(uploadPath,fileSystemName1);
////				    return files;
//				}
//			}
//		}
//		catch( IOException e ) {
//		    // 업로드 시 작업 실패했을 경우 올렸던 파일을 삭제한다.
//		    files.setStatus("E");
//		    delFiles(files);
//		    return files;
//		}
//
//		// 파일 업로드를 처리한 멀티파트 객체를 넣어준다.
//		files.setMultipart(multipartRequest);
//		files.setUploadPath(uploadPath);
//		// get parameter via multipart request
//		String fileName = "fileName";
//		Enumeration attachedFiles = multipartRequest.getFileNames();
//
//	    if( attachedFiles == null ) {
//		    files.setStatus("N");
//	        return files;
//		}
//
//	    UploadFile fileEntity = null;
//	    File file = null;
//	    long itemSize = 0;
//	    String fileSystemName = "";
//	    long paramFileSize = 0;
//	    String fileSystemNameExt="";
//
//	    while(attachedFiles.hasMoreElements()){
//            fileName = (String)attachedFiles.nextElement();
//            fileEntity = new UploadFile();
//            fileEntity.setObjName(fileName);
//
//            file = multipartRequest.getFile(fileName);
//            if(file == null) continue;
//
//            fileSystemName = multipartRequest.getFilesystemName(fileName);
//            fileName = multipartRequest.getOriginalFileName(fileName);
//
//            paramFileSize = file.length();
//		    itemSize = file.length();
//
//    		// 업로드 할수 없는 확장자 체크
//		    fileSystemNameExt = fileSystemName.substring(fileSystemName.lastIndexOf('.') + 1).toLowerCase().trim();
//			if (fileSystemNameExt.equals("properties") || fileSystemNameExt.equals("xml")
//					|| fileSystemNameExt.equals("jsp") || fileSystemNameExt.equals("java")
//					|| fileSystemNameExt.equals("class") || fileSystemNameExt.equals("sh")) {
//			}else{
//				if( itemSize > MAX_POST_SIZE*1000*1024 ) {
//					// exceed item size
//					file.delete();
//					files.setStatus("O");
//					return files;
//				}else {	// 파일이 무사히 업로드 됨
//				    files.setStatus("S");
//
//		            fileEntity.setRootName(fileName);
//		            fileEntity.setUploadName(fileSystemName);
//		            fileEntity.setSize(paramFileSize);
//		            files.addFile(fileEntity);
//				}
//			}
//		}
//
//		return files;
//	}

	/**
	 * 디렉토리 삭제
	 * @param path
	 */
	public static void delDirectory(String path) {
		File dirFile = new File(path);
		String[] fileList = dirFile.list();

		String lastStr = "/";
		if (path.lastIndexOf("\\") > -1) {
			lastStr = "\\";
		}
		if (!path.substring(path.length()-1).equals(lastStr)) {
			path += lastStr;
		}

		if (dirFile.exists()) {
			for (int i = 0; i < fileList.length; i++) {
				File subFile = new File(path+fileList[i]);

				if (subFile.isDirectory()) {
					delDirectory(path+fileList[i]);
				}
				else {
					subFile.delete();
				}

			}
			dirFile.delete();
		}
	}

	/**
	 * 파일을 삭제한다.
	 * @param path
	 */
	public static  void	 delFile(String  path) {
	    File cFile = new File(path);
		if(cFile.exists()) cFile.delete();
	}

	/**
	 *  파일을 삭제해준다.
	 * @param path (pull path)
	 * @param filename
	 */
	public static  void	 delFile(String  path, String filename ) {
	    String dir = path +"/"+filename;
	    File cFile = new File(dir);
		if(cFile.exists()) cFile.delete();
	}

	/**
	 * 업로드 디렉토리 세팅
	 */
	public static boolean setDirectory(String directory) {
		File wantedDirectory = new File(directory);
		if (wantedDirectory.isDirectory())
			return true;

		return wantedDirectory.mkdirs();
	}

	/**
	 * 파일을  카피해준다.
	 * sourcedir : 원본 파일이 들어가 있는 디렉토리 예) file1/lmcourse
	 * targetdir   :   새로 생성할  파일이 들어가 있는 디렉토리 예) file1/lmsystem
	 * sourcefilename : 원본 파일명 예) lmcourse_00000000.gif
	 * targetfilename : 새로 생성할  파일명 예) lmsystem_00000000.gif
	 * @param sourcedir
	 * @param targetdir
	 * @param sourefilename
	 * @param targetfilename
	 * @return
	 */
//	public  static boolean fileCopy(String systemcode, String sourcedir, String targetdir, String sourefilename, String targetfilename){
//	      int i, len=0;
//	      InputStream in=null;
//	      OutputStream out=null;
//
//	      String sourcePath = UPLOAD_PATH + "file"+systemcode+ sourcedir;
//	      String targetPath = UPLOAD_PATH + "file"+systemcode + targetdir;
//
//	      if(!setDirectory(targetPath)) return false;
//
//	      sourcePath +="/"+sourefilename;
//	      targetPath += "/" + targetfilename;
//
//	        try {
//
//		       in = new FileInputStream(new File(sourcePath));
//
//		        out = new FileOutputStream(targetPath, true);
//
//	           while((i=in.read()) != -1) {
//	              out.write(i);
//	              len++;
//	           }
//
//	           in.close();
//	           out.close();
//
//	           return true;
//	        } catch(IOException e1) {
//	           e1.printStackTrace();
//	        }
//
//	    return false;
//	}

	/**
	 * 파일을  카피해준다. (풀페스)
	 * sourcedir : 원본 파일이 들어가 있는 디렉토리 예) file1/lmcourse
	 * targetdir   :   새로 생성할  파일이 들어가 있는 디렉토리 예) file1/lmsystem
	 * sourcefilename : 원본 파일명 예) lmcourse_00000000.gif
	 * targetfilename : 새로 생성할  파일명 예) lmsystem_00000000.gif
	 * @param sourcedir
	 * @param targetdir
	 * @param sourefilename
	 * @param targetfilename
	 * @return
	 */
	public static boolean fileCopy(String sourcePath, String targetPath){
		//복사 대상 파일 생성
		File sourceFile = new File(sourcePath);

		//스트림 체널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;

		try {
			//스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(targetPath);

			//채널생성
			inFileChannel = inputStream.getChannel();
			outFileChannel = outputStream.getChannel();

			//채널을 통한 스트림 전송
			long size =  inFileChannel.size();
			inFileChannel.transferTo(0, size, outFileChannel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원해제
			try {
				outFileChannel.close();
			} catch (IOException ioe) {}
			try {
				inFileChannel.close();
			} catch (IOException ioe) {}
			try {
				outputStream.close();
			} catch (IOException ioe) {}
			try {
				inputStream.close();
			} catch (IOException ioe) {}
		}
	   return false;
	}

    /**
     * 파일의 확장명 검색 함수
     * @param fname
     * @return
     */
	public static String	fn_file_ext(String fname) {
		if (!fname.equals("")) {
			int		lst_in			=	fname.lastIndexOf('.');
			String	ext				=	fname.substring(lst_in+1);
			return	ext.toLowerCase();
		}
		else {
			return	"";
		}
	}

	/**
	 * 파일 확장명에 따른 이미지 출력 함수
	 * @param fname
	 * @param fwhere
	 * @return
	 */
	public static String	fn_file_img(String fname, String fwhere) {
		String	ext					=	null;
		String	type_img;
		if (!fname.equals("")) {
			ext						=	fn_file_ext(fname);

			if (ext.equals("gz") || ext.equals("zip") || ext.equals("rar") || ext.equals("arj")
				|| ext.equals("lzh") || ext.equals("tar")) {
				type_img			=	"compressed";
			}
			else if (ext.equals("gif") || ext.equals("jpg") || ext.equals("bmp") || ext.equals("pcx")
				|| ext.equals("tif")) {
				type_img			=	"image";
			}
			else if (ext.equals("exe") || ext.equals("com") || ext.equals("dll")) {
				type_img			=	"exe";
			}
			else if (ext.equals("htm") || ext.equals("html")) {
				type_img			=	"html";
			}
			else if (ext.equals("hwp")) {
				type_img			=	"hwp";
			}
			else if (ext.equals("mov") || ext.equals("avi") || ext.equals("mpg") || ext.equals("mpeg")) {
				type_img			=	"movie";
			}
			else if (ext.equals("mp3")) {
				type_img			=	"mp3";
			}
			else if (ext.equals("rm") || ext.equals("ra") || ext.equals("ram")) {
				type_img			=	"ra";
			}
			else if (ext.equals("wav") || ext.equals("mod") || ext.equals("mid")) {
				type_img			=	"sound";
			}
			else if (ext.equals("txt") || ext.equals("log") || ext.equals("dat") || ext.equals("ini")) {
				type_img			=	"text";
			}
			else if (ext.equals("xls") || ext.equals("csv")) {
				type_img			=	"excel";
			}
			else if (ext.equals("doc")) {
				type_img			=	"word";
			}
			else if (ext.equals("ppt")) {
				type_img			=	"ppt";
			}
			else if (ext.equals("rlt")) {
				type_img			=	"rlt";
			}
			else {
				type_img			=	"unknown";
			}
			return	 fwhere+"common/file_type/"+ type_img + ".gif";
		}
		else {
			return	"";
		}
	}

    /**
     * 서버경로에서 파일명만 추출하는 함수
     * @param fname
     * @return
     */
	public static String	fn_file_name(String fname) {
		if (!fname.equals("")) {
			int		lst_in			=	fname.lastIndexOf('/');
			String	file_name		=	fname.substring(lst_in+1);
			return	file_name;
		}
		else {
			return	"";
		}
	}

    /**
     * 환경에 따라 서버 절대경로를 표시하는 함수
     * @return
     */
	public static String	fn_file_road() {
		String		result			=	"..";
		return		result;
	}

    /**
     * 컨텐츠 타입을 리턴하는 함수
     * @param str
     * @return
     */
	public static String	fn_file_type(String str) {
		String	file_type			=	fn_file_ext(str);
		if(file_type.equals("html") || file_type.equals("htm") || file_type.equals("HTML") || file_type.equals("HTM") || file_type.equals("jpg")|| file_type.equals("JPG") || file_type.equals("gif")|| file_type.equals("GIF") || file_type.equals("txt") || file_type.equals("TXT") || file_type.equals("yaz") || file_type.equals("YAZ")) {
			return "T";
		}
		if(file_type.equals("wmv") || file_type.equals("WMV") || file_type.equals("asf") || file_type.equals("ASF")) {
			return "V";
		}
		//--	Live Share File(추가)
		if(file_type.equals("lsa") || file_type.equals("LSA") || file_type.equals("lsv") || file_type.equals("LSV")) {
			return "L";
		}
		if(file_type.equals("ouc") || file_type.equals("OUC")) {
			return "O";
		}
		//--	ActiveTutor file(추가)
		if(file_type.equals("arf") || file_type.equals("ARF")) {
			return "A";
		}
		else {
			return "B";
		}
	}

	/**
	 * 주어진 클래스의 인스턴스를 만들어 반환
	 *
	 * @param className
	 * @return
	 */
	public static Object newInstance(String className)
	{
		Object instance = null;

		ClassLoader loader = FileUtil.class.getClassLoader();
		try
		{
			//instance = Class.forName(className).newInstance();
			instance = loader.loadClass(className).newInstance();

		} catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();

		} catch(IllegalAccessException iae)
		{
			iae.printStackTrace();

		} catch(InstantiationException ie)
		{
			ie.printStackTrace();

		}

		return instance;

	}

	/**
	 * 파일 확장자를 리턴, 확장자가 없으면 "" 를 리턴
	 *
	 * @param filename 파일명
	 * @return extention 파일명 확장자 스트링
	 */
	public static String getFileExtention(String filename)
	{
		String extention = "";

		if (filename != null)
		{
			int sepIndex = filename.lastIndexOf(".");
			if (sepIndex > 0 )
				extention = filename.substring(sepIndex);
		}
		return extention;
	}

   /**
    * 파일명을 리턴, 확장자가 없으면 "" 를 리턴
    *
    * @param file 파일
    * @return filename 파일명 스트링
    */
   public static String getFileName(String file)
   {
           String filename = "";

           if (file != null)
           {
                   int sepIndex = file.lastIndexOf(".");
                   if (sepIndex > 0 )
                           filename = file.substring(0,sepIndex);
           }
           return filename;
   }

   /**
    * 파일전체 경로중 파일명을 제외한 경로만 가져온다.
    * @param filePath
    * @return
    */
   public static String getFileDir(String filePath)
   {
           String dirName = "";

           if (filePath != null)
           {
                   int sepIndex = filePath.lastIndexOf("/");
                   if (sepIndex > 0 )
                   	dirName = filePath.substring(0,sepIndex);
           }
           return dirName;
   }

	public static void saveToFileParameter(HttpServletRequest request, String filename){
		request.setAttribute("output", "file");
		request.setAttribute("inline", "false");
		request.setAttribute("filename", filename);
	}

	public static void createFile(String path, String filename, String msg){
	    OutputStream out=null;
	    try {
	      setDirectory(path);
	      out = new FileOutputStream(path+"/"+filename, false);
	      out.write(msg.getBytes());
		  out.close();
	    } catch(Exception e) {
	      System.out.println(e);
	    }finally{
	    	if(out != null){
	    		try{
	    		   out.close();
	    		}catch(Exception e){
	    			 System.out.println(e);
	    		}
	    	}
	    }
	}

	public static void createDirectory(String path){
      setDirectory(path);
	}

	/**
	 * 파일 압축 풀기
	 * target을 입력하지 않을경우 source 경로에 압축을 푼다.
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean unZipFile(String source, String target) {
		try {

			ZipUtil zipUtil = new ZipUtil();
			File sourceFile = new File(source);
			File targetFile = new File(target);
			zipUtil.unzip(sourceFile, targetFile,"UTF-8");
			//zipUtil.unzip(sourceFile, targetFile,"KSC5601");
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 파일 압축 풀기 encoding 추가
	 * target을 입력하지 않을경우 source 경로에 압축을 푼다.
	 * @param source
	 * @param target
	 * @param encoding
	 * @return
	 */
	public static boolean unZipFile(String source, String target, String encoding) {
		try {
			
			ZipUtil zipUtil = new ZipUtil();
			File sourceFile = new File(source);
			File targetFile = new File(target);
			zipUtil.unzip(sourceFile, targetFile, encoding);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

   	/**
	 * 디렉토리, 파일 복사
	 * 지정된 위치에 있는 디렉토리와 파일들을 대상 위치로 모두 복사한다.
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean copyDirectoryFile(String source, String target) {
		boolean result = true;
		int bufSize 	= 4096;
		byte buf[] 	= new byte[bufSize];

		String delimeter = "/";
		if (source.lastIndexOf("\\") > -1) {
			delimeter = "\\";
		}

		setDirectory(target);

		File sourceFile = new File(source);
		if (sourceFile.isDirectory()) {
			File sourceFileList[] = sourceFile.listFiles();
			for (int i = 0; i < sourceFileList.length; i++) {
				File sFile 			= sourceFileList[i];
				String newSource	= sFile.getPath();
				String newTarget	= target + delimeter + sFile.getName();

				// 디렉토리
				if (sFile.isDirectory()) {
					copyDirectoryFile(newSource, newTarget);
				}
				// 파일
				else if (sFile.isFile()) {
					InputStream in = null;
					OutputStream out = null;

					try {
						in 	= new FileInputStream(sFile);
						out = new FileOutputStream(newTarget, true);
						int read 		= 0;

						while ((read = in.read(buf, 0, bufSize)) > 0) {
							out.write(buf, 0, read);
						}

						in.close();
						out.close();
					}
					catch (IOException e) {
						result = false;
					}
					finally {
						try {
							if (in != null) { in.close(); }
							if (out != null) {	out.close(); }
						} catch (IOException e) {
							result = false;
						}
					}
				}
			}
		}
		else if (sourceFile.isFile()) {
			InputStream in = null;
			OutputStream out = null;

			try {
				String newTarget	= target + delimeter + sourceFile.getName();
				in 	= new FileInputStream(sourceFile);
				out = new FileOutputStream(newTarget, true);
				int read 		= 0;

				while ((read = in.read(buf, 0, bufSize)) > 0) {
					out.write(buf, 0, read);
				}

				in.close();
				out.close();
			}
			catch (IOException e) {
				result = false;
			}
			finally {
				try {
					if (in != null) { in.close(); }
					if (out != null) {	out.close(); }
				} catch (IOException e) {
					result = false;
				}
			}
		}

		return result;
	}

	public static void sort(File[] filterResult) {
		Arrays.sort(filterResult, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				File file1 = (File)arg0;
				File file2 = (File)arg1;
				return file1.getName().compareToIgnoreCase(file2.getName());
			}
		});
	}

	/**
	 * 입력한 디렉토리의 사이즈를 구하여 반환한다.
	 * 파일을 입력시 단일 파일의 사이즈를 구하여 반환한다.
	 * @param dir
	 * @return
	 */
	public static long getTotalSize(String dir) {
		result = 0;
		File tf = new File(dir);
		if(tf.isDirectory()) {
			// -- 디렉토리일 경우에만 하위 파일 목록 찾아 길이 반환
			dirSize(tf);
		} else {
			//-- 파일의 경우에는 단일 파일의 사이즈만 반환
			result += tf.length();
		}
		return result;
	}

	/**
	 * 디렉토리 하위의 파일을 검색하여 사이즈를 증가 시킴.
	 * 제귀 호출을 통해 하위 디렉토리의 파일 사이즈까지 중가 시킬 수 있도록 구현.
	 * @param tarFile
	 */
	public static void dirSize(File tarFile) {
		try {
			File[] fileList = tarFile.listFiles();
			for(int i=0; i < fileList.length; i++) {
				if(fileList[i].isFile()) {
					result += fileList[i].length();
				} else {
					dirSize(fileList[i]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bytes 단위를 크기를 받아 KB, MB, GB, TB 형태로 변경하여 반환한다.
	 * @param bytes
	 * @return
	 */
	public static String fileSizeFormatter(long bytes) {
		String[] s = {"Bytes", "KB", "MB", "GB", "TB", "PB"};
		int e = (int)Math.floor(Math.log(bytes)/Math.log(1024));
		String fileSizeStr = "";
		if(bytes <= 0) fileSizeStr = "0 Bytes";
		else fileSizeStr = Math.round((bytes/Math.pow(1024, Math.floor(e))))+" "+s[e];
		return fileSizeStr;
	}
	/**
	 * 파일경로를 조회한다.
	 * @param bytes
	 * @return
	 */
	public static String fullFilePath(String fileType, String directory, String orgCd) {
		String saveFullPath="";
		if("contents".equals(fileType)) {
			saveFullPath = Constants.CONTENTS_STORAGE_PATH + "/"+orgCd+directory;
		} else if("ftp".equals(fileType)) {
			saveFullPath = Constants.CONTENTS_STORAGE_PATH + "/"+orgCd+directory;
		} else {
			saveFullPath = Constants.FILE_STORAGE_PATH + "/Z99999" + "/"+orgCd+directory;
		}
		return saveFullPath;
	}

}
