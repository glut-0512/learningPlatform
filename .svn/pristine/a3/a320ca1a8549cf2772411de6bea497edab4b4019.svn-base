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
		$(".password_tips").removeClass("password_show");
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
	
	var InterValObj, //timer变量，控制时间
		count = 30, //间隔函数，1秒执行
		curCount, //当前剩余秒数
		codeName = $(".verification_button a");
		
	codeName.click(function(){
		curCount = count;	
		$(this).attr("disabled","true");
		$(this).css("pointer-events","none");
		$(this).html(+curCount + "秒再获取");
		InterValObj = window.setInterval(SetRemainTime, 1000);
	});
	//timer处理函数
	function SetRemainTime() {
		if(curCount == 0) {
			window.clearInterval(InterValObj); //停止计时器
			codeName.removeAttr("disabled");
			codeName.css("pointer-events","");
			codeName.html("获取验证码");
		} else {
			curCount--;
			codeName.html(+curCount + "秒再获取");
		}
	}
	
	$(".jianpan_icon").click(function(){
		var softkeysName = $(this).parent().next().children(".softkeys");
		var softkeysNull = softkeysName.html();
		$(this).parent().next().toggleClass("cuo_none");
		if(softkeysNull == ""){
			$('.softkeys').softkeys({
				target: softkeysName.data('target'),
				layout: [
					[
						['`', '~'],
						['1', '!'],
						['2', '@'],
						['3', '#'],
						['4', '$'],
						['5', '%'],
						['6', '^'],
						['7', '&amp;'],
						['8', '*'],
						['9', '('],
						['0', ')'],
						['-', '_'],
						['=', '+'],
						'←'
					],
					[
						'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', ['[', '{'],
						[']', '}']
					],
					[
						'shift',
						'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', [';', ':'],
						["'", '&quot;'],
						['\\', '|']
					],
					[
						'z', 'x', 'c', 'v', 'b', 'n', 'm', [',', '&lt;'],
						['.', '&gt;'],
						['/', '?'],
						['@']
					]
				]
			});
		} else {
			$('.softkeys').html("");
		}
	});
	
	$(document).on("click", function(evt) {
		if($(evt.target).closest(".password").length == 0) {
			$('.virtual_keyboard').addClass("cuo_none");
			$('.softkeys').html("");
		}
	});

	function sendMsg(){

		$.ajax({
			url : "sendRandom",
			method : 'post',
			data : {"email":$("#mailName").val()},
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				/*window.location.href = 'login';*/
			},
			error:function(){
				alert('服务器出错！');
			}
		});
	}

});


function sendMsg(){
	$.ajax({
		url : "sendRandom",
		method : 'post',
		data : {"email":$("#mailName").val()},
		dataType : 'json',
		success : function(data) {
			alert(data.message);
			/*window.location.href = 'login';*/
		},
		error:function(){
			alert('服务器出错！');
		}
	});
}

function submit() {
	if ($("#loginPwd_01").val()!=$("#loginPwd_02").val()){
		alert("两次输入的密码不正确！")
	}else if ($("#loginName").val()==""||$("#passwordVerification").val()==""||$("#loginPwd_01").val()==""||$("#mailName").val()==""){
		alert("请填写完整信息！");
	}else {
		$.ajax({
			url : "checkRandom",
			method : 'post',
			data : {"loginName":$("#loginName").val(),
				"checkMsg":$("#passwordVerification").val(),
				"email":$("#mailName").val(),
				"password":$("#loginPwd_01").val()},
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				window.location.href = 'login';
			},
			error:function(){
				alert('服务器出错！');
			}
		});
	}
}

