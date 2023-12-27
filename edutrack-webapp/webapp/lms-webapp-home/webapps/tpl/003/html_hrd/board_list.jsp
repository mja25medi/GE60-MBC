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
                            <li class="active"><a href="#">공지사항</a></li>
                            <li><a href="#">학습자료실</a></li>  
                            <li><a href="#">1:1 문의하기</a></li> 
                            <li><a href="#">FAQ</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">공지사항</h3>
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
                        <caption>공지사항 테스트 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w8 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">첨부파일</th>
                                <th scope="col" class="m_hidden">등록일</th>
                                <th scope="col" class="m_hidden">조회수</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
                            <tr class="notice">
                                <td class="m_hidden"><i class="icon_notice">공지</i></td>
                                <td class="title"><a href="#">온라인교육 개인정보변경 관련</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">651</td>
                            </tr>
                            <tr class="notice">
                                <td class="m_hidden"><i class="icon_notice">공지</i></td>
                                <td class="title"><a href="#">금일 19시~21시 전산 점검 안내입니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">1568</td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="title" data-title="타이틀"> <a href="#">사이트 오픈 공지 입니다.<i class="xi-new"></i><span class="sr-only">새글</span></a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">33</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">16</td>
                                <td class="title"><a href="#">웹표준 브라우저 설치 안내에 대해 알려드립니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">456</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">15</td>
                                <td class="title"><a href="#">온라인교육 상담·안내 전화번호 변경안내</a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">1</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">14</td>
                                <td class="title"><a href="#">패스워드 찾기 관련 안내</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">19</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">13</td>
                                <td class="title"><i class="xi-lock"></i><span class="sr-only">비밀글</span><a href="#">
                                    로그인이 잘 안될때 해결 방법입니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">116</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">12</td>
                                <td class="title"><i class="xi-subdirectory-arrow"><span class="sr-only">답글</span></i><a
                                    href="#">로그인이 잘 안될때 해결 방법입니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">32</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">11</td>
                                <td class="title"><a href="#">웹표준 브라우저 설치 안내에 대해 알려드립니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">1567</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">10</td>
                                <td class="title"><a href="#">금일 19시~21시 전산 점검 안내입니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">356</td>
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