$(function() {	
	$('input[name="check-box"]').wrap('<div class="check-box"><i></i></div>');
	$.fn.toggleCheckbox = function () {
	    this.attr('checked', !this.attr('checked'));
	}
	$('.check-box').on('click', function () {
	    $(this).find(':checkbox').toggleCheckbox();
	    $(this).toggleClass('checkedBox');
	});


	//获取文件列表
	getFileList();
});


//获取PPT文件列表
function getFileList(index){
	if(index == null || index == 0){
		index = 1;
	}

	$.ajax({
		url: 'fileManagers/' + index + '/getFilePageModel',
		method : 'post',
		data : {
			"fileName" : "",
			"fileType" : ""
		},
		dataType : 'json',
		success : function(data) {
			console.log(data.list);
			//填充文件列表
			fillFileList(data);
		}
	})
}


function fillFileList(pageModel){
	var fileData = pageModel.list;
	$.each(fileData,function(index,fileManager){
		if(fileManager.remarks == ".ppt"){
			$(document.getElementsByClassName("list-unstyled")[8]).append($('<dd><a href="ppt"><img src="img/icon_ppt.png" /><p>'+fileManager.filename+'</p></a></dd>'));
		}
		if(fileManager.remarks == ".jpg"){
			$(document.getElementsByClassName("list-unstyled")[9]).append($('<dd><a href="jpg"><img src="img/icon_jpg.png" /><p>'+fileManager.filename+'</p></a></dd>'));
		}
		if(fileManager.remarks == ".swf"){
			$(document.getElementsByClassName("list-unstyled")[10]).append($('<dd><a href="swf"><img src="img/icon_swf.png" /><p>'+fileManager.filename+'</p></a></dd>'));
		}
		if(fileManager.remarks == ".pdf"){
			$(document.getElementsByClassName("list-unstyled")[11]).append($('<dd><a href="pdf"><img src="img/icon_pdf.png" /><p>'+fileManager.filename+'</p></a></dd>'));
		}
	});
	$(document.getElementsByClassName("list-unstyled")[8]).append($('<div class="clearfix"></div>'));
	$(document.getElementsByClassName("list-unstyled")[9]).append($('<div class="clearfix"></div>'));
	$(document.getElementsByClassName("list-unstyled")[10]).append($('<div class="clearfix"></div>'));
	$(document.getElementsByClassName("list-unstyled")[11]).append($('<div class="clearfix"></div>'));
}
