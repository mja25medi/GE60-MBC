<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
                            <c:forEach items="${courseList}" var="item" varStatus="status">
                            <li>
                                <a href="javascript:readCourse('${item.crsCreCd}','${item.crsCd}')">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <c:if test="${not empty item.thumbFileSn}">
			                                      	<img src="/app/file/thumb/${item.thumbFileSn }" alt="${item.crsNm}">
			                                    </c:if>
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">${item.crsNm}</p>
                                            <div class="card_labels">
                                                 <c:forEach items="${item.metaTagArr}" var="tag" varStatus="status">
				                                 	<span>${tag}</span>
				                                 </c:forEach>
                                            </div>                                                                              
                                        </div>
                                    </div>
                                </a>
                            </li>
                            </c:forEach>

						                   
<script type="text/javascript">
$(document).ready(function () { 
	
    $('.slider_list').slick({
        infinite: true,
        arrows: true,
        dots: false,
        // autoplay: true,
        autoplaySpeed: 5000,
        slidesToShow: 5,
        slidesToScroll: 1,
        responsive: [
            {
              breakpoint: 1200,
              settings: {
                fade: false,
                slidesToShow: 4
              }
            },
            {
              breakpoint: 950,
              settings: {
                dots: true,
                slidesToShow: 3
              }
            },
            {
              breakpoint: 620,
              settings: {
                dots: true,
                slidesToShow: 2
              }
            },
            {
              breakpoint: 380,
              settings: {
                dots: true,
                slidesToShow: 1
              }
            }
        ]
    });
})
function readCourse(crsCreCd,crsCd){
	if(crsCreCd == ''){
		alert("등록된 회차가 없는 과정입니다.")
	}else{
		var url = generateUrl("/home/course/readCourseCreateMain", { "mcd": 'MC00000023' ,"crsCreCd": crsCreCd, "crsCd": crsCd });
		document.location.href = url;
	}
}

</script>
	                            