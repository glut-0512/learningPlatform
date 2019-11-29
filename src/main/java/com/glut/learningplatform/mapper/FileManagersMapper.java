package com.glut.learningplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.mapper.dynaSqlProvider.FileManagersDynaSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "fileManagersMapper")
public interface FileManagersMapper extends BaseMapper<FileManagers> {
    int deleteByPrimaryKey(String id);

    int insert(FileManagers record);

    int insertSelective(FileManagers record);

    FileManagers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileManagers record);

    int updateByPrimaryKey(FileManagers record);

    @SelectProvider(type = FileManagersDynaSqlProvider.class , method = "selectFilePathByName")
    String selectFilePathByName(String fileName);

    @SelectProvider(type = FileManagersDynaSqlProvider.class , method = "countFileManager")
    Integer countFileManager(Map<String, Object> params);

    @SelectProvider(type = FileManagersDynaSqlProvider.class , method = "selectByParam")
    List<FileManagers> selectByParam(Map<String, Object> params);
}