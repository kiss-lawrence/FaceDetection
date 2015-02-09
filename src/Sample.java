enum Lable {
  FACE, NONFACE;
}

public class Sample {

  protected IntegralImage img;
  protected Lable lable;
  
  public Sample(IntegralImage img, Lable lable) {
    this.img = img;
    this.lable = lable;
  }
  
  public IntegralImage getImage() {
    return img;
  }
  
  public Lable getLable() {
    return lable;
  }
  
}
