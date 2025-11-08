package ru.uni.geometry.figures;

import java.util.Arrays;
import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {
    //Проверка на отрицательные стороны треугольника и неравенство треугольников
    public Triangle{
        if (sideA<0 || sideB<0 || sideC<0)
        {
            throw new IllegalArgumentException("Triange side should be non-negative");
        }
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
        double [] arrT1 = {sideA, sideB, sideC};
        double [] arrT2 = {triangle.sideA, triangle.sideB, triangle.sideC};
        Arrays.sort(arrT1);
        Arrays.sort(arrT2);


        return (arrT1[0]==arrT2[0]&&arrT1[1]==arrT2[1]&&arrT1[2]==arrT2[2]);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
