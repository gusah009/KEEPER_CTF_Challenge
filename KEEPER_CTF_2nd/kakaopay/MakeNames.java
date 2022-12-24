package acmicpc.hyeonmo._2022.keeper.dijkstra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeNames {

    private static final int NODE_COUNT = 100_000;
    private static final int EDGE_COUNT = 50_000_000;
    private static final Random random = new Random();
    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/dijkstra/data/dataset.txt";
    private static final List<String> names = new ArrayList<>(NODE_COUNT + 1);

    public static void main(String[] args) {
        getNames();
        writeInFile();
    }

    private static void getNames() {
        int num = NODE_COUNT / 10;
        for (int i = 0; i < NODE_COUNT; i++) {
            if (i % num == 0) {
                System.out.println((i / num * 10) + "%");
            }
            int nameSize = random.nextInt(3) + 1;
            StringBuilder sb = new StringBuilder(nameSize);
            for (int j = 0; j < nameSize; j++) {
                sb.append(getRandomCharacter());
            }
            names.add(sb.toString());
        }
    }

    private static char getRandomCharacter() {
        switch (random.nextInt(3)) {
            case 0 -> {
                return (char) (random.nextInt('Z' + 1 - 'A') + 'A');
            }
            case 1 -> {
                return (char) (random.nextInt('z' + 1 - 'a') + 'a');
            }
            default -> {
                return (char) (random.nextInt('9' + 1 - '0') + '0');
            }
        }
    }

    private static void writeInFile() {
        try (FileOutputStream outputStream = new FileOutputStream(fileName, true)) {
            names.forEach(str -> {
                try {
                    outputStream.write((str + "\n").getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
