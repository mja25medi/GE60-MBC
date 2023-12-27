package egovframework.edutrack.modules.kollus.util;

import com.kollus.crypt.MediaToken;

public class KollusMediaTokenUtil {

	public static String getKollusMediaToken(String security_key, String media_content_key, String media_profile_key, String client_user_id) {
		String awt_code = null;   // 사용하지 않는 경우 null을 지정합니다.
		int expire_time = 3600; // 1 Hour
		boolean playlist_flag = false;

		String mediaToken = MediaToken.ByUserId(security_key , media_content_key , media_profile_key, awt_code, client_user_id, expire_time, playlist_flag);
		int errorCode = 0;
		if(mediaToken.length() < 20) {
			System.out.print("ERROR: ");
			System.out.println(mediaToken);
			errorCode = Integer.valueOf(mediaToken);
			System.out.println("Error: " + getCryptErrorCode(errorCode));
		} else {
			System.out.print("SUCCESS: ");
			System.out.println(mediaToken);
		}
		return mediaToken;
	}

	public static String getCryptErrorCode(int error) {
		String msg = "Unknown error";
		switch(error) {
		case 7700:
			msg = "입력 파라미터 오류, 버전 정보 누락 오류";
			break;
		case 7701:
			msg = "버전정보 오류, input parameter의 오류 or 암호화할 텍스트 길이 : 2048byte 제한";
			break;
		case 7702:
			msg = "복호화할 텍스트 길이 : 4096byte 제한";
			break;
		case 7703:
			msg = "암호화에 사용될 키 길이 : 16byte 제한";
			break;
		case 7704:
			msg = "security key 길이 : 16byte 제한";
			break;
		case 7705:
			msg = "media content key 길이 : 16byte 제한";
			break;
		case 7706:
			msg = "media profile key 길이 : 64byte 제한";
			break;
		case 7707:
			msg = "워터마크 코드 validation 체크 : 버전정보 없거나 16진수가 아닌경우";
			break;
		case 7708:
			msg = "User ID 길이 : 255byte 제한";
			break;
		case 7709:
			msg = "User Key 길이 : 2048byte 제한";
			break;
		case 7710:
			msg = "mediakey_expire_time : 0 이상";
			break;
		}
		return msg;
	}

}
