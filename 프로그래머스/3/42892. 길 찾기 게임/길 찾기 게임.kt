class Solution {

    private class Node(val x: Int, val y: Int, val id: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    private fun insert(root: Node, child: Node) {
        var cur: Node? = root
        while (cur != null) {
            if (child.x < cur.x) {
                if (cur.left == null) {
                    cur.left = child
                    return
                }
                cur = cur.left
            } else {
                if (cur.right == null) {
                    cur.right = child
                    return
                }
                cur = cur.right
            }
        }
    }

    private fun preorder(node: Node?, out: MutableList<Int>) {
        if (node == null) return
        out.add(node.id)
        preorder(node.left, out)
        preorder(node.right, out)
    }

    private fun postorder(node: Node?, out: MutableList<Int>) {
        if (node == null) return
        postorder(node.left, out)
        postorder(node.right, out)
        out.add(node.id)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val n = nodeinfo.size

        // nodes: intArrayOf(x, y, id)
        val nodes = ArrayList<IntArray>(n)
        for (i in 0 until n) {
            nodes.add(intArrayOf(nodeinfo[i][0], nodeinfo[i][1], i + 1))
        }

        // y desc, x asc
        nodes.sortWith(compareByDescending<IntArray> { it[1] }.thenBy { it[0] })

        // build tree
        val root = Node(nodes[0][0], nodes[0][1], nodes[0][2])
        for (i in 1 until n) {
            insert(root, Node(nodes[i][0], nodes[i][1], nodes[i][2]))
        }

        val pre = ArrayList<Int>(n)
        val post = ArrayList<Int>(n)

        preorder(root, pre)
        postorder(root, post)

        val answer = Array(2) { IntArray(n) }
        for (i in 0 until n) {
            answer[0][i] = pre[i]
            answer[1][i] = post[i]
        }
        return answer
    }
}