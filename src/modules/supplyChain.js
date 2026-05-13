/**
 * @Feature 供应链全流程追踪
 * @Version v0.4.0
 */

export function getSupplyChainOrder(orderNo = '#PO-2026-88401') {
  return {
    orderNo,
    customer: '潮玩派对有限公司',
    quantity: 1200,
    stages: [
      { name: '设计矢量确认', status: 'completed', date: '07-28 完成' },
      { name: '打样校样通过', status: 'completed', date: '08-01 完成' },
      { name: '原材料采购', status: 'completed', date: '08-03 完成' },
      { name: '柔性生产缝制', status: 'in-progress', progress: '82%' },
      { name: '质检与包装', status: 'pending', date: '待开始' },
      { name: '物流交付', status: 'pending', date: '待开始' }
    ],
    nodes: [
      { name: '面料裁切', status: '已完成', plan: '08-03 09:00', actual: '08-03 11:30', assignee: '裁剪车间 - 组A' },
      { name: '电绣刺绣', status: '已完成', plan: '08-03 13:00', actual: '08-03 18:00', assignee: '刺绣车间 - 机组02' },
      { name: '缝制组装', status: '处理中', plan: '08-04 08:00', actual: '预计 08-05 17:00', assignee: '缝制二车间' },
      { name: '充棉整形', status: '未开始', plan: '08-06 09:00', actual: '预计 08-06 18:00', assignee: '后整理组' },
      { name: '总检包装', status: '未开始', plan: '08-07 08:00', actual: '预计 08-07 16:00', assignee: '品控部' }
    ]
  };
}
