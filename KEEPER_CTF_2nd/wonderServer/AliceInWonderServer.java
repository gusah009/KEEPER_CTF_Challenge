package acmicpc.hyeonmo._2022.keeper.alice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AliceInWonderServer {

    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/alice/data/rabbit.txt";
    private static final String patterns = "rabbit".repeat(10000);
    private static int answer = 0;

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str = reader.readLine();
            long start = System.currentTimeMillis();
            KMP(str, patterns);
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(answer);
        System.out.println();
    }

    static int findString(String parent, String pattern) {
        int n1 = parent.length();
        int n2 = pattern.length();
        for (int i = 0; i <= n1 - n2; i++) {
            boolean flag = true;
            for (int j = 0; j < n2; j++) {
                if (parent.charAt(i + j) != pattern.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }
        return 0; // 찾지 못했으면 0
    }

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }
        return table;
    }

    static void KMP(String parent, String pattern) {
        int[] table = makeTable(pattern);

        int n1 = parent.length();
        int n2 = pattern.length();

        int idx = 0;
        for (int i = 0; i < n1; i++) {
            while (idx > 0 && parent.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }
            if (parent.charAt(i) == pattern.charAt(idx)) {
                if (idx == n2 - 1) {
//                    System.out.println(i - idx + 1 + "번째에서 찾았습니다. ~" + (i + 1));
                    answer++;
                    idx = table[idx];
                } else {
                    idx += 1;
                }
            }
        }
    }
}
