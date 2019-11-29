$(function (){
    //获取ppt文件列表
    getJPGFileList();

})

//获取PPT文件列表
function getJPGFileList(index,fileName){
    if(index == null || index == 0){
        index = 1;
    }

    $.ajax({
        url: 'fileManagers/' + index + '/getFilePageModel',
        method : 'post',
        data : {
            "fileName" : fileName,
            "fileType" : ".jpg"
        },
        dataType : 'json',
        success : function(data) {
            //填充文件列表
            fillFileList(data);
        }
    })
}

//通过文件名查询文件信息
function getFileListBYFileName(){
    var fileName = $('#screen-fileName').val();
    getPPTFileList(null,fileName);
}


//填充文件列表
function fillFileList(pageModel){
    console.log(pageModel);
    var fileData = pageModel.list;
    console.log(fileData);
    //填充数据
    media = document.getElementsByClassName("media")[0];
    $.each(fileData,function(index,fileManager){
        $(media).append(fillFileData(fileManager));
    });
}

function fillFileData(fileManager){
    //文件信息
    var $ul = $('<ul class="list-unstyled"></ul>');
    var $li = $('<li></li>');
    var $divImg = $('<div class="media_img"><img src="img/icon_jpg.png" /></div>');
    var $idvText = $('<div class="media_text"><h1>'+ fileManager.filename +'</h1><h2>面相对象：学生<span>资源简介：'+ fileManager.filename +'</span></h2><h3>关键词：</h3><h4>文件格式：jpg</h4><h5>大小：未知</h5></div>');
    //下载按钮
    var $divDow = $('<div class="download"><a href="#" onclick="downloadFileByForm(this.name)" name="'+ fileManager.filename +'"><div class="download_img"><img src="img/download.png" /></div></a><p>(已下载0次)</p></div>');
    var $divClearfix = $('<div class="clearfix"></div>');
    //拼接
    return $ul.append($li.append($divImg).append($idvText).append($divDow).append($divClearfix));
}




