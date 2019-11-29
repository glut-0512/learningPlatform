package com.glut.learningplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glut.learningplatform.entity.CourseType;
import com.glut.learningplatform.mapper.CourseTypeMapper;
import com.glut.learningplatform.service.CourseTypeService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements CourseTypeService{

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Override
    public PageModel<CourseType> findCourseType(CourseType courseType, PageModel<CourseType> pageModel) {
        Map<String,Object> params = new HashMap<>();
        params.put("courseType", courseType);
        Integer recordCount = courseTypeMapper.count(params);
        if(recordCount > 0){
            pageModel.setRecordCount(recordCount);
            params.put("pageModel",pageModel);
        }
        //查询分页记录
        List<CourseType> list = courseTypeMapper.selectByPage(params);
        pageModel.setList(list);
        return pageModel;
    }

    @Override
    public Map<String, Object> getUserNews(int id) {
        Map<String,Object> maps = new HashMap<>();
        maps.put("userinfo", courseTypeMapper.getuserinfoNrews(id));
        maps.put("users",courseTypeMapper.getUserNews(id));
        return maps;
    }
}