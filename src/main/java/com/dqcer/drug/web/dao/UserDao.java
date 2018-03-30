package com.dqcer.drug.web.dao;

import com.dqcer.drug.web.vo.entity.UserEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>Description:操作用户接口类</p>
 * @author administrator
 * @date 2018/3/30 12:16
 * @param
 * @return
 */
//@CacheConfig(cacheNames = "UserDao")
@Repository

public interface UserDao {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserEntity findUserByUsername(String username);
}
