package ru.netology;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dictionary {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String scanData;

        while (true) {
            System.out.println("\nВведите предложение:");
            scanData = scan.nextLine();
            if (scanData.equals("end"))
                break;

            System.out.println("\nСловарь:");
            dictionaryComplete.apply(scanData).forEach(System.out::println);
        }
        scan.close();
    }

    public static Function<String, List<String>> dictionaryComplete = (String str) ->
    {
        List<String> list = Arrays.stream(str.trim().split(" "))
                .map(x -> x.trim().toLowerCase())
                .sorted((String o1, String o2) -> o1.compareTo(o2))
                .collect(Collectors.toList());
        return list;
    };
}
