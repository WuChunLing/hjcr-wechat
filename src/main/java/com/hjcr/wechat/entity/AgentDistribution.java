package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="agentDistribution")
@Entity
public class AgentDistribution {

	//用户分配比例和代金券的设置
	
	
	
	public int AgentDistributionId;  //id
	public int AgentDistributionCashfirst;//一级代理的分配数额
	public int AgentDistributionCashsecond; //二级代理的分配数额
	public int AgentDistributionConfirm;   //确定选择的分配方式
	
	@GeneratedValue
	@Id
	public int getAgentDistributionId() {
		return AgentDistributionId;
	}
	public void setAgentDistributionId(int agentDistributionId) {
		AgentDistributionId = agentDistributionId;
	}
	public int getAgentDistributionCashfirst() {
		return AgentDistributionCashfirst;
	}
	public void setAgentDistributionCashfirst(int agentDistributionCashfirst) {
		AgentDistributionCashfirst = agentDistributionCashfirst;
	}
	public int getAgentDistributionCashsecond() {
		return AgentDistributionCashsecond;
	}
	public void setAgentDistributionCashsecond(int agentDistributionCashsecond) {
		AgentDistributionCashsecond = agentDistributionCashsecond;
	}
	public int getAgentDistributionConfirm() {
		return AgentDistributionConfirm;
	}
	public void setAgentDistributionConfirm(int agentDistributionConfirm) {
		AgentDistributionConfirm = agentDistributionConfirm;
	}
	
	
}
