import Foundation

struct Node: Hashable {
    let x: Int
    let y: Int
    let a: Int   // 0: pillar, 1: beam
}

func solution(_ n: Int, _ build_frame: [[Int]]) -> [[Int]] {
    var S = Set<Node>()

    func canPlace(_ x: Int, _ y: Int, _ a: Int) -> Bool {
        if a == 0 { // pillar
            if y == 0 { return true } // on ground
            if S.contains(Node(x: x, y: y - 1, a: 0)) { return true } // on another pillar
            if S.contains(Node(x: x, y: y, a: 1)) { return true }     // on right beam end
            if S.contains(Node(x: x - 1, y: y, a: 1)) { return true } // on left beam end
            return false
        } else { // beam
            if S.contains(Node(x: x, y: y - 1, a: 0)) { return true }       // left end on pillar
            if S.contains(Node(x: x + 1, y: y - 1, a: 0)) { return true }   // right end on pillar
            if S.contains(Node(x: x - 1, y: y, a: 1)) &&
               S.contains(Node(x: x + 1, y: y, a: 1)) { return true }       // connected both sides
            return false
        }
    }

    func validAll() -> Bool {
        for node in S {
            if !canPlace(node.x, node.y, node.a) { return false }
        }
        return true
    }

    for cmd in build_frame {
        let x = cmd[0], y = cmd[1], a = cmd[2], b = cmd[3]
        let cur = Node(x: x, y: y, a: a)

        if b == 1 { // install
            S.insert(cur)
            if !validAll() { S.remove(cur) } // rollback
        } else { // delete
            S.remove(cur)
            if !validAll() { S.insert(cur) } // rollback
        }
    }

    return S
        .map { [$0.x, $0.y, $0.a] }
        .sorted {
            if $0[0] != $1[0] { return $0[0] < $1[0] }
            if $0[1] != $1[1] { return $0[1] < $1[1] }
            return $0[2] < $1[2] // pillar(0) before beam(1)
        }
}