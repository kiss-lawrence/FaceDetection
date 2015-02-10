public class FeatureD extends Feature {

  public FeatureD(Rect shape) {
    super(shape);
    type = 'D';
  }
  
  @Override
  public int getValue(IntegralImage img, Rect workingRect) {
    int x = workingRect.getX(), y = workingRect.getY(), 
        w = workingRect.getWidth(), h = workingRect.getHeight();
    Rect workingRect1 = new Rect(x, y, w, h);
    Rect workingRect2 = new Rect(x, y + 2 * h, w, h);
    Rect workingRect3 = new Rect(x, y + h, w, h);
    return img.sumValue(workingRect1) + img.sumValue(workingRect2) 
        - img.sumValue(workingRect3);
  }

}
