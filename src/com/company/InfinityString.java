package com.company;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InfinityString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long size = s.length(), repeated = n/size;
        long left = n - (size * repeated);
        int extra = 0;

        int count = 0;
        for(int i = 0; i < size; i++){
            if(s.charAt(i) == 'a'){
                ++count;
            }
        }

        for(int i = 0; i < left; i++){
            if(s.charAt(i) == 'a'){
                ++extra;
            }
        }

        repeated = (repeated * count) + extra;

        return repeated;
    }

    //all those methods are not supported when N is higher than Integer;

    private static Long replicateStringManually(String s, long n) {
        String repeatedString;
        if (s.length() < n) {
            StringBuffer sb = new StringBuffer();
            long module = n % s.length();
            long tti = n / s.length();

             for (int i = 0; i < tti ; i++){
                 sb.append(s);
             }

            if (module != 0) {
                sb.append(s, 0, (int) module);
            }

            repeatedString = sb.toString();
        }else {
            repeatedString = s;
        }

        System.out.println("infinite string -> " + repeatedString);
        return repeatedString.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toList()).stream().filter(character -> 'a' == character).count();
    };

    private static Long java8ReplicatedString(String s, long n) {
        String repeatedString;
        if (s.length() < n) {
            long module = n % s.length();
            if (module == 0) {
                repeatedString = String.join("", Collections.nCopies(Math.toIntExact(n / s.length()), s));
            } else {
                repeatedString = String.join("", Collections.nCopies(Math.toIntExact(n / s.length()) + 1, s)).substring(0, (int) n - 1);
            }

        } else {
            repeatedString = s;
        }
        System.out.println("infinite string -> " + repeatedString);
        return repeatedString.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toList()).stream().filter(character -> 'a' == character).count();
    }

    private static Long java11ReplicatedString(String s, long n) {
        String repeatedString;
        if (s.length() < n) {
            // Java 11
            long module = n % s.length();
            if (module == 0) {
                repeatedString = s.repeat(Math.toIntExact(n / s.length()));
            } else {
                repeatedString = s.repeat(Math.toIntExact(n / s.length()) + 1).substring(0, (int) n - 1);
            }
        } else {
            repeatedString = s;
        }
        System.out.println("infinite string -> " + repeatedString);
        return repeatedString.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toList()).stream().filter(character -> 'a' == character).count();

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();
        System.out.println("string -> " + s);
        System.out.println("number  -> " + n);
        System.out.println("result  -> " + result);
        scanner.close();
    }
}
