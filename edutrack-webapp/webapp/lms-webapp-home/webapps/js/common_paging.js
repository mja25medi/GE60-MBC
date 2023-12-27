/**
 * 페이징 네비게이션 생성. jQuery 확장.
 * $(obj).pagingHtml(PageInfo, functionName, imagePath)
 * 페이징 html을 해당 개체에 새로 기록한다.
 * @author SungKook
 */ 
$.fn.pagingHtml = function(pageInfo, func_name, img_path) {
	
	var iTemp = 0;

	if (pageInfo.totalRecordCount < 1)
		return "";

	if (isNull(img_path))
		img_path = lmsImgPath+"/button";	// common_conf.js

	if (isNull(func_name))
		func_name = "goPage";
		
	var intStartPage = (Math.ceil(pageInfo.currentPageNo/pageInfo.pageSize)-1) * pageInfo.pageSize + 1;
	
	var page = "";
	if (intStartPage > 1) {
		page +=	"<a href=\"javascript:"
				+ func_name
				+ "("
				+ (intStartPage - pageInfo.pageSize)
				+ ",2)\" onMouseOver=\"window.status='Previous Page'; return true\" onMouseOut=\"window.status=''; return true\"><img src=\""
				+ img_path
				+ "/btn_prev_list.gif\" border=0 align=absmiddle></a>\n";
	} else {
		page +=	"<img src=\"" 
				+ img_path
				+ "/btn_prev_list.gif\" border=0 align=absmiddle>\n";
	}
	if (pageInfo.currentPageNo > 1) {
		page +=	"<a href=\"javascript:"
				+ func_name
				+ "("
				+ (pageInfo.currentPageNo - 1)
				+ ",2)\" onMouseOver=\"window.status='First Page'; return true\" onMouseOut=\"window.status=''; return true\"> <img src=\""
				+ img_path
				+ "/btn_prev.gif\" border=0 title='Prev Page' align=absmiddle></a>&nbsp;\n";
	} else {
		page +=	"<img src=\""
				+ img_path
				+ "/btn_prev.gif\" border=0 title=\"Prev Page\" align=absmiddle>&nbsp;\n";
	}

	for (var i = intStartPage; i < intStartPage + pageInfo.pageSize; i++) {
		iTemp = i;
		if (i <= pageInfo.totalPageCount) {
			if (i != pageInfo.currentPageNo) {
				page +=	"<a href=\"javascript:"
						+ func_name
						+ "("
						+ i
						+ ",2)\" onMouseOver=\"window.status='page "
						+ i
						+ "'; return true\" onMouseOut=\"window.status=''; return true\" class='b_list01'>"
						+ i + "</a>&nbsp;\n";
			} else {
				page +=	"<font style='color:red;font-weight:bold;font-size:11pt'>" + i
						+ "</font>&nbsp;\n";
			}
		} else {
			break;
		}
	}

	if (pageInfo.currentPageNo < pageInfo.totalPageCount) {
		page +=	"<a href=\"javascript:"
				+ func_name
				+ "("
				+ (pageInfo.currentPageNo + 1)
				+ ",2)\" onMouseOver=\"window.status='Next Page'; return true\" onMouseOut=\"window.status=''; return true\"> <img src=\""
				+ img_path
				+ "/btn_next.gif\" border=0 title='Next Page' align=absmiddle></a>\n";
	} else {
		page +=	"<img src=\""
				+ img_path
				+ "/btn_next.gif\" border=0 title='Next Page' align=absmiddle>\n";
	}
	if (iTemp + 1 <= pageInfo.totalPageCount) {
		page +=	"<a href=\"javascript:"
				+ func_name
				+ "("
				+ (iTemp + 1)
				+ ",2)\"  onMouseOver=\"window.status='Next Page'; return true\" onMouseOut=\"window.status=''; return true\"><img src=\""
				+ img_path
				+ "/btn_next_list.gif\" border=0 align=absmiddle></a>\n";
	} else {
		page +=	"<img src=\"" + img_path
				+ "/btn_next_list.gif\" border=0 align=absmiddle>\n";
	}

	this.html(page);	// 생성된 html을 기록
	return this;
};


