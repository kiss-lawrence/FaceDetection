public class FeatureE extends Feature {

  public FeatureE(Rect shape) {
    super(shape);
    type = 'E';
  }
  
  @Override
  public int getValue(IntegralImage img, Rect workingRect) {
    int x = workingRect.getX(), y = workingRect.getY(), 
        w = workingRect.getWidth(), h = workingRect.getHeight();
    Rect workingRect1 = new Rect(x + w, y, w, h);
    Rect workingRect2 = new Rect(x, y + h, w, h);
    Rect workingRect3 = new Rect(x, y, w, h);
    Rect workingRect4 = new Rect(x + w, y + h, w, h);
    return img.sumValue(workingRect1) + img.sumValue(workingRect2) 
        - img.sumValue(workingRect3) - img.sumValue(workingRect4);
  }

}
