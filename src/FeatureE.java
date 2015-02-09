public class FeatureE extends Feature {

  public FeatureE(Rect rect) {
    super(rect);
    type = 'E';
  }

  @Override
  public Feature scale(double s) {
    return new FeatureE(rect.scale(s));
  }
  
  @Override
  public int getValue(IntegralImage img) {
    int x = rect.getX(), y = rect.getY(), 
        w = rect.getWidth(), h = rect.getHeight();
    Rect rect1 = new Rect(x + w, y, w, h);
    Rect rect2 = new Rect(x, y + h, w, h);
    Rect rect3 = new Rect(x, y, w, h);
    Rect rect4 = new Rect(x + w, y + h, w, h);
    return img.sumValue(rect1) + img.sumValue(rect2) 
        - img.sumValue(rect3) - img.sumValue(rect4);
  }

}
