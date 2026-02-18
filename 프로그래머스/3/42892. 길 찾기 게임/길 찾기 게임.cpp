#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    int x, y, id;
    Node* left;
    Node* right;
    Node(int _x, int _y, int _id) : x(_x), y(_y), id(_id), left(nullptr), right(nullptr) {}
};

static void insertNode(Node* root, Node* child) {
    Node* cur = root;
    while (true) {
        if (child->x < cur->x) {
            if (cur->left == nullptr) {
                cur->left = child;
                return;
            }
            cur = cur->left;
        } else {
            if (cur->right == nullptr) {
                cur->right = child;
                return;
            }
            cur = cur->right;
        }
    }
}

static void preorder(Node* node, vector<int>& out) {
    if (!node) return;
    out.push_back(node->id);
    preorder(node->left, out);
    preorder(node->right, out);
}

static void postorder(Node* node, vector<int>& out) {
    if (!node) return;
    postorder(node->left, out);
    postorder(node->right, out);
    out.push_back(node->id);
}

static void freeTree(Node* node) {
    if (!node) return;
    freeTree(node->left);
    freeTree(node->right);
    delete node;
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    int n = (int)nodeinfo.size();

    // (x, y, id)
    vector<tuple<int,int,int>> nodes;
    nodes.reserve(n);
    for (int i = 0; i < n; i++) {
        nodes.emplace_back(nodeinfo[i][0], nodeinfo[i][1], i + 1);
    }

    // y desc, x asc
    sort(nodes.begin(), nodes.end(), [](const auto& a, const auto& b) {
        int ax, ay, aid; tie(ax, ay, aid) = a;
        int bx, by, bid; tie(bx, by, bid) = b;
        if (ay != by) return ay > by;
        return ax < bx;
    });

    // Build tree
    int rx, ry, rid; tie(rx, ry, rid) = nodes[0];
    Node* root = new Node(rx, ry, rid);

    for (int i = 1; i < n; i++) {
        int x, y, id; tie(x, y, id) = nodes[i];
        Node* child = new Node(x, y, id);
        insertNode(root, child);
    }

    vector<int> pre, post;
    pre.reserve(n);
    post.reserve(n);

    preorder(root, pre);
    postorder(root, post);

    vector<vector<int>> answer;
    answer.push_back(pre);
    answer.push_back(post);

    freeTree(root); // optional but clean

    return answer;
}