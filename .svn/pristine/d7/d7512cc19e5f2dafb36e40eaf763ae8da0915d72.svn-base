package com.glut.learningplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glut.learningplatform.entity.UserInfo;
import com.glut.learningplatform.mapper.UserInfoMapper;
import com.glut.learningplatform.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userInfoSevice")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private  UserInfoMapper userInfoMapper;
    @Override
    public boolean saveuserInfos(Map<String, Object> map) {
        if(userInfoMapper.updateUser(map)){
            if(userInfoMapper.selectuserInfo(Integer.parseInt(((map.get("userId")).toString())))!=null){
                return userInfoMapper.updateUserInfo(map);
            }
            else{
                return userInfoMapper.insertUserinfo(map);
            }
        }
        return false;
    }
}
