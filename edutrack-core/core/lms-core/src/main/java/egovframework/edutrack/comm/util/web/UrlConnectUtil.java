package egovframework.edutrack.comm.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UrlConnectUtil {

	private UrlConnectUtil(){}

	public static String getUrlConnect(String connectUrl, String args[]) {
		String result = "";
		BufferedReader rd = null;
		OutputStreamWriter wr = null;
		try {
			String data = "";
			for(String argString : args) {
				String[] arg = StringUtil.split(argString, "=");
				String key = arg[0];
				String val = arg[1];
				if(data != "") data += "&";
				data += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
			}
			URL url = new URL(connectUrl);

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (rd != null) {
					rd.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
