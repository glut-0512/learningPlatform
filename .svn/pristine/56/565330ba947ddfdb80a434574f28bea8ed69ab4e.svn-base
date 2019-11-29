package com.glut.learningplatform.controller;

import com.glut.learningplatform.entity.Manager;
import com.glut.learningplatform.service.ManagerService;
import com.glut.learningplatform.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
	@Autowired
	private ManagerService managerService;

	//分页查询管理员
	@RequestMapping("/manager/{pageIndex}/getManagerPageModel")
	public @ResponseBody
	PageModel<Manager> getManagerPageModel(@PathVariable Integer pageIndex, @RequestParam("keyword") String keyword){
		PageModel<Manager> pageModel = new PageModel<>();
		pageModel.setPageIndex(pageIndex);
		if(keyword != null && !keyword.equals("")){
			Manager manager  = new Manager();
			manager.setUsername(keyword);
			PageModel<Manager> text = managerService.findManager(manager, pageModel);
			return managerService.findManager(manager, pageModel);
		}
		PageModel<Manager> text1 = managerService.findManager(null, pageModel);
		return managerService.findManager(null, pageModel);
	}
	
	//根据id查询
	@RequestMapping("/findManagerById")
	public @ResponseBody Manager findManagerById(@RequestParam("id") String id){
		return managerService.getById(id);
	}
	
	
	/**
	 * 保存管理员
	 * @param manager
	 * @return
	 */
	@RequestMapping("/manager/saveManager")
	public @ResponseBody String saveManager(Manager manager){
		managerService.save(manager);
		return "{\"status\":true}";
	}

	/**
	 * 更新管理员信息
	 * @param manager
	 * @return
	 */
	@RequestMapping("/manager/updateManager")
	public @ResponseBody String updateManager(Manager manager){
		managerService.saveOrUpdate(manager);
		return "{\"status\":true}";
	}
	
	@RequestMapping("/manager/deleteManager")
	public @ResponseBody String batchDelManager(@RequestParam("ids[]") List<String> ids){
		managerService.removeByIds(ids);
		//managerService.batchDelManager(ids);
		return "{\"status\":true}";
	}
	
	@GetMapping("/manager/getOrdinaryManager")
	@ResponseBody
	public List<Manager> getOrdinaryManager(){
		return managerService.getOrdinaryManager();
	}
}