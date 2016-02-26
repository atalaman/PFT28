package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.rft.sandbox.Point;

/**
 * Created by Анна on 25.02.2016.
 */
public class PointTests {

  @Test
  public void testDistance(){
    Point p=new Point(6, 4);
    Point p2=new Point(9, 4);
    assert p.distanceNew(p2)== 3.0;
  }

  @Test
  public void testDistance1(){
    Point p=new Point(6, 8);
    Point p2=new Point(9, 4);
    Assert.assertEquals(p.distanceNew(p2), 5.0);
  }

  @Test
  public void testDistance2 (){
    Point p=new Point(11, 2);
    Point p2=new Point(9, 8);
    Assert.assertEquals(p.distanceNew(p2), 5.25);
  }
}
