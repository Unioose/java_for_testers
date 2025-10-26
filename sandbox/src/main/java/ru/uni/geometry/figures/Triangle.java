package ru.uni.geometry.figures;

public record Triangle(double sideA, double sideB, double sideC) {

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
