public abstract class Feature {
  
  protected char type;
  protected final Rect shape;
  
  public Feature(Rect r) {
    shape = r;
  }
  
  public Rect getShape() {
    return shape;
  }
  
  @Override
  public String toString() {
    return type + " " + shape.toString();
  }
  
  public abstract int getValue(IntegralImage img, Rect workingRect);

}
