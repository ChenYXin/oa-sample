package com.imooc.oa.mapper;

import com.imooc.oa.entity.ProcessFlow;

public interface ProcessFlowMapper {
    public void insert(ProcessFlow processFlow);

    public void update(ProcessFlow processFlow);

    public ProcessFlow selectByFormId(Long formId);
}
