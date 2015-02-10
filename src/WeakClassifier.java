public class WeakClassifier implements Classifier {

  private Feature feature;
  private int parity, threshold;
  
  public WeakClassifier(Feature feature, int parity, int threshold) {
    this.feature = feature;
    this.parity = parity;
    this.threshold = threshold;
  }

  private double getScale(Rect windowRect) {
    return (double)windowRect.getWidth() / (double)C.SIZE_X;
  }
  
  private Rect getWorkingRect(Rect windowRect) {
    Rect featureRect = feature.getShape();
    double scale = getScale(windowRect);
    int x = (int)Math.floor(scale * featureRect.getX()) + windowRect.getX();
    int y = (int)Math.floor(scale * featureRect.getY()) + windowRect.getY();
    int w = (int)Math.floor(scale * featureRect.getWidth());
    int h = (int)Math.floor(scale * featureRect.getHeight());
    return new Rect(x, y, w, h);
  }
  
  @Override
  public Lable classify(IntegralImage img, Rect windowRect) {
    double scale = getScale(windowRect);
    Rect workingRect = getWorkingRect(windowRect);
    int scaledValue = feature.getValue(img, workingRect);
    int scaledThreshold = (int)Math.round(threshold * scale * scale);
    if (parity * scaledValue > parity * scaledThreshold) {
      return Lable.FACE;
    } else {
      return Lable.NONFACE;
    }
  }
  
  @Override
  public String toString() {
    return "Feature = " + feature.toString() + "\n" + 
          "Parity = " + parity + "\n" + 
          "Threshold = " + threshold;
  }

}
