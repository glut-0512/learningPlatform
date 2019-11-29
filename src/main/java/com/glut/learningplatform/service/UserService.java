package com.glut.learningplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glut.learningplatform.entity.User;
import com.glut.learningplatform.util.tag.PageModel;

public interface UserService extends IService<User> {
    // 分页查询用户
    PageModel<User> findUser(User user, PageModel<User> pageModel);
}
