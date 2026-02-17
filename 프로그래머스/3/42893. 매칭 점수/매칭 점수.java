import java.util.*;
import java.util.regex.*;

class Solution {

    private static int countWordOccurrences(String html, String targetLower) {
        int cnt = 0;
        StringBuilder cur = new StringBuilder();

        for (int i = 0; i < html.length(); i++) {
            char c = Character.toLowerCase(html.charAt(i));
            if (c >= 'a' && c <= 'z') {
                cur.append(c);
            } else {
                if (cur.length() > 0) {
                    if (cur.toString().equals(targetLower)) cnt++;
                    cur.setLength(0);
                }
            }
        }
        if (cur.length() > 0 && cur.toString().equals(targetLower)) cnt++;
        return cnt;
    }

    public int solution(String word, String[] pages) {
        int n = pages.length;
        String target = word.toLowerCase();

        Pattern urlP = Pattern.compile("<meta[^>]*property=\"og:url\"[^>]*content=\"([^\"]+)\"");
        Pattern linkP = Pattern.compile("<a href=\"([^\"]+)\"");

        String[] urls = new String[n];
        List<String>[] outLinks = new ArrayList[n];
        double[] base = new double[n];
        double[] linkScore = new double[n];

        Map<String, Integer> urlToIndex = new HashMap<>(n * 2);

        // 1) parse url, outlinks, base score
        for (int i = 0; i < n; i++) {
            String html = pages[i];

            // url
            Matcher um = urlP.matcher(html);
            if (um.find()) {
                urls[i] = um.group(1);
                urlToIndex.put(urls[i], i);
            } else {
                urls[i] = "";
            }

            // outlinks
            outLinks[i] = new ArrayList<>();
            Matcher lm = linkP.matcher(html);
            while (lm.find()) {
                outLinks[i].add(lm.group(1));
            }

            // base score (word boundary by non-letters)
            base[i] = countWordOccurrences(html, target);
        }

        // 2) distribute link score
        for (int i = 0; i < n; i++) {
            int outCnt = outLinks[i].size();
            if (outCnt == 0) continue;
            double share = base[i] / outCnt;

            for (String to : outLinks[i]) {
                Integer j = urlToIndex.get(to);
                if (j != null) linkScore[j] += share;
            }
        }

        // 3) best matching score (tie -> smallest index)
        int bestIdx = 0;
        double best = base[0] + linkScore[0];

        for (int i = 1; i < n; i++) {
            double s = base[i] + linkScore[i];
            if (s > best) {
                best = s;
                bestIdx = i;
            }
        }

        return bestIdx;
    }
}