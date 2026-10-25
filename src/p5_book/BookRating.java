package p5_book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BookRating {
    static void main() {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Ask user for path to file with ratings
        System.out.print("Enter the path to the file containing ratings to process: ");
        String path = scanner.nextLine();

        // Attempt to read file using Files.readAllLines instead of BufferedReader because I feel it is more appropriate
        // to read the file all at once rather than line by line because of the small size.
        // Reference: https://www.geeksforgeeks.org/java/files-class-readstring-method-in-java-with-examples/
        ArrayList<String> contents;
        try {
            contents = new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (Exception e) {
            System.out.println("Hello, world!"); // TODO
            return;
        }

        // First line is the number of ratings
        int length = Integer.parseInt(contents.removeFirst());

        // Parse the file
        // TODO: This assumes that the input file is valid.
        HashMap<String, ArrayList<Integer>> ratings = new HashMap<>();
        for (int i = 0; i < contents.size(); i += 2) {
            if (!ratings.containsKey(contents.get(i))) {
                ratings.put(contents.get(i), new ArrayList<Integer>());
            }
            ratings.get(contents.get(i)).add(Integer.parseInt(contents.get(i + 1)));
        }

        // Display information for each title
        ratings.forEach((key, value) -> {
            float avg = 0;
            for (int rating: value) {
                avg += rating;
            }
            avg /= value.size();
            System.out.printf("%s: %d reviews, average of %.1f/5\n", key, value.size(), avg);
        });
    }
}
