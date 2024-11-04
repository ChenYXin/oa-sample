package com.imooc.oa.controller;

import com.imooc.oa.entity.Node;
import com.imooc.oa.service.RbacService;
import com.imooc.oa.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/user_info")
public class UserInfoServlet extends HttpServlet {
    private RbacService rbacService = new RbacService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        List<Node> list = rbacService.selectNodeByUserId(Long.parseLong(uid));
        List<Map> treeLis = new ArrayList<>();
        Map module = null;
        for (Node node : list) {
            if (node.getNodeType() == 1) {
                module = new LinkedHashMap();
                module.put("node", node);
                module.put("children", new ArrayList());
                treeLis.add(module);
            } else if (node.getNodeType() == 2) {
                List children = (List) module.get("children");
                children.add(node);
            }
        }
        String json = new ResponseUtils().put("nodeList", treeLis).toJsonString();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
