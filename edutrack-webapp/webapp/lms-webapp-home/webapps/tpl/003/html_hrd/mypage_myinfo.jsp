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
                                    <li><span class="current">개인정보 수정</span></li>
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
                            <li><a href="#">문의내역</a></li> 
                            <li><a href="#">받은쪽지</a></li>    
                            <li class="active"><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">개인정보 수정</h3>
                    
                    <div class="txt_info"><i class="icon_star"></i>필수 입력 사항입니다.</div>
                    <div class="join_form">
                        <div class="tstyle mb30">
                            <ul class="dbody">
                                   
                                <li>
                                    <div class="row">
                                        <label for="nameInput" class="form-label col-sm-2">이름<i class="icon_star" aria-hidden="true"></i></label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
                                                <input class="form-control w50" type="text" name="name" id="nameInput" value="학습자2"  readonly>
                                            </div>
                                        </div>
                                    </div>
                                </li>   
                                <li>
                                    <div class="row">
                                        <label for="birthdayInput" class="form-label col-sm-2">생년월일<i class="icon_star" aria-hidden="true"></i></label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
                                                <input class="form-control w50" type="text" name="name" id="birthdayInput" value="20220101" maxlength="10" readonly>
                                            </div>                                            
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row">
                                        <label for="idInput" class="form-label col-sm-2">아이디<i class="icon_star" aria-hidden="true"></i></label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
                                                <input class="form-control w50" type="text" name="name" id="idInput" value="medistu2" readonly>
                                            </div>                                           
                                        </div>
                                    </div>
                                </li>  
                                <li>
                                    <div class="row">
                                        <label for="pwInput" class="form-label col-sm-2">비밀번호<i class="icon_star" aria-hidden="true"></i></label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
                                                <button type="button" id="" class="btn gray2 ml5">비밀번호 변경</button>
                                            </div>                                                                        
                                        </div>
                                    </div> 
                                </li>                                   
                                <li>
                                    <div class="row">
                                        <label for="inputEmail1" class="form-label col-sm-2"><span>이메일주소</span><i class="icon_star" aria-hidden="true"></i></label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
                                                <input class="form-control w50" type="text" name="name" value="kdw912001@mediopia.co.kr" id="inputEmail1" readonly>
                                                <button type="button" class="btn gray2 ml5">이메일 변경</button>
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
                                            <input class="form-control" type="text" name="title" value="1234" id="inputPhone2" title="전화번호 두번째자리">
                                            <input class="form-control" type="text" name="title" value="5678" id="inputPhone3" title="전화번호 세번째자리">                            
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row">
                                        <span class="form-label col-sm-2"><span>메일수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
                                        <div class="col-sm-10 form-inline">
                                            <span class="custom-input">
                                                <input type="radio" name="emailRecv" id="emailRecvY" value="Y" checked="">
                                                <label for="emailRecvY">수신</label>
                                            </span>
                                            <span class="custom-input ml5">
                                                <input type="radio" name="emailRecv" id="emailRecvN" value="N">
                                                <label for="emailRecvN">수신거부</label>
                                            </span>
                                        </div>
                                    </div>
                                </li> 
                                <li>
                                    <div class="row">
                                        <span class="form-label col-sm-2"><span>문자수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
                                        <div class="col-sm-10 form-inline">
                                            <span class="custom-input">
                                                <input type="radio" name="smsRecv" id="smsRecvY" value="Y" checked="">
                                                <label for="smsRecvY">수신</label>
                                            </span>
                                            <span class="custom-input ml5">
                                                <input type="radio" name="smsRecv" id="smsRecvN" value="N">
                                                <label for="smsRecvN">수신거부</label>
                                            </span>
                                        </div>
                                    </div>
                                </li>                                         
                            </ul>
                            
                        </div>
                    </div>
                    <!-- // 정보입력 --> 
                    
                    <div class="btns mt20">
                        
                            <button class="btn type4">확인</button>
                            <button class="btn">취소</button>
                            <button class="btn gray2">회원탈퇴</button>
                        
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