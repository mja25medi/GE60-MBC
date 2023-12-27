$(function() {
	$('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 550;
        bottomOffset = 60;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset - bottomOffset;
        if (height < 1) height = 1;
        if (height > (topOffset+bottomOffset)) {
            $("#content").css("min-height", (height) + "px");
        }
    });

    var element = $('ul.nav a').filter(function() {
        return this.id == MENUCODE;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

$(document).keydown(function(e){
	if(e.target.nodeName != 'INPUT' && e.target.nodeName != 'TEXTAREA') {
		if(e.keyCode === 8 ) return false;
	}
});
window.history.forward(0);
