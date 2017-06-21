 $(document).ready(function(){
	 var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
	 $BODY = $('body'),
	 $MENU_TOGGLE = $('#menu_toggle'),
	 $SIDEBAR_MENU = $('#sidebar-menu'),
	 $SIDEBAR_FOOTER = $('.sidebar-footer'),
	 $LEFT_COL = $('.left_col'),
	 $RIGHT_COL = $('.right_col'),
	 $NAV_MENU = $('.nav_menu'),
	 $FOOTER = $('footer');
	// toggle small or large menu 
	$MENU_TOGGLE.on('click', function() {
//		  var chart = $('#container').highcharts();
//		  var chart2 = $('#container2').highcharts();
			console.log('clicked - menu toggle');
			if ($BODY.hasClass('nav-md')) {
				$SIDEBAR_MENU.find('li.active ul').hide();
				$SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass('active');
			} else {
				$SIDEBAR_MENU.find('li.active-sm ul').show();
				$SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass('active-sm');
				
			}
	
		$BODY.toggleClass('nav-md nav-sm');
//		chart.reflow();
//		chart2.reflow();
	});
	// toggle small or large menu 
	  $SIDEBAR_MENU.find('a').on('click', function(ev) {
		  console.log('clicked - sidebar_menu');
	        var $li = $(this).parent();

	        if ($li.is('.active')) {
	            $li.removeClass('active active-sm');
	            $('ul:first', $li).slideUp(function() {
	                
	            });
	        } else {
	            // prevent closing menu if we are on child menu
	            if (!$li.parent().is('.child_menu')) {
	                $SIDEBAR_MENU.find('li').removeClass('active active-sm');
	                $SIDEBAR_MENU.find('li ul').slideUp();
	            }else
	            {
					if ( $BODY.is( ".nav-sm" ) )
					{
						$SIDEBAR_MENU.find( "li" ).removeClass( "active active-sm" );
						$SIDEBAR_MENU.find( "li ul" ).slideUp();
					}
				}
	            $li.addClass('active');

	            $('ul:first', $li).slideDown(function() {
	               
	            });
	        }
	    });
	// check active menu
		$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');

		$SIDEBAR_MENU.find('a').filter(function () {
			return this.href == CURRENT_URL;
		}).parent('li').addClass('current-page').parents('ul').slideDown(function() {
			
		}).parent().addClass('active');

		

		

		// fixed sidebar
		if ($.fn.mCustomScrollbar) {
			$('.menu_fixed').mCustomScrollbar({
				autoHideScrollbar: true,
				theme: 'minimal',
				mouseWheel:{ preventDefault: true }
			});
		}
//		setInterval(current,1000); 
 });
 function current(){ 
	 var d=new Date();
	 time_now2
	 $("#time_now").html(d.getFullYear()+'年'+d.getMonth()+1+'月'+d.getDate()+'日');
	 $("#time_now2").html(d.getHours()+'时'+d.getMinutes()+'分'+d.getSeconds()+'秒');
  } 