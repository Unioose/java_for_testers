package ru.uni.geometry;

import ru.uni.geometry.figures.Rectangle;
import ru.uni.geometry.figures.Square;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare =  () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
//        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//        for(Square square: squares){
//            Square.printSquareArea(square);
//        }
        //Исп функции вместо цикла
        // Consumer<Square> print = (square)-> {Square.printSquareArea(square);};
        //Альт вариант написания
//        Consumer<Square> print = square -> {
//            Square.printSquareArea(square);
//            Square.printPerimetrArea(square);
//        };
        squares.peek(Square::printSquareArea).forEach(Square::printPerimetrArea);
    }

}
