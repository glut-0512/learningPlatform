package com.glut.learningplatform.mapper.dynaSqlProvider;

import com.glut.learningplatform.entity.CourseType;
import com.glut.learningplatform.util.common.RasConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class CourseTypeDynaSqlProvider {
    public String countCourseType(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("count(*)");
                FROM(RasConstants.COURSETYPETABLE);
                CourseType courseType  = (CourseType) params.get("courseType");
                if(courseType != null){
                    if(courseType.getTypeName() != null &&  !courseType.getTypeName().equals("")){
                        WHERE(" type_name like CONCAT('%',#{courseType.typeName},'%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    public String selectByParma(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(RasConstants.COURSETYPETABLE);
                CourseType courseType  = (CourseType) params.get("courseType");
                if(courseType != null){
                    if(courseType.getTypeName() != null &&  !courseType.getTypeName().equals("")){
                        WHERE(" type_name like CONCAT('%',#{courseType.typeName},'%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }
}
