package com.glut.learningplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glut.learningplatform.entity.CourseType;
import com.glut.learningplatform.util.tag.PageModel;

import java.util.Map;

public interface CourseTypeService extends IService<CourseType> {
    // 分页查询课程类型
    PageModel<CourseType> findCourseType(CourseType courseType , PageModel<CourseType> pageModel);
    //获取用户的信息
    Map<String, Object> getUserNews(int id);
}
