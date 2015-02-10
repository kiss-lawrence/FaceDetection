import java.util.ArrayList;

public class Detector {

  private IntegralImage img;
  private Classifier cf;
  
  private static final double MIN_DIST_BETWEEN_FACES = 10.0d;
  private double distThreshold;
  
  private static final double scaleFactor = 1.25d;
  private static final double moveFactor = 1.5d;
  private static final int scanTimes = 10;
  
  public Detector(String path, Classifier cf) {
    img = new IntegralImage(path);
    this.cf = cf;
    this.distThreshold = MIN_DIST_BETWEEN_FACES;
  }
  
  public void setDistThreshold(double newVal) {
    distThreshold = newVal;
  }
  
  public void setClassifier(Classifier cf) {
    this.cf = cf;
  }
  
  public ArrayList<Rect> getFaces() {
    ArrayList<Rect> ret = new ArrayList<Rect>();
    double scale = 1.0d;
    int count = 0;
    for (int t = 0; t < scanTimes; t++) {
      scale *= scaleFactor;
      double move = scale * moveFactor;
      
      int width = (int)Math.round(C.SIZE_X * scale);
      int height = (int)Math.round(C.SIZE_Y * scale);
      for (int i = 0; ; i++) {
        int x = (int)Math.round(move * i);
        if (x + width - 1 >= img.getWidth()) break;
        for (int j = 0; ; j++) {
          int y = (int)Math.round(move * j);
          if (y + height - 1 >= img.getHeight()) break;
          
          Rect window = new Rect(x, y, width, height);
          count++;
          if (cf.classify(img, window) == Lable.FACE) {
            ret.add(window);
          }
        }
      }
    }
    System.out.println(count);
    System.out.println(ret.size());
    return combineFaces(ret);
  }
  
  private ArrayList<Rect> combineFaces(ArrayList<Rect> faces) {
    ArrayList<Rect> ret = new ArrayList<Rect>();
    outer: for (int i = 0; i < faces.size(); i++) {
      Rect unchecked = faces.get(i);
      for (int j = 0; j < ret.size(); j++) {
        Rect checked = ret.get(j);
        double dist = unchecked.getCenter().distWith(checked.getCenter());
        if (dist <= distThreshold) {
          ret.set(j, checked.meanRect(unchecked));
          continue outer;
        }
      }
      ret.add(unchecked);
    }
    return ret;
  }
  
  public static void main(String[] args) {
    
  }

}
