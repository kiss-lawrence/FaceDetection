import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class WeakTrainer {
  
  private static XStream xstream = new XStream();
  
  public static ArrayList<Feature> generateAllFeatures() {
    ArrayList<Feature> ret = new ArrayList<Feature>();
    PrintStream output = null;
    try {
      output = new PrintStream(new File(C.FEATURE_PATH));
    } catch (FileNotFoundException e) { }
    
    for (int x = 0; x < C.SIZE_X; x++) {
      for (int y = 0; y < C.SIZE_Y; y++) {
        for (int w = 1; x + 2 * w - 1 < C.SIZE_X; w++) {
          for (int h = 1; y + h - 1 < C.SIZE_Y; h++) {
            Feature f = new FeatureA(new Rect(x, y, w, h));
            ret.add(f);
            output.println(xstream.toXML(f));
            output.println();
          }
        }
        for (int w = 1; x + w - 1 < C.SIZE_X; w++) {
          for (int h = 1; y + 2 * h - 1 < C.SIZE_Y; h++) {
            Feature f = new FeatureB(new Rect(x, y, w, h));
            ret.add(f);
            output.println(xstream.toXML(f)); output.println();
          }
        }
        for (int w = 1; x + 3 * w - 1 < C.SIZE_X; w++) {
          for (int h = 1; y + h - 1 < C.SIZE_Y; h++) {
            Feature f = new FeatureC(new Rect(x, y, w, h));
            ret.add(f);
            output.println(xstream.toXML(f)); output.println();
          }
        }
        for (int w = 1; x + w - 1 < C.SIZE_X; w++) {
          for (int h = 1; y + 3 * h - 1 < C.SIZE_Y; h++) {
            Feature f = new FeatureD(new Rect(x, y, w, h));
            ret.add(f);
            output.println(xstream.toXML(f)); output.println();
          }
        }
        for (int w = 1; x + 2 * w - 1 < C.SIZE_X; w++) {
          for (int h = 1; y + 2 * h - 1 < C.SIZE_Y; h++) {
            Feature f = new FeatureE(new Rect(x, y, w, h));
            ret.add(f);
            output.println(xstream.toXML(f)); output.println();
          }
        }
      }
    }
    output.close();
    return ret;
  }
  
  public static ArrayList<Sample> generateAllSamples() {
    ArrayList<Sample> ret = new ArrayList<Sample>();
    for (int i = 1; i <= C.FACE_NUM; i++) {
      String path = C.FACE_PATH + "face" + String.format("%04d", i) + ".gif";
      IntegralImage img = new IntegralImage(path);
      ret.add(new Sample(img, Lable.FACE));
    }
    for (int i = 1; i <= C.NONFACE_NUM; i++) {
      String path = C.NONFACE_PATH + "nonface" + String.format("%04d", i) + ".gif";
      IntegralImage img = new IntegralImage(path);
      ret.add(new Sample(img, Lable.NONFACE));
    }
    return ret;
  }
  
  public static ArrayList<Classifier> generateWeakClassifiers() {
    ArrayList<Classifier> ret = new ArrayList<Classifier>();
    ArrayList<Feature> features = generateAllFeatures();
    ArrayList<Sample> samples = generateAllSamples();
    
    PrintStream output = null;
    try {
      output = new PrintStream(new File(C.WEAK_CLASSIFIERS_PATH));
    } catch (FileNotFoundException e) { }
    
    for (Feature f : features) {
      int faceSum = 0, nonfaceSum = 0;
      for (Sample s : samples) {
        int val = f.getValue(s.getImage(), f.getShape());
        if (s.getLable() == Lable.FACE) {
          faceSum += val;
        } else {
          nonfaceSum += val;
        }
      }
      int faceAvg = faceSum / C.FACE_NUM;
      int nonfaceAvg = nonfaceSum / C.NONFACE_NUM;
      int threshold = (faceAvg + nonfaceAvg) / 2;
      int parity = faceAvg > nonfaceAvg ? 1 : -1;
      Classifier cf = new WeakClassifier(f, parity, threshold);
      output.println(xstream.toXML(cf)); output.println();
      ret.add(cf);
    }
    output.close();
    return ret;
  }
  
  public static void main(String[] args) {
    Utils.makeXstreamAlias(xstream);
    PrintStream output = null;
    try {
      output = new PrintStream(new File(C.TIME_PATH));
    } catch (FileNotFoundException e) { }
    
    long startTime = System.currentTimeMillis(); 
    generateWeakClassifiers();
    long timeElapsed = System.currentTimeMillis() - startTime;
    Utils.printTime(output, "Time for training weak classifiers", timeElapsed);
  }

}
