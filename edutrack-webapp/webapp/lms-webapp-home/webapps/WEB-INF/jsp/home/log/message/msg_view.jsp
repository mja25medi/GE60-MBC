<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

							<div class="table_list">
		                        <ul class="list">
		                            <li class="head">
		                            	<label><spring:message code="common.title.title"/></label>
	                            	</li>
		                            <li>${vo.title }</li>                          
		                        </ul>
		                        <ul class="list">
		                            <li class="head">
		                            	<label><spring:message code="log.title.msg.sender"/></label>
		                            </li>
		                            <li>${vo.sendNm}</li>
		                            <li class="head">
	                            		<label><spring:message code="log.title.msg.senddate"/></label>
	                            	</li>
		                            <li><meditag:dateformat type="1" delimeter="." property="${vo.regDttm}" /></li>
		                        </ul>
		                        <ul class="list">
		                            <li class="head">
		                            	<label><spring:message code="common.title.cnts"/></label>
	                            	</li>
		                            <li>${fn:replace(vo.cts,crlf,"<br/>")}</li>                          
		                        </ul>                                     
		                    </div>
		                    <div class="btns mt30">
		                        <button type="button" class="btn gray2" onclick="listMsg(1)">목록</button>
		                    </div>
		                    
<script type="text/javascript">

</script>
