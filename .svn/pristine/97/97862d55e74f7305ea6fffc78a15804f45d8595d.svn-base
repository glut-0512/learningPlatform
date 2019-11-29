var idnum = 0;
$(function() {
	var index;
	var keyword;
	
	//超出部分显示省略号 
	omit();
	
	// 加载课程类型下拉列表
	loadCourseTypeList("");
	// 加载课程详情下拉列表
//	loadCourseDetailsList();
	
	// 绑定课程事件
	courseHandler();
	// 请求课程数据事件
	requestCourse();
	echoImg();
	$.getScript('js/choice.js', function(data, textStatus, jqxhr) {
		if (jqxhr.status == '200')
			console.log('导入js/choice.js成功！');
		else
			console.log('导入js/choice.js失败！');
	});
	// 搜索事件
	courseSeach(index);
	
	//鼠标移动过去显示大图
    $(".bimg").hide();
    $(".box img").mousemove(function(e){
        $(".bimg").css({top:e.pageY-86,left:e.pageX}).html('<img src="' + this.src + '" />').show();
    }).mouseout( function(){
    $(".bimg").hide();
  })
	
	$('#close').unbind().click(function() {
		$('.zz').hide();
		$('.editInfo').hide();
	});
    
    //是否收费课程触发事件
    $("#orfree").change(function(data){
    	var value = $("#orfree").val();
    	if(value==1){
    		$("#coursePrice").attr("disabled",false);
    	}else{
    		$("#coursePrice").attr("disabled",true);
    		$("#coursePrice").val('0');
    	}
    });

    
    $('#screen-select').unbind().click(function(e){
		// 取消默认事件
		e.preventDefault();
		// 请求消息与用户数据事件
		requestCourse();
	});
    
   
});

/**
 * 加载课程类型列表
 */
function loadCourseTypeList(id) {
	$.ajax({
		url : 'courseType/getCourseType',
		async:false,
		success : function(data) {
			// 先清空select下拉列表
			$('#courseTypeSelect'+id).html('');
			if (data) {
				$a = $('<option selected value = "0">请选择教育类型</option>')
				$('#courseTypeSelect'+id).append($a).append(fillCourseTypeSelect(data));
				$b = $('<option selected value = "0">请选择课程类型</option>')
				$('#courseTypeSelectChildren'+id).append($b);
			}
		}
	});
	$("#courseTypeSelect"+id).change(function(){
		var typeId = $(this).find('option:selected').val();
		if(typeId!="0"){
			var selected=$(this).children('option:selected').val();
			$.ajax({
				url : 'courseType/getCourseType',
				success : function(data) {
					// 先清空select下拉列表
					$('#courseTypeSelectChildren'+id).html('');
					if (data) {
						$a = $('<option selected value = "0">请选择课程类型</option>')
						$('#courseTypeSelectChildren'+id).append($a).append(fillCourseTypeChildrenSelect(data,selected));
					}
				}
			});
		}else{
			$('#courseTypeSelectChildren'+id).html('');
			$('#courseTypeSelectChildren'+id).append($('<option selected value = "0">请选择课程类型</option>'));
		}
	});
}
function fillCourseTypeSelect(data) {
	var $optionArr = new Array();
	// 遍历数组
	$.each(data,function(index, courseType) {
		var $option;
		if(courseType.parentId==null||courseType.parentId==0){
			$option = $('<option value=' + courseType.id + '>' + courseType.typeName
					+ '</option>')
			$optionArr.push($option);
		}
	});
	return $optionArr;
}
function fillCourseTypeChildrenSelect(data,id){
	var $optionArr = new Array();
	// 遍历数组
	$.each(data,function(index, courseType) {
		var $option;
		if(courseType.parentId==id){
			$option = $('<option value=' + courseType.id + '>' + courseType.typeName
					+ '</option>')
			$optionArr.push($option);
		}
	});
	return $optionArr;
}


function courseHandler() {
	// 保存新闻事件
	saveCourseFunc();
	// 更新新闻事件
	delCourseFunc();
}

/**
 * 保存课程事件
 */
