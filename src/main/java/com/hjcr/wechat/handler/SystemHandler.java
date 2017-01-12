package com.hjcr.wechat.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.SystemUserService;
import com.hjcr.wechat.shiro.ShiroPrincipal;
import com.hjcr.wechat.tools.ResultMessage;

@Controller
@RequestMapping(value = "/system")
public class SystemHandler extends GenericController {
	
	private final Logger log = LoggerFactory.getLogger(SystemHandler.class);
	
	@Autowired
	private SystemUserService systemUserService;
	
//	@RequiresPermissions("system:getByName")
	@RequestMapping(value = "/getByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByName(String name) {
		log.info("get System User by username");
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		SystemUser user = systemUserService.findOneByName(name);
		result.getResultParm().put("user", user);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
//	@RequiresPermissions("system:getUserPrivilegeCode")
	@RequestMapping(value = "/getUserPrivilegeCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getUserPrivilegeCode(Integer userId) {
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		List<String> list = systemUserService.findPrivilegeCode(userId);
		result.getResultParm().put("user", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
//	@RequiresPermissions("system:getRoleName")
	@RequestMapping(value = "/getRoleName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getRoleName(Integer userId) {
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		String roleName = systemUserService.findRoleName(userId);
		result.getResultParm().put("roleName", roleName);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> login(SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			result.setServiceResult(false);
			result.setResultInfo("数据错误");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
//		String MD5Password = MD5Util.md5(user.getPassword());
		log.info("----------------完美分割线-------------------");
		SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
		ShiroPrincipal principal = (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
		SystemUser userInfo = principal.getUser();
		result.getResultParm().put("user",userInfo);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	
}
