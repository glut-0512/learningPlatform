package com.glut.learningplatform.mapper.dynaSqlProvider;

import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.util.common.RasConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ManagerDynaSqlProvider {
	
	public String selectByParam(Map<String, Object> params){
		String sql = new SQL(){
			{
				SELECT("*");
				FROM(RasConstants.MANAGERTABLE);
				Manager manager = (Manager) params.get("manager");
				ORDER_BY("id Desc");
				if(manager != null){
					if(manager.getUsername() != null && !manager.getUsername().equals("")){
						WHERE(" username like CONCAT('%',#{username},'%')");
					}
				}
			}
		}.toString();
		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String countManager(Map<String, Object> params){
		String sql = new SQL(){
			{
				SELECT("count(*)");
				FROM(RasConstants.MANAGERTABLE);
				Manager manager = (Manager) params.get("manager");
				if(manager != null){
					if(manager.getUsername() != null && !manager.getUsername().equals("")){
						WHERE(" username like CONCAT('%',#{username},'%')");
					}
				}
			}
		}.toString();
		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String updateManager(Manager manager){
		String sql =  new SQL(){
			{
				UPDATE(RasConstants.MANAGERTABLE);
				if(manager != null){
					if(manager.getUsername() != null && !manager.getUsername().equals("")){
						SET("username = #{username}");
					}
					if(manager.getPassword() != null && !manager.getPassword().equals("")){
						SET("password = #{password}");
					}
					WHERE(" id = #{id} ");
				}
			}
		}.toString();
		return sql;
	}
	
	public String bathDel(Map<String, Integer[]> ids){
		return new SQL(){
			{
				if(ids != null && ids.get("ids") != null){
					DELETE_FROM(RasConstants.MANAGERTABLE);
					String inConditions = "id in (";
					Integer[] idsArr = ids.get("ids");
					for (int index = 0; index < idsArr.length; index++) {
						inConditions += idsArr[index];
						if(index != idsArr.length - 1){
							inConditions += ",";
						}
					}
					inConditions +=") ";
					WHERE(inConditions);
				}
			}
		}.toString();
	}
}
