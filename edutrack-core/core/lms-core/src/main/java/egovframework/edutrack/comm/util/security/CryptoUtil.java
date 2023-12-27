package egovframework.edutrack.comm.util.security;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import egovframework.edutrack.comm.util.web.StringUtil;


/**
 * 암호화된 파라매터를 복호화해서 사용할 수 있도록 한다.<br>
 *
 * @author SungKook
 *
 */
@SuppressWarnings("restriction")
public class CryptoUtil {

	public final static String		_ERROR			= "_ERROR";

	private final static String	CHARSET_DEFAULT		= "8859_1";
	private final static String	CHARSET_SERVER		= "utf-8";
	private final static String	DELIMETER_NORMAL	= "!#!";
	//private final static String	DELIMETER_DATA		= "!@!";

	/**
	 * 2중 암호화된 파라매터 문자열을 복호화 해서 String[]로 반환한다.
	 */
	private static byte[] decode(String encodeString) {
		byte[] bytDecodedData;

		try {
			bytDecodedData = Base64.getDecoder().decode(encodeString);
			return bytDecodedData;
		} catch (Exception ex) {
			return null;
		}
	}

	// * Triple-DES Decryption Method
	private static String triDES_DecryptString(byte[] key, byte[] encMessage) {
		byte[] bytDecryptedData;
		SecretKey sKey;
		Cipher DecryptCipher;

		try {
			// 키를 설정한다.
			sKey = new SecretKeySpec(key, "DESede");

			// Cipher를 생성하고 초기화 한다.
			DecryptCipher = Cipher.getInstance("DESede/CBC/NoPadding");
			DecryptCipher.init(Cipher.DECRYPT_MODE, sKey, new IvParameterSpec(
					key, 0, 8));

			bytDecryptedData = DecryptCipher.doFinal(encMessage);
			return new String(bytDecryptedData, CHARSET_SERVER);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 클라이언트 정보를 복호화해서 배열로 반환.
	 * @param str
	 * @return
	 */
	public static String[] descrypt(String str) {
		String		strDescInfo	= "";
		String[]	aryValues	= null;
		String		ckData		= "";

		try {
			if (StringUtil.isNull(str)) return aryValues;	//

			strDescInfo = new String(decode(str), CHARSET_DEFAULT);
			aryValues = strDescInfo.split(DELIMETER_NORMAL);

			ckData		= aryValues[0];		// 복호화 키.
			strDescInfo	= aryValues[1];		// 복호화 대상.

			if (aryValues.length < 2) {
				aryValues = new String[]{ _ERROR, "복호화키, 값 분리에 실패했습니다."};
				return aryValues;
			}

			strDescInfo = triDES_DecryptString(ckData.getBytes(CHARSET_DEFAULT), strDescInfo.getBytes(CHARSET_DEFAULT));
			aryValues = strDescInfo.split(DELIMETER_NORMAL);

			for(int cnt=0; cnt < aryValues.length; cnt++) {
				aryValues[cnt] = URLDecoder.decode(aryValues[cnt].trim(), CHARSET_SERVER);
			}

			return aryValues;

		} catch (Exception ex) {
			aryValues = new String[] { _ERROR, "파라매터 복호화 과정에서 오류가 발생하였습니다." + ex.getMessage()};
			return aryValues;
		}
	}

	
	/**
	 * 랜덤 문자열을 생성해서 반환한다. 0~9, A~Z 범위내에서 length만큼 생성한다.
	 * @param length
	 * @return
	 */
	static public String makeRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		
		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9".split(",");
		
		for ( int i=0 ; i<length ; i++ ) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	/**
	 * SHA256 Hashing
	 * @param str
	 * @return
	 */
	public static String SHA256Encode(String str) 
	{
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			for (int i = 0; i < byteData.length; i++)
			{
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
			sb.append("Error");
		}
		
		return sb.toString();
	}
	
	/**
	 * SHA512 Hashing - 이니시스 추가
	 * @param str
	 * @return
	 */
	public static String SHA512Encode(String data_hash) {
		String salt = data_hash;
		String hex = null;

		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(salt.getBytes());
			hex = String.format("%128x", new BigInteger(1, msg.digest()));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("암호화 에러");
		}
		
		return hex;
	}
	
}
