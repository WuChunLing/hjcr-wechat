/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.hjcr.wechat.shiro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hjcr.wechat.entity.SystemUser;



/**
 * 自定义认证主体
 * @author yuqs
 * @since 0.1
 */
public class ShiroPrincipal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1428196040744555722L;
	//用户对象
	private SystemUser user;
	//用户权限列表
	private Set<String> authorities = new HashSet<String>();
	//用户角色列表
	private String role;
	//是否已授权。如果已授权，则不需要再从数据库中获取权限信息，减少数据库访问
	//这里会导致修改权限时，需要重新登录方可有效
	private boolean isAuthorized = false;
	
	/**
	 * 构造函数，参数为User对象
	 * 根据User对象属性，赋值给Principal相应的属性上
	 * @param user
	 */
	public ShiroPrincipal(SystemUser user) {
		this.user = user;
	}
	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities.addAll(authorities);
	}
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isAuthorized() {
		return isAuthorized;
	}
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public String getUsername() {
		return this.user.getUsername();
	}
	public Integer getId() {
		return this.user.getId();
	}

	@Override
	public String toString() {
		return "ShiroPrincipal [user=" + user + ", authorities=" + authorities + ", role=" + role + ", isAuthorized="
				+ isAuthorized + "]";
	}
	



	
}
