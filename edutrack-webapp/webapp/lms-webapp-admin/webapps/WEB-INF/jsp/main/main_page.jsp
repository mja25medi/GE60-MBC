<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<c:url var="img_base" value="/img"/>

			<div class="row" id="content">
				<div class="col-md-4 col-sm-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-home fa-3x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge" style="font-size:18px;">${orgStatusVO.totalOrgCnt}</div>
									<div>Total LMS Sites</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left"></span>
								<span class="pull-right"></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-users fa-3x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge" style="font-size:18px;">${orgStatusVO.totalUserCnt}</div>
									<div>Total Users</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left"></span>
								<span class="pull-right"></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-4 col-sm-12">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-th-list fa-3x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge" style="font-size:18px;">${orgStatusVO.totalCrsCnt}</div>
									<div>Total Courses</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left"></span>
								<span class="pull-right"></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							Status
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div id="statusListArea"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

<script type="text/javascript">
	$(document).ready(function() {
		listStatus(1);
	});

	function listStatus(page) {
		$("#statusListArea")
			.load(
				cUrl("/OrgStatusAdmin.do"),
				{
					"cmd" : "indexList",
					"curPage" : page
				}
			);
	}
</script>
