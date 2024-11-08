package com.imooc.oa.service;

import com.imooc.oa.entity.Node;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RbacServiceTest {

    private RbacService rbacService = new RbacService();

    @Test
    public void selectNodeByUserId() {
        List<Node> list = rbacService.selectNodeByUserId(3l);
        for (Node node : list) {
            System.out.println(node.getNodeName());
        }
    }
}