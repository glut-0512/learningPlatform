package com.glut.learningplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glut.learningplatform.entity.User;
import com.glut.learningplatform.mapper.dynaSqlProvider.UserDynaSqlProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //获取所有用户list
    @SelectProvider(type = UserDynaSqlProvider.class , method = "selectByParam")
    @Results(value = {@Result(column = "login_name" , property = "loginName" , javaType = java.lang.String.class),
            @Result(column = "phone_number" , property = "phoneNumber", javaType = java.lang.String.class),
            @Result(column = "user_pic" , property = "userPic", javaType = java.lang.String.class),
            @Result(column = "card_id" , property = "cardId", javaType = java.lang.String.class)})
    List<User> selectByParam(Map<String, Object> params);

    // 查询所有用户总数
    @SelectProvider(type = UserDynaSqlProvider.class , method = "countUser")
    Integer countUser(Map<String, Object> params);
}