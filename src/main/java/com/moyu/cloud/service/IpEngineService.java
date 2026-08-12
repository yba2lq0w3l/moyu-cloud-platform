package com.moyu.cloud.service;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IpEngineService {

    public Map<String, Object> parseVectorFile(String name, String size, String type) {
        if (name == null || !name.matches("(?i).*\\.(svg|dxf|ai)$")) {
            throw new IllegalArgumentException("不支持的文件格式，仅支持 .svg, .dxf, .ai");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("filename", name);
        result.put("size", size != null ? size : "1200 x 800 px");
        result.put("layersCount", 24);
        result.put("nodesCount", 1842);
        result.put("parsedAt", Instant.now().toString());
        return result;
    }

    public List<Map<String, Object>> calculateDerivatives(double similarityThreshold, String mode) {
        double threshold = similarityThreshold > 0 ? similarityThreshold : 0.85;

        List<Map<String, Object>> baseSuggestions = Arrays.asList(
            createSuggestion("DERIV-01", "毛绒玩具（立体烫印 + 刺绣）", 0.96, "短毛绒 2mm", "基于图层 1-8 矢量轮廓，自动生成 3D 填充剪裁排版图", "立体缝制"),
            createSuggestion("DERIV-02", "陶瓷马克杯（曲面转印）", 0.89, "CMYK 高温瓷", "提取矢量核心标志，适配直径 80mm 圆柱表面转印", "曲面转印"),
            createSuggestion("DERIV-03", "丙烯亚克力立牌（UV直喷 + 激光切割）", 0.82, "高透亚克力 3mm", "自动提取外围闭合路径，加边 3mm 偏移生成闭合切割线", "激光切割")
        );

        return baseSuggestions.stream()
                .filter(item -> (double) item.get("matchRate") >= (threshold - 0.1))
                .collect(Collectors.toList());
    }

    private Map<String, Object> createSuggestion(String id, String title, double matchRate, String fabric, String description, String craftType) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", id);
        item.put("title", title);
        item.put("matchRate", matchRate);
        item.put("fabric", fabric);
        item.put("description", description);
        item.put("craftType", craftType);
        return item;
    }
}
