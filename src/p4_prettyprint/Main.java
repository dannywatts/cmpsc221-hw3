package p4_prettyprint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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

        // Break source file into tokens
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < contents.length(); i++) {
            // Detect keywords, numbers, and variables and function names
            if ((48 <= contents.charAt(i) && contents.charAt(i) <= 57) || (65 <= contents.charAt(i) && contents.charAt(i) <= 90) || contents.charAt(i) == '_' || (97 <= contents.charAt(i) && contents.charAt(i) <= 122)) {
                StringBuilder sequence = new StringBuilder();
                while ((48 <= contents.charAt(i) && contents.charAt(i) <= 57) || (65 <= contents.charAt(i) && contents.charAt(i) <= 90) || contents.charAt(i) == '_' || (97 <= contents.charAt(i) && contents.charAt(i) <= 122)) {
                    sequence.append(contents.charAt(i));
                    i++;
                }
                tokens.add(sequence.toString());
            }

            // TODO: Detect operators
            else if (contents.charAt(i) == '=' & contents.charAt(i + 1) == '=') {
                tokens.add("==");
                i++;
            } else if (contents.charAt(i) == '=') {
                tokens.add("=");
            } else if (contents.charAt(i) == '*') {
                tokens.add("*");
            }

            // Detect punctuation
            else {
                List<Character> punctuation = List.of(
                        '{',
                        '}',
                        '(',
                        ')',
                        ';'
                );
                if (punctuation.contains(contents.charAt(i))) {
                    tokens.add(String.valueOf(contents.charAt(i)));
                }
            }

        }
        // TODO: System.out.println(tokens);

        // Format tokens
        String formatted = "";
        for (String token: tokens) {
            if (token.equals("{")) {
                formatted += "{\n";
            } else if (token.equals("}")) {
                formatted += "\n}\n";
            } else if (token.equals("(") || token.equals(")")) {
                formatted += token;
            } else if (token.equals(";")) {
                formatted += token + "\n";
            } else {
                formatted += token + " ";
            }
        }
        System.out.println(formatted);
    }
}
