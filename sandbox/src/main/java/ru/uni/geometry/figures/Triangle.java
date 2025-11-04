package ru.uni.geometry.figures;

import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {
    //Проверка на отрицательные стороны треугольника
    public Triangle{
        if (sideA<0 || sideB<0 || sideC<0)
        {
            throw new IllegalArgumentException("Triange side should be non-negative");
        }
    }

    public void inequality(){
        if (sideA>=sideB+sideC || sideB>=sideA+sideC || sideC>=sideA+sideB)
        {
            throw new IllegalArgumentException("These sides cannot form a triangle");
        }
    }

   //Вычисление периметра треугольника
    public double perimetr() {
        return this.sideA+this.sideB+this.sideC;
    }


    //Вычисление площади треугольника по формуле Герона
    public double area()
    {
        var semiperimeter = perimetr()/2;
        return Math.sqrt(semiperimeter*(semiperimeter-this.sideA)*(semiperimeter-this.sideB)*(semiperimeter-this.sideC));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(sideA, triangle.sideA) == 0 && Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideC) == 0)
                || (Double.compare(sideB, triangle.sideA) == 0 && Double.compare(sideC, triangle.sideB) == 0 && Double.compare(sideA, triangle.sideC) == 0)
                || (Double.compare(sideC, triangle.sideA) == 0 && Double.compare(sideA, triangle.sideB) == 0 && Double.compare(sideB, triangle.sideC) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
