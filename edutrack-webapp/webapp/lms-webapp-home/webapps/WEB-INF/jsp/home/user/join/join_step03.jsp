<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
                    
                <!-- content -->
                <div id="content" class="content">
                    
                    <div class="member">
                        <div class="flex-container">
                            
                            <div class="join_cont">
                                <ol class="join_step">
                                    <li>               
                                        <span class="title"><small>Step 1</small> <b>약관동의</b></span>
                                    </li>
                                    <li>
                                        <span class="title"><small>Step 2</small> <b>정보입력</b></span>                                       
                                    </li>                            
                                    <li class="active">
                                        <span class="title"><small>Step 3</small> <b>가입완료</b></span>                                       
                                    </li>                                     
                                </ol>
                                
                                <div class="join_area join_complete">
                                    <h5 class="title_h2"><strong>회원가입</strong>을 축하드립니다.</h5>
                                    <img src="/tpl/002/img/contents/picto_join.png" alt="" class="picto">
                                    <p class="desc">
                                        로그인 후 다양한 서비스를 이용하 실 수 있습니다. 
                                    </p>
                                    <p class="desc2"> 아이디는 <strong>${vo.userId }</strong> 입니다.</p>
                                </div>
                                
		                        <div class="btns">
		                        	<a href="/" class="btn type5 txt_center">메인으로</a>
		                        	<a href="/home/main/goMenuPage?mcd=HM04001000" class="btn type4 txt_center">로그인 하기</a>
		                        </div>
                                
                            </div>

                        </div>
                    </div>
                       
                        
                </div>
                <!-- //content -->
                    
                    
