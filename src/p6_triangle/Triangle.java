package p6_triangle;

import java.util.Scanner;

public class Triangle {
    // Variables
    private int side1, side2, side3;

    // Constructors
    public Triangle(int side1, int side2, int side3) throws IllegalTriangleException {
        if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side1 > side2) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new IllegalTriangleException();
        }
    }

    // Getters
    public int getSide1() {return side1;}
    public int getSide2() {return side2;}
    public int getSide3() {return side3;}

    // Setters
    public void setSide1(int side1) throws IllegalTriangleException {
        if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side1 > side2) {
            this.side1 = side1;
        } else {
            throw new IllegalTriangleException();
        }
    }

    public void setSide2(int side2) throws IllegalTriangleException {
        if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side1 > side2) {
            this.side2 = side2;
        } else {
            throw new IllegalTriangleException();
        }
    }

    public void setSide3(int side3) throws IllegalTriangleException {
        if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side1 > side2) {
            this.side3 = side3;
        } else {
            throw new IllegalTriangleException();
        }
    }

    // Main
    static void main() {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        // Get lengths of sides from user
        int len1, len2, len3;
        System.out.print("Enter the length of side 1: ");
        len1 = scanner.nextInt();
        System.out.print("Enter the length of side 2: ");
        len2 = scanner.nextInt();
        System.out.print("Enter the length of side 3: ");
        len3 = scanner.nextInt();

        // Create Triangle object
        try {
            Triangle triangle = new Triangle(len1, len2, len3);
        } catch (IllegalTriangleException e) {
            System.out.println("Illegal triangle!"); // TODO
        }
    }
}
