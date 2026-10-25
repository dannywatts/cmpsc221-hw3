package p3_occurence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CountKeywords {
    static void main() {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt user for Java source file
        System.out.print("Enter the path of the Java source file to scan for keywords: ");
        String path = scanner.nextLine();

        // Read the contents of the file and handle any exceptions
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
                while (j < contents.length() && (contents.charAt(j) != '*' || contents.charAt(j + 1) != '/')) {
                    j++;
                }
                contents = contents.substring(0, i) + contents.substring(j + 2);
            }
        }

        // Remove strings
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) == '"') {
                int j = i + 1;
                while (j < contents.length() && contents.charAt(j) != '"') {
                    j++;
                }
                contents = contents.substring(0, i) + contents.substring(j + 1);
            }
        }

        // Extract all sequences of letters, numbers, and underscores.
        ArrayList<String> sequences = new ArrayList<>();
        for (int i = 0; i < contents.length(); i++) {
            if ((48 <= contents.charAt(i) && contents.charAt(i) <= 57) || (65 <= contents.charAt(i) && contents.charAt(i) <= 90) || contents.charAt(i) == '_' || (97 <= contents.charAt(i) && contents.charAt(i) <= 122)) {
                StringBuilder sequence = new StringBuilder();
                while ((48 <= contents.charAt(i) && contents.charAt(i) <= 57) || (65 <= contents.charAt(i) && contents.charAt(i) <= 90) || contents.charAt(i) == '_' || (97 <= contents.charAt(i) && contents.charAt(i) <= 122)) {
                    sequence.append(contents.charAt(i));
                    i++;
                }
                sequences.add(sequence.toString());
            }
        }

        // Log the number of times keywords appear in those sequences
        // List taken from https://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html.
        final List<String> keywords = List.of(
                "abstract",
                "assert",
                "boolean",
                "break",
                "byte",
                "case",
                "catch",
                "char",
                "class",
                "const",
                "continue",
                "default",
                "do",
                "double",
                "else",
                "enum",
                "extends",
                "final",
                "finally",
                "float",
                "for",
                "goto",
                "if",
                "implements",
                "import",
                "instanceOf",
                "int",
                "interface",
                "long",
                "native",
                "new",
                "package",
                "private",
                "protected",
                "public",
                "return",
                "short",
                "static",
                "strictfp",
                "super",
                "switch",
                "synchronized",
                "this",
                "throw",
                "throws",
                "transient",
                "try",
                "void",
                "volatile",
                "while"
        );
        HashMap<String, Integer> keywordFrequency = new HashMap<>();
        for (String sequence: sequences) {
            if (keywords.contains(sequence)) {
                if (!keywordFrequency.containsKey(sequence)) {
                    keywordFrequency.put(sequence, 1);
                } else {
                    keywordFrequency.put(sequence, keywordFrequency.get(sequence) + 1);
                }
            }
        }

        // Output results to user
        keywordFrequency.forEach((keyword, frequency) -> System.out.printf("%s appears %d times\n", keyword, frequency));
    }
}
