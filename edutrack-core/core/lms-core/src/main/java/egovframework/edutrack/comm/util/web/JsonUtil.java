package egovframework.edutrack.comm.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class JsonUtil {

	private static final Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * Json String을 Object 형태로 변환 하여 돌려 준다.
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> getJsonObject(String jsonString) {
		Map<String, Object> result = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
        try {
            //convert JSON string to Map
        	result = mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}

	/**
	 * 일반 인스턴스를 Json 형태로 변경해서 반환한다.
	 * @param obj json으로 변경하고자 하는 인스턴스
	 * @return
	 */
	public static String getJsonString(Object obj) {
		return getJsonString(obj, null);
	}

	/**
	 * 일반 인스턴스를 Json 형태로 변경해서 반환한다.
	 * JsonConfig를 이용해서 출력하고자 하는 맴버를 필터링 할 수 있다.
	 * <pre>
	 * JsonConfig jsonConfig = new JsonConfig();
	 *   jsonConfig.setRootClass( BeanA.class );
	 *   jsonConfig.setJavaPropertyFilter( new PropertyFilter(){
	 *     public boolean apply( Object source, String name, Object value ) {
	 *       if( "bool".equals( name ) || "integer".equals( name ) ){
	 *         return true;
	 *       }
	 *       return false;
	 *     }
	 *   });
	 * </pre>
	 * @param obj json으로 변경하고자 하는 인스턴스
	 * @return
	 */
	public static String getJsonString(Object obj, JsonConfig jsonConfig) {
		String json = "";
		if (obj == null)
			return null;

		if (obj instanceof List<?>) {
			json = (jsonConfig != null) ? JSONArray.fromObject(obj, jsonConfig).toString() : JSONArray.fromObject(obj).toString();
		} else {
			json = (jsonConfig != null) ? JSONObject.fromObject(obj, jsonConfig).toString() : JSONObject.fromObject(obj).toString();
		}

		if(log.isDebugEnabled())
			log.debug("JSON String : " + obj + "->" + json);
		return json;
	}
	
	/**
	 * Response에서 writer를 구해서 문자열을 출력한다.
	 * @param response 응답 인스턴스
	 * @param string 브라우져로 보내고자 하는 문자열
	 */
	public static void responseWrite(HttpServletResponse response, String string) {

		 response.setHeader("Pragma", "no-cache");
		 response.setHeader("Expires", "0");
		 response.setHeader("Cache-Control", "no-cache");
		 response.setContentType("application/json;charset=utf-8");

		try {
			PrintWriter writer = response.getWriter();
			writer.print(string);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Response에 인스턴스를 json으로 변경해서 출력. 메시지 처리를 하지 않는다.
	 * @param response 응답 인스턴스
	 * @param obj json으로 보내고자 하는 인스턴스
	 */
	public static String responseJson(HttpServletResponse response, Object obj) {
		responseWrite(response, getJsonString(obj));
		return null;
	}
	
	/**
	 * Response에 인스턴스를 json으로 변경해서 출력. 메시지 처리를 하지 않는다. <br>
	 * JsonConfig를 이용해서 전달 프로퍼티를 제어한다.
	 *
	 * @see JsonUtil#getJsonString(Object, JsonConfig)
	 *
	 * @param response 응답 인스턴스
	 * @param obj json으로 보내고자 하는 인스턴스
	 */
	public static String responseJson(HttpServletResponse response, Object obj, JsonConfig jsonConfig) {
		responseWrite(response, getJsonString(obj, jsonConfig));
		return null;
	}	
	
	private static String jsonReadAll(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = reader.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	public static String readJsonFromUrl(String url) throws IOException, JSONException {
		StringBuilder result = new StringBuilder();
		try {
			URL clsUrl = new URL(url);
			HttpURLConnection clsConn = (HttpURLConnection)clsUrl.openConnection();
			clsConn.setRequestMethod("GET");
			clsConn.setRequestProperty("Cache-Control", "no-cache");
			clsConn.setRequestProperty("Content-Type", "application/json");
			clsConn.setRequestProperty("Accept", "application/json");
			//clsConn.setDoOutput(true);
			// InputStream으로 서버로 부터 응답을 받겠다는 옵션.
			clsConn.setDoInput(true);
			clsConn.connect();
			
//			OutputStreamWriter clsOutput = new OutputStreamWriter(clsConn.getOutputStream());
//			clsOutput.write(param.toString());
//			clsOutput.flush();
			int HttpResult =clsConn.getResponseCode(); 
			if(HttpResult ==HttpURLConnection.HTTP_OK){
				BufferedReader clsInput = new BufferedReader(new InputStreamReader(clsConn.getInputStream(),"utf-8"));  
				try {
				String inputLine;
				
				while((inputLine = clsInput.readLine()) != null) {
					result.append(inputLine);
				}
				clsInput.close();
				}  catch (Exception e) {
					   e.printStackTrace();
				} finally {clsInput.close();}
			}
//			clsOutput.close();
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {}
		
		return result.toString();
		
	}
	
	public static String getJsonStringHrdApi(List<EgovMap> egovList) {
		ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", egovList);
        String jsonObj = "";
        try {
			jsonObj = objectMapper.writeValueAsString(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonObj;
	}
}
