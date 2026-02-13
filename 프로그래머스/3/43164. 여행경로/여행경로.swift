import Foundation

func solution(_ tickets: [[String]]) -> [String] {
    // 1) 인접 리스트 구성
    var graph: [String: [String]] = [:]
    for t in tickets {
        graph[t[0], default: []].append(t[1])
    }

    // 2) 사전순으로 가장 앞 목적지를 꺼내야 하므로
    //    배열의 마지막에서 pop 하게끔 "내림차순" 정렬
    for (k, v) in graph {
        graph[k] = v.sorted(by: >)
    }

    // 3) Hierholzer (스택 기반)
    var stack: [String] = ["ICN"]
    var route: [String] = []

    while let cur = stack.last {
        if var nexts = graph[cur], !nexts.isEmpty {
            // 다음 목적지 하나 사용(pop)
            let nxt = nexts.removeLast()
            graph[cur] = nexts
            stack.append(nxt)
        } else {
            // 더 갈 곳 없으면 경로에 확정
            route.append(stack.removeLast())
        }
    }

    // route는 역순으로 쌓였으니 뒤집기
    return route.reversed()
}