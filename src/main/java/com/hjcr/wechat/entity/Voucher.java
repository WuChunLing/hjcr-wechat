package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Cacheable
@Table(name="voucher")
@Entity
public class Voucher {

	private int voucherId;
	private int voucherMoney;
	private String voucherCord;
    private String voucherConfirm;  //卡卷url获取连接
	
	@GeneratedValue
	@Id
	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public int getVoucherMoney() {
		return voucherMoney;
	}

	public void setVoucherMoney(int voucherMoney) {
		this.voucherMoney = voucherMoney;
	}

	public String getVoucherCord() {
		return voucherCord;
	}

	public void setVoucherCord(String voucherCord) {
		this.voucherCord = voucherCord;
	}

	public String getVoucherConfirm() {
		return voucherConfirm;
	}

	public void setVoucherConfirm(String voucherConfirm) {
		this.voucherConfirm = voucherConfirm;
	}

	@Override
	public String toString() {
		return "Voucher [voucherId=" + voucherId + ", voucherMoney=" + voucherMoney + ", voucherCord=" + voucherCord
				+ ", voucherConfirm=" + voucherConfirm + "]";
	}

	
	
	
}
