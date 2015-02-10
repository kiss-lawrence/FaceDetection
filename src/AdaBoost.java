import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class AdaBoost {

  private ArrayList<WeightedSample> samples;
  private ArrayList<WeakClassifier> wkClassifiers;
  
  private XStream xstream;
  
  public AdaBoost() {
    xstream = new XStream();
    Utils.makeXstreamAlias(xstream);
    
    initialize();
  }
  
  private void initialize() {
    samples = new ArrayList<WeightedSample>();
    wkClassifiers = new ArrayList<WeakClassifier>();
    ArrayList<Sample> unweightedSamples = WeakTrainer.generateAllSamples();
    for (Sample us : unweightedSamples) {
      IntegralImage img = us.getImage();
      Lable lable = us.getLable();
      samples.add(new WeightedSample(img, lable, 
          lable == Lable.FACE ? 1d / C.FACE_NUM : 1d / C.NONFACE_NUM));
    }
    unweightedSamples = null;
    
    ArrayList<Object> uncastedClassifiers = 
        Utils.readXMLFile(xstream, C.WEAK_CLASSIFIERS_PATH);
    for (Object o : uncastedClassifiers) {
      wkClassifiers.add((WeakClassifier)o);
    }
    uncastedClassifiers = null;
  }
  
  public void getNewClassifier(StrongClassifier stCf) {
    normalizeWeights();
    
    double minErr = 1000000d;
    WeakClassifier chosen = null;
    ArrayList<Integer> correctOnes = null; 
    for (WeakClassifier wkCf : wkClassifiers) {
      ArrayList<Integer> temCorrectOnes = new ArrayList<Integer>();
      double sumErr = 0d; 
      for (int j = 0; j < samples.size(); j++) {
        WeightedSample ws = samples.get(j);
        if (wkCf.classify(ws.getImage(), C.STD_RECT) != ws.getLable()) {
          sumErr += ws.getWeight();
        } else {
          temCorrectOnes.add(j);
        }
      }
      if (sumErr < minErr) {
        minErr = sumErr;
        chosen = wkCf;
        correctOnes = temCorrectOnes;
      }
    }
    
    stCf.addClassifier(chosen, Math.log(1d / minErr));
    updateWeights(minErr, correctOnes);
  }
  
  private void normalizeWeights() {
    double sum = 0d;
    for (WeightedSample s : samples) {
      sum += s.getWeight();
    }
    for (WeightedSample s : samples) {
      s.setWeight(s.getWeight() / sum);
    }
  }
  
  private void updateWeights(double minErr, ArrayList<Integer> correctOnes) {
    for (int i : correctOnes) {
      WeightedSample ws = samples.get(i);
      ws.setWeight(ws.getWeight() * (minErr / (1d - minErr)));
    }
  }
  
}



















