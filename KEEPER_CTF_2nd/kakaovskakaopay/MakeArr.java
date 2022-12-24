package acmicpc.hyeonmo._2022.keeper.kakaovskakaopay;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MakeArr {

    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/kakaovskakaopay/data/array.txt";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder(100_000_000);
        Random random = new Random();

        for (int i = 0; i < 100_000_000; i++) {
            sb.append(random.nextInt(10));
        }

        try (FileOutputStream outputStream = new FileOutputStream(fileName, true)) {
            outputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
