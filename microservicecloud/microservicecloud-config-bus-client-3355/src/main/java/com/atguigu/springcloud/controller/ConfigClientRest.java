package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dao.DeptDao;
import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class ConfigClientRest
{


	@Value("${from}")
	private String from;

	@Autowired
	private DeptDao deptDao;

	@RequestMapping("/config")
	public String getConfig()
	{
		return this.from;
	}


	@RequestMapping("/dept/list")
	public List<Dept> list()
	{
		return deptDao.findAll();
	}
}
