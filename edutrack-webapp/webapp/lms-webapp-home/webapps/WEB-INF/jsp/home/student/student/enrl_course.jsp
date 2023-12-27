<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module/>
</mhtml:home_head>
<mhtml:home_body id="Menu_bg01">
	<div id="contents">
		<mhtml:home_location />
		<!--CONTENTS START-->
		<h4 class="tm20"><img src="${img_base}/title/tb_legend_03.gif" width="46" height="12" alt="교육신청" /></h4>
		<div class="box_eduapply">
			<p>홍길동님께서  <strong>사회통일교육(통일교육위원반) 1기수</strong> 과정을 신청하셨습니다.</p>
			<ul class="base">
				<li>아래의 <span class="important">개인정보를 정확하게</span> 확인 수정하신 후 확인 버튼을 클릭하시면 접수완료 됩니다.</li>
				<li class="end">수강인증까지는 1~3일 정도의 시간이 소요되며, 진행과정은 <span class="important">“마이페이지 &gt; 나의강의실"</span> 에서 확인하실 수 있습니다.</li>
			</ul>
		</div>
		<fieldset class="tm10">
			<legend>교육신청</legend>
			<table class="vt_dtable" summary="회원구분, 직장명">
				<colgroup>
				<col width="20%" />
				<col width="auto" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="mdiv">회원구분</label> <span title="필수항목">*</span></th>
						<td>
							<p>
							<input type="radio" name="" id="mdiv" class="nbrd" />
							<label for="mdiv">일반</label>
							<input type="radio" name="" id="teacher" class="nbrd lm10" />
							<label for="teacher">교원(교육전문직 해당)</label>
							<input type="radio" name="" id="civil" class="nbrd lm10" />
							<label for="civil">공무원</label>
							<input type="radio" name="" id="unite" class="nbrd lm10" />
							<label for="unite">통일교육원</label>
							</p>
							<p class="tm5">
								<select name="">
									<option selected="selected" value=""></option>
									<option value=""></option>
								</select>
							</p>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="njob">직장명</label></th>
						<td><input type="text" name="" id="njob" maxlength="" size="20" class="txt" /></td>
					</tr>
				</tbody>
			</table>
			<p class="hr"></p>
			<table class="vt_dtable">
				<caption class="sr-only">회원목록</caption>
				<colgroup>
				<col width="20%" />
				<col width="auto" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="tel">전화번호(자택)</label> <span title="필수항목">*</span></th>
						<td><input type="text" name="" id="tel" maxlength="" size="7" class="txt" />
							-
							<input type="text" name="" id="tel01" maxlength="" size="7" class="txt" />
							-
							<input type="text" name="" id="tel02" maxlength="" size="7" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="hp">핸드폰번호</label> <span title="필수항목">*</span></th>
						<td><input type="text" name="" id="hp" maxlength="" size="7" class="txt" />
							-
							<input type="text" name="" id="hp01" maxlength="" size="7" class="txt" />
							-
							<input type="text" name="" id="hp02" maxlength="" size="7" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="addr">주소(자택)</label> <span title="필수항목">*</span></th>
						<td><p>
								<input type="text" name="" id="addr" maxlength="" size="20" class="txt" title="우편번호" />
								<a href=""><img src="${img_base}/common/btn/btn_post.gif" width="59" height="20" alt="우편번호" /></a></p>
							<p class="tm3">
								<input type="text" name="" id="addr01" maxlength="" size="70" class="txt" title="기본주소" />
							</p>
							<p class="tm3">
								<input type="text" name="" id="addr02" maxlength="" size="70" class="txt" title="상세주소" />
							</p></td>
					</tr>
					<tr>
						<th scope="row"><label for="check">메일수신여부</label> <span title="필수항목">*</span></th>
						<td><input type="radio" name="" id="check" class="nbrd" />
							<label for="check">수신</label>
							<input type="radio" name="" id="refuse" class="nbrd lm20" />
							<label for="refuse">거부</label></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<p class="btnsright tm20"><a href=""><img src="${img_base}/common/btn/btn_entry03.gif" width="46" height="23" alt="확인" /></a>
			<a href="javascript:history.go(-1)" class="lm5"><img src="${img_base}/common/btn/btn_cancel01.gif" width="46" height="23" alt="취소" /></a></p>
		<!--//CONTENTS END-->
	</div>
</mhtml:home_body>
</mhtml:home_html>