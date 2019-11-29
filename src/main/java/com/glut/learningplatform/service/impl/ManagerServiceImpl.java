package com.glut.learningplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.mapper.ManagerMapper;
import com.glut.learningplatform.service.ManagerService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("managerService")
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService{

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager validateManager(String username, String password) {
        return managerMapper.selectManeger(username,password);
    }

    @Override
    public PageModel<Manager> findManager(Manager manager, PageModel<Manager> pageModel) {
        Map<String,Object> params = new HashMap<>();
        params.put("manager", manager);
        Integer recordCount = managerMapper.countManager(params);
        if(recordCount > 0){
            pageModel.setRecordCount(recordCount);
            params.put("pageModel", pageModel);
        }

        List<Manager> list = managerMapper.selectByParam(params);
        pageModel.setList(list);
        return pageModel;
    }

    @Override
    public List<Manager> getOrdinaryManager() {
        return null;
    }
}
