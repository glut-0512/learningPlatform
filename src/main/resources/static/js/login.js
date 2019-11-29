$(function() {
	
	function lhxDropDown(e){
		$(document).ready(function(){
			$(e +' li').hover(function(){
				$(this).children("ul").show(); //mouseover
			},function(){
				$(this).children("ul").hide(); //mouseout
			});
		});
	}
	lhxDropDown('.drop_down');
	
	
	$(".header_nav .drop_down li ul li").hover(function() {
		$(this).children(".nav_abg").stop(true).animate({
			width:"100%"
		}, 300);
	},function(){
		$(this).children(".nav_abg").stop(true).animate({
			width:"0%"
		}, 300);
	});
	
	$("input").focus(function() {
		$(this).parent().addClass("highlight");
		$(".login_tips").removeClass("password_show");
	});
	
	$("input").blur(function() {
		$(this).parent().removeClass("highlight");
	});
	
	$(".cuo_icon").click(function(){
		$(this).prev().val("");
		$(this).addClass("cuo_none");
	});
	
	$('input').bind('input propertychange', function() {
		if($(this).val()) {
			$(this).next().removeClass("cuo_none");
		} else {
			$(this).next().addClass("cuo_none");
		}
	})
	
	$(".jianpan_icon").click(function(){
		$(this).parent().next().toggleClass("cuo_none");
	});
	
	$(document).on("click", function(evt) {
		if($(evt.target).closest(".password").length == 0) {
			$('.virtual_keyboard').addClass("cuo_none");
		}
	});
	userLogin();
});

/**
 * 点击更换验证码
 */
function getCode(that) {
	that.src='getCode?d=' + Math.random();
}

//登录事件
function userLogin() {
	$("#login").unbind().click(function() {
			var code = $('#loginVerification').val();
			var username = $('#loginName').val();
			var password = $('#loginPwd').val();
			if(!username){
				alert('请输入用户名！！！');
				return;
			}
			if(!password){
				alert('请输入密码！！！');
				return;
			}

			if(!code){
				alert('请输入验证码！！！');
				return;
			}
			var map = {};
			map.code = code;
			map.loginName = username;
			map.password = password;

			//var $formParams = getParams($('form').serializeArray());
			$.ajax({
				url : "userLogin",
				method : 'post',
				data : map,
				dataType : 'json',
				success : function(resp) {
					if (resp.status) {
						//console.log(resp.message);
						/*window.location.href = "learning_process";*/
						//不使用window.history.back(-1); 该方式会出现bug，即登录后，在用户信息栏不出现用户信息，
						//原因：该方式为浏览器返回上一个页面，而上一个页面是未登录的，不会存在用户信息
						var last = document.referrer;
						//window.history.back(-1);
						//重新请求上一次页面，由于已经登录，所以存在用户信息
						if(last.indexOf("login")==-1&&last!=""&&last.indexOf("register")==-1){
							window.location.href = "collection_course";
						}else{
							window.location.href = "index";
						}
					} else {
						//console.log("false");
						$('#prompt').html(resp.message);
						$('#prompt').removeClass('hide');
					}
				}
			})
		}
	);
}