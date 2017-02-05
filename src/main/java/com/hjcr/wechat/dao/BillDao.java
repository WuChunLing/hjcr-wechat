package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjcr.wechat.entity.Allocation;
import com.hjcr.wechat.entity.Bill;

public interface BillDao extends JpaRepository<Bill,Integer>{

}
