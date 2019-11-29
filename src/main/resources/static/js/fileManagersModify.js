$(function() {

    //请求列表信息
    requestFileList();


    uploadFile();

});


function uploadFile(){
    $('#submitFile').unbind().click(
            function(e) {
                e.preventDefault(); //取消默认动作
                console.log("11");
                var formData = new FormData();
                var file = $("#file")[0].files[0];
                if (!file) {
                    alert('请选择文件！');
                    return;
                }
                // 进度条
                //uploadProgress(); 没有实现
                // 模态框关闭事件
                formData.append("file", file);
                console.log(file);
                //提交
                $.ajax({
                    url : "fileManage/uploadFile",
                    method : 'post',
                    data : formData ,
                    dataType : 'json',
                    // 告诉jQuery不要去处理发送的数据
                    processData : false,
                    // 告诉jQuery不要去设置Content-Type请求头
                    contentType : false,
                    success : function(data) {
                        console.log(data);
                    },
                    error : function() {
                        alert('服务器异常，上传失败');
                    }

                })
            });
}


function requestFileList(index,fileName){
    if(index == null || index == 0){
        index = 1;
    }
    var filiType = null;
    $.ajax({
        url: 'fileManagers/' + index + '/getFilePageModel',
        method : 'post',
        data : {
            "fileName" : fileName,
            "fileType" :filiType
        },
        dataType : 'json',
        success : function(data) {
            //填充文件列表
            fillFileList(data);
        }
    })
}

function fillFileList(pageModel) {
    var $fileData = pageModel.list;
    // 1.清空列表
    $('.fill tbody').html('');
    // 2.填充数据
    $.each($fileData,function(index,file){
        $('.fill tbody').append(fillFileData(file));
    });
    // 清空分页栏
    $('#main #pagination').html('');
    // 加载分页栏
    fillPage(pageModel);
    // 分页栏点击事件
    onclickPage(pageModel);
    // 3.清空列表事件
  //  clearUserFormFunc();
    // 4.编辑用户事件
   // editUserForm();
    // 5.复选框事件
    // 5-1.标题复选框事件
  //  theadChecboxFunc();
    // 5-2.列表复选框事件
 //   tbodyChecboxFunc();
    //详细介绍显示省略
    omit1();

}

/**
 * 列表填充
 *
 * @param User
 * @returns
 */
function fillFileData(file) {
    var $tr = $('<tr></tr>');
    var $td1 = $('<td style="text-align: center;"><input type="checkbox" /></td>');
    var $td5 = $('<td>' + file.filename + '</td>');
    var $td6 = $('<td>' +"说明"+ '</td>');
    var $td7 = $('<td>' + file.remarks + '</td>');
    var $td11 = $('<td><a href="#">编辑</a></td>');
    var $td12 = $('<td class="hide" name = "id">' + file.id + '</td>');
    return $tr.append($td1).append($td5).append($td6).append($td7).append($td11).append($td12);
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

function omit1(){
    //超出部分显示省略号
    $(".userOmit").each(function() {
        var maxwidth = 5;
        if($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() +'...');
        }
    });
}