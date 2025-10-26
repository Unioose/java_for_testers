package ru.uni.geometry.figures;

public class Square {
    public static void printSquareArea(double a) {
        String text = String.format("Площадь квадрата со стороной %f = %f", a, area(a));
        System.out.println(text);

    }

    public static double area(double a) {
        return a * a;
    }

    public static double perimetr(double a) {
        return 4*a;
    }
}
