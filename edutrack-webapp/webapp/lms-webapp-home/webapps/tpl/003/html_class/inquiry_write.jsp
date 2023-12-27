<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<body>
    <div id="wrap">
        <%@ include file="../inc/class_header.jsp" %>
        <div class="container">
            <%@ include file="../inc/class_lnb.jsp" %>
            <div id="content">
                <div class="learn_top">
                    <div class="left_title">
                        <h3>학습문의</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle_view">
                        <div class="title">개인정보처리방침 변경 안내</div>
                        <ul class="head">
                            <li><strong>작성자</strong><span>학습자1</span></li>
                            <li><strong>회차</strong><span>1-2차시 파이썬 설치 및 가상환경 구축</span></li>
                            <li><strong>작성일시</strong><span>2023.10.28 15:47</span></li>
                        </ul>
                        <div class="tb_contents">
                        안녕하세요!<br>
                        찾아주신 수강생분들께 진심으로 감사드립니다.<br><br>

                        개인정보처리방침이 변경되어 안내드립니다.<br><br>


                        변경된 개인정보처리방침은 2023년 08월 21일부터 사전 고지 후 2023년 08월 28일부터 효력이 발생합니다.<br><br>

                        - 시행예정일 : 2023년 08월 28일<br>
                        - 변경내용 : 하단 각각 링크를 통해 현행 방침과 개정 방침 내용 확인 가능<br><br>


                        ▷Link : 개인정보처리방침 '현행안' 전문 보기<br><br>

                        2023년 07월 04일 부터 시행 될 주요 개정내용은 하단 상세링크를 통해 확인 가능합니다.<br><br>

                        ▷ Link : 개인정보처리방침 '개정안' 전문 보기<br><br>

                        ※ 회원 여러분께서 본 공지일로부터 30일 내에 별도 의사표시를 하지 않는 경우, 변경 후 개인정보처리방침에 동의하신 것으로 간주합니다.<br>
                        변경 후 약관의 적용에 동의하지 않는 회원은 이용계약을 해지하고 회원 탈퇴를 요청할 수 있습니다.<br><br>

                        회원 여러분께 더 좋은 서비스로 보답하겠습니다.<br>
                        감사합니다.<br><br>
                           
                        </div>
                        <div class="add_file_list">
                            <strong class="title">첨부파일</strong>
                            <ul class="add_file">
                                <li>                    
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_doc.png" alt="">
                                        <span class="text">첨부파일명마우스오버 시.doc</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>                    
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                                <li>
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_img.png" alt="">
                                        <span class="text">154873973477000.jpg</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" maxlength="100" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2">첨부파일</span>
                                    <div class="col-sm-10 attach_area">
                                        <input type="file" class="input_attach sr-only" id="attchFile" multiple=""><label for="attchFile" class="btn gray2">파일 찾기</label> 
                                        <div class="attach_list">
                                            <div class="attach_name">첨부파일첨부파일첨부파일.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                            <div class="attach_name">동물관련종사자_입력폼.jpg<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                            <div class="attach_name">KakaoTalk_20210729_131327900.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                        </div>                                            
                                    </div> 
                                </div>
                            </li>                             
                        </ul>
                    </div>
                    <div class="btns mt30">
                        <button type="button" class="btn gray2">저장</button>
                        <button type="button" class="btn type5">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>