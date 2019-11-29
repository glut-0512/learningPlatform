package com.glut.learningplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glut.learningplatform.entity.User;
import com.glut.learningplatform.entity.UserInfo;
import com.glut.learningplatform.service.UserInfoService;
import com.glut.learningplatform.service.UserService;
import com.glut.learningplatform.util.common.CaptchaUtils;
import com.glut.learningplatform.util.common.MD5Util;
import com.glut.learningplatform.util.common.RandomUtil;
import com.glut.learningplatform.util.common.RasConstants;
import com.glut.learningplatform.util.mail.TextMessageSender;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    private String code = "";//验证码
    /************************后台************************/
    // 分页查询用户
    @RequestMapping("/user/{pageIndex}/getUserPageModel")
    public @ResponseBody
    PageModel<User> getUserPageModel(@PathVariable Integer pageIndex, String userName, String userIdcar, @RequestParam(value="pageSize",required=false) Integer pageSize) {
        PageModel<User> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        if(pageSize!=null){
            pageModel.setPageSize(pageSize);
        }
        User user = new User();
        if (userName != null && !userName.equals("")) {
            user.setUsername(userName);
        }
        if (userIdcar != null && !userIdcar.equals("")) {
            user.setCardId(userIdcar);
        }
        return userService.findUser(user, pageModel);
    }

    // 根据id查询
    @RequestMapping("/findUserById")
    public @ResponseBody User findUserById(@RequestParam("id") Integer id) {
        return userService.getById(id);
    }
    /************************后台************************/
    /************************前端************************/
    //注冊
    @RequestMapping("/registerUser")
    public @ResponseBody String registerUser(@RequestParam Map map) throws UnsupportedEncodingException {
        //System.err.println(user);
        String s = "ran/*dom.01";
        String DM5Password = MD5Util.getEncryption(map.get("loginPwd_01").toString());
        String newPassword = DM5Password + s;
        User user = new User();
        user.setLoginName(map.get("loginName").toString());
        user.setPassword(MD5Util.getEncryption(newPassword));
        user.setUsername(map.get("userName").toString());
        user.setPhoneNumber(map.get("phoneName").toString());
        user.setEmail(map.get("mailName").toString());
        userService.save(user);
        return "{\"status\":true,\"message\":\"注册成功！\"}";
    }

    long startTime = 0;//记录发送时间
    long endTime = 0;//记录校验时间
    String msg = "";//生成验证码
    //发送验证码
    @RequestMapping("/sendRandom")
    public @ResponseBody String sendRandom(@RequestParam("email") String email){
        RandomUtil rand = new RandomUtil();
        msg = rand.getRandom();
        TextMessageSender emailSend = new TextMessageSender();
        String[] args = {email,";",";","教学资源平台验证码","您收到的验证码为："+ msg +"，该验证码有效期为3分钟。"};
        startTime =  System.currentTimeMillis();
        /** 程序运行 processRun();*/
        emailSend.email(args);
        return "{\"status\":false,\"message\":\"发送成功！\"}";
    }

    @RequestMapping("/checkRandom")
    public @ResponseBody String checkRandom(@RequestParam("checkMsg") String checkMsg,
                                            @RequestParam("password") String passWord,
                                            @RequestParam("loginName") String loginName,
                                            @RequestParam("email") String email) throws UnsupportedEncodingException{
        /** 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数，除以1000就是秒数*/
        endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime)/1000;
        if (usedTime>300){
            return "{\"status\":true,\"message\":\"验证码错误或已失效！\"}";
        }
        if (msg.equals(checkMsg)){
            //查询账号
            User user = userService.getOne(new QueryWrapper<User>().eq("login_name",loginName).eq("email",email),true);
            if (user!=null){
                String s = "ran/*dom.01";
                String DM5Password = MD5Util.getEncryption(passWord);
                String newPassword = DM5Password + s;
                user.setPassword(newPassword);
                userService.saveOrUpdate(user);
                return "{\"status\":true,\"message\":\"密码重置成功！\"}";
            }else {
                return "{\"status\":false,\"message\":\"该账号不存在！\"}";
            }
        }
        return "{\"status\":false,\"message\":\"验证码输入错误！\"}";
    }

    //获取登录页面验证码
    @RequestMapping("/getCode")
    public void getCaptcheImage(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pargma", "-1");
        // 制作验证码
        Object[] obj = CaptchaUtils.getCaptchaImage(150, 50, 35, 3, 0, false, true, CaptchaUtils.ComplexLevel.MEDIUM);
        // 将验证码输入到session中，用来验证
        //session.setAttribute("login_code", obj[1]);
        code = (String) obj[1];
        // 输出到web页面
        ImageIO.write((BufferedImage) obj[0], "jpg", response.getOutputStream());
    }

    // 获取用户session
    @RequestMapping("/getUserSession")
    public @ResponseBody User getUserSession(HttpSession session) {
        User userSession = (User) session.getAttribute("user_session");
        return userSession;
    }

    // 判断用户session
    @RequestMapping("/judgeUserSession")
    public @ResponseBody String judgeUserSession(HttpSession session) {
        User userSession = (User) session.getAttribute("user_session");
        if (userSession != null) {
            return "{\"status\":true}";
        }
        return "{\"status\":false}";
    }

    // 用户登录 、、、、登录名 ，手机号，邮箱
    @RequestMapping("/userLogin")
    public @ResponseBody String validateUser(@RequestParam("loginName") String loginName,
                                             @RequestParam("password") String password,
                                             @RequestParam("code") String codeString,HttpSession session)
                                             throws UnsupportedEncodingException {

        if(!code.toLowerCase().equals(codeString.toLowerCase())){
            return "{\"status\":false,\"message\":\"验证码错误!!!\"}";
        }

        // 验证用户名和密码
        String s = "ran/*dom.01";
        String MD5password = MD5Util.getEncryption(password);
        //System.out.println("MD5加密");
        //System.out.println(MD5password);
        String String1 = MD5password + s;
        String newPassword = MD5Util.getEncryption(String1);
        User user = userService.getOne(new QueryWrapper<User>().eq("login_name",loginName).eq("password",newPassword),true);
        if (user != null) {
            session.setAttribute(RasConstants.USER_SESSION, user);
            session.setAttribute("courseTimeRandom", RandomUtil.getRandom15());
            //保存登录时间
            Date date = new Date();
            user.setLoginTime(date);
            userService.saveOrUpdate(user);
            return "{\"status\":true}";
        }
        return "{\"status\":false,\"message\":\"用户名或密码错误!!!\"}";
    }

    // 用户退出
    @RequestMapping("/userOutLogin")
    public String userOutLogin(HttpSession session) {
        //保存登出时间
        User userSession = (User) session.getAttribute("user_session");
        Date date = new Date();
        userSession.setLogoutTime(date);
        userService.saveOrUpdate(userSession);
        session.invalidate();
        return "index";
    }

    // 获取用户详情
    @RequestMapping("/getUserInfo")
    public  @ResponseBody
    UserInfo getUserInfon(HttpSession session) {
        User userSession = (User) session.getAttribute("user_session");
        if(userSession != null){
            UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id",userSession.getId()),true);
            if(userInfo != null){
                return userInfo;
            }
        }
        return null;
    }
    //保存用户信息
    @PostMapping("/saveTheUserInfo")
    @ResponseBody
    boolean saveTheUserInfo(@RequestParam Map<String,Object> map, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User)session.getAttribute("user_session");
        map.put("userId",user.getUserId());
        return userInfoService.saveuserInfos(map);
    }

    @RequestMapping(value = "saveUserInfo", method = RequestMethod.POST)
    public @ResponseBody String saveUserInfo(UserInfo userInfo) throws IOException {
        if(userService.getById(userInfo.getUserId()) == null){
            return "{\"status\":false}";
        }
        if (userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id",userInfo.getUserId())) != null) {
            userInfoService.saveOrUpdate(userInfo);
        } else {
            userInfoService.save(userInfo);
        }
        return "{\"status\":true}";
    }

    /**
     * 更新用户
     *
     * @param user
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public @ResponseBody String updateUser(User user,HttpSession session) throws IOException {
        userService.saveOrUpdate(user);
        session.setAttribute(RasConstants.USER_SESSION, user);
        return "{\"status\":true}";
    }

    //验证用户的密码
    @RequestMapping(value = "/validUser")
    public @ResponseBody String validUser(HttpSession session,@RequestParam("oldPassword") String oldPassword,
                                            @RequestParam("newPassword") String newPassword) throws UnsupportedEncodingException{
        User userSession = (User) session.getAttribute("user_session");
        String s = "ran/*dom.01";
        String DM5Password = MD5Util.getEncryption(oldPassword);
        String Password = MD5Util.getEncryption(DM5Password + s);
        if(Password.equals(userSession.getPassword())){
            userSession.setPassword(MD5Util.getEncryption(MD5Util.getEncryption(newPassword) + s));
            userService.saveOrUpdate(userSession);
            return "{\"status\":true,\"message\":\"密码修改成功！\"}";
        }
        return "{\"status\":false,\"message\":\"原密码输入不正确，请重新输入！\"}";
    }
    /************************前端************************/
}