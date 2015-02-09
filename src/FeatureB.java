public class FeatureB extends Feature {

  public FeatureB(Rect rect) {
    super(rect);
    type = 'B';
  }

  @Override
  public Feature scale(double s) {
    return new FeatureB(rect.scale(s));
  }  
  
  @Override
  public int getValue(IntegralImage img) {
    int x = rect.getX(), y = rect.getY(),
        w = rect.getWidth(), h = rect.getHeight();
    Rect rect1 = new Rect(x, y + h, w, h);
    Rect rect2 = new Rect(x, y, w, h);
    return img.sumValue(rect1) - img.sumValue(rect2);
  }

}
