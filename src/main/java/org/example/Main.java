package org.example;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    //    TODO: Update shell script to support no args
//    TODO: Update below pgm to take cat output as an input
    public static void main(String[] args) throws IOException {
        int len = args.length;
        if (len < 1) {
            System.out.println("Usage: -c|-l|-w|-m <file_path>");
            return;
        }

        File file = new File(args[args.length - 1]);
        if (!file.exists()) {
            System.out.println("Error: File " + file.getName() + " does not exist");
            return;
        }

        try {
            switch (args[0]) {
                case "-c":
                    System.out.println(byteCount(file));
                    break;
                case "-l":
                    System.out.println(lineCount(file));
                    break;
                case "-w":
                    System.out.println(wordCount(file));
                    break;
                case "-m":
                    System.out.println(charCount(file));
                    break;
                default:
                    System.out.print(byteCount(file) + "\t" + lineCount(file) + "\t" + wordCount(file) + "\t" + file.getName());
                    break;
            }
        } catch (IOException exception) {
            System.out.println("Error while processing file" + exception.getMessage());
        }

    }

    public static long byteCount(File file) throws IOException {
        return Files.size(file.toPath());
    }

    public static int charCount(File file) {
        int charCount = 0;
        try (FileReader fileReader = new FileReader(file)) {
            while (fileReader.read() != -1) {
                charCount += 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return charCount;
    }

    public static long lineCount(File file) throws IOException {
        Stream<String> fileStream = Files.lines(file.toPath());
        return fileStream.count();
    }

    public static int wordCount(File file) throws FileNotFoundException {
        int count = 0;
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                sc.next();
                count++;
            }
        }
        return count;
    }

}