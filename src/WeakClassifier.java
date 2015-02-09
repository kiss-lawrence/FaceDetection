public class WeakClassifier implements Classifier {

  private Feature feature;
  private int parity, threshold;
  
  public WeakClassifier(Feature feature, int parity, int threshold) {
    this.feature = feature;
    this.parity = parity;
    this.threshold = threshold;
  }
  
  @Override
  public Lable classify(IntegralImage img, double s) {
    Feature scaledFeature = feature.scale(s);
    int scaledValue = scaledFeature.getValue(img);
    int scaledThreshold = (int)Math.round(threshold * s * s); 
    if (parity * scaledValue > parity * scaledThreshold) {
      return Lable.FACE;
    } else {
      return Lable.NONFACE;
    }
  }

}
