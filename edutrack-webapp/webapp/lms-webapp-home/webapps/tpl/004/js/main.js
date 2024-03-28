$(document).ready(function () { 
	
   /* 20240116 중복/미사용 스크립트
    * const $sect01Slider = $(".event_zone .slider")
    $sect01Slider.slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true, 
        dots:false
    }).on("beforeChange", function (event, slick, currentSlide, nextSlide) {
        $(".current").text(nextSlide + 1)
    })
    
    
    $(".control_pause").on("click", function () {
        $(this).removeClass(AC).siblings("button").addClass(AC)
        $(this).parent().siblings(".slider").slick("slickPause")
    })    
    $(".control_play").on("click", function () {
        $(this).removeClass(AC).siblings("button").addClass(AC)
        $(this).parent().siblings(".slider").slick("slickPlay")
    })   

    const $anmailStorySlider = $(".basic_edu .slider")
    $anmailStorySlider.slick({
        slidesToScroll: 1,
        slidesToShow: 3,
        autoplay: false,
        dots: false
    })
    
    const $lawStorySlider = $(".law_story .slider")
    $lawStorySlider.slick({
        slidesToScroll: 1,
        slidesToShow: 1,
        autoplay: true,
        autoplaySpeed: 4000,
        arrows: false,
        dots: true,
        vertical: true
    })*/
    
	//리스트 sort
    clickAC(".sort button");    

    
   $(".section02 .select .title").on("click", function () {
        active(this, "active")
    })      

    //scrollAC(".section")
    setTimeout(function(){$(".section").addClass(AC)},100)
    
   
    
  /* 20240116 중복/미사용 스크립트
   *  $('.slider_list').slick({
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
    

    */
    /********** main popzone **********//*
   $('.pop-latest').slick({
        infinite: true,
        arrows: true,
        dots: false,
        autoplay: true,
        autoplaySpeed: 4000,
        fade: false,
        slidesToShow: 1,
        responsive: [
            {
              breakpoint: 1217,
              settings: {
                fade: false,
                slidesToShow: 1
              }
            },
            {
              breakpoint: 786,
              settings: {
                fade: false,
                slidesToShow: 1
              }
            },
            {
              breakpoint: 480,
              settings: {
                slidesToShow: 1
              }
            }
        ]
    });
*/
    /********** popupBox **********/
    $('.popup-close').unbind('click').bind('click', function(e) {
      $('.popup-wrap, .popup-close').hide();
    });
    $('.popup-open').on('click', function() {
        $('.popup-wrap, .popup-close').css('display', 'flex');
        $('#slides-main').get(0).slick.setPosition()
    });

   
/*   20240116 중복/미사용 스크립트
 *  /********** main hot news *********
 *  $('.news_slider_list').slick({
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
  });*/
})

