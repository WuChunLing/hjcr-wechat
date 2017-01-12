package com.hjcr.wechat.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjcr.wechat.entity.SystemUser;
import com.hjcr.wechat.service.SystemUserService;


/**
 * shiro的认证授权域
 * @author yuqs
 * @since 0.1
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm {
	
	private final Logger logger = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);
	
	@Autowired
	private SystemUserService systemUserService;

	/**
	 * 获取当前认证实体的授权信息（授权包括：角色、权限）
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录的用户名
		ShiroPrincipal subject = (ShiroPrincipal)super.getAvailablePrincipal(principals);
		String username = subject.getUsername();
		Integer userId = subject.getId();
		
		try {
			if(!subject.isAuthorized()) {
				//根据用户名称，获取该用户所有的权限列表
				List<String> authorities = systemUserService.findPrivilegeCode(userId);
				String role = systemUserService.findRoleName(userId);
				subject.setAuthorities(authorities);
				subject.setRole(role);
				subject.setAuthorized(true);
				logger.info("用户【" + username + "】授权初始化成功......");
				logger.info("用户【" + username + "】 角色列表为：" + subject.getRole());
				logger.info("用户【" + username + "】 权限列表为：" + subject.getAuthorities());
			}
		} catch(RuntimeException e) {
			throw new AuthorizationException("用户【" + username + "】授权失败");
		}
		//给当前用户设置权限
		info.addStringPermissions(subject.getAuthorities());
		info.addRole(subject.getRole());
		return info;
	}
	
	/**
	 * 根据认证方式（如表单）获取用户名称、密码
	 */                          
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		SystemUser user = null;
		try {
			user = systemUserService.findOneByName(username);
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.info("获取用户失败\n" + ex.getMessage());
		}
		if (user == null) {
		    throw new UnknownAccountException("-------用户不存在--------- ");
		}
		logger.info("用户【" + username + "】登录成功");
		ShiroPrincipal subject = new ShiroPrincipal(user);
//		List<String> authorities = systemUserService.findPrivilegeCode(user.getId());
//		String role = systemUserService.findRoleName(user.getId());
//		subject.setAuthorities(authorities);
//		subject.setRole(role);
//		subject.setAuthorized(true);
//		logger.info("用户【" + username + "】 角色列表为：" + subject.getRole());
//		logger.info("用户【" + username + "】 权限列表为：" + subject.getAuthorities());
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(subject, user.getPassword(), getName());
		return info;
	}
	
	 @Override  
	    public String getName() {  
	        return getClass().getName();  
	    }}