function saveCourseFunc() {
	$('#saveCourse').unbind().click(function() {
		/*var $formParams = getParams($('#courseForm').serializeArray());
		var upload = document.getElementById('uploadFile').value;
		var arr = JSON.stringify(upload).split("\\");
		var arr1 = arr[arr.length-1].split("\"");
		$formParams.coursePic = arr1[0];*/
		var arr = document.getElementsByName('courseTypeChildrenId');
		var typeIds="";
		for(var i=0;i<arr.length;i++){
			if(i==0){
				typeIds += arr[i].value;
				continue;
			}
			typeIds += ","+ arr[i].value;
		}
		var formData = new FormData();
		formData.append("id", $("#courseForm [name=id]").val());
		formData.append("courseNo", $("#courseForm [name=courseNo]").val());
		formData.append("courseName", $("#courseForm [name=courseName]").val());
		formData.append("courseTypeId", typeIds);
		formData.append("introduce", $("#courseForm [name=introduce]").val());
		formData.append("outline", $("#courseForm [name=outline]").val());
		formData.append("examine", $("#courseForm [name=examine]").val());
		formData.append("teachingMateril", $("#courseForm [name=teachingMateril]").val());
		formData.append("coursePeriod", $("#courseForm [name=coursePeriod]").val());
		formData.append("expendTime", $("#courseForm [name=expendTime]").val());
		formData.append("courseLanguage", $("#courseForm [name=courseLanguage]").val());
		formData.append("subtitleType", $("#courseForm [name=subtitleType]").val());
		formData.append("file", $("#uploadFile")[0].files[0]);
		formData.append("studyStartTime", $("#courseForm [name=studyStartTime]").val());
		formData.append("studyEndTime", $("#courseForm [name=studyEndTime]").val());
		formData.append("examEndTime", $("#courseForm [name=examEndTime]").val());
		formData.append("orfree", $("#courseForm [name=orfree]").val());
		formData.append("coursePrice", $("#courseForm [name=coursePrice]").val());
		formData.append("remark", $("#courseForm [name=remark]").val());

		var ss = $("#courseForm [name=studyStartTime]").val();
		if ($('#courseForm .hide input[name=id]').val()) {
			if(confirm("确认更新吗")){
				updateCourse(formData);
			} 
		} else {
			if(confirm("确认增加吗")){
				saveCourse(formData);
			}
		}
	});
}

/**
 * 提交数据，并请求数据填充到页面
 * 
 * @param formParams
 */
function saveCourse(formParams) {
	$.ajax({
		url : "course/saveCourse",
		method : 'post',
		data : formParams,
		dataType : 'json',
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		success : function(data) {
			if (data.status!="repeat") {
				alert('保存成功');
				requestCourse();
			}else{
				alert('该课程编号已存在，请重新输入');
			}
		},
		error:function(data){
			if(((data.responseText.split(':'))[1].split('}'))[0]=="repeat"){
				alert("该课程编号已存在，请重新输入");
			}else{
				alert('保存失败，请填写完整必填信息！');
			}
		}
	});
}

/**
 * 请求数据事件
 */
function requestCourse(index,keyword) {
	if(index == null || index == 0){
		index = 1;
	}
	if(keyword == null){
		keyword = null;
	}
	var courseNo = $('#screen-courseNo').val();
	var courseName = $('#screen-courseName').val();
	$.ajax({
		url : 'course/'+ index +'/getCoursePageModelBackstage',
		method : 'post',
		data : {
			keyword : keyword,
			"courseNo":courseNo,
			"courseName":courseName
		},
		dataType : 'json',
		success : function(pageModel) {
			fillCourseList(pageModel);
		}
	});
}

function requestCourseByPage(pageIndex) {
	$.get('course/' + pageIndex + '/getCoursePageModel',function(pageModel){
		fillCourseList(pageModel);
	});
}

function updateCourse(formParams) {
	var $courseId = $('#courseForm .hide input[name=id]').val();
	$.ajax({
		url : "course/updateCourse",
		method : 'post',
		data : formParams,
		processData : false,
		contentType : false,
		success : function(data) {
			if (data) {
				alert('保存成功');
				requestCourse();
			}
		},
		error:function(){
			alert('保存失败，请填写完整必填信息！');
		}
	});
}

function delCourseFunc() {
	$('#delCourse').unbind().click(function () {
		if(isChoosedAny()){
			if (confirm('确认删除吗?')){
				delCourseHandler();
			}
		} else {
			alert("请选择要删除的课程！！！");
		}
	});
}

function delCourseHandler() {
	var $idArr = [];
	$.each($('.fill tbody input[type=checkbox]'),function(index,checkbox){
		if($(checkbox).is(':checked')){
			$idArr.push($(checkbox).parent().siblings().eq(11).text());
		}
	});
	//console.log($idArr);
	$.ajax({
		url : "course/deleteCourse",
		method : 'post',
		data : {
			ids : $idArr
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				// 重新加载新闻列表
				alert('删除成功！');
				requestCourse();
			}
		},
		error : function(){
			alert('服务器异常，请联系管理员');
		}
	});
}

