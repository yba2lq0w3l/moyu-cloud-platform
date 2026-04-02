import assert from 'node:assert';
import test from 'node:test';
import { matchFactories } from '../src/modules/flexMatch.js';
import { getSupplyChainOrder } from '../src/modules/supplyChain.js';

test('Flex Match - matchFactories returns sorted factory recommendations', () => {
  const factories = matchFactories({ productType: '毛绒玩具', batchSize: 1200 });
  assert.strictEqual(factories.length, 3);
  assert.strictEqual(factories[0].name, '东莞美泰智能玩具厂');
  assert.ok(factories[0].finalScore >= factories[1].finalScore);
});

test('Supply Chain - getSupplyChainOrder returns order stages and node list', () => {
  const data = getSupplyChainOrder('#PO-2026-88401');
  assert.strictEqual(data.stages.length, 6);
  assert.strictEqual(data.nodes.length, 5);
  assert.strictEqual(data.nodes[0].name, '面料裁切');
});
