package com.example.jsonrpc.service;

import com.example.jsonrpc.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * JSON-RPC服务实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final Map<Long, User> userStore = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public UserServiceImpl() {
        // 初始化一些测试数据
        User user1 = User.builder()
                .id(1L)
                .name("张三")
                .email("zhangsan@example.com")
                .age(25)
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("李四")
                .email("lisi@example.com")
                .age(30)
                .build();
        userStore.put(1L, user1);
        userStore.put(2L, user2);
        idGenerator.set(3);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("获取用户信息，用户ID: {}", userId);
        User user = userStore.get(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在: " + userId);
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        log.info("创建新用户: {}", user);
        Long id = idGenerator.getAndIncrement();
        user.setId(id);
        userStore.put(id, user);
        return user;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        log.info("计算两数之和: {} + {}", a, b);
        return a + b;
    }

    @Override
    public String concatenate(String str1, String str2) {
        log.info("拼接字符串: {} + {}", str1, str2);
        return str1 + str2;
    }

    @Override
    public String sayHello(String name) {
        log.info("问候: {}", name);
        return "你好, " + name + "!";
    }
}
