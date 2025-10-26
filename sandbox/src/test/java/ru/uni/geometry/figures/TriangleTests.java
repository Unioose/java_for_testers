package ru.uni.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    //Тест - Проверка вычисления периметра треугольника
    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(12.0,new Triangle(5.0, 4.0, 3.0).perimetr());
        Assertions.assertEquals(3.0,new Triangle(1.0, 1.0, 1.0).perimetr());
        Assertions.assertEquals(32.0,new Triangle(25.0, 4.0, 3.0).perimetr());
    }
    //Тест - Проверка вычисления площади треугольника
    @Test
    void canCalculateArea()
    {
        Assertions.assertEquals(6, new Triangle(5.0, 4.0, 3.0).area());
        Assertions.assertEquals(84, new Triangle(13.0, 14.0, 15.0).area());
        Assertions.assertEquals(360, new Triangle(36.0, 25.0, 29).area());
    }
}
