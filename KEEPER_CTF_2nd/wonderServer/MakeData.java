package acmicpc.hyeonmo._2022.keeper.alice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MakeData {

    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/alice/data/rabbit.txt";
    private static final int targetLength = 500_000_000;

    private static final String[] patterns = new String[]{
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(10000),
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(9999) + "rabbi",
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(9999) + "rabb",
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(9999) + "rab",
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(9999) + "ra",
            "rabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbitrabbit".repeat(9999) + "r"};

    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName, true)) {
            outputStream.write(givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(targetLength)
                    .getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(int targetStringLength) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(targetLength + 10000);

        while (sb.length() < targetStringLength) {
            sb.append(patterns[random.nextInt(6)]);
        }
        return sb.toString();
    }
}
