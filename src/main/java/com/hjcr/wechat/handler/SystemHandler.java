package com.hjcr.wechat.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjcr.wechat.entity.Privilege;
import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.PrivilegeService;
import com.hjcr.wechat.service.RolePrivilegeService;
import com.hjcr.wechat.service.RoleService;
import com.hjcr.wechat.service.SystemUserService;
import com.hjcr.wechat.shiro.ShiroPrincipal;
import com.hjcr.wechat.tools.ResultMessage;

/*
 * 后台管理系统权限管理控制器
 */
@Controller
@RequestMapping(value = "/system")
public class SystemHandler extends GenericController {
	
	private final Logger log = LoggerFactory.getLogger(SystemHandler.class);
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	@Autowired
	private PrivilegeService privilegeService;
	
	
	
	/*
	 * 根据用户名（登录账号）获取用户信息（测试所用）
	 */
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
	
	/*
	 * 根据用户id获取用户权限（测试所用）
	 */
//	@RequiresPermissions("system:getUserPrivilegeCode")
	@RequestMapping(value = "/getUserPrivilegeCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getUserPrivilegeCode(Integer userId) {
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		List<String> list = systemUserService.findPrivilegeCode(userId);
		result.getResultParm().put("user", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/*
	 * 根据用户id获取用户角色（测试所用）
	 */
//	@RequiresPermissions("system:getRoleName")
	@RequestMapping(value = "/getRoleName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getRoleName(Integer userId) {
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		String roleName = systemUserService.findRoleName(userId);
		result.getResultParm().put("roleName", roleName);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 系统登录
	 * @author 宋
	 * @param username 登录账号
	 * @param password 登录密码
	 * @return
	 */
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
	
	/**
	 * 添加系统用户
	 * @author 宋
	 * @param username 用户账号
	 * @param password 用户密码
	 * @return
	 */
	@RequestMapping(value="/add", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> add(SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			result.setServiceResult(false);
			result.setResultInfo("数据错误");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		user = systemUserService.addUser(user);
		if (user.getId() == null) {
			result.setServiceResult(false);
			result.setResultInfo("添加失败");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 添加系统角色
	 * @author 宋
	 * @param rolename 角色名（必须）
	 * @param note 角色描述
	 * @return
	 */
	@RequestMapping(value = "/addRole" ,method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addRole(Role role) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(role.getRolename())) {
			result.setServiceResult(false);
			result.setResultInfo("数据错误");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		role = roleService.addRole(role);
		if (role.getId() == null) {
			result.setServiceResult(false);
			result.setResultInfo("添加失败");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		result.getResultParm().put("role",role);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 给某个角色添加某权限
	 * @author 宋
	 * @param roleId 角色id
	 * @param privilegeId 权限id
	 * @return
	 */
	@RequestMapping(value = "/addPrivilegeToRole" ,method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addPrivilegeToRole(RolePrivilege rp) {
		ResultMessage result = new ResultMessage();
		if (rp.getRoleId() == null || rp.getPrivilegeId() == null) {
			result.setServiceResult(false);
			result.setResultInfo("数据错误");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		rp = rolePrivilegeService.addPrivilegeToRole(rp);
		if (rp.getId() == null) {
			result.setServiceResult(false);
			result.setResultInfo("添加失败");
			return new ResponseEntity<ResultMessage>(result,HttpStatus.NO_CONTENT);
		}
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取系统所有权限.
	 * @author 宋
	 * @return
	 */
	@RequestMapping(value = "/getAllPrivilege" ,method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllPrivilege() {
		ResultMessage result = new ResultMessage();
		List<Privilege> list = privilegeService.getAllPrivilege();
		result.getResultParm().put("privilegeList",list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 根据角色id获取该角色拥有的权限
	 * @author 宋
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value = "/getPrivilegeByRoleId" ,method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getPrivilegeByRoleId(Integer roleId) {
		ResultMessage result = new ResultMessage();
		List<Privilege> list = privilegeService.getPrivilegeByRoleId(roleId);
		result.getResultParm().put("privilegeList",list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取系统所有角色
	 * @author 宋
	 * @return
	 */
	@RequestMapping(value = "/getAllRole" ,method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllRole() {
		ResultMessage result = new ResultMessage();
		List<Role> list = roleService.getAllRole();
		result.getResultParm().put("roleList",list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取系统用户的角色信息
	 * @author 宋
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getRoleByUser" ,method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getRoleByUser(Integer userId) {
		ResultMessage result = new ResultMessage();
		
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
