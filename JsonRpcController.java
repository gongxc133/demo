package com.example.jsonrpc.controller;

import com.example.jsonrpc.service.UserService;
import com.googlecode.jsonrpc4j.JsonRpcMultiServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSON-RPC 2.0 Controller
 * 处理JSON-RPC请求
 */
@Slf4j
@RestController
@RequestMapping("/api/jsonrpc")
public class JsonRpcController {

    private final JsonRpcMultiServer jsonRpcServer;

    @Autowired
    public JsonRpcController(UserService userService) {
        // 使用JsonRpcMultiServer支持带前缀的方法名
        this.jsonRpcServer = new JsonRpcMultiServer();
        this.jsonRpcServer.addService("UserService", userService, UserService.class);
        log.info("JSON-RPC服务已注册: UserService (支持UserService.methodName格式)");
    }

    /**
     * 处理JSON-RPC 2.0请求
     */
    @PostMapping
    public void handleJsonRpcRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        log.info("收到JSON-RPC请求");
        // jsonRpcServer会从request中读取JSON-RPC请求并写入response
        jsonRpcServer.handle(request, response);
        log.info("JSON-RPC响应已生成");
    }
}
