package com.glut.learningplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glut.learningplatform.entity.FileManagers;
import com.glut.learningplatform.util.tag.PageModel;

public interface FileManagerService extends IService<FileManagers> {

    String selectFilePathByFileName(String fileName);

    PageModel<FileManagers> findFileManager(FileManagers fileManagers, PageModel<FileManagers> pageModel);
}
