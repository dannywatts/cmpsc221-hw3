package p3_occurence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CountKeywords {
    static void main() {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt user for Java source file
        System.out.print("Enter the absolute path of the Java source file to scan for keywords: ");
        String path = scanner.nextLine();

        // Create file handle
        File file = new File(path);


        // File I/O exceptions must be handled
        String contents;
        try {
            // Read the file
            contents = Files.readString(Path.of(path));
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            return;
        }

        // Remove single-line comments
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) == '/' && contents.charAt(i + 1) == '/') {
                int j = i + 2;
                while (j < contents.length() && contents.charAt(j) != '\n') {
                    j++;
                }
                contents = contents.substring(0, i) + contents.substring(j);
            }
        }

        // Remove multi-line comments
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) == '/' && contents.charAt(i + 1) == '*') {
                int j = i + 2;
                while (j < contents.length() - 1 && (contents.charAt(j) != '*' && contents.charAt(j + 1) != '/')) {
                    System.out.println(contents.substring(j, j + 2));
                    j++;
                }
                contents = contents.substring(0, i) + contents.substring(j);
            }
        }

        System.out.println(contents);

        // Determine keywords in Java code, ignoring comments, using the following algorithm:
        // 1. Remove comments, which are sequences of characters bounded by either '//' and '\n' or '/*' and '*/'.
        // 2. Divide code into an ArrayList of String objects at the spaces.
        // 3. Search the ArrayList against Java's keywords.
    }
}
