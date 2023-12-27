<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
								<div class="panel panel-default">
									<div class="panel-body">
										<form name="InputUrl">
										<input type="text" name="directUrl" id="directUrl" class="form-control input-sm">
										<div class="text-center" style="margin-top:10px;">
											<a href="#none" onclick="inputDirectUrl()" class="btn btn-primary btn-sm"><spring:message code="button.ok"/></a>
										</div>
										</form>
									</div>
								</div>