$(function() {
	$.getScript('js/choice.js', function(data, textStatus, jqxhr) {
	});
	var index;
	var keyword;
	// 绑定课程类型事件
	courseTypeHandler();
	// 请求课程类型数据事件
	requestCourseType(index,keyword);
	//loadCourseTypeList();


	// 搜索事件
	//courseTypeSeach(index);
});

function courseTypeHandler() {
	// 保存或更新课程类型事件
	saveCourseTypeFunc();
	// 删除课程类型事件
	delCourseTypeFunc();
}

/**
 * 保存课程类型事件
 */
function saveCourseTypeFunc() {
	$('#saveCourseType').unbind().click(function() {
		/*		var $formParams = getParams($('#newsForm').serializeArray());*/
		//创建表单数据对象
		var formData = new FormData();
		formData.append("id", $("#courseTypeForm [name=id]").val());
		formData.append("typeName", $("#courseTypeForm [name=typeName]").val());
		formData.append("parentId", $("#courseTypeForm [name=courseTypeId]").val());
		//formData.append("parentId", $("#courseTypeForm [name=parentId]").val());
		if ($('#courseTypeForm .hide input[name=id]').val()) {
			if(confirm("确认更新吗")){
				updateCourseType(formData);
			}
		} else {
			if(confirm("确认增加吗")){
				saveCourseType(formData);
			}
		}
	});
}

/**
 * 加载课程类型列表
 */
function loadCourseTypeList() {
	$.ajax({
		url : 'getCourseType',
		method : 'get',
		success : function(data) {
			// 先清空select下拉列表
			$('#courseTypeSelect').html('');
			if (data) {
				$a = $('<option selected value = "0">请选择类型所属</option>');
				$('#courseTypeSelect').append($a).append(fillCourseTypeSelect(data));
			}
		}
	});
}

function fillCourseTypeSelect(data) {
	var $optionArr = new Array();
	// 遍历数组
	$.each(data,function(index, courseType) {
		var $option;
		if(courseType.property == 0){
			$option = $('<option value=' + courseType.id + '>' + courseType.typeName
				+ '</option>');
			$optionArr.push($option);
		}
	});
	return $optionArr;
}

/**
 * 提交数据，并请求数据填充到页面
 *
 * @param formParams
 */
function saveCourseType(formData) {
	$.ajax({
		url : "courseType/saveCourseType",
		method : 'post',
		data : formData,
		dataType : 'json',
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		success : function(data) {
			if (data.status) {
				alert('保存成功');
				requestCourseType();
			}
		},
		error:function(){
			alert('保存失败，请填写完整信息！');
		}
	});
}

/**
 * 请求课程类型数据事件
 */
function requestCourseType(index,keyword) {
	loadCourseTypeList();
	if(index == null || index == 0){
		index = 1;
	}
	if(keyword == null){
		keyword = null;
	}
	$.ajax({
		url : 'courseType/'+ index +'/getCourseTypePageModel',
		method : 'post',
		data : {
			keyword : keyword
		},
		dataType : 'json',
		success : function(data) {
			fillCourseTypeList(data);
		}
	});
}

function updateCourseType(formData) {
	var $id = $('#courseTypeForm .hide input[name=id]').val();
	$.ajax({
		url : "courseType/updateCourseType",
		method : 'post',
		data : formData,
		// 告诉jQuery不要去处理发送的数据,用来序列化data
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		success : function(data) {
			if (data) {
				alert('更新成功');
				requestCourseType();
			}
		},
		error:function(){
			alert('服务器异常，保存失败');
		}
	});
}

function delCourseTypeFunc() {
	$('#delCourseType').unbind().click(function () {
		if(isChoosedAny()){
			if (confirm('确认删除吗?若是一级类型，与它关联的子类型也会删除')){
				delCourseTypeHandler();
			}
		} else {
			alert("请选择要删除的课程类型！！！");
		}
	});
}

