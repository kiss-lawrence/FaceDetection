public abstract class Feature {
  
  protected char type;
  protected final Rect rect;
  
  public Feature(Rect r) {
    rect = r;
  }
  
  public abstract Feature scale(double s);
  
  public abstract int getValue(IntegralImage img);

}
