/**
 * @Feature 三端门户与协同订单
 * @Version v0.5.0
 */

export function getPortalData(role = 'IP版权方') {
  const summary = {
    totalOrders: 384,
    deliveryRate: '98.2%',
    settlementAmount: '¥ 1,482,900',
    pendingLicenses: 12
  };

  const orders = [
    { orderId: '#ORD-991', ipName: '星际喵', category: '毛绒玩偶 (20cm)', quantity: '2,000 件', stage: '柔性缝制中', sourceRole: '跨境卖家' },
    { orderId: '#ORD-992', ipName: '酷洛熊', category: '亚克力立牌', quantity: '5,000 件', stage: '质检包装中', sourceRole: 'IP授权方' },
    { orderId: '#ORD-993', ipName: '幻彩兔', category: '马克杯', quantity: '1,000 件', stage: '打样校验中', sourceRole: '设计服务商' },
    { orderId: '#ORD-994', ipName: '赛博狐', category: '毛绒钥匙扣', quantity: '3,000 件', stage: '原材料采购', sourceRole: '跨境卖家' }
  ];

  return { role, summary, orders };
}
