package acmicpc.hyeonmo._2022.keeper.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/dijkstra/data/dataset.txt";
    private static final String answerFileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/dijkstra/data/answer.txt";

    static int nodeCount;
    static int edgeCount;
    static List<Edge>[] edges;
    static String[] nodeNames;
    static String answer = null;
    static int[] dist;
    static int[] route;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] line = reader.readLine().split(" ");
            init(line);
            initNode(reader);
            initEdge(reader);

            long algorithmStartTime = System.currentTimeMillis();

            dijkstra();
            long algorithmEndTime = System.currentTimeMillis();

            System.out.println((algorithmEndTime - algorithmStartTime) / 1000 + "s");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(answerFileName))) {
                writer.write(answer);
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(1_000_000);
        pq.add(new Edge(0, 0));
        dist[0] = 0;
        while (!pq.isEmpty()) {
            if (pq.size() % 10000 == 0) {
                System.out.println("pq size: " + pq.size());
            }
            Edge edge = pq.poll();
            int cost = edge.cost;
            int now = edge.dstId;

            if (now == nodeCount - 1) {
                if (answer == null) {
                    answer = getPath();
                } else if (dist[now] == cost) {
                    String other = getPath();
                    if (answer.compareTo(other) > 0) {
                        answer = other;
                    }
                }
                continue;
            }

            if (cost > dist[now]) {
                continue;
            }

            for (Edge e : edges[now]) {
                int nextNodeId = e.dstId;
                int nextCost = e.cost;
                if (dist[nextNodeId] >= dist[now] + nextCost) {
                    dist[nextNodeId] = dist[now] + nextCost;
                    route[nextNodeId] = now;
                    pq.add(new Edge(nextNodeId, dist[nextNodeId]));

                }
            }
        }
    }

    private static String getPath() {
        List<Integer> path = new ArrayList<>(100_000);
        int idx = nodeCount - 1;
        while (idx != 0) {
            path.add(idx);
            idx = route[idx];
        }
        path.add(0);
        Collections.reverse(path);
        System.out.println(path);
        return path.stream()
                .map(p -> nodeNames[p])
                .collect(Collectors.joining());
    }

    private static void initNode(BufferedReader reader) throws IOException {
        for (int i = 0; i < nodeCount; i++) {
            nodeNames[i] = reader.readLine();
        }
        dist = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
    }

    private static void initEdge(BufferedReader reader) throws IOException {
        int tmp = edgeCount / 100;
        for (int i = 0; i < edgeCount; i++) {
            if (tmp != 0 && i % tmp == 0) {
                System.out.println("add edges... " + (i / tmp) + "%");
            }
            int[] edgeInput = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            edges[edgeInput[0]].add(new Edge(edgeInput[1], edgeInput[2]));
            edges[edgeInput[1]].add(new Edge(edgeInput[0], edgeInput[2]));
        }
        tmp = nodeCount / 100;
        for (int i = 0; i < nodeCount; i++) {
            if (tmp != 0 && i % tmp == 0) {
                System.out.println("sorting edges... " + (i / tmp) + "%");
            }
            Collections.sort(edges[i]);
        }
    }

    private static void init(String[] line) {
        nodeCount = Integer.parseInt(line[0]);
        nodeNames = new String[nodeCount];
        route = new int[nodeCount + 1];
        edgeCount = Integer.parseInt(line[1]);
        edges = new List[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            edges[i] = new ArrayList<>(1024);
        }
    }

    private static class Edge implements Comparable<Edge> {

        int dstId;
        int cost;

        public Edge(int dstId, int cost) {
            this.dstId = dstId;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "dstId=" + dstId +
                    ", cost=" + cost +
                    '}';
        }
    }
}
