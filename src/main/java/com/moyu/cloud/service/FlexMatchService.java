package com.moyu.cloud.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlexMatchService {

    public List<Map<String, Object>> matchFactories(String productType, Integer batchSize, Double weightDelivery, Double weightCost, Double weightQuality) {
        double wDelivery = weightDelivery != null ? weightDelivery : 0.35;
        double wCost = weightCost != null ? weightCost : 0.35;
        double wQuality = weightQuality != null ? weightQuality : 0.30;
        double totalWeight = wDelivery + wCost + wQuality;

        List<Map<String, Object>> factories = new ArrayList<>();
        factories.add(createFactory("FAC-01", "东莞美泰智能玩具厂", 0.985, "< 2小时", 18.50, "8月12日 完工", "42%", "自动化激光裁剪机、智能绣花机", "ISO9001 / Sedex 认证"));
        factories.add(createFactory("FAC-02", "深圳墨云柔性快反应中心", 0.942, "< 1小时", 21.00, "8月09日 完工", "65%", "3D数码直喷、自动充棉机", "品牌级打样实验室"));
        factories.add(createFactory("FAC-03", "惠州艺达工艺品有限公司", 0.860, "< 4小时", 16.20, "8月18日 完工", "20%", "标准缝制流水线", "BSCI 认证"));

        return factories.stream().map(fac -> {
            double baseMatchRate = (double) fac.get("baseMatchRate");
            double weightedScore = Math.round(baseMatchRate * 100 * totalWeight * 10.0) / 10.0;
            fac.put("finalScore", weightedScore);
            return fac;
        }).sorted((a, b) -> Double.compare((double) b.get("finalScore"), (double) a.get("finalScore")))
          .collect(Collectors.toList());
    }

    private Map<String, Object> createFactory(String id, String name, double baseMatchRate, String responseTime, double unitPrice, String completionDate, String idleCapacity, String equipment, String certifications) {
        Map<String, Object> fac = new HashMap<>();
        fac.put("id", id);
        fac.put("name", name);
        fac.put("baseMatchRate", baseMatchRate);
        fac.put("responseTime", responseTime);
        fac.put("unitPrice", unitPrice);
        fac.put("completionDate", completionDate);
        fac.put("idleCapacity", idleCapacity);
        fac.put("equipment", equipment);
        fac.put("certifications", certifications);
        return fac;
    }
}
