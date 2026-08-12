package com.moyu.cloud;

import com.moyu.cloud.service.IpEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IpEngineServiceTest {

    private IpEngineService ipEngineService;

    @BeforeEach
    void setUp() {
        ipEngineService = new IpEngineService();
    }

    @Test
    @DisplayName("IP Engine - parseVectorFile should parse svg correctly")
    void testParseVectorFileSvg() {
        Map<String, Object> result = ipEngineService.parseVectorFile("artboard_ip_v2.svg", "1200 x 800 px", "image/svg+xml");
        assertEquals("artboard_ip_v2.svg", result.get("filename"));
        assertEquals(24, result.get("layersCount"));
        assertEquals(1842, result.get("nodesCount"));
        assertNotNull(result.get("parsedAt"));
    }

    @Test
    @DisplayName("IP Engine - parseVectorFile should reject invalid file extensions")
    void testParseVectorFileInvalidExtension() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ipEngineService.parseVectorFile("doc.pdf", "100KB", "application/pdf");
        });
        assertTrue(exception.getMessage().contains("不支持的文件格式"));
    }

    @Test
    @DisplayName("IP Engine - calculateDerivatives returns matching suggestions")
    void testCalculateDerivatives() {
        List<Map<String, Object>> suggestions = ipEngineService.calculateDerivatives(0.85, "标准对称");
        assertEquals(3, suggestions.size());
        assertEquals("立体缝制", suggestions.get(0).get("craftType"));
    }
}
