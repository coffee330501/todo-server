package io.github.coffee330501.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.module.entity.User;
import io.github.coffee330501.dao.mapper.UserMapper;
import io.github.coffee330501.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
