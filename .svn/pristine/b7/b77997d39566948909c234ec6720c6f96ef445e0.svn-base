package com.glut.learningplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glut.learningplatform.entity.CourseType;
import com.glut.learningplatform.service.CourseTypeService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseTypeController {
    @Autowired
    private CourseTypeService courseTypeService;

    //课程类型分页查询
    @RequestMapping("/courseType/{pageIndex}/getCourseTypePageModel")
    public @ResponseBody
    PageModel<CourseType> getCourseTypePageModel(@PathVariable Integer pageIndex, @RequestParam("keyword") String keyword){
        PageModel<CourseType> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        if(keyword != null && !keyword.equals("")){
            CourseType courseType = new CourseType();
            courseType.setTypeName(keyword);
            return courseTypeService.findCourseType(courseType, pageModel);
        }
        return courseTypeService.findCourseType(null, pageModel);
    }

    //添加课程类型
    @RequestMapping("/courseType/saveCourseType")
    public @ResponseBody String saveCourseType(CourseType courseType){
        if(courseType.getParentId().equals("0")){
            courseType.setProperty("0");
        }else{
            courseType.setProperty("1");
        }
        courseTypeService.save(courseType);
        return "{\"status\":true}";
    }

    //编辑课程类型
    @RequestMapping("/courseType/updateCourseType")
    public @ResponseBody String updateCourseType(CourseType courseType){
        courseTypeService.saveOrUpdate(courseType);
        return "{\"status\":true}";
    }

    @RequestMapping("/getCourseType")
    public @ResponseBody List<CourseType> getCourseType(){
        List<CourseType> list = courseTypeService.list(null);
        return courseTypeService.list(null);
    }

    //根据ID查询课程类型
    @RequestMapping("/findCourseTypeById")
    public @ResponseBody CourseType findCourseTypeById(@RequestParam("id") String id){
        return courseTypeService.getById(id);
    }

    //删除课程类型
    @RequestMapping("/courseType/deleteCourseType")
    public @ResponseBody String deleteCourseType(@RequestParam("ids[]") List<String> ids){
        List<String> dIds = new ArrayList<String>();
        for (int i=0;i<ids.size();i++){
           List<CourseType> list = courseTypeService.list(new QueryWrapper<CourseType>().eq("parent_id",ids.get(i)));
            if (list.size()>0){
                for (int j=0;j<list.size();j++){
                    dIds.add(list.get(j).getId());
                }
            }
            dIds.add(ids.get(i));
        }
        courseTypeService.removeByIds(dIds);
        return "{\"status\":true}";
    }

    @RequestMapping("/getCourseTypeToPage")
    public @ResponseBody Map<String,Object> getCourseTypeToPage(){
        List<CourseType> list = courseTypeService.list(null);
        //一级分类
        List<CourseType> fList = new ArrayList<>();
        List<CourseType> sList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String status = list.get(i).getProperty();
            if("0".equals(list.get(i).getProperty())){
                fList.add(list.get(i));
            }else{
                sList.add(list.get(i));
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("slist",sList);
        map.put("flist",fList);
        return map;
    }
}
