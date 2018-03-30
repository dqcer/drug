package com.dqcer.drug.web.service;

import com.dqcer.drug.web.dao.UserDao;
import com.dqcer.drug.web.vo.UserVo;
import com.dqcer.drug.web.vo.common.Result;
import com.dqcer.drug.web.vo.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Description:用户业务处理类</p>
 * @author administrator
 * @date 2018/3/30 12:15
 * @param
 * @return
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public Result findUser(UserVo userVo) {
        UserEntity user = userDao.findUserByUsername(userVo.getUsername());
        boolean status = false;
        //if (null == user)
         //   return new Result("该用户不存在");
        //TODO
        //比较密码是否相等，MD5
        return new Result();
    }

}
