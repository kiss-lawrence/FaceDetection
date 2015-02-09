public class Rect {
  
  private final int x, y, width, height;
  
  public Rect(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  public Rect scale(double s) {
    return new Rect((int)Math.round(x * s), (int)Math.round(y * s), 
        (int)Math.round(width * s), (int) Math.round(height * s));
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }

}
