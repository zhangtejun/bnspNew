
$(function() {

	$(".hover_xg li").hover(function() {
		$(this).children('.img_jieshi').fadeIn('fast');

	},function(){
		$(this).children('.img_jieshi').fadeOut('fast');
	});

	// 标签切换
	$(".tabs_c > div:gt(0)").hide();
	$(".tabs > ul > li").click(function(){
		$(this).addClass("tabs_on").siblings().removeClass("tabs_on")
		var liindex=$(this).index();
		$(".tabs_c > div").eq(liindex).fadeIn().siblings().hide()
	});
	// 标签切换
	$(".tabs_c2 > div:gt(0)").hide();
	$(".tabs2 > ul > li").click(function(){
		$(this).addClass("tabs_on2").siblings().removeClass("tabs_on2")
		var liindex=$(this).index();
		$(".tabs_c2 > div").eq(liindex).fadeIn().siblings().hide()
	});
	// 标签切换
	$(".tabs_c3 > div:gt(0)").hide();
	$(".tabs3 > ul > li").click(function(){
		$(this).addClass("tabs_on3").siblings().removeClass("tabs_on3")
		var liindex=$(this).index();
		$(".tabs_c3 > div").eq(liindex).fadeIn().siblings().hide()
	});

  // 视频弹窗
    $(".video_left >img").click(function(event) {
      $(".alert").fadeIn('fast');
    });

    $(".close").click(function(event) {
      $(".alert").fadeOut('fast');

    });

    // 发展历程
    $(".fzlc li").click(function(event) {
    	$(this).addClass('lic_on').siblings().removeClass('lic_on');
    });
	
	$(".lic_cont > div:gt(0)").hide();
	$(".fzlc  li").click(function(){		
		var liindex=$(this).index();
		$(".lic_cont > div").eq(liindex).fadeIn().siblings().hide()
	});

    // 技术资质
    $(".jishu_list ul li").hover(function() {
    	$(this).find('.ckfd').fadeIn('fast');
    }, function() {
    	$(this).find('.ckfd').fadeOut('fast');
    });

  	$(".ckfd").click(function(event) {
  		$(this).parent('li').children(".js_box").fadeIn('fast');
  	});
  	// $(".jishu_list ul >li .close").click(function(event) {
   //    $(this).parent(".alert").fadeOut('fast');

   //  });

  	$(".guset1>ul >li").click(function(event) {
  		$(this).addClass('fl_on').siblings().removeClass('fl_on');
  		$(this).children('.down_list').slideDown('fast').parent().siblings().children('.down_list').slideUp('fast');
  		
  	});

window.onscroll = function(){ 
    var t = document.documentElement.scrollTop || document.body.scrollTop;  
    if( t >= 1400 ) { 
	      $(".ys_list1").addClass('ys_list1_1');
	     	setTimeout(function(){
					$(".ys_list2").addClass('ys_list1_1');
				},100);
				setTimeout(function(){
					$(".ys_list3").addClass('ys_list1_2');
				},200);
				setTimeout(function(){
					$(".ys_list4").addClass('ys_list1_2');
				},300);
    	}
    }

});

   // 小图片切换开始
