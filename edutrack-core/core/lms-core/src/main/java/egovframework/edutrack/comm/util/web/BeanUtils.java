package egovframework.edutrack.comm.util.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 빈을 직접 다루고자 할 때 사용.<br>
 * org.apache.commons.beanutils.BeanUtils를 상속 받았다.
 *
 * @author SungKook
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 빈의 값을 병합한다. src에서 dest로 값을 넣는다. 단 src에서 null인 항목은 제외시킨다.
	 * @param dest
	 * @param src
	 */
	// VO내에 객체가 있을 경우 오류 발생(describe). 추후 수정 필요(객체 처리, Exception 핸들링)
	@SuppressWarnings("unchecked")
	public static void mergeBean(Object dest, Object src) {
		try {
			Map<String, Object> srcMap = describe(src);
			Set<String> keySet = srcMap.keySet();


			for (String key : keySet) {
				if( ValidationUtils.isNotEmpty(srcMap.get(key)) ) {
					setProperty(dest, key, srcMap.get(key));
					//System.out.println(key+" : "+srcMap.get(key));
				}
			}
		} catch (IllegalAccessException ex) {
		} catch (InvocationTargetException ex) {
		} catch (NoSuchMethodException ex) {
		} catch (IllegalArgumentException ex) {
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void mergePropertyBean(Object dest, Object src) throws Exception {
		Map<String, Object> srcMap = PropertyUtils.describe(src);
		Set<String> keySet = srcMap.keySet();
		
		for(String key : keySet) {
			Object value = srcMap.get(key);
			if( ValidationUtils.isNotEmpty(value) ) {
				setProperty(dest, key, value);
			}
		}
	}
}