/**
 * 填充新闻列表事件
 * 
 * @param courseData
 */
function fillCourseList(pageModel) {
	var $courseData = pageModel.list;
	// 1.清空列表
	$('.fill tbody').html('');
	// 2.填充数据
	$.each($courseData,function(index,course){
		var orfree;
		if(course.orfree == 0){
			orfree = '开放课程';
		}if(course.orfree == 1){
			orfree = '收费课程';
		}
		$('.fill tbody').append(fillCourseData(course,orfree));
	});
	// 清空分页栏
	$('#main #pagination').html('');
	// 加载分页栏
	fillPage(pageModel);
	// 分页栏点击事件
	onclickPage(pageModel);
	// 3.清空列表事件
	clearCourseFormFunc();
	// 4.编辑新闻事件
	editCourseForm();
	// 5.复选框事件
	// 5-1.标题复选框事件
	theadChecboxFunc();
	// 5-2.列表复选框事件
	tbodyChecboxFunc();
	//超出部分显示省略号 
	omit();
	//课程详情
	//courseDetail('0');
}

function courseDetail(id){
	$.ajax({
		url:"findCourseDetailById",
		data:{id:id},
		datatype:"json",
		success: function(data){
			document.getElementById('examine').innerHTML=data.examine;
			document.getElementById('teachingMateril').innerHTML=data.teachingMateril;
			document.getElementById('coursePeriod').innerHTML=data.coursePeriod;
			document.getElementById('expendTime').innerHTML=data.expendTime;
			document.getElementById('courseLanguage').innerHTML=data.courseLanguage;
			document.getElementById('subtitleType').innerHTML=data.subtitleType;
			document.getElementById('studyStartTime').innerHTML=dateFormat(new Date(data.studyStartTime), 'yyyy-MM-dd');;
			document.getElementById('studyEndTime').innerHTML=dateFormat(new Date(data.studyEndTime), 'yyyy-MM-dd');
			document.getElementById('examStartTime').innerHTML=dateFormat(new Date(data.examStartTime), 'yyyy-MM-dd');
			document.getElementById('examEndTime').innerHTML=dateFormat(new Date(data.examEndTime), 'yyyy-MM-dd');
			document.getElementById('coursePic').src=data.coursePic;
		}
	});
}

function editCourseForm() {
	$('.edit').unbind().click(function (e) {
		// 取消默认事件
		e.preventDefault();
		// 把改行数据填充到form表单中
		loadTrCourseForm($(this));
	});
}

/**
 * 初始化课程类型
 */
function initCourseType(){
	$("#courseTypeSelect").val('0');
	$("#courseTypeSelectChildren").html('');
	$("#courseTypeSelectChildren").append($('<option selected value = "0">请选择课程类型</option>'));
	$("#courseTypeSelectChildren").val('0');
	var obj = document.getElementsByName("courseTypeId");
	var len = obj.length;
	for(var i=0;i<len;i++){
		if(i!=0){
			var tr = obj[i].parentNode.parentNode;
			tr.remove();
			$("#courseTypetd").attr("rowspan",parseInt($("#courseTypetd").attr("rowspan"))-1);
		}
	}
}

/**
 * 填充表单事件
 * @param that
 */
