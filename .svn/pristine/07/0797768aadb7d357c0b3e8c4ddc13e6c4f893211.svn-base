package com.glut.learningplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.util.tag.PageModel;

import java.util.List;

public interface ManagerService extends IService<Manager> {
    Manager validateManager(String username , String password);
    // 分页查询管理员
    PageModel<Manager> findManager(Manager manager, PageModel<Manager> pageModel);

    /**
     * 获取非admin的管理员
     * @return
     */
    List<Manager> getOrdinaryManager();
}
