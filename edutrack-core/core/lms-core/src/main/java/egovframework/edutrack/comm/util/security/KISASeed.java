package egovframework.edutrack.comm.util.security;

import java.util.Base64;

import KISA.SHA256;
import KISA.SeedCBC;
import egovframework.edutrack.Constants;

@SuppressWarnings("restriction")
public class KISASeed {
	/**
	 문자열 암호화 (블록)
	 * @return
	 * @param str
	 */
	public static String seedEncryption(String str) {
		String result = "";
		try{
			// KISA  주민번호 블록 암호화
			SeedCBC s = new SeedCBC();
			String retMsg = s.LoadConfig(Constants.KISAKEYFILE+"/key.dat");
			if (retMsg.equals("OK")) {
				byte[] bCipherText = s.Encryption(str.getBytes());
				Base64.getEncoder().encodeToString(bCipherText);
			}
		}catch(Exception e){}
		return result;
	}


	/**
	 문자열 복호화 (블록)
	 * @return
	 * @param str
	 */
	public static String seedDecryption(String str) {
		String result = "";
		try{
			byte[] bCipherText = Base64.getDecoder().decode( str );
			SeedCBC s = new SeedCBC();
			String retMsg = s.LoadConfig(Constants.KISAKEYFILE+"/key.dat");
			if (retMsg.equals("OK")) {
				byte[] bPlainText = s.Decryption( bCipherText );
				result = (new String(bPlainText));
			}
		}catch(Exception e){}
		return result;
	}

	/**
	 문자열 암호화 (hash)
	 * @return
	 * @param str
	 */
	public static String seed256HashEncryption(String str) {
		String result = "";
		try{
			SHA256 s = new SHA256( str.getBytes() );
			result = Base64.getEncoder().encodeToString(s.GetHashCode());
		}catch(Exception e){}
		return result;
	}
}
