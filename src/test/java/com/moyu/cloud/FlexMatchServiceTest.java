package com.moyu.cloud;

import com.moyu.cloud.service.FlexMatchService;
import com.moyu.cloud.service.SupplyChainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FlexMatchServiceTest {

    private FlexMatchService flexMatchService;
    private SupplyChainService supplyChainService;

    @BeforeEach
    void setUp() {
        flexMatchService = new FlexMatchService();
        supplyChainService = new SupplyChainService();
    }

    @Test
    @DisplayName("Flex Match - matchFactories returns sorted factory recommendations")
    void testMatchFactories() {
        List<Map<String, Object>> factories = flexMatchService.matchFactories("毛绒玩具", 1200, 0.35, 0.35, 0.30);
        assertEquals(3, factories.size());
        assertEquals("东莞美泰智能玩具厂", factories.get(0).get("name"));
        
        double score0 = (double) factories.get(0).get("finalScore");
        double score1 = (double) factories.get(1).get("finalScore");
        assertTrue(score0 >= score1);
    }

    @Test
    @DisplayName("Supply Chain - getSupplyChainOrder returns order stages and node list")
    void testGetSupplyChainOrder() {
        Map<String, Object> data = supplyChainService.getSupplyChainOrder("#PO-2026-88401");
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> stages = (List<Map<String, Object>>) data.get("stages");
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> nodes = (List<Map<String, Object>>) data.get("nodes");

        assertEquals(6, stages.size());
        assertEquals(5, nodes.size());
        assertEquals("面料裁切", nodes.get(0).get("name"));
    }
}
