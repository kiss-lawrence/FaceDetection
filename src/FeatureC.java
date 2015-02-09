public class FeatureC extends Feature {

  public FeatureC(Rect rect) {
    super(rect);
    type = 'C';
  }
  
  @Override
  public Feature scale(double s) {
    return new FeatureC(rect.scale(s));
  }
  
  @Override
  public int getValue(IntegralImage img) {
    int x = rect.getX(), y = rect.getY(), 
        w = rect.getWidth(), h = rect.getHeight();
    Rect rect1 = new Rect(x, y, w, h);
    Rect rect2 = new Rect(x + 2 * w, y, w, h);
    Rect rect3 = new Rect(x + w, y, w, h);
    return img.sumValue(rect1) + img.sumValue(rect2) - img.sumValue(rect3);
  }

}
