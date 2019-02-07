package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsDistanceTests {

    @Test
    public void test1(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertEquals(Point.distance(p1,p2),2.8284271247461903);
    }

    @Test
    public void test2(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertNotEquals(Point.distance(p1,p2),2.82);
    }

    @Test
    public void test3(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertNotNull(Point.distance(p1,p2));
    }

    @Test
    public void test4(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);

        Assert.assertTrue(Point.distance(p1,p2)==0);
    }

    @Test
    public void test5(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertTrue(Point.distance(p1,p2)>0);
    }

    @Test
    public void test6(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertEquals(Point.distance(p1,p2),Point.distance(p2,p1));
    }

    @Test
    public void test7(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        Assert.assertEquals((int)Math.round(Point.distance(p1,p2)),5); //3
    }

}
