package ru.uni.geometry;

import ru.uni.geometry.figures.Rectangle;
import ru.uni.geometry.figures.Square;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//        for(Square square: squares){
//            Square.printSquareArea(square);
//        }
        //Исп функции вместо цикла
        // Consumer<Square> print = (square)-> {Square.printSquareArea(square);};
        //Альт вариант написания
        Consumer<Square> print = Square::printSquareArea;
        squares.forEach(print);
//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);
    }

}
