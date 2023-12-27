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
                            <li class="active"><a href="#">1:1 문의하기</a></li> 
                            <li><a href="#">FAQ</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">1:1 문의하기</h3>
                    
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
                            <select class="form-select" name="keyField" id="select" title="검색어 분류를 선택하세요.">
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

                    <table class="tstyle list">
                        <caption>문의 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                            <th scope="col" class="m_hidden">번호</th>
                            <th scope="col" class="m_hidden">분류</th>
                            <th scope="col" class="title">제목</th>
                            <th scope="col" class="m_hidden">첨부파일</th>
                            <th scope="col" class="m_hidden">등록일</th>
                            <th scope="col" class="m_hidden">처리상태</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="분류">학습문의</td>
                                <td class="title" data-title="타이틀"> <a href="#">학습에 대한 문의가 있어요<i class="xi-new"></i><span class="sr-only">새글</span></a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcGray">답변대기</span></td>
                            </tr>
                            <tr>
                            <td class="m_hidden">16</td>
                                <td class="m_hidden" data-title="분류">학습장애</td>
                                <td class="title"><i class="xi-lock"></i><span class="sr-only">비밀글</span><a href="#">
                                    로그인이 잘 안되는데 왜 그럴까요?</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcBlue">답변완료</span></td>
                            </tr>
                            <tr>
                                <td class="m_hidden">12</td>
                                <td class="m_hidden" data-title="분류">학습장애</td>
                                <td class="title"><i class="xi-subdirectory-arrow"><span class="sr-only">답글</span></i><a
                                    href="#">로그인이 잘 안될때 해결 방법입니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수"></td>
                            </tr>
                            <tr>
                                <td class="m_hidden">15</td>
                                <td class="m_hidden" data-title="분류">학습문의</td>
                                <td class="title"><a href="#">학습에 대한 문의가 있어요</a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="처리상태"><span class="label fcGray">답변대기</span></td>
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