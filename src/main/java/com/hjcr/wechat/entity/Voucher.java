package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="voucher")
@Entity
public class Voucher {

	private int voucherId;
	private int voucherMoney;
	private String voucherCord;
    private int voucherConfirm;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public int getVoucherConfirm() {
		return voucherConfirm;
	}

	public void setVoucherConfirm(int voucherConfirm) {
		this.voucherConfirm = voucherConfirm;
	}

	
	
	
}
