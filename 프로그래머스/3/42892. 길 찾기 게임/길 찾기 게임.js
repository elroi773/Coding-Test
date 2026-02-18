function solution(nodeinfo) {
  const n = nodeinfo.length;

  // [x, y, id]
  const nodes = nodeinfo.map(([x, y], idx) => [x, y, idx + 1]);

  // y desc, x asc
  nodes.sort((a, b) => (b[1] - a[1]) || (a[0] - b[0]));

  // Tree node
  class Node {
    constructor(x, y, id) {
      this.x = x;
      this.y = y;
      this.id = id;
      this.left = null;
      this.right = null;
    }
  }

  // Insert by x (BST)
  const root = new Node(nodes[0][0], nodes[0][1], nodes[0][2]);

  function insert(root, child) {
    let cur = root;
    while (true) {
      if (child.x < cur.x) {
        if (!cur.left) {
          cur.left = child;
          return;
        }
        cur = cur.left;
      } else {
        if (!cur.right) {
          cur.right = child;
          return;
        }
        cur = cur.right;
      }
    }
  }

  for (let i = 1; i < n; i++) {
    insert(root, new Node(nodes[i][0], nodes[i][1], nodes[i][2]));
  }

  const preorder = [];
  const postorder = [];

  function dfsPre(node) {
    if (!node) return;
    preorder.push(node.id);
    dfsPre(node.left);
    dfsPre(node.right);
  }

  function dfsPost(node) {
    if (!node) return;
    dfsPost(node.left);
    dfsPost(node.right);
    postorder.push(node.id);
  }

  dfsPre(root);
  dfsPost(root);

  return [preorder, postorder];
}