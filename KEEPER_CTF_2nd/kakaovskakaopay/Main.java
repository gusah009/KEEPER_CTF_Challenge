package acmicpc.hyeonmo._2022.keeper.kakaovskakaopay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final String fileName = "/Users/jeonghyeonmo/Desktop/acmicpc/ps/src/acmicpc/hyeonmo/_2022/keeper/kakaovskakaopay/data/array.txt";

    public static void main(String[] args) throws FileNotFoundException {
        int kakao = 0;
        int pay = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            for (int i = 0; i < line.length(); i++) {
                int num = line.charAt(i) - '0';
                if (num < 5) {
                    kakao += 5 - num;
                } else {
                    pay += num - 4;
                }
            }
            System.out.println(kakao + ", " + pay);
            String answer =
                    kakao > pay ? "KEEPER{KAKAO" + (kakao - pay) + "}" : "KEEPER{KAKAOPAY" + (pay - kakao) + "}";
            System.out.println(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
