$(function() {
	element();
});

//
function down() {
	$(".dlc_none .dl_content_top").click(function(){
		$(this).next("ul").slideToggle();
		var dlcName = $(this).find("h5").attr("class");
		if(dlcName == "icon-caret-down dlc_down"){
			$(this).find("h5").attr("class","icon-caret-down dlc_up");
		}
		else{
			$(this).find("h5").attr("class","icon-caret-down dlc_down");
		}
	});
	$('input[name="check-box"]').wrap('<div class="check-box"><i></i></div>');
	$.fn.toggleCheckbox = function () {
		this.attr('checked', !this.attr('checked'));
	}
	$('.check-box').on('click', function () {
		$(this).find(':checkbox').toggleCheckbox();
		$(this).toggleClass('checkedBox');
	});

	//分页
	var GG = {
		"kk":function(mm){
		}
	}
	$("#page").initPage(35,1,GG.kk);
}

function element(){

	var div = "";
	var childDiv = "";
	var appendDiv = "";
    $.ajax({
        url : '/getCourseTypeToPage',
        method : 'post',
        dataType : 'json',
//		async:false,
        success : function(data) {
			for(var i=0;i<data.flist.length;i++){
				div = 	"<div class=\"dl_content dlc_none\">" +
							"<div class=\"dl_content_top\">" +
								"<i class=\"icon-lightbulb\"></i>" +
								"<h1>"+data.flist[i].typeName+"</h1>" +
								"<h5 class=\"icon-caret-down dlc_down\"></h5>" +
								"<div class=\"clearfix\"></div>" +
							"</div>" +
							"<ul class=\"list-unstyled\">";
				for (var j=0;j<data.slist.length;j++){
					if (data.slist[j].parentId == data.flist[i].id){
						childDiv += "<li>" +
										"<div class=\"checkbox_course\">" +
											"<input type=\"checkbox\" value=\""+data.slist[j].id+"\"  name=\"check-box\" />" +
										"</div>" +
										"<p>"+data.slist[j].typeName+"</p>" +
										"<span>245</span>" +
									"</li>";
					}
				}
				div += childDiv+"</ul>" + "</div>";
				childDiv = "";
				$('#lan').after(div);
			}
			console.log(div)
			down();
        }
    });

}
