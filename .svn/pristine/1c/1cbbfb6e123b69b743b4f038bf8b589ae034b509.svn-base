$(function() {
	getUserSession();
	userOutLogin();
});

function getUserSession(){
	$.ajax({
		url:'getUserSession',
		success:function(userSession){
			//console.log(userSession);
			if(userSession.username != null){
				$('.header_right').css('display','none');
				$('.header_user').css('display','block');
				$('#username').text(userSession.username);
			}
		}
	});
	
	
}

/**
 * 请求服务器session
 */
function getSession() {
	$.get('getUserSession', function(data) {
		console.log(data);
		$('#username').text(data.username);
	});
}

//用户退出
function userOutLogin(){
	$(".userOutLogin").click(function(){ 
		if (confirm("确定要退出吗？"))
			$.ajax({url:"userOutLogin"});
			location.reload();
	});
}
	