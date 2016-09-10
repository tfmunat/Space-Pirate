/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * The main player sprite
  *********************************************************/

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class HeroSprite {
  
  // instance variable
  private BufferedImage heroBufferImage;
  private File image;
  private AffineTransform at;
  public  int x, y;        // needs public access
  public  boolean isAlive; // needs public access
  
  // constructor
  public HeroSprite() {
    this.heroBufferImage = heroBufferImage;
    this.image = new File("hero.png");
    this.x = 485;
    this.y = 290;
    this.isAlive = true;
    this.at = new AffineTransform();
    
    // load the sprite image
    try{
      this.heroBufferImage = ImageIO.read(this.image);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    if (this.heroBufferImage == null) {
      this.heroBufferImage.createGraphics();
    }
  }
  
  // get the BufferedImage
  public BufferedImage getHeroBufferImage() {
    return this.heroBufferImage;
  }
  
  // return a rectangle with the BufferedImage
  public Rectangle getRectangleBounds() {
    return new Rectangle((this.x + 20), (y + 20), (this.heroBufferImage.getWidth() - 20) , (this.heroBufferImage.getHeight() - 20));
  }
  
  // set the given value to x
  public void setX(int x) {
    this.x = x;
  }
  
  // set the given value to y
  public void setY(int y) {
    this.y = y;
  }
  
  // draw the hero sprite
  public void draw(Graphics2D g2d, double theta){
    at.setToIdentity();
    at.rotate(theta, x + 50, y + 50);
    at.translate(x, y);
    g2d.drawImage(heroBufferImage, at, null);
  }
  
}