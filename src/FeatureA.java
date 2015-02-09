public class FeatureA extends Feature {

  public FeatureA(Rect rect) {
    super(rect);
    type = 'A';
  }
  
  @Override
  public Feature scale(double s) {
    return new FeatureA(rect.scale(s));
  }

  @Override
  public int getValue(IntegralImage img) {
    int x = rect.getX(), y = rect.getY(), 
        w = rect.getWidth(), h = rect.getHeight();
    Rect rect1 = new Rect(x, y, w, h);
    Rect rect2 = new Rect(x + w, y, w, h);
    return img.sumValue(rect1) - img.sumValue(rect2);
  }

}
