package ru.stqa.rft.sandbox;

/**
 * Created by Анна on 22.02.2016.
 */
public class WorkWithPoint {
  public static void main(String[] args) {
    Point p1 = new Point(2.3, 5.7);
    Point p2 = new Point(6.4, 8.2);
    System.out.println("Distance between two points is " + distance(p1,p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.x - p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
  }
}
