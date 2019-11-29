package com.glut.learningplatform.service.impl;

import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.service.FileManagerService;
import com.glut.learningplatform.service.UploadHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service("UploadHandleService")
public class UploadHandleServiceImpl implements UploadHandleService {

    @Autowired
    FileManagerService fileManagerService;

    @Override
    public String uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        //String savePath = request.getServletContext().getRealPath("/resources/upload");  //部署到服务器的时候用
        String savePath = System.getProperty("user.dir")+"\\src\\main\\resources\\upload";

       // String savePath = "D:\\learningPlatform\\src\\main\\resources\\upload";
        // System.out.println("保存地址:" + System.getProperty("user.dir"));
        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        System.out.println("multipartRequest：" + multipartRequest);
        System.out.println("fileMap：" + fileMap);
        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();

            // 获得原始文件名
            String fileName = mf.getOriginalFilename();
            // 截取文件类型; 这里可以根据文件类型进行判断
            String fileType = fileName.substring(fileName.lastIndexOf('.'));
            // 截取上传的文件名称
            String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("文件后缀：————》》》》》" + fileType);
            System.out.println("上传来的文件名称------->>>>>>>>>" + newFileName);
//                String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
//            //String filepath = request.getServletContext().getRealPath("/") + "files\\";//获取项目路径到webapp
            File file = new File(savePath);
            if (!file.exists()) {//目录不存在就创建
                file.mkdirs();
            }
            mf.transferTo(new File(file + "\\" + newFileName  + fileType));//保存文件

            //保存到数据库
            FileManagers fileManagers = new FileManagers();
            fileManagers.setFilename(fileName);
            fileManagers.setUrl(file.toString());
            fileManagers.setRemarks(fileType);
            fileManagerService.saveOrUpdate(fileManagers);

        }
        //获取文件名
//        String name = "";
//        if (multipartFile != null) {
//            long size = multipartFile.getSize();
//            if (size > 5242880) {//文件设置大小，我这里设置5M。
//                return "{\"message \":文件大小超过5M}";
//            }
//            name = multipartFile.getOriginalFilename();//直接返回文件的名字
//            String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
//            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
//            //String filepath = request.getServletContext().getRealPath("/") + "files\\";//获取项目路径到webapp
//            System.out.println("文件类型：" + subffix);
//            System.out.println("重命名：" + fileName);
//           // File file = new File(filepath);
//           // if (!file.exists()) {//目录不存在就创建
//           //     file.mkdirs();
//           // }
//           // multipartFile.transferTo(new File(file + "\\" + fileName + "." + subffix));//保存文件
//            //realpath=file+"\\"+fileName+"."+subffix;返回地址
//        }
        return "{\"message \":true}";
    }
}
