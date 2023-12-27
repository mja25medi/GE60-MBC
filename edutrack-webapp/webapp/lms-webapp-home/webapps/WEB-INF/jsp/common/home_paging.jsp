<%@ page language="java" pageEncoding="utf-8" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="pageIndex" value="${pageInfo.currentPageNo }"/>
<c:set var="lastIndex" value="${pageInfo.lastPageNoOnPageList}"/>
<c:if test="${not empty pageInfo and lastIndex ne 1 }">

<section class="pagination" role="navigation">
<div class="pageSlider long"></div>
<div class="pageForm">
	<c:if test="${pageIndex ne 1}"><a class="pagePrev pageSkip" href="#" onclick="${param.funcName }(${pageIndex - 1})" title="<spring:message code="home.page.prev"/>"><spring:message code="home.page.prev"/></a></c:if>
	<c:if test="${pageIndex eq 1 }"><a class="pagePrev pageSkip" href="#" onclick="${param.funcName }(1);" title="<spring:message code="home.page.prev"/>"><spring:message code="home.page.prev"/></a></c:if>
	<input id="pageInput" class="pageInput" type="text" maxlength="3" placeholder="#">
	<c:if test="${lastIndex ne pageIndex }"><a class="pageNext pageSkip" href="#" onclick="${param.funcName }(${pageIndex + 1 });" title="<spring:message code="home.page.next"/>"><spring:message code="home.page.next"/></a></c:if>
	<c:if test="${lastIndex eq pageIndex }"><a class="pageNext pageSkip" href="#" onclick="${param.funcName }(${pageIndex });" title="<spring:message code="home.page.next"/>"><spring:message code="home.page.next"/></a></c:if>
	<button class="pageButton" title="Go to chosen page of results"><spring:message code="home.page.move"/></button>
</div>
</section>

<script type="text/javascript">
	/********** 2017.01.05 arothy 사용자 페이징 처리  **********/
	
	var pagesMax = ${lastIndex}	//마지막 페이지
	var pagesMin = 1;	
	var startPage = ${pageIndex};	//현재 페이지
	
	$('.pagination .pageSlider').slider({
	
	value: startPage, max: pagesMax, min: pagesMin,
	animate: true,
	
	create: function( event, ui ) {
	  
	  $('.pagination .pageSlider .ui-slider-handle').attr({
		"aria-valuenow": startPage,
		"aria-valuetext": "Page " + startPage,
		"role": "slider",
		"aria-valuemin": pagesMin,
		"aria-valuemax": pagesMax,
		"aria-describedby": "pageSliderDescription" 
	  });
	   
	  $('.pagination .pageInput').val( startPage );
	
	}
	
	}).on( 'slide', function(event,ui) {
	  
	  // let user skip 10 pages with keyboard ;)
	  if( event.metaKey || event.ctrlKey ) {
		
		if( ui.value > $(this).slider('value') ) {
		  
		  if( ui.value+9 < pagesMax ) { ui.value+=9; } 
		  else { ui.value=pagesMax }
		  $(this).slider('value',ui.value);
		
		} else {
		  
		  if( ui.value-9 > pagesMin ) { ui.value-=9; } 
		  else { ui.value=pagesMin }
		  $(this).slider('value',ui.value);
		  
		}
		
		event.preventDefault();
		
	  }
	  
	  $('.pagination .pageNumber span').text( ui.value );
	  $('.pagination .pageInput').val( ui.value );
	  
	}).on('slidechange', function(event, ui) {
	
	  $('.pagination .pageNumber')
		.attr('role','alert')
		.find('span')
		.text( ui.value );
	
	  $('.pagination .pageInput').val( ui.value );
	  
	  $('.pagination .pageSlider .ui-slider-handle').attr({
		"aria-valuenow": ui.value,
		"aria-valuetext": "Page " + ui.value 
	  });
	
	});
	
	$('.pagination .pageSlider .ui-slider-handle').on( 'keyup' , function(e) {
	
	if( e.which == 13 ) {
	var curPage = $('.pagination .pageSlider').slider('value');
	}
	
	});
	
	
	$('.pagination .pageInput').on( 'change' , function(e) {
	$('.pagination .pageSlider').slider( 'value', $(this).val() );
	});
	
	var tmr;
	$('.pageSkip').on('click', function(e) {
	
	e.preventDefault();
	
	var $this = $(this);
	
	if( $this.hasClass('pageNext') ) {
	  var curPage = $('.pagination .pageSlider').slider('value')+1;
	} else if( $this.hasClass('pagePrev') ) {
	  var curPage = $('.pagination .pageSlider').slider('value')-1;
	}
	
	$('.pagination .pageSlider').slider('value',curPage);
	
	clearTimeout(tmr);
	tmr = setTimeout( function() {
	},1000);
	
	});
	
	function sliderPips( min, max ) {
	
	var pips = max-min;
	var $pagination = $('.pagination .pageSlider');
	
	for( i=0; i<=pips; i++ ) {
	
	var s = $('<span class="pagePip"/>').css({ 
	  left: '' + (100/pips)*i + '%' 
	});
	
	$pagination.append( s );
	
	}
	
	var minPip = $('<span class="pageMinPip">'+min+'</span>');
	var maxPip = $('<span class="pageMaxPip">'+max+'</span>');
	$pagination.prepend( minPip, maxPip );
	
	};sliderPips( pagesMin, pagesMax );
	
	
	function sliderLabel() {
	$('.pagination .ui-slider-handle').append(
	'<span class="pageNumber"><spring:message code="home.page.page"/> <span>' + 
	$('.pagination .pageSlider').slider('value') + 
	'</span></span>');
	};sliderLabel();
	
	//이동 버튼 클릭 시
	$('.pagination .pageButton').on('click', function(e) {
		e.preventDefault();
		var curPage = $('.pagination .pageSlider').slider('value');
		//함수 실행
		${param.funcName }(curPage);
	});

</script>

</c:if>