import { parseVectorFile, calculateDerivatives } from './modules/ipEngine.js';
import { matchFactories } from './modules/flexMatch.js';
import { getSampleProject, addComment } from './modules/sampleCollab.js';
import { getSupplyChainOrder } from './modules/supplyChain.js';
import { getPortalData } from './modules/portalOrder.js';

document.addEventListener('DOMContentLoaded', () => {
  // Navigation Tab Switcher
  const navItems = document.querySelectorAll('.nav-item');
  const viewPanels = document.querySelectorAll('.view-panel');
  const currentTitle = document.getElementById('current-title');

  navItems.forEach(item => {
    item.addEventListener('click', () => {
      const targetView = item.getAttribute('data-view');
      const viewName = item.querySelector('.nav-label').textContent;

      navItems.forEach(nav => nav.classList.remove('active'));
      viewPanels.forEach(panel => panel.classList.remove('active'));

      item.classList.add('active');
      document.getElementById(`view-${targetView}`).classList.add('active');
      currentTitle.textContent = viewName;
    });
  });

  // Render IP Engine derivatives
  const deriveBtn = document.getElementById('btn-derive');
  if (deriveBtn) {
    deriveBtn.addEventListener('click', () => {
      const threshold = parseFloat(document.getElementById('similarity-slider').value) / 100;
      const suggestions = calculateDerivatives({}, { similarityThreshold: threshold });
      renderDerivatives(suggestions);
    });
  }

  // Render Flex Match factories
  const matchBtn = document.getElementById('btn-recalculate-match');
  if (matchBtn) {
    matchBtn.addEventListener('click', () => {
      const delivery = parseFloat(document.getElementById('weight-delivery').value) / 100;
      const cost = parseFloat(document.getElementById('weight-cost').value) / 100;
      const quality = parseFloat(document.getElementById('weight-quality').value) / 100;

      const factories = matchFactories({ weights: { delivery, cost, quality } });
      renderFactories(factories);
    });
  }

  // Add Comment for Sample Collab
  const sendCommentBtn = document.getElementById('btn-send-comment');
  if (sendCommentBtn) {
    sendCommentBtn.addEventListener('click', () => {
      const input = document.getElementById('comment-input');
      if (input.value.trim()) {
        const project = getSampleProject();
        addComment(project, '专家工程师', input.value.trim());
        renderComments(project.comments);
        input.value = '';
      }
    });
  }

  // Initial renders
  renderDerivatives(calculateDerivatives({}, { similarityThreshold: 0.85 }));
  renderFactories(matchFactories());
  renderComments(getSampleProject().comments);
  renderSupplyChain(getSupplyChainOrder());
  renderPortal(getPortalData());
});

function renderDerivatives(items) {
  const container = document.getElementById('deriv-container');
  if (!container) return;
  container.innerHTML = items.map(item => `
    <div class="deriv-card">
      <div>
        <span class="match-badge">契合度：${Math.round(item.matchRate * 100)}%</span>
        <h4 style="margin-bottom:8px;font-size:1rem;">${item.title}</h4>
        <p style="font-size:0.8rem;color:#64748b;margin-bottom:8px;">${item.description}</p>
        <p style="font-size:0.8rem;color:#3b82f6;">材质与工艺：${item.fabric}</p>
      </div>
      <button class="btn btn-primary" style="margin-top:12px;" onclick="alert('已成功生成 [${item.title}] 工艺图纸！')">生成工艺单</button>
    </div>
  `).join('');
}

function renderFactories(factories) {
  const tbody = document.getElementById('factory-tbody');
  if (!tbody) return;
  tbody.innerHTML = factories.map(f => `
    <tr>
      <td><strong>${f.name}</strong><br/><span style="font-size:0.75rem;color:#64748b;">${f.equipment}</span></td>
      <td><span style="color:#16a34a;font-weight:bold;">${f.baseMatchRate * 100}%</span></td>
      <td>${f.responseTime}</td>
      <td>¥ ${f.unitPrice.toFixed(2)} / 件</td>
      <td>${f.completionDate}</td>
      <td><button class="btn btn-secondary" onclick="alert('已成功选择 ${f.name} 作为打样派单工厂！')">选择工厂</button></td>
    </tr>
  `).join('');
}

function renderComments(comments) {
  const container = document.getElementById('comments-list');
  if (!container) return;
  container.innerHTML = comments.map(c => `
    <div style="background:#f8fafc;padding:10px;border-radius:6px;margin-bottom:8px;border:1px solid #e2e8f0;">
      <div style="display:flex;justify-content:space-between;font-size:0.75rem;color:#64748b;margin-bottom:4px;">
        <strong>${c.author}</strong><span>${c.time}</span>
      </div>
      <p style="font-size:0.85rem;">${c.text}</p>
    </div>
  `).join('');
}

function renderSupplyChain(data) {
  const container = document.getElementById('supply-chain-nodes');
  if (!container) return;
  container.innerHTML = data.nodes.map(n => `
    <tr>
      <td><strong>${n.name}</strong></td>
      <td><span class="task-tag ${n.status === '已完成' ? 'tag-high' : n.status === '处理中' ? 'tag-mid' : 'tag-low'}">${n.status}</span></td>
      <td>${n.plan}</td>
      <td>${n.actual}</td>
      <td>${n.assignee}</td>
    </tr>
  `).join('');
}

function renderPortal(data) {
  const tbody = document.getElementById('portal-orders-tbody');
  if (!tbody) return;
  tbody.innerHTML = data.orders.map(o => `
    <tr>
      <td><strong>${o.orderId}</strong></td>
      <td>${o.ipName}</td>
      <td>${o.category}</td>
      <td>${o.quantity}</td>
      <td><span style="color:#2563eb;font-weight:600;">${o.stage}</span></td>
      <td>${o.sourceRole}</td>
      <td><button class="btn btn-secondary" onclick="alert('查看订单 ${o.orderId} 详细授权协同链条')">查看详情</button></td>
    </tr>
  `).join('');
}
