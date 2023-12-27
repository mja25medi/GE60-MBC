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
                                    <li>수강신청</li>
                                    <li><span class="current">원격훈련과정</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">수강신청</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">원격훈련과정</a></li>
                            <li><a href="#">집체훈련과정</a></li>  
                            <li><a href="#">혼합훈련과정</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">원격훈련과정</h3>         
                    
                    <div class="course_detail">
                        <div class="detail_top">
                            <div class="course_img">
                                <img src="../img/contents/thumb_01.png" alt="[A-1코스] K-디지털 빅데이터 과정">      
                            </div>
                            <div class="course_info">
                                <div class="card_label">
                                    <span class="label basic bcBlue">인공지능</span>
                                </div>
                                <div class="item">
                                    <h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2>
                                    <ul>
                                        <li><span>교육구분</span>국비지원 / 개발원 자체</li>
                                        <li><span>과정유형</span>온라인</li>
                                        <li><span>과목 수</span>5과목</li>
                                        <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                        <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (960시간)</li>
                                        <li><span>교육비용</span><strong class="price">무료 / 300,000원</strong></li>
                                    </ul>
                                </div>                    
                            </div>
                        </div>

                        <div class="btns right mt20">
                            <button class="btn gray2">비회원 상담신청</button>
                            <button class="btn type4">수강신청</button>
                            <button class="btn">장바구니에 담기</button>
                        </div>  

                        <div class="detail_con">
                            <h5 class="subtitle">과정소개</h5>
                            <p>
                                본 과정은 머신러닝을 위한 데이터 수집 및 분석 과정을 파이썬을 이용하여 처리하는 방법을 쉽게 익힐 수 있도록 다양한 예제를 활용한 강좌이다. <br>
                                본 과정을 통해 인공지능 프로그래밍과 머신러닝을 위한 데이터 처리에 대한 이해를 돕는 계기가 될 것으로 기대한다.<br><br>

                                - 필수적인 웹 기반 지식을 차근차근 배우며 탄탄하게 웹 프로그래밍을 시작하고 싶은 분<br>
                                - 자신이 만든 인공지능 예측 모델을 웹서비스로 만들어내고 싶은 분<br>
                                - 회사나 제품의 소개를 위한 브랜드 랜딩페이지를 만들고 싶은 분<br>
                                - BERT와 같은 대규모 NLP 모델을 구현하여 직접 서비스 적용까지 해내고 싶은 분<br>
                                - 인공지능 예측모델을 활용한 웹서비스 프로젝트들로 포트폴리오를 만들고 싶은 분<br><br>

                                <strong>인공지능 모델 활용을 위한 웹 프로그래밍은?</strong><br><br>

                                웹 프로그래밍과 인공 지능 프로그래밍에 대한 중급 수준의 과정으로<br>
                                이론, 실습, 동영상 강의로 구성되어 코딩 초보자도 무리 없이 완주하실 수 있습니다.<br><br>

                                <strong class="fcBlue">● 인공지능 정보공학 컴퓨팅</strong><br>
                                <strong class="fcDarkgray">사물지능의 첫 걸음은 컴퓨터를 이해하는 것<br>
                                컴퓨터의 구성과 동작원리를 이해하고 개발환경을 구축해보자</strong><br>
                                인공지능 개발 프로세스 이해<br>
                                운영체제, 소프트웨어 공학, 통신 프로토콜(TCP/IP) 이해<br>
                                실무 개발 환경의 구축 방법과 이해(SDK, DB, Maven 등)<br><br>

                                <strong class="fcBlue">● 센싱 데이터 처리와 시각화</strong><br>
                                <strong class="fcDarkgray">garbage IN garbage OUT. 질 나쁜 데이터는 나쁜 결과를 유도한다<br>
                                좋은 결과물을 얻기위해 센서데이터를 정제하고 관리하는 방법을 배워보자</strong><br>
                                파이썬 프로그래밍 기초<br>
                                객체 지향 프로그래밍, SW개발 방법론 이해<br>
                                파이썬 데이터 전처리 및 조작<br>
                                결측치, 이상치 제거, 탐색적 데이터 분석<br><br>

                                <strong class="fcBlue">● 지능 제어 데이터 관리와 저장</strong><br>
                                <strong class="fcDarkgray">매 초마다 쏟아져 나오는 방대한 데이터를 어떻게 관리할까?<br>
                                다양한 데이터의 유형별 특징을 이해하고 효율적으로 저장/관리하는 방법을 알아보자</strong><br>
                                데이터 유형별 특성 이해 (영상/센서/시계열 등)<br>
                                데이터 저장 구조 이해 (정형/비정형/반정형)<br>
                                데이터베이스 개론, RDBMS 실습 (DML, DDL, 제약조건, 트랜젝션 등)
                            </p>
                        </div>

                        <div class="btns mt30">
                            <button type="button" class="btn gray2">목록</button>
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