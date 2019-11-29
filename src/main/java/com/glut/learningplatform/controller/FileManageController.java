package com.glut.learningplatform.controller;

import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.service.FileManagerService;
import com.glut.learningplatform.service.UploadHandleService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileManageController {

    @Autowired
     private UploadHandleService uploadHandleService;

    @Autowired
    FileManagerService fileManagerService;

    @RequestMapping("fileManage/uploadFile")
    public @ResponseBody String uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
        return uploadHandleService.uploadFile(request);
    }

    @RequestMapping("fileManage/download")
    public @ResponseBody Object download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        System.out.println("文件名: " + fileName);
        //根据文件名查找文件路径   后期可能需要改成根据文件id
        //String filePath = fileManagerService.selectFilePathByFileName(fileName);
        //System.out.println("文件路径: " + filePath);
        // File file = new File(filePath + File.separator + fileName);
        //File file = new File("D:\\learningPlatform\\src\\main\\resources\\upload\\" + fileName);
        String savePath = System.getProperty("user.dir")+"\\src\\main\\resources\\upload";
        File file = new File(savePath + "\\" + fileName);

        System.out.println("文件: " + file);
        long fileLength = file.length();
        //浏览器下载中文名文件兼容性处理
        String filaname = new String(fileName.getBytes("gb2312"), "ISO8859-1");
        // 清空response
        response.reset();
        //octet-stream 自动匹配文件类型
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filaname + "\"");
        response.setHeader("Content-Length", String.valueOf(fileLength));
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        while (is.read(b) != -1) {
            os.write(b);
        }
        is.close();
        os.flush();
        os.close();
        return "{\\\"message \\\":true}";
    }


//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition","attachment;filename=" + fileName);
//        response.setContentLength((int) file.length());

//        if(!file.exists()){
//            System.out.println("文件不存在");
//            return "";
//        }
//
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//            byte[] buffer = new byte[128];
//            int count = 0;
//            while ((count = fis.read(buffer)) > 0) {
//                response.getOutputStream().write(buffer, 0, count);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//            fis.close();
//        }


    @RequestMapping("fileManagers/{pageIndex}/getFilePageModel")
    public @ResponseBody PageModel<FileManagers> getPPTList(@PathVariable Integer pageIndex, String fileName, String fileType, @RequestParam(value="pageSize",required=false) Integer pageSize ){
        PageModel<FileManagers> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        if(pageSize!=null){
            pageModel.setPageSize(pageSize);
        }
        System.err.println("fileName:"  + fileName);
        System.err.println("fileType:"  + fileType);
        FileManagers fileManagers = new FileManagers();
        if (fileName != null && !fileName.equals("")) {
            System.err.println("fileName111:"  + fileName);
            fileManagers.setFilename(fileName);
        }
        if (fileType != null && !fileType.equals("")) {
            System.err.println("fileType111:"  + fileType);
            fileManagers.setRemarks(fileType);
        }
        System.err.println("fileManagers: " + fileManagers.getRemarks());
        return fileManagerService.findFileManager(fileManagers, pageModel);
    }

}