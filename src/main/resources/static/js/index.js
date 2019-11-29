$(function() {
	
	//获取系统时间
	function indexTime() {		
		var now = new Date(),
			year = now.getFullYear(), //得到年份
			month = now.getMonth() + 1, //得到月份
			day = now.getDate(), //得到日期
			hour = now.getHours(), //得到小时
			minu = now.getMinutes(), //得到分钟
			seconds = now.getSeconds(); //秒

		function check(i){
			var num;
			i<10?num="0"+i:num=i;
			return num;
		}
		
		hour = check(hour);
		minu = check(minu);
		seconds = check(seconds);
			
		$(".broadcast_text h2").each(function(){
		    var time = "";
		    time = year + "年" + month + "月" + day + "日" + " " + hour + ":" + minu + ":" + seconds ;
		    $(this).html(time);
		});	
	}
	
	setInterval( indexTime , 1000);
	
	//超出部分显示省略号
    $(".broadcast_text p").each(function() {
		var maxwidth = 60;
		if($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() +'...');
		}
	});


	//跳转PPT
	$($(".resources_box")[0]).click(function(){
		window.location.href = '../ppt';
	});

    //跳转图片
    $($(".resources_box")[1]).click(function(){
        window.location.href = '../jpg';
    });

    //跳转动画
    $($(".resources_box")[2]).click(function(){
        window.location.href = '../swf';
    });

    //跳转课程列表
    $($(".resources_box")[5]).click(function(){
        window.location.href = '../list_of_catalogues';
    });

});

