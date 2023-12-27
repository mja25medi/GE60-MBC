package egovframework.edutrack.notification.sms.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Message_Common {

	protected static final String			SOCKET_LOCALE	= "euc-kr";

	protected final Log						log				= LogFactory.getLog(getClass());

	private Socket							sendSocket		= null;
	private final String					smsServerIP1	= "messenger.surem.com";
	private final String					smsServerIP2	= "messenger3.surem.com";
	private final int						smsServerPort	= 8080;

	protected void appendToBuffer(StringBuffer strBuff, String fieldData, int fieldLength) {
		int fieldDataLength = 0;
		try {
			fieldDataLength = fieldData.getBytes(SOCKET_LOCALE).length;
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		strBuff.append(fieldData);

		for (int inx = fieldLength - fieldDataLength; inx > 0; inx--)
			strBuff.append('\0');
	}

	protected int int2byte(int i) {

		i = (((i >> 24) & 0xFF) | ((i >> 8) & 0xFF00) | ((i << 8) & 0xFF0000) | ((i << 24) & 0xFF000000));

		return i;
	}

	protected Socket sConnect() throws SocketException {

		try {
			sendSocket = new Socket(smsServerIP1, smsServerPort); // 클라이언트측 소캣 생성
		} catch (IOException e) {
			try {
				sendSocket = new Socket(smsServerIP2, smsServerPort); // 클라이언트측 소캣 생성
			} catch (IOException se) {
				throw new SocketException(se.getMessage());
			}
		}

		return sendSocket;
	}

}
