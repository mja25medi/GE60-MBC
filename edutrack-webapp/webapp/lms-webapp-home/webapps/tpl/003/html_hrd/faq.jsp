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
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">학습자료실</a></li>  
                            <li><a href="#">1:1 문의하기</a></li> 
                            <li class="active"><a href="#">FAQ</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">FAQ</h3>

                    <ul class="tabs">
                        <li class="active"><a href="#" title="선택된 탭메뉴">전체</a></li>
                        <li><a href="#" title="비활성 탭메뉴">수강신청</a></li>
                        <li><a href="#" title="비활성 탭메뉴">결제방법</a></li>
                        <li><a href="#" title="비활성 탭메뉴">회원정보</a></li>
                        <li><a href="#" title="비활성 탭메뉴">학습문의</a></li>
                        <li><a href="#" title="비활성 탭메뉴">학습장애</a></li>
                        <li><a href="#" title="비활성 탭메뉴">고용보험환급</a></li>
                        <li><a href="#" title="비활성 탭메뉴">기타</a></li>
                    </ul>

                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>12</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>1</strong>/10</span>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                                <legend class="blind">공지사항 검색</legend>
                                <select class="form-select mr5" name="keyField" id="select" title="검색어 분류를 선택하세요.">
                                    <option value="ALL" selected="selected">전체</option>
                                    <option value="TITLE">제목</option>
                                    <option value="CONTENT">내용</option>
                                    <option value="NAME">작성자</option>
                                </select>
                                <input type="text" class="form-control w50" name="keyWord" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="">
                                <button type="submit" class="btn type2">검색</button>
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->

                    <div class="listFaq">
                        <ul>
                            <li class="active">
                                <a href="#A1" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">"교차 사이트 스트립팅을 방지하기 위해 ......." 메시지가 뜰 때</span>
                                </a>
                                <div class="answer" id="A1">
                                  <div class="answer_box">
                                    <span class="category">A</span>
                                    <div class="con">XSS 라고 불리는 교차 사이트 스크립팅은 악의적 스크립트로 인한 해킹 방법으로 이러한 보안 취약점에 대비하기 위해 브라우저와 eClass 시스템에서는 필터를 통해 걸러내도록 하고 있습니다. 악의적 공격에 대비하기 위한 방법이지만, 사이트에서 복사 붙여 넣기를 하거나 한글, 워드에서 복사 붙여넣기를 할 경우에도 시스템이 오해할 만한 스크립트가 삽입되어 필터링 되게 됩니다.<br>

                                    eClass 시스템을 사용하시다가 아래와 같은 메시지와 함께 정상동작하지 않는다면 아래의 내용을 적용해 주시기 바랍니다.</div>
                                </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A2" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">Chrome 브라우저 설치하기</span>
                                </a>
                                <div class="answer" id="A2">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">eClass 시스템 사용 시에 문제가 있으실 경우 크롬 브라우저를 통해 문제가 해결될 수 있습니다.<br>
                                        인터넷 브라우저의 주소창에 https://www.google.co.kr/chrome/browser/desktop/ 를 입력하시거나 링크를 클릭해 주십시오</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A3" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">동영상 재생에 대한 대처 방법</span>
                                </a>
                                <div class="answer" id="A3">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">안녕하세요.
                                        동영상 재생에 대한 대처 방법 안내드립니다.
                                        동영상 재생이 안되는 경우 익스플로러 초기화를 진행해 주시기 바랍니다.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A4" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">비밀번호를 잊어버렸습니다.</span>
                                </a>
                                <div class="answer" id="A4">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">비밀번호 찾기 기능을 먼저 이용해주세요. 이 방법으로 정상 변경이 어려우실 경우에는 044-861-8855, 8856으로 비밀번호 초기화 요청 후, 재로그인하여 주세요. 재로그인 후, 정보수정-비밀번호 입력 후, 신규 비밀번호로 변경하여 주세요.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A5" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">과거에 이수했던 수료증 발급을 하고 싶습니다.</span>
                                </a>
                                <div class="answer" id="A5">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">로그인 후, 정보수정-교육이수내역에서 과거 이수했던 수료증 기록을 확인 하실 수 있습니다.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A6" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">기존에 가입한 교육번호로 로그인이 안 돼요</span>
                                </a>
                                <div class="answer" id="A6">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">홈페이지 개편으로 새로 회원가입을 진행해주셔야 합니다. 본인인증을 통해 재가입하시면, 기존 수강 내역이 자동으로 이관됩니다.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A7" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">홈페이지 회원가입은 어떻게 하나요.</span>
                                </a>
                                <div class="answer" id="A7">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">홈페이지 우측 상단 회원가입 클릭<br><br>

                                        ◦ 이용약관 및 개인정보 수집 및 이용에 대한 동의<br><br>

                                        ◦ 휴대폰 또는 아이핀으로 본인인증<br><br>

                                        - 휴대폰을 이용한 본인인증 방법: 회원가입 하시려는 분의 명의로 등록된 휴대폰을 통해 본인확인 인증을 진행하실 수 있습니다.<br><br>

                                        - 아이핀을 이용한 본인인증 방법: 회원가입 하시려는 분의 아이핀 아이디를 발급받아 본인확인 인증을 진행하실 수 있습니다. <br><br>

                                        ◦ 정보 입력<br><br>

                                        - 비밀번호는 영문(대소문자 구분), 숫자, 특수문자(~!@#$%^&*-_?) 중 2가지를 조합하여 8~16자리 설정<br><br>

                                        - 재직증명서(최근 1년 이내 발급) 업로드<br><br>

                                        - 이메일 및 문자 수신 동의 체크</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A8" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">문제풀이를 하는데 너무 어려워요. 70점을 넘기지 못하면 어떡하나요?</span>
                                </a>
                                <div class="answer" id="A8">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">문제풀이는 총 3번의 기회가 제공됩니다. 중복되는 문제도 있으니 충분히 풀 수 있는 부분입니다. 만약, 세 번 모두 70점을 넘기지 못하였을 경우, 재수강 후 재시험이 가능합니다.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A9" class="question">
                                  <span class="category">Q</span>
                                  <span class="title">교육을 받지 못해 과태료가 부과 되었습니다. 어떻게 해야 하나요?</span>
                                </a>
                                <div class="answer" id="A9">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">우선 동물보호관리시스템은 교육만 제공하고 있어 과태료 부과에 대한 답변이 어렵습니다. 과태료 관련 자세한 내용은 관할 지자체 담당자에게 문의하시기 바랍니다.</div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="#A10" class="question">
                                    <span class="category">Q</span>
                                    <span class="title">교육 이수 기간은 어떻게 되나요?</span>
                                </a>
                                <div class="answer" id="A10">
                                    <div class="answer_box">
                                        <span class="category">A</span>
                                        <div class="con">
                                            <div class="table">
                                                <table>
                                                    <caption>교육이수시간 표 - 동물판매업자(생산/판매/수입,전시/위탁관리,미용/운송), 동물보호명예감시원, 맹견소유자, 교육시간, 교육인정기간</caption>
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" rowspan="2"></th>
                                                            <th scope="col" colspan="3">동물판매업자</th>
                                                            <th scope="col" rowspan="2">동물보호명예감시원</th>
                                                            <th scope="col" rowspan="2">맹견소유자</th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="col">생산/판매/수입</th>
                                                            <th scope="col">전시/위탁관리</th>
                                                            <th scope="col">미용/운송</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row">교육시간</th>
                                                            <td colspan="3">1년에 약 3시간 <strong class="color">(5강)</strong></td>
                                                            <td>1년에 약 6시간 <strong class="color">(12강)</strong></td>
                                                            <td>1년에 약 3시간 <strong class="color">(5강)</strong></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="2">교육인정기간</th>
                                                            <td colspan="5">1년</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" >
                                                                수료일로부터 1년
                                                                (예) 2021.11.6. 수료 시, 2021.11.6.~2022.11.5. 수료 인정
                                                                * 수료 갱신 시, 이전 수료 종료 날짜 3개월 전부터 수강 가능
                                                                (예) 2021.11.27. 수료 시, 
                                                            2022.9.27.부터 수강 가능</td>
                                                            <td colspan="2">연중 상관없이 1년에 한 번만 수강하면 됨
                                                            (예) 2021.1.1. 수료 → 2021.1.1.~2021.12.31. 수료 인정</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="board_pager">
                        <span class="inner">
                            <a href="" class="page_first" title="첫페이지"><i class="xi-angle-left-min"></i><span class="sr_only">첫페이지</span></a>
                            <a href="" class="page_prev" title="이전페이지"><i class="xi-angle-left-min"></i><span class="sr_only">이전페이지</span></a>
                            <a href="" class="page_now" title="1페이지"><strong>1</strong></a>
                            <a href="" class="page_none" title="2페이지">2</a>
                            <a href="" class="page_none" title="3페이지">3</a>
                            <a href="" class="page_next" title="다음페이지"><i class="xi-angle-right-min"></i><span class="sr_only">다음페이지</span></a>
                            <a href="" class="page_last" title="마지막페이지"><i class="xi-angle-right-min"></i><span class="sr_only">마지막페이지</span></a>
                        </span>
                    </div>
                    <!-- //board_pager -->
                    
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>