import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class IntegralImage {

  private final int width, height;
  private final int[][] intensity;
  
  public IntegralImage(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) { }
    
    width = img.getWidth();
    height = img.getHeight();
    intensity = new int[width][height];
    storeInArray(img);
    preProcess();
  }
  
  private void storeInArray(BufferedImage img) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = img.getRGB(x, y);
        int r = (rgb >> 16) & 0xff, g = (rgb >> 8) & 0xff, b = rgb & 0xff;
        intensity[x][y] = (r + g + b) / 3;
      }
    }
  }
  
  private void preProcess() {
    for (int x = 0; x < width; x++) {
      for (int y = 1; y < height; y++) {
        intensity[x][y] += intensity[x][y - 1];
      }
    }
    for (int x = 1; x < width; x++) {
      for (int y = 0; y < height; y++) {
        intensity[x][y] += intensity[x - 1][y];
      }
    }
  }
  
  public boolean contains(Rect r) {
    int x = r.getX(), y = r.getY(), 
        x2 = x + r.getWidth() - 1, y2 = y + r.getHeight() - 1;
    return contains(x, y) && contains(x2, y2);
  }
  
  private boolean contains(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }
  
  // Pre: Rectangle is within the image.
  public int sumValue(Rect r) {
    int x = r.getX(), y = r.getY(), 
        x2 = x + r.getWidth() - 1, y2 = y + r.getHeight() - 1;
    return intensity[x2][y2] - (x > 0 ? intensity[x - 1][y2] : 0)
                             - (y > 0 ? intensity[x2][y - 1] : 0)
                             + (x > 0 && y > 0 ? intensity[x - 1][y - 1] : 0);
  }
  
}




















