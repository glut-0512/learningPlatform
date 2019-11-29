package com.glut.learningplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glut.learningplatform.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    @Update("UPDATE user_table set phone_number=#{map.phoneNumber},email=#{map.email},username=#{map.username} WHERE user_id=#{map.userId}")
    boolean updateUser(@Param("map") Map<String,Object> map);

    @Select("SELECT * FROM personal_settings WHERE user_id=#{userId}")
    UserInfo selectuserInfo(@Param("userId") int userId);

    @Update("UPDATE personal_settings SET birthday =#{map.birthday},  education=#{map.education},universities_colleges=#{map.universitiesColleges},major=#{map.major},entrance=#{map.entrance},address=#{map.address},sex=#{map.sex},personal_profile=#{map.personalProfile} WHERE user_id=#{map.userId}")
    boolean updateUserInfo(@Param("map") Map<String,Object> map);

    @Insert("INSERT INTO personal_settings (user_id,education,privacy,at_school,universities_colleges,major,grade,address,sex,birthday,personal_profile,entrance) \n" +
            "VALUES(#{map.userId},#{map.education},1,1,#{map.universitiesColleges},#{map.major},1,#{map.address},#{map.sex},#{map.birthday},#{map.personalProfile},#{map.entrance})")
    boolean insertUserinfo(@Param("map") Map<String,Object> map);

}