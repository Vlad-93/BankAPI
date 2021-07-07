package main.java.util;

import java.io.*;
import java.util.Scanner;

public class Generator {

    public static long getNextCardNumber() {
        try {
            String pathToFile = "src/main/resources/cardNumber.txt";
            long num = new Scanner(new File(pathToFile)).nextLong();
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile));
            writer.write("" + (num + 1));
            writer.close();
            return num + 1;
        } catch (IOException e) {
            System.out.println("Ошибка при генерации номера карты");
        }
        return -1;
    }

}
