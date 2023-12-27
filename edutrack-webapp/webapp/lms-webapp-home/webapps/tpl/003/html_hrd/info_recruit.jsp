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
                                    <li>개발원소개</li>
                                    <li><span class="current">인재채용</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">개발원소개</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">개발원 이야기</a></li>
                            <li><a href="#">조직현황/강사진</a></li>  
                            <li><a href="#">세미콜론 매거진</a></li> 
                            <li class="active"><a href="#">인재채용</a></li> 
                            <li><a href="#">뉴스룸</a></li> 
                            <li><a href="#">오시는길</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">인재채용</h3>
                    <div class="sub_desc">스마트인재개발원에서 미래를 함께 하고 싶은 모든 분들을 모십니다.
                        <a href="https://www.saramin.co.kr/zf_user/jobs/relay/view?rec_idx=44218064&amp;view_type=list" target="_blank" class="job_link">2022.10 하반기 채용공고(클릭!) <i class="xi-external-link"></i></i></a>
                    </div>

                    <div class="info_recruit">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="column-inner">
                                    <div class="page_tit">스마트인재개발원의<br> 강의/직무 분야 소개</div>
                                    <div class="page_desc">스마트인재개발원은 전원 내부 강사진으로<br>
                                    운영되므로 내가 원하는 강의분야와 직무분야를<br>
                                    선택한 후 병행하여 업무를 진행합니다.</div>
                                    <div class="go_link"><a href="#apply" class="link">입사지원 하기</a></div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="column-inner staff_field">
                                    <dl>
                                        <dt>강의분야</dt>
                                        <dd>
                                            <ul class="default_list">
                                                <li>Java, DB, Python, HTML/CSS/JS, JSP/Servlet</li>
                                                <li>Android, IoT, Hadoop, Linux, ML, DL, IT자격증</li>
                                                <li>UI/UX, Design Thinking, DCX, DT, 취업스킬</li>
                                            </ul>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>업무분야</dt>
                                        <dd>
                                            <ul class="default_list two">
                                                <li>교육운영/기획</li>
                                                <li>취업관리</li>
                                                <li>교육생 모집/홍보</li>
                                                <li>사업관리</li>
                                                <li>프로젝트 관리</li>
                                                <li>전략사업</li>
                                            </ul>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>

                        <div class="recruit_process">
                            <div class="page_tit">
                                <h4>자체 강사양성시스템 운영</h4>
                            </div> 
                            <div class="page_desc">강의경험, 관련분야 경험이 없어도 걱정하지마세요!<br>
                                처음부터 스마트인재개발원이 도와드립니다!</div>
                            <div class="step">
                                <ul class="top">
                                    <li>
                                        <div class="step">STEP. 01</div>
                                        <div class="ctt"><strong>교육 중 스터디 랩 참여</strong>
                                            교육 중 배운 지식을 기반으로 모의강의를
                                            통해 지속적으로 강의스킬을 꾸준히 기르는
                                            절차로 가장 중요한 절차입니다.
                                            <ul>
                                                <li>- 스마트인재개발원 교육수강 필수</li>
                                                <li>- 주 1~2회 연구한 주제로 모의강의</li>
                                                <li>- 장비, 식비, 교통비, 교재비 지원</li>
                                            </ul>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="step">STEP. 02</div>
                                        <div class="ctt"><strong>보조강사(TA) 및 인턴 채용</strong>
                                            STEP. 01 수행 후 선발되면 인턴으로
                                            채용되며 인턴기간동안 담당업무와 함께
                                            최종 모의강의 리허설을 진행합니다.
                                            <ul>
                                                <li>- 강의분야 고도화를 위한 실전 리허설</li>
                                                <li>- 강의분야에 따른 보조강사(TA) 투입</li>
                                                <li>- 개인 별 업무/강의 적합도 분석</li>
                                            </ul>
                                        </div>
                                    </li>
                                    <li class="last">
                                        <div class="step">STEP. 03</div>
                                        <div class="ctt"><strong>정식 강사 및 연구원 채용</strong>
                                            STEP. 01~02 수행 후 최종 합격 시 정규직
                                            연구원과 강사로 채용됩니다. 여러분들의
                                            꿈과 능력을 마음껏 펼쳐주세요!
                                            <ul>
                                                <li>- 강의분야에 따른 주강사 투입</li>
                                                <li>- 강의분야 다양화 (1인 2과목 이상)</li>
                                                <li>- 담당부서 및 업무배정 </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <p class="caution">※ 경력/전공자, 직무 등에 따라 양성/채용절차는 변경될 수 있습니다.</p>
                        </div>

                        <div class="recruit_benefit">
                            <div class="page_tit">
                                <h4>스마트인재개발원에 입사하면<br>
                                    받을 수 있는 스마트한 혜택!</h4>
                            </div> 
                            <div class="staff_benefit">
                                <ul>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff1.png"></div>
                                        <div class="tit"><strong>각종 수당/인센티브 지급</strong>열정만 요구하지 않습니다, 보여주신 열정에 따른 보상은 충분히 보답해 드립니다.</div>
                                        <div class="accent">- 담임 및 수료생취업연계 수당지급<br>
                                            - 초과강의, 연장근무, 자격증 수당지급<br>
                                            - 매년 성과급/인센티브 지급</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff2.png"></div>
                                        <div class="tit"><strong>대학원 등록금 지원</strong>좀 더 다양하고 수준높은 학업과 학위취득을 위해 대학원을 가고싶지만, 부족한 시간과 비싼 학비로 어려웠던 분들을 위해!</div>
                                        <div class="accent">석/박사 학위 취득을 위한 대학원 진학 시 매 학기 등록금을 50% 지원해 드립니다.</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff3.png"></div>
                                        <div class="tit"><strong>연차 및 연차수당 지원</strong>일할 땐 일하고, 쉴때는 쉬자!<br>
                                            근속년수에 따라 매년 연차는 늘어나고 연차를 쓸때 눈치주지 않습니다.</div>
                                        <div class="accent">다 못쓴 연차는 내년에 이월해서 사용하거나 연차수당으로 지급해 드립니다.</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff4.png"></div>
                                        <div class="tit"><strong>도서구입/학습콘텐츠 무제한 지원</strong>더 공부하고 싶은데, 더 잘하고 싶은데..<br>
                                            듣고싶은 강의가, 구독하고 싶은 콘텐츠가, 보고싶은 책이 있으신가요? </div>
                                        <div class="accent">자기계발 및 자기성장에 필요한 도서, 콘텐츠 강의 등 무제한으로 지원해 드립니다.</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff5.png"></div>
                                        <div class="tit"><strong>외부 강의 및 컨퍼런스 지원</strong>내 강의/업무분야, 평소에 관심있던 인사의 강의를 듣고싶거나, 관심있던 분야의 외부 강의나 컨퍼런스에 가고싶다구요?</div>
                                        <div class="accent">국/내외 컨퍼런스와 외부강의 참여 시 출장비/강의료/참가비를 지원해드립니다.</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff6.png"></div>
                                        <div class="tit"><strong>풍요로운 명절을 위한 선물</strong>말만 풍요로운 설날과 추석, 아닙니다..<br>
                                            정말 풍요로운 명절을 위해 대표님이 힘이 닿는데까지.. 선물.. 드려보겠습니다..</div>
                                        <div class="accent">지금까지는 30만원 상당 명절 선물을 드렸어요.. 회사가 크면 더 많이 드릴게요..</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff7.png"></div>
                                        <div class="tit"><strong>간식/회식비 무제한 지원</strong>항상 다과 수납장에는 신상과자와 음료가 가득 차 있고, 불편한 전체 회식보다는 각 팀별로 소규모 회식은 언제든지 환영!</div>
                                        <div class="accent">- 팀워크 향상을 위한 회식비 지원<br>
                                            - 당이 필요한 직원들을 위한 간식 지원</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff8.png"></div>
                                        <div class="tit"><strong>교통비/유류비 지원</strong>스마트인재개발원은 특성상 2개지점으로 나뉘어 있습니다, 이동이 잦을 수 있기에 교통비/유류비를 지원해 드립니다!</div>
                                        <div class="accent">- 자차 보유 시 유류비 지원<br>
                                            - 대중교통 이용 시 교통비 일부 지원</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff9.png"></div>
                                        <div class="tit"><strong>건강과 취미를 지켜드려요</strong>매년 1회 종합건강검진을 무료로 실시하고 취미가 비슷한 동료들끼리 건강증진을 위해 동호회 활동을 권장합니다!</div>
                                        <div class="accent">- 동호회 활동비용 지원<br>
                                            - 종합검진 무료 지원 및 1일 휴가 지원</div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="../img/contents/staff6.png"></div>
                                        <div class="tit"><strong>생일 축하, 회사에서도 해드려요!</strong>입사한지 하루가 지났든, 1년이 지났든 주말이 아니라면 생일파티는 꼭 챙겨드립니다, 아! 주말 생일이어도 선물은 챙겨드려요!</div>
                                        <div class="accent">- 생일자 케이크 제공<br>
                                            - 5만원 상당 기프티콘 선물</div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="row recruit_join">
                            <div class="col-sm-6">
                                <div class="column-inner">
                                    <div class="page_tit">스마트인재개발원<br> 입사절차 안내</div>
                                    <div class="page_desc">신입강사 지원의 경우 자체 강사양성시스템을<br>
                                        거쳐 선발되며 경력직이나 전공자, 직무에 따라<br>
                                        입사절차는 변경될 수 있습니다.</div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="column-inner">
                                    <img src="../img/contents/staff_step.png">
                                </div>
                            </div>
                        </div>

                        <div id="apply" class="recruit_form">
                            <div class="page_tit">
                                <h4>입사지원서 작성</h4>
                            </div>
                            <div class="tstyle mb30">
                                <ul class="dbody">
                                    
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
                                            <label for="selectPhone1" class="form-label col-sm-2"><span>연락처</span><i class="icon_star" aria-hidden="true"></i></label>
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
                                                </div>
                                            </div>
                                        </div>
                                    </li> 
                                    <li>
                                        <div class="row">
                                            <label for="jobType" class="form-label col-sm-2"><span>지원분야</span><i class="icon_star" aria-hidden="true"></i></label>
                                            <div class="col-sm-10">
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType1">
                                                    <label for="jobType1">사업관리팀</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType2">
                                                    <label for="jobType2">기획팀</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType3">
                                                    <label for="jobType3">교육운영팀</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType4">
                                                    <label for="jobType4">전문강사</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType5">
                                                    <label for="jobType5">전략사업부</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType6">
                                                    <label for="jobType6">홍보팀</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobType7">
                                                    <label for="jobType7">취업지원팀</label>
                                                </span>                                               
                                            </div>
                                        </div>
                                    </li>  
                                    <li>
                                        <div class="row">
                                            <label for="jobCareer" class="form-label col-sm-2"><span>지원분야 경력</span><i class="icon_star" aria-hidden="true"></i></label>
                                            <div class="col-sm-10">
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobCareer1">
                                                    <label for="jobCareer1">5년 이상</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobCareer2">
                                                    <label for="jobCareer2">3 ~ 5년</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobCareer3">
                                                    <label for="jobCareer3">1 ~ 2년</label>
                                                </span>
                                                <span class="custom-input">
                                                    <input type="checkbox" name="name" id="jobCareer4">
                                                    <label for="jobCareer4">신입</label>
                                                </span>
                                            </div>
                                        </div>
                                    </li> 
                                    <li>
                                        <div class="row">
                                            <label for="jobCareer" class="form-label col-sm-2"><span>첨부파일</span><i class="icon_star" aria-hidden="true"></i></label>
                                            <div class="col-sm-10 attach_area">
                                                <input type="file" class="input_attach sr-only" id="attchFile" multiple><label for="attchFile" class="btn gray2">파일 찾기</label> 
                                                <div class="attach_list">
                                                    <div class='attach_name'>홍길동_이력서.hwp<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                                    <div class='attach_name'>홍길동_포트폴리오.pdf<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                                    <div class='attach_name'>홍길동_강의경력증명.zip<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                                </div>
                                                <small class="note mt10">* 이력서/자소서/포트폴리오/강의경력증명 등을 첨부해주세요.<br>
                                                    (최대 1개의 파일, 용량은 20MB, 형식은 .zip / .pdf / .hwp / .word 파일만 업로드 가능)
                                                    
                                                </small>                                            
                                            </div> 
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="btns mt30">
                                <button type="button" class="btn type4">입사지원 완료</button>
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