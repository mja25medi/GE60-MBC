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
                                    <li>나의 강의실</li>
                                    <li><span class="current">과정별 학습 문의</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">나의 강의실</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">나의 학습</a></li>
                            <li><a href="#">종료과정 성적조회</a></li>  
                            <li><a href="#">설문</a></li> 
                            <li class="active"><a href="#">과정별 학습 문의</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <div class="board_info">
                        <h3 class="subMenu_title">과정별 학습 문의</h3>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="keyField" id="select" title="검색어 분류를 선택하세요.">
                                <option value="ALL" selected="selected">전체</option>
                                <option value="과정1">DCX기반 빅데이터 분석서비스 개발자 과정</option>
                                <option value="과정2">과정2</option>
                            </select>
                            
                            </fieldset>
                        </form>
                    </div>

                    <table class="tstyle list">
                        <caption>학습 문의 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w25 m_hidden">
                            <col>
                            <col class="w10 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="m_hidden">과정명</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">등록일</th>
                                <th scope="col" class="m_hidden">상태</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">                            
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="과정명">빅데이터 분석서비스 개발자과정(NCS)</td>
                                <td class="title" data-title="제목">
                                    <a href="#">메서드와 프로퍼티의 차이가 뭔가요?</a>
                                </td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcGray">답변대기</span></td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="과정명">빅데이터 분석서비스 개발자과정(NCS)</td>
                                <td class="title" data-title="제목">
                                    <a href="#"><i class="xi-lock"></i>4-1 작업형 예시 질문 있습니다</a>
                                </td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcGray">답변대기</span></td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="과정명">빅데이터 분석서비스 개발자과정(NCS)</td>
                                <td class="title" data-title="제목">
                                    <a href="#"><i class="xi-lock"></i>validation에서 구체화 궁금증</a>
                                </td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcBlue">답변완료</span></td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="과정명">빅데이터 분석서비스 개발자과정(NCS)</td>
                                <td class="title" data-title="제목">
                                    <a href="#">
                                        ModelAttribute 사용하여 에러시 이전 데이터 유지 방식 질문
                                    </a>
                                </td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcBlue">답변완료</span></td>
                            </tr>                            
                        </tbody>
                    </table>
                    
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