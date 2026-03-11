/**
 * @Feature 打样协同与在线校样
 * @Version v0.3.0
 */

export function getSampleProject(projectId = '#SMP-2026-089') {
  return {
    projectId,
    title: '毛绒小熊 IP 衍生打样',
    status: '三阶段打样校样中',
    experts: ['张工（首席样品师）', '李设计师'],
    comments: [
      { id: 1, author: '样品师-张工', time: '10:15', text: '头部充棉饱满度需增加 15g，眼珠扣固定位偏下 2mm。' },
      { id: 2, author: '设计师-王薇', time: '11:30', text: '已确认修正，请同步更新样板缝线定位标记。' },
      { id: 3, author: 'QC质量官', time: '14:05', text: '面料抗拉撕裂测试通过，防火等级达到 EN71 标准。' }
    ]
  };
}

export function addComment(project, author, text) {
  const newComment = {
    id: project.comments.length + 1,
    author,
    time: new Date().toLocaleTimeString('zh-CN', { hour: '2-digit', minute: '2-digit' }),
    text
  };
  project.comments.push(newComment);
  return newComment;
}
