package com.imooc.oa.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.imooc.oa.entity.LeaveForm;
import com.imooc.oa.utils.MyBatisUtils;
import org.junit.Test;

public class LeaveFormMapperTest {

    @Test
    public void insert() {
        MyBatisUtils.executeUpdate(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(4l); //员工编号
            form.setFormType(1); //事假
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;//起始时间
            Date endTime = null;//结束时间
            try {
                startTime = sdf.parse("2020-03-25 08:00:00");
                endTime = sdf.parse("2020-04-01 18:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("回家探亲");//请假事由
            form.setCreateTime(new Date());//创建时间
            form.setState("processing");//当前状态
            mapper.insert(form);
            return null;
        });
    }
}