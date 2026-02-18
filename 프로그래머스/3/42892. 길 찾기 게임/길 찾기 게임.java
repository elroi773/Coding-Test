import java.util.*;

class Solution {
    static class Node {
        int x, y, id;
        Node left, right;

        Node(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    private static void insert(Node root, Node child) {
        Node cur = root;
        while (true) {
            if (child.x < cur.x) {
                if (cur.left == null) {
                    cur.left = child;
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = child;
                    return;
                }
                cur = cur.right;
            }
        }
    }

    private static void preorder(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.id);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    private static void postorder(Node node, List<Integer> out) {
        if (node == null) return;
        postorder(node.left, out);
        postorder(node.right, out);
        out.add(node.id);
    }

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;

        int[][] nodes = new int[n][3]; // [x, y, id]
        for (int i = 0; i < n; i++) {
            nodes[i][0] = nodeinfo[i][0];
            nodes[i][1] = nodeinfo[i][1];
            nodes[i][2] = i + 1;
        }

        Arrays.sort(nodes, (a, b) -> {
            if (a[1] != b[1]) return b[1] - a[1]; // y desc
            return a[0] - b[0];                   // x asc
        });

        Node root = new Node(nodes[0][0], nodes[0][1], nodes[0][2]);
        for (int i = 1; i < n; i++) {
            insert(root, new Node(nodes[i][0], nodes[i][1], nodes[i][2]));
        }

        List<Integer> pre = new ArrayList<>(n);
        List<Integer> post = new ArrayList<>(n);

        preorder(root, pre);
        postorder(root, post);

        int[][] answer = new int[2][n];
        for (int i = 0; i < n; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        return answer;
    }
}