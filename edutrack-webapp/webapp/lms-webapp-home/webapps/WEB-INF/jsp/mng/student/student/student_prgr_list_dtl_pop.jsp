<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/popupbox.js"/>
</mhtml:head>
<mhtml:frm_body>
	<br>
	<form name="Search" id="Search">
	<div style="float:left;width:98%;padding:5px 0px 5px 0px">
		<div style="float:left;padding-left:20px">
			<select name="sbjCd" id="sbjCd" style="width:120px;" onChange="listContents()">
				<c:forEach items="${sbjList}" var="item">
					<option value="${item.sbjCd}" <c:if test="${studentVO.sbjCd eq item.sbjCd}">selected</c:if>>${item.sbjNm}</option>
				</c:forEach>
			</select>
		</div>
		 <div style="float:right">
		       <meditag:button value="닫기" title="진도율 상세 확인 창을 닫습니다." func="parent.ratioPopBox.close()" />
   		</div>
   	</div>
	</form>
           <table class="table_list" style="width:96%" align="center" summary="번호,목차,단원평가,진도율,학습횟수,학습시간,학습하기">
          		<caption>강의실 교재목차 리스트</caption>
            <colgroup>
            	<col width="7%" />
                <col width="auto" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
            </colgroup>
               <thead>
                   <tr>
                       <th scope="col">번호</th>
                       <th scope="col">목차</th>
                       <th scope="col">단원평가</th>
                       <th scope="col">진도율</th>
                       <th scope="col">학습횟수</th>
                       <th scope="col" class="rnone">학습시간</th>
                   </tr>
               </thead>
               <tbody id="tbodyList">
               <c:forEach items="${bookmarkList}" var="item" varStatus="status">
               	<tr height="35" align="center">
               		<td>${status.count}</td>
               		<td class="subject">${item.unitNm}</td>
               		<td></td>
               		<td>${item.prgrRatio}%</td>
               		<td>${item.connCnt}회</td>
               		<td>${meditag:round((item.connTotTm / 60),0)}분</td>
               	</tr>
               </c:forEach>
               </tbody>
           </table>
<form id="bookmarkForm" name="bookmarkForm" onsubmit="return false" action="/mng/std/student/listStdPrgrRatioDetail">
	<input type="hidden" name="sbjCd" id="bookmarkSbjCd" value="${vo.sbjCd }" />
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="stdNo" value="${vo.stdNo }" />
</form>
<script type="text/javascript">
	function listContents() {
		$("#bookmarkSbjCd").val($("#sbjCd > option:selected").val());
		document.bookmarkForm.submit();
	}

	function goList(){
		document.location.href = cUrl("/lec/bookmark/listStdPrgrRatio");
	}
</script>
</mhtml:frm_body>
</mhtml:html>