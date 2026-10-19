package p2_permutation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt user for string
        System.out.print("Enter a string to determine its permutations: ");
        String string = scanner.nextLine();

        // Print all possible permutations of that string
        printPermutations(string);
    }

    static void printPermutations(String string) {
        System.out.println(determinePermutations(string));
    }

    static ArrayList<String> determinePermutations(String string) {
        ArrayList<String> permutations = new ArrayList<>();

        if (string.length() == 1) {
            permutations.add(string);
        } else {
            for (int i = 0; i < string.length(); i++) {
                for (String permutation: determinePermutations(string.substring(0, i) + string.substring(i + 1))) {
                    permutations.add(string.charAt(i) + permutation);
                }
            }
        }

        return permutations;
    }
}
