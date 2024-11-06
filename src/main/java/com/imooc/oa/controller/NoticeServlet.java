package com.imooc.oa.controller;

import com.imooc.oa.entity.Notice;
import com.imooc.oa.service.NoticeService;
import com.imooc.oa.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/notice/list")
public class NoticeServlet extends HttpServlet {
    private NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("eid");
        ResponseUtils resp = null;
        try {
            List<Notice> noticeList = noticeService.getNoticeList(Long.parseLong(employeeId));
            resp = new ResponseUtils().put("list", noticeList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(resp.toJsonString());
    }
}
