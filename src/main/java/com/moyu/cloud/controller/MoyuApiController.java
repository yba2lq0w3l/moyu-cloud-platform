package com.moyu.cloud.controller;

import com.moyu.cloud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MoyuApiController {

    @Autowired
    private IpEngineService ipEngineService;

    @Autowired
    private FlexMatchService flexMatchService;

    @Autowired
    private SampleCollabService sampleCollabService;

    @Autowired
    private SupplyChainService supplyChainService;

    @Autowired
    private PortalOrderService portalOrderService;

    @GetMapping("/ip-engine/parse")
    public Map<String, Object> parseVector(@RequestParam(defaultValue = "artboard_ip_v2.svg") String name) {
        return ipEngineService.parseVectorFile(name, "1200 x 800 px", "image/svg+xml");
    }

    @GetMapping("/ip-engine/derivatives")
    public List<Map<String, Object>> getDerivatives(@RequestParam(defaultValue = "0.85") double threshold) {
        return ipEngineService.calculateDerivatives(threshold, "标准对称");
    }

    @GetMapping("/flex-match/factories")
    public List<Map<String, Object>> matchFactories(@RequestParam(defaultValue = "毛绒玩具") String productType) {
        return flexMatchService.matchFactories(productType, 1200, 0.35, 0.35, 0.30);
    }

    @GetMapping("/sample-collab/project")
    public Map<String, Object> getSampleProject(@RequestParam(defaultValue = "#SMP-2026-089") String projectId) {
        return sampleCollabService.getSampleProject(projectId);
    }

    @GetMapping("/supply-chain/order")
    public Map<String, Object> getSupplyChainOrder(@RequestParam(defaultValue = "#PO-2026-88401") String orderNo) {
        return supplyChainService.getSupplyChainOrder(orderNo);
    }

    @GetMapping("/portal/dashboard")
    public Map<String, Object> getPortalDashboard(@RequestParam(defaultValue = "IP版权方") String role) {
        return portalOrderService.getPortalData(role);
    }
}
