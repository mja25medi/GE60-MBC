$(document).ready(function () { 
	
    const $sect01Slider = $(".event_zone .slider")
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
    
    
	//리스트 sort
    clickAC(".sort button");    
    
   $(".section02 .select .title").on("click", function () {
        active(this, "active")
    })      

    
    $('.slider_list').slick({
        infinite: true,
        arrows: true,
        dots: false,
        // autoplay: true,
        autoplaySpeed: 5000,
        slidesToShow: 4,
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
    

    /********** main best lecture **********/
    $('.section03 .best_list').slick({
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
  
    /********** popupBox **********/
    $('.popup-close').unbind('click').bind('click', function(e) {
      $('.popup-wrap, .popup-close').hide();
    });
    $('.popup-open').on('click', function() {
        $('.popup-wrap, .popup-close').css('display', 'flex');
        $('#slides').get(0).slick.setPosition()
    });
})

