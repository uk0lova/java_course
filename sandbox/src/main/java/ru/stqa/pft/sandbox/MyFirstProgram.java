package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        print(p1,p2);
        print(p3,p1);
    }

    public static void print(Point p1,Point p2){
        System.out.println("Расстояние между точкой Х с координатами ("
                + p1.x + "," + p1.y + ") и Y с координатами (" + p2.x + "," + p2.y + ") = " + Point.distance(p1, p2));
    }
}
