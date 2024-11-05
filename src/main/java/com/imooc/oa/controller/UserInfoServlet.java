package com.imooc.oa.controller;

import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.Node;
import com.imooc.oa.service.DepartmentService;
import com.imooc.oa.service.EmployeeService;
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
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String eid = request.getParameter("eid");
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
        Employee employee = employeeService.selectById(Long.parseLong(eid));
        Department department = departmentService.selectById(employee.getDepartmentId());

        String json = new ResponseUtils()
                .put("nodeList", treeLis)
                .put("employee", employee)
                .put("department", department)
                .toJsonString();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
