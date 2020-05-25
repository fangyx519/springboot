package com.fyx.springboot.aop;

import com.fyx.springboot.dao.ActionDao;
import com.fyx.springboot.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author fyx
 * @Time in 20:33 2020/5/24
 * @Despriction
 */
@Service
public class ActionService {
    @Autowired
    ActionDao actionDao;

    int add(Action action){
        return actionDao.add(action);
    }
}
