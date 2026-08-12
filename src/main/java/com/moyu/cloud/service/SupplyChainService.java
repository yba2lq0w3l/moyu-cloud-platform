package com.moyu.cloud.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SupplyChainService {

    public Map<String, Object> getSupplyChainOrder(String orderNo) {
        String ono = orderNo != null ? orderNo : "#PO-2026-88401";
        Map<String, Object> result = new HashMap<>();
        result.put("orderNo", ono);
        result.put("customer", "潮玩派对有限公司");
        result.put("quantity", 1200);

        List<Map<String, Object>> stages = new ArrayList<>();
        stages.add(createStage("设计矢量确认", "completed", "07-28 完成", null));
        stages.add(createStage("打样校样通过", "completed", "08-01 完成", null));
        stages.add(createStage("原材料采购", "completed", "08-03 完成", null));
        stages.add(createStage("柔性生产缝制", "in-progress", null, "82%"));
        stages.add(createStage("质检与包装", "pending", "待开始", null));
        stages.add(createStage("物流交付", "pending", "待开始", null));
        result.put("stages", stages);

        List<Map<String, Object>> nodes = new ArrayList<>();
        nodes.add(createNode("面料裁切", "已完成", "08-03 09:00", "08-03 11:30", "裁剪车间 - 组A"));
        nodes.add(createNode("电绣刺绣", "已完成", "08-03 13:00", "08-03 18:00", "刺绣车间 - 机组02"));
        nodes.add(createNode("缝制组装", "处理中", "08-04 08:00", "预计 08-05 17:00", "缝制二车间"));
        nodes.add(createNode("充棉整形", "未开始", "08-06 09:00", "预计 08-06 18:00", "后整理组"));
        nodes.add(createNode("总检包装", "未开始", "08-07 08:00", "预计 08-07 16:00", "品控部"));
        result.put("nodes", nodes);

        return result;
    }

    private Map<String, Object> createStage(String name, String status, String date, String progress) {
        Map<String, Object> s = new HashMap<>();
        s.put("name", name);
        s.put("status", status);
        if (date != null) s.put("date", date);
        if (progress != null) s.put("progress", progress);
        return s;
    }

    private Map<String, Object> createNode(String name, String status, String plan, String actual, String assignee) {
        Map<String, Object> n = new HashMap<>();
        n.put("name", name);
        n.put("status", status);
        n.put("plan", plan);
        n.put("actual", actual);
        n.put("assignee", assignee);
        return n;
    }
}
