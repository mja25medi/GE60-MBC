package egovframework.edutrack.comm.util.spring;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.util.DigestUtils;

/**
 * MD5 알고리즘을 이용한 랜덤문자열을 생성.
 * @author SungKook
 * 
 */
public class RandomStringUtil {

	private RandomStringUtil() {
	}

	public static String getRandomMD5() {
		String result = null;
		try {
			result = DigestUtils.md5DigestAsHex(String.valueOf(
					new Random().nextInt(1000000)).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("can not make random string");
		}
		return result;
	}

}
