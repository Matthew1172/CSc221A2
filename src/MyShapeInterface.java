public interface MyShapeInterface {
    MyRectangle getMyBoundingRectangle(MyColor c);
    //Set<MyPoint> getMyArea();
    MyRectangle overlapMyShapes(MyShape s1, MyShape s2);
    MyRectangle overlapMyRectangles(MyRectangle r1, MyRectangle r2);
}
