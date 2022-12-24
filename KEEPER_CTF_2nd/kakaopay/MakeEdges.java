package acmicpc.hyeonmo._2022.keeper.dijkstra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MakeEdges {

    private static final int NODE_COUNT = 100_000;
    private static final int EDGE_COUNT = 50_000_000;
    private static final int MINUMUM_FLAG_SIZE = 30;
    private static final Random random = new Random();
    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/dijkstra/data/dataset.txt";
    private static final Set<Edge> edges = new HashSet<>(EDGE_COUNT + 1);

    public static void main(String[] args) {
        setInitEdges();
        setEdges();
        writeInFile();
    }

    private static void setInitEdges() {
        for (int i = 0; i < NODE_COUNT - 1; i++) {
            edges.add(new Edge(i, i + 1, random.nextInt(99) + 1));
        }
    }

    private static void setEdges() {
        int i = 0;
        int num = EDGE_COUNT / 10;
        while (edges.size() != EDGE_COUNT) {
            if (i % num == 0) {
                System.out.println((i / num * 10) + "%");
            }
            i++;
            edges.add(pickRandomEdge(0, NODE_COUNT));
        }
    }

    private static Edge pickRandomEdge(int startInclusive, int endExclusive) {
        int num1 = random.nextInt(endExclusive) + startInclusive;
        int randomOffset = NODE_COUNT / MINUMUM_FLAG_SIZE;
        int num2;
        do {
            int offset = random.nextInt(randomOffset);
            num2 = num1 + offset >= NODE_COUNT ? num1 - offset : num1 + offset;
        } while (num1 == num2);
        return new Edge(Math.min(num1, num2), Math.max(num1, num2), random.nextInt(999) + 1);
    }

    private static void writeInFile() {
        try (FileOutputStream outputStream = new FileOutputStream(fileName, true)) {
            final int[] i = {0};
            int num = EDGE_COUNT / 100;
            edges.stream()
                    .map(Edge::toString)
                    .forEach(str -> {
                        if (i[0] % num == 0) {
                            System.out.println((i[0] / num) + "%");
                        }
                        i[0]++;
                        try {
                            outputStream.write(str.getBytes());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Edge {

        int src;
        int dst;
        int weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return src + " " + dst + " " + weight + "\n";
        }
    }
}
