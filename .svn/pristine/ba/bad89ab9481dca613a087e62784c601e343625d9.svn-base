$(function() {
	var myVideo = document.getElementById("videoid");
	$(".video_right li").click(function(){
		$(this).addClass("list_block").siblings().removeClass("list_block");
	});
	
	$(".video_right li a").click(function(){
		var videoSrc = $(this).attr("data-videosrc");
		var postersrc = videoSrc + ".jpg";
		myVideo.src=videoSrc + ".mp4";
 		myVideo.load();
 		$("#videoid").attr("poster", postersrc);
	});
	
	$("#videoid").click(function(){
		if(myVideo.paused) {
			myVideo.play();
		} else {
			myVideo.pause();
		}
	});
});

