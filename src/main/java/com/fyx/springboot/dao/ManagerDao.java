package com.fyx.springboot.dao;

import com.fyx.springboot.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ManagerDao {
    int deleteByPrimaryKey(int mgrid);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(int mgrid);

    Manager selectByName(String mgrname);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}