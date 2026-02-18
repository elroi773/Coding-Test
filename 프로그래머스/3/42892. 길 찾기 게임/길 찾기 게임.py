import sys
sys.setrecursionlimit(10**6)

class Node:
    __slots__ = ("x", "y", "id", "left", "right")
    def __init__(self, x, y, _id):
        self.x = x
        self.y = y
        self.id = _id
        self.left = None
        self.right = None

def solution(nodeinfo):
    n = len(nodeinfo)

    # (x, y, id)
    nodes = [(x, y, i + 1) for i, (x, y) in enumerate(nodeinfo)]
    # y desc, x asc
    nodes.sort(key=lambda t: (-t[1], t[0]))

    # build BST by x
    rx, ry, rid = nodes[0]
    root = Node(rx, ry, rid)

    for x, y, _id in nodes[1:]:
        cur = root
        while True:
            if x < cur.x:
                if cur.left is None:
                    cur.left = Node(x, y, _id)
                    break
                cur = cur.left
            else:
                if cur.right is None:
                    cur.right = Node(x, y, _id)
                    break
                cur = cur.right

    pre, post = [], []

    def dfs_pre(node):
        if not node:
            return
        pre.append(node.id)
        dfs_pre(node.left)
        dfs_pre(node.right)

    def dfs_post(node):
        if not node:
            return
        dfs_post(node.left)
        dfs_post(node.right)
        post.append(node.id)

    dfs_pre(root)
    dfs_post(root)

    return [pre, post]