function delCourseTypeHandler() {
	var $idArr = [];

	$.each($('.fill tbody input[type=checkbox]'),function(index,checkbox){
		if($(checkbox).is(':checked')){
			$idArr.push($(checkbox).parent().siblings().eq(4).text());
		}
	});
	//console.log($idArr);
	$.ajax({
		url : "courseType/deleteCourseType",
		method : 'post',
		data : {
			ids : $idArr
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				// 重新加载课程类型列表
				requestCourseType();
				alert('删除成功');
			}
		},
		error : function(){
			alert('服务器异常，请联系管理员');
		}
	});
}

/**
 * 填充课程类型列表事件
 *
 * @param courseTypeData
 */
function fillCourseTypeList(pageModel) {
	var $courseTypeDate = pageModel.list;
	// 1.清空列表
	$('.fill tbody').html('');
	// 2.填充数据
	$.each($courseTypeDate,function(index,courseType){
		$('.fill tbody').append(fillCourseTypeData(courseType));
	});
	// 清空分页栏
	$('#main #pagination').html('');
	// 加载分页栏
	fillPage(pageModel);
	// 分页栏点击事件
	onclickPage(pageModel);
	// 3.清空列表事件
	clearCourseTypeFormFunc();
	// 4.编辑课程类型事件
	editCourseTypeForm();
	// 5.复选框事件
	// 5-1.标题复选框事件
	theadChecboxFunc();
	// 5-2.列表复选框事件
	tbodyChecboxFunc();
}

/**
 * 列表填充
 *
 * @param courseType
 * @returns
 */
function fillCourseTypeData(courseType) {
	var $tr = $('<tr></tr>');
	var $td1 = $('<td style="text-align: center;"><input type="checkbox" /></td>');
	var $td2 = $('<td>' + courseType.typeName + '</td>');
	var $td3 = "";
	var $td4 = "";
	if(courseType.property==0||courseType.property==null){
		$td3 = $('<td>一级</td>');
		$td4 = $('<td>-</td>');
	}else{
		$td3 = $('<td>二级</td>');
		$.ajax({
			url : 'findCourseTypeById',
			data:{id:courseType.parentId},
			datatype:"json",
			async: false,
			success : function(data) {
				if (data) {
					$td4 = $('<td>'+data.typeName+'</td>');
				}
			}
		});
	}
	var $td5 = $('<td><a href="#" class="editCourseTypeForm">编辑</a></td>');
	var $td6 = $('<td class="hide" name = "id">' + courseType.id + '</td>');
	return $tr.append($td1).append($td2).append($td3).append($td4).append($td5).append($td6);
}


/**
 * 清空列表事件
 */
function clearCourseTypeFormFunc() {
	$('#courseTypeForm .hide input[name = id]').val('');
	$('#courseTypeForm input[name = typeName]').val('');
}

/**
 * 编辑课程类型事件
 */
function editCourseTypeForm() {
	$('.fill tbody a').unbind().click(function (e) {
		// 取消默认事件
		e.preventDefault();
		// 把改行数据填充到form表单中
		loadTrCourseTypeForm($(this));
	});
}
function loadTrCourseTypeForm(that) {
	var $tdArr = that.parent().siblings();
	var id = $tdArr.eq(4).text();
	$.ajax({
		url:"findCourseTypeById",
		data:{id:id},
		datatype:"json",
		success: function(data){
			$('#courseTypeForm input[name = typeName]').val(data.typeName);
			$('#courseTypeForm select[name = courseTypeId]').val(data.parentId);
			$('#courseTypeForm input[name = id]').val(data.id);
			if(data.parentId==null||data.parentId==""){
				$('#courseTypeForm input[name = property]').val("0");
				$('#courseTypeForm select[name = courseTypeId]').val("0");
			}else{
				$('#courseTypeForm input[name = property]').val("1");
			}
		}
	});
}

/**
 * 关键字查询事件
 * @param index
 */
function courseTypeSeach(index) {
	$('.btn-primary').unbind().click(function () {
		keyword = $('input[name=keyword]').val();
		//console.log(keyword);
		requestCourseType(index,keyword);
	});
}

function pageSeach(index) {
	keyword = $('input[name=keyword]').val();
	if(keyword == null){
		keyword = null;
	}
	requestCourseType(index,keyword);
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
