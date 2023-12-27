<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

									<c:forEach var="bookmark" items="${student.bookmarkList }" varStatus="status">
										<td id="${student.stdNo }_${status.count }" class="text-center">
											<a href="javascript:showStsBtn('${student.stdNo}_${status.count }');">
												<c:choose>
													<c:when test="${bookmark.attendStsCd eq 'EMPTY'}"><span class="text-muted">[미입력]</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ATTEND'}"><span class="text-light-blue">출석</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ABSENCE'}"><span class="text-red">미출석</span></c:when>
													<c:when test="${bookmark.attendStsCd eq 'ALTER'}"><span class="text-yellow">보강출석</span></c:when>
												</c:choose>
											</a>
											<div name="stsBtns" style="display :none;">
												<button name="stsBtn" onclick="updateSts('ATTEND','${student.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">출석</button>
												<button name="stsBtn" onclick="updateSts('ABSENCE','${student.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">미출석</button>
												<button name="stsBtn" onclick="updateSts('ALTER','${student.stdNo}', '${bookmark.unitCd }')" class="btn btn-default btn-sm">보강</button>
											</div>
										</td>
									</c:forEach>
									
						<script>
							var stdNo = '${student.stdNo}';
							var attendCnt = '${student.attendCnt}';
							var alterCnt = '${student.alterCnt}';
							var absenceCnt = '${student.absenceCnt}';
							$("#attdCnt_" + stdNo).text(attendCnt + "/" + alterCnt + "/" + absenceCnt);
						</script>

