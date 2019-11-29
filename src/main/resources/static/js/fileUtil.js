$(function ( ) {
    //downloadFileByForm(this);
})

function downloadFileByForm(fileName){
//验证用户是否登录
    var status = "";
    $.get('getUserSession', function(data) {
        status = data;
        if (status==""){
            alert("请先登录！")
            window.location.href = "login";
            return;
        }else{
            openDownloadDialog(fileName);
        }
    });
}

function openDownloadDialog(fileName) {
    var url = 'fileManage/download/?filename=' + fileName;

    var xhr = new XMLHttpRequest();

    xhr.open('POST', url, true);        // 也可以使用POST方式，根据接口

    xhr.responseType = "blob";    // 返回类型blob

    // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑

    xhr.onload = function () {

        // 请求完成

        if (this.status === 200) {

            // 返回200

            var blob = this.response;

            var reader = new FileReader();

            reader.readAsDataURL(blob);    // 转换为base64，可以直接放入a表情href

            reader.onload = function (e) {

                // 转换完成，创建一个a标签用于下载

                var a = document.createElement('a');

                a.download = fileName;

                a.href = e.target.result;

                $("body").append(a);    // 修复firefox中无法触发click

                a.click();

                $(a).remove();

            }

        }

    };

    // 发送ajax请求

    xhr.send()
}

// function openDownloadDialog() {
//     // for ie 10 and later url, saveName
//     url = "C:\\Users\\Administrator\\Pictures\\1.png";
//     saveName = "1.png";
//     if (window.navigator.msSaveBlob) {
//         try {
//             window.navigator.msSaveBlob(url, downloadFileName);
//         }
//         catch (e) {
//             console.log(e);
//         }
//     }
//     // 谷歌浏览器 创建a标签 添加download属性下载
//     else {
//         if (typeof url == 'object' && url instanceof Blob) {
//             url = URL.createObjectURL(url); // 创建blob地址
//         }
//         var aLink = document.createElement('a');
//         aLink.href = url;
//         aLink.download = saveName || ''; // HTML5新增的属性，指定保存文件名，可以不要后缀，注意，file:///模式下不会生效
//         var event;
//         if (window.MouseEvent) {
//             event = new MouseEvent('click');
//         }
//         else {
//             event = document.createEvent('MouseEvents');
//             event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
//         }
//         aLink.dispatchEvent(event);
//     }
// }