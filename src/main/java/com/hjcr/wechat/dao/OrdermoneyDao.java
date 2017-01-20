package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjcr.wechat.entity.Ordermoney;

public interface OrdermoneyDao extends JpaRepository<Ordermoney, Integer> {

}
