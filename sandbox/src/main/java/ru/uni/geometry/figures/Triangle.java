package ru.uni.geometry.figures;

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
}
