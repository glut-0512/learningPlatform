package com.glut.learningplatform.controller;

import com.glut.learningplatform.entity.User;
import com.glut.learningplatform.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RootController {

    @Autowired
    private CourseTypeService courseTypeService;

    //跳转首页
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //跳转前端页面登录
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //跳转前端注册
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //跳转教学资源
    @RequestMapping("/list_of_catalogues")
    public String listOfCatalogues(HttpServletRequest request){
        return "list_of_catalogues";
    }

    //跳转忘記密碼
    @RequestMapping("/password_back")
    public String passwordBack(HttpServletRequest request){
        return "password_back";
    }

    //跳转学习进度
    @RequestMapping("/collection_course")
    public String collectionCourse(HttpServletRequest request){
        return "collection_course";
    }

    //跳转个人设置-基础信息
    @RequestMapping("/my_set_up")
    public String mySetUp(HttpServletRequest request){
        return "my_set_up";
    }

    //获取等人存在session中的基本信息
    @GetMapping("/getmysessionNews")
    @ResponseBody
    public Map<String, Object> getmysessionNews(HttpServletRequest request){
            HttpSession session = request.getSession();
            User thisUser = (User)session.getAttribute("user_session");
        return courseTypeService.getUserNews(thisUser.getUserId());
    }

    //跳转个人设置-密码设置
    @RequestMapping("/new_password")
    public String newPassword(HttpServletRequest request){
        return "new_password";
    }

    //跳转个人设置-头像设置
    @RequestMapping("/my_portrait")
    public String my_portrait(HttpServletRequest request){
        return "my_portrait";
    }

    //跳转我的课程
    @RequestMapping("/learning")
    public String learning(HttpServletRequest request){
        return "learning";
    }

    //跳转课程详情
    @RequestMapping("/curriculum")
    public String curriculum(HttpServletRequest request){
        return "curriculum";
    }

    //跳转教师详情
    @RequestMapping("/teacher")
    public String teacher(HttpServletRequest request){
        return "teacher";
    }

    //进入学习
    @RequestMapping("/video")
    public String video(HttpServletRequest request){
        return "video";
    }

    //进入学习
    @RequestMapping("/jpg")
    public String jpg(HttpServletRequest request){
        return "jpg";
    }

    //进入学习
    @RequestMapping("/ppt")
    public String ppt(HttpServletRequest request){
        return "ppt";
    }

    //进入学习
    @RequestMapping("/pdf")
    public String pdf(HttpServletRequest request){
        return "pdf";
    }

    //进入学习
    @RequestMapping("/swf")
    public String swf(HttpServletRequest request){
        return "swf";
    }

    //进入学习
    @RequestMapping("/professional_type")
    public String professional_type(HttpServletRequest request){
        return "professional_type";
    }
}