package com.glut.learningplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.mapper.FileManagersMapper;
import com.glut.learningplatform.service.FileManagerService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("FileManagerService")
public class FileManagerServiceImpl extends ServiceImpl<FileManagersMapper, FileManagers> implements FileManagerService {

    @Autowired
    private FileManagersMapper fileManagersMapper;

    @Override
    public String selectFilePathByFileName(String fileName) {
        return fileManagersMapper.selectFilePathByName(fileName);
    }

    @Override
    public PageModel<FileManagers> findFileManager(FileManagers fileManagers, PageModel<FileManagers> pageModel) {
        Map<String,Object> params = new HashMap<>();
        params.put("fileManagers", fileManagers);
        Integer recordCount = fileManagersMapper.countFileManager(params);
        //System.out.println(recordCount);
        if(recordCount > 0){
            pageModel.setRecordCount(recordCount);
            params.put("pageModel", pageModel);
        }

        List<FileManagers> list = fileManagersMapper.selectByParam(params);
        //System.out.println(list);
        pageModel.setList(list);
        return pageModel;
    }
}
