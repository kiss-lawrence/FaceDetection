import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.thoughtworks.xstream.XStream;

public class Utils {

  public static void makeXstreamAlias(XStream xstream) {
    xstream.alias("featureA", FeatureA.class);
    xstream.alias("featureB", FeatureB.class);
    xstream.alias("featureC", FeatureC.class);
    xstream.alias("featureD", FeatureD.class);
    xstream.alias("featureE", FeatureE.class);
    
    xstream.alias("weakClassifier", WeakClassifier.class);
  }
  
  public static void printTime(PrintStream output, String forWhat, long mili) {
    long seconds = mili / 1000;
    long hrs = seconds / 3600;
    long mins = seconds % 3600 / 60;
    long snds = seconds % 60;
    output.println(forWhat + " = " + hrs + "hrs " + mins + "mins " + snds + "snds");
  }
  
  public static ArrayList<Object> readXMLFile(XStream xstream, String path) {
    Scanner input = null;
    try {
      input = new Scanner(new File(path));
    } catch (FileNotFoundException e) { }
    input.useDelimiter("\n{2}");
    ArrayList<Object> ret = new ArrayList<Object>();
    
    while (input.hasNext()) {
      String xml = input.next();
      ret.add(xstream.fromXML(xml));
    }
    input.close();
    return ret;
  }
  
  public static BufferedImage readBufferedImage(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) { }
    return img;
  }
  
}
