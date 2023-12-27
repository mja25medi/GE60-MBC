package egovframework.edutrack.comm.util.web;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResultMap extends FastHashMap {
	
	public Object put(Object key, Object value) {
		return super.put(key.toString().toLowerCase(), value);
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, false);
	}

}
 