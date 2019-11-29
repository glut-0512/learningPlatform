package com.glut.learningplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.mapper.dynaSqlProvider.ManagerDynaSqlProvider;
import com.glut.learningplatform.util.common.RasConstants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "managerMapper")
public interface ManagerMapper extends BaseMapper<Manager> {

    @Select("select * from " + RasConstants.MANAGERTABLE + " where username = #{username} and password = #{password}")
    Manager selectManeger(@Param("username") String username , @Param("password") String password);

    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    // 查询所有管理员总数
    @SelectProvider(type = ManagerDynaSqlProvider.class , method = "countManager")
    Integer countManager(Map<String, Object> params);

    // 获取所有管理员
    @SelectProvider(type = ManagerDynaSqlProvider.class , method = "selectByParam")
    List<Manager> selectByParam(Map<String, Object> params);

}