function loadTrCourseForm(that) {
	initCourseType()
	var $tdArr = that.parent().siblings();
	var id = $tdArr.eq(11).text();
	$.ajax({
		url:"findCourseById",
		data:{id:id},
		dataType:"json",
		success: function(data){
			debugger
			var studyStartTime = dateFormat(new Date(data.studyStartTime), 'yyyy-MM-dd');
			var studyEndTime = dateFormat(new Date(data.studyEndTime), 'yyyy-MM-dd');
			var examStartTime = dateFormat(new Date(data.examStartTime), 'yyyy-MM-dd');
			var examEndTime = dateFormat(new Date(data.examEndTime), 'yyyy-MM-dd');
			$('#courseForm .hide input[name = id]').val(data.id);
			$('#courseForm input[name = courseNo]').val(data.courseNo);
			$('#courseForm input[name = courseName]').val(data.courseName);
			$('#courseForm textarea[name = introduce]').val(data.introduce);
			$('#courseForm textarea[name = outline]').val(data.outline);
			$('#courseForm textarea[name = examine]').val(data.examine);
			$('#courseForm textarea[name = teachingMateril]').val(data.teachingMateril);
			$('#courseForm input[name = coursePeriod]').val(data.coursePeriod);
			$('#courseForm input[name = expendTime]').val(data.expendTime);
			$('#courseForm select[name = courseLanguage]').val(data.courseLanguage);
			$('#courseForm input[name = subtitleType]').val(data.subtitleType);
			$('#img').attr('src',data.coursePic);		//图片
			$('#courseForm input[name = studyStartTime]').val(studyStartTime);
			$('#courseForm input[name = studyEndTime]').val(studyEndTime);
			$('#courseForm input[name = examStartTime]').val(examStartTime);
			$('#courseForm input[name = examEndTime]').val(examEndTime);
			$("#orfree").val(data.orfree);
			$("#coursePrice").val(data.coursePrice);
			/******课程类型******/
			var courseTypeArr = data.courseType;
			var len = courseTypeArr.length;
			var detail = $("#detail");
			var courseId = "";
			for(var k=0;k<len;k++){
				if(k>0){
					detail.before("<tr><td><select id='courseTypeSelect"+k+"' name='courseTypeId' style='width: 230px'></select></td>" +
							"<td><select id='courseTypeSelectChildren"+k+"' name='courseTypeChildrenId' style='width: 230px'></select></td>" +
							"<td><input type='button' value='删除' onclick='delType(this)'></td></tr>");
					$("#courseTypetd").attr("rowspan",parseInt($("#courseTypetd").attr("rowspan"))+1);
					courseId = k;
				}
				//判断教育类型
				$.ajax({
					url : 'findCourseTypeById',
					data:{id:courseTypeArr[k].parentId},
					datatype:"json",
					async:false,
					success : function(data1) {
						if(courseId!=""){
							loadCourseTypeList(courseId);
						}
						$.ajax({
							url : 'courseType/getCourseType',
							async:false,
							success : function(data2) {
								// 先清空select下拉列表
								$('#courseTypeSelectChildren'+courseId).html('');
								if (data) {
									$a = $('<option selected value = "0">请选择课程类型</option>')
									$('#courseTypeSelectChildren'+courseId).append($a).append(fillCourseTypeChildrenSelect(data2,data1.id));
									$("#courseTypeSelectChildren"+courseId).val(courseTypeArr[k].id);
									$("#courseTypeSelect"+courseId).val(data1.id);
								}
							}
						});
					}
				});
			}
			/*******************/
			//$("#courseDetailsSelect").val(data.courseDetails.id);
			$('#courseForm input[name = remark]').val(data.remark==null?"":data.remark);
		}
	});
}

/**
 * 列表填充
 * 
 * @param course
 * @returns
 */
function fillCourseData(course,orfree) {
	var $tr = $('<tr></tr>');
	var $td1 = $('<td style="text-align: center;"><input type="checkbox" /></td>');
	var $td2 = $('<td>' + course.courseNo + '</td>');
	var $td3 = $('<td>' + course.courseName + '</td>');
	var $td4 = $('<td class="courseOmit">' + course.introduce + '</td>');
	var $td5 = $('<td class="courseOmit">' + course.outline + '</td>');
	var ftypeName = "";
	var stypeName = "";
	var typeIdArr = course.courseType;
	//遍历课程类型数组
	for(var i=0;i<typeIdArr.length;i++){
		if(stypeName==""){//课程类型字符拼接
			stypeName += typeIdArr[i].typeName;
		}else{
			stypeName += "、"+typeIdArr[i].typeName;
		}
		var typeId = "";
		if(typeIdArr[i].parentId==0||typeIdArr[i].parentId==null){//判断获取教育类型数据
			typeId += typeIdArr[i].id;
		}else{
			typeId += typeIdArr[i].parentId;
		}
		var result = "";
		$.ajax({
			url : 'findCourseTypeById',
			data:{id:typeId},
			method : 'get',
			dataType:"json",
			async:false, 
			success : function(data) {
				if(ftypeName==""){//拼接教育类型
					ftypeName += data.typeName;
				}else{
					if(ftypeName.indexOf(data.typeName)==-1){
						ftypeName += "、"+data.typeName;
					}
				}
			}
		});
	}
	var $td6 = $('<td>' + ftypeName + '</td>');
	var $td7 = "";
	if(stypeName==""){
		$td7 = $('<td>-</td>');
	}else{
		$td7 = $('<td>' + stypeName + '</td>');
	}
	var $td8 = $('<td align="center"><input type="button" id="aaa" data-toggle="modal" data-target="#myModal" value="点击查看" onclick="courseDetail('+course.id+')"></td>') ;
	var $td9 = $('<td>' + orfree + '</td>');
	var $td10 = $("<td align='center'>" + course.coursePrice + '</td>');
	var $td11 = $('<td>' + course.remark + '</td>');
	var $td12 = $('<td><a href="#" class="edit">编辑</a></td>');
	var $td13 = $('<td class="hide" name = "id">' + course.id + '</td>');
	return $tr.append($td1).append($td2).append($td3).append($td4)
	.append($td5).append($td6).append($td7).append($td8).append($td9).append($td10).append($td11).append($td12).append($td13);
}

