import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;


public class Main {

  public static void main(String[] args) {
    XStream xstream = new XStream();
    Utils.makeXstreamAlias(xstream);
    ArrayList<Object> file = Utils.readXMLFile(xstream, C.WEAK_CLASSIFIERS_PATH);
    WeakClassifier wk = (WeakClassifier)file.get(0);
    Detector detector = new Detector("faceTestingData/addams-family.gif", wk);
    System.out.println(detector.getFaces().size());
  }

}
