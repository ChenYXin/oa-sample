package com.imooc.oa.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        //接收用户输入
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用业务逻辑
        Map result = new LinkedHashMap<>();
        try {
            User user = userService.checkLogin(username, password);
            //处理结果编码，0代表处理成功，非0代表处理失败
            result.put("code", "0");
            result.put("message", "success");
            Map data = new LinkedHashMap();
            data.put("user", user);
            result.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }
        //返回json结果
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(result);
        resp.getWriter().println(json);
    }
}