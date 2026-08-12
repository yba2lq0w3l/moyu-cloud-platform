package com.moyu.cloud.service;

import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SampleCollabService {

    public Map<String, Object> getSampleProject(String projectId) {
        String pid = projectId != null ? projectId : "#SMP-2026-089";
        Map<String, Object> result = new HashMap<>();
        result.put("projectId", pid);
        result.put("title", "毛绒小熊 IP 衍生打样");
        result.put("status", "三阶段打样校样中");
        result.put("experts", Arrays.asList("张工（首席样品师）", "李设计师"));

        List<Map<String, Object>> comments = new ArrayList<>();
        comments.add(createComment(1, "样品师-张工", "10:15", "头部充棉饱满度需增加 15g，眼珠扣固定位偏下 2mm。"));
        comments.add(createComment(2, "设计师-王薇", "11:30", "已确认修正，请同步更新样板缝线定位标记。"));
        comments.add(createComment(3, "QC质量官", "14:05", "面料抗拉撕裂测试通过，防火等级达到 EN71 标准。"));

        result.put("comments", comments);
        return result;
    }

    public Map<String, Object> addComment(Map<String, Object> project, String author, String text) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> comments = (List<Map<String, Object>>) project.get("comments");
        int nextId = comments != null ? comments.size() + 1 : 1;
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        Map<String, Object> newComment = createComment(nextId, author, currentTime, text);
        if (comments != null) {
            comments.add(newComment);
        }
        return newComment;
    }

    private Map<String, Object> createComment(int id, String author, String time, String text) {
        Map<String, Object> c = new HashMap<>();
        c.put("id", id);
        c.put("author", author);
        c.put("time", time);
        c.put("text", text);
        return c;
    }
}
