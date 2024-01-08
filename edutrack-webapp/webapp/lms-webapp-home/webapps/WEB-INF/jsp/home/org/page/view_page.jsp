<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
	<head>
		<style>
		.terms {overflow-y: auto; height: 1000px; line-height: 1.6;}
		.box {padding: 3rem; display: flex; border-radius: 1rem; border: 1px solid #dddddd;}
		</style>
	</head>
	<body>
		<div class="content-section">
			<div class="contentWrap">
				${vo.pageCts }
			</div>
		</div>
				
		<script>
		var pageCd = '${vo.pageCd}';
		$(document).ready(function() {
			if(pageCd == 'PAGE001' || pageCd == 'PAGE002'){
				$("#pageCts").addClass("terms box");
			}
		});
		
		</script>
		        
<script type="text/javascript">
	 $(document).ready(function(){
		 /* sub slide */
		 $('.slider_system').slick({
			centerMode: true,
			centerPadding: '150px',
			infinite: true,
			arrows: true,
			dots: true,
			autoplay: false,
			autoplaySpeed: 4000,
			fade: false,
			slidesToShow: 1,
			responsive: [
				{
				  breakpoint: 1217,
				  settings: {
					fade: false,
					centerMode: true,
					centerPadding: '40px',
					slidesToShow: 1
				  }
				},
				{
				  breakpoint: 786,
				  settings: {
					fade: false,
					centerMode: true,
					centerPadding: '10px',
					slidesToShow: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					arrows: false,
					centerMode: false,
					slidesToShow: 1
				  }
				}
			]
		});

		$('.slider_photo').slick({
			infinite: true,
			arrows: true,
			dots: false,
			autoplay: true,
			autoplaySpeed: 5000,
			slidesToShow: 3,
			slidesToScroll: 1,
			responsive: [
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
					arrows: false,
					slidesToShow: 2
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					dots: true,
					arrows: false,
					slidesToShow: 1
				  }
				}
			]
		});

		$('.facList').slick({
			infinite: true,
			arrows: false,
			dots: true,
			autoplay: true,
			autoplaySpeed: 5000,
			slidesToShow: 1,
			slidesToScroll: 1,
			responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					dots: true,
					slidesToShow: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					dots: true,
					slidesToShow: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					dots: true,
					slidesToShow: 1
				  }
				}
			]
		});
	});
   
	
</script>
	</body>
</html>