package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjcr.wechat.entity.Template;
import com.hjcr.wechat.entity.UserBill;

public interface UserBillDao  extends JpaRepository<UserBill, Integer>{

}
