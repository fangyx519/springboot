package com.fyx.springboot.dao;

import com.fyx.springboot.entity.Action;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author fyx
 * @Time in 20:35 2020/5/24
 * @Despriction
 */
@Mapper
@Repository
public interface ActionDao {
    int add(Action action);
}
