package com.imooc.oa.service;

import com.imooc.oa.entity.Notice;
import com.imooc.oa.mapper.NoticeMapper;
import com.imooc.oa.utils.MyBatisUtils;

import java.util.List;

public class NoticeService {
    public List<Notice> getNoticeList(Long receiverId) {
        List<Notice> list = (List<Notice>) MyBatisUtils.executeQuery(sqlSession -> {
            NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
            return noticeMapper.selectByReceiverId(receiverId);
        });
        return list;
    }
}
