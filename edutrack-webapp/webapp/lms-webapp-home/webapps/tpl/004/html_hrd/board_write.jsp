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
                                    <li>지원센터</li>
                                    <li><span class="current">공지사항</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">지원센터</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">공지사항</a></li>
                            <li><a href="#">학습자료실</a></li>  
                            <li><a href="#">1:1 문의하기</a></li> 
                            <li><a href="#">FAQ</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">공지사항</h3>
                    
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
                                                <input class="form-control" type="text" name="writeInput" id="writeInput" value="관리자" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row">
                                        <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                        <div class="col-sm-10">
                                            <div id="editor"></div>
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
                                <li>
                                    <div class="row">
                                        <label for="hashInput" class="form-label col-sm-2">해시태그</label>
                                        <div class="col-sm-10">     
                                            <small class="note">* 해시태그 입력후 엔터(Enter)나 우측 추가(+) 버튼을 눌러주세요</small>                  
                                            <div class="form-row">
                                                <input type="text" id="hashInput" class="form-control hash_input">
                                                <button type="button" class="hash_add"><i class="xi-plus"></i><span class="sr-only">해시태그 추가</span></button> 
                                            </div>                                            
                                            <div class="hash_area">
                                                <span class="sr-only">해시태그추가영역</span>
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