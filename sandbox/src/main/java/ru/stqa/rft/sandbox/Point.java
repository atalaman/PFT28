package ru.stqa.rft.sandbox;

/**
 * Created by Анна on 22.02.2016.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x, double y){
    this.x =x;
    this.y =y;
  }

  public double distanceNew(Point anotherPoint) {
    return Math.sqrt(((anotherPoint.x - x) * (anotherPoint.x - x)) + ((anotherPoint.y - y) * (anotherPoint.y - y)));
  }
}

