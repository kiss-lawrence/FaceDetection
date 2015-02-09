public class WeightedSample extends Sample {

  private double weight;
  
  public WeightedSample(IntegralImage img, Lable lable, double weight) {
    super(img, lable);
    this.weight = weight;
  }
  
  public void setWeight(double weight) {
    this.weight = weight;
  }
  
  public double getWeight() {
    return weight;
  }
  
}
