package com.glut.learningplatform.controller;

import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.service.ManagerService;
import com.glut.learningplatform.util.authority.CheckAuthority;
import com.glut.learningplatform.util.common.RasConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class BackstageController {

    @Autowired
    private ManagerService managerService;



    //后台登录页面
    @RequestMapping("/backstage")
    public String backLogin()
    {
        return "backLogin";
    }

    /**
     * 验证管理员是否允许登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/backManagerLogin")
    public @ResponseBody
    String validateManager(@RequestParam("username") String username,
                           @RequestParam("password") String password, HttpSession session) {
        // 验证用户名和密码
        Manager manager = managerService.validateManager(username, password);
        if (manager != null) {
            session.setAttribute(RasConstants.MANAGER_SESSION, manager);
            //List<RoleAuthority> list =  roleManagerService.getManagerAuthority(manager.getId());
            //session.setAttribute(RasConstants.MANAGERROLEAUTHORITY_SESSION, list);
            //Map<String,String> map = new HashMap<>();
            //System.out.println(" ------------ 权限 >>>>>>>>>>>> ");
			/*for (RoleAuthority roleAuthority : list) {
				System.out.println(roleAuthority.getAuthority().getId());
				map.put(roleAuthority.getAuthority().getId(), roleAuthority.getAuthority().getId());
			}*/
//            System.out.println(" <<<<<<<<<<<< 权限 ------------ ");
            //session.setAttribute("authority",map);
            return "{\"message\":true}";
        }
        return "{\"message\":false}";
    }

    @RequestMapping("/main")
    public String main(HttpSession session, ModelMap modelMap) {
        Manager manager = (Manager) session.getAttribute(RasConstants.MANAGER_SESSION);
        if (manager != null) {
            modelMap.put("CheckAuthority",new CheckAuthority());
            return "welcome";
        }
        return "backLogin";
    }

    /**
     * 请求服务器中session中的用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping("/getSession")
    public @ResponseBody Manager getSession(HttpSession session) {
        // 取出session中的用户
        Manager manager = (Manager) session.getAttribute(RasConstants.MANAGER_SESSION);
        if (manager != null) {
            return manager;
        } else {
            return new Manager();
        }
    }


    /**
     * 跳转文件管理页面(目前文件没有跟任何属性绑定，后续是否跟课程、教师相关联看需求)
     *
     * @return
     */
    @GetMapping("/main/fileManagers")
    public String fileManagers() { return "fileManageModify";}


    /**
     * 跳转到修改管理员页面
     *
     * @return
     */
    @GetMapping("/main/modifyManager")
    public String modifyManager() {
        return "modifyManager";
    }

    /**
     * 跳转到用户管理页面
     *
     * @return
     */
    @GetMapping("/main/modifyUser")
    public String modifyUser() {
        return "modifyUser";
    }

    /**
     * 跳转到课程类型页面
     *
     * @return
     */
    @GetMapping("/main/modifyCourseType")
    public String modifyCourseType() {
        return "modifyCourseType";
    }

    /**
     * 跳转到课程类型管理页面
     *
     * @return
     */
    @GetMapping("/main/modifyCourse")
    public String modifyCourse() {
        return "modifyCourse";
    }

}
