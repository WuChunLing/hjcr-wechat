package com.hjcr.wechat.handler;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjcr.wechat.entity.Privilege;
import com.hjcr.wechat.entity.Role;
import com.hjcr.wechat.entity.RolePrivilege;
import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.PrivilegeService;
import com.hjcr.wechat.service.RolePrivilegeService;
import com.hjcr.wechat.service.RoleService;
import com.hjcr.wechat.service.SystemUserService;
import com.hjcr.wechat.tools.MD5Util;
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

	/**
	 * 系统登录
	 * 
	 * @author 宋
	 * @param username
	 *            登录账号
	 * @param password
	 *            登录密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> login(SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(user.getUsername())) {
			throw new SecurityException("账号不能为空");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new SecurityException("密码不能为空");
		}
		if (systemUserService.findOneByName(user.getUsername()) == null) {
			throw new SecurityException("该用户不存在");
		}
		String MD5Password = MD5Util
				.md5(user.getPassword() + user.getUsername());
		log.info("----------------完美分割线-------------------");
		SecurityUtils.getSubject().login(
				new UsernamePasswordToken(user.getUsername(), MD5Password));
		result.setResultInfo("登录成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取系统所有用户.
	 * 
	 * @author 宋
	 * @return
	 */
	@RequestMapping(value = "/getAllSystemUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllSystemUser() {
		ResultMessage result = new ResultMessage();
		List<SystemUser> list = systemUserService.getAllSystemUser();
		result.getResultParm().put("userList", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

	/**
	 * 添加系统用户
	 * 
	 * @author 宋
	 * @param username
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @return
	 */
	@RequestMapping(value = "/addSystemUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> add(@RequestBody SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(user.getUsername())
				|| StringUtils.isBlank(user.getPassword())) {
			throw new SecurityException("数据有误");
		}
		user = systemUserService.addUser(user);
		if (user.getId() == null) {
			throw new SecurityException("添加失败");
		}
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 删除系统用户
	 * 
	 * @author 宋
	 * @param id 
	 * @return
	 */
	@RequestMapping(value = "/deleteSystemUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteSystemUser(
			@RequestBody SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (user.getId() == null) {
			throw new SecurityException("数据有误");
		}
		systemUserService.delete(user);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 更新系统用户密码
	 * @author 宋
	 * @param username 用户账号
	 * @param oldPassword 用户旧密码
	 * @param newPassword 用户新密码
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updatePassword(@RequestBody Map<String,Object> map) {
		ResultMessage result = new ResultMessage();
		String username  = (String) map.get("username");
		String oldPassword  = (String) map.get("oldPassword");
		String newPassword  = (String) map.get("newPassword");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(oldPassword)
				|| StringUtils.isBlank(newPassword)) {
			throw new SecurityException("数据有误");
		}
		systemUserService.updatePassword(username, oldPassword,newPassword);
		result.setResultInfo("更改成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 更新系统用户角色
	 * @author 宋
	 * @param id 用户id
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateUserRole(@RequestBody SystemUser user) {
		ResultMessage result = new ResultMessage();
		if (user.getId() == null || user.getRoleId() == null) {
			throw new SecurityException("数据有误");
		}
		systemUserService.updateUserRole(user);
		result.setResultInfo("更改成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 添加系统角色
	 * 
	 * @author 宋
	 * @param rolename
	 *            角色名（必须）
	 * @param note
	 *            角色描述
	 * @return
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> addRole(
			@RequestParam(value = "rolename", required=false) String rolename, 
			@RequestParam(value = "privilegeIdList", required=false) Integer[] list) {
		ResultMessage result = new ResultMessage();
		
		if (StringUtils.isBlank(rolename)) {
			throw new SecurityException("角色名称不能为空");
		}
		Role role = new Role();
		role.setRolename(rolename);
		role = roleService.addRole(role,list);
		if (role.getId() == null) {
			throw new SecurityException("添加失败");
		}
		result.getResultParm().put("role", role);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 更新系统角色名
	 * 
	 * @author 宋
	 * @param rolename
	 *            角色名（必须）
	 * @return
	 */
	@RequestMapping(value = "/updateRoleName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateRoleName(
			@RequestBody Role role) {
		ResultMessage result = new ResultMessage();
		if (StringUtils.isBlank(role.getRolename())) {
			throw new SecurityException("角色名称不能为空");
		}
		role = roleService.updateRoleName(role);
		if (role == null) {
			throw new SecurityException("更新失败");
		}
		result.getResultParm().put("role", role);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 删除系统角色
	 * 
	 * @author 宋
	 * @param id 
	 * @return
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteSystemUser(
			@RequestBody Role role) {
		ResultMessage result = new ResultMessage();
		if (role.getId() == null) {
			throw new SecurityException("数据有误");
		}
		roleService.delete(role);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 更新某个角色所拥有的权限
	 * 
	 * @author 宋
	 * @param roleId
	 *            角色id
	 * @param privilegeId
	 *            权限id
	 * @return
	 */
	@RequestMapping(value = "/updateRolePrivilege", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateRolePrivilege(
			@RequestBody RolePrivilege rp) {
		ResultMessage result = new ResultMessage();
		if (rp.getRoleId() == null || rp.getPrivilegeId() == null) {
			throw new SecurityException("数据有误");
		}
		if (!rolePrivilegeService.updateRolePrivilege(rp)) {
			throw new SecurityException("更新失败");
		}
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 获取系统所有权限.
	 * 
	 * @author 宋
	 * @return
	 */
	@RequestMapping(value = "/getAllPrivilege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllPrivilege() {
		ResultMessage result = new ResultMessage();
		List<Privilege> list = privilegeService.getAllPrivilege();
		result.getResultParm().put("privilegeList", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/*
	 * 获取系统所有角色
	 * 
	 * @author 宋
	 * @return
	 */
//	@RequiresRoles("administrator")
	@RequestMapping(value = "/getAllRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAllRole() {
		ResultMessage result = new ResultMessage();
		List<Role> list = roleService.getAllRole();
		result.getResultParm().put("roleList", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/*
	 * 根据角色id获取该角色拥有的权限（测试所用）
	 */
	@RequestMapping(value = "/getPrivilegeByRoleId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getPrivilegeByRoleId(Integer roleId) {
		ResultMessage result = new ResultMessage();
		List<Privilege> list = privilegeService.getPrivilegeByRoleId(roleId);
		result.getResultParm().put("privilegeList", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/*
	 * 获取系统用户的角色信息（测试所用）
	 */
	@RequestMapping(value = "/getRoleByUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getRoleByUser(Integer userId) {
		ResultMessage result = new ResultMessage();
		Role role = roleService.getRoleByUser(userId);
		result.getResultParm().put("role", role);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/*
	 * 根据用户名（登录账号）获取用户信息（测试所用）
	 */
	@RequiresPermissions("system:getByName")
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
	@RequiresPermissions("system:getUserPrivilegeCode")
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
	@RequiresPermissions("system:getRoleName")
	@RequestMapping(value = "/getRoleName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getRoleName(Integer userId) {
		ResultMessage result = new ResultMessage();
		result.setServiceResult(true);
		String roleName = systemUserService.findRoleName(userId);
		result.getResultParm().put("roleName", roleName);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

}
