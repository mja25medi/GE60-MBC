package egovframework.edutrack.comm.util.security;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class ApiSecurityUtils {
	
	// Base64 암호화
	public static String encryptBase64(String str) {
		
		byte[] encoded = Base64.encodeBase64(str.getBytes());
		
		return new String(encoded);
	}
	
	// Base64 복호화
	public static String decryptBase64(String str) {
		
		byte[] decoded = Base64.decodeBase64(str.getBytes());
		
		return new String(decoded);
	}

	// AES128 암호화
	public static String encryptAes128(String str, String key) {
		byte[] encrypted = null;
		if (str != null) {
		    try {
		    	SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
	    	    Cipher cipher = Cipher.getInstance("AES");
	    	    cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
	    	    encrypted = cipher.doFinal(str.getBytes());
		    } catch (Exception e) {
		    	System.out.println(e);
		    }
		}
		
		return Hex.encodeHexString(encrypted);
	}
	
	// AES128 복호화
	public static String decryptAes128(String str, String key) {
		byte[] original = null;
		byte[] encrypted = null;
		String result = null;
		if (str != null ) {
		    try {
		    	encrypted = Hex.decodeHex(str.toCharArray());
		    	SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
		    	Cipher cipher = Cipher.getInstance("AES");
		    	cipher.init(Cipher.DECRYPT_MODE,skeySpec);
		    	original = cipher.doFinal(encrypted);
		    	result = new String(original);
		    } catch (Exception e) {
		    	System.out.println(e);
		    }
		}
		
		return result;
	}
}
