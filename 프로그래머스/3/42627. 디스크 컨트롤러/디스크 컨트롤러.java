import java.util.*;

class Solution {
    static class Job {
        int req, dur, id;
        Job(int req, int dur, int id) {
            this.req = req;
            this.dur = dur;
            this.id = id;
        }
    }

    public int solution(int[][] jobs) {
        int n = jobs.length;
        if (n == 0) return 0;

        Job[] arr = new Job[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Job(jobs[i][0], jobs[i][1], i); // req, dur, id
        }

        // 요청시각 기준 정렬
        Arrays.sort(arr, (a, b) -> {
            if (a.req != b.req) return Integer.compare(a.req, b.req);
            if (a.dur != b.dur) return Integer.compare(a.dur, b.dur);
            return Integer.compare(a.id, b.id);
        });

        // 우선순위: dur ↑, req ↑, id ↑
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> {
            if (a.dur != b.dur) return Integer.compare(a.dur, b.dur);
            if (a.req != b.req) return Integer.compare(a.req, b.req);
            return Integer.compare(a.id, b.id);
        });

        long time = 0;
        long total = 0;
        int idx = 0, done = 0;

        while (done < n) {
            while (idx < n && arr[idx].req <= time) {
                pq.offer(arr[idx]);
                idx++;
            }

            if (pq.isEmpty()) {
                // 다음 요청 시각으로 점프
                time = Math.max(time, arr[idx].req);
                continue;
            }

            Job cur = pq.poll();
            time += cur.dur;
            total += (time - cur.req);
            done++;
        }

        return (int)(total / n);
    }
}