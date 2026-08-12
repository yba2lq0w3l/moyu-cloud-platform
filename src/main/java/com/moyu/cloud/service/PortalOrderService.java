package com.moyu.cloud.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PortalOrderService {

    public Map<String, Object> getPortalData(String role) {
        String r = role != null ? role : "IP版权方";

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalOrders", 384);
        summary.put("deliveryRate", "98.2%");
        summary.put("settlementAmount", "¥ 1,482,900");
        summary.put("pendingLicenses", 12);

        List<Map<String, Object>> orders = Arrays.asList(
            createOrder("#ORD-991", "星际喵", "毛绒玩偶 (20cm)", "2,000 件", "柔性缝制中", "跨境卖家"),
            createOrder("#ORD-992", "酷洛熊", "亚克力立牌", "5,000 件", "质检包装中", "IP授权方"),
            createOrder("#ORD-993", "幻彩兔", "马克杯", "1,000 件", "打样校验中", "设计服务商"),
            createOrder("#ORD-994", "赛博狐", "毛绒钥匙扣", "3,000 件", "原材料采购", "跨境卖家")
        );

        Map<String, Object> result = new HashMap<>();
        result.put("role", r);
        result.put("summary", summary);
        result.put("orders", orders);
        return result;
    }

    private Map<String, Object> createOrder(String orderId, String ipName, String category, String quantity, String stage, String sourceRole) {
        Map<String, Object> o = new HashMap<>();
        o.put("orderId", orderId);
        o.put("ipName", ipName);
        o.put("category", category);
        o.put("quantity", quantity);
        o.put("stage", stage);
        o.put("sourceRole", sourceRole);
        return o;
    }
}
