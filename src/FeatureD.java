public class FeatureD extends Feature {

  public FeatureD(Rect rect) {
    super(rect);
    type = 'D';
  }

  @Override
  public Feature scale(double s) {
    return new FeatureD(rect.scale(s));
  }
  
  @Override
  public int getValue(IntegralImage img) {
    int x = rect.getX(), y = rect.getY(), 
        w = rect.getWidth(), h = rect.getHeight();
    Rect rect1 = new Rect(x, y, w, h);
    Rect rect2 = new Rect(x, y + 2 * h, w, h);
    Rect rect3 = new Rect(x, y + h, w, h);
    return img.sumValue(rect1) + img.sumValue(rect2) - img.sumValue(rect3);
  }

}
