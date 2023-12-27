package egovframework.edutrack.comm.util.web;

import java.text.DecimalFormat;
import java.text.MessageFormat;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PaginationRenderer extends AbstractPaginationRenderer {
	public static String tagStartLabel;
	public static String tagStartEndLabel;
	
	public static String tagMidLabel;
	public static String tagMidEndLabel;
	
	public static String tagLastLabel;
	public static String tagLastEndLabel;

	public static String disabledPreviousPageLabel;
	public static String disabledNextPageLabel;
	public static String disabledFirstPageLabel;
	public static String disabledLastPageLabel;
	public static String pageSeparateLabel;
	
	// totalcount 출력을 위한 변수
	public static String totalCountLabel;
	public static DecimalFormat df;

	public PaginationRenderer() {
/*		
		tagStartLabel             = "<div class=\"paging-box\">\n";
		disabledFirstPageLabel    = "<span class=\"first off\">&nbsp;{1}&nbsp;</span>";
		firstPageLabel            = "<span class=\"first\"><a href=\"#goFirstPage\" onclick=\"{0}({1}); return false;\">&nbsp;{1}&nbsp;</a></span>";
		disabledPreviousPageLabel = "<span class=\"pre off\">&nbsp;이전&nbsp;</span>";
		previousPageLabel         = "<span class=\"pre\"><a href=\"#goPreviousPage\" onclick=\"{0}({1}); return false;\">&nbsp;이전&nbsp;</a></span>";
		currentPageLabel          = "<span><strong>&nbsp;{0}&nbsp;</strong></span>";
		otherPageLabel            = "<span><a href=\"#goPage{1}\" onclick=\"{0}({1}); return false;\">&nbsp;{2}&nbsp;</a></span>";
		nextPageLabel             = "<span class=\"next\"><a href=\"#goNextPage\" onclick=\"{0}({1}); return false;\">&nbsp;다음&nbsp;</a></span>";
		disabledNextPageLabel     = "<span class=\"next off\">&nbsp;다음&nbsp;</span>";
		lastPageLabel             = "<span class=\"last\"><a href=\"#goLastPage\" onclick=\"{0}({1}); return false;\">&nbsp;{1}&nbsp;</a></span>";
		pageSeparateLabel         = "<span class=\"separate\">&nbsp;...&nbsp;</span>";
		tagEndLabel               = "</div>\n";
*/		

		tagStartLabel             = "<div class=\"pagePrev pageCtrl\">\n";
		disabledFirstPageLabel    = "<div class=\"pages\"><li>{1}</li></div>";
		firstPageLabel            = "<div class=\"pages\"><li><a href=\"#goFirstPage\" onclick=\"{0}({1}); return false;\">{1}</a></li></div>";
		disabledPreviousPageLabel = "<div class=\"prev\"><a href=\"blank\" onclick=\"return false;\" style=\"cursor:default\">이전페이지가 없습니다</a></div>";
		previousPageLabel         = "<div class=\"prev\"><a href=\"#goPreviousPage\" onclick=\"{0}({1}); return false;\">이전</a></div>";
		tagStartEndLabel          = "</div>\n";
		
		tagMidLabel               = "<div class=\"pages\">\n";
		currentPageLabel          = "<li><strong>{0}</strong></li>";		
		otherPageLabel            = "<li><a href=\"#goPage{1}\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		tagMidEndLabel            = "</div>\n";
		
		tagLastLabel              = "<div class=\"pageNext pageCtrl\">\n";
		nextPageLabel             = "<div class=\"next\"><a href=\"#goNextPage\" onclick=\"{0}({1}); return false;\">다음</a></div>";
		disabledNextPageLabel     = "<div class=\"next\"><a href=\"blank\" onclick=\"return false;\" style=\"cursor:default\">다음페이지가 없습니다</a></div>";
		lastPageLabel             = "<div class=\"pages\"><li><a href=\"#goLastPage\" onclick=\"{0}({1}); return false;\">{1}</a></li></div>";
		disabledLastPageLabel     = "<div class=\"pages\"><li>{1}</li></div>";
		pageSeparateLabel         = "<div class=\"separate\">&nbsp;...&nbsp;</div>";
		tagLastEndLabel           = "</div>\n";

		
		//total count 출력을 위한 변수
		//totalCountLabel = "<div class=\"count-box\"><span>총 : <span class=\"num\">{0}</span>건</span></div>\n";
		totalCountLabel = "\n";
		df = new DecimalFormat("###,###,###,###,###,###");
	
	}
	
	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		if (paginationInfo == null) return ""; // NULL Pointer Dereference 처리
		
		StringBuffer strBuff = new StringBuffer();
        
        int firstPageNo = paginationInfo.getFirstPageNo();
        int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
        int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();
		int pageHalfSize = (int)Math.floor(pageSize / 2);
		int totalRecordCount = paginationInfo.getTotalRecordCount();
		
		//int pageOffset = (currentPageNo - (int)Math.floor(pageSize / 2)) < 0 ? 0 : currentPageNo - (int)Math.floor(pageSize / 2) - 1;
		//firstPageNoOnPageList = firstPageNo + pageOffset;
		//lastPageNoOnPageList = pageSize + pageOffset;
		//if (lastPageNoOnPageList > lastPageNo) lastPageNoOnPageList = lastPageNo;
		//if (lastPageNoOnPageList - pageSize < firstPageNoOnPageList) firstPageNoOnPageList = la
		if (currentPageNo > pageHalfSize) {
			firstPageNoOnPageList = currentPageNo - pageHalfSize;
			lastPageNoOnPageList = currentPageNo + pageHalfSize;
		} else {
			firstPageNoOnPageList = firstPageNo;
			lastPageNoOnPageList = pageSize;
		}
		
		if (lastPageNoOnPageList >= lastPageNo) {
			firstPageNoOnPageList = lastPageNo - pageSize + 1;
			lastPageNoOnPageList = lastPageNo;
		}
		
		if (firstPageNoOnPageList < 0) firstPageNoOnPageList = 1;
		
		//strBuff.append(MessageFormat.format(totalCountLabel,new Object[]{df.format(totalRecordCount)}));
		strBuff.append(tagStartLabel);
		
		if(currentPageNo > firstPageNo){
			strBuff.append(MessageFormat.format(previousPageLabel,new Object[]{jsFunction,Integer.toString(currentPageNo-1)}));
			if (currentPageNo > pageHalfSize+1) {
				strBuff.append(MessageFormat.format(firstPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNo)}));
				strBuff.append(pageSeparateLabel);
			}
		} else {
			strBuff.append(disabledPreviousPageLabel);
		}
		
		strBuff.append(tagStartEndLabel);
		strBuff.append(tagMidLabel);
		
		for(int i=firstPageNoOnPageList;i<=lastPageNoOnPageList;i++){
			if(i==currentPageNo){
        		strBuff.append(MessageFormat.format(currentPageLabel,new Object[]{Integer.toString(i)}));
        	}else{
        		strBuff.append(MessageFormat.format(otherPageLabel,new Object[]{jsFunction,Integer.toString(i),Integer.toString(i)}));
        	}
        }

		strBuff.append(tagMidEndLabel);
		strBuff.append(tagLastLabel);
		
		if(currentPageNo < lastPageNo){
			if (lastPageNoOnPageList < lastPageNo) {
				strBuff.append(pageSeparateLabel);
				strBuff.append(MessageFormat.format(lastPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo)}));
			}
			strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(currentPageNo+1)}));
		} else {
			strBuff.append(disabledNextPageLabel);
		}
		strBuff.append(tagLastEndLabel);
		
		return strBuff.toString();
	}
}
