package com.example.jsonrpc.service;

import com.example.jsonrpc.dto.User;

/**
 * JSON-RPC服务接口
 */
public interface UserService {

    /**
     * 根据ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    User getUserById(Long userId);

    /**
     * 创建新用户
     * @param user 用户信息
     * @return 创建的用户信息
     */
    User createUser(User user);

    /**
     * 计算两数之和
     * @param a 第一个数
     * @param b 第二个数
     * @return 两数之和
     */
    Integer add(Integer a, Integer b);

    /**
     * 拼接两个字符串
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 拼接后的字符串
     */
    String concatenate(String str1, String str2);

    /**
     * 获取问候信息
     * @param name 姓名
     * @return 问候信息
     */
    String sayHello(String name);
}
