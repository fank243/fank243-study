package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.UserRepository;
import com.fank243.springboot.app.service.base.BaseService;
import com.fank243.springboot.core.consts.DictConsts;
import com.fank243.springboot.core.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 用户
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:41:32
 */
@Service
public class UserService extends BaseService<User> {

    @Resource
    private UserRepository repository;

    public User findById(Long userId) {
        Optional<User> optional = repository.findById(userId);
        return optional.orElse(null);
    }

    public User findAvailableById(long userId) {
        return repository.findByIdAndStatus(userId, DictConsts.USER_STATUS_DISABLE);
    }
}
