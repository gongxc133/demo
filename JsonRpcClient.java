package com.example.jsonrpc.client;

import com.example.jsonrpc.dto.User;
import com.example.jsonrpc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON-RPC客户端示例
 */
@Slf4j
public class JsonRpcClient {

    private static final String SERVER_URL = "http://localhost:8080/api/jsonrpc";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            // 创建JSON-RPC客户端
            JsonRpcHttpClient client = new JsonRpcHttpClient(new java.net.URL(SERVER_URL));

            log.info("===========================================");
            log.info("开始测试JSON-RPC服务");
            log.info("===========================================\n");

            // 测试1: 计算两数之和
            testAdd(client);

            // 测试2: 拼接字符串
            testConcatenate(client);

            // 测试3: 问候
            testSayHello(client);

            // 测试4: 获取用户信息
            testGetUserById(client);

            // 测试5: 创建新用户
            testCreateUser(client);

            log.info("\n===========================================");
            log.info("所有测试完成");
            log.info("===========================================");

        } catch (Throwable e) {
            log.error("JSON-RPC调用失败", e);
        }
    }

    /**
     * 测试加法
     */
    private static void testAdd(JsonRpcHttpClient client) throws Throwable {
        log.info("\n[测试1] 计算两数之和");
        Integer result = client.invoke("UserService.add", new Object[]{10, 20}, Integer.class);
        log.info("结果: 10 + 20 = {}", result);
    }

    /**
     * 测试字符串拼接
     */
    private static void testConcatenate(JsonRpcHttpClient client) throws Throwable {
        log.info("\n[测试2] 拼接字符串");
        String result = client.invoke("UserService.concatenate", new Object[]{"Hello", " World"}, String.class);
        log.info("结果: \"Hello\" + \" World\" = \"{}\"", result);
    }

    /**
     * 测试问候
     */
    private static void testSayHello(JsonRpcHttpClient client) throws Throwable {
        log.info("\n[测试3] 问候");
        String greeting = client.invoke("UserService.sayHello", new Object[]{"张三"}, String.class);
        log.info("结果: {}", greeting);
    }

    /**
     * 测试获取用户信息
     */
    private static void testGetUserById(JsonRpcHttpClient client) throws Throwable {
        log.info("\n[测试4] 获取用户信息");
        User user = client.invoke("UserService.getUserById", new Object[]{1L}, User.class);
        log.info("结果: {}", objectMapper.writeValueAsString(user));
    }

    /**
     * 测试创建新用户
     */
    private static void testCreateUser(JsonRpcHttpClient client) throws Throwable {
        log.info("\n[测试5] 创建新用户");
        User newUser = User.builder()
                .name("王五")
                .email("wangwu@example.com")
                .age(28)
                .build();
        User createdUser = client.invoke("UserService.createUser", new Object[]{newUser}, User.class);
        log.info("结果: {}", objectMapper.writeValueAsString(createdUser));
    }
}
