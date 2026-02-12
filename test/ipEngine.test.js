import assert from 'node:assert';
import test from 'node:test';
import { parseVectorFile, calculateDerivatives } from '../src/modules/ipEngine.js';

test('IP Engine - parseVectorFile should parse svg correctly', () => {
  const result = parseVectorFile({ name: 'artboard_ip_v2.svg', size: '1200 x 800 px', type: 'image/svg+xml' });
  assert.strictEqual(result.filename, 'artboard_ip_v2.svg');
  assert.strictEqual(result.layersCount, 24);
  assert.strictEqual(result.nodesCount, 1842);
});

test('IP Engine - parseVectorFile should reject invalid file extensions', () => {
  assert.throws(() => {
    parseVectorFile({ name: 'doc.pdf' });
  }, /不支持的文件格式/);
});

test('IP Engine - calculateDerivatives returns matching suggestions', () => {
  const suggestions = calculateDerivatives({}, { similarityThreshold: 0.85 });
  assert.strictEqual(suggestions.length, 3);
  assert.strictEqual(suggestions[0].craftType, '立体缝制');
});
