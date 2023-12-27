<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->
<body>
    
    <div id="wrap">
        <%@ include file="../inc/hrd_header.jsp" %>

        <main class="main">
            <div id="contentWrap" class="container">
                
                <!-- content -->
                <div id="content" class="content">
                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></li>
                                    <li>마이 페이지</li>
                                    <li><span class="current">문의내역</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">마이 페이지</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">장바구니</a></li>
                            <li><a href="#">결제내역</a></li>  
                            <li class="active"><a href="#">문의내역</a></li> 
                            <li><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">문의내역</h3>
                    
                    <div class="detail_cont write">
                       
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
                                        <label for="writeInput" class="form-label col-sm-2">작성자</label>
                                        <div class="col-sm-10">
                                            <div class="form-inline">
                                                <input class="form-control" type="text" name="writeInput" id="writeInput" value="홍길동" readonly>
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
                                            <input type="file" class="input_attach sr-only" id="attchFile" multiple><label for="attchFile" class="btn gray2">파일 찾기</label> 
                                            <div class="attach_list">
                                                <div class='attach_name'>첨부파일첨부파일첨부파일.png<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                                <div class='attach_name'>동물관련종사자_입력폼.jpg<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                                <div class='attach_name'>KakaoTalk_20210729_131327900.png<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                            </div>                                            
                                        </div> 
                                    </div>
                                </li>                                                            
                            </ul>
                        </div>
                        <div class="btns mt30">
                            <button type="button" class="btn gray2">등록</button>
                            <button type="button" class="btn type5">취소</button>
                        </div>
                    </div>
                    <!-- //detail_cont -->                   
                    
                    

                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>