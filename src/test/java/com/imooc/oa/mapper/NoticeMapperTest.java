package com.imooc.oa.mapper;

import com.imooc.oa.entity.Notice;
import com.imooc.oa.utils.MyBatisUtils;
import org.junit.Test;

public class NoticeMapperTest {

    @Test
    public void insert() {
        MyBatisUtils.executeUpdate(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            mapper.insert(new Notice(2l, "测试消息"));
            return null;
        });
    }
}