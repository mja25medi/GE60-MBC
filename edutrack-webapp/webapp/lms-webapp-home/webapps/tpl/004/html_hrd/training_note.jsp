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
                                    <li><span class="current">훈련진행 유의사항</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">교육안내</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">훈련과정</a></li>
                            <li><a href="#">고용보험 환급제도</a></li>  
                            <li><a href="#">훈련비 환급방법</a></li> 
                            <li><a href="#">훈련 진행절차</a></li>   
                            <li class="active"><a href="#">훈련진행 유의사항</a></li> 
                            <li><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">훈련진행 유의사항</h3>

                    <div class="training_area box">
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    훈련진행 유의사항
                                </dt>
                                <dd>
                                    <ul class="txt_num_list">
                                        <li><span class="num">1.</span> 훈련세부일정에 따라 본인이 직접 훈련을 수강하여야 하며, 대리·허위 수강 등을 하는 경우에는 고용노동부로부터 훈련비용의 지원을 받을 수 없습니다.</li>
                                        <li><span class="num">2.</span> 평가에는 반드시 참여하여야 하며, 지방노동과서의 장으로부터 지정받은 훈련기간이 종료하기 전까지 모든 평가에 참여하여 수료기준에 도달한 경우에 한하여 귀하의 사업주는
                                            고용노동부로부터 훈련비용의 지원을 받을 수 있습니다.</li>
                                        <li><span class="num">3.</span> 시험, 과제 제출 시 대리 및 허위 작성 등 부정한 방법에 대해서는 수료할 수 없으며, 향후 귀  하가 소속되어있는 사업주는 부정수급액에 따라 일정기간 지원 및 융자 제한을 받게 될
                                            수 있습니다. 특히 모사행위 적발 시 쌍방(모사자/피모사자)이 모두 동일한 수준의 피해를 받으므로 학습자 상호 간에 유의하시어 선의의 피해가 발생하지 않도록 주의하시기 바랍니다.</li>
                                        <li><span class="num">4.</span> 대리 수강하는 경우 IP추적을 통해 적발되고 있습니다. 본인은 본인 답안작성만 하시기 바랍니다.</li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    국민내일배움카드 훈련생 유의사항
                                </dt>
                                <dd>
                                    천재지변, 훈련기관의 폐업, 군입대, 임신 등 불가피한 사유 없이 훈련과정에서 미수료 또는 중도 탈락(수강포기)한 경우 해당 계좌에서 일정 금액을 차감
                                </dd>
                                <dd class="mt20">
                                    <table class="tbl w100">
                                        <colgroup>
                                            <col width="17%">
                                            <col width="*">
                                            <col width="30%">
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th>미수료 횟수</th>
                                                <th>2019년 1월 15일 이후 근로자 내일배움카드 발급자</th>
                                                <th>2020년 1월 이후 국민내일배움카드 발급자</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1회</td>
                                                <td class="txt-left">
                                                    <ul class="list-dot">
                                                        <li>사유가 발생한 날부터 60일간 카드 사용 중지</li>
                                                    </ul>
                                                </td>
                                                <td>지원한도액 20만원 차감</td>
                                            </tr>
                                            <tr>
                                                <td>2회</td>
                                                <td class="txt-left">
                                                    <ul class="list-dot">
                                                        <li>지원한도액 50만원 차감</li>
                                                        <li>사유가 발생한 날부터 60일간 카드 사용 중지</li>
                                                        <li>패널티 발생 이후 훈련과정 등록 시 정부지원 기준금액의 20% 추가 자부담</li>
                                                    </ul>
                                                </td>
                                                <td>지원한도액 50만원 차감</td>
                                            </tr>
                                            <tr>
                                                <td>3회 이상</td>
                                                <td class="txt-left">
                                                    <ul class="list-dot">
                                                        <li>지원한도액 100만원 차감</li>
                                                        <li>사유가 발생한 날부터 60일간 카드 사용 중지</li>
                                                        <li>패널티 발생 이후 훈련과정 등록 시 정부지원 기준금액의 20% 추가 자부담</li>
                                                    </ul>
                                                </td>
                                                <td>지원한도액 100만원 차감</td>
                                            </tr>                             
                                        </tbody>
                                    </table>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    학습 유의사항
                                </dt>
                                <dd>
                                    <h5 class="point">OTP 인증 시행</h5>
                                    <ul class="txt_num_list">
                                        <li><span class="num">1.</span> 원격훈련 부정감시 “OTP 인증시스템” 시행</li>
                                    </ul>
                                    <ul class="info_list">
                                        <li>한국산업인력공단의 훈련생인증(OTP) 시스템을 통해 훈련생 인증을 진행하여야 합니다.</li>
                                        <li>부정수료, 대리수강, 허위수강 등을 방지하고자 보안 절차를 진행합니다.</li>
                                    </ul>
                                    <div class="inner_box">
                                        <p class="txt_circle_num"><span>①</span>학습 : 학습시작 및 학습진행 8차시마다(ex. 1차시, 9차시, 17차시...) 훈련생인증(OTP) 인증 후 학습 진행 가능</p>
                                        <p class="txt_circle_num"><span>②</span>시험/과제 : 평가 응시 및 과제 입장 시 훈련생인증(OTP) 인증 후 문항 확인 및 응시 가능</p>
                                        ※ 훈련생인증(OTP) 인증 5번 오류 발생 시, 본인인증 1회 진행하여야 OTP 인증을 다시 진행할 수 있습니다.
                                    </div>
                                </dd>
                                <dd class="mt30">
                                    <h5 class="point">수료기준</h5>
                                    <ul class="info_list">
                                        <li>모든 평가항목에 대한 참여와 평가를 합산한 100점 만저 중 60점 이상을 취득하여야 합니다. (과정별로 수료기준이 다를 수 있음)</li>
                                        <li>필수적으로 진도율이 80%이상 충족되어야 합니다.</li>
                                    </ul>
                                </dd>
                                <dd class="mt30">
                                    <h5 class="point">1일 학습분량 제한</h5>
                                    <ul class="info_list">
                                        <li>국민내일배움카드과정은 1일 최대 8차시(혼합훈련과정은 최대 12차시)를 초과하여 학습할 수 없습니다.</li>
                                        <li>1일 학습분량을 제한함은 건전한 학습문화를 정착시키기 위해 적용된 운영 규정입니다.</li>
                                    </ul>
                                </dd>
                                <dd class="mt30">
                                    <h5 class="point">본인인증</h5>
                                    <ul class="info_list">
                                        <li>대리, 허위 수강방지 및 개인정보보호를 위해 본인인증 후 학습이 가능합니다.</li>
                                        <li>본인인증은 각 과정별로 진행되며 과정 입과 시, 최초 1회 학습을 위한 본인인증을 진행하여야 합니다.</li>
                                    </ul>
                                </dd>
                                <dd class="mt30">
                                    <h5 class="point">시험 응시</h5>
                                    <ul class="txt_num_list">
                                        <li><span class="num">1.</span> 진행단계평가</li>
                                    </ul>
                                    <ul class="info_list">
                                        <li>학습진도 50% 이상 달성 시 응시 가능하며 미응시 시 다음 차시 학습이 불가합니다.</li>
                                        <li>평가는 1회 응시 가능하며 평가시간 종료 후에는 입력한 답안이 자동제출 되오니 주어진 시간 내 응시 완료 바랍니다.</li>
                                    </ul>
                                    <ul class="txt_num_list">
                                        <li><span class="num">2.</span> 최종평가</li>
                                    </ul>
                                    <ul class="info_list">
                                        <li>진행단계 평가를 응시 완료하고 학습진도 80% 이상 달성 시 응시 가능하며, 평가시간은 훈련과정에 따라 60~90분입니다.</li>
                                        <li>최종평가 재응시는 아래에 해당하는 학습자는 최종평가에 한 해 1회 재응시가 가능합니다.</li>
                                    </ul>
                                    <div class="inner_box">
                                        <p class="txt_circle_num"><span>①</span>진도율 80% 이상</p>
                                        <p class="txt_circle_num"><span>②</span>모든 평가 및 과제 응시 완료 후 평가점수가 60점 미만</p>
                                        <p class="txt_circle_num"><span>③</span>최종평가가 있는 과정</p>
                                    </div>
                                    <ul class="info_list">
                                        <li>평가창 접속시점부터 평가시간이 시작되며 임시저장 시에도 입력한 답안만 저장될 뿐 시간은 계속 흘러갑니다. 되도록 재입장 없이 한 번에 응시 완료하시길 권장 드립니다.</li>
                                        <li>정해진 시간 내 평가지 미제출 시 자동제출 처리됩니다.</li>
                                        <li>제출은 서버시간을 기준으로 체크되며 종료일 자정 근접하여 접속 시 기본응시시간과 관계없이 진행됩니다.<br>
                                            (ex. 종료일 23시 30분에 접속한 경우 응시시간 30분 제공)</li>
                                        <li>PC 및 네트워크 오류 발생 시 재부팅 후 접속하여 남은 시간 내 응시 완료해야 합니다.</li>
                                    </ul>
                                </dd>
                                <dd class="mt30">
                                    <h5 class="point">과제 제출</h5>
                                    <ul class="info_list">
                                        <li>과제는 시스템 과부하로 인핸 제출 오류, 파일 미첨부 등의 이유로 제출에 문제가 발생할 수 있으므로 학습종료 2~3일 전까지 제출해 주시길 권장드립니다.</li>
                                        <li>과제는 진도율 80% 이상 시 제출 가능합니다.</li>
                                        <li>과제 제출은 1회만 가능하며 제출 완료 후에는 수정 및 재제출 불가하오니 최종 제출 전 반드시 임시저장 상태에서 내용 수정 및 보완 바랍니다.</li>
                                        <li>보안 파일은 채점이 불가하오니 파일 첨부 시 반드시 보안 해제 후 제출 부탁 드리니다.</li>
                                    </ul>
                                    ※ 보안(DRM) 적용 리포트 제출 시 0점 처리될 수 있으니 이점 유의바랍니다.
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