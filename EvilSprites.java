/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Game enemy sprites
  *********************************************************/

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public class EvilSprites {
  
  // instance variables
  private BufferedImage evilSpriteBufferImage;
  private File image1, image2;     // evil sprite image files
  public  int x, y;                // position (needs public access)
  public  double sin ,cos, theta;  // position adjustment (needs public access)
  public  boolean isAlive;         // whether sprite is alive or not (needs public access)
  
  // An affine transform is a transformation such as translate,
  // rotate, scale, or shear in which parallel lines remain 
  // parallel even after being transformed.
  private AffineTransform at;
  
  // first constructor for evil sprite #1
  public EvilSprites() {
    this.evilSpriteBufferImage = evilSpriteBufferImage;
    this.image1 = new File("evil1.png");
    this.isAlive = true;
    this.x = x;
    this.y = y;
    this.sin = sin;
    this.cos = cos;
    this.theta = theta;
    this.at = new AffineTransform();
    
    // load the sprite image
    try {
      this.evilSpriteBufferImage = ImageIO.read(this.image1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (this.evilSpriteBufferImage == null) {
      this.evilSpriteBufferImage.createGraphics();
    }
    
    this.setLocation();
  }
  
  // second constructor for evil sprite #2
  public EvilSprites(int i) {
    this.evilSpriteBufferImage = evilSpriteBufferImage;
    this.image2 = new File("evil2.png");
    this.isAlive = true;
    this.x = x;
    this.y = y;
    this.sin = sin;
    this.cos = cos;
    this.theta = theta;
    this.at = new AffineTransform();
    
    // load the sprite image
    try {
      this.evilSpriteBufferImage = ImageIO.read(this.image2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (this.evilSpriteBufferImage == null) {
      this.evilSpriteBufferImage.createGraphics();
    }
    
    this.setLocation();
  }
  
  // get the BufferedImage
  public BufferedImage getEvilSpriteBufferImage() {
    return this.evilSpriteBufferImage;
  }
  
  // return a rectangle with the BufferedImage
  public Rectangle getRectangleBounds() {
    return new Rectangle(this.x, this.y, this.evilSpriteBufferImage.getWidth() - 5, this.evilSpriteBufferImage.getHeight() - 5);
  }
  
  // draw the evil sprites
  public void draw(Graphics2D g2d) {
    at.setToIdentity();
    at.rotate(theta, x + 50, y + 40);
    at.translate(x, y);
    g2d.drawImage(evilSpriteBufferImage, at, null);
  }
  
  // set the evil sprites at random location on the screen
  public void setLocation() {
    int i = (int) (Math.random()*2 +1);
    int j = (int) (Math.random()*2 +1); 
    if(i == 1) {
      this.x = (int) (Math.random()*220 + 1);
    } else {
      this.x = (int) (Math.random()*220 + 811);
    }  
    if(j == 1) {
      this.y = (int) (Math.random()*115 + 1);
    } else {
      this.y = (int) (Math.random()*115 + 496) ;
    }
  }
  
}