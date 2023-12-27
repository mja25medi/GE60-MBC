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
                    
                    <div class="member">
                        <div class="flex-container">
                            
                            <div class="join_cont">
                                <ol class="join_step">
                                    <li>               
                                        <span class="title"><small>Step 1</small> <b>약관동의</b></span>
                                    </li>
                                    <li class="active">
                                        <span class="title"><small>Step 2</small> <b>정보입력</b></span>                                       
                                    </li>                            
                                    <li>
                                        <span class="title"><small>Step 3</small> <b>가입완료</b></span>                                       
                                    </li>                                     
                                </ol>
                            
                                <div class="txt_info"><i class="icon_star"></i>필수 입력 사항입니다.</div>
                                <div class="join_form">
                                    <div class="tstyle mb30">
                                        <ul class="dbody">
                                            <li>
                                                <div class="row">
                                                    <label for="idInput" class="form-label col-sm-2">아이디<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="text" name="name" id="idInput" value="" placeholder="아이디를 입력하세요">
                                                            <button type="button" class="btn gray2 ml5">중복확인</button>
                                                        </div>
                                                        <small class="note">
                                                            <span class="fcBlue">사용가능한 아이디 입니다.</span>
                                                            <span class="fcRed">이미 가입된 아이디 입니다.</span>
                                                        </small>
                                                    </div>
                                                </div>
                                            </li>     
                                            <li>
                                                <div class="row">
                                                    <label for="nameInput" class="form-label col-sm-2">이름<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="text" name="name" id="nameInput" value=""  placeholder="이름을 입력하세요">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>   
                                            <li>
                                                <div class="row">
                                                    <label for="pwInput" class="form-label col-sm-2">비밀번호<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="password" name="name" id="pwInput" value="">
                                                        </div> 
                                                        <small class="note">* 비밀번호는 최소 8자리, 특수문자+숫자+영문 조합입니다.</small>                                               
                                                    </div>
                                                </div> 
                                            </li>    
                                            <li>
                                                <div class="row">   
                                                    <label for="pwInputConfirm" class="form-label col-sm-2">비밀번호 확인<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="password" name="name" id="pwInputConfirm" value=""  placeholder="">
                                                        </div>                                                       
                                                    </div>
                                                </div>
                                            </li>  
                                            <li>
                                                <div class="row">
                                                    <label for="inputEmail1" class="form-label col-sm-2"><span>이메일주소</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-inline">
                                                            <input class="form-control mr5" type="text" name="name" value="" id="inputEmail1">
                                                            <span class="mr5">@</span>
                                                            <input class="form-control mr5" type="text" name="name" id="inputEmail2" value=""  title="이메일 주소 뒷자리" placeholder="">
                                                            <select class="form-select" id="selectEmail2">
                                                                <option value="-">직접입력</option>
                                                                <option value="-">naver.com</option>
                                                                <option value="-">daum.net</option>
                                                            </select>
                                                            <button type="button" class="btn gray2 ml5">중복확인</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>                           
                                            <li>
                                                <div class="row">
                                                    <label for="birthdayInput" class="form-label col-sm-2">생년월일<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="text" name="name" id="birthdayInput" value="1904022" maxlength="10" title="시작일을 입력하세요.">
                                                        </div>
                                                        <small class="note">* 생년월일 6자리와 주민번호 앞 1자리를 입력하세요.</small>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="row">
                                                    <label for="selectPhone1" class="form-label col-sm-2"><span>휴대폰번호</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-inline">
                                                        <select class="form-select w20" id="selectPhone1">
                                                            <option value="-">선택</option>
                                                            <option value="-" selected>010</option>
                                                            <option value="-">016</option>
                                                        </select>                                                                        
                                                        <input class="form-control" type="text" name="title" value="" id="inputPhone2" title="전화번호 두번째자리" placeholder="">
                                                        <input class="form-control" type="text" name="title" id="inputPhone3" value="" title="전화번호 세번째자리" placeholder="">                            
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="row">
                                                    <label for="inputAddress1" class="form-label col-sm-2"><span>주소</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">                                            
                                                        <div class="form-inline mb10">
                                                            <input class="form-control" type="text" name="name" id="inputAddress1" value="" placeholder="우편번호">
                                                            <button type="button" class="btn gray2 ml5">검색</button>
                                                        </div>
                                    
                                                        <div class="form-row">
                                                            <input class="form-control mr5" type="text" name="title" id="inputAddress2" value="" title="시군구 주소를 입력하세요" placeholder="시군구 주소">
                                                        </div>
                                                        <div class="form-row">
                                                            <input class="form-control mr5" type="text" name="title" id="inputAddress3" value="" title="시군구 상세 주소를 입력하세요" placeholder="상세 주소">
                                                        </div>
                                    
                                                    </div>
                                                </div>
                                            </li>                                            
                                        </ul>
                                        
                                    </div>
                                </div>
                                <!-- // 정보입력 -->                                        
                
                                <div class="btns">
                                    <button type="button" class="btn type5">취소</button>
                                    <button type="button" class="btn type4">다음</button>
                                </div>
                                
                            </div>

                        </div>
                    </div>
                       
                        
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>