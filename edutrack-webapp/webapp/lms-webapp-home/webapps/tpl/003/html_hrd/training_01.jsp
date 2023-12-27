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
                                    <li>교육안내</li>
                                    <li><span class="current">훈련과정</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">교육안내</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">훈련과정</a></li>
                            <li><a href="#">고용보험 환급제도</a></li>  
                            <li><a href="#">훈련비 환급방법</a></li> 
                            <li><a href="#">훈련 진행절차</a></li>   
                            <li><a href="#">훈련진행 유의사항</a></li> 
                            <li><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">훈련과정</h3>

                    <ul class="tabs mb20 four_list">
                        <li class="active"><a href="#" title="선택된 탭메뉴">지원제도안내</a></li>
                        <li><a href="#" title="비활성 탭메뉴">K-디지털 트레이닝 과정</a></li>
                        <li><a href="#" title="비활성 탭메뉴">K-디지털 기초역량 훈련과정</a></li>
                        <li><a href="#" title="비활성 탭메뉴">사업주 직업능력개발훈련</a></li>
                    </ul>
                    
                    <div class="training_area">
                        <div class="infotitle">국민내일배움카드</div>

                        <dl class="guide_info">
                            <div>
                                <dt>
                                    국민내일배움카드 안내
                                </dt>
                                <dd>
                                    국민내일배움카드란?<br>
                                    근로자의 직무능력 향상 및 결쟁력 강화를 도모하기 위해 훈련비용의 일부를 지원하는 제도로 재직자 내일배움카드와 실업자 내일배움카드를 하나로 통합하여 운영
                                </dd>
                            </div>
                        </dl>
                        <div class="learning_card">
                            <div class="card_area">
                                <div class="card">
                                    <img src="../img/contents/learning_card_01.png" alt="실업자 내일배움카드">
                                </div>
                                <div class="icon">
                                    <i class="xi-plus"></i>
                                </div>
                                <div class="card">
                                    <img src="../img/contents/learning_card_02.png" alt="재직자 내일배움카드">
                                </div>
                                <div class="icon2">
                                    <i class="xi-drag-handle"></i>
                                </div>
                                <div class="card">
                                    <img src="../img/contents/learning_card_03.png" alt="국민 내일배움카드">
                                </div>
                            </div>
                            <div class="txt">실업자 근로자 구분없이 국민내일배움카드 한장으로 통합</div>
                        </div>
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    지원대상
                                </dt>
                                <dd>
                                    누구나 국민내일배움카드 신청이 가능합니다.<br><br>

                                    ※ 다만, 공무원, 사립학교 교직원(사학연금 대상자), 졸업예정자 이외 재학생, 연매출 1억 5천만원 이상의 자영업자,<br>
                                    월 임금 300만원 이상인 대기업 근로자(45세 미만)·특수형태 근로종사자는 제외
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    지원내용
                                </dt>
                                <dd>
                                    <p class="txt_circle_num"><span>①</span>실업, 재직, 자영업 여부에 관계 없이 국민내일배움카드 한 장으로 5년 간 사용 가능합니다.</p>
                                    <p class="txt_circle_num"><span>②</span>개인당 300~500만원의 훈련비용을 지원합니다.</p>
                                    <div class="inner_box">
                                        <ul class="info_list mb10">
                                            <li>취업성공패키지Ⅰ참여자 등 저소득계층에게는 500만원 지원</li>
                                            <li>국가기간·전략산업직종, 과정평가형 자격과정 등 특화과정은 훈련비 전액 지원</li>
                                        </ul>

                                        <div class="gray_box">
                                            훈련 참여자는 훈련비의 일부를 자부담(실업자, 재직자, 자영자 등 자부담 비율 동일)
                                            <ul class="info_list">
                                                <li>저소득계층 및 국가기간전략산업직종, 과정평가형 자격과정 등 특화과정은 자부담 없음</li>
                                                <li>자부담 수준은 직종별 취업률 등에 따라 15~55% 차등 부과</li>
                                                <li>자부담 5% 추가 부과</li>
                                            </ul>
                                            ① 일반사무 ② 회계 ③ 요약보호사 ④ 음식조리 ⑤ 공예 ⑥ 바리스타 ⑦ 제과제빵 ⑧ 이·미용 ⑨ 문화콘텐츠 제작 ⑩ 간호조무사
                                        </div>
                                    </div>
                                    <p class="txt_circle_num"><span>③</span>국민들은 상담절차를 거쳐 개인에 맞는 훈련을 선택하여 수강할 수 있습니다.</p>
                                    <div class="inner_box">
                                        <ul class="info_list mb10">
                                            <li>연간 훈련 참여 횟수 제한(1년간 5회 수강 가능)</li>
                                            <li>동일 직종 훈련과정 참여 제한(1년간 동일직종 3회 수강 가능)</li>
                                            <li>동일한 과정 수강 제한</li>
                                        </ul>
                                    </div>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    국민내일배움카드 이용방법
                                </dt>
                                <dd>
                                    <p class="txt_circle_num"><span>①</span>내일배움카드 신청 ▶ 가까운 고용센터를 방문하거나 HRD-Net을 통해 신청</p>
                                    <p class="txt_circle_num"><span>②</span>훈련과정 수강신청 ▶ 140시간 이상 훈련과정 : 고용센터 상담을 통해 신청 가능</p>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    국민내일배움카드 진행절차
                                </dt>
                                <dd>
                                    <strong class="fcBlue">Step 1.</strong> 국민내일배움카드 발급 신청 및 발급(대표전화 1350)                                    
                                    <ul class="info_list">
                                        <li>온라인 신청 : HRD-Net 로그인 > MY서비스 > 회원정보관리 실명인증 > 공인인증서 등록 > 국민내일배움카드 발급 신청 클릭 > 국민내일배움카드 신청서 작성 및 카드 신청</li>
                                        <li>오프라인 신청 : 신분증과 근로계약서 및 증빙서류 지참 후 관할 고용센터 방문 > 고용센터에서 지정확인서 수령 후 지정은행(신한/농협은행) 방문하여 즉시 발급 및 해당 카드사 우편배송</li>
                                    </ul>
                                    ※ 발급관련 진행절차 등은 신청 중인 고용센터로 문의
                                </dd>
                                <dd class="mt20">
                                    <strong class="fcBlue">Step 2.</strong> 수강신청
                                    <ul class="info_list">
                                        <li>훈련과정 검색 및 수강신청</li>
                                    </ul>
                                    ※ 국민내일배움카드로 수강신청 시에는 2번의 수강신청 필요(HRD-Net, 기관 홈페이지)<br>
                                    ※ HRD-Net과 기관 사이트에 모두 수강신청이 되어야 최종 신청 완료
                                </dd>
                                <dd class="mt20">
                                    <strong class="fcBlue">Step 3.</strong> 자기부담금 결제
                                    <ul class="info_list">
                                        <li>자기부담금이 있는 과정은 수강신청 시 국민내일배움카드(신한카드/NH카드)로 결제</li>
                                    </ul>
                                    ※ 카드 결제와 관련된 장애 시 신한(1544-7000), NH(1588-1600)으로 문의
                                </dd>
                                <dd class="mt20">
                                    <strong class="fcBlue">Step 4.</strong> 훈련생 실시 신고
                                    <ul class="info_list">
                                        <li>HRD-Net에 훈련생 실시신고</li>
                                    </ul>                                   
                                </dd>
                                <dd class="mt20">
                                    <strong class="fcBlue">Step 5.</strong> 훈련과정 수강
                                    <ul class="info_list">
                                        <li>훈련일(개강일)에 맞춰 술기준 확인 후 교육기간 내 수강 진행</li>
                                    </ul>                                   
                                </dd>
                                <dd class="mt20">
                                    <strong class="fcBlue">Step 6.</strong> 훈련생 수료보고
                                    <ul class="info_list">
                                        <li>HRD-Net에 훈련생 수료보고</li>
                                    </ul>                                   
                                </dd>
                            </div>
                        </dl>
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