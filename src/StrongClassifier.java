import java.util.ArrayList;

public class StrongClassifier implements Classifier {

  private ArrayList<WeakClassifier> wkClassifiers;
  private ArrayList<Double> weights;
  private double sumWeights;
  private double threshold;
  
  public StrongClassifier() {
    wkClassifiers = new ArrayList<WeakClassifier>();
    weights = new ArrayList<Double>();
    sumWeights = 0d;
    threshold = 0d;
  }
  
  public void addClassifier(WeakClassifier wk, double weight) {
    wkClassifiers.add(wk);
    weights.add(weight);
    sumWeights += weight;
  }
  
  public double getThreshold() {
    return threshold;
  }
  
  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }
  
  public double getSumWeights() {
    return sumWeights;
  }
  
  @Override
  public Lable classify(IntegralImage img, Rect windowRect) {
    double sum = 0d;
    for (int i = 0; i < wkClassifiers.size(); i++) {
      WeakClassifier wkCf = wkClassifiers.get(i); 
      sum += wkCf.classify(img, windowRect) == Lable.FACE ? weights.get(i) : 0d;
    }
    if (sum > threshold) {
      return Lable.FACE;
    } else {
      return Lable.NONFACE; 
    }
  }

}