function typeData(id){
	$.ajax({
		url : 'findCourseTypeById',
		data:{id:id},
		datatype:"json",
		async: true,
		success : function(data) {
			return data;
		}
	});
}


function clearCourseFormFunc() {
	$('#courseForm input[name = courseNo]').val('');
	$('#courseForm input[name = courseName]').val('');
	$('#courseForm textarea[name = introduce]').val('');
	$('#courseForm textarea[name = outline]').val('');
	$('#courseForm textarea[name = examine]').val('');
	$('#courseForm textarea[name = teachingMateril]').val('');
	$('#courseForm input[name = studyStartTime]').val('');
	$('#courseForm input[name = studyEndTime]').val('');
	$('#courseForm input[name = examStartTime]').val('');
	$('#courseForm input[name = examEndTime]').val('');
	$('#courseForm input[name = remark]').val('');
	$('#courseForm input[name = coursePeriod]').val('');
	$('#courseForm select[name = courseLanguage]').val('');
	$('#courseForm input[name = subtitleType]').val('');
	$('#courseForm input[name = expendTime]').val('');
	$('#courseForm input[name = file]').val('');
	$('#img').attr('src',"");
	$("#courseDetailsSelect").val('0');
	$('#courseForm select[name = orfree]').val('0');
	$('#courseForm input[name = coursePrice]').val('0');
	$("#coursePrice").attr("disabled",true);
	$('#courseForm .hide input[name = id]').val('');
	initCourseType();
}
/**
 * 关键字查询事件
 * @param index
 */
function courseSeach(index) {
	$('.btn-primary').unbind().click(function () {
		keyword = $('input[name=keyword]').val();
		//console.log(keyword);
		requestCourse(index,keyword);
	});
}

function pageSeach(index) {
	keyword = $('input[name=keyword]').val();
	if(keyword == null){
		keyword = null;
	}
	requestCourse(index,keyword);
}

function onclickPage(pageModel) {
	// 当前页
	var $pageIndex = pageModel.pageIndex;
	// 总页数
	var $totalSize = pageModel.totalSize;
	// 上页
	var $preIndex = $pageIndex - 1 > 0 ? ($pageIndex - 1) : 1;
	// 下页
	var $nextIndex = $pageIndex + 1 < $totalSize ? ($pageIndex + 1)
			: $totalSize;
	$('#pagination ul li:eq(0) a').attr("onClick","pageSeach(1)");
	$('#pagination ul li:eq(1) a').attr("onClick","pageSeach(" + $preIndex+")");
	$('#pagination ul li:eq(3) a').attr("onClick","pageSeach(" + $nextIndex+")");
	$('#pagination ul li:eq(4) a').attr("onClick","pageSeach(" + $totalSize+")");
}

function omit(){
	//超出部分显示省略号 
	   $(".courseOmit").each(function() {
			var maxwidth = 5;
			if($(this).html().length > maxwidth) {
				$(this).html($(this).text().substring(0, maxwidth));
				$(this).text($(this).html() +'...');
			}
		});
}

/**
 * 选择图片回显
 */
function echoImg() {
	$('#uploadFile').unbind().change(function(e) {
		// 创建FileReader对象
		var freader = new FileReader();
		// 设置回显图片文件
		freader.readAsDataURL(e.target.files.item(0));
		// 回显重载
		freader.onload = function(e) {
			$('.box img').attr('src', e.target.result);
		}
	});
}

function addType(obj){
	var detail = $("#detail");
	detail.before("<tr><td><select id='courseTypeSelect"+idnum+"' name='courseTypeId' style='width: 230px'></select></td>" +
			"<td><select id='courseTypeSelectChildren"+idnum+"' name='courseTypeChildrenId' style='width: 230px'></select></td>" +
			"<td><input type='button' value='删除' onclick='delType(this)'></td></tr>");
	$("#courseTypetd").attr("rowspan",parseInt($("#courseTypetd").attr("rowspan"))+1);
	loadCourseTypeList(idnum);
	idnum++;
}

function delType(obj){
	 $(obj).parent().parent().remove();
	 $("#courseTypetd").attr("rowspan",parseInt($("#courseTypetd").attr("rowspan"))-1);
}