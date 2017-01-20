package com.hjcr.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjcr.wechat.entity.Allocation;

public interface AllocationDao extends JpaRepository<Allocation,Integer>{

}
