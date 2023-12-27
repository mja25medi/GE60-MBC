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
		
		    $('.news_slider_list').slick({
		      infinite: true,
		      arrows: true,
		      dots: false,
		      // autoplay: true,
		      autoplaySpeed: 5000,
		      slidesToShow: 4,
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
	});
   
	
</script>
	</body>
</html>