package com.hjcr.wechat.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcr.wechat.dao.impl.BaseDao;

@Controller
public class Handler {

	
	@Autowired
	private BaseDao baseDao;
	
	  @RequestMapping(value={"/Test"}, produces={"application/json;charset=UTF-8"})
	  public String Test() {
		  System.out.println(baseDao.getSession());
	    return "NewFile";
	  }
	
}
