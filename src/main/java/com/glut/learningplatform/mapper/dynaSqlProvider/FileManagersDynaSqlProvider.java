package com.glut.learningplatform.mapper.dynaSqlProvider;


import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.util.common.RasConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class FileManagersDynaSqlProvider {

    public String selectFilePathByName(String fileName){
        System.out.println("SQL : " + fileName);
        String sql = new SQL(){
            {
                SELECT("url");
                FROM(RasConstants.FILEMANAGERS);
                WHERE(" fileName = " + "\"" + fileName + "\"");
            }
        }.toString();
        return sql;
    }

    public String countFileManager(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(RasConstants.FILEMANAGERS);
                FileManagers fileManagers = (FileManagers) params.get("fileManagers");
                if(fileManagers != null){
                    if(fileManagers.getFilename() != null && !fileManagers.getFilename().equals("")){
                        WHERE(" fileName like CONCAT('%',#{fileManagers.filename},'%') " );
                    }
                    if(fileManagers.getRemarks() != null && !fileManagers.getRemarks().equals("")){
                        WHERE(" remarks = #{fileManagers.remarks} " );
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        System.err.println(sql);
        return sql;
    }

    public String selectByParam(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(RasConstants.FILEMANAGERS);
                ORDER_BY("id Desc");
                FileManagers fileManagers = (FileManagers) params.get("fileManagers");
                if(fileManagers != null){
                    if(fileManagers.getFilename() != null && !fileManagers.getFilename().equals("")){
                        WHERE("fileName like CONCAT('%',#{fileManagers.filename},'%')");
                    }
                    if(fileManagers.getRemarks() != null && !fileManagers.getRemarks().equals("")){
                        WHERE("remarks = #{fileManagers.remarks}");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        System.err.println(sql);
        return sql;
    }
}
