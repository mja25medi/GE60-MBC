<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
	
	
	<div class="res_tbl_wrap">
                <table>
                    <caption>수강자 목록</caption>
                    <thead>
                        <tr>
                            <th scope="col" width="4%"><span class="custom-input onlychk"><input type="checkbox" id="chkall" onclick="chkStdAll()"><label for="chkall"></label></span></th>
                            <th scope="col" width="4%">번호</th>
                            <th scope="col" width="15%">이름(ID)</th>
                            <th scope="col" width="8%">관리</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty stdList }">
                    <c:forEach var="item" items="${stdList }" varStatus="status">
                        <tr>
                            <td scope="row" data-label="선택"><span class="custom-input onlychk">
                            <input type="checkbox" id="chkStd${status.index }" name="chkStd" value=${item.userNo }/> <label for="chkStd${status.index }"></label></span>
                            </td>
                            <td scope="row" data-label="번호">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
                            <td class="title" data-label="이름(ID)" style="text-align: center;">${item.userNm }(${item.userId })</td>
                            <td data-label="관리">
                          	    <ul class="vertical_btn">
                                   <li><button class="btn icon type3" onclick="viewEduResultPop('${item.stdNo}')"><i class="xi-chart-pyramid" aria-hidden="true"></i></button></li>
                                   <li><button class="btn icon type3" onclick="viewMsgPop('MSG','${item.userNo}')"><i class="xi-note-o" aria-hidden="true"></i></button></li>
                                </ul>
                            </td>
                        </tr>
                        </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
	<c:if test="${empty stdList }">
		<div class="no-list">해당하는 수강생이 없습니다.</div>
	</c:if>
			
		<div class="board_pager">
		   <span class="inner">
		       <meditag:paging pageInfo="${pageInfo}" funcName="listStd" name="lect"/>
		    </span>
		</div>

<script type="text/javascript">
	var chkAllFlag = 0;
	$(document).ready(function() {

	});
	
	function chkStdAll(){
	    if(!chkAllFlag){
	   		$(document).find("input[name='chkStd']").prop({checked:true});
	   		chkAllFlag = 1;
	    }else{
	    	$(document).find("input[name='chkStd']").prop({checked:false});
	    	chkAllFlag = 0;
	    }
	}

</script>
