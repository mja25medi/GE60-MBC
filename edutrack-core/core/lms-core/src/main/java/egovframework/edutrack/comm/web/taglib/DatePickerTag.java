package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;

/**
 * 팝업 달력을 가지고 있는 날짜 폼을 출력해준다.
 * 아래와 같은 문장을 body태그 아래다 삽입해 주어야 한다.
 * @author 장철웅
 */
@SuppressWarnings("serial")
public class DatePickerTag extends TagSupport {

	private String name1;
	private String name2;
	private String icon;
	private String iconUse = "no";

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconUse() {
		return iconUse;
	}

	public void setIconUse(String iconUse) {
		this.icon = iconUse;
	}

	/**
	 * 날짜 입력 폼을 출력해준다.
	 */
	public int doEndTag() throws JspException {
		HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
		String lmsContext = req.getContextPath();
		String localeKey = UserBroker.getLocaleKey(req);
		String monthNamesSort = "['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dct']";
		String dayNamesMin="['Su','Mo','Tu','We','Th','Fr','Sa']";
		String showMonthAfterYear = "false";
		String iconPath = lmsContext+"/img/framework/icon/icon_calendar.gif";
		if(ValidationUtils.isNotEmpty(icon)) {
			iconPath = icon;
		}
		if("ko".equals(localeKey)) {
			monthNamesSort = "['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']";
			dayNamesMin="['일','월','화','수','목','금','토']";
			showMonthAfterYear = "true";
		} else if ("jp".equals(localeKey) || "cn".equals(localeKey)) {
			monthNamesSort = "['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']";
			dayNamesMin="['日','月','火','水','木','金','土']";
			showMonthAfterYear = "true";
		}
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<script language='javascript'>");
			sb.append("	$(document).ready(function() {");
			sb.append("		$('#"+name1+"').val(addDateFormatStr(toNumberStr($('#"+name1+"').val())));");
			sb.append("		$('#"+name1+"').datepicker({");
			sb.append("			dateFormat:'yy.mm.dd',");
			if("yes".equals(iconUse)) {
				sb.append("			showOn:'button',");
				sb.append("			buttonImage:'"+ iconPath +"',");
				sb.append("			buttonImageOnly:true,");
			}
			sb.append("			changeMonth:true,");
			sb.append("			changeYear:true,");
			sb.append("			monthNamesShort:"+ monthNamesSort +",");
			sb.append("			dayNamesMin:"+ dayNamesMin +",");
			sb.append("			showMonthAfterYear:"+ showMonthAfterYear +",");
			if(ValidationUtils.isNotEmpty(name2)) {
				sb.append("			onClose:function(selectedDate){ ");
				sb.append("				$('#"+name2+"').datepicker('option','minDate',selectedDate); ");
				sb.append("			}, ");
			}
			sb.append("			showButtonPanel:true");
			sb.append("		});");
			if(ValidationUtils.isNotEmpty(name2)) {
				sb.append("		$('#"+name2+"').val(addDateFormatStr(toNumberStr($('#"+name2+"').val())));");
				sb.append("		$('#"+name2+"').datepicker({");
				sb.append("			dateFormat:'yy.mm.dd',");
				if("yes".equals(iconUse)) {
					sb.append("			showOn:'button',");
					sb.append("			buttonImage:'"+ iconPath +"',");
					sb.append("			buttonImageOnly:true,");
				}
				sb.append("			changeMonth:true,");
				sb.append("			changeYear:true,");
				sb.append("			monthNamesShort:"+ monthNamesSort +",");
				sb.append("			dayNamesMin:"+ dayNamesMin +",");
				sb.append("			showMonthAfterYear:"+ showMonthAfterYear +",");
				sb.append("			showButtonPanel:true");
				sb.append("		});");

			}
			sb.append("	});");
			sb.append(" function _clickCalendar(str) { ");
			sb.append("     $('#'+str).focus(); ");
			sb.append(" } ");
			sb.append("</script>");
			pageContext.getOut().print(sb.toString());
		} catch (IOException ignored) { }
		return EVAL_PAGE;
	}
}
