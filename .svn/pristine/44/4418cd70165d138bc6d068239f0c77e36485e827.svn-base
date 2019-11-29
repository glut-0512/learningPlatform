package com.glut.learningplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glut.learningplatform.entity.CourseType;
import com.glut.learningplatform.entity.User;
import com.glut.learningplatform.entity.UserInfo;
import com.glut.learningplatform.mapper.dynaSqlProvider.CourseTypeDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component(value = "courseTypeMapper")
@Repository
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    int deleteByPrimaryKey(String id);

    int insert(CourseType record);

    int insertSelective(CourseType record);

    CourseType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseType record);

    int updateByPrimaryKey(CourseType record);

    @Select("SELECT login_name,phone_number,email,username FROM user_table WHERE user_id=#{id}")
    User getUserNews(@Param("id") int id);

    @Select("SELECT universities_colleges,education,major,entrance,birthday,address,sex,personal_profile FROM personal_settings WHERE user_id=#{id}")
    UserInfo getuserinfoNrews(@Param("id") int id);

    // 查询课程类型总数
    @SelectProvider(type = CourseTypeDynaSqlProvider.class , method = "countCourseType")
    Integer count(Map<String, Object> params);

    // 查询课程类型列表
    @SelectProvider(type = CourseTypeDynaSqlProvider.class , method = "selectByParma")
    @Results(value = {@Result(column = "type_name" , property = "typeName" , javaType = java.lang.String.class),
            @Result(column = "parent_id" , property = "parentId" , javaType = java.lang.String.class)})
    List<CourseType> selectByPage(Map<String, Object> params);